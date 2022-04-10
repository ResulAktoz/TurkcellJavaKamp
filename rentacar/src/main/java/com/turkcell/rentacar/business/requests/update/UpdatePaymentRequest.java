package com.turkcell.rentacar.business.requests.update;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NotNull
public class UpdatePaymentRequest {

    @NotNull
    @Min(1)
    private int paymentId;


    @NotNull
    @Min(1)
    private int rentId;
}
