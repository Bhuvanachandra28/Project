package com.hexaware.flightbookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.flightbookingsystem.entity.Ticket;
import com.hexaware.flightbookingsystem.entity.User;

import java.util.List;


public interface TicketRepository extends JpaRepository<Ticket, Integer> {

	List<Ticket> findByUser(User user);
}
