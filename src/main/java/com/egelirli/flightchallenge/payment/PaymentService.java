package com.egelirli.flightchallenge.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egelirli.flightchallenge.entity.Payment;
import com.egelirli.flightchallenge.repository.PaymentRepository;

import java.math.BigDecimal;

@Service
@Transactional
public class PaymentService {

    private Logger logger = LoggerFactory.getLogger(PaymentService.class);

    private BankService bankService;
    private PaymentRepository paymentRepository;

    public PaymentService(BankService bankService, 
    					  PaymentRepository paymentRepository) {
        this.bankService = bankService;
        this.paymentRepository = paymentRepository;
    }

    public void pay(BigDecimal price) {
        //pay with bank
    	logger.error("In pay -  price: {}", price);
        BankPaymentRequest request = new BankPaymentRequest();
        request.setPrice(price);
        BankPaymentResponse response = bankService.pay(request);

        //insert records
        Payment payment = new Payment();
        payment.setBankResponse(response.getResultCode());
        payment.setPrice(price);
        paymentRepository.save(payment);
        logger.info("Payment saved successfully price : {}!",price);
    }
}
