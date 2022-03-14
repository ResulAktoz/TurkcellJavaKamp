package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.getDto.GetCityDto;
import com.turkcell.rentacar.business.dtos.listDto.CityListDto;
import com.turkcell.rentacar.business.requests.create.CreateCityRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteCityRequest;
import com.turkcell.rentacar.business.requests.update.UpdateCityRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.results.DataResult;
import com.turkcell.rentacar.core.results.Result;

import java.util.List;

public interface CityService {

    Result add(CreateCityRequest createCityRequest) throws BusinessException;
    Result update(UpdateCityRequest updateCityRequest) throws BusinessException;
    Result delete(DeleteCityRequest deleteCityRequest) throws BusinessException;

    DataResult<List<CityListDto>> getAll() throws BusinessException;
    DataResult<GetCityDto> getById(Integer id) throws BusinessException;
}
