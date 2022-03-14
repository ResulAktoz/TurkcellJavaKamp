package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.listDto.CarMaintenanceListDto;
import com.turkcell.rentacar.business.dtos.getDto.GetCarMaintenanceDto;
import com.turkcell.rentacar.business.requests.create.CreateCarMaintenanceRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteCarMaintenanceRequest;
import com.turkcell.rentacar.business.requests.update.UpdateCarMaintenanceRequest;
import com.turkcell.rentacar.core.results.DataResult;
import com.turkcell.rentacar.core.results.Result;

import java.util.List;
public interface CarMaintenanceService {
    DataResult<List<CarMaintenanceListDto>> getAll();
    Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest);
    DataResult<GetCarMaintenanceDto> getById(Integer id);
    Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest);
    Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest);
    DataResult<List<CarMaintenanceListDto>> getListedCars();
    DataResult<List<CarMaintenanceListDto>> getByCarId(int id);
    Result checkIfCarIsAlreadyInMaintenance(int id);

}
