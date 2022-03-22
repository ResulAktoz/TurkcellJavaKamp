package com.turkcell.rentacar.core.services.abstracts;

import com.turkcell.rentacar.entities.concretes.Payment;

public interface BaseBankPaymentServiceAdapter {

    public boolean payment(Payment payment);
}
