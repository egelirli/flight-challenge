package com.egelirli.flightchallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.egelirli.flightchallenge.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight, String>{

}
