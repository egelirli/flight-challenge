package com.egelirli.flight.flightchallenge.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;


import com.egelirli.flightchallenge.entity.Flight;
import com.egelirli.flightchallenge.payment.PaymentService;
import com.egelirli.flightchallenge.repository.FlightRepository;

import jakarta.persistence.EntityManager;

@EnableAutoConfiguration
@SpringBootTest
@DataJpaTest
//@ExtendWith(MockitoExtension.class)
public class FlightRepositoryUnitTest {
	
	private Logger logger = LoggerFactory.getLogger(FlightRepositoryUnitTest.class);
	//@Autowired
	//private EntityManager entityManager;
	
	@Autowired
	private FlightRepository flightRepo;
	
	@Test
	private void shouldPass() {
		logger.debug("In shouldPass");	
	 	assertTrue(true);
	}
	
	
	@Test
	private void shouldFindNoFlight() {
	 	List<Flight> list =  flightRepo.findAll();
	 	
	 	assertThat(list.isEmpty());
	}

}
