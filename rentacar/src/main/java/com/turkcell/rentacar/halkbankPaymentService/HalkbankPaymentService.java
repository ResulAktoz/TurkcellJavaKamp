package com.turkcell.rentacar.halkbankPaymentService;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class HalkbankPaymentService {

    public boolean payment(LocalDate expirationDate, String cvv, String creditCardNo, String cardHolder){
        return true;
    }
}
