package com.egelirli.flightchallenge.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.egelirli.flightchallenge.exception.ResourceNotFoundException;
import com.egelirli.flightchallenge.flightservice.FlightReservationService;

@RestController
public class FlightReservationController {

	private FlightReservationService reservationService;
	
	public FlightReservationController(FlightReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	@GetMapping("/reserveseat/flight/{flightNumber}/seatId/{seatId}")
	public boolean reserveSeat(@PathVariable String flightNumber, 
							  @PathVariable int seatId) throws
											ResourceNotFoundException {
		
		return reservationService.reserveSeat(flightNumber, seatId);
	}

	@GetMapping("/reserveseat/flight/{flightNumber}/seatNumber/{seatNum}")
	public boolean reserveSeat(@PathVariable String flightNumber, 
							  @PathVariable String seatNum) throws
											ResourceNotFoundException {
		
		return reservationService.reserveSeat(flightNumber, seatNum);
	}
	
	
}
