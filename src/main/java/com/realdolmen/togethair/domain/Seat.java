package com.realdolmen.togethair.domain;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by GWTBF10 on 6/11/2017.
 */
@Entity
public class Seat {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String location;
	private float price;
	@Enumerated
	private Enum<TraveClassName> travelClassName;
	
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
	
	public Enum<TraveClassName> getTravelClassName() {
		return travelClassName;
	}
	
	public void setTravelClassName(Enum<TraveClassName> travelClassName) {
		this.travelClassName = travelClassName;
	}
}
