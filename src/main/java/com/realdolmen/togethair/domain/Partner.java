package com.realdolmen.togethair.domain;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@ManagedBean
@SessionScoped
public class Partner extends User {

	@NotNull
	@ManyToOne
	private FlightCompany company;
	
	public FlightCompany getCompany() {
		return company;
	}
	
	public void setCompany(FlightCompany company) {
		this.company = company;
	}
}
