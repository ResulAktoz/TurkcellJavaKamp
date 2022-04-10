package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.CorporateCustomerService;
import com.turkcell.rentacar.business.constants.messages.BusinessMessages;
import com.turkcell.rentacar.business.dtos.getDto.GetCorporateCustomerDto;
import com.turkcell.rentacar.business.dtos.getDto.GetIndividualCustomerDto;
import com.turkcell.rentacar.business.dtos.listDto.CorporateCustomerListDto;
import com.turkcell.rentacar.business.requests.create.CreateCorporateCustomerRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteCorporateCustomerRequest;
import com.turkcell.rentacar.business.requests.update.UpdateCorporateCustomerRequest;
import com.turkcell.rentacar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.*;
import com.turkcell.rentacar.dataAccess.abstracts.CorporateCustomerDao;
import com.turkcell.rentacar.entities.concretes.CorporateCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CorporateCustomerManager implements CorporateCustomerService {

    private CorporateCustomerDao corporateCustomerDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public CorporateCustomerManager(CorporateCustomerDao corporateCustomerDao, ModelMapperService modelMapperService) {
        this.corporateCustomerDao = corporateCustomerDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest)  {
        CorporateCustomer corporateCustomer = this.modelMapperService.forRequest()
                .map(createCorporateCustomerRequest, CorporateCustomer.class);

        this.corporateCustomerDao.save(corporateCustomer);
        return new SuccessResult(BusinessMessages.CORPORATE_CUSTOMER_ADDED_SUCCESSFULLY);
    }

    @Override
    public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
        checkIfCorporateCustomerExistById(updateCorporateCustomerRequest.getUserId());

        CorporateCustomer corporateCustomer = this.modelMapperService.forRequest()
                .map(updateCorporateCustomerRequest, CorporateCustomer.class);
        this.corporateCustomerDao.save(corporateCustomer);

        return new SuccessResult(BusinessMessages.CORPORATE_CUSTOMER_UPDATED_SUCCESSFULLY);

    }

    @Override
    public Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest){
        checkIfCorporateCustomerExistById(deleteCorporateCustomerRequest.getUserId());

        this.corporateCustomerDao.deleteById(deleteCorporateCustomerRequest.getUserId());

        return new SuccessResult(BusinessMessages.CORPORATE_CUSTOMER_DELETED_SUCCESSFULLY);
    }

    @Override
    public DataResult<List<CorporateCustomerListDto>> getAll(){
        List<CorporateCustomer> result = this.corporateCustomerDao.findAll();

        List<CorporateCustomerListDto> response = result.stream()
                .map(corporateCustomer -> this.modelMapperService.forDto()
                        .map(corporateCustomer, CorporateCustomerListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<CorporateCustomerListDto>>(response,BusinessMessages.CORPORATE_CUSTOMER_LISTED_SUCCESSFULLY);
    }

    @Override
    public DataResult<GetCorporateCustomerDto> getByCorporatelCustomerId(int corporateCustomerId) {
        CorporateCustomer result = this.corporateCustomerDao.getByUserId(corporateCustomerId);
        if(result == null){
            return new ErrorDataResult<GetCorporateCustomerDto>(BusinessMessages.CORPORATE_CUSTOMER_NOT_FOUND);
        }

        GetCorporateCustomerDto response = this.modelMapperService.forDto().map(result, GetCorporateCustomerDto.class);

        return new SuccessDataResult<GetCorporateCustomerDto>(response, BusinessMessages.CORPORATE_CUSTOMER_GET_BY_ID);
    }


    public void checkIfCorporateCustomerExistById(int userId){

       if(!this.corporateCustomerDao.existsById(userId)){
           throw new BusinessException(BusinessMessages.CORPORATE_CUSTOMER_NOT_FOUND);
       }

    }
}
