package com.turkcell.rentacar.dataAccess.abstracts;

import com.turkcell.rentacar.entities.concretes.CarDamage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarDamageDao extends JpaRepository<CarDamage, Integer> {



}
