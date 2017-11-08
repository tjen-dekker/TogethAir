package com.realdolmen.togethair.DTO;

import com.realdolmen.togethair.domain.Airport;
import com.realdolmen.togethair.domain.FlightCompany;
import com.realdolmen.togethair.domain.Seat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by TDKBG57 on 2017-11-08.
 */
public class FlightDTO {
	
	private FlightCompany flightCompany;
	
	private Set<Seat> seats = new HashSet<>();
	
	private Airport from;
	private Airport to;
	
	private String flightCode;
	private int duration; //estimated travel time in min
	
	private Date departureDateTime;
	
	public FlightCompany getFlightCompany() {
		return flightCompany;
	}
	
	public void setFlightCompany(FlightCompany flightCompany) {
		this.flightCompany = flightCompany;
	}
	
	public Set<Seat> getSeats() {
		return seats;
	}
	
	public void setSeats(Set<Seat> seats) {
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
	
	public String getFlightCode() {
		return flightCode;
	}
	
	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public Date getDepartureDateTime() {
		return departureDateTime;
	}
	
	public void setDepartureDateTime(Date departureDateTime) {
		this.departureDateTime = departureDateTime;
	}
	
	
}
