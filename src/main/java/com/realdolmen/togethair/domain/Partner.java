package com.realdolmen.togethair.domain;

import javax.persistence.*;

/**
 * Created by GWTBF10 on 6/11/2017.
 */
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
