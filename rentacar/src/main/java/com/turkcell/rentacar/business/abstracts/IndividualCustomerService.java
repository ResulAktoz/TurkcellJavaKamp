package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.getDto.GetIndividualCustomerDto;
import com.turkcell.rentacar.business.dtos.listDto.IndividualCustomerListDto;
import com.turkcell.rentacar.business.requests.create.CreateIndividualCustomerRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteIndividualCustomerRequest;
import com.turkcell.rentacar.business.requests.update.UpdateIndividualCustomerRequest;
import com.turkcell.rentacar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

import java.util.List;

public interface IndividualCustomerService {

    Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) throws BusinessException;
    Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) throws BusinessException;
    Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) throws BusinessException;

    DataResult<List<IndividualCustomerListDto>> getAll() throws BusinessException;
    DataResult<GetIndividualCustomerDto> getByUserId(int userId) throws BusinessException;
}
