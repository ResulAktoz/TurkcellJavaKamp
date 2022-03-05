package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.requests.UpdateCarRequest;
import com.turkcell.rentacar.business.dtos.CarListDto;
import com.turkcell.rentacar.business.dtos.GetCarDto;
import com.turkcell.rentacar.business.requests.CreateCarRequest;
import com.turkcell.rentacar.business.requests.DeleteCarRequest;
import com.turkcell.rentacar.core.results.DataResult;
import com.turkcell.rentacar.core.results.Result;

public interface CarService {
	DataResult<List<CarListDto>> getAll();
    Result add(CreateCarRequest createCarRequest);
    DataResult<GetCarDto> getByCarId(int id);
    Result delete(DeleteCarRequest deleteCarRequest);
    Result update(UpdateCarRequest updateCarRequest);
    Result existByCarId(int id);
    DataResult<List<CarListDto>> getAllPaged(int pageNumber, int pageSize);
    DataResult<List<CarListDto>> getAllSorted(String param);
    DataResult<List<CarListDto>> findByDailyPriceLessThan(double requestedPrice);
    DataResult<List<CarListDto>> findByDailyPriceBetween(double since, double until);
    

}
