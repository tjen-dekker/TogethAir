package main.domain;

/**
 * Created by GWTBF10 on 6/11/2017.
 */
public class Seat {
	private String location;
	private float price;
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
