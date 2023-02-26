package com.egelirli.flightchallenge.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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
import com.egelirli.flightchallenge.flight.FlightService;
import com.egelirli.flightchallenge.flight.SeatService;

@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
class SeatServiceUnitTest {
	private static Logger logger = LoggerFactory.getLogger(SeatServiceUnitTest.class);

	@Autowired
	private FlightService flightService;
	
	@Autowired
	private SeatService seatService;
	
	
	 private static Flight testFlight1;
	 private static String testFlNumber1 = "ERD-101";
	 
	 private static  Flight testFlight2;
	 private static  String testFlNumber2 = "ERD-102";

	 
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
		seatService.deleteAll();
	}

	
	@Test
	void testAddSeat() {
		logger.debug("In testAddSeat");
		
		flightService.add(testFlight1);
		String  seat1 = "1A", seat2= "1B", seat3 = "1C";
		
		
		
		try {
			FlightSeat fs = seatService.addSeat(testFlight1.getFlightNumber(), seat1);
			if(fs != null) {
				assertTrue(fs.getSeatNumber().equals(seat1));
			}else {
				fail("Could not add new seat!");
			}
			
//			List<FlightSeat> availSeats = 
//					seatService.getAvailableSeats(testFlight1.getFlightNumber());
//			
//			if(availSeats.size() > 0) {
//				FlightSeat seat = availSeats.get(0);	
//				assertTrue(seat.getSeatNumber().equals(seat1));
//			}else{
//				fail("availSeats.size == 0!");
//			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("Exception in getAvailableSeats ");
			e.printStackTrace();
		}
		
//		FlightSeat s1 = seatService.findSeat(testFlight1.getFlightNumber(), seat1);
//		assertNotNull(s1);
//		
//		assertTrue(seat1.equals(s1.getSeatNumber()));
		
	}
	
	@Test
	public void testUpdateSeat() {
		logger.debug("In testUpdateSeat");
		
		try {
			flightService.add(testFlight1);
			String  seat1 = "1A", seat2= "1B", seat3 = "1C";
			
			FlightSeat flightSeat = 
					seatService.addSeat(testFlight1.getFlightNumber(), seat1);
			if(flightSeat != null) {
				//flightSeat.setAvailable(false);
				FlightSeat fs = 
						seatService.updateSeat(flightSeat.getId(), false);
				assertTrue(fs.getAvailable() == false);
			}else {
				fail("Could not add new seat!");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("Exception in getAvailableSeats ");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteSeat() {
		logger.debug("In testUpdateSeat");
		
		try {
			flightService.add(testFlight1);
			String  seat1 = "1A", seat2= "1B", seat3 = "1C";
			
			FlightSeat flightSeat = 
					seatService.addSeat(testFlight1.getFlightNumber(), seat1);
			if(flightSeat != null) {
				//flightSeat.setAvailable(false);
				seatService.removeSeat(flightSeat.getId());
				Optional<FlightSeat> fs = seatService.findById(flightSeat.getId());
				assertTrue(fs.isEmpty());
				
			}else {
				fail("Could not add new seat!");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("Exception in getAvailableSeats ");
			e.printStackTrace();
		}
	}

	@Test
	public void testGetAvailableSeats() {
		logger.debug("In testDeleteAllSeatsOfFlight");
		
		try {
			flightService.add(testFlight1);
			String  seat1 = "1A", seat2= "1B", seat3 = "1C";
			
			FlightSeat fs1 = 
					seatService.addSeat(testFlight1.getFlightNumber(), seat1);
			FlightSeat fs2 = 
					seatService.addSeat(testFlight1.getFlightNumber(), seat2);
			FlightSeat fs3 = 
					seatService.addSeat(testFlight1.getFlightNumber(), seat3);
			
			
			assertNotNull(fs1);
			assertNotNull(fs2);
			assertNotNull(fs3);
			
			if((fs1 != null) && (fs2 != null) && (fs3 != null) ) {
				//flightSeat.setAvailable(false);
				List<FlightSeat> seatLits = 
						seatService.getAvailableSeats(testFlight1.getFlightNumber());
				assertTrue(seatLits.size() == 3);
				
			}else {
				fail("Could not add new seat!");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("Exception in getAvailableSeats ");
			e.printStackTrace();
		}
	}

}
