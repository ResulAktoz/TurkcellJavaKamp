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
public class CreateInvoiceRequest {

    @NotNull
    @Min(1)
    private int userId;

    @NotNull
    @Size(min = 2)
    private String invoiceNumber;

    private LocalDate creationDate;

    @NotNull
    @Min(1)
    private int rentId;


}
