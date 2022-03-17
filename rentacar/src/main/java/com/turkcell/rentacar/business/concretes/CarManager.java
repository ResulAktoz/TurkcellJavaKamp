package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import com.turkcell.rentacar.business.requests.update.UpdateCarKilometerInfoRequest;
import com.turkcell.rentacar.core.exceptions.CarNotFoundException;
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
import com.turkcell.rentacar.core.mapping.ModelMapperService;
import com.turkcell.rentacar.core.results.DataResult;
import com.turkcell.rentacar.core.results.ErrorDataResult;
import com.turkcell.rentacar.core.results.ErrorResult;
import com.turkcell.rentacar.core.results.Result;
import com.turkcell.rentacar.core.results.SuccessDataResult;
import com.turkcell.rentacar.core.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.BrandDao;
import com.turkcell.rentacar.dataAccess.abstracts.CarDao;
import com.turkcell.rentacar.dataAccess.abstracts.ColorDao;
import com.turkcell.rentacar.entities.concretes.Car;


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
        return new SuccessDataResult<List<CarListDto>>(response,"Veriler listelendi");		
	}

	@Override
	public Result add(CreateCarRequest createCarRequest) {
		
			Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
			this.carDao.save(car);
			return new SuccessResult("Araba eklendi");
			
	}

	@Override
	public DataResult<GetCarDto> getByCarId(int id) {
		if(carDao.existsCarById(id)) {
		Car car = carDao.getById(id); //Optional<Car> car = carDao.findById(id);
        GetCarDto response = modelMapperService.forDto().map(car,GetCarDto.class);
        return new SuccessDataResult<GetCarDto>(response,"Id'ye göre listelendi");
		}else {
			return new ErrorDataResult<GetCarDto>("Bu Id'de araç bulunamadı");
		}
	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) {
	
			if (this.carDao.existsCarById(deleteCarRequest.getId())) {
					this.carDao.deleteById(deleteCarRequest.getId());
					return new SuccessResult("Araba silindi.");
			} else {
			return new ErrorResult("Araba bulunamadı");
		}		
	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {
		if(carDao.existsCarById(updateCarRequest.getId())) {
		Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		

		this.carDao.save(car);
		return new SuccessResult("Araba güncellendi");
		}else {
			throw new CarNotFoundException("Araba bulunamadı");
			//return new ErrorResult("Araba bulunamadı");
			
		}
	}

	@Override
	public Result existByCarId(int id) {
		if(this.carDao.existsCarById(id)){
			return new SuccessResult("Araba bulundu.");
		}
		return new ErrorResult("Araba bulunamadı");
	}

	@Override
	public Result ıpdateKilometerInfo(UpdateCarKilometerInfoRequest updateCarKilometerInfoRequest) {
		this.carDao.updateKilometerToCarByCarId(updateCarKilometerInfoRequest.getCarId(),
				updateCarKilometerInfoRequest.getKilometerInfo());
		return new SuccessResult("Kilometre bilgisi güncellendi.");

	}


	@Override
	public DataResult<List<CarListDto>> getAllPaged(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
		List<Car> result = this.carDao.findAll(pageable).getContent(); //listeye çeviriyoruz .getContent olmazsa sayfayı getirmeye çalışır.
		List<CarListDto> response = result.stream()
				.map(car -> this.modelMapperService.forDto().map(car, CarListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response);
		
	}



	@Override
	public DataResult<List<CarListDto>> getAllSorted(String param) {

		Sort sort;

		if(param.toUpperCase().equals("ASC")) {
			sort = Sort.by(Sort.Direction.ASC, "dailyPrice");
		}else if(param.toUpperCase().equals("DESC")) {
			sort = Sort.by(Sort.Direction.DESC, "dailyPrice");
		}else {
			return new ErrorDataResult<List<CarListDto>>("Düzgün parametre giriniz. ASC veya DESC");
		}

		List<Car> result = this.carDao.findAll(sort);

		List<CarListDto> response = result.stream()
				.map(car -> this.modelMapperService.forDto().map(car, CarListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response);
	}



	@Override
	public DataResult<List<CarListDto>> findByDailyPriceLessThan(double requestedPrice) {
		List<Car> result = this.carDao.findByDailyPriceLessThan(requestedPrice);

		List<CarListDto> response = result.stream()
				.map(car -> this.modelMapperService.forDto().map(car, CarListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response);
		
	}



	@Override
	public DataResult<List<CarListDto>> findByDailyPriceBetween(double since, double until) {
		List<Car> result = this.carDao.findByDailyPriceBetween(since, until);

		List<CarListDto> response = result.stream()
				.map(car -> this.modelMapperService.forDto().map(car, CarListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response);
	}	
	
}

