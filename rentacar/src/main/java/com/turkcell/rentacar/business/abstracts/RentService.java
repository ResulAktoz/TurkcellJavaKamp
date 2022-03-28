package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.getDto.GetRentDto;
import com.turkcell.rentacar.business.dtos.listDto.RentListDto;
import com.turkcell.rentacar.business.requests.create.CreateRentRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteRentRequest;
import com.turkcell.rentacar.business.requests.update.UpdateEndedKilometerInfoRequest;
import com.turkcell.rentacar.business.requests.update.UpdateRentDeliveryDateRequest;
import com.turkcell.rentacar.business.requests.update.UpdateRentRequest;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

import java.time.LocalDate;
import java.util.List;

public interface RentService {


    Result addForCorporateCustomer(CreateRentRequest createRentRequest);
    Result addForIndividualCustomer(CreateRentRequest createRentRequest);
    Result update(UpdateRentRequest updateRentRequest);
    Result delete(DeleteRentRequest deleteRentRequest);
    Result updateEndedKilometer(UpdateEndedKilometerInfoRequest updateEndedKilometerInfoRequest);
    Result updateRentDeliveryDate(UpdateRentDeliveryDateRequest updateRentDeliveryDateRequest);
    Result checkIfReturnCityIsDifferentFromRentedCity(int rentId);

    DataResult<List<RentListDto>> getAll();
    DataResult<GetRentDto> getById(int rentId);
    DataResult<Double> calculateRentPrice(int rentId);;
    DataResult<Double> calculateDelayedDayPrice(int rentId);
    Result checkIfCarIsRented(int carId);






}
