package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.CityService;
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
    public Result add(CreateCityRequest createCityRequest) throws BusinessException {
        City city = this.modelMapperService.forRequest().map(createCityRequest, City.class);
        this.cityDao.save(city);
        return new SuccessResult("Şehir başarıyla eklendi.");
    }

    @Override
    public Result update(UpdateCityRequest updateCityRequest) throws BusinessException {
        checkIfCityIdExists(updateCityRequest.getCityId());
        checkIfCityNameExists(updateCityRequest.getCityName());

        City city= this.modelMapperService.forRequest().map(updateCityRequest, City.class);
        this.cityDao.save(city);
        return new SuccessResult("Şehir ismi başarıyla güncellendi.");
    }

    @Override
    public Result delete(DeleteCityRequest deleteCityRequest) throws BusinessException {
        checkIfCityIdExists(deleteCityRequest.getCityId());
        this.cityDao.deleteById(deleteCityRequest.getCityId());
        return new SuccessResult("Şehir başarıyla kaldırıldı.");
    }

    @Override
    public DataResult<List<CityListDto>> getAll() throws BusinessException {
        List<City> result = this.cityDao.findAll();
        List<CityListDto> response = result.stream()
                .map(city -> this.modelMapperService.forDto()
                        .map(city, CityListDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<CityListDto>>(response, "Şehirler başarıyla listelendi");
    }

    @Override
    public DataResult<GetCityDto> getById(Integer id) throws BusinessException {

        checkIfCityIdExists(id);

        City city = this.cityDao.getById(id);
        GetCityDto response = this.modelMapperService.forDto().map(city, GetCityDto.class);

        return new SuccessDataResult<GetCityDto>(response, "Şehirlerin id'leri listelendi.");
    }

    private void checkIfCityNameExists(String cityName) throws BusinessException{
        if(this.cityDao.existsCityByCityName(cityName)){
            throw new BusinessException("Bu isimde şehir kayıtlı!");

        }
    }

    private void checkIfCityIdExists(int id) throws BusinessException{
        if(!this.cityDao.existsById(id)){
            throw new BusinessException("Bu id'ye kayıtlı şehir bulunamadı.");
        }
    }
}
