package com.realdolmen.togethair.DTO;

import com.realdolmen.togethair.domain.Airport;


/**
 * Created by GWTBF10 on 9/11/2017.
 */
public class AirportDTO {
	private String name;
	private String code;
	private CityDTO city;

	public AirportDTO(){}

	public AirportDTO(Airport airport) {
		setCity(new CityDTO(airport.getCity()));
		setCode(airport.getCode());
		setName(airport.getName());
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public CityDTO getCity() {
		return city;
	}
	
	public void setCity(CityDTO city) {
		this.city = city;
	}
}
