package com.turkcell.rentacar.dataAccess.abstracts;

import com.turkcell.rentacar.entities.concretes.CarMaintenance;
import org.springframework.aop.IntroductionInterceptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository

public interface CarMaintenanceDao extends JpaRepository<CarMaintenance,Integer> {
    boolean existsCarMaintenanceById(Integer id);
    List<CarMaintenance> getByCar_CarId(int id);
    List<CarMaintenance> findCarMaintenanceByReturnDateIsNull();


}
