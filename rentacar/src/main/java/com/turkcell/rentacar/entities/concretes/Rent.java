package com.turkcell.rentacar.entities.concretes;

import com.turkcell.rentacar.entities.abstracts.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
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

    @Column(name = "started_kilometer_info")
    private String startedKilometerInfo;

    @Column(name = "ended_kilometer_info")
    private String endedKilometerInfo;

    @ManyToOne()
    @JoinColumn(name = "car_id")
    private Car car;

    @OneToMany(mappedBy = "rent")
    private List<OrderedAdditionalService> orderedAdditionalServices;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "rent")
    private Invoice invoice;






}
