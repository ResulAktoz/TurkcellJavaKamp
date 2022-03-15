package com.turkcell.rentacar.entities.concretes;

import com.turkcell.rentacar.entities.abstracts.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "incvoices")

public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private int invoiceId;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    @Column(name = "creation_day")
    private LocalDate creationDay;

    @Column(name = "rent_start_day")
    private LocalDate rentStartDate;

    @Column(name = "rent_return_date")
    private LocalDate rentReturnDate;

    @Column(name = "total_rent_day")
    private int totalRentDay;

    @Column(name = "total_price")
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "rent_id")
    private Rent rent;
}
