package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.IndividualCustomerService;
import com.turkcell.rentacar.business.constants.messages.BusinessMessages;
import com.turkcell.rentacar.business.dtos.getDto.GetIndividualCustomerDto;
import com.turkcell.rentacar.business.dtos.listDto.IndividualCustomerListDto;
import com.turkcell.rentacar.business.requests.create.CreateIndividualCustomerRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteIndividualCustomerRequest;
import com.turkcell.rentacar.business.requests.update.UpdateIndividualCustomerRequest;
import com.turkcell.rentacar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.IndividualCustomerDao;
import com.turkcell.rentacar.entities.concretes.IndividualCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndividualCustomerManager implements IndividualCustomerService {

    private IndividualCustomerDao individualCustomerDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public IndividualCustomerManager(IndividualCustomerDao individualCustomerDao, ModelMapperService modelMapperService) {
        this.individualCustomerDao = individualCustomerDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest){
        IndividualCustomer individualCustomer = this.modelMapperService.forRequest()
                .map(createIndividualCustomerRequest, IndividualCustomer.class);

        this.individualCustomerDao.save(individualCustomer);
        return new SuccessResult(BusinessMessages.INDIVIDUAL_CUSTOMER_ADDED_SUCCESSFULLY);

    }

    @Override
    public Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
        checkIfIndividualCustomerExistsById(updateIndividualCustomerRequest.getUserId());
        IndividualCustomer individualCustomer = this.modelMapperService.forRequest()
                .map(updateIndividualCustomerRequest, IndividualCustomer.class);
        return new SuccessResult(BusinessMessages.INDIVIDUAL_CUSTOMER_UPDATED_SUCCESSFULLY);
    }

    @Override
    public Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
        checkIfIndividualCustomerExistsById(deleteIndividualCustomerRequest.getUserId());
        this.individualCustomerDao.deleteById(deleteIndividualCustomerRequest.getUserId());
        return new SuccessResult(BusinessMessages.INDIVIDUAL_CUSTOMER_DELETED_SUCCESSFULLY);
    }

    @Override
    public DataResult<List<IndividualCustomerListDto>> getAll(){
        List<IndividualCustomer> result = this.individualCustomerDao.findAll();
        List<IndividualCustomerListDto> response = result.stream()
                .map(individualCustomer -> this.modelMapperService.forDto()
                        .map(individualCustomer, IndividualCustomerListDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<IndividualCustomerListDto>>(response, BusinessMessages.INDIVIDUAL_CUSTOMER_LISTED_SUCCESSFULLY);
    }

    public void checkIfIndividualCustomerExistsById(int userId){
        if(!this.individualCustomerDao.existsById(userId)){
            throw new BusinessException(BusinessMessages.INDIVIDUAL_CUSTOMER_NOT_FOUND);
        }
    }
}
