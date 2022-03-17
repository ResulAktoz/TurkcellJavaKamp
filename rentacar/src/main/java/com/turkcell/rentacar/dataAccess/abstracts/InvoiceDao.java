package com.turkcell.rentacar.dataAccess.abstracts;

import com.turkcell.rentacar.entities.concretes.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.List;

public interface InvoiceDao extends JpaRepository<Invoice,Integer> {

    boolean findByUser_UserId(int userId);
    List<Invoice> getByUser_UserId(int userId);
    List<Invoice> findAllByCreationDateBetween(LocalDate startDate, LocalDate endDate);
    boolean existByRent_RentId(int rentId);

}
