package com.turkcell.rentacar.dataAccess.abstracts;

import com.turkcell.rentacar.entities.concretes.CarMaintenance;
import com.turkcell.rentacar.entities.concretes.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentDao extends JpaRepository<Rent,Integer> {
    Rent getByCar_Id(int carId);

}
