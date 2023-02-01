package com.egelirli.flightchallenge.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.egelirli.flightchallenge.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
