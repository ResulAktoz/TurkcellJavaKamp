package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.CarDamageService;
import com.turkcell.rentacar.business.dtos.listDto.CarDamageListDto;
import com.turkcell.rentacar.business.requests.create.CreateCarDamageRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteCarDamageRequest;
import com.turkcell.rentacar.business.requests.update.UpdateCarDamageRequest;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/carDamages")
public class CarDamagesController {

    private CarDamageService carDamageService;

    @Autowired
    public CarDamagesController(CarDamageService carDamageService) {
        this.carDamageService = carDamageService;
    }
    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateCarDamageRequest createCarDamageRequest){

        return this.carDamageService.add(createCarDamageRequest);
    }
    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateCarDamageRequest updateCarDamageRequest ){

        return this.carDamageService.update(updateCarDamageRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody @Valid DeleteCarDamageRequest deleteCarDamageRequest ){

        return this.delete(deleteCarDamageRequest);
    }

    @GetMapping("/getAll")
    public DataResult<List<CarDamageListDto>> getAll(){
        return this.carDamageService.getAll();
    }

}
