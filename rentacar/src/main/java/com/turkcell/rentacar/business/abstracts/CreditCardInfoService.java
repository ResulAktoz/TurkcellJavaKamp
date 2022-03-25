package com.turkcell.rentacar.business.abstracts;


import com.turkcell.rentacar.business.requests.create.CreateCreditCardInfoRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteCreditCardInfoRequest;
import com.turkcell.rentacar.business.requests.update.UpdateCreditCardInfoRequest;
import com.turkcell.rentacar.core.utilities.results.Result;

public interface CreditCardInfoService {

    Result add(CreateCreditCardInfoRequest createCreditCardInfoRequest);
    Result update(UpdateCreditCardInfoRequest updateCreditCardInfoRequest);
    Result delete(DeleteCreditCardInfoRequest deleteCreditCardInfoRequest);
}
