package com.turkcell.rentacar.business.dtos.listDto;

import com.turkcell.rentacar.business.dtos.getDto.GetRentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerListDto {
    private int userId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String nationalIdentity;
    private List<GetRentDto> rents;
}
