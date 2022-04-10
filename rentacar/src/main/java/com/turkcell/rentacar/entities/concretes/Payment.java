package com.turkcell.rentacar.entities.concretes;


import com.turkcell.rentacar.entities.abstracts.User;
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

    @Column(name = "total_payment")
    private double totalPayment;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "rent_id")
    private Rent rent;


    @OneToMany(mappedBy = "payment")
    private List<Invoice> invoices;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User User;
}
