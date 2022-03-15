package com.turkcell.rentacar.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "corporate_customers")
public class CorporateCustomer extends Customer{

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "tax_number")
    private String taxNumber;

}
