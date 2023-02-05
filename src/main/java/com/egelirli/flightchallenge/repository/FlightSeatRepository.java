package com.egelirli.flightchallenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.egelirli.flightchallenge.entity.FlightSeat;

import jakarta.transaction.Transactional;

public interface FlightSeatRepository extends JpaRepository<FlightSeat, Integer>{
	
	List<FlightSeat> findByIsAvailable(boolean isAvailable);

	List<FlightSeat> findByFlightNumber(boolean isAvailable);
	
	
	@Transactional
	void deleteByFlightNumber(String flightNumber);
}
