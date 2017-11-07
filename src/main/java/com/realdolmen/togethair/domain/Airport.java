package com.realdolmen.togethair.domain;

import javax.inject.Inject;
import javax.persistence.*;
import java.time.Duration;

/**
 * Created by GWTBF10 on 6/11/2017.
 */
@Entity
public class Airport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String code;
	
	@ManyToOne
	private City city;
	
	public long getId() {
		return id;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public City getCountry() {
		return city;
	}
	
	public void setCountry(City country) {
		this.city = city;
	}
	
}
