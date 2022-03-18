package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.RentService;
import com.turkcell.rentacar.business.dtos.getDto.GetRentDto;
import com.turkcell.rentacar.business.dtos.listDto.RentListDto;
import com.turkcell.rentacar.business.requests.create.CreateRentRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteRentRequest;
import com.turkcell.rentacar.business.requests.update.UpdateRentRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.results.DataResult;
import com.turkcell.rentacar.core.results.Result;
import com.turkcell.rentacar.core.results.SuccessResult;
import com.turkcell.rentacar.entities.concretes.Rent;
import org.hibernate.sql.Delete;
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

    @PostMapping("/addForCorporateCustomer")
    public Result addForCorporateCustomer(CreateRentRequest createRentRequest){
        return this.rentService.addForCorporateCustomer(createRentRequest);
    }

    @PostMapping("/addForIndividualCustomer")
    public Result addForIndividualCustomer(CreateRentRequest createRentRequest){
       return this.rentService.addForIndividualCustomer(createRentRequest);
    }

    @PutMapping("/update")
    public Result update(UpdateRentRequest updateRentRequest){
       return this.rentService.update(updateRentRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(DeleteRentRequest deleteRentRequest){
        return this.rentService.delete(deleteRentRequest);
    }

    @GetMapping("/getAll")
    public DataResult<List<RentListDto>> getAll(){
        return this.rentService.getAll();
    }

    public DataResult<GetRentDto> getRentById(@RequestParam int rentId){
        return this.rentService.getRentDetailsByRentId(rentId);
    }

}
