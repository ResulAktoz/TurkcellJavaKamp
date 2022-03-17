package com.turkcell.rentacar.dataAccess.abstracts;

import com.turkcell.rentacar.entities.concretes.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.support.SimpleTriggerContext;

public interface CityDao extends JpaRepository<City,Integer> {
    //boolean existByCity_CityName(String cityName);
    boolean existsCityByCityName(String cityName);

}
