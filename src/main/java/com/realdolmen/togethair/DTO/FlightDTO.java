package com.realdolmen.togethair.DTO;

import com.realdolmen.togethair.domain.Flight;
import com.realdolmen.togethair.domain.Seat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by TDKBG57 on 2017-11-08.
 */
public class FlightDTO {
	
	private String flightCompany;
	
	private Set<SeatDTO> seats = new HashSet<>();
	
	private AirportDTO from;
	private AirportDTO to;
	
	private int flightCode;
	private int duration; //estimated travel time in min
	
	private Date departureDateTime;

	public FlightDTO(){

	}

	public FlightDTO(Flight flight) {
		setFlightCode(flight.getFlightCode());
		setDepartureDateTime(flight.getDepartureDateTime());
		setDuration(flight.getDuration());
		setFlightCompany(flight.getFlightCompany().getName());
		setFrom(new AirportDTO(flight.getFrom()));
		setTo(new AirportDTO(flight.getTo()));

		for (Seat s : flight.getSeats()){
			seats.add(new SeatDTO(s));
		}
	}
	
	public String getFlightCompany() {
		return flightCompany;
	}
	
	public void setFlightCompany(String flightCompany) {
		this.flightCompany = flightCompany;
	}
	
	public Set<SeatDTO> getSeats() {
		return seats;
	}
	
	public void setSeats(Set<SeatDTO> seats) {
		this.seats = seats;
	}
	
	public AirportDTO getFrom() {
		return from;
	}
	
	public void setFrom(AirportDTO from) {
		this.from = from;
	}
	
	public AirportDTO getTo() {
		return to;
	}
	
	public void setTo(AirportDTO to) {
		this.to = to;
	}
	
	public int getFlightCode() {
		return flightCode;
	}
	
	public void setFlightCode(int flightCode) {
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
