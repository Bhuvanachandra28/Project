package com.hexaware.flightbookingsystem.controller;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.flightbookingsystem.entity.Flight;
import com.hexaware.flightbookingsystem.entity.User;
import com.hexaware.flightbookingsystem.exception.FlightException;
import com.hexaware.flightbookingsystem.service.FlightService;


@CrossOrigin()
@EnableTransactionManagement
@RestController
@RequestMapping("/flight")
public class FlightController {

	@Autowired
	FlightService fservice;
	


	@PostMapping(value = "/add",consumes = "application/json")
	public String addFlight(@RequestBody Flight flight, HttpSession session) {
		try {		
				int id=fservice.addFlight(flight);
				return "Flight added with flight number "+id;
			
		} catch (FlightException e) {
			e.printStackTrace();
			return ""+e.getMessage();
		}
		
	}
	

	@GetMapping(value="/fetchall",produces="application/json")
	public Collection<Flight> serachFlights(){
		return fservice.fetchAll();	
	}
	

	@GetMapping(value="/fetch",produces="application/json")  
	public ResponseEntity<?> searchFlight(@RequestParam String source,@RequestParam String destination
			,@RequestParam String date) {
		try {
			
			LocalDate dt=LocalDate.parse(date);
			Collection<Flight> flights = fservice.fetchFlightsOnCondition(source, destination, dt);
			return new ResponseEntity< Collection<Flight> >(flights,HttpStatus.OK);
			
		} catch (FlightException e) {
			
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	


	@DeleteMapping(value="/remove/{fid}")
	public String removeFlight(@PathVariable int fid, HttpSession session) {

			fservice.removeFlight(fid);
			return "flight removed with id" + fid;

		
	}
	

	@PutMapping(value="/update",produces="application/json")
	public String updateFlight(@RequestBody Flight flight, HttpSession session) {
		try {

				int id=fservice.updateFlight(flight);
				return "Flight updated with id "+id;
			
		} catch (FlightException e) {
			
			e.printStackTrace();
			return ""+e.getMessage();
		}
	}
}