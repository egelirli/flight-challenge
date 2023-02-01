package com.egelirli.flightchallenge.flight;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egelirli.flightchallenge.entity.Flight;
import com.egelirli.flightchallenge.repository.FlightRepository;

@Service
@Transactional
public class FlightService {
	private Logger logger = LoggerFactory.getLogger(FlightService.class);
	
	private FlightRepository flightRepo;
	
	public FlightService(FlightRepository flightRepo){
		this.flightRepo = flightRepo;
	}
	
	public boolean add(String flightNumber, 
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
		flight.setAirlineName(airlineName);
		flight.setOrigin(origin);
		flight.setDestin(destin);
		flightRepo.save(flight);
		logger.debug("In add - added  flightNumber: {} "
				+ "airlineName: {}", flightNumber, airlineName);
		
		return true;
	}
	
	public boolean remove(String flightNumber) {
		
		Optional<Flight> fl = flightRepo.findById(flightNumber);
		if(fl.isEmpty()) {
			logger.error("In remove - could not find  flight({}) to remove!", flightNumber);
			return false;
		}else {
			flightRepo.delete(fl.get());
			logger.debug("In remove - removed flight - flightNumber: {} ",flightNumber);

			return true;
		}
	}

	public boolean update(Flight flToSave) {
		Optional<Flight> fl = flightRepo.findById(flToSave.getFlightNumber());
		if(fl.isEmpty()) {
			logger.error("In update - could not find  flight({}) to update!", 
														flToSave.getFlightNumber());
			return false;
		}else {
			flightRepo.save(flToSave);
			logger.debug("In update - Updated flight - flightNumber: {} ",
													flToSave.getFlightNumber());
			return true;
		}
		
	}
	
}
