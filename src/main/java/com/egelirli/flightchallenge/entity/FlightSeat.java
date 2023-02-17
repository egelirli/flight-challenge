package com.egelirli.flightchallenge.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class FlightSeat {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String seatNumber;
	
//	@Id
//	private String flightNumber;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "flightNumber", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Flight flight;
	
	private boolean isAvailable;
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}



	public String getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	
    public void setFlight(Flight flight) {
       this.flight = flight;	
    }
    
	public Flight getFlight() {
		return flight;
	}
//	public String getFlightNumber() {
//		return flightNumber;
//	}
//	public void setFlightNumber(String flightNumber) {
//		this.flightNumber = flightNumber;
//	}
//	public BigDecimal getPrice() {
//		return price;
//	}
//	public void setPrice(BigDecimal price) {
//		this.price = price;
//	}
	
	public boolean getAvailable(){
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	
	@Override
	public String toString() {
		
		return "FlightSeat [seatNumber=" + seatNumber + 
							"Flight Number = " + flight.getFlightNumber() + 
				            ",isAvailable=" + isAvailable + "]";
	}
	
	
	
}
