package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.getDto.GetCityDto;
import com.turkcell.rentacar.business.dtos.listDto.CityListDto;
import com.turkcell.rentacar.business.requests.create.CreateCityRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteCityRequest;
import com.turkcell.rentacar.business.requests.update.UpdateCityRequest;
import com.turkcell.rentacar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

import java.util.List;

public interface CityService {

    Result add(CreateCityRequest createCityRequest);
    Result update(UpdateCityRequest updateCityRequest);
    Result delete(DeleteCityRequest deleteCityRequest);

    DataResult<List<CityListDto>> getAll();
}
