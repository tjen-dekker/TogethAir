package com.realdolmen.togethair.domain;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.*;

/**
 * Created by GWTBF10 on 6/11/2017.
 */
@Entity
@ManagedBean
@RequestScoped
public class Seat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String location;
	private float price;
	@Enumerated(value = EnumType.STRING) @Column(name = "class")
	private TravelClass travelClassName;
	@Transient
	private boolean available;
	@Transient
	private Flight flight;
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public TravelClass getTravelClassName() {
		return travelClassName;
	}
	
	public void setTravelClassName(TravelClass travelClassName) {
		this.travelClassName = travelClassName;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Flight getFlight() {
		return flight;
	}

	/**
	 * You should probably not be using this
	 * @param flight
	 */
	protected void setFlight(Flight flight) {
		this.flight = flight;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Seat seat = (Seat) o;

		if (id != null ? !id.equals(seat.id) : seat.id != null) return false;
		if (location != null ? !location.equals(seat.location) : seat.location != null) return false;
		return flight != null ? flight.equals(seat.flight) : seat.flight == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (location != null ? location.hashCode() : 0);
		result = 31 * result + (flight != null ? flight.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return location + " (" + getTravelClassName() + ")";
	}
}
