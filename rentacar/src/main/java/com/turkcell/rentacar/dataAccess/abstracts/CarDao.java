package com.turkcell.rentacar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turkcell.rentacar.entities.concretes.Car;

public interface CarDao extends JpaRepository<Car, Integer> {
	
	 boolean existsCarById(Integer id);
	 List<Car> findByDailyPriceLessThan(double requestPrice);
	 List<Car> findByDailyPriceBetween (double maxValue, double minValue);
	

}
