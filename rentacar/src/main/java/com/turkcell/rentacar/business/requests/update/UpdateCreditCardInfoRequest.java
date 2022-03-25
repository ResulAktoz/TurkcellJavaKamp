package com.turkcell.rentacar.business.requests.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCreditCardInfoRequest {

    @NotNull
    @Min(1)
    private int creditCardInfoId;

    @NotNull
    @Size(min = 16)
    private String creditCarNo;

    @NotNull
    @Size(min = 4)
    private String cardHolder;

    @NotNull
    private LocalDate expirationDate;

    @NotNull
    @Size(min = 3)
    private String cvv;
}
