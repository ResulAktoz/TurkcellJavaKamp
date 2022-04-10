package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.api.models.CorporateCustomerPaymentModel;
import com.turkcell.rentacar.api.models.IndividualCustomerPaymentModel;
import com.turkcell.rentacar.business.abstracts.PaymentService;
import com.turkcell.rentacar.business.requests.create.CreatePaymentRequest;
import com.turkcell.rentacar.business.requests.delete.DeletePaymentRequest;
import com.turkcell.rentacar.business.requests.update.UpdatePaymentRequest;
import com.turkcell.rentacar.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/payments")
public class PaymentsController {

    private PaymentService paymentService;

    @Autowired
    public PaymentsController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Transactional
    @PostMapping("/addForIndividualCustomer")
    public Result addForIndividualCustomer(@RequestBody @Valid IndividualCustomerPaymentModel individualCustomerPaymentModel){
        return this.paymentService.addForIndividualCustomer(individualCustomerPaymentModel);
    }
    @Transactional
    @PostMapping("/api/addForCorporateCustomer")
    public Result addForCorporateCustomer(@RequestBody @Valid CorporateCustomerPaymentModel corporateCustomerPaymentModel){
        return this.paymentService.addForCorporateCustomer(corporateCustomerPaymentModel);
    }

}
