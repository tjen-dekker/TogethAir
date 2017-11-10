package com.realdolmen.togethair.domain;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by GWTBF10 on 6/11/2017.
 */
@Entity
@ManagedBean
@RequestScoped
public class Airport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(length = 50)
	@Pattern(regexp = "[a-zA-Z]+(?:[ '-][a-zA-Z]+)*")
	private String name;
	
	@NotNull
	@Column(length = 3)
	@Pattern(regexp = "^([A-Z]){3}")
	private String code;
	
	@NotNull
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
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return city + " (" + code + ")";
	}
}
