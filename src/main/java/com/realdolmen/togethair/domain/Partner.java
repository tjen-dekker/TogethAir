package com.realdolmen.togethair.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by GWTBF10 on 6/11/2017.
 */
@Entity
public class Partner extends User {
	@Id
	@GeneratedValue
	private long id;
	
	private FlightCompany company;
	
	public FlightCompany getCompany() {
		return company;
	}
	
	public void setCompany(FlightCompany company) {
		this.company = company;
	}
}
