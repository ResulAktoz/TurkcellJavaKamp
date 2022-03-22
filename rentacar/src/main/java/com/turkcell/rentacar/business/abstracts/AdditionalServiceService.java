package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.getDto.GetAdditionalServiceDto;
import com.turkcell.rentacar.business.dtos.listDto.AdditionalServiceListDto;
import com.turkcell.rentacar.business.requests.create.CreateAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.update.UpdateAdditionalServiceRequest;
import com.turkcell.rentacar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;


import java.util.List;

public interface AdditionalServiceService {

    Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) throws BusinessException;
    Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) throws BusinessException;
    Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) throws  BusinessException;

    DataResult<GetAdditionalServiceDto> getByAdditionalServiceId(Integer id)throws BusinessException;
    DataResult<List<AdditionalServiceListDto>> getAll() throws BusinessException;

}
