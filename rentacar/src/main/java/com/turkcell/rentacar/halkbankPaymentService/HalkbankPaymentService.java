package com.turkcell.rentacar.halkbankPaymentService;

import org.springframework.stereotype.Service;

@Service
public class HalkbankPaymentService {

    public boolean isCardExists(int expirationMonth,int expirationYear, String cvv, String creditCardNo, String cardHolder){
        return true;
    }

    public boolean makePayment(int expirationMonth, int expirationYear, String cvv, String creditCardNo, String cardHolder, double totalPayment){
        return true;
    }


}
