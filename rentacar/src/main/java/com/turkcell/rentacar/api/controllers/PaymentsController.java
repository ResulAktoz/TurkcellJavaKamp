package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.PaymentService;
import com.turkcell.rentacar.business.requests.create.CreatePaymentRequest;
import com.turkcell.rentacar.business.requests.delete.DeletePaymentRequest;
import com.turkcell.rentacar.business.requests.update.UpdatePaymentRequest;
import com.turkcell.rentacar.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/payments")
public class PaymentsController {

    private PaymentService paymentService;

    @Autowired
    public PaymentsController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreatePaymentRequest createPaymentRequest){
        return this.paymentService.add(createPaymentRequest);
    }
    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdatePaymentRequest updatePaymentRequest){
        return this.paymentService.update(updatePaymentRequest);
    }
    @DeleteMapping("/delete")
    public Result delete(@RequestBody @Valid DeletePaymentRequest deletePaymentRequest){
        return this.paymentService.delete(deletePaymentRequest);
    }
}
