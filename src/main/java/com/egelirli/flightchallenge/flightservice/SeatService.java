package com.egelirli.flightchallenge.flightservice;

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
	
	public FlightSeat addSeat(String flightNumber, 
						   String seatNumber) {
		logger.debug("In addSeat -  flightNumber: {} seatNumber: {}"
				 , flightNumber, seatNumber);
		
		Optional<Flight> flight = flightRepo.findById(flightNumber);
		if(flight.isEmpty()) {
				logger.error("In addSeat - flight({}) not exists !", flightNumber);
				return null;
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
		
		FlightSeat newSeat =  seatRepo.save(seat);
		if(newSeat != null) {
			logger.debug("In addSeat - added new seat seatId : {} flightNumber: {} "
					+ "seatNumber: {}",newSeat.getId(), flightNumber, seatNumber);
			
		}else {
			logger.error("In addSeat - Error adding new seat : flightNumber: {} "
					+ "seatNumber: {}", flightNumber, seatNumber);
			
		}
		
		return newSeat;
		
		
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

	public  FlightSeat updateSeat(int  seatId, 
							boolean isAvailable) throws ResourceNotFoundException {
		
		logger.debug("In updateSeat - seatId: {} "
				+ "isAvailable : {}", seatId,isAvailable );
		
		FlightSeat fs =  this.seatRepo.findById(seatId).
				 orElseThrow(()-> new ResourceNotFoundException("Seat not exists -  seatId : " + seatId));
		
		//fs.setPrice(price);
		fs.setAvailable(isAvailable);
		return this.seatRepo.save(fs);
	}
	
	

	public boolean isSeatAvailable(int  seatId) throws ResourceNotFoundException {
		FlightSeat fs =  this.seatRepo.findById(seatId).
				 orElseThrow(()-> 
				    new ResourceNotFoundException("Seat not exists- seatId : " + seatId));
		
		//fs.setPrice(price);
		return fs.getAvailable();
		
	}
	
	public void removeSeat(int  seatId) {

		logger.debug("In removeSeat - seatId: {}", seatId);
		this.seatRepo.deleteById(seatId);
		logger.debug("In removeSeat - removed seatId: {}", seatId);

	}
	
	public void deleteAllSeatsOfFlight(String flightNumber) 
								throws ResourceNotFoundException {
		logger.debug("In removeAllSeatsOfFlight - flightNumber: {}", flightNumber);
		if(!this.flightRepo.existsById(flightNumber)) {
			throw new ResourceNotFoundException(
					"Flight not exists -  flightNumber : " + flightNumber);
		}
		
		this.seatRepo.deleteByFlightFlightNumber(flightNumber);
	}
	
	public List<FlightSeat> getAvailableSeats(String flightNumber ) 
											throws ResourceNotFoundException{
		logger.debug("In getAvailableSeats - flightNumber: {}", flightNumber);
		flightRepo.findById(flightNumber).
				orElseThrow(()->new ResourceNotFoundException(
					"Flight Not Found " + flightNumber));
	
		return this.seatRepo.findByFlightFlightNumberAndIsAvailable(flightNumber, true);
	}
	
	public List<FlightSeat> getAllSeats(String flightNumber) throws ResourceNotFoundException {
		logger.debug("In getAllSeats - flightNumber: {}", flightNumber);
		flightRepo.findById(flightNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Flight Not Found " + flightNumber));

		return this.seatRepo.findByFlightFlightNumber(flightNumber);
	}
	
//	public Flight getFlightWithAvailableSeats(String flightNumber) 
//										throws ResourceNotFoundException{
//		logger.debug("In getFlightWithAvailableSeats - flightNumber: {}", flightNumber);  
//		Flight flight = flightRepo.findById(flightNumber).
//				orElseThrow(()->new ResourceNotFoundException(
//					"Flight Not Found " + flightNumber));
//	
//		flight.setSeatList(this.seatRepo.findByIsAvailable(true));
//		return flight;		
//		
//	}

	public FlightSeat findSeat(String flightNumber, String seat1) {
		return seatRepo.findByFlightFlightNumberAndSeatNumber(flightNumber, flightNumber);
	}

	public void deleteAll() {
		seatRepo.deleteAll();
		
	}

	public Optional<FlightSeat> findById(int id) {
		// TODO Auto-generated method stub
		return seatRepo.findById(id);
	}
}
