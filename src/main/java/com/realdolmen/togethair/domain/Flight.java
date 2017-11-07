package com.realdolmen.togethair.domain;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by GWTBF10 on 6/11/2017.
 */
@Entity
public class Flight {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private FlightCompany flightCompany;
	@OneToMany
	private List<Seat> seats;
	@ManyToOne
	private Airport from;
	@ManyToOne
	private Airport to;
	
	private Duration duration;
	private LocalDateTime departureDateTime;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Duration getDuration() {
		return duration;
	}
	
	public void setDuration(Duration duration) {
		this.duration = duration;
	}
	
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
