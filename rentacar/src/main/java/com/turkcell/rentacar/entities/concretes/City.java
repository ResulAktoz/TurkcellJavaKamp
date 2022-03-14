package com.turkcell.rentacar.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cities")

public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private int id;

    @Column(name = "city_name")
    private String cityName;


    @OneToMany(mappedBy = "rentedCity")
    private List<Rent> rentedCity;

    @OneToMany(mappedBy = "returnCity")
    private List<Rent> returnCity;

}
