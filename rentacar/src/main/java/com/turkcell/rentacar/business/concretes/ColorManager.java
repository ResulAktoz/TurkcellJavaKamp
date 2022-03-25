package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.ColorService;
import com.turkcell.rentacar.business.constants.messages.BusinessMessages;
import com.turkcell.rentacar.business.dtos.getDto.GetColorDto;
import com.turkcell.rentacar.business.dtos.listDto.ColorListDto;
import com.turkcell.rentacar.business.requests.create.CreateColorRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteColorRequest;
import com.turkcell.rentacar.business.requests.update.UpdateColorRequest;
import com.turkcell.rentacar.core.utilities.exceptions.ColorAlreadyExistsException;
import com.turkcell.rentacar.core.utilities.exceptions.ColorNotfoundException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.*;
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
        return new SuccessDataResult<List<ColorListDto>>(response, BusinessMessages.COLORS_LISTED_SUCCESSFULLY);
    }

    @Override
    public Result add(CreateColorRequest createColorRequest) {
        checkIfColorNameExists(createColorRequest.getName());

        Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);

        this.colorDao.save(color);

        return new SuccessResult(BusinessMessages.COLOR_ADDED_SUCCESSFULLY);
    }

    @Override
    public DataResult<GetColorDto> getById(int id) {

        Color color = this.colorDao.getById(id);
        GetColorDto response = this.modelMapperService.forDto().map(color, GetColorDto.class);

        return new SuccessDataResult<GetColorDto>(response, BusinessMessages.COLOR_LISTED_SUCCESSFULLY);

  }
    @Override
    public Result update(UpdateColorRequest updateColorRequest) {
        checkIfColorExists(updateColorRequest.getId());

        Color color = this.modelMapperService.forRequest()
                .map(updateColorRequest, Color.class);

        this.colorDao.save(color);

        return new SuccessResult(BusinessMessages.COLOR_UPDATED_SUCCESSFULLY);
    }

    @Override
    public Result delete(DeleteColorRequest deleteColorRequest) {
        checkIfColorExists(deleteColorRequest.getId());

        Color color = this.modelMapperService.forRequest()
                .map(deleteColorRequest, Color.class);
        this.colorDao.deleteById(color.getId());

        return new SuccessResult(BusinessMessages.COLOR_DELETED_SUCCESSFULLY);
    }

    private void checkIfColorExists(int colorId){
        if(!this.colorDao.existsById(colorId)){
            throw new ColorNotfoundException(BusinessMessages.COLOR_NOT_FOUND);
        }
    }

    private void checkIfColorNameExists(String colorName) {
        if (this.colorDao.existsColorByColorName(colorName)) {
            throw new ColorAlreadyExistsException(BusinessMessages.COLOR_ALREADY_EXISTS);
        }
    }

    
}
