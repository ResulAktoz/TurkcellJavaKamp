package com.turkcell.rentacar.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "user_id")
@Table(name = "individual_customers")
public class IndividualCustomer extends Customer{

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "national_identity", length = 11)
    private String nationalIdentity;
}
