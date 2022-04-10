package com.turkcell.rentacar.business.requests.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCreditCardInfoRequest {

    @NotNull
    @Size(min = 16)
    private String creditCardNo;

    @NotNull
    @Size(min = 4)
    private String cardHolder;

    @NotNull
    @Min(1)
    @Max(12)
    private int expirationMonth;

    @NotNull
    @Min(2022)
    @Max(2029)
    private int expirationYear;

    @NotNull
    @Size(min = 3)
    private String cvv;
}
