package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.OrderedAdditionalServiceService;
import com.turkcell.rentacar.business.dtos.getDto.GetOrderedAdditionalServiceDto;
import com.turkcell.rentacar.business.dtos.listDto.OrderedAdditionalServiceListDto;
import com.turkcell.rentacar.business.requests.create.CreateOrderedAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteOrderedAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.update.UpdateOrderedAdditionalServiceRequest;
import com.turkcell.rentacar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/orderedAdditionalServices")
public class OrderedAdditionalServiceController {

    private OrderedAdditionalServiceService orderedAdditionalServiceService;

    @Autowired
    public OrderedAdditionalServiceController(OrderedAdditionalServiceService orderedAdditionalServiceService) {
        this.orderedAdditionalServiceService = orderedAdditionalServiceService;
    }
    @PostMapping("/add")
    Result add(@RequestBody @Valid CreateOrderedAdditionalServiceRequest createOrderedAdditionalServiceRequest) throws BusinessException{
        return this.orderedAdditionalServiceService.add(createOrderedAdditionalServiceRequest);
    }
    @PutMapping("/update")
    Result update(@RequestBody @Valid UpdateOrderedAdditionalServiceRequest updateOrderedAdditionalServiceRequest) throws BusinessException{
        return this.orderedAdditionalServiceService.update(updateOrderedAdditionalServiceRequest);
    }

    @DeleteMapping("/delete")
    Result delete(@RequestBody @Valid DeleteOrderedAdditionalServiceRequest deleteOrderedAdditionalServiceRequest) throws BusinessException{
        return this.orderedAdditionalServiceService.delete(deleteOrderedAdditionalServiceRequest);

    }
    @GetMapping("/getAll")
    DataResult<List<OrderedAdditionalServiceListDto>> getAll() throws BusinessException{
        return this.orderedAdditionalServiceService.getAll();
    }
    @GetMapping("/getByOrderedAdditionalServiceId/{orderedAdditionalServiceId}")
    DataResult<GetOrderedAdditionalServiceDto> getByOrderedAdditionalServiceId(@RequestParam("orderedAdditionalServiceId") Integer id) throws BusinessException{
    return this.orderedAdditionalServiceService.getByOrderedAdditionalServiceId(id);
    }
    @GetMapping("/getByRentId/{rentId}")
    DataResult<List<OrderedAdditionalServiceListDto>> getByRentId(@RequestParam("rentId")Integer id){
        return this.orderedAdditionalServiceService.getByRentId(id);

    }

}
