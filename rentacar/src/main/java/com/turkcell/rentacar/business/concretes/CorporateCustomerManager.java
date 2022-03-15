package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.CorporateCustomerService;
import com.turkcell.rentacar.business.dtos.getDto.GetCorporateCustomerDto;
import com.turkcell.rentacar.business.dtos.listDto.CorporateCustomerListDto;
import com.turkcell.rentacar.business.requests.create.CreateCorporateCustomerRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteCorporateCustomerRequest;
import com.turkcell.rentacar.business.requests.update.UpdateCorporateCustomerRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.mapping.ModelMapperService;
import com.turkcell.rentacar.core.results.DataResult;
import com.turkcell.rentacar.core.results.Result;
import com.turkcell.rentacar.core.results.SuccessDataResult;
import com.turkcell.rentacar.core.results.SuccessResult;
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
    public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) throws BusinessException {
        CorporateCustomer corporateCustomer = this.modelMapperService.forRequest()
                .map(createCorporateCustomerRequest, CorporateCustomer.class);

        this.corporateCustomerDao.save(corporateCustomer);
        return new SuccessResult("Başarıyla eklendi");
    }

    @Override
    public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) throws BusinessException {
        checkIfCorporateCustomerExistById(updateCorporateCustomerRequest.getUserId());

        CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(updateCorporateCustomerRequest, CorporateCustomer.class);
        this.corporateCustomerDao.save(corporateCustomer);

        return new SuccessResult("Kurumsal müşteri başarıyla güncellendi.");

    }

    @Override
    public Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) throws BusinessException {
        checkIfCorporateCustomerExistById(deleteCorporateCustomerRequest.getUserId());

        this.corporateCustomerDao.deleteById(deleteCorporateCustomerRequest.getUserId());

        return new SuccessResult("Kurumsal müşteri başarıyla silindi.");
    }

    @Override
    public DataResult<List<CorporateCustomerListDto>> getAll() throws BusinessException {
        List<CorporateCustomer> result = this.corporateCustomerDao.findAll();

        List<CorporateCustomerListDto> response = result.stream()
                .map(corporateCustomer -> this.modelMapperService.forDto()
                        .map(corporateCustomer, CorporateCustomerListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<CorporateCustomerListDto>>(response, "Veriler başarıyla yüklendi.");
    }

    @Override
    public DataResult<GetCorporateCustomerDto> getByUserId(int userId) throws BusinessException {
        checkIfCorporateCustomerExistById(userId);

        CorporateCustomer corporateCustomer=this.corporateCustomerDao.getById(userId);

        GetCorporateCustomerDto response = this.modelMapperService.forDto().map(corporateCustomer, GetCorporateCustomerDto.class);

        return new SuccessDataResult<GetCorporateCustomerDto>(response, "Veriler kullanıcı id'sine göre listelendi.");
    }

    private void checkIfCorporateCustomerExistById(int userId) throws BusinessException{

       if(!this.corporateCustomerDao.existsById(userId)){
           throw new BusinessException("Bu id'de kayıtlı kurumsal müşteri bulunamadı");
       }

    }
}
