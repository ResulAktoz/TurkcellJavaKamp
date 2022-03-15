package com.turkcell.rentacar.dataAccess.abstracts;

import com.turkcell.rentacar.entities.abstracts.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {

}
