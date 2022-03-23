package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.PaymentService;
import com.turkcell.rentacar.business.requests.create.CreatePaymentRequest;
import com.turkcell.rentacar.business.requests.delete.DeletePaymentRequest;
import com.turkcell.rentacar.business.requests.update.UpdatePaymentRequest;
import com.turkcell.rentacar.core.services.abstracts.BaseBankPaymentServiceAdapter;
import com.turkcell.rentacar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.PaymentDao;
import com.turkcell.rentacar.entities.concretes.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PaymentManager implements PaymentService {

    private PaymentDao paymentDao;
    private ModelMapperService modelMapperService;
    private BaseBankPaymentServiceAdapter baseBankPaymentServiceAdapter;

    @Autowired
    public PaymentManager(PaymentDao paymentDao, ModelMapperService modelMapperService,
                          @Qualifier("ziraatBank") BaseBankPaymentServiceAdapter baseBankPaymentServiceAdapter) {
        this.paymentDao = paymentDao;
        this.modelMapperService = modelMapperService;
        this.baseBankPaymentServiceAdapter = baseBankPaymentServiceAdapter;
    }

    @Override
    public Result add(CreatePaymentRequest createPaymentRequest) {
        Payment payment = this.modelMapperService.forRequest().map(createPaymentRequest, Payment.class);

        this.paymentDao.save(payment);
        return new SuccessResult("Ödeme yöntremi eklendi.");

    }

    @Override
    public Result update(UpdatePaymentRequest updatePaymentRequest) {
        checkIFPaymentExists(updatePaymentRequest.getPaymentId());

        Payment payment = this.modelMapperService.forRequest().map(updatePaymentRequest, Payment.class);
        this.paymentDao.save(payment);

        return new SuccessResult("Güncelleme başarılı.");
    }

    @Override
    public Result delete(DeletePaymentRequest deletePaymentRequest) {
        checkIFPaymentExists(deletePaymentRequest.getPaymentId());
        this.paymentDao.deleteById(deletePaymentRequest.getPaymentId());

        return new SuccessResult("Ödeme yöntemi başarıyla kaldırıldı");
    }

    // private void checkPaymentBankType(int bankId){
    //}

    private void checkIFPaymentExists(int paymentId){
        if(!this.paymentDao.existsById(paymentId)){
            throw new BusinessException("Bu id'ye kayıtlı ödeme yöntemi bulunamadı.");
        }
    }
}
