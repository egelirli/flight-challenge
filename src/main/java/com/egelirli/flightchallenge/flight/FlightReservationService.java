package com.egelirli.flightchallenge.flight;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egelirli.flightchallenge.entity.Flight;
import com.egelirli.flightchallenge.exception.ResourceNotFoundException;
import com.egelirli.flightchallenge.payment.PaymentService;

@Service
@Transactional
public class FlightReservationService {

	private Logger logger = LoggerFactory.getLogger(FlightService.class);
	
	private PaymentService paymentService;
	private SeatService seatService;

	private FlightService flightService;

	public FlightReservationService(PaymentService paymentService,
									FlightService flightService,
								    SeatService seatService) {
		
		this.paymentService = paymentService;
		this.flightService = flightService;
		this.seatService = seatService;
	}
	
	
	public synchronized boolean reserveSeat(String flighNumber, 
								            int seatId) throws ResourceNotFoundException {
		logger.debug("In reserveSeat - seatId : {}", seatId);
		
		Flight fl = flightService.findFlightByNumber(flighNumber);
		
		if(seatService.isSeatAvailable(seatId)) {
			seatService.updateSeat(seatId, false);
			paymentService.pay(fl.getPrice());
			return true;
		}else {
			logger.warn("In reserveSeat - Seat is not available -  seatId : {}", seatId);
			return false;
		}
		
	}
}
