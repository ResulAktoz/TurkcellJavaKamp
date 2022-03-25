package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.CityService;
import com.turkcell.rentacar.business.constants.messages.BusinessMessages;
import com.turkcell.rentacar.business.dtos.getDto.GetCityDto;
import com.turkcell.rentacar.business.dtos.listDto.CityListDto;
import com.turkcell.rentacar.business.requests.create.CreateCityRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteCityRequest;
import com.turkcell.rentacar.business.requests.update.UpdateCityRequest;
import com.turkcell.rentacar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.CityDao;
import com.turkcell.rentacar.entities.concretes.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityManager implements CityService {

    private CityDao cityDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public CityManager(CityDao cityDao, ModelMapperService modelMapperService) {
        this.cityDao = cityDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CreateCityRequest createCityRequest) {
        City city = this.modelMapperService.forRequest().map(createCityRequest, City.class);
        this.cityDao.save(city);
        return new SuccessResult(BusinessMessages.CITY_ADDED_SUCCESSFULLY);
    }

    @Override
    public Result update(UpdateCityRequest updateCityRequest){
        checkIfCityIdExists(updateCityRequest.getCityId());
        checkIfCityNameExists(updateCityRequest.getCityName());

        City city= this.modelMapperService.forRequest().map(updateCityRequest, City.class);
        this.cityDao.save(city);
        return new SuccessResult(BusinessMessages.CITY_UPDATED_SUCCESSFULLY);
    }

    @Override
    public Result delete(DeleteCityRequest deleteCityRequest) {
        checkIfCityIdExists(deleteCityRequest.getCityId());
        this.cityDao.deleteById(deleteCityRequest.getCityId());
        return new SuccessResult(BusinessMessages.CITY_DELETED_SUCCESSFULLY);
    }

    @Override
    public DataResult<List<CityListDto>> getAll() {
        List<City> result = this.cityDao.findAll();
        List<CityListDto> response = result.stream()
                .map(city -> this.modelMapperService.forDto()
                        .map(city, CityListDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<CityListDto>>(response, BusinessMessages.CITIES_LISTED_SUCCESSFULLY);
    }



    private void checkIfCityNameExists(String cityName) throws BusinessException{
        if(this.cityDao.existsCityByCityName(cityName)){
            throw new BusinessException(BusinessMessages.CITY_ALREADY_EXIST);

        }
    }

    private void checkIfCityIdExists(int id) throws BusinessException{
        if(!this.cityDao.existsById(id)){
            throw new BusinessException(BusinessMessages.CITY_NOT_FOUND);
        }
    }
}
