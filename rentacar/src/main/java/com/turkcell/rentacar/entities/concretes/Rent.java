package com.turkcell.rentacar.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.boot.jaxb.hbm.spi.JaxbHbmCollectionIdType;

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

    @Column(name = "start_date")
    private LocalDate rentStartDate;

    @Column(name = "return_date")
    private LocalDate rentReturnDate;

    @ManyToOne
    @JoinColumn(name = "rented_city_id")
    private City rentedCity;

    @ManyToOne
    @JoinColumn(name = "return_city_id")
    private City returnCity;

    @ManyToOne()
    @JoinColumn(name = "car_id")
    private Car car;






}
