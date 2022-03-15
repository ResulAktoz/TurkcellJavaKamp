package com.turkcell.rentacar.dataAccess.abstracts;

import com.turkcell.rentacar.entities.concretes.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao extends JpaRepository<Customer,Integer> {

}
