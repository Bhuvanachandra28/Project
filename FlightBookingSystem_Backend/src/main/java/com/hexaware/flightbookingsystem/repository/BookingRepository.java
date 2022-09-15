package com.hexaware.flightbookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.flightbookingsystem.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer>{

}
