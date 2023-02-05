package com.egelirli.flightchallenge.flight;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.egelirli.flightchallenge.entity.Flight;
import com.egelirli.flightchallenge.entity.FlightSeat;
import com.egelirli.flightchallenge.exception.ResourceNotFoundException;
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
						   String seatNumber) {
		logger.debug("In addSeat -  flightNumber: {} seatNumber: {}"
				 , flightNumber, seatNumber);
		
		Optional<Flight> flight = flightRepo.findById(flightNumber);
		if(flight.isEmpty()) {
				logger.error("In addSeat - flight({}) not exists !", flightNumber);
				return false;
		 }

//		Optional<FlightSeat> st = flight.get().findSeat(seatNumber);
//		if(!st.isEmpty()) {
//				logger.error("In addSeat - seat({}) already exists !", seatNumber);
//				return false;
//		 }

			
		FlightSeat seat = new FlightSeat();
		seat.setFlight(flight.get());
		seat.setSeatNumber(seatNumber);
		seat.setAvailable(true);
		seatRepo.save(seat);
		
		logger.debug("In addSeat - added new seat flightNumber: {} "
				+ "seatNumber: {}", flightNumber, seatNumber);
		
		return true;
		
		
	}
	
//	public boolean removeSeat(String flightNumber, 
//							  String seatNumber) {
//		
//		logger.debug("In removeSeat -  flightNumber: {} seatNumber: {}"
//				 , flightNumber, seatNumber);
//		
//		Optional<Flight> flight = flightRepo.findById(flightNumber);
//		if(flight.isEmpty()) {
//				logger.error("In removeSeat - flight({}) not exists !", flightNumber);
//				return false;
//		 }
//		
//		
//		seatRepo.findAllById(null)
//		seatRepo.delete(seat);
//	
//	}

	public  void updateSeat(int  seatId, 
							boolean isAvailable) throws ResourceNotFoundException {
		
		logger.debug("In updateSeat - seatId: {} "
				+ "isAvailable : {}", seatId,isAvailable );
		
		FlightSeat fs =  this.seatRepo.findById(seatId).
				 orElseThrow(()-> new ResourceNotFoundException("Seat not exists -  seatId : " + seatId));
		
		//fs.setPrice(price);
		fs.setAvailable(isAvailable);
		this.seatRepo.save(fs);
	}
	
	
	public void removeSeat(int  seatId) {

		logger.debug("In removeSeat - seatId: {}", seatId);
		this.seatRepo.deleteById(seatId);
		logger.debug("In removeSeat - removed seatId: {}", seatId);

	}
	
	public void removeAllSeatsOfFlight(String flightNumber) 
								throws ResourceNotFoundException {
		logger.debug("In removeAllSeatsOfFlight - flightNumber: {}", flightNumber);
		if(!this.flightRepo.existsById(flightNumber)) {
			throw new ResourceNotFoundException(
					"Flight not exists -  flightNumber : " + flightNumber);
		}
		
		this.seatRepo.deleteByFlightNumber(flightNumber);
	}
	
	public List<FlightSeatInfo> getAvailableSeats(String flightNumber ) 
											throws ResourceNotFoundException{
		logger.debug("In getAvailableSeats - flightNumber: {}", flightNumber);
		Flight fl = flightRepo.findById(flightNumber).
				orElseThrow(()->new ResourceNotFoundException(
					"Flight Not Found " + flightNumber));
	
		return this.seatRepo.findByIsAvailable(true);
	}
	 
}
