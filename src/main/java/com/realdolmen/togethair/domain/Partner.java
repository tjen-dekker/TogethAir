package com.realdolmen.togethair.domain;

/**
 * Created by GWTBF10 on 6/11/2017.
 */
public class Partner extends User {
	private FlightCompany company;
	
	public FlightCompany getCompany() {
		return company;
	}
	
	public void setCompany(FlightCompany company) {
		this.company = company;
	}
}
