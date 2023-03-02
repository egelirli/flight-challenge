package com.egelirli.flightchallenge.restcontroller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.egelirli.flightchallenge.entity.Flight;
import com.egelirli.flightchallenge.exception.ResourceNotFoundException;
import com.egelirli.flightchallenge.flightservice.FlightService;

@RestController
public class FlightController {
	
	private FlightService fightService;
	
	
	
	public FlightController(FlightService fightService) {
		this.fightService = fightService;
		
	}

	@GetMapping("/flights")
	List<Flight> getAllFlights() 
			throws ResourceNotFoundException {
	    
		return fightService.getAllFlights();
	 }
	
	
	@PostMapping("/flights")
	public boolean addFlight(@RequestBody Flight newFlight) {
		return fightService.add(newFlight);
	}
	
	@DeleteMapping("/flights/flightNumber")
	 public boolean deleteFlight(@PathVariable String flightNumber) {
		return fightService.remove(flightNumber);
	 }
	
	@GetMapping("/flights/{flightNumber}")
	Flight findFlight(@PathVariable String flightNumber) 
			throws ResourceNotFoundException {
	    
		return fightService.findFlightByNumber(flightNumber);
	 }
	
}
