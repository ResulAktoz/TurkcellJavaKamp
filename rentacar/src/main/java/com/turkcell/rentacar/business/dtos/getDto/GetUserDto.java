package com.turkcell.rentacar.business.dtos.getDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserDto {
    private int userId;
    private String email;
    private String password;
    private List<GetRentDto> rents;
}
