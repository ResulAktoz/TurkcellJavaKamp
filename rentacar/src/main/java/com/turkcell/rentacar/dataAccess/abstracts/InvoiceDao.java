package com.turkcell.rentacar.dataAccess.abstracts;

import com.turkcell.rentacar.entities.concretes.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface InvoiceDao extends JpaRepository<Invoice,Integer> {

    boolean findByUser_UserId(int userId);
    List<Invoice> getByUser_UserId(int userId);
    List<Invoice> findAllByCreationDateBetween(LocalDate startDate, LocalDate endDate);
    boolean existsByRent_RentId(int rentId);
    List<Invoice> getByRent_RentId(int rentId);

}
