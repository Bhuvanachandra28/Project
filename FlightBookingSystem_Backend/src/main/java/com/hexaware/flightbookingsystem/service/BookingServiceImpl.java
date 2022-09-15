package com.hexaware.flightbookingsystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.flightbookingsystem.entity.Booking;
import com.hexaware.flightbookingsystem.entity.Passenger;
import com.hexaware.flightbookingsystem.entity.Ticket;
import com.hexaware.flightbookingsystem.entity.User;
import com.hexaware.flightbookingsystem.repository.BookingRepository;
import com.hexaware.flightbookingsystem.repository.PassengerRepository;
import com.hexaware.flightbookingsystem.repository.TicketRepository;
import com.hexaware.flightbookingsystem.repository.UserRepository;



@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private UserRepository urepo;
	
	@Autowired
	private BookingRepository brepo;
	
	@Autowired
	private PassengerRepository prepo;
	
	@Autowired
	private TicketRepository trepo;
	
	
	
	@Override
	public int addBooking(Booking booking) {
		brepo.save(booking);
		return booking.getBookingId();
	}

	@Override
	public int addPassenger(Passenger passenger, int bookingId) {
		Booking booking = brepo.findById(bookingId).get();
		booking.getPassengers().add(passenger);
		passenger.setBooking(booking);
		prepo.save(passenger);
		return passenger.getPid();
	}

	@Override
	public Ticket generateTicket(Ticket ticket, int userId, int bookingId) {
		Booking booking = brepo.findById(bookingId).get();
		User user = urepo.findById(userId).get();
		ticket.setBooking(booking);
		ticket.setUser(user);
	
		trepo.save(ticket);
		return ticket;
	}
	
	
	@Override
	public void updateBooking(Booking bookPay) {
		brepo.save(bookPay);
	}

	
	@Override
	public List<Ticket> getTicket(int uid) {
		User user=urepo.findById(uid).get();
		List<Ticket> tlist=trepo.findByUser(user);
		return tlist;
	}

	
	@Override
	public Booking getBookingById(int bid) {
		
		return brepo.findById(bid).get();
	}

}