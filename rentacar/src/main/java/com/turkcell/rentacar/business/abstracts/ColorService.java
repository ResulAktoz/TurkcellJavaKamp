package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.listDto.ColorListDto;
import com.turkcell.rentacar.business.dtos.getDto.GetColorDto;
import com.turkcell.rentacar.business.requests.create.CreateColorRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteColorRequest;
import com.turkcell.rentacar.business.requests.update.UpdateColorRequest;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

import java.util.List;

public interface ColorService {
    DataResult<List<ColorListDto>> getAll();
    Result add(CreateColorRequest createColorRequest);
    DataResult<GetColorDto> getById(Integer id);
    Result update(UpdateColorRequest updateColorRequest);
    Result delete(DeleteColorRequest deleteColorRequest);
}
