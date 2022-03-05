package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.BrandService;
import com.turkcell.rentacar.business.dtos.GetBrandDto;
import com.turkcell.rentacar.business.requests.DeleteBrandRequest;
import com.turkcell.rentacar.business.requests.UpdateBrandRequest;
import com.turkcell.rentacar.business.dtos.BrandListDto;
import com.turkcell.rentacar.business.requests.CreateBrandRequest;
import com.turkcell.rentacar.core.exceptions.BrandAlreadyExistsException;
import com.turkcell.rentacar.core.exceptions.BrandNotFoundException;
import com.turkcell.rentacar.core.mapping.ModelMapperService;
import com.turkcell.rentacar.core.results.*;
import com.turkcell.rentacar.dataAccess.abstracts.BrandDao;
import com.turkcell.rentacar.entities.concretes.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return new SuccessDataResult<List<BrandListDto>>(response,"Veri listelendi.");
    }

    @Override
    public Result add(CreateBrandRequest createBrandRequest) {
        if(this.brandDao.existsBrandByName(createBrandRequest.getName())) {
            throw new BrandAlreadyExistsException("Bu id'ye sahip marka sisteme kayıtlı");
            //return new ErrorResult("Kayıtlı marka");
        }else {
            //System.out.println(createBrandRequest.getName());
            Brand brand = this.modelMapperService
                    .forRequest().map(createBrandRequest,Brand.class);
            //System.out.println(createBrandRequest.getName());
            this.brandDao.save(brand);
            return new SuccessResult("Marka eklendi.");
            
        }
    }

    @Override
    public DataResult<GetBrandDto> getById(Integer id) {
    	Optional<Brand> brand = brandDao.findById(id);
        if(brand.isPresent()) {
        GetBrandDto response = this.modelMapperService.forDto().map(brand.get(),GetBrandDto.class);
        return new SuccessDataResult<GetBrandDto>(response,"Bu Id'de marka kayıtlı") ;
        }else {
        	throw new BrandNotFoundException("Bu id'de kayıtlı marka bulunamadı");
        //return new ErrorDataResult<GetBrandDto>("Bu Id'de marka bulunamadı");
        }
    }

	

	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) {
		if(this.brandDao.findById(updateBrandRequest.getId()).isPresent()) {
			Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
			brandDao.save(brand);
            return new SuccessResult("Marka güncellendi.");
		}else {
			throw new BrandNotFoundException("Bu id'de kayıtlı Marka bulunamadı");
		   // return new ErrorResult("Bu Id'de kayıtlı marka bulunamadı, güncelleme başarısız. ");
        }
		
	}

	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) {
		if(this.brandDao.findById(deleteBrandRequest.getId()).isPresent()) {
			brandDao.deleteById(deleteBrandRequest.getId());
            return new SuccessResult("Marka silindi.");
		}else {
			throw new BrandNotFoundException("Bu id'de kayıtlı marka bulunamadı");
            //return new ErrorResult("Marka bulunamadı, silinme başarız");
        }
		
	}
}
