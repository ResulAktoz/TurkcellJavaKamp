package com.turkcell.rentacar.business.requests.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderedAdditionalServiceRequest {

    @NotNull
    @Min(1)
    private int orderedAdditionalServiceId;

    @NotNull
    @Min(1)
    private int orderedAdditionalServiceAmount;

    @NotNull
    @Min(1)
    private int rentId;

    @NotNull
    @Min(1)
    private int additionalServiceId;

}
