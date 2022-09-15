package com.hexaware.flightbookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.flightbookingsystem.entity.Passenger;


public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

}
