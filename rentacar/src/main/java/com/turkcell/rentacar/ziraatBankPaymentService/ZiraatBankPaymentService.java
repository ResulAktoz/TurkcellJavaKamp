package com.turkcell.rentacar.ziraatBankPaymentService;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ZiraatBankPaymentService {

    public boolean payment(String creditCardNo, String cardHolder, LocalDate expirationDate, String cvv){
        return true;
    }
}
