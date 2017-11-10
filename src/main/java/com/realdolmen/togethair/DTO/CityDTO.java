package com.realdolmen.togethair.DTO;

import com.realdolmen.togethair.domain.City;
import com.realdolmen.togethair.domain.Country;

import javax.persistence.ManyToOne;

/**
 * Created by GWTBF10 on 9/11/2017.
 */
public class CityDTO {
	private String name;
	private CountryDTO country;
	
	public CityDTO(City city) {
		setName(city.getName());
		setCountry(new CountryDTO(city.getCountry()));
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public CountryDTO getCountry() {
		return country;
	}
	
	public void setCountry(CountryDTO country) {
		this.country = country;
	}
}
