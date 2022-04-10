package com.turkcell.rentacar.dataAccess.abstracts;

import com.turkcell.rentacar.entities.concretes.CarMaintenance;
import com.turkcell.rentacar.entities.concretes.OrderedAdditionalService;
import com.turkcell.rentacar.entities.concretes.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RentDao extends JpaRepository<Rent,Integer> {

    List<OrderedAdditionalService> getOrderedAdditionalServicesByRentId(int rentId);

    boolean existsByCar_CarId(int carId);

    List<Rent> getByCar_CarId(int carId);



    @Modifying
    @Query("update Rent r set r.endedKilometerInfo = ?2 where r.rentId = ?1")
    int updateEndedKilometerInfoToRentByRentId(int rentId, String endedKilometerInfo);

    @Modifying
    @Query("update Rent r set r.deliveryDate =?2 where r.rentId =?1")
    int updateDeliveryDateToRentByRentId(int rentId, LocalDate deliveryDate);


}
