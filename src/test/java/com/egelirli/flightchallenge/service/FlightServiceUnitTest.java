package com.egelirli.flightchallenge.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.egelirli.flightchallenge.entity.Flight;
import com.egelirli.flightchallenge.exception.ResourceNotFoundException;
import com.egelirli.flightchallenge.flight.FlightService;
import com.egelirli.flightchallenge.repository.FlightRepository;
import com.egelirli.flightchallenge.repository.FlightSeatRepository;


@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightServiceUnitTest {
	private static Logger logger = LoggerFactory.getLogger(FlightServiceUnitTest.class);
	
	@Autowired
	private FlightService flightService;
	
	 private static Flight testFlight1;
	 private static String testFlNumber1 = "ERD-001";
	 
	 private static  Flight testFlight2;
	 private static  String testFlNumber2 = "ERD-002";

	
//	@Test
//	public void test() {
//		fail("test - Not yet implemented");
//	}
	
	//@BeforeClass
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

		 BigDecimal price2 = new BigDecimal(1000);
		 testFlight2 = new Flight();
		 testFlight2.setFlightNumber(testFlNumber2);
		 testFlight2.setPrice(price2);
		 testFlight2.setAirlineName("ERD-HY");
		 testFlight2.setOrigin("IST");
		 testFlight2.setOrigin("ANK");
		 
		
	}
	
	@BeforeEach
	public void beforeEachMethod() {
		logger.debug("In beforeEachMethod");
		flightService.deleteAll();
	}
	
	
	@Test
	public void shouldFindNoFlight() {
		logger.debug("In shouldFindNoFlight");
		//fail("shouldFindNoFlightNot - yet implemented 2");
		try {
			Flight fl =  flightService.findFlightByNumber("test");
			//assertTrue(fl==null);
		} catch (ResourceNotFoundException e) {
			 assertTrue(true);
		}
	}

	@Test
	public void testAddAndFindFlight() {
		logger.debug("In testAddFlight");
		//fail("shouldFindNoFlightNot - yet implemented 2");
		
		try {
			
			 String flNumber1 = "ERD-002";
			 BigDecimal price = new BigDecimal(1000);
			 
			 boolean isAdded =  flightService.add(
					 flNumber1, price, "ERD-HY", "IST", "IZMIR");
			 assertTrue(isAdded);
			 
			 Flight fl =  flightService.findFlightByNumber(flNumber1);
			 assertTrue(fl != null);
			 if(fl != null) {
				 assertTrue(fl.getFlightNumber().equals(flNumber1));
				 //assertTrue(fl.getPrice().equals(price));
			 }
			 
		} catch (Exception e) {
			logger.error("testAddFlight - Exception e: {}", e);
			 assertTrue(false);
		}
	}

	
	
	
	@Test
	public void testUpdateFlight() {
		logger.debug("In testUpdateFlight");
		//fail("shouldFindNoFlightNot - yet implemented 2");
		
		try {
			 
			 boolean isAdded =  flightService.add(testFlight2);
			 assertTrue(isAdded);
			 
			 BigDecimal price1 = testFlight2.getPrice();
			 BigDecimal price2 = price1.add(new BigDecimal(1000));
			 testFlight2.setPrice(price2);
			 flightService.update(testFlight2);
			 
			 Flight fl =  flightService.findFlightByNumber(testFlNumber2);
			 assertTrue(fl != null);
			 
			 logger.debug("In testUpdateFlight - price2 : {} flPrice : {}", 
					 price2.toString(), fl.getPrice().toString());
			 //assertTrue(price2.equals(fl.getPrice()));
			 assertTrue(price2.floatValue() == fl.getPrice().floatValue());
			 
		} catch (Exception e) {
			logger.error("testUpdateFlight - Exception e: {}", e);
			 fail("Exception " + e.getMessage());
		}
	}

	@Test
	public void testUpdateFlightWithMockRepo() {
		logger.debug("In testUpdateFlightWithMockRepo");
		//fail("shouldFindNoFlightNot - yet implemented 2");
		
		try {
			FlightRepository flightRepo = mock(FlightRepository.class);
			FlightSeatRepository seatRepo =  mock(FlightSeatRepository.class);
			
			//Optional<Flight> fl = new Optional<Flight>(testFlight2.getFlightNumber());
//			when(flightRepo.findById(testFlight2.getFlightNumber())).
//						thenThrow(new ResourceNotFoundException("Flight Not Found!"));
			when(flightRepo.findById(testFlight2.getFlightNumber())).
						thenReturn(Optional.empty());
			
			
			 BigDecimal price = new BigDecimal(2000);
			 String flNumber1 = "ERD-002";
			 
			 Flight testFlight = new Flight();
			 testFlight.setFlightNumber(flNumber1);
			 testFlight.setPrice(price);
			 testFlight.setAirlineName("ERD-HY");
			 testFlight.setOrigin("IST");
			 testFlight.setOrigin("IZMIR");

			
			 FlightService flightServiceLocal = new FlightService(flightRepo, seatRepo);
			 
			 boolean isAdded =  flightServiceLocal.add(testFlight);
			 assertTrue(isAdded);
			 
			 BigDecimal price1 = testFlight.getPrice();
			 BigDecimal price2 = price1.add(new BigDecimal(1000));
			 testFlight.setPrice(price2);
			 flightServiceLocal.update(testFlight);
			 fail("Should have been thrown Exception!");
			
			 
		} catch (ResourceNotFoundException e ) {
			logger.error("testUpdateFlight - Exception e: {}", e);
			 assertTrue(true);
		}
	}
	
	
	
	@Test
	public void testRemovFlight() {
		logger.debug("In testAddFlight");
		//fail("shouldFindNoFlightNot - yet implemented 2");
		
		try {
			 
			 boolean isAdded =  flightService.add(testFlight1);
			 assertTrue(isAdded);
			 
			 flightService.remove(testFlNumber1);
			 
			 Flight fl =  flightService.findFlightByNumber(testFlNumber1);
			 assertTrue(fl != null);
			 if(fl != null) {
				 assertTrue(!fl.getFlightNumber().equals(testFlNumber1));
				 //assertTrue(fl.getPrice().equals(price));
			 }
			 
		} catch (Exception e) {
			logger.error("testAddFlight - Exception e: {}", e);
			 assertTrue(true);
		}
	}
	
}
