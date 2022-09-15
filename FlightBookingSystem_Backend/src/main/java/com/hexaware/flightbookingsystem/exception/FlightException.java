package com.hexaware.flightbookingsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class FlightException extends Exception{

	public FlightException() {
		super();

	}

	public FlightException(String message) {
		super(message);
	
	}
	
}
