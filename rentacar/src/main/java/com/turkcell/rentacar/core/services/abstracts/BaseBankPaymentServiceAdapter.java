package com.turkcell.rentacar.core.services.abstracts;

import com.turkcell.rentacar.business.requests.create.CreateCreditCardInfoRequest;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.entities.concretes.Payment;

public interface BaseBankPaymentServiceAdapter {

    Result isCardExists(CreateCreditCardInfoRequest createCreditCardInfoRequest);

    Result makePayment(CreateCreditCardInfoRequest createCreditCardInfoRequest, double totalPayment);
}
