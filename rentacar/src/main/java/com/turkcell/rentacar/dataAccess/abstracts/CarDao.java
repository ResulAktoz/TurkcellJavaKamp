package com.turkcell.rentacar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turkcell.rentacar.entities.concretes.Car;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CarDao extends JpaRepository<Car, Integer> {
	
	 boolean existsCarByCarId(Integer id);
	 List<Car> findByDailyPriceLessThan(double requestPrice);
	 List<Car> findByDailyPriceBetween (double maxValue, double minValue);

	 @Modifying
	 @Query("update Car c set c.kilometerInfo =?2 where c.carId =?1")
	 int updateKilometerToCarByCarId(int carId, String kilometerInfo);

}
