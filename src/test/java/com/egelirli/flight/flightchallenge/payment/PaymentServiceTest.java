package com.egelirli.flight.flightchallenge.payment;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

//@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest
@EnableAsync
@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {
	private Logger logger = LoggerFactory.getLogger(PaymentService.class);
    @Autowired
    private PaymentServiceClients paymentServiceClients;
    
    @Test
    public void alwaysTrue(){
    	assertTrue(true);
    }
    
    @Test
    public void should_pay_with_iyzico_with_100_clients_together() {
    	logger.error("In should_pay_with_iyzico_with_100_clients_together");
        List<CompletableFuture<String>> futures = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            CompletableFuture<String> future = paymentServiceClients.call(new BigDecimal(i));
            futures.add(future);
        }
        futures.stream().forEach(f -> CompletableFuture.allOf(f).join());
    }
}
