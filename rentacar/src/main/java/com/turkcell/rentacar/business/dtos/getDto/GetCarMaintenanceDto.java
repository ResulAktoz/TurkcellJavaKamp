package com.turkcell.rentacar.business.dtos.getDto;

import com.turkcell.rentacar.entities.concretes.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCarMaintenanceDto {

    private int id;
    private String description;
    private Date returnDate;
    private int carId;

}
