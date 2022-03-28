package com.turkcell.rentacar.business.concretes;


import com.turkcell.rentacar.business.abstracts.CarMaintenanceService;
import com.turkcell.rentacar.business.abstracts.RentService;
import com.turkcell.rentacar.business.constants.messages.BusinessMessages;
import com.turkcell.rentacar.business.dtos.getDto.GetCarMaintenanceDto;
import com.turkcell.rentacar.business.dtos.listDto.CarMaintenanceListDto;
import com.turkcell.rentacar.business.requests.create.CreateCarMaintenanceRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteCarMaintenanceRequest;
import com.turkcell.rentacar.business.requests.update.UpdateCarMaintenanceRequest;
import com.turkcell.rentacar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.*;
import com.turkcell.rentacar.dataAccess.abstracts.CarMaintenanceDao;
import com.turkcell.rentacar.entities.concretes.CarMaintenance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarMaintenanceManager implements CarMaintenanceService {

    private CarMaintenanceDao carMaintenanceDao;
    private ModelMapperService modelMapperService;
    private RentService rentService;

    @Autowired
    @Lazy
    public CarMaintenanceManager(CarMaintenanceDao carMaintenanceDao, ModelMapperService modelMapperService,RentService rentService) {
        this.carMaintenanceDao = carMaintenanceDao;
        this.modelMapperService = modelMapperService;
        this.rentService = rentService;

    }


    @Override
    public DataResult<List<CarMaintenanceListDto>> getAll() {
        List<CarMaintenance> result = this.carMaintenanceDao.findAll();
        List<CarMaintenanceListDto> response = result.stream()
                .map(carMaintenance -> this.modelMapperService.forDto()
                        .map(carMaintenance, CarMaintenanceListDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<CarMaintenanceListDto>>(response, BusinessMessages.CAR_MAINTENANCE_LISTED_SUCCESSFULLY);
    }

    @Override
    public Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest) {


        checkIfCarIsAlreadyInMaintenanceIsSuccess(createCarMaintenanceRequest.getCarId());
        checkIfCarIsAlreadyInRent(createCarMaintenanceRequest.getCarId());
            CarMaintenance carMaintenance = this.modelMapperService.forRequest()
                    .map(createCarMaintenanceRequest, CarMaintenance.class);

            this.carMaintenanceDao.save(carMaintenance);

            return new SuccessResult(BusinessMessages.CAR_MAINTENANCE_ADDED_SUCCESSFULLY);

    }


    @Override
    public Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
        checkIfCarMaintenanceIdExist(updateCarMaintenanceRequest.getId());
        CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(updateCarMaintenanceRequest, CarMaintenance.class);
        this.carMaintenanceDao.save(carMaintenance);
        return new SuccessResult(BusinessMessages.CAR_MAINTENANCE_UPDATED_SUCCESSFULLY);
    }

    @Override
    public Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) {
        checkIfCarMaintenanceIdExist(deleteCarMaintenanceRequest.getId());
        this.carMaintenanceDao.deleteById(deleteCarMaintenanceRequest.getId());
        return new SuccessResult(BusinessMessages.CAR_MAINTENANCE_DELETED_SUCCESSFULLY);
    }


    @Override
    public DataResult<List<CarMaintenanceListDto>> getByCarId(int id) {
        List<CarMaintenance> result = this.carMaintenanceDao.getByCar_CarId(id);
        List<CarMaintenanceListDto> response = result.stream()
                .map(carMaintenance -> this.modelMapperService
                        .forDto()
                        .map(carMaintenance, CarMaintenanceListDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<CarMaintenanceListDto>>(response, BusinessMessages.CAR_MAINTENANCES_FOR_CAR_LISTED_SUCCESSFULLY);
    }


    public Result checkIfCarMaintenanceIdExist(int id) throws BusinessException {
        for(CarMaintenance carMaintenance : this.carMaintenanceDao.getByCar_CarId(id)){
            if(carMaintenance.getReturnDate() != null){
                return new SuccessResult(BusinessMessages.CAR_NOT_IN_MAINTENANCE);
            }
        }
        return new ErrorResult(BusinessMessages.CAR_ALREADY_IN_MAINTENANCE);
    }

    private void checkIfCarIsAlreadyInMaintenanceIsSuccess(int id) {

        if(this.carMaintenanceDao.existsByCar_CarId(id)){
            if(!checkIfCarMaintenanceIdExist(id).isSuccess()){
                throw new BusinessException(BusinessMessages.CAR_ALREADY_IN_MAINTENANCE);
            }
        }

    }



    private void checkIfCarIsAlreadyInRent(int carId){
        if(this.rentService.checkIfCarIsRented(carId).isSuccess()){
            throw new BusinessException(BusinessMessages.CAR_ALREADY_IN_RENT);
        }
    }




}
