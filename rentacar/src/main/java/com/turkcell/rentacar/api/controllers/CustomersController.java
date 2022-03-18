package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.CustomerService;
import com.turkcell.rentacar.business.dtos.listDto.CustomerListDto;
import com.turkcell.rentacar.business.requests.create.CreateCustomerRequest;
import com.turkcell.rentacar.core.results.DataResult;
import com.turkcell.rentacar.core.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomersController {

    private CustomerService customerService;

    @Autowired
    public CustomersController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/getAll")
    public DataResult<List<CustomerListDto>> getAll() {

        return this.customerService.getAll();
    }
}
