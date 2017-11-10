package com.realdolmen.togethair.domain;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by GWTBF10 on 6/11/2017.
 */
@Entity
@ManagedBean
@RequestScoped
public class Flight implements Comparable<Flight>, Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@ManyToOne
	private FlightCompany flightCompany;
	
	@NotNull
	@OneToMany(fetch = FetchType.EAGER,orphanRemoval = true,mappedBy = "flight")
	private Set<Seat> seats = new HashSet<>();
	
	@NotNull
	@ManyToOne
	private Airport from;
	
	@NotNull
	@ManyToOne
	private Airport to;
	
	@NotNull
	@Digits(integer = 4, fraction = 0)
	private int flightCode;
	
	@NotNull
	@DecimalMin(value = "0")
	@DecimalMax(value = "1200")
	private int duration; //estimated travel time in min
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Future
	private Date departureDateTime;
	
	public int getFreeSeatsOfClass(TravelClass travelClass){
		int count =0;
		for(Seat s : seats){
			if(s.getTravelClassName()==travelClass && s.isAvailable())
				count++;
		}
		return count;
	}
	
	public float getPriceOfCheapestSeat() {
		float lowestPrice=0f;
		for(Seat s : seats){
			if(lowestPrice<s.getPrice())
				lowestPrice = s.getPrice();
		}
		return lowestPrice;
	}
	
	public float getPriceOfCheapestSeatOfClass(TravelClass tClass) {
		float lowestPrice=0f;
		for(Seat s : seats){
			if(lowestPrice<s.getPrice() && s.getTravelClassName()==tClass)
				lowestPrice = s.getPrice();
		}
		return lowestPrice;
	}

	public Long getId() {
		return id;
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
	
	public Date getDepartureDateTime() {
		return departureDateTime;
	}
	
	public void setDepartureDateTime(Date departureDateTime) {
		this.departureDateTime = departureDateTime;
	}
	
	public static Comparator<Flight> cheapestEconomyComparator = (f1, f2) -> {
		return Float.compare(f1.getPriceOfCheapestSeatOfClass(TravelClass.ECONOMY),f2.getPriceOfCheapestSeatOfClass(TravelClass.ECONOMY));
	};
	
	public static Comparator<Flight> cheapestBusinessComparator = (f1, f2) -> {
		return Float.compare(f1.getPriceOfCheapestSeatOfClass(TravelClass.BUSINESS),f2.getPriceOfCheapestSeatOfClass(TravelClass.BUSINESS));
	};
	
	public static Comparator<Flight> cheapestFirstClassComparator = (f1, f2) -> {
		return Float.compare(f1.getPriceOfCheapestSeatOfClass(TravelClass.FIRSTCLASS),f2.getPriceOfCheapestSeatOfClass(TravelClass.FIRSTCLASS));
	};
	
	@Override   //Compare on cheapest ticket
	public int compareTo(Flight f) {
		return Float.compare(this.getPriceOfCheapestSeat(),f.getPriceOfCheapestSeat());
	}
	
	public static Comparator<Flight> DateTimeComparator = (f1, f2) -> {
		LocalDateTime f1Date = LocalDateTime.ofInstant(f1.getDepartureDateTime().toInstant(), ZoneId.systemDefault());
		LocalDateTime f2Date = LocalDateTime.ofInstant(f2.getDepartureDateTime().toInstant(), ZoneId.systemDefault());
		
		return f1Date.compareTo(f2Date);
	};
	
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
