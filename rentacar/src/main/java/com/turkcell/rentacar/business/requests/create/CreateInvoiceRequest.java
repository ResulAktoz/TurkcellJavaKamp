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
public class CreateInvoiceRequest {

    @NotNull
    @Min(1)
    private int userId;

    @NotNull
    @Min(1)
    private int rentId;
}