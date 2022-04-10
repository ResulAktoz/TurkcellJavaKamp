package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.*;
import com.turkcell.rentacar.business.constants.messages.BusinessMessages;
import com.turkcell.rentacar.business.dtos.getDto.GetRentDto;
import com.turkcell.rentacar.business.dtos.listDto.InvoiceListDto;
import com.turkcell.rentacar.business.dtos.listDto.RentListDto;
import com.turkcell.rentacar.business.requests.create.CreateRentRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteRentRequest;
import com.turkcell.rentacar.business.requests.update.UpdateEndedKilometerInfoRequest;
import com.turkcell.rentacar.business.requests.update.UpdateRentDeliveryDateRequest;
import com.turkcell.rentacar.business.requests.update.UpdateRentRequest;
import com.turkcell.rentacar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.*;
import com.turkcell.rentacar.dataAccess.abstracts.RentDao;
import com.turkcell.rentacar.entities.concretes.Invoice;
import com.turkcell.rentacar.entities.concretes.OrderedAdditionalService;
import com.turkcell.rentacar.entities.concretes.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentManager implements RentService {

    private RentDao rentDao;
    private ModelMapperService modelMapperService;
    private CarMaintenanceService carMaintenanceService;
    private CarService carService;
    private IndividualCustomerService individualCustomerService;
    private CorporateCustomerService corporateCustomerService;
    private OrderedAdditionalServiceService orderedAdditionalServiceService;


    @Autowired
    public RentManager(RentDao rentDao, ModelMapperService modelMapperService,
                       @Lazy CarMaintenanceService carMaintenanceService, CarService carService,
                       @Lazy IndividualCustomerService individualCustomerService, @Lazy CorporateCustomerService corporateCustomerService,
                       @Lazy OrderedAdditionalServiceService orderedAdditionalServiceService) {
        this.rentDao = rentDao;
        this.modelMapperService = modelMapperService;
        this.carMaintenanceService = carMaintenanceService;
        this.carService = carService;
        this.individualCustomerService = individualCustomerService;
        this.corporateCustomerService = corporateCustomerService;
        this.orderedAdditionalServiceService = orderedAdditionalServiceService;

    }


    @Override
    public DataResult<Rent> addForCorporateCustomer(CreateRentRequest createRentRequest){
        checkIfCarIsAlreadyInMaintenance(createRentRequest.getCarId());
        checkIfCarIsRented(createRentRequest.getCarId());
        this.corporateCustomerService.checkIfCorporateCustomerExistById(createRentRequest.getUserId());

        Rent rent = this.modelMapperService.forDto()
                .map(createRentRequest, Rent.class);

        rent.setOrderedAdditionalServices(this.orderedAdditionalServiceService.findOrderedAdditionalServicesByRent_RentId(rent.getRentId()));
        rent.setStartedKilometerInfo(rent.getStartedKilometerInfo());

        this.rentDao.save(rent);

        return new SuccessDataResult<Rent>(BusinessMessages.RENT_ADDED_SUCCESSFULLY);

    }

    @Override
    public DataResult<Rent> addForIndividualCustomer(CreateRentRequest createRentRequest) {
        checkIfCarIsAlreadyInMaintenance(createRentRequest.getCarId());
        checkIfCarIsRented(createRentRequest.getCarId());
        this.individualCustomerService.checkIfIndividualCustomerExistsById(createRentRequest.getUserId());


        Rent rent = this.modelMapperService.forDto()
                .map(createRentRequest, Rent.class);
        rent.setStartedKilometerInfo(rent.getStartedKilometerInfo());

        this.rentDao.saveAndFlush(rent);

        rent.setOrderedAdditionalServices(this.orderedAdditionalServiceService.findOrderedAdditionalServicesByRent_RentId(rent.getRentId()));
        rent.setStartedKilometerInfo(this.carService.getByCarId(createRentRequest.getCarId()).getData().getKilometerInfo());

        Rent newRent = this.rentDao.save(rent);
        return new SuccessDataResult<Rent>(BusinessMessages.RENT_ADDED_SUCCESSFULLY);
    }

    @Override
    public Result update(UpdateRentRequest updateRentRequest)  {

        Rent rent = this.modelMapperService.forRequest().map(updateRentRequest, Rent.class);
        this.rentDao.save(rent);

        return new SuccessResult(BusinessMessages.RENT_UPDATED_SUCCESSFULLY);
    }

    @Override
    public Result delete(DeleteRentRequest deleteRentRequest){
        Rent rent = this.modelMapperService.forRequest().
                map(deleteRentRequest, Rent.class);
        this.rentDao.delete(rent);

        return new SuccessResult(BusinessMessages.RENT_DELETED_SUCCESSFULLY);
    }

    @Override
    public Result updateEndedKilometer(UpdateEndedKilometerInfoRequest updateEndedKilometerInfoRequest) {

        this.rentDao.updateEndedKilometerInfoToRentByRentId(updateEndedKilometerInfoRequest.getRentId(),
                updateEndedKilometerInfoRequest.getEndedKilometerInfo());

        return new SuccessResult(BusinessMessages.ENDED_KILOMETER_INFO_UPDATED_SUCCESSFULLY);
    }

    @Override
    public Result updateRentDeliveryDate(UpdateRentDeliveryDateRequest updateRentDeliveryDateRequest) {
        Rent rent = this.modelMapperService.forRequest().map(updateRentDeliveryDateRequest, Rent.class);
        checkDeliveryDateAndReturnDateIsDifferent(updateRentDeliveryDateRequest.getRentId());
            this.rentDao.updateDeliveryDateToRentByRentId(updateRentDeliveryDateRequest.getRentId(),
                    updateRentDeliveryDateRequest.getDeliveryDate());
            return new SuccessResult(BusinessMessages.RENT_UPDATED_SUCCESSFULLY);

    }

    @Override
    public Result checkIfReturnCityIsDifferentFromRentedCity(int rentId) {
        Rent rent = this.rentDao.getById(rentId);

        if(!rent.getRentedCity().equals(rent.getReturnCity())){
            return new SuccessResult(BusinessMessages.CITIES_ARE_DIFFERENT);
        }
        return new ErrorResult(BusinessMessages.CITIES_ARE_SAME);
    }

    @Override
    public DataResult<List<RentListDto>> getAll(){
        List<Rent> rents = this.rentDao.findAll();

        List<RentListDto> response= rents.stream()
                .map(rent -> this.modelMapperService.forDto()
                        .map(rent, RentListDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<RentListDto>>(response, BusinessMessages.RENTS_LISTED_SUCCESSFULLY);
    }

    @Override
    public DataResult<GetRentDto> getById(int rentId) {
        Rent rent = this.rentDao.getById(rentId);
        GetRentDto response = this.modelMapperService.forDto().map(rent, GetRentDto.class);

        return new SuccessDataResult<GetRentDto>(response, BusinessMessages.RENT_LISTED_SUCCESSFULLY);
    }






    @Override
    public Result checkIfCarIsRented(int carId){

        for (Rent rent: this.rentDao.getByCar_CarId(carId)) {
            if(!this.rentDao.existsById(carId)){
                return new SuccessResult(BusinessMessages.CAR_NOT_IN_RENT);

            }
        }
        return new ErrorResult(BusinessMessages.CAR_ALREADY_IN_RENT);

    }

    @Override
    public Rent getByRentId(int rentId) {
        checkIfRentIdExists(rentId);
        return this.rentDao.getById(rentId);
    }

    /*@Override
    public void checkIfIndividualCustomerExists(int userId) {
       if(this.individualCustomerService.getByIndividualCustomerId(userId).getData() == null){
           throw new BusinessException(BusinessMessages.INDIVIDUAL_CUSTOMER_NOT_FOUND);
       }
    }

    @Override
    public void checkIfCorporateCustomerExists(int userId) {
        if(this.corporateCustomerService.getByCorporatelCustomerId(userId).getData() == null){
            throw new BusinessException(BusinessMessages.CORPORATE_CUSTOMER_NOT_FOUND);
        }
    }*/


    @Override
    public double calculateRentPrice(int rentId) {

        checkDeliveryDateAndReturnDateIsDifferent(rentId);

        long usageTime = ChronoUnit.DAYS.between(this.rentDao.getById(rentId).getRentStartDate(), this.rentDao.getById(rentId).getRentReturnDate());

        double dailyPrice = (this.rentDao.getById(rentId).getCar().getDailyPrice());

        double rentPrice = (usageTime * dailyPrice);


        return rentPrice;



    }

    private void checkIfCarIsAlreadyInMaintenance(int id) throws BusinessException{

       if(this.carMaintenanceService.checkIfCarMaintenanceIdExist(id).isSuccess()){
           throw new BusinessException(BusinessMessages.CAR_ALREADY_IN_MAINTENANCE);
       }
    }

    private Result checkDeliveryDateAndReturnDateIsDifferent(int rentId){

        if(this.rentDao.getById(rentId).getDeliveryDate()!= null && this.rentDao.getById(rentId).getDeliveryDate().isAfter(this.rentDao.getById(rentId).getRentReturnDate())){
            this.rentDao.getById(rentId).setRentReturnDate(this.rentDao.getById(rentId).getDeliveryDate());

        }else{
            this.rentDao.getById(rentId).setDeliveryDate(this.rentDao.getById(rentId).getRentReturnDate());
        }
        return new SuccessResult(BusinessMessages.RENT_DELIVERY_DATE_DID_NOT_CHANGE);
    }

    private void checkIfRentIdExists(int rentId){
        if(!this.rentDao.existsById(rentId)){
            throw new BusinessException(BusinessMessages.RENT_NOT_FOUND);
        }
    }






}
