package com.egelirli.flightchallenge.flightservice;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egelirli.flightchallenge.entity.Flight;
import com.egelirli.flightchallenge.exception.ResourceNotFoundException;
import com.egelirli.flightchallenge.repository.FlightRepository;
import com.egelirli.flightchallenge.repository.FlightSeatRepository;

@Service
@Transactional
public class FlightService {
	private Logger logger = LoggerFactory.getLogger(FlightService.class);
	
	private FlightRepository flightRepo;

	private FlightSeatRepository seatRepo;
	
	public FlightService(FlightRepository flightRepo,
						 FlightSeatRepository seatRepo){
		this.flightRepo = flightRepo;
		this.seatRepo = seatRepo;
	}
	
	public Flight findFlightByNumber(String flighNumber) 
							throws ResourceNotFoundException {
		return flightRepo.findById(flighNumber).
				orElseThrow(()->new ResourceNotFoundException(
					"Flight Not Found " + flighNumber));
		
	}

	
	public boolean add(String flightNumber, 
					   BigDecimal price,
					   String airlineName, 
					   String origin,
					   String destin) {
		logger.debug("In add -  flightNumber: {} "
				+ "airlineName: {}", flightNumber, airlineName);
		
		Optional<Flight> fl = flightRepo.findById(flightNumber);
		if(!fl.isEmpty()) {
			logger.error("In add - flight({}) already exists !", flightNumber);
			return false;
		}
		
		Flight flight = new Flight();
		flight.setFlightNumber(flightNumber);
		flight.setPrice(price);
		flight.setAirlineName(airlineName);
		flight.setOrigin(origin);
		flight.setDestin(destin);
		flightRepo.save(flight);
		logger.debug("In add - added  flightNumber: {} "
				+ "airlineName: {}", flightNumber, airlineName);
		
		return true;
	}

	
	public boolean add(Flight newFlight) {
		logger.debug("In add -  flight: {} ", newFlight.toString());

		Optional<Flight> fl = flightRepo.findById(newFlight.getFlightNumber());
		if (!fl.isEmpty()) {
			logger.error("In add - flight({}) already exists !", newFlight.getFlightNumber());
			return false;
		}
		flightRepo.save(newFlight);
		logger.debug("In add - added  new flight: {} ", newFlight.toString());

		return true;
	}	
	
	public boolean remove(String flightNumber) {
		
		Optional<Flight> fl = flightRepo.findById(flightNumber);
		if(fl.isEmpty()) {
			logger.error("In remove - could not find  flight({}) to remove!", flightNumber);
			return false;
		}else {
			flightRepo.delete(fl.get());
			seatRepo.deleteByFlightFlightNumber(flightNumber);
			logger.debug("In remove - removed flight  and seats - flightNumber: {} ",flightNumber);

			return true;
		}
	}

	public void update(Flight flToSave) throws ResourceNotFoundException {
		
		flightRepo.findById(flToSave.getFlightNumber()).
				orElseThrow(()->new ResourceNotFoundException(
					"Flight Not Found " + flToSave.getFlightNumber()));
		flightRepo.save(flToSave);
		
//		Optional<Flight> fl = flightRepo.findById(flToSave.getFlightNumber());
//		if(fl.isEmpty()) {
//			logger.error("In update - could not find  flight({}) to update!", 
//														flToSave.getFlightNumber());
//			return false;
//		}else {
//			flightRepo.save(flToSave);
//			logger.debug("In update - Updated flight - flightNumber: {} ",
//													flToSave.getFlightNumber());
//			return true;
//		}
		
	}

	public void deleteAll() {
		flightRepo.deleteAll();
	}
}
