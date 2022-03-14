package com.turkcell.rentacar.business.concretes;


import com.turkcell.rentacar.business.dtos.listDto.CarMaintenanceListDto;
import com.turkcell.rentacar.business.dtos.getDto.GetCarMaintenanceDto;
import com.turkcell.rentacar.business.dtos.listDto.RentListDto;
import com.turkcell.rentacar.business.abstracts.CarMaintenanceService;
import com.turkcell.rentacar.business.abstracts.RentService;
import com.turkcell.rentacar.business.requests.create.CreateCarMaintenanceRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteCarMaintenanceRequest;
import com.turkcell.rentacar.business.requests.update.UpdateCarMaintenanceRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.mapping.ModelMapperService;
import com.turkcell.rentacar.core.results.*;
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
    @Lazy
    @Autowired
    public CarMaintenanceManager(CarMaintenanceDao carMaintenanceDao, ModelMapperService modelMapperService, RentService rentService) {
        this.carMaintenanceDao = carMaintenanceDao;
        this.modelMapperService = modelMapperService;
        this.rentService = rentService;

    }


    @Override
    public DataResult<List<CarMaintenanceListDto>> getAll() {
        List<CarMaintenance> result = this.carMaintenanceDao.findAll();
        List<CarMaintenanceListDto> response = result.stream()
                .map(carMaintenance -> this.modelMapperService
                        .forDto()
                        .map(carMaintenance, CarMaintenanceListDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<CarMaintenanceListDto>>(response, "Veriler listelendi");
    }

    @Override
    public Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest) {
        checkCarRentStatus(createCarMaintenanceRequest.getCarId());
        checkIfCarIsAlreadyInMaintenanceIsSuccess(createCarMaintenanceRequest.getCarId());
            CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(createCarMaintenanceRequest, CarMaintenance.class);
            this.carMaintenanceDao.save(carMaintenance);
            return new SuccessResult("Araba eklendi");

    }

    @Override
    public DataResult<GetCarMaintenanceDto> getById(Integer id) {
        if (carMaintenanceDao.existsCarMaintenanceById(id)) {
            CarMaintenance carMaintenance = carMaintenanceDao.getById(id); //Optional<Car> car = carDao.findById(id);
            GetCarMaintenanceDto response = modelMapperService.forDto().map(carMaintenance, GetCarMaintenanceDto.class);
            return new SuccessDataResult<GetCarMaintenanceDto>(response, "Id'ye göre listelendi");
        } else {
            throw new BusinessException("Bu Id'de araç bulunamadı.");

        }
    }

    @Override
    public Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
        if (carMaintenanceDao.existsCarMaintenanceById(updateCarMaintenanceRequest.getId())) {
            CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(updateCarMaintenanceRequest, CarMaintenance.class);
            this.carMaintenanceDao.save(carMaintenance);
            return new SuccessResult("Bakım");
        } else {
            throw new BusinessException("Bakım bulunamadı.");


        }
    }

    @Override
    public Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) {
        if (this.carMaintenanceDao.existsCarMaintenanceById(deleteCarMaintenanceRequest.getId())) {
            this.carMaintenanceDao.deleteById(deleteCarMaintenanceRequest.getId());
            return new SuccessResult("Araba silindi.");
        } else {
            throw new BusinessException("Araba bulunamadı");

        }
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
        List<CarMaintenance> result = this.carMaintenanceDao.getByCar_Id(id);
        List<CarMaintenanceListDto> response = result.stream()
                .map(carMaintenance -> this.modelMapperService
                        .forDto()
                        .map(carMaintenance, CarMaintenanceListDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<CarMaintenanceListDto>>(response,"Arabaya ait bakım bilgileri listelendi.");
    }


    public Result  checkIfCarIsAlreadyInMaintenance(int id) {
        var result = this.carMaintenanceDao.getByCar_Id(id);
        for (CarMaintenance carMaintenance : result) {
            if (carMaintenance.getReturnDate() != null) {
                return new SuccessResult("Araba bakım dışı.");

            }

        }
        return new ErrorResult("Araba bakımda");
    }

    private void  checkIfCarIsAlreadyInMaintenanceIsSuccess(int id) {

       if(checkIfCarIsAlreadyInMaintenance(id).isSuccess()){
           throw new BusinessException("Araba bakımda");
       }
    }

    private void checkCarRentStatus(int carId){
        RentListDto rent = this.rentService.getByCarId(carId).getData();
        if(this.rentService.getByCarId(carId).getData().isRentStatus()){
            throw new BusinessException("Araba kirada olduğu için bakıma alınamaz!");
        }

    }
}
