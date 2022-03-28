package com.turkcell.rentacar.business.dtos.listDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceListDto {
    private int invoiceId;
    private int userId;
    private String invoiceNumber;
    private LocalDate creationDate;
    private double totalPrice;
    private int rentId;
}
