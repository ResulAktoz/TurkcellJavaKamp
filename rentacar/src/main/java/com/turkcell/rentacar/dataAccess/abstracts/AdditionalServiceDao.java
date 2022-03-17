package com.turkcell.rentacar.dataAccess.abstracts;

import com.turkcell.rentacar.entities.concretes.AdditionalService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionalServiceDao extends JpaRepository<AdditionalService,Integer> {
    boolean existsByAdditionalServiceName(String additionalServiceName);
    //boolean existByAdditionalServiceId(int id);

}
