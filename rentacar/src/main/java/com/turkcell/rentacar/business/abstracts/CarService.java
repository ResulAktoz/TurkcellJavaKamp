package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.requests.update.UpdateCarKilometerInfoRequest;
import com.turkcell.rentacar.business.requests.update.UpdateCarRequest;
import com.turkcell.rentacar.business.dtos.listDto.CarListDto;
import com.turkcell.rentacar.business.dtos.getDto.GetCarDto;
import com.turkcell.rentacar.business.requests.create.CreateCarRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteCarRequest;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

public interface CarService {
	DataResult<List<CarListDto>> getAll();
    Result add(CreateCarRequest createCarRequest);
    DataResult<GetCarDto> getByCarId(int id);
    Result delete(DeleteCarRequest deleteCarRequest);
    Result update(UpdateCarRequest updateCarRequest);
    Result existByCarId(int id);
    Result updateKilometerInfo(UpdateCarKilometerInfoRequest updateCarKilometerInfoRequest);

    DataResult<List<CarListDto>> getAllPaged(int pageNumber, int pageSize);
    DataResult<List<CarListDto>> getAllSorted(String param);
    DataResult<List<CarListDto>> findByDailyPriceLessThan(double requestedPrice);
    DataResult<List<CarListDto>> findByDailyPriceBetween(double since, double until);
    

}
