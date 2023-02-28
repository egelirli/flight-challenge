package com.egelirli.flightchallenge.flightservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.egelirli.flightchallenge.exception.ResourceNotFoundException;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

@Service
public class FlightServiceClients {
	private static Logger logger = LoggerFactory.getLogger(FlightServiceClients.class);
	
	@Autowired
    private FlightReservationService reservationService;

//    public FlightServiceClients(PaymentService iyzicoPaymentService) {
//        this.iyzicoPaymentService = iyzicoPaymentService;
//    }

    @Async
    public CompletableFuture<String> call(String flightNumber,
    								      int seatId) {
    	try {
			reservationService.reserveSeat(flightNumber, seatId);
		} catch (ResourceNotFoundException e) {
			logger.error("Exception : {}", e);
			e.printStackTrace();
		}
        return CompletableFuture.completedFuture("success");
    }
}
