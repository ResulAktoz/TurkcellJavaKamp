package com.turkcell.rentacar.dataAccess.abstracts;

import com.turkcell.rentacar.entities.concretes.CorporateCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorporateCustomerDao extends JpaRepository<CorporateCustomer,Integer> {



}
