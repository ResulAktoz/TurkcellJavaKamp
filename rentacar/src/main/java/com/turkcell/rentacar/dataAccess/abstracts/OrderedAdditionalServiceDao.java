package com.turkcell.rentacar.dataAccess.abstracts;

import com.turkcell.rentacar.entities.concretes.OrderedAdditionalService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderedAdditionalServiceDao extends JpaRepository<OrderedAdditionalService,Integer> {

    List<OrderedAdditionalService> findOrderedAdditionalServicesByRent_RentId(int rentId);

}
