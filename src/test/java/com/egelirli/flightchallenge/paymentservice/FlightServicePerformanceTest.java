package com.egelirli.flightchallenge.paymentservice;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;

import com.egelirli.flightchallenge.entity.Flight;
import com.egelirli.flightchallenge.entity.FlightSeat;
import com.egelirli.flightchallenge.flightservice.FlightService;
import com.egelirli.flightchallenge.flightservice.FlightServiceClients;
import com.egelirli.flightchallenge.flightservice.SeatService;
import com.egelirli.flightchallenge.payment.PaymentService;
import com.egelirli.flightchallenge.payment.PaymentServiceClients;

//@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest
@EnableAsync
public class FlightServicePerformanceTest {
	private Logger logger = LoggerFactory.getLogger(FlightServicePerformanceTest.class);
    @Autowired
    private FlightServiceClients flightServiceClients;
   
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private SeatService seatService;
   
    @Test
    public void shouldReserve10Seats() {
    	logger.debug("In shouldReserve10Seats");
    	
    	String flightNumber = "FL-001";
        List<CompletableFuture<String>> futures = new ArrayList<>();
    	List<FlightSeat>  seats =  addTestFlightAndSeats(flightNumber,10);
    	for (FlightSeat flightSeat : seats) {
            CompletableFuture<String> future = 
            		flightServiceClients.call(flightNumber ,flightSeat.getId());
            futures.add(future);
        }
        futures.stream().forEach(f -> CompletableFuture.allOf(f).join());
    }

	private List<FlightSeat> addTestFlightAndSeats(String flightNumber, int numSeats) {
		List<FlightSeat> retList = new ArrayList<>();
		
		BigDecimal price = new BigDecimal(2000);
		Flight testFlight1 = new Flight();
		testFlight1.setFlightNumber(flightNumber);
		testFlight1.setPrice(price);
		testFlight1.setAirlineName("ERD-HY");
		testFlight1.setOrigin("IST");
		testFlight1.setOrigin("IZMIR");
		flightService.add(testFlight1);
		
		for (int i = 0; i < numSeats; i++) {
			retList.add(seatService.addSeat(flightNumber, "seat"+i));
		}
		
		return retList;
	}
}
