package com.realdolmen.togethair.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by GWTBF10 on 6/11/2017.
 */
@Entity
public class Flight {
	
	@Id
	@GeneratedValue
	private long id;
	
	private FlightCompany flightCompany;
	private List<Seat> seats;
	private Airport from;
	private Airport to;
	private LocalDateTime departureDateTime;
	
	public FlightCompany getFlightCompany() {
		return flightCompany;
	}
	
	public void setFlightCompany(FlightCompany flightCompany) {
		this.flightCompany = flightCompany;
	}
	
	public List<Seat> getSeats() {
		return seats;
	}
	
	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}
	
	public Airport getFrom() {
		return from;
	}
	
	public void setFrom(Airport from) {
		this.from = from;
	}
	
	public Airport getTo() {
		return to;
	}
	
	public void setTo(Airport to) {
		this.to = to;
	}
	
	public LocalDateTime getDepartureDateTime() {
		return departureDateTime;
	}
	
	public void setDepartureDateTime(LocalDateTime departureDateTime) {
		this.departureDateTime = departureDateTime;
	}
}
