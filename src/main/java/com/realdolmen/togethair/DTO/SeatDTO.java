package com.realdolmen.togethair.DTO;

import com.realdolmen.togethair.domain.Seat;
import com.realdolmen.togethair.domain.TravelClass;


/**
 * Created by GWTBF10 on 9/11/2017.
 */
public class SeatDTO {
	private String location;
	private float price;
	private TravelClass travelClassName;
	private boolean available;
	
	public SeatDTO(Seat seat) {
		setAvailable(seat.isAvailable());
		setLocation(seat.getLocation());
		setPrice(seat.getPrice());
		setTravelClassName(seat.getTravelClassName());
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
}
