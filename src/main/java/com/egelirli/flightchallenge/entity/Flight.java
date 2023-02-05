package com.egelirli.flightchallenge.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.persistence.Entity;
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
    private BigDecimal price;
	
	
	//private String    departureTime;
	//private int       flightDuration;
	//@OneToMany(mappedBy = "flight")
	
    private List<FlightSeat> seatList;
	
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
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

	
	/////////////////////////////////////
	///Seats
	////////////////////////////////////
	
	public List<FlightSeat> getSeatList() {
		return seatList;
	}
	public void setSeatList(List<FlightSeat> seatList) {
		this.seatList = seatList;
	}
	
//	
//	public List<FlightSeat> getAvailableSeats(){
//		return this.seatList.stream().
//				filter(seat -> seat.isAvailable() == true).collect(Collectors.toList());
//	}
//	
//	public Optional<FlightSeat> findSeat(String seatNumber){
//		return this.seatList.stream().
//				filter(seat -> seat.getSeatNumber().equalsIgnoreCase(seatNumber)).findFirst();
//	}
	
}
