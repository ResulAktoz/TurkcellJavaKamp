package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.CreditCardInfoService;
import com.turkcell.rentacar.business.requests.create.CreateCreditCardInfoRequest;
import com.turkcell.rentacar.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/creditcardinfos")
public class CreditCardInfosController {

    private CreditCardInfoService creditCardInfoService;

    @Autowired
    public CreditCardInfosController(CreditCardInfoService creditCardInfoService) {
        this.creditCardInfoService = creditCardInfoService;
    }

    @PostMapping("/add")
    Result add(@RequestBody @Valid CreateCreditCardInfoRequest createCreditCardInfoRequest){
       return this.creditCardInfoService.add(createCreditCardInfoRequest);
    }
}
