package com.turkcell.rentacar.business.dtos.getDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRentDto {

    private int rentId;
    private LocalDate rentStartDate;
    private LocalDate rentReturnDate;
    private String rentedCityName;
    private String returnCityName;
    private String brandName;
    //private int carId;
    private int userId;
    private List<GetOrderedAdditionalServiceDto> orderedAdditionalServices;

}
