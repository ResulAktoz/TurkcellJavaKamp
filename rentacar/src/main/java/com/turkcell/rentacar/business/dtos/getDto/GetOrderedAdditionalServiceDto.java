package com.turkcell.rentacar.business.dtos.getDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderedAdditionalServiceDto {

    private int id;
    private int orderedAdditionalServiceAmount;
    private String additionalServiceName;
    private double additionalServiceDailyPrice;
    private int rentId;
}
