package com.turkcell.rentacar.dataAccess.abstracts;

import com.turkcell.rentacar.entities.concretes.CarMaintenance;
import com.turkcell.rentacar.entities.concretes.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentDao extends JpaRepository<Rent,Integer> {
    //Rent getByCar_Id(int carId);
    List<Rent> getAllByCarCarId(int carId);

}
