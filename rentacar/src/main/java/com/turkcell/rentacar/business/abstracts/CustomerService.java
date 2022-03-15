package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.getDto.GetCustomerDto;
import com.turkcell.rentacar.business.dtos.listDto.CustomerListDto;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.results.DataResult;

import java.util.List;


public interface CustomerService {

    DataResult<List<CustomerListDto>> getAll() throws BusinessException;
    DataResult<List<GetCustomerDto>> getByUserId(int userId)  throws BusinessException;
}
