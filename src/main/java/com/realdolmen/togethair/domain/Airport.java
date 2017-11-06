package com.realdolmen.togethair.domain;

import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.Duration;

/**
 * Created by GWTBF10 on 6/11/2017.
 */
@Entity
public class Airport {
	@Id
	@GeneratedValue
	private long id;
	
	private String code;
	
	@ManyToOne
	private Country country;
	
	public long getId() {
		return id;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public Country getCountry() {
		return country;
	}
	
	public void setCountry(Country country) {
		this.country = country;
	}
	
}
