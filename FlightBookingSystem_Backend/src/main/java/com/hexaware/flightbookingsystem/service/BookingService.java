package com.hexaware.flightbookingsystem.service;

import java.util.List;

import com.hexaware.flightbookingsystem.entity.Booking;
import com.hexaware.flightbookingsystem.entity.Passenger;
import com.hexaware.flightbookingsystem.entity.Ticket;

public interface BookingService {
	
	int addBooking(Booking booking);
	
	int addPassenger(Passenger passenger, int bookingId);
	
	Ticket generateTicket(Ticket ticket, int userId, int bookingId);
	
	List<Ticket> getTicket(int uid);
	
	Booking getBookingById(int bid);
	
	void updateBooking(Booking bookPay);
	
}
