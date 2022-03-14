package com.turkcell.rentacar.business.requests.create;

import com.turkcell.rentacar.entities.concretes.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentRequest {

    @NotNull
    private LocalDate rentDate;
    @NotNull
    private LocalDate rentReturnDate;
    @NotNull
    private int usageTime;
    @NotNull
    private boolean rentStatus;
    @NotNull
    private int carId;

}
