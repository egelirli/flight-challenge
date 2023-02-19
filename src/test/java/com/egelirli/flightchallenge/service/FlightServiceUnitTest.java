package com.egelirli.flightchallenge.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.BeforeClass;
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


@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightServiceUnitTest {
	private Logger logger = LoggerFactory.getLogger(FlightServiceUnitTest.class);
	
	@Autowired
	private FlightService flightService;
	
	 private Flight testFlight1;
	 private String testFlNumber1 = "ERD-001";
	 
	 private Flight testFlight2;
	 private String testFlNumber2 = "ERD-002";

	
//	@Test
//	public void test() {
//		fail("test - Not yet implemented");
//	}
	
	@BeforeClass
	public void beforeClass() {
		logger.debug("In beforeClass");
		
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
	
	@Before
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
			assertTrue(fl==null);
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
			 
			 testFlight2.setPrice(price1.add(price2));
			 
			 Flight fl =  flightService.findFlightByNumber(testFlNumber2);
			 assertTrue(fl != null);
			 
			 assertTrue(price2.equals(fl.getPrice()));
			 
		} catch (Exception e) {
			logger.error("testUpdateFlight - Exception e: {}", e);
			 fail("Exception " + e.getMessage());
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
