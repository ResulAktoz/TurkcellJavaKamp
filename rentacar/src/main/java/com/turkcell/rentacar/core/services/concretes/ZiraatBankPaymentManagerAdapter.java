package com.turkcell.rentacar.core.services.concretes;

import com.turkcell.rentacar.business.constants.messages.BusinessMessages;
import com.turkcell.rentacar.business.requests.create.CreateCreditCardInfoRequest;
import com.turkcell.rentacar.core.services.abstracts.BaseBankPaymentServiceAdapter;
import com.turkcell.rentacar.core.utilities.results.ErrorResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.entities.concretes.Payment;
import com.turkcell.rentacar.ziraatBankPaymentService.ZiraatBankPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Primary
public class ZiraatBankPaymentManagerAdapter implements BaseBankPaymentServiceAdapter {

    private ZiraatBankPaymentService ziraatBankPaymentService;

    @Autowired
    public ZiraatBankPaymentManagerAdapter(ZiraatBankPaymentService ziraatBankPaymentService) {
        this.ziraatBankPaymentService = ziraatBankPaymentService;
    }


    @Override
    public Result isCardExists(CreateCreditCardInfoRequest createCreditCardInfoRequest) {
        if(ziraatBankPaymentService.isCardExists(createCreditCardInfoRequest.getCreditCardNo(), createCreditCardInfoRequest.getCardHolder(), createCreditCardInfoRequest.getExpirationMonth(), createCreditCardInfoRequest.getExpirationYear(), createCreditCardInfoRequest.getCvv())){
            return new SuccessResult(BusinessMessages.CREDIT_CARD_IS_EXISTS);
        }
        return new ErrorResult(BusinessMessages.CREDIT_CARD_IS_NOT_EXISTS);
    }

    @Override
    public Result makePayment(CreateCreditCardInfoRequest createCreditCardInfoRequest, double totalPayment) {
        if(ziraatBankPaymentService.makePayment(createCreditCardInfoRequest.getCreditCardNo(), createCreditCardInfoRequest.getCardHolder(), createCreditCardInfoRequest.getExpirationMonth(), createCreditCardInfoRequest.getExpirationYear(), createCreditCardInfoRequest.getCvv(), totalPayment)){
            return new SuccessResult(BusinessMessages.PAYMENT_ADDED_SUCCESSFULLY);
        }
        return new ErrorResult(BusinessMessages.PAYMENT_FAILED);
    }
}
