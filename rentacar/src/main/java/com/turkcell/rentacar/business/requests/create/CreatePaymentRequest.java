package com.turkcell.rentacar.business.requests.create;

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
public class CreatePaymentRequest {

    @NotNull
    @Size(min = 2)
    private String creditCardNo;

    @NotNull
    @Size(min = 2)
    private String cardHolder;

    @NotNull
    private LocalDate expirationDate;

    @NotNull
    @Size(min = 3)
    private String cvv;

    @NotNull
    @Min(1)
    private int rentId;
}
