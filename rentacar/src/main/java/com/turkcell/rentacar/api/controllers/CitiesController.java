package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.CityService;
import com.turkcell.rentacar.business.dtos.getDto.GetCityDto;
import com.turkcell.rentacar.business.dtos.listDto.CityListDto;
import com.turkcell.rentacar.business.requests.create.CreateCityRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteCityRequest;
import com.turkcell.rentacar.business.requests.update.UpdateCityRequest;
import com.turkcell.rentacar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/api/cities")
public class CitiesController {

    private CityService cityService;

    @Autowired
    public CitiesController(CityService cityService) {
        this.cityService = cityService;
    }
    @PostMapping("/add")
    Result add(@RequestBody @Valid CreateCityRequest createCityRequest) throws BusinessException{
        return this.cityService.add(createCityRequest);
    }
    @PutMapping("/update")
    Result update(@RequestBody @Valid UpdateCityRequest updateCityRequest) throws BusinessException{
        return this.cityService.update(updateCityRequest);

    }
    @DeleteMapping("/delete")
    Result delete(@RequestBody @Valid DeleteCityRequest deleteCityRequest) throws BusinessException{
        return this.cityService.delete(deleteCityRequest);
    }
    @GetMapping("/getAll")
    DataResult<List<CityListDto>> getAll() throws BusinessException{
        return this.cityService.getAll();
    }


}
