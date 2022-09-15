package com.hexaware.flightbookingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.flightbookingsystem.entity.Ticket;
import com.hexaware.flightbookingsystem.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsernameAndPassword(String username,String password);
	
}
