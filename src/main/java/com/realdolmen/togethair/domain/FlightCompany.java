package com.realdolmen.togethair.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by GWTBF10 on 6/11/2017.
 */
@Entity
public class FlightCompany {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
