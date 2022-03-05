package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.RentService;
import com.turkcell.rentacar.business.dtos.RentListDto;
import com.turkcell.rentacar.business.requests.CreateRentRequest;
import com.turkcell.rentacar.core.results.DataResult;
import com.turkcell.rentacar.core.results.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/api/rents")
public class RentsController {

    private RentService rentService;

    public RentsController(RentService rentService) {
        this.rentService = rentService;
    }
    @GetMapping("/getAll")
    DataResult<List<RentListDto>> getAll(){
        return this.rentService.getAll();
    }
    @PostMapping("/add")
    Result add(@RequestBody @Valid CreateRentRequest createRentRequest){
        return this.rentService.add(createRentRequest);


    }
}
