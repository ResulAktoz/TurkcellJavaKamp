package com.turkcell.rentacar.business.requests.create;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class CreateCreditCardInfoRequest {

    @NotNull
    @Size(min = 16)
    private String creditCardNo;

    @NotNull
    @Size(min = 4)
    private String cardHolder;

    @NotNull
    private LocalDate expirationDate;

   /* private int expirationMonth;

    private int expirationYear; */

    @NotNull
    @Size(min = 3)
    private String cvv;
}
