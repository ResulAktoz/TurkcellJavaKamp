package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.listDto.CarDamageListDto;
import com.turkcell.rentacar.business.requests.create.CreateCarDamageRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteCarDamageRequest;
import com.turkcell.rentacar.business.requests.update.UpdateCarDamageRequest;
import com.turkcell.rentacar.core.results.DataResult;
import com.turkcell.rentacar.core.results.Result;

import java.util.List;

public interface CarDamageService {

    Result add(CreateCarDamageRequest createCarDamageRequest) ;
    Result update(UpdateCarDamageRequest updateCarDamageRequest);
    Result delete(DeleteCarDamageRequest deleteCarDamageRequest);

    DataResult<List<CarDamageListDto>> getAll();
}
