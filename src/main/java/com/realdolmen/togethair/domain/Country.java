package com.realdolmen.togethair.domain;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.*;

/**
 * Created by GWTBF10 on 6/11/2017.
 */
@Entity
@ManagedBean
@RequestScoped
public class Country {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated
	private Region region;
	private String name;

	public Long getId() {
		return id;
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
