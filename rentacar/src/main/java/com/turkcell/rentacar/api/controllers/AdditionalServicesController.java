package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.AdditionalServiceService;
import com.turkcell.rentacar.business.dtos.getDto.GetAdditionalServiceDto;
import com.turkcell.rentacar.business.dtos.listDto.AdditionalServiceListDto;
import com.turkcell.rentacar.business.requests.create.CreateAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.update.UpdateAdditionalServiceRequest;
import com.turkcell.rentacar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/additionalServices")
public class AdditionalServicesController {

    private AdditionalServiceService additionalServiceService;

    @Autowired
    public AdditionalServicesController(AdditionalServiceService additionalServiceService) {
        this.additionalServiceService = additionalServiceService;
    }
    @PostMapping("/add")
    Result add(@RequestBody @Valid CreateAdditionalServiceRequest createAdditionalServiceRequest) throws BusinessException{

        return this.additionalServiceService.add(createAdditionalServiceRequest);

    }
    @PutMapping("/update")
    Result update(@RequestBody @Valid UpdateAdditionalServiceRequest updateAdditionalServiceRequest) throws BusinessException{

        return this.additionalServiceService.update(updateAdditionalServiceRequest);

    }
    @DeleteMapping("/delete")
    Result delete(@RequestBody @Valid DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) throws  BusinessException{

        return this.additionalServiceService.delete(deleteAdditionalServiceRequest);

    }

    @GetMapping("/getAll")
    DataResult<List<AdditionalServiceListDto>> getAll() throws BusinessException{
        return this.additionalServiceService.getAll();

    }
}
