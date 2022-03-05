package com.turkcell.rentacar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentListDto {

    @NotNull
    private LocalDate rentDate;
    @NotNull
    private LocalDate rentReturnDate;
    @NotNull
    private int usageTime;
    @NotNull
    private boolean rentStatus;
    @NotNull
    private String brandName;
}
