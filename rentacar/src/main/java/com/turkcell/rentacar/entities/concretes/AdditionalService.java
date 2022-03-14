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
@Table(name = "additional_services")
public class AdditionalService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "additional_service_id")
    private int id;

    @Column(name = "additional_service_name")
    private String additionalServiceName;

    @Column(name =  "additional_service_daily_price")
    private double dailyPrice;

    @OneToMany(mappedBy = "additionalService")
    private List<OrderedAdditionalService> orderedAdditionalServices;

}
