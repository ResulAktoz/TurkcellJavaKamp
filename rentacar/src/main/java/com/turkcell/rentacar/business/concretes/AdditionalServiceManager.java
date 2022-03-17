package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.AdditionalServiceService;
import com.turkcell.rentacar.business.dtos.getDto.GetAdditionalServiceDto;
import com.turkcell.rentacar.business.dtos.listDto.AdditionalServiceListDto;
import com.turkcell.rentacar.business.requests.create.CreateAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.update.UpdateAdditionalServiceRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.mapping.ModelMapperService;
import com.turkcell.rentacar.core.results.DataResult;
import com.turkcell.rentacar.core.results.Result;
import com.turkcell.rentacar.core.results.SuccessDataResult;
import com.turkcell.rentacar.core.results.SuccessResult;
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
        return new SuccessResult("Ek hizmet eklendi");
    }

    @Override
    public Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) throws BusinessException {
        checkIfAdditionalServiceIdExists(updateAdditionalServiceRequest.getAdditionalServiceId());
        AdditionalService additionalService = this.modelMapperService.forRequest()
                .map(updateAdditionalServiceRequest, AdditionalService.class);
        this.additionalServiceDao.save(additionalService);
        return new SuccessResult("Ek urun güncellendi");
    }

    @Override
    public Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) throws BusinessException {
        checkIfAdditionalServiceIdExists(deleteAdditionalServiceRequest.getAdditionalServiceId());
        this.additionalServiceDao.deleteById(deleteAdditionalServiceRequest.getAdditionalServiceId());
        return new SuccessResult("Ek ürün başarıyla kaldırıldı.");
    }

    @Override
    public DataResult<GetAdditionalServiceDto> getByAdditionalServiceId(Integer id) throws BusinessException {
        checkIfAdditionalServiceIdExists(id);
        AdditionalService additionalService= additionalServiceDao.getById(id);
        GetAdditionalServiceDto response = this.modelMapperService.forDto().
                map(additionalService, GetAdditionalServiceDto.class);
        return new SuccessDataResult<GetAdditionalServiceDto> (response,"Ürün bilgisi başarıyla getirildi.");
    }

    @Override
    public DataResult<List<AdditionalServiceListDto>> getAll() throws BusinessException {
        List<AdditionalService> result =this.additionalServiceDao.findAll();

        List<AdditionalServiceListDto> response = result.stream()
                .map(additionalService -> this.modelMapperService.forDto()
                        .map(additionalService,AdditionalServiceListDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<AdditionalServiceListDto>>(response,"Ürün bilgileri başarıyla sıralandı");
    }

    private void checkIfAdditionalServiceNameExists(String additionalServiceName) throws BusinessException{

        if(this.additionalServiceDao.existsByAdditionalServiceName(additionalServiceName)){
            throw new BusinessException("Bu isimde ek hizmet mevcut.");
        }
    }

    private void checkIfAdditionalServiceIdExists(Integer id) throws BusinessException{
        if(!this.additionalServiceDao.existsById(id)){
            throw new BusinessException("Bu id'ye ait ek hizmet yok.");

        }
    }

}
