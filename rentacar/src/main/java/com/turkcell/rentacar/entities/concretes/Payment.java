package com.turkcell.rentacar.entities.concretes;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymnet_id")
    private int paymentId;

    @Column(name = "credit_card_no")
    private String creditCarNo;

    @Column(name = "card_holder")
    private String cardHolder;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "cvv")
    private String cvv;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "rent_id")
    private Rent rent;

    @OneToMany(mappedBy = "payment")
    private List<OrderedAdditionalService> orderedAdditionalServices;

    @OneToMany(mappedBy = "payment")
    private List<Invoice> invoices;
}
