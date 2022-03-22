package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.BrandService;
import com.turkcell.rentacar.business.dtos.listDto.BrandListDto;
import com.turkcell.rentacar.business.dtos.getDto.GetBrandDto;
import com.turkcell.rentacar.business.requests.create.CreateBrandRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteBrandRequest;
import com.turkcell.rentacar.business.requests.update.UpdateBrandRequest;

import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/brands")
public class BrandsController {

    private BrandService brandService;

    @Autowired
    public BrandsController(BrandService brandService) {

        this.brandService = brandService;
    }
    @GetMapping("/getall")
    DataResult<List<BrandListDto>> getAll(){

        return this.brandService.getAll();
    }
    
    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateBrandRequest createBrandRequest) {
    		return this.brandService.add(createBrandRequest);
    }
    
    @GetMapping("/getByBrandId/{brandId}")
    public DataResult<GetBrandDto> getByBrandId(@RequestParam ("brandID") int brandId) {
    	return this.brandService.getById(brandId);
    }
    
    @PutMapping("/update")
    Result update(@RequestBody @Valid UpdateBrandRequest updateBrandRequest) {
    	return this.brandService.update(updateBrandRequest);
    }
    
    @DeleteMapping("/delete")
    Result delete(@RequestBody @Valid DeleteBrandRequest deleteBrandRequest) {
    	return this.brandService.delete(deleteBrandRequest);
    	
    
    
    }
}
