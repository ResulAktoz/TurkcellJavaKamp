package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.getDto.GetCorporateCustomerDto;
import com.turkcell.rentacar.business.dtos.listDto.CorporateCustomerListDto;
import com.turkcell.rentacar.business.requests.create.CreateCorporateCustomerRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteCorporateCustomerRequest;
import com.turkcell.rentacar.business.requests.update.UpdateCorporateCustomerRequest;
import com.turkcell.rentacar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

import java.util.List;

public interface CorporateCustomerService {

    Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest);
    Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest);
    Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest);

    DataResult<List<CorporateCustomerListDto>> getAll();
    void checkIfCorporateCustomerExistById(int userId);

}
