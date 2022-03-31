package com.turkcell.rentacar.business.dtos.listDto;

import com.turkcell.rentacar.business.dtos.getDto.GetOrderedAdditionalServiceDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentListDto {

    private int rentId;
    private LocalDate rentStartDate;
    private LocalDate rentReturnDate;
    private int rentedCityId;
    private int returnCityId;
    private int userId;

}
