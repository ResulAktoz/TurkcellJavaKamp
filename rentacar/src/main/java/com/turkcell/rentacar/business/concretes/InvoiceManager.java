package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.InvoiceService;
import com.turkcell.rentacar.business.abstracts.OrderedAdditionalServiceService;
import com.turkcell.rentacar.business.abstracts.RentService;
import com.turkcell.rentacar.business.dtos.getDto.GetInvoiceDto;
import com.turkcell.rentacar.business.dtos.listDto.InvoiceListDto;
import com.turkcell.rentacar.business.requests.create.CreateInvoiceRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteInvoiceRequest;
import com.turkcell.rentacar.business.requests.update.UpdateInvoiceRequest;
import com.turkcell.rentacar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.InvoiceDao;
import com.turkcell.rentacar.entities.concretes.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public Result add(CreateInvoiceRequest createInvoiceRequest) throws BusinessException {
        checkIfRentIdAlreadyExists(createInvoiceRequest.getRentId());
        Invoice invoice = this.modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);

        calculateAndSetTotalPrice(createInvoiceRequest.getRentId(), invoice);

        this.invoiceDao.save(invoice);

        return new SuccessResult("Fatura başarıyla eklendi.");
    }

    @Override
    public Result update(UpdateInvoiceRequest updateInvoiceRequest) throws BusinessException {
        checkIfInvoiceExists(updateInvoiceRequest.getInvoiceId());
        Invoice invoice = this.modelMapperService.forRequest().map(updateInvoiceRequest, Invoice.class);

        calculateAndSetTotalPrice(updateInvoiceRequest.getRentId(), invoice);
        this.invoiceDao.save(invoice);

        return new SuccessResult("Fatura başarıyla güncellendi.");

    }

    @Override
    public Result delete(DeleteInvoiceRequest deleteInvoiceRequest) throws BusinessException {
        checkIfInvoiceExists(deleteInvoiceRequest.getInvoiceId());
        this.invoiceDao.deleteById(deleteInvoiceRequest.getInvoiceId());

        return new SuccessResult("Fatura başarıyla kaldırıldı.");
    }

    @Override
    public DataResult<List<InvoiceListDto>> getAll() throws BusinessException {
        List<Invoice> result = this.invoiceDao.findAll();

        List<InvoiceListDto> response = result.stream()
                .map(invoice -> this.modelMapperService.forDto()
                        .map(invoice, InvoiceListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<InvoiceListDto>>(response, "Faturlara başarıyla listelendi.");
    }

    @Override
    public DataResult<GetInvoiceDto> getByInvoiceId(int invoiceId) throws BusinessException {
        checkIfInvoiceExists(invoiceId);
        Invoice invoice =this.invoiceDao.getById(invoiceId);

        GetInvoiceDto response = this.modelMapperService.forDto().map(invoice, GetInvoiceDto.class);
        return new SuccessDataResult<GetInvoiceDto>(response, "Faturalar listelendi.");
    }

    @Override
    public DataResult<List<InvoiceListDto>> getByUserId(int userId) throws BusinessException {
        checkIfUserExists(userId);

        List<Invoice> result = this.invoiceDao.getByUser_UserId(userId);

        List<InvoiceListDto> response = result.stream()
                .map(invoice -> this.modelMapperService.forDto()
                        .map(invoice, InvoiceListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<InvoiceListDto>>(response, "Kullanıcı faturaları listelendi.");
    }

    @Override
    public DataResult<List<InvoiceListDto>> findByCreationDayBetween(LocalDate startDate, LocalDate endDate) {
        List<Invoice> result = this.invoiceDao.findAllByCreationDateBetween(startDate, endDate);

        List<InvoiceListDto> response= result.stream()
                .map(invoice -> this.modelMapperService.forDto()
                        .map(invoice, InvoiceListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<InvoiceListDto>>(response, "Tarih aralığındaki faturalar listelendi.");


    }


    private void checkIfRentIdAlreadyExists(int rentId) throws BusinessException{
        if(this.invoiceDao.existsByRent_RentId(rentId)){
            throw new BusinessException("Bu id'ye ait bir kiralama faturası mevcut.");
        }
    }

    private void calculateAndSetTotalPrice(int rentId, Invoice invoice){
        double rentPrice = this.rentService.calculateRentPrice(rentId);

        double orderedServicePrice = this.orderedAdditionalServiceService.calculateOrderedServicePrice(rentId);

        double totalInvoicePrice = rentPrice + orderedServicePrice;

        invoice.setTotalPrice(totalInvoicePrice);
    }

    private void checkIfInvoiceExists(int invoiceId){
        if(!this.invoiceDao.existsById(invoiceId)){
            throw new BusinessException("Bu id'ye ait fatura bulunamadı");
        }
    }

    private void checkIfUserExists(int userId){
        if(!this.invoiceDao.findByUser_UserId(userId)){
            throw new BusinessException("Kullanıcı bulunamadı.");
        }
    }
}
