package com.turkcell.rentacar.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ordered_additional_services")
public class OrderedAdditionalService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "ordered_additional_service_id")
    private int id;

    @Column(name = "ordered_additional_service_amount")
    private int orderedAdditionalServiceAmount;

    @ManyToOne
    @JoinColumn(name = "additional_service_id")
    private AdditionalService additionalService;

    @ManyToOne
    @JoinColumn(name = "rent_id")
    private Rent rent;

}
