package com.egelirli.flightchallenge.flight;

import java.math.BigDecimal;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.egelirli.flightchallenge.entity.Flight;
import com.egelirli.flightchallenge.entity.FlightSeat;
import com.egelirli.flightchallenge.repository.FlightRepository;
import com.egelirli.flightchallenge.repository.FlightSeatRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class SeatService {
	private Logger logger = LoggerFactory.getLogger(SeatService.class);

	private FlightSeatRepository seatRepo;
	private FlightRepository flightRepo;
	
	public SeatService(FlightSeatRepository seatRepo,
					   FlightRepository flightRepo) {
		
		this.seatRepo = seatRepo;
		this.flightRepo = flightRepo;
	}
	
	public boolean addSeat(String flightNumber, 
						   String seatNumber,
						   BigDecimal price) {
		logger.debug("In addSeat -  flightNumber: {} seatNumber: {}"
				 , flightNumber, seatNumber);
		

		Optional<FlightSeat> st = seatRepo.findById(seatNumber);
		if(!st.isEmpty()) {
				logger.error("In addSeat - seat({}) already exists !", seatNumber);
				return false;
		 }

		Optional<Flight> fl = flightRepo.findById(flightNumber);
		if(fl.isEmpty()) {
				logger.error("In addSeat - flight({}) not exists !", flightNumber);
				return false;
		 }
			
		FlightSeat seat = new FlightSeat();
		seat.setFlightNumber(flightNumber);
		seat.setSeatNumber(seatNumber);
		seat.setPrice(price);
		seat.setAvailable(true);
		seatRepo.save(seat);
		
		logger.debug("In addSeat - added new seat flightNumber: {} "
				+ "seatNumber: {}", flightNumber, seatNumber);
		
		return true;
		
		
	}
	
	public boolean removeSeat(String flightNumber, 
							  String seatNumber) {
		
		logger.debug("In removeSeat -  flightNumber: {} seatNumber: {}"
				 , flightNumber, seatNumber);
		
		seatRepo.findAllById(null)
		seatRepo.delete(seat);
	
	}
	
	public  boolean updateSeat(String fligntNumber, 
			  				  String seatNumber,
			  				  boolean isAvailable) {

    }
	
	 
}
