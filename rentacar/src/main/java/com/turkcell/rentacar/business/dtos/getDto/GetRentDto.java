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
    private int rentCityId;
    private int returnCityId;
    private int carId;
    private int userId;
    private int invoiceId;
    private List<GetOrderedAdditionalServiceDto> orderedAdditionalServices;

}
