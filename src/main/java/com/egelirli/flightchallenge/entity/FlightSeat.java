package com.egelirli.flightchallenge.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class FlightSeat {
	
	@Id
	private String seatNumber;
	
//	@Id
//	private String flightNumber;

	@ManyToOne(fetch = FetchType.LAZY)
	private Flight flight;
	
	private BigDecimal price;
	private boolean isAvailable;
	
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
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	@Override
	public String toString() {
		
		return "FlightSeat [seatNumber=" + seatNumber + ", price=" + price + ", "
				+ "isAvailable=" + isAvailable + "]";
	}
	
	
	
}
