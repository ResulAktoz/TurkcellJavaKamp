package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.BrandService;
import com.turkcell.rentacar.business.constants.messages.BusinessMessages;
import com.turkcell.rentacar.business.dtos.getDto.GetBrandDto;
import com.turkcell.rentacar.business.dtos.listDto.BrandListDto;
import com.turkcell.rentacar.business.requests.create.CreateBrandRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteBrandRequest;
import com.turkcell.rentacar.business.requests.update.UpdateBrandRequest;
import com.turkcell.rentacar.core.utilities.exceptions.BrandAlreadyExistsException;
import com.turkcell.rentacar.core.utilities.exceptions.BrandNotFoundException;
import com.turkcell.rentacar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.BrandDao;
import com.turkcell.rentacar.entities.concretes.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandManager implements BrandService {

    private BrandDao brandDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public BrandManager(BrandDao brandDao,ModelMapperService modelMapperService) {
        this.brandDao = brandDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<BrandListDto>> getAll() {
        List<Brand> result = this.brandDao.findAll();
        List<BrandListDto> response = result.stream()
                .map(brand -> this.modelMapperService
                        .forDto()
                        .map(brand,BrandListDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<BrandListDto>>(response, BusinessMessages.BRANDS_LISTED_SUCCESSFULLY);
    }

    @Override
    public Result add(CreateBrandRequest createBrandRequest) {

            Brand brand = this.modelMapperService
                    .forRequest().map(createBrandRequest,Brand.class);

            this.brandDao.save(brand);
            return new SuccessResult(BusinessMessages.BRAND_ADDED_SUCCESSFULLY);

    }

    @Override
    public DataResult<GetBrandDto> getById(int id) {

        Brand brand = this.brandDao.getById(id);
        GetBrandDto response = this.modelMapperService.forDto().map(brand, GetBrandDto.class);

        return new SuccessDataResult<GetBrandDto>(response, BusinessMessages.BRAND_LISTED_SUCCESSFULLY);
    }

	

	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) {
        checkIfBrandExists(updateBrandRequest.getId());
        Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
        brandDao.save(brand);
        return new SuccessResult(BusinessMessages.BRAND_UPDATED_SUCCESSFULLY);

		
	}

	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) {
        checkIfBrandExists(deleteBrandRequest.getId());
        this.brandDao.deleteById(deleteBrandRequest.getId());
        return new SuccessResult(BusinessMessages.BRAND_DELETED_SUCCESSFULLY);

		
	}

    private void checkIfBrandExists(int brandId){
    if(this.brandDao.existsById(brandId)){
        throw new BusinessException(BusinessMessages.BRAND_NOT_FOUND);
        }
    }
}
