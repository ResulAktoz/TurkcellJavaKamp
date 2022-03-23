package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.requests.create.CreatePaymentRequest;
import com.turkcell.rentacar.business.requests.delete.DeletePaymentRequest;
import com.turkcell.rentacar.business.requests.update.UpdatePaymentRequest;
import com.turkcell.rentacar.core.utilities.results.Result;

public interface PaymentService {

    Result add(CreatePaymentRequest createPaymentRequest);
    Result update(UpdatePaymentRequest updatePaymentRequest);
    Result delete(DeletePaymentRequest deletePaymentRequest);
}
