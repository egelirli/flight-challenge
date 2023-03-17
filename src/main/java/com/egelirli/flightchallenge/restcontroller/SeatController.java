package com.egelirli.flightchallenge.restcontroller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.egelirli.flightchallenge.entity.Flight;
import com.egelirli.flightchallenge.entity.FlightSeat;
import com.egelirli.flightchallenge.exception.ResourceNotFoundException;
import com.egelirli.flightchallenge.flightservice.SeatService;

@RestController
public class SeatController {
	
	private SeatService seatService;
	
	public SeatController(SeatService seatService) {
		
		this.seatService = seatService;
	}
	
	
	@PostMapping("/seats/{flightNumber}")
	public FlightSeat addSeat(@RequestBody String seatNumber, 
						   @PathVariable String flightNumber) {
		return seatService.addSeat(flightNumber, seatNumber);
	}

	
	@GetMapping("/seats/available/{flightNumber}")
	List<FlightSeat> getAllAvailableSeats(@PathVariable String flightNumber) 
			throws ResourceNotFoundException {
	    
		return seatService.getAvailableSeats(flightNumber);
	 }

	@GetMapping("/seats/all/{flightNumber}")
	List<FlightSeat> getAllSeats(@PathVariable String flightNumber) 
			throws ResourceNotFoundException {
	    
		return seatService.getAllSeats(flightNumber);
	 }
	
	
	@DeleteMapping("/seats/{id}")
	 public void deleteSeat(@PathVariable int id) {
		 seatService.removeSeat(id);
	 }
	
	@DeleteMapping("/seats/deleteall/{flightNumber}")
	 public void deleteAllSeatsOfFlight(@PathVariable String flightNumber) 
			 								throws ResourceNotFoundException {
		 seatService.deleteAllSeatsOfFlight(flightNumber);
	 }
	

}
