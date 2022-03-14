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
public class CreateAdditionalServiceRequest {

    @NotNull
    @Size(min = 2)
    private String additionalServiceName;

    @NotNull
    @Min(1)
    private double dailyPrice;

}
