package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.InvoiceService;
import com.turkcell.rentacar.business.abstracts.OrderedAdditionalServiceService;
import com.turkcell.rentacar.business.abstracts.RentService;
import com.turkcell.rentacar.business.constants.messages.BusinessMessages;
import com.turkcell.rentacar.business.dtos.getDto.GetInvoiceDto;
import com.turkcell.rentacar.business.dtos.listDto.InvoiceListDto;
import com.turkcell.rentacar.business.requests.create.CreateInvoiceRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteInvoiceRequest;
import com.turkcell.rentacar.business.requests.update.UpdateInvoiceRequest;
import com.turkcell.rentacar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.*;
import com.turkcell.rentacar.dataAccess.abstracts.InvoiceDao;
import com.turkcell.rentacar.entities.concretes.Invoice;
import com.turkcell.rentacar.entities.concretes.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceManager implements InvoiceService {

    private InvoiceDao invoiceDao;
    private ModelMapperService modelMapperService;
    private RentService rentService;
    private OrderedAdditionalServiceService orderedAdditionalServiceService;
    @Autowired
    public InvoiceManager(InvoiceDao invoiceDao, ModelMapperService modelMapperService,
                          @Lazy RentService rentService, OrderedAdditionalServiceService orderedAdditionalServiceService) {
        this.invoiceDao = invoiceDao;
        this.modelMapperService = modelMapperService;
        this.rentService = rentService;
        this.orderedAdditionalServiceService = orderedAdditionalServiceService;
    }




    @Override
    public Result addForCustomer(CreateInvoiceRequest createInvoiceRequest) {
        Invoice invoice = this.modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);

        setInvoiceFields(createInvoiceRequest.getRentId(), invoice);

            this.invoiceDao.save(invoice);


            return new SuccessResult(BusinessMessages.INVOICE_ADDED_SUCCESSFULLY);

    }

    @Override
    public Result update(UpdateInvoiceRequest updateInvoiceRequest) {
        checkIfInvoiceExists(updateInvoiceRequest.getInvoiceId());
        Invoice invoice = this.modelMapperService.forRequest().map(updateInvoiceRequest, Invoice.class);

        invoice.setTotalPrice(calculateAndSetTotalPrice(updateInvoiceRequest.getRentId()));
        this.invoiceDao.save(invoice);


        return new SuccessResult(BusinessMessages.INVOICE_UPDATED_SUCCESSFULLY);

    }

    @Override
    public Result delete(DeleteInvoiceRequest deleteInvoiceRequest)  {

        Invoice invoice = this.modelMapperService.forRequest().map(deleteInvoiceRequest, Invoice.class);

        this.invoiceDao.delete(invoice);
        return new SuccessResult(BusinessMessages.INVOICE_DELETED_SUCCESSFULLY);
    }

    @Override
    public Invoice add(int rentId) {
        // create versiyonla aynı?
        Rent rent = this.rentService.getByRentId(rentId);
        Invoice invoice = new Invoice();

        setInvoiceFields(rentId, invoice);
        return this.invoiceDao.save(invoice);
    }

    @Override
    public DataResult<List<InvoiceListDto>> getAll(){
        List<Invoice> result = this.invoiceDao.findAll();

        List<InvoiceListDto> response = result.stream()
                .map(invoice -> this.modelMapperService.forDto()
                        .map(invoice, InvoiceListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<InvoiceListDto>>(response, BusinessMessages.INVOICE_LISTED_SUCCESSFULLY);
    }



    @Override
    public DataResult<List<InvoiceListDto>> getByUserId(int userId)  {
        checkIfUserExists(userId);

        List<Invoice> result = this.invoiceDao.getByUser_UserId(userId);

        List<InvoiceListDto> response = result.stream()
                .map(invoice -> this.modelMapperService.forDto()
                        .map(invoice, InvoiceListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<InvoiceListDto>>(response, BusinessMessages.USERS_INVOICES_LISTED_SUCCESSFULLY);
    }

    @Override
    public DataResult<List<InvoiceListDto>> findByCreationDayBetween(LocalDate startDate, LocalDate endDate) {
        List<Invoice> result = this.invoiceDao.findAllByCreationDateBetween(startDate, endDate);

        List<InvoiceListDto> response= result.stream()
                .map(invoice -> this.modelMapperService.forDto()
                        .map(invoice, InvoiceListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<InvoiceListDto>>(response, BusinessMessages.INVOICE_BETWEEN_START_DATE_AND_END_DATE_LISTED_SUCCESSFULLY);

    }

    @Override
    public void setInvoiceFields(int rentId, Invoice invoice){

        Rent rent = this.rentService.getByRentId(rentId);

        double totalPrice = calculateAndSetTotalPrice(rentId);

        invoice.setTotalPrice(totalPrice);

        invoice.setRentReturnDate(rent.getRentReturnDate());

        invoice.setRentStartDate(rent.getRentStartDate());

        invoice.setTotalRentDay((int) (ChronoUnit.DAYS.between(rent.getRentStartDate(),rent.getRentReturnDate())));

        invoice.setUser(rent.getUser());

    }


    private double calculateIfCityIsDifferentPrice(int rentId){
        if(this.rentService.checkIfReturnCityIsDifferentFromRentedCity(rentId).isSuccess()){
            return 750;
        }
        return 0;
    }

    private double calculateAndSetTotalPrice(int rentId){
        double rentPrice = this.rentService.calculateRentPrice(rentId);

        double orderedServicePrice = this.orderedAdditionalServiceService.calculateOrderedServicePrice(rentId);

        double differentCityPrice = calculateIfCityIsDifferentPrice(rentId);

        return (rentPrice + orderedServicePrice+ differentCityPrice);

    }



    private void checkIfInvoiceExists(int invoiceId){
        if(!this.invoiceDao.existsById(invoiceId)){
            throw new BusinessException(BusinessMessages.INVOICE_NOT_FOUND);
        }
    }

    private void checkIfUserExists(int userId){
        if(!this.invoiceDao.findByUser_UserId(userId)){
            throw new BusinessException(BusinessMessages.USER_NOT_FOUND);
        }
    }




}
