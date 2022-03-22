package com.turkcell.rentacar.business.concretes;


import com.turkcell.rentacar.business.dtos.listDto.CarMaintenanceListDto;
import com.turkcell.rentacar.business.dtos.getDto.GetCarMaintenanceDto;
import com.turkcell.rentacar.business.abstracts.CarMaintenanceService;
import com.turkcell.rentacar.business.abstracts.RentService;
import com.turkcell.rentacar.business.requests.create.CreateCarMaintenanceRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteCarMaintenanceRequest;
import com.turkcell.rentacar.business.requests.update.UpdateCarMaintenanceRequest;
import com.turkcell.rentacar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.results.*;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
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
        return new SuccessDataResult<List<CarMaintenanceListDto>>(response, "Veriler listelendi");
    }

    @Override
    public Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest) {
        this.rentService.checkIfCarIsRented(createCarMaintenanceRequest.getCarId());

        checkIfCarMaintenanceIdExist(createCarMaintenanceRequest.getCarId());

        checkIfCarIsAlreadyInMaintenanceIsSuccess(createCarMaintenanceRequest.getCarId());

            CarMaintenance carMaintenance = this.modelMapperService.forRequest()
                    .map(createCarMaintenanceRequest, CarMaintenance.class);

            this.carMaintenanceDao.save(carMaintenance);

            return new SuccessResult("Araba eklendi");

    }

    @Override
    public DataResult<GetCarMaintenanceDto> getByCarMaintenanceId(Integer id) {
        checkIfCarMaintenanceIdExist(id);

        CarMaintenance carMaintenance = this.carMaintenanceDao.getById(id);

        GetCarMaintenanceDto response = this.modelMapperService.forDto()
                .map(carMaintenance, GetCarMaintenanceDto.class);

        return new SuccessDataResult<GetCarMaintenanceDto>(response,"ID'ye göre listelendi");
    }

    @Override
    public Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
        checkIfCarMaintenanceIdExist(updateCarMaintenanceRequest.getId());
        CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(updateCarMaintenanceRequest, CarMaintenance.class);
        this.carMaintenanceDao.save(carMaintenance);
        return new SuccessResult("Bakım güncellendi");
    }

    @Override
    public Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) {
        checkIfCarMaintenanceIdExist(deleteCarMaintenanceRequest.getId());
        this.carMaintenanceDao.deleteById(deleteCarMaintenanceRequest.getId());
        return new SuccessResult("Bakım başarıyla silindi");
    }

    @Override
    public DataResult<List<CarMaintenanceListDto>> getListedCars() {
        List<CarMaintenance> result = this.carMaintenanceDao.findCarMaintenanceByReturnDateIsNull();
        List<CarMaintenanceListDto> response = result.stream()
                .map(carMaintenance -> this.modelMapperService
                        .forDto()
                        .map(carMaintenance, CarMaintenanceListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<CarMaintenanceListDto>>(response, "Veriler listelendi");

    }

    @Override
    public DataResult<List<CarMaintenanceListDto>> getByCarId(int id) {
        List<CarMaintenance> result = this.carMaintenanceDao.getByCar_CarId(id);
        List<CarMaintenanceListDto> response = result.stream()
                .map(carMaintenance -> this.modelMapperService
                        .forDto()
                        .map(carMaintenance, CarMaintenanceListDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<CarMaintenanceListDto>>(response,"Arabaya ait bakım bilgileri listelendi.");
    }


    public boolean checkIfCarMaintenanceIdExist(int id) throws BusinessException {
        if(!this.carMaintenanceDao.existsCarMaintenanceById(id)){
           throw new BusinessException("Bu id'ye kayıtlı bakım bulunamadı.");
        }
        return true;
    }

    private void checkIfCarIsAlreadyInMaintenanceIsSuccess(int id) {

       checkIfCarMaintenanceIdExist(id);

       throw new BusinessException("Araba zaten bakımda!");
    }


}
