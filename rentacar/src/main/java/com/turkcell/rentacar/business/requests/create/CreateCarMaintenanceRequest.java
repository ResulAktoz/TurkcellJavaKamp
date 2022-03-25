package com.turkcell.rentacar.business.requests.create;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarMaintenanceRequest {

    @NotNull
    private String description;

    private Date returnDate;

    @NotNull
    private int carId;



}
