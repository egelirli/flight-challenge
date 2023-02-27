package com.egelirli.flightchallenge.service;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.egelirli.flightchallenge.entity.Flight;
import com.egelirli.flightchallenge.entity.FlightSeat;
import com.egelirli.flightchallenge.flight.FlightService;
import com.egelirli.flightchallenge.flight.SeatService;

public class FlightReservationServiceUnitTest {
	private static Logger logger = 
			LoggerFactory.getLogger(FlightReservationServiceUnitTest.class);
	
	 private static Flight testFlight1;
	 private static String testFlNumber1 = "ERD-001";
	
	 private static String  seat1 = "1A", seat2= "1B", seat3 = "1C";
		
	 private static FlightSeat fs1,fs2,fs3; 
	 
	 
	private static  Flight testFlight2;
	private static  String testFlNumber2 = "ERD-002";

	@Autowired
	private FlightService flightService;
 
	@Autowired
	private static SeatService seatService;
 
	@BeforeAll
	public static void executeBeforeAll() {
		logger.debug("In onceExecutedBeforeAll");
		
		 BigDecimal price = new BigDecimal(2000);
		 testFlight1 = new Flight();
		 testFlight1.setFlightNumber(testFlNumber1);
		 testFlight1.setPrice(price);
		 testFlight1.setAirlineName("ERD-HY");
		 testFlight1.setOrigin("IST");
		 testFlight1.setOrigin("IZMIR");

		String  seat1 = "1A", seat2= "1B", seat3 = "1C";
		
		fs1 = seatService.addSeat(testFlight1.getFlightNumber(), seat1);
		fs2 = seatService.addSeat(testFlight1.getFlightNumber(), seat2);
		fs3 = seatService.addSeat(testFlight1.getFlightNumber(), seat3);

		
	}
	
	@BeforeEach
	public void beforeEachMethod() {
		logger.debug("In beforeEachMethod");
		flightService.deleteAll();
		seatService.deleteAll();
		
	}
 
	@Test
	public void testReserveSeat() {
		logger.debug("In testReserveSeat ");
		
		
		
	}
}
