package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.getDto.GetRentDto;
import com.turkcell.rentacar.business.dtos.listDto.RentListDto;
import com.turkcell.rentacar.business.requests.create.CreateRentRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteRentRequest;
import com.turkcell.rentacar.business.requests.update.UpdateRentDeliveryDateRequest;
import com.turkcell.rentacar.business.requests.update.UpdateRentRequest;
import com.turkcell.rentacar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.entities.concretes.Rent;

import java.util.List;

public interface RentService {

    //Result add(CreateRentRequest createRentRequest) throws BusinessException;
    Result addForCorporateCustomer(CreateRentRequest createRentRequest) throws BusinessException;
    Result addForIndividualCustomer(CreateRentRequest createRentRequest) throws BusinessException;
    Result update(UpdateRentRequest updateRentRequest) throws BusinessException;
    Result delete(DeleteRentRequest deleteRentRequest) throws BusinessException;
    Result updateRentDeliveryDate(UpdateRentDeliveryDateRequest updateRentDeliveryDateRequest) throws BusinessException;

    DataResult<List<RentListDto>> getAll() throws BusinessException;
    DataResult<GetRentDto> getRentDetailsByRentId(int id) throws BusinessException;
    DataResult<List<RentListDto>> getByCarId(int carId) throws BusinessException;
    DataResult<Double> calculateDelayedDayPrice(int rentId);
    void checkIfCarIsRented(int carId) throws BusinessException;
    void checkIfRentIdExist(int id) throws BusinessException;
    double calculateRentPrice(int rentId);


}
