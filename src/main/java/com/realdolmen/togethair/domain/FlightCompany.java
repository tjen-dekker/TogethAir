package com.realdolmen.togethair.domain;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by GWTBF10 on 6/11/2017.
 */
@Entity
@ManagedBean
@RequestScoped
public class FlightCompany {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(length = 45)
	@Pattern(regexp = "/^[a-z ,.'-]+$/i")
	private String name;
	
	@NotNull
	@Column(length = 2)
	@Pattern(regexp = "^([A-Z]){2}")
	private String code;
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
