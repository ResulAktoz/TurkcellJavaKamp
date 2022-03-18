package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.CarDamageService;
import com.turkcell.rentacar.business.dtos.listDto.CarDamageListDto;
import com.turkcell.rentacar.business.requests.create.CreateCarDamageRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteCarDamageRequest;
import com.turkcell.rentacar.business.requests.update.UpdateCarDamageRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.mapping.ModelMapperService;
import com.turkcell.rentacar.core.results.DataResult;
import com.turkcell.rentacar.core.results.Result;
import com.turkcell.rentacar.core.results.SuccessDataResult;
import com.turkcell.rentacar.core.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.CarDamageDao;
import com.turkcell.rentacar.entities.concretes.CarDamage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarDamageManager implements CarDamageService {

    private CarDamageDao carDamageDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public CarDamageManager(CarDamageDao carDamageDao, ModelMapperService modelMapperService) {
        this.carDamageDao = carDamageDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CreateCarDamageRequest createCarDamageRequest) {
        CarDamage carDamage = this.modelMapperService.forRequest()
                .map(createCarDamageRequest, CarDamage.class);

        this.carDamageDao.save(carDamage);

        return new SuccessResult("Hasar başarıyla eklendi.");

    }

    @Override
    public Result update(UpdateCarDamageRequest updateCarDamageRequest) {
        checkIfCarDamageExists(updateCarDamageRequest.getCarDamageId());
        CarDamage carDamage = this.modelMapperService.forRequest()
                .map(updateCarDamageRequest, CarDamage.class);

        this.carDamageDao.save(carDamage);

        return new SuccessResult("Hasar başarıyla güncellendi.");
    }

    @Override
    public Result delete(DeleteCarDamageRequest deleteCarDamageRequest) {
        checkIfCarDamageExists(deleteCarDamageRequest.getCarDamageId());
        this.carDamageDao.deleteById(deleteCarDamageRequest.getCarDamageId());

        return new SuccessResult("Hasar başarıyla kaldırıldı.");
    }

    @Override
    public DataResult<List<CarDamageListDto>> getAll() {
        List<CarDamage> result = this.carDamageDao.findAll();

        List<CarDamageListDto> response= result.stream()
                .map(carDamage -> this.modelMapperService.forDto()
                        .map(carDamage, CarDamageListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<CarDamageListDto>>(response, "Hasarlar başarıyla listelendi.");
    }

    private void checkIfCarDamageExists(int carDamageId){
        if(!this.carDamageDao.existsById(carDamageId)){
            throw new BusinessException("Bu id'ye ait hasar yok.");
        }

    }
}
