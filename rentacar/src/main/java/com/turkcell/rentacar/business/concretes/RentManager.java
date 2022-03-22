package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.CarMaintenanceService;
import com.turkcell.rentacar.business.abstracts.RentService;
import com.turkcell.rentacar.business.dtos.getDto.GetRentDto;
import com.turkcell.rentacar.business.dtos.listDto.RentListDto;
import com.turkcell.rentacar.business.requests.create.CreateRentRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteRentRequest;
import com.turkcell.rentacar.business.requests.update.UpdateRentRequest;
import com.turkcell.rentacar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.RentDao;
import com.turkcell.rentacar.entities.concretes.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentManager implements RentService {

    private RentDao rentDao;
    private ModelMapperService modelMapperService;
    private CarMaintenanceService carMaintenanceService;

    @Autowired
    public RentManager(RentDao rentDao, ModelMapperService modelMapperService,
                       @Lazy CarMaintenanceService carMaintenanceService) {
        this.rentDao = rentDao;
        this.modelMapperService = modelMapperService;
        this.carMaintenanceService = carMaintenanceService;
    }

    /*
    public Result add(CreateRentRequest createRentRequest) throws BusinessException {
        checkIfCarIsAlreadyInMaintenance(createRentRequest.getCarId());
        checkIfCarIsRented(createRentRequest.getCarId());

        Rent rent = this.modelMapperService.forRequest()
                .map(createRentRequest, Rent.class);
        this.rentDao.save(rent);

        return new SuccessResult("Kiralama eklendi");

    Genel olur mu ??
    }
     */

    @Override
    public Result addForCorporateCustomer(CreateRentRequest createRentRequest) throws BusinessException {
        checkIfCarIsAlreadyInMaintenance(createRentRequest.getCarId());
        checkIfCarIsRented(createRentRequest.getCarId());

        Rent rent = this.modelMapperService.forRequest()
                .map(createRentRequest, Rent.class);
        this.rentDao.save(rent);

        return new SuccessResult("Kurumsal müşteri için kiralama eklendi");

    }

    @Override
    public Result addForIndividualCustomer(CreateRentRequest createRentRequest) throws BusinessException {
        checkIfCarIsAlreadyInMaintenance(createRentRequest.getCarId());
        checkIfCarIsRented(createRentRequest.getCarId());

        Rent rent = this.modelMapperService.forRequest()
                .map(createRentRequest, Rent.class);
        this.rentDao.save(rent);

        return new SuccessResult("Bireysel müşteri için kiralama eklendi");
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
    public DataResult<GetRentDto> getRentDetailsByRentId(int id) throws BusinessException {
        checkIfRentIdExist(id);

        Rent rent = this.rentDao.getById(id);
        GetRentDto response = this.modelMapperService.forDto()
                .map(rent, GetRentDto.class);

        return new SuccessDataResult<GetRentDto>(response, "ID'ye göre listelendi");
    }

    @Override
    public DataResult<List<RentListDto>> getByCarId(int carId) throws BusinessException {
        List<Rent> rents = this.rentDao.getAllByCarCarId(carId);

        List<RentListDto> response = rents.stream()
                .map(rent -> this.modelMapperService.forDto()
                        .map(rent, RentListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<RentListDto>>(response, "Car id'sine göre listelendi.");
    }

    @Override
    public void checkIfCarIsRented(int carId) throws BusinessException {

        List<Rent> result= this.rentDao.getAllByCarCarId(carId);
        List<RentListDto> response= result.stream()
                .map(rent -> this.modelMapperService.forDto()
                        .map(rent, RentListDto.class))
                .collect(Collectors.toList());

        for (RentListDto rent: response) {
            if(rent.getRentReturnDate()==null ||
                    LocalDate.now().isBefore(rent.getRentReturnDate()) ||
                    LocalDate.now().isEqual(rent.getRentReturnDate())){
                throw new BusinessException("Araç şu anda kirada.");
            }
        }
    }

    @Override
    public void checkIfRentIdExist(int id){
        if(!this.rentDao.existsById(id)){
            throw new BusinessException("Bu id'ye kayıtlı araba bulunamadı.");
        }
    }

    @Override
    public double calculateRentPrice(int rentId) {
        double differentCityPrice= 0;
        if(!(this.rentDao.getById(rentId).getRentedCity().equals(this.rentDao.getById(rentId).getReturnCity()))){
            differentCityPrice = 750;
        }
        long daysBetween = (ChronoUnit.DAYS.between(
                this.rentDao.getById(rentId).getRentStartDate(), this.rentDao.getById(rentId).getRentReturnDate())+1);
        double dailyPrice = this.rentDao.getById(rentId).getCar().getDailyPrice();
        double totalRentPrice = (daysBetween * dailyPrice) +differentCityPrice;
        return totalRentPrice;
    }

    private void checkIfCarIsAlreadyInMaintenance(int id) throws BusinessException{
       if(!this.carMaintenanceService.checkIfCarMaintenanceIdExist(id)){
           throw new BusinessException("Araba bakımda olduğundan kiralanamaz.");

       }
    }




}
