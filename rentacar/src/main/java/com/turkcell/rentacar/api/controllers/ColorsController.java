package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.ColorService;
import com.turkcell.rentacar.business.dtos.ColorListDto;
import com.turkcell.rentacar.business.dtos.GetColorDto;
import com.turkcell.rentacar.business.requests.CreateColorRequest;
import com.turkcell.rentacar.business.requests.DeleteColorRequest;
import com.turkcell.rentacar.business.requests.UpdateColorRequest;

import com.turkcell.rentacar.core.results.DataResult;
import com.turkcell.rentacar.core.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/colors")
public class ColorsController {

    private ColorService colorService;

    @Autowired
    public ColorsController(ColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping("/getAll")
    public DataResult<List<ColorListDto>> getAll() {
        return colorService.getAll();
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateColorRequest createColorRequest) {
        return this.colorService.add(createColorRequest);
    }

    @GetMapping("/getById/{id}")
    public DataResult<GetColorDto> getColorById(@PathVariable Integer id) {
        return colorService.getById(id);
    }
    
    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateColorRequest updateColorRequest) {
        return this.colorService.update(updateColorRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody @Valid DeleteColorRequest deleteColorRequest) {
        return this.colorService.delete(deleteColorRequest);
    }
}
