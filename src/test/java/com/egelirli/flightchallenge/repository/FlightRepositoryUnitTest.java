package com.egelirli.flightchallenge.repository;

//import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.egelirli.flightchallenge.entity.Flight;

//@DataJpaTest
//@ExtendWith(MockitoExtension.class)
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightRepositoryUnitTest {
	
	//private Logger logger = LoggerFactory.getLogger(FlightRepositoryUnitTest.class);
	//@Autowired
	//private EntityManager entityManager;
	
	//@Autowired
	//private FlightRepository flightRepo;
	
	@Test
	private void shouldPass() {
		//logger.debug("In shouldPass");	
	 	assertTrue(true);
	}
	
	
//	@Test
//	private void shouldFindNoFlight() {
//	 	List<Flight> list =  flightRepo.findAll();
//	 	
//	 	assertThat(list.isEmpty());
//	}

}
