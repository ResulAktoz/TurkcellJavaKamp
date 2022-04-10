package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.api.models.CorporateCustomerPaymentModel;
import com.turkcell.rentacar.api.models.IndividualCustomerPaymentModel;
import com.turkcell.rentacar.business.requests.create.CreatePaymentRequest;
import com.turkcell.rentacar.business.requests.delete.DeletePaymentRequest;
import com.turkcell.rentacar.business.requests.update.UpdatePaymentRequest;
import com.turkcell.rentacar.core.utilities.results.Result;

public interface PaymentService {

    Result addForIndividualCustomer(IndividualCustomerPaymentModel individualCustomerPaymentModel);
    Result addForCorporateCustomer(CorporateCustomerPaymentModel corporateCustomerPaymentModel);

}
