package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.AdditionalServiceService;
import com.turkcell.rentacar.business.constants.messages.BusinessMessages;
import com.turkcell.rentacar.business.dtos.getDto.GetAdditionalServiceDto;
import com.turkcell.rentacar.business.dtos.listDto.AdditionalServiceListDto;
import com.turkcell.rentacar.business.requests.create.CreateAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.update.UpdateAdditionalServiceRequest;
import com.turkcell.rentacar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.AdditionalServiceDao;
import com.turkcell.rentacar.entities.concretes.AdditionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdditionalServiceManager implements AdditionalServiceService {

    private AdditionalServiceDao additionalServiceDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public AdditionalServiceManager(AdditionalServiceDao additionalServiceDao, ModelMapperService modelMapperService) {
        this.additionalServiceDao = additionalServiceDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) throws BusinessException {
        checkIfAdditionalServiceNameExists(createAdditionalServiceRequest.getAdditionalServiceName());

        AdditionalService additionalService = this.modelMapperService.forRequest().map(createAdditionalServiceRequest, AdditionalService.class);
        this.additionalServiceDao.save(additionalService);
        return new SuccessResult(BusinessMessages.ADDITIONAL_SERVICE_ADDED_SUCCESSFULLY);
    }

    @Override
    public Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) throws BusinessException {
        checkIfAdditionalServiceIdExists(updateAdditionalServiceRequest.getAdditionalServiceId());
        AdditionalService additionalService = this.modelMapperService.forRequest()
                .map(updateAdditionalServiceRequest, AdditionalService.class);
        this.additionalServiceDao.save(additionalService);
        return new SuccessResult(BusinessMessages.ADDITIONAL_SERVICE_UPDATED_SUCCESSFULLY);
    }

    @Override
    public Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) throws BusinessException {
        checkIfAdditionalServiceIdExists(deleteAdditionalServiceRequest.getAdditionalServiceId());
        this.additionalServiceDao.deleteById(deleteAdditionalServiceRequest.getAdditionalServiceId());
        return new SuccessResult(BusinessMessages.ADDITIONAL_SERVICE_DELETED_SUCCESSFULLY);
    }


    @Override
    public DataResult<List<AdditionalServiceListDto>> getAll() throws BusinessException {
        List<AdditionalService> result =this.additionalServiceDao.findAll();

        List<AdditionalServiceListDto> response = result.stream()
                .map(additionalService -> this.modelMapperService.forDto()
                        .map(additionalService,AdditionalServiceListDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<AdditionalServiceListDto>>(response,BusinessMessages.ADDITIONAL_SERVICE_LISTED_SUCCESSFULLY);
    }


    private void checkIfAdditionalServiceNameExists(String additionalServiceName) throws BusinessException{

        if(this.additionalServiceDao.existsByAdditionalServiceName(additionalServiceName)){
            throw new BusinessException(BusinessMessages.ADDITIONAL_SERVICE_ALREADY_EXISTS);
        }
    }

    private void checkIfAdditionalServiceIdExists(int additionalServiceId) throws BusinessException{
        if(!this.additionalServiceDao.existsById(additionalServiceId)){
            throw new BusinessException(BusinessMessages.ADDITIONAL_SERVICE_NOT_FOUND);

        }
    }

}
