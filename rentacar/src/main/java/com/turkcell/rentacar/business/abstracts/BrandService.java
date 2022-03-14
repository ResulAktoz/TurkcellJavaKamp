package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.getDto.GetBrandDto;
import com.turkcell.rentacar.business.requests.delete.DeleteBrandRequest;
import com.turkcell.rentacar.business.requests.update.UpdateBrandRequest;
import com.turkcell.rentacar.business.dtos.listDto.BrandListDto;
import com.turkcell.rentacar.business.requests.create.CreateBrandRequest;
import com.turkcell.rentacar.core.results.DataResult;
import com.turkcell.rentacar.core.results.Result;

import java.util.List;

public interface BrandService {
    DataResult<List<BrandListDto>> getAll();
    Result add(CreateBrandRequest createBrandRequest);
    DataResult<GetBrandDto> getById(Integer id);
    Result update(UpdateBrandRequest updateBrandRequest);
    Result delete(DeleteBrandRequest deleteBrandRequest);
    

}
