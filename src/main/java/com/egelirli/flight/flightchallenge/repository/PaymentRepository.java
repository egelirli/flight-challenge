package com.egelirli.flight.flightchallenge.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.egelirli.flight.flightchallenge.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
