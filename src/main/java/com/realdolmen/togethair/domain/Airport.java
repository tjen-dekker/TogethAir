package com.realdolmen.togethair.domain;

import javax.persistence.*;

/**
 * Created by GWTBF10 on 6/11/2017.
 */
@Entity
public class Airport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String code;
	
	@ManyToOne
	private City city;
	
	public Long getId() {
		return id;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public City getCity() {
		return city;
	}
	
	public void setCity(City city) {
		this.city = city;
	}
}
