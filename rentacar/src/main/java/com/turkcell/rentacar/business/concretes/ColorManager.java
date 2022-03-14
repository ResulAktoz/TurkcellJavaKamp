package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.ColorService;
import com.turkcell.rentacar.business.dtos.listDto.ColorListDto;
import com.turkcell.rentacar.business.dtos.getDto.GetColorDto;
import com.turkcell.rentacar.business.requests.create.CreateColorRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteColorRequest;
import com.turkcell.rentacar.business.requests.update.UpdateColorRequest;
import com.turkcell.rentacar.core.exceptions.ColorAlreadyExistsException;
import com.turkcell.rentacar.core.exceptions.ColorNotfoundException;
import com.turkcell.rentacar.core.mapping.ModelMapperService;
import com.turkcell.rentacar.core.results.*;
import com.turkcell.rentacar.dataAccess.abstracts.ColorDao;
import com.turkcell.rentacar.entities.concretes.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColorManager implements ColorService {

    private ColorDao colorDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public ColorManager(ColorDao colorDao,ModelMapperService modelMapperService) {
        this.colorDao = colorDao;
        this.modelMapperService=modelMapperService;
    }

    @Override
    public DataResult<List<ColorListDto>> getAll() {
        List<Color> result = this.colorDao.findAll();
        List<ColorListDto> response = result.stream()
                .map(color -> this.modelMapperService
                        .forDto()
                        .map(color,ColorListDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ColorListDto>>(response,"Veriler listelendi.");
    }

    @Override
    public Result add(CreateColorRequest createColorRequest) {
    	 if(this.colorDao.existsColorByName(createColorRequest.getName())) {
             throw new ColorAlreadyExistsException("Aynı isimde renk kayıtlı");
             //return new ErrorResult("Aynı isimde renk kayıtlı.");
         }else {
             Color color = this.modelMapperService
                     .forRequest().map(createColorRequest,Color.class);
             this.colorDao.save(color);
             return new SuccessResult("Renk eklendi.");
        }
    }

    @Override
    public DataResult<GetColorDto> getById(Integer id) {
    	if(this.colorDao.existsById(id)) {
            Color foundColor = colorDao.getById(id);
            GetColorDto response = this.modelMapperService.forDto().map(foundColor, GetColorDto.class);
            return new SuccessDataResult<GetColorDto>(response,"Bu Id'de renk kayıtlı.");
        }else {
            throw new ColorNotfoundException("Bu id'de renk bulunamadı");

        }
  }
    @Override
    public Result update(UpdateColorRequest updateColorRequest) {
        if (this.colorDao.existsById(updateColorRequest.getId())) {
            Color color = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);
            colorDao.save(color);
            return new SuccessResult("Renk güncellendi");
        }else {
            throw new ColorNotfoundException("Bu id'de kayıtlı renk bulunamadı.");

        }
    }

    @Override
    public Result delete(DeleteColorRequest deleteColorRequest) {
        if (this.colorDao.existsById(deleteColorRequest.getId())) {
            colorDao.deleteById(deleteColorRequest.getId());
            return new SuccessResult("Renk silindi.");
        }else {
            //throw new ColorNotfoundException("Bu id'de kayıtlı renk bulunamadı.");
            return new ErrorResult("Bu id'de kayıtlı renk bulunamadı.");
        }
    }

    
}
