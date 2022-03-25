package com.turkcell.rentacar.api.controllers;

import java.util.List;

import com.turkcell.rentacar.business.requests.update.UpdateCarKilometerInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.dtos.listDto.CarListDto;
import com.turkcell.rentacar.business.dtos.getDto.GetCarDto;
import com.turkcell.rentacar.business.requests.create.CreateCarRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteCarRequest;
import com.turkcell.rentacar.business.requests.update.UpdateCarRequest;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

import javax.transaction.Transactional;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/cars")
public class CarsController {
	
	private CarService carService;

	@Autowired
	public CarsController(CarService carService) {
		super();
		this.carService = carService;
	}
	@GetMapping("/getAll")
	public DataResult<List<CarListDto>> getAll() {
		return this.carService.getAll();
				
	}

	@PostMapping("/add")
	
	public Result add(@RequestBody @Valid CreateCarRequest createCarRequest) {
		return this.carService.add(createCarRequest);

	}

	@GetMapping("/getByCarId")
    public DataResult<GetCarDto> getByCarId(@PathVariable("carId") Integer carId) {
        return this.carService.getByCarId(carId);
		
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestBody DeleteCarRequest deleteCarRequest) {
		 return this.carService.delete(deleteCarRequest);
		
	}

	@PutMapping("/put")
	public Result update(UpdateCarRequest updateCarRequest) {
		 return this.carService.update(updateCarRequest);
		
		
	}
	@Transactional
	@PostMapping("/updateKilometerInfo")
	public Result updateKilometerInfo(@RequestBody @Valid UpdateCarKilometerInfoRequest updateCarKilometerInfoRequest){
		return this.carService.updateKilometerInfo(updateCarKilometerInfoRequest);
	}

	 @GetMapping("/getALlPaged/{pageNumber}/{pageSize}")
	 DataResult<List<CarListDto>> getAllPaged(@PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize){
		 return this.carService.getAllPaged(pageNumber-1, pageSize);
	 }
	 
	 @GetMapping("/getAllSorted/{orderOfSort}")
	    DataResult<List<CarListDto>> getAllSorted (@RequestParam("orderOfSort") boolean sort){
	    	
	    	return this.carService.getAllSorted(sort);
	    }
	    
	    @GetMapping("/findByDailyPriceLessThan/{requestedPrice}")
	    DataResult<List<CarListDto>> findByDailyPriceLessThan(@PathVariable("requestedPrice") double requestedPrice){
	    	
	    	return this.carService.findByDailyPriceLessThan(requestedPrice);
	    	
	    }
	    
	    @GetMapping("/findByDailyPriceBetween/{since}/{until}")
	    DataResult<List<CarListDto>> findByDailyPriceBetween (@PathVariable("since") double since, @PathVariable("until") double until){
	    	
	    	return this.carService.findByDailyPriceBetween(since, until);
}
}