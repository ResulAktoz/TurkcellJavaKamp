package com.turkcell.rentacar.business.dtos.listDto;

import com.turkcell.rentacar.business.dtos.getDto.GetRentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorporateCustomerListDto {

    private int userId;
    private String email;
    private String password;
    private String companyName;
    private String taxNumber;
    private List<GetRentDto> rents;
}
