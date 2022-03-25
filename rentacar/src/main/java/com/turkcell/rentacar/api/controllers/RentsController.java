package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.RentService;
import com.turkcell.rentacar.business.dtos.getDto.GetRentDto;
import com.turkcell.rentacar.business.dtos.listDto.RentListDto;
import com.turkcell.rentacar.business.requests.create.CreateRentRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteRentRequest;
import com.turkcell.rentacar.business.requests.update.UpdateRentDeliveryDateRequest;
import com.turkcell.rentacar.business.requests.update.UpdateRentRequest;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;


import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/api/rents")
public class RentsController {

    private RentService rentService;

    public RentsController(RentService rentService) {
        this.rentService = rentService;
    }

    @PostMapping("/addForCorporateCustomer")
    public Result addForCorporateCustomer(@RequestBody @Valid CreateRentRequest createRentRequest){
        return this.rentService.addForCorporateCustomer(createRentRequest);
    }

    @PostMapping("/addForIndividualCustomer")
    public Result addForIndividualCustomer(@RequestBody @Valid CreateRentRequest createRentRequest){
        System.out.println(createRentRequest);
       return this.rentService.addForIndividualCustomer(createRentRequest);

    }

    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateRentRequest updateRentRequest){
       return this.rentService.update(updateRentRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody @Valid DeleteRentRequest deleteRentRequest){
        return this.rentService.delete(deleteRentRequest);
    }

    @Transactional
    @PutMapping("/updateRentDeliveryDate")
    public Result updateRentDeliveryDate(@RequestBody @Valid UpdateRentDeliveryDateRequest updateRentDeliveryDateRequest){
        return this.rentService.updateRentDeliveryDate(updateRentDeliveryDateRequest);
    }

    @GetMapping("/getAll")
    public DataResult<List<RentListDto>> getAll(){
        return this.rentService.getAll();
    }

    public DataResult<GetRentDto> getRentById(@RequestParam int rentId){
        return this.rentService.getRentDetailsByRentId(rentId);
    }

}
