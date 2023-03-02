package com.egelirli.flightchallenge.restcontroller;

import org.springframework.web.bind.annotation.PostMapping;

import com.egelirli.flightchallenge.flightservice.FlightService;
import com.egelirli.rest.webservices.restfullwebservices.user.RequestBody;
import com.egelirli.rest.webservices.restfullwebservices.user.User;

import jakarta.validation.Valid;

public class FlightController {
	
	private FlightService fightService;
	
	
	
	public FlightController(FlightService fightService) {
		this.fightService = fightService;
		
	}

	@PostMapping("/flights")
	public void addFlight(@Valid @RequestBody Flight flight) {
		
	}
	

}
