package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.CarMaintenanceService;
import com.turkcell.rentacar.business.dtos.listDto.CarMaintenanceListDto;
import com.turkcell.rentacar.business.dtos.getDto.GetCarMaintenanceDto;
import com.turkcell.rentacar.business.requests.create.CreateCarMaintenanceRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteCarMaintenanceRequest;
import com.turkcell.rentacar.business.requests.update.UpdateCarMaintenanceRequest;
import com.turkcell.rentacar.core.results.DataResult;
import com.turkcell.rentacar.core.results.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/carMaintenances")
public class CarMaintenancesController {
    private CarMaintenanceService carMaintenanceService;

    public CarMaintenancesController(CarMaintenanceService carMaintenanceService) {
        this.carMaintenanceService = carMaintenanceService;
    }

    @GetMapping("/getall")
    public DataResult<List<CarMaintenanceListDto>> getAll() {
        return this.carMaintenanceService.getAll();

    }

    @PostMapping("/add")

    public Result add(@RequestBody @Valid CreateCarMaintenanceRequest createCarMaintenanceRequest) {
        return this.carMaintenanceService.add(createCarMaintenanceRequest);

    }

    @GetMapping("/getByCarMaintenanceId/{carMaintenanceId}")
    public DataResult<List<CarMaintenanceListDto>> getByCarId(@PathVariable("carMaintenanceId") Integer id) {
        return this.carMaintenanceService.getByCarId(id);

    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody @Valid DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) {
        return this.carMaintenanceService.delete(deleteCarMaintenanceRequest);

    }
    @PutMapping("/put")
    public Result update(@RequestBody @Valid UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
        return this.carMaintenanceService.update(updateCarMaintenanceRequest);


    }

    @GetMapping("/getalllistedCar")
    public DataResult<List<CarMaintenanceListDto>> getAllActiveCars() {
        return this.carMaintenanceService.getListedCars();


    }
}
