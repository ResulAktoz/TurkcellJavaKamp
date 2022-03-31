package com.turkcell.rentacar.business.requests.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePosRequest {

    @NotNull
    private String creditCardNo;

    @NotNull
    private String cardHolder;

    @NotNull
    private String expirationDate;

    @NotNull
    private String cvv;
}
