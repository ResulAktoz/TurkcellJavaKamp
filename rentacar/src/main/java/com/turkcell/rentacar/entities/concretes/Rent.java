package com.turkcell.rentacar.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rents")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rent_id")
    private int rentId;

    @Column(name = "rent_date")
    private LocalDate rentDate;

    @Column(name = "rent_return_date")
    private LocalDate rentReturnDate;

    @Column(name = "usage_time")
    private int usageTime;

    @Column(name = "rent_status")
    private boolean rentStatus;

    @ManyToOne()
    @JoinColumn(name = "car_id")
    private Car car;






}
