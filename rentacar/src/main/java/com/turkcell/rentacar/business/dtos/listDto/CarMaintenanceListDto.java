package com.turkcell.rentacar.business.dtos.listDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarMaintenanceListDto {

    private int id;
    private String description;
    private LocalDate startDate;
    private LocalDate returnDate;
    private String carName;


}
