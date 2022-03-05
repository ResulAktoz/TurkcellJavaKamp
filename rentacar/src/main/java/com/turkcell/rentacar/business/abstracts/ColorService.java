package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.ColorListDto;
import com.turkcell.rentacar.business.dtos.GetColorDto;
import com.turkcell.rentacar.business.requests.CreateColorRequest;
import com.turkcell.rentacar.business.requests.DeleteColorRequest;
import com.turkcell.rentacar.business.requests.UpdateColorRequest;
import com.turkcell.rentacar.core.results.DataResult;
import com.turkcell.rentacar.core.results.Result;

import java.util.List;

public interface ColorService {
    DataResult<List<ColorListDto>> getAll();
    Result add(CreateColorRequest createColorRequest);
    DataResult<GetColorDto> getById(Integer id);
    Result update(UpdateColorRequest updateColorRequest);
    Result delete(DeleteColorRequest deleteColorRequest);
}