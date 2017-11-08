package com.realdolmen.togethair.domain;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by GWTBF10 on 6/11/2017.
 */
@Entity
@ManagedBean
@RequestScoped
public class Flight {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private FlightCompany flightCompany;
	@OneToMany(fetch = FetchType.EAGER)
	private Set<Seat> seats = new HashSet<>();
	@ManyToOne
	private Airport from;
	@ManyToOne
	private Airport to;
	
	private Duration duration;
	private LocalDateTime departureDateTime;


	
	public Long getId() {
		return id;
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
	
	public Set<Seat> getSeats() {
		return seats;
	}
	
	public void setSeats(Set<Seat> seats) {
		for (Seat s: seats) {
			addSeat(s);
		}
	}

	public void addSeat(Seat s){
		seats.add(s);
		s.setFlight(this);
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Flight flight = (Flight) o;

		if (id != flight.id) return false;
		if (from != null ? !from.equals(flight.from) : flight.from != null) return false;
		if (to != null ? !to.equals(flight.to) : flight.to != null) return false;
		return departureDateTime != null ? departureDateTime.equals(flight.departureDateTime) : flight.departureDateTime == null;
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (from != null ? from.hashCode() : 0);
		result = 31 * result + (to != null ? to.hashCode() : 0);
		result = 31 * result + (departureDateTime != null ? departureDateTime.hashCode() : 0);
		return result;
	}
}
