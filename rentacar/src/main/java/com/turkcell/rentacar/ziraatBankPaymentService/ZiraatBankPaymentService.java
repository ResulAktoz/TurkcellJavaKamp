package com.turkcell.rentacar.ziraatBankPaymentService;

import org.springframework.stereotype.Service;

@Service
public class ZiraatBankPaymentService {


    public boolean isCardExists(String creditCardNo, String cardHolder,int expirationMonth,int expirationYear, String cvv){
        return true;
    }
    public boolean makePayment(String creditCardNo, String cardHolder, int expirationMonth, int expirationYear, String cvv, double totalPayment){
        return true;
    }
}
