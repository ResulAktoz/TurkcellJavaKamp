package com.turkcell.rentacar.business.requests.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentRequest {

    private int rentId;
    @NotNull
    private LocalDate rentStartDate;

    @NotNull
    private LocalDate rentReturnDate;

    @NotNull
    private String startedKilometerInfo;

    @NotNull
    @Min(1)
    private int rentCityId;

    @NotNull
    @Min(1)
    private int returnCityId;


    @NotNull
    @Min(1)
    private int carId;



}
