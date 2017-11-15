package com.realdolmen.togethair.domain;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

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
	
	@NotNull
	@Column(length = 4)
	private String location;
	
	@NotNull
	@DecimalMin(value = "0")
	private float price;
	
	@NotNull
	@Column(name = "class")
	@Enumerated(value = EnumType.STRING)
	private TravelClass travelClassName;
	
	@NotNull
	private boolean available=true;
	
	@ManyToOne
	private Flight flight;

    @Version
    private int version;

	public Seat(){}

	public Seat(String location, TravelClass travelClassName, float price, boolean available) {
		this.location = location;
		this.price = price;
		this.travelClassName = travelClassName;
		this.available = available;
	}

	public Seat(String location, float price, TravelClass travelClassName, boolean available, Flight flight) {
		this.location = location;
		this.price = price;
		this.travelClassName = travelClassName;
		this.available = available;
		this.flight = flight;
	}

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

	public Long getId() {
		return id;
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
	public String toString() {
		return location + " (" + getTravelClassName() + ")";
	}
}
