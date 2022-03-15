package com.turkcell.rentacar.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name =  "car_maintenances")
public class CarMaintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maintenance_id")
    private int id;
    @Column(name = "maintenance_description")
    private String description;

    @Column(name = "maintenance_startDate")
    private LocalDate startDate;

    @Column(name = "maintenance_returnDate")
    private LocalDate returnDate;

    @ManyToOne()
    @JoinColumn(name = "car_id")
    private Car car;



}
