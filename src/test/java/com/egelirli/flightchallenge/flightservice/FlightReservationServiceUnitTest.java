package com.egelirli.flightchallenge.flightservice;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.egelirli.flightchallenge.entity.Flight;
import com.egelirli.flightchallenge.entity.FlightSeat;
import com.egelirli.flightchallenge.exception.ResourceNotFoundException;
import com.egelirli.flightchallenge.flightservice.FlightReservationService;
import com.egelirli.flightchallenge.flightservice.FlightService;
import com.egelirli.flightchallenge.flightservice.SeatService;

@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightReservationServiceUnitTest {
	private static Logger logger = 
			LoggerFactory.getLogger(FlightReservationServiceUnitTest.class);
	
	 private static Flight testFlight1;
	 private static String testFlNumber1 = "ERD-001";
	
	// private static String  seat1 = "1A", seat2= "1B", seat3 = "1C";
		
	 private static FlightSeat fs1,fs2,fs3; 
	 
	 
	//private static  Flight testFlight2;
	//private static  String testFlNumber2 = "ERD-002";

	@Autowired
	private FlightService flightService;
 
	@Autowired
	private SeatService seatService;
	
	@Autowired
	private FlightReservationService reservationService;
	
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
		 
		 
//		String  seat1 = "1A", seat2= "1B", seat3 = "1C";
		
//		fs1 = seatService.addSeat(testFlight1.getFlightNumber(), seat1);
//		fs2 = seatService.addSeat(testFlight1.getFlightNumber(), seat2);
//		fs3 = seatService.addSeat(testFlight1.getFlightNumber(), seat3);

		
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
		
		try {
			String  seat1 = "1A", seat2= "1B", seat3 = "1C";
			
			assertTrue(flightService.add(testFlight1));
			fs1 = seatService.addSeat(testFlight1.getFlightNumber(), seat1);
			fs2 = seatService.addSeat(testFlight1.getFlightNumber(), seat2);
			fs3 = seatService.addSeat(testFlight1.getFlightNumber(), seat3);
			
			assertTrue(
					reservationService.reserveSeat(testFlight1.getFlightNumber(), fs1.getId()));
			assertFalse(
					reservationService.reserveSeat(testFlight1.getFlightNumber(), fs1.getId()));
			assertFalse(
					reservationService.reserveSeat(testFlight1.getFlightNumber(), fs1.getId()));

			
			assertTrue(
					reservationService.reserveSeat(testFlight1.getFlightNumber(), fs2.getId()));
		
			
			assertTrue(
					reservationService.reserveSeat(testFlight1.getFlightNumber(), fs3.getId()));

			
			
		} catch (ResourceNotFoundException e) {
			fail("Exception : " + e.getMessage());
		}
		
		
	}
}
