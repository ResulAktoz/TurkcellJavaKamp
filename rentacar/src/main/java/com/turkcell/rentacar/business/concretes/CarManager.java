package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import com.turkcell.rentacar.business.constants.messages.BusinessMessages;
import com.turkcell.rentacar.business.requests.update.UpdateCarKilometerInfoRequest;
import com.turkcell.rentacar.core.utilities.exceptions.CarNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.dtos.listDto.CarListDto;
import com.turkcell.rentacar.business.dtos.getDto.GetCarDto;
import com.turkcell.rentacar.business.requests.create.CreateCarRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteCarRequest;
import com.turkcell.rentacar.business.requests.update.UpdateCarRequest;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.ErrorDataResult;
import com.turkcell.rentacar.core.utilities.results.ErrorResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.BrandDao;
import com.turkcell.rentacar.dataAccess.abstracts.CarDao;
import com.turkcell.rentacar.dataAccess.abstracts.ColorDao;
import com.turkcell.rentacar.entities.concretes.Car;

import javax.persistence.EntityNotFoundException;


@Service
public class CarManager implements CarService{
	
	private CarDao carDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CarManager(CarDao carDao, ModelMapperService modelMapperService,BrandDao brandDao, ColorDao colorDao) {
		super();
		this.carDao = carDao;
		this.modelMapperService = modelMapperService;
	}

	

	@Override
	public DataResult<List<CarListDto>> getAll() {
		List<Car> result = this.carDao.findAll();
        List<CarListDto> response = result.stream()
                .map(car -> this.modelMapperService
                        .forDto()
                        .map(car,CarListDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<CarListDto>>(response, BusinessMessages.CARS_LISTED_SUCCESSFULLY);
	}

	@Override
	public Result add(CreateCarRequest createCarRequest) {
		
			Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
			this.carDao.save(car);
			return new SuccessResult(BusinessMessages.CAR_ADDED_SUCCESSFULLY);
			
	}

	@Override
	public DataResult<GetCarDto> getByCarId(int id) {

		Car car = carDao.getById(id); //Optional<Car> car = carDao.findById(id);
        GetCarDto response = modelMapperService.forDto()
				.map(car,GetCarDto.class);
        return new SuccessDataResult<GetCarDto>(response, BusinessMessages.CAR_LISTED_SUCCESSFULLY);


	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) {
		checkIfCarExists(deleteCarRequest.getId());
		this.carDao.deleteById(deleteCarRequest.getId());
		return new SuccessResult(BusinessMessages.CAR_DELETED_SUCCESSFULLY);

	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {
		checkIfCarExists(updateCarRequest.getId());
		Car car = this.modelMapperService.forRequest()
				.map(updateCarRequest, Car.class);
		

		this.carDao.save(car);
		return new SuccessResult(BusinessMessages.CAR_UPDATED_SUCCESSFULLY);

	}


	@Override
	public Result updateKilometerInfo(UpdateCarKilometerInfoRequest updateCarKilometerInfoRequest) {
		this.carDao.updateKilometerToCarByCarId(updateCarKilometerInfoRequest.getCarId(),
				updateCarKilometerInfoRequest.getKilometerInfo());
		return new SuccessResult(BusinessMessages.KILOMETER_INFO_CAR_UPDATED_SUCCESSFULLY);

	}


	@Override
	public DataResult<List<CarListDto>> getAllPaged(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
		List<Car> result = this.carDao.findAll(pageable).getContent(); //listeye çeviriyoruz .getContent olmazsa sayfayı getirmeye çalışır.
		List<CarListDto> response = result.stream()
				.map(car -> this.modelMapperService.forDto().map(car, CarListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response, BusinessMessages.CAR_LISTED_AND_PAGINATED_SUCCESSFULLY);
		
	}



	@Override
	public DataResult<List<CarListDto>> getAllSorted(boolean sort) {

		Sort sorted = Sort.by(checkSortDirectionType(sort), "dailyPrice");

		List<Car> result = this.carDao.findAll(sorted);

		List<CarListDto> response = result.stream()
				.map(car -> this.modelMapperService.forDto().map(car, CarListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response, BusinessMessages.CAR_SORTED_SUCCESSFULLY);
	}



	@Override
	public DataResult<List<CarListDto>> findByDailyPriceLessThan(double requestedPrice) {
		List<Car> result = this.carDao.findByDailyPriceLessThan(requestedPrice);

		List<CarListDto> response = result.stream()
				.map(car -> this.modelMapperService.forDto()
						.map(car, CarListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response, BusinessMessages.CAR_LISTED_SUCCESSFULLY_BY_DAILY_PRICE);
		
	}



	@Override
	public DataResult<List<CarListDto>> findByDailyPriceBetween(double since, double until) {
		List<Car> result = this.carDao.findByDailyPriceBetween(since, until);

		List<CarListDto> response = result.stream()
				.map(car -> this.modelMapperService.forDto().map(car, CarListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response);
	}

	private void checkIfCarExists(int carId){
		if(!this.carDao.existsCarByCarId(carId)){
			throw new EntityNotFoundException(BusinessMessages.CAR_NOT_FOUND);
		}
	}

	private Sort.Direction checkSortDirectionType(boolean sort){
		if(sort){
			return  Sort.Direction.ASC;
		}else{
			return Sort.Direction.DESC;
		}
	}
	
}

