package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.CreditCardInfoService;
import com.turkcell.rentacar.business.constants.messages.BusinessMessages;
import com.turkcell.rentacar.business.requests.create.CreateCreditCardInfoRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteCreditCardInfoRequest;
import com.turkcell.rentacar.business.requests.update.UpdateCreditCardInfoRequest;
import com.turkcell.rentacar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.CreditCardInfoDao;
import com.turkcell.rentacar.entities.concretes.CreditCardInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditCardInfoManager implements CreditCardInfoService {

    private CreditCardInfoDao creditCardInfoDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public CreditCardInfoManager(CreditCardInfoDao creditCardInfoDao, ModelMapperService modelMapperService) {
        this.creditCardInfoDao = creditCardInfoDao;
        this.modelMapperService = modelMapperService;
    }



    @Override
    public Result add(CreateCreditCardInfoRequest createCreditCardInfoRequest) {
        CreditCardInfo creditCardInfo = this.modelMapperService.forRequest()
                .map(createCreditCardInfoRequest, CreditCardInfo.class);

        this.creditCardInfoDao.save(creditCardInfo);

        return new SuccessResult(BusinessMessages.CREDIT_CARD_INFO_ADDED_SUCCESFULLY);
    }

    @Override
    public Result update(UpdateCreditCardInfoRequest updateCreditCardInfoRequest) {
        checkIfCreditCardInfoExists(updateCreditCardInfoRequest.getCreditCardInfoId());

        CreditCardInfo creditCardInfo = this.modelMapperService.forRequest()
                .map(updateCreditCardInfoRequest, CreditCardInfo.class);

        this.creditCardInfoDao.save(creditCardInfo);

        return new SuccessResult(BusinessMessages.CREDIT_CARD_INFO_UPDATED_SUCCESFULLY);

    }

    @Override
    public Result delete(DeleteCreditCardInfoRequest deleteCreditCardInfoRequest) {
        checkIfCreditCardInfoExists(deleteCreditCardInfoRequest.getCreditCardInfoId());

        this.creditCardInfoDao.deleteById(deleteCreditCardInfoRequest.getCreditCardInfoId());

        return new SuccessResult(BusinessMessages.CREDIT_CARD_INFO_DELETED_SUCCESFULLY);
    }

    private void checkIfCreditCardInfoExists(int infoId){
        if(!this.creditCardInfoDao.existsById(infoId)){
            throw new BusinessException(BusinessMessages.CREDIT_CARD_INFO_NOT_FOUND);
        }

    }
}
