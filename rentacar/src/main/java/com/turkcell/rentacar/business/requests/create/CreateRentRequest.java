package com.turkcell.rentacar.business.requests.create;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentRequest {


    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate rentStartDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate rentReturnDate;

    @NotNull
    @Min(1)
    private int rentCityId;

    @NotNull
    @Min(1)
    private int returnCityId;

    @NotNull
    @Min(1)
    private int carId;

    @NotNull
    @Min(1)
    private int userId;

}
