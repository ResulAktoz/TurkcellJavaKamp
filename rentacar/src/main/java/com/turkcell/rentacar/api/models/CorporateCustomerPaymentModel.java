package com.turkcell.rentacar.api.models;

import com.turkcell.rentacar.business.requests.create.CreateCreditCardInfoRequest;
import com.turkcell.rentacar.business.requests.create.CreatePaymentRequest;
import com.turkcell.rentacar.business.requests.create.CreateRentRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorporateCustomerPaymentModel {

    private CreateRentRequest createRentRequest;

    private CreateCreditCardInfoRequest createCreditCardInfoRequest;

    private CreatePaymentRequest createPaymentRequest;
}
