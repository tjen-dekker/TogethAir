package com.realdolmen.togethair.DTO;

import com.realdolmen.togethair.domain.Country;
import com.realdolmen.togethair.domain.Region;

/**
 * Created by GWTBF10 on 9/11/2017.
 */
public class CountryDTO {
	private Region region;
	private String name;
	
	public CountryDTO(Country country) {
		setRegion(country.getRegion());
		setName(country.getName());
	}
	
	public Region getRegion() {
		return region;
	}
	
	public void setRegion(Region region) {
		this.region = region;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
