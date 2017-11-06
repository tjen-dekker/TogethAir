package com.realdolmen.togethair.domain;

import java.time.Duration;

/**
 * Created by GWTBF10 on 6/11/2017.
 */
public class Airport {
	private String code;
	private Country country;
	private Duration duration;
	
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
	
	public Duration getDuration() {
		return duration;
	}
	
	public void setDuration(Duration duration) {
		this.duration = duration;
	}
}
