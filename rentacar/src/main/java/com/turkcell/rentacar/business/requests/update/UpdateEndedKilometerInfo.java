package com.turkcell.rentacar.business.requests.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEndedKilometerInfo {

    @NotNull
    @Min(1)
    private int rentId;

    @NotNull
    @Size(min = 2)
    private String endedKilometerInfo;

}