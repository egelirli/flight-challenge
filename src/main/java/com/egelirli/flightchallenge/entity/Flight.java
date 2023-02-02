package com.egelirli.flightchallenge.entity;

import java.util.List;

import com.egelirli.rest.webservices.restfullwebservices.user.Post;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Flight {	 
	
//	@Id
//	@GeneratedValue
//	private Long      flightId;
	
	@Id
	private String    flightNumber;	
	
	
	private String    airlineName;
	private String    origin, destin;
	
	//private String    departureTime;
	//private int       flightDuration;
	@OneToMany(mappedBy = "flight")
	private List<FlightSeat> seatList;

	
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getAirlineName() {
		return airlineName;
	}
	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestin() {
		return destin;
	}
	public void setDestin(String destin) {
		this.destin = destin;
	}
	
	
	
}
