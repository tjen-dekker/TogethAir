package com.realdolmen.togethair.domain;

import javax.persistence.*;


@Entity
public class Partner extends User {
	
	@ManyToOne
	private FlightCompany company;
	
	public FlightCompany getCompany() {
		return company;
	}
	
	public void setCompany(FlightCompany company) {
		this.company = company;
	}
}
