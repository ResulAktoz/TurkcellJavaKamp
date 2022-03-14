package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.CarMaintenanceService;
import com.turkcell.rentacar.business.abstracts.RentService;
import com.turkcell.rentacar.business.dtos.listDto.RentListDto;
import com.turkcell.rentacar.business.requests.create.CreateRentRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.mapping.ModelMapperService;
import com.turkcell.rentacar.core.results.*;
import com.turkcell.rentacar.dataAccess.abstracts.RentDao;
import com.turkcell.rentacar.entities.concretes.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public DataResult<List<RentListDto>> getAll() {




        return null;
    }

    @Override
    public Result add(CreateRentRequest createRentRequest) {
        checkIfCarIsAlreadyInMaintenance(createRentRequest.getCarId());
        Rent rent = this.modelMapperService.forRequest().map(createRentRequest, Rent.class);
        this.rentDao.save(rent);
        return new SuccessResult("Araba kiralanabilir.");

    }

    @Override
    public DataResult<RentListDto> getByCarId(int carId) {
        Rent rent = this.rentDao.getByCar_Id(carId);
        RentListDto response = this.modelMapperService.forDto().map(rent,RentListDto.class);
       return new SuccessDataResult(response);
    }

    private void checkIfCarIsAlreadyInMaintenance(int id){
        if(checkIfCarExist(id)==false) {
            if (!this.carMaintenanceService.checkIfCarIsAlreadyInMaintenance(id).isSuccess()) {
                throw new BusinessException("Araba bakımda olduğundan kiralanamaz.");
            }
        }

    }

    private boolean checkIfCarExist(int id){
        if(this.carMaintenanceService.getByCarId(id).getData().isEmpty()){
            return true;
        }
        return false;
    }


}
