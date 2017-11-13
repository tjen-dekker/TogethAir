package com.realdolmen.togethair.domain;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by GWTBF10 on 6/11/2017.
 */
@Entity
@ManagedBean
@RequestScoped
public class Flight implements Serializable{
	
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
	
	@ElementCollection(fetch = FetchType.EAGER)
	@MapKeyColumn(name="volumeDiscountNUmberOfTickets")
	@Column(name="volumeDiscountPercentage")
	@CollectionTable(name="volumeDiscounts", joinColumns=@JoinColumn(name="id"))
	private Map<Integer, Integer> volumeDiscounts= new HashMap<>();
	
	@NotNull
	@DecimalMax(value = "200")
	@DecimalMin(value = "50")
	private int priceOverridePercentage=100;
	
	@Version
	private int version;
	
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
	
	public Map<Integer, Integer> getVolumeDiscounts() {
		return volumeDiscounts;
	}
	
	public void setVolumeDiscounts(Map<Integer, Integer> volumeDiscounts) {
		this.volumeDiscounts = volumeDiscounts;
	}
	
	public int getPriceOverridePercentage() {
		return priceOverridePercentage;
	}
	
	public void setPriceOverridePercentage(int priceOverridePercentage) {
		this.priceOverridePercentage = priceOverridePercentage;
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

	public Set<Seat> getAvailableSeats(){
		return seats.stream()
				.filter(s -> s.isAvailable())
				.collect(Collectors.toSet());
	}

	public Seat getSeat(String location){
		for(Seat s : getSeats()){
			if(s.getLocation().equals(location)){
				return s;
			}
		}
		return null;
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
