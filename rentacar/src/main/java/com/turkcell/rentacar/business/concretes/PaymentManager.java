package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.api.models.CorporateCustomerPaymentModel;
import com.turkcell.rentacar.api.models.IndividualCustomerPaymentModel;
import com.turkcell.rentacar.business.abstracts.*;
import com.turkcell.rentacar.business.constants.messages.BusinessMessages;
import com.turkcell.rentacar.business.requests.create.CreateInvoiceRequest;
import com.turkcell.rentacar.business.requests.create.CreatePaymentRequest;
import com.turkcell.rentacar.business.requests.delete.DeletePaymentRequest;
import com.turkcell.rentacar.business.requests.update.UpdatePaymentRequest;
import com.turkcell.rentacar.core.services.abstracts.BaseBankPaymentServiceAdapter;
import com.turkcell.rentacar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.PaymentDao;
import com.turkcell.rentacar.entities.concretes.Invoice;
import com.turkcell.rentacar.entities.concretes.Payment;
import com.turkcell.rentacar.entities.concretes.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentManager implements PaymentService {

    private PaymentDao paymentDao;
    private ModelMapperService modelMapperService;
    private BaseBankPaymentServiceAdapter baseBankPaymentServiceAdapter;
    private InvoiceService invoiceService;
    private RentService rentService;
    private CreditCardInfoService creditCardInfoService;

    @Autowired
    public PaymentManager(PaymentDao paymentDao, ModelMapperService modelMapperService,
                          BaseBankPaymentServiceAdapter baseBankPaymentServiceAdapter,
                          @Lazy InvoiceService invoiceService,@Lazy RentService rentService,
                          @Lazy CreditCardInfoService creditCardInfoService) {
        this.paymentDao = paymentDao;
        this.modelMapperService = modelMapperService;
        this.baseBankPaymentServiceAdapter = baseBankPaymentServiceAdapter;
        this.invoiceService= invoiceService;
        this.rentService = rentService;
        this.creditCardInfoService = creditCardInfoService;
    }


    @Override
    public Result addForIndividualCustomer(IndividualCustomerPaymentModel individualCustomerPaymentModel) {
        baseBankPaymentServiceAdapter.isCardExists(individualCustomerPaymentModel.getCreateCreditCardInfoRequest());
        runPaymentSuccessorForIndividualCustomer(individualCustomerPaymentModel);

        return new SuccessResult(BusinessMessages.PAYMENT_ADDED_SUCCESSFULLY);
    }

    @Override
    public Result addForCorporateCustomer(CorporateCustomerPaymentModel corporateCustomerPaymentModel) {
        baseBankPaymentServiceAdapter.isCardExists(corporateCustomerPaymentModel.getCreateCreditCardInfoRequest());
        runPaymentSuccessorForCorporateCustomer(corporateCustomerPaymentModel);

        return new SuccessResult(BusinessMessages.PAYMENT_ADDED_SUCCESSFULLY);
    }
    @Transactional(rollbackFor = BusinessException.class)
    public void runPaymentSuccessorForIndividualCustomer(IndividualCustomerPaymentModel individualCustomerPaymentModel){
        Payment payment= this.modelMapperService.forRequest().map(individualCustomerPaymentModel.getCreatePaymentRequest(), Payment.class);

        Rent rent = this.rentService.addForIndividualCustomer(individualCustomerPaymentModel.getCreateRentRequest()).getData();

        Invoice invoice = this.invoiceService.add(rent.getRentId());



        payment.setRent(rent);
        setPaymentFields(payment, rent, invoice);





        this.baseBankPaymentServiceAdapter.makePayment(individualCustomerPaymentModel.getCreateCreditCardInfoRequest(), invoice.getTotalPrice() );

        this.paymentDao.save(payment);
    }
    @Transactional(rollbackFor = BusinessException.class)
    public void runPaymentSuccessorForCorporateCustomer(CorporateCustomerPaymentModel corporateCustomerPaymentModel){
        Payment payment = this.modelMapperService.forRequest().map(corporateCustomerPaymentModel.getCreatePaymentRequest(), Payment.class);
        Rent rent = this.rentService.addForCorporateCustomer(corporateCustomerPaymentModel.getCreateRentRequest()).getData();
        Invoice invoice = this.invoiceService.add(rent.getRentId());

        payment.setRent(rent);
        payment.setInvoices((List<Invoice>) invoice);
        setPaymentFields(payment, rent, invoice);

        this.baseBankPaymentServiceAdapter.makePayment(corporateCustomerPaymentModel.getCreateCreditCardInfoRequest(), invoice.getTotalPrice());

        this.paymentDao.save(payment);

    }

    private void setPaymentFields(Payment payment, Rent rent, Invoice invoice){
        payment.setUser(rent.getUser());
        payment.setRent(rent);
        payment.setTotalPayment(invoice.getTotalPrice());
    }
}
