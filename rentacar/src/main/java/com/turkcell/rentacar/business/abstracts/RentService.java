package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.getDto.GetRentDto;
import com.turkcell.rentacar.business.dtos.listDto.RentListDto;
import com.turkcell.rentacar.business.requests.create.CreateRentRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteRentRequest;
import com.turkcell.rentacar.business.requests.update.UpdateRentRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.results.DataResult;
import com.turkcell.rentacar.core.results.Result;
import org.hibernate.sql.Update;

import java.util.List;

public interface RentService {

    Result add(CreateRentRequest createRentRequest) throws BusinessException;
    Result update(UpdateRentRequest updateRentRequest) throws BusinessException;
    Result delete(DeleteRentRequest deleteRentRequest) throws BusinessException;

    DataResult<List<RentListDto>> getAll() throws BusinessException;
    DataResult<GetRentDto> getByRentId(int id) throws BusinessException;
    DataResult<List<RentListDto>> getByCarId(int carId) throws BusinessException;

    void checkIfCarIsRented(int carId) throws BusinessException;
    void checkIfRentIdExist(int id) throws BusinessException;
}
