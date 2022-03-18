package com.turkcell.rentacar.business.requests.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarDamageRequest {

    @NotNull
    @Min(1)
    private int carDamageId;

    @NotNull
    @Size(min = 2)
    private String damageInfo;

    @NotNull
    @Min(1)
    private int carId;
}
