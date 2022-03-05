package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.RentListDto;
import com.turkcell.rentacar.business.requests.CreateRentRequest;
import com.turkcell.rentacar.core.results.DataResult;
import com.turkcell.rentacar.core.results.Result;

import java.util.List;

public interface RentService {
    DataResult<List<RentListDto>> getAll();
    Result add(CreateRentRequest createRentRequest);
    DataResult<RentListDto> getByCarId(int carId);
}
