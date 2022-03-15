package com.turkcell.rentacar.business.dtos.getDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetInvoiceDto {


    private int invoiceId;
    private String invoiceNumber;
    private LocalDate creationDay;
    private LocalDate rentStartDate;
    private LocalDate rentReturnDate;
    private int totalRentDay;
    private double totalPrice;

    private int userId;

    private int rentId;


}
