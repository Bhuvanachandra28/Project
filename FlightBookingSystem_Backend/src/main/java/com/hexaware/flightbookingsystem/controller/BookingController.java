package com.hexaware.flightbookingsystem.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.flightbookingsystem.bean.ListPassenger;
import com.hexaware.flightbookingsystem.entity.Booking;
import com.hexaware.flightbookingsystem.entity.Flight;
import com.hexaware.flightbookingsystem.entity.Passenger;
import com.hexaware.flightbookingsystem.entity.Ticket;
import com.hexaware.flightbookingsystem.entity.User;
import com.hexaware.flightbookingsystem.exception.FlightException;
import com.hexaware.flightbookingsystem.service.BookingService;
import com.hexaware.flightbookingsystem.service.FlightService;



@CrossOrigin()
@RestController
@RequestMapping("/book")
public class BookingController {

	@Autowired
	private BookingService bookservice;
	
	@Autowired
	private FlightService flightservice;
	

	@PostMapping(value = "/booking", consumes = "application/json")
	public String addBooking(@RequestBody Booking booking, @RequestParam int fid, String source, String destination, String date) throws FlightException {
		

		
		Flight flight = flightservice.fetchById(fid);
		if(flight.getAvailableSeats()<=0) {
			return "Seats are not available";
		}else if(booking.getNumberOfSeatsToBook()>flight.getAvailableSeats()) {
			return "Only "+flight.getAvailableSeats()+" are Available";
		}else {
			flight.setAvailableSeats(flight.getAvailableSeats()-booking.getNumberOfSeatsToBook());
			flightservice.updateFlight(flight);
			booking.setFlight(flight);
			booking.setBookingDate(LocalDate.now());
			int bid = bookservice.addBooking(booking);
			return "" + bid;
		}
	}
	
	
	@PostMapping(value = "/passenger/{bid}",  consumes = "application/json")
	public String addPassengers(@RequestBody ListPassenger pass1, @PathVariable int bid) {

		String s1 = "";
		Booking booking=bookservice.getBookingById(bid);
		for (int i = 0; i<booking.getNumberOfSeatsToBook(); i++) {
			s1 += " : "+bookservice.addPassenger(pass1.getPass1().get(i), bid) ; 
		}
		
		return "Passengers added with id's " + s1 ;
	}
	

	
	@PostMapping(value = "/ticket/{userId}/{bookid}/{pay}", consumes = "application/json",produces = "application/json")
	public ResponseEntity<?> createBookingTicket(@RequestBody Ticket ticket, @PathVariable int userId, @PathVariable int bookid, @PathVariable int pay ) {
		

		Booking booking=bookservice.getBookingById(bookid);
		booking.setPayStatus(pay);
		bookservice.updateBooking(booking);
		
		int pay_status = booking.getPayStatus();
		double total_pay=booking.getFlight().getPrice()*booking.getNumberOfSeatsToBook();
		if(pay_status==1) {
			LocalDate date = LocalDate.now();
			ticket.setBooking_date(date);
			ticket.setTotal_pay(total_pay); 
			Ticket ticket1 = bookservice.generateTicket(ticket, userId, bookid);
			
			return new ResponseEntity<Ticket>(ticket1, HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Payment failed, please book ticket again.",HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping(value="/getTickets/{uid}" ,produces = "application/json")
	public List<Ticket> getAllTickets(@PathVariable int uid) {
		bookservice.getTicket(uid);
		return bookservice.getTicket(uid);
		
	}
	

}
