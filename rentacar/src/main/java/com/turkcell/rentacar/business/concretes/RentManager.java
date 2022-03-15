package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.CarMaintenanceService;
import com.turkcell.rentacar.business.abstracts.RentService;
import com.turkcell.rentacar.business.dtos.getDto.GetRentDto;
import com.turkcell.rentacar.business.dtos.listDto.RentListDto;
import com.turkcell.rentacar.business.requests.create.CreateRentRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteRentRequest;
import com.turkcell.rentacar.business.requests.update.UpdateRentRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.mapping.ModelMapperService;
import com.turkcell.rentacar.core.results.*;
import com.turkcell.rentacar.dataAccess.abstracts.RentDao;
import com.turkcell.rentacar.entities.concretes.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentManager implements RentService {

    private RentDao rentDao;
    private ModelMapperService modelMapperService;
    private CarMaintenanceService carMaintenanceService;
    @Lazy
    @Autowired
    public RentManager(RentDao rentDao, ModelMapperService modelMapperService, CarMaintenanceService carMaintenanceService) {
        this.rentDao = rentDao;
        this.modelMapperService = modelMapperService;
        this.carMaintenanceService = carMaintenanceService;
    }

    @Override
    public Result add(CreateRentRequest createRentRequest) throws BusinessException {
        this.carMaintenanceService.checkIfCarMaintenanceIdExist(createRentRequest.getCarId());
        checkIfCarIsRented(createRentRequest.getCarId());

        Rent rent = this.modelMapperService.forRequest().map(createRentRequest, Rent.class);
        this.rentDao.save(rent);

        return new SuccessResult("Kiralama eklendi");
    }

    @Override
    public Result update(UpdateRentRequest updateRentRequest) throws BusinessException {
        checkIfRentIdExist(updateRentRequest.getRentId());

        Rent rent = this.modelMapperService.forRequest().map(updateRentRequest, Rent.class);
        this.rentDao.save(rent);

        return new SuccessResult("Kiralama güncellendi");
    }

    @Override
    public Result delete(DeleteRentRequest deleteRentRequest) throws BusinessException {
        checkIfRentIdExist(deleteRentRequest.getRentId());
        this.rentDao.deleteById(deleteRentRequest.getRentId());

        return new SuccessResult("Kiralama başarıyla kaldırıldı.");
    }

    @Override
    public DataResult<List<RentListDto>> getAll() throws BusinessException {
        List<Rent> rents = this.rentDao.findAll();

        List<RentListDto> response= rents.stream()
                .map(rent -> this.modelMapperService.forDto()
                        .map(rent, RentListDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<RentListDto>>(response,"Veriler başarıyla listelendi.");
    }

    @Override
    public DataResult<GetRentDto> getByRentId(int id) throws BusinessException {
        checkIfRentIdExist(id);

        Rent rent = this.rentDao.getById(id);
        GetRentDto response = this.modelMapperService.forDto()
                .map(rent, GetRentDto.class);

        return new SuccessDataResult<GetRentDto>(response, "ID'ye göre listelendi");
    }

    @Override
    public DataResult<List<RentListDto>> getByCarId(int carId) throws BusinessException {
        List<Rent> rents = this.rentDao.getAllByCarId(carId);

        List<RentListDto> response = rents.stream()
                .map(rent -> this.modelMapperService.forDto()
                        .map(rent, RentListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<RentListDto>>(response, "Car id'sine göre listelendi.");
    }

    @Override
    public void checkIfCarIsRented(int carId) throws BusinessException {

        List<Rent> result= this.rentDao.getAllByCarId(carId);
        List<RentListDto> response= result.stream()
                .map(rent -> this.modelMapperService.forDto()
                        .map(rent, RentListDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public void checkIfRentIdExist(int id){
        if(!this.rentDao.existsById(id)){
            throw new BusinessException("Bu id'ye kayıtlı araba bulunamadı.");
        }
    }




}
