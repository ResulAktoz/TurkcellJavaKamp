package com.turkcell.rentacar.business.requests.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderedAdditionalServiceRequest {

    @NotNull
    @Min(1)
    private int additionalServiceId;

    @NotNull
    @Min(1)
    private int orderedAdditionalServiceAmount;

    @NotNull
    @Min(1)
    private int rentId;


}
