package com.hexaware.flightbookingsystem.service;

import java.time.LocalDate;
import java.util.Collection;

import com.hexaware.flightbookingsystem.entity.Flight;
import com.hexaware.flightbookingsystem.exception.FlightException;

public interface FlightService {
	
	 int addFlight(Flight flight) throws FlightException;
	
	 Collection<Flight> fetchAll();
	
	 Flight fetchFlight(String source, String destination, LocalDate scheduleDate) 
			throws FlightException;
	 
	 Collection<Flight> fetchFlightsOnCondition(String source, String destination, LocalDate scheduleDate) 
				throws FlightException;
	
	 int updateFlight(Flight flight) throws FlightException; //seatnotavailableexception
	 
	 void removeFlight(int flightNumber);
	 
	 Flight fetchById(int fid);
}