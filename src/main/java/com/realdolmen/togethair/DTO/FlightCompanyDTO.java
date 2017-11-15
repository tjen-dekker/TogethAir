package com.realdolmen.togethair.DTO;

import com.realdolmen.togethair.domain.FlightCompany;

/**
 * Created by GWTBF10 on 15/11/2017.
 */
public class FlightCompanyDTO {
	private String name;
	private String code;
	
	public FlightCompanyDTO(FlightCompany flightCompany) {
		setName(flightCompany.getName());
		setCode(flightCompany.getCode());
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
}
