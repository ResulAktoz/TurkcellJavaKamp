package com.turkcell.rentacar.business.dtos.getDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCorporateCustomerDto {

    private int userId;
    private String email;
    private String password;
    private String companyName;
    private String taxNumber;
    private List<GetRentDto> rents;
}
