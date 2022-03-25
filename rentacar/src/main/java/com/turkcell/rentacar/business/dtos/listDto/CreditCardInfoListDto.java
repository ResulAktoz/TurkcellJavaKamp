package com.turkcell.rentacar.business.dtos.listDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardInfoListDto {

    private String creditCarNo;

    private String cardHolder;

    private LocalDate expirationDate;

    private String cvv;


}
