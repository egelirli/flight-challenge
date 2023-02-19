package com.egelirli.flightchallenge.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.egelirli.flightchallenge.entity.Flight;
import com.egelirli.flightchallenge.exception.ResourceNotFoundException;
import com.egelirli.flightchallenge.flight.FlightService;
import com.egelirli.flightchallenge.repository.FlightRepository;
import com.egelirli.flightchallenge.repository.FlightRepositoryUnitTest;


@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightServiceUnitTest {
	private Logger logger = LoggerFactory.getLogger(FlightServiceUnitTest.class);
	
	@Autowired
	private FlightService flightService;
	
	
//	@Test
//	public void test() {
//		fail("test - Not yet implemented");
//	}
	
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
			
			 String flNumber1 = "ERD-001";
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
	public void testRemovFlight() {
		logger.debug("In testAddFlight");
		//fail("shouldFindNoFlightNot - yet implemented 2");
		
		try {
		
			 String flNumber1 = "ERD-002";
			 BigDecimal price = new BigDecimal(2000);
			 Flight newFl = new Flight();
			 newFl.setFlightNumber(flNumber1);
			 newFl.setPrice(price);
			 newFl.setAirlineName("ERD-HY");
			 newFl.setOrigin("IST");
			 newFl.setOrigin("IZMIR");
			 
			 boolean isAdded =  flightService.add(newFl);
			 assertTrue(isAdded);
			 
			 flightService.remove(flNumber1);
			 
			 Flight fl =  flightService.findFlightByNumber(flNumber1);
			 assertTrue(fl != null);
			 if(fl != null) {
				 assertTrue(!fl.getFlightNumber().equals(flNumber1));
				 //assertTrue(fl.getPrice().equals(price));
			 }
			 
		} catch (Exception e) {
			logger.error("testAddFlight - Exception e: {}", e);
			 assertTrue(true);
		}
	}
	
}
