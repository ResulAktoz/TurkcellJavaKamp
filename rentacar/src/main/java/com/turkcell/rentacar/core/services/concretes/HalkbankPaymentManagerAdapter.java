package com.turkcell.rentacar.core.services.concretes;

import com.turkcell.rentacar.business.constants.messages.BusinessMessages;
import com.turkcell.rentacar.business.requests.create.CreateCreditCardInfoRequest;
import com.turkcell.rentacar.core.services.abstracts.BaseBankPaymentServiceAdapter;
import com.turkcell.rentacar.core.utilities.results.ErrorResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.entities.concretes.Payment;
import com.turkcell.rentacar.halkbankPaymentService.HalkbankPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.validation.Path;

@Service
public class HalkbankPaymentManagerAdapter implements BaseBankPaymentServiceAdapter {

    private HalkbankPaymentService halkbankPaymentService;

    @Autowired
    public HalkbankPaymentManagerAdapter(HalkbankPaymentService halkbankPaymentService) {
        this.halkbankPaymentService = halkbankPaymentService;
    }


    @Override
    public Result isCardExists(CreateCreditCardInfoRequest createCreditCardInfoRequest) {
        if (halkbankPaymentService.isCardExists(createCreditCardInfoRequest.getExpirationMonth(), createCreditCardInfoRequest.getExpirationYear(), createCreditCardInfoRequest.getCvv(), createCreditCardInfoRequest.getCreditCardNo(), createCreditCardInfoRequest.getCardHolder())) {
            return new SuccessResult(BusinessMessages.CREDIT_CARD_IS_EXISTS);
        }

        return new ErrorResult(BusinessMessages.CREDIT_CARD_IS_NOT_EXISTS);
    }

    @Override
    public Result makePayment(CreateCreditCardInfoRequest createCreditCardInfoRequest, double totalPayment) {
        if (halkbankPaymentService.makePayment(createCreditCardInfoRequest.getExpirationMonth(), createCreditCardInfoRequest.getExpirationYear(), createCreditCardInfoRequest.getCvv(), createCreditCardInfoRequest.getCreditCardNo(), createCreditCardInfoRequest.getCardHolder(), totalPayment)) {
            return new SuccessResult(BusinessMessages.PAYMENT_ADDED_SUCCESSFULLY);
        }
        return new ErrorResult(BusinessMessages.PAYMENT_FAILED);
    }
}
