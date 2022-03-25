package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.CarDamageService;
import com.turkcell.rentacar.business.constants.messages.BusinessMessages;
import com.turkcell.rentacar.business.dtos.listDto.CarDamageListDto;
import com.turkcell.rentacar.business.requests.create.CreateCarDamageRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteCarDamageRequest;
import com.turkcell.rentacar.business.requests.update.UpdateCarDamageRequest;
import com.turkcell.rentacar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
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

        return new SuccessResult(BusinessMessages.CAR_DAMAGE_ADDED_SUCCESSFULLY);

    }

    @Override
    public Result update(UpdateCarDamageRequest updateCarDamageRequest) {

        CarDamage carDamage = this.modelMapperService.forRequest()
                .map(updateCarDamageRequest, CarDamage.class);

        this.carDamageDao.save(carDamage);

        return new SuccessResult(BusinessMessages.CAR_DAMAGE_UPDATED_SUCCESSFULLY);
    }

    @Override
    public Result delete(DeleteCarDamageRequest deleteCarDamageRequest) {

        this.carDamageDao.deleteById(deleteCarDamageRequest.getCarDamageId());

        return new SuccessResult(BusinessMessages.CAR_DAMAGE_DELETED_SUCCESSFULLY);
    }

    @Override
    public DataResult<List<CarDamageListDto>> getAll() {
        List<CarDamage> result = this.carDamageDao.findAll();

        List<CarDamageListDto> response= result.stream()
                .map(carDamage -> this.modelMapperService.forDto()
                        .map(carDamage, CarDamageListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<CarDamageListDto>>(response, BusinessMessages.CAR_DAMAGE_LISTED_SUCCESSFULLY);
    }


}
