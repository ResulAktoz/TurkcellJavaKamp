package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.getDto.GetCorporateCustomerDto;
import com.turkcell.rentacar.business.dtos.listDto.CorporateCustomerListDto;
import com.turkcell.rentacar.business.requests.create.CreateCorporateCustomerRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteCorporateCustomerRequest;
import com.turkcell.rentacar.business.requests.update.UpdateCorporateCustomerRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.results.DataResult;
import com.turkcell.rentacar.core.results.Result;

import java.util.List;

public interface CorporateCustomerService {

    Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) throws BusinessException;
    Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) throws BusinessException;
    Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) throws BusinessException;

    DataResult<List<CorporateCustomerListDto>> getAll() throws BusinessException;
    DataResult<GetCorporateCustomerDto> getByUserId(int userId) throws BusinessException;
}
