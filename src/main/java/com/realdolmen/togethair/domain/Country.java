package com.realdolmen.togethair.domain;

import javax.persistence.*;

/**
 * Created by GWTBF10 on 6/11/2017.
 */
@Entity
public class Country {
	@Id
	@GeneratedValue
	private long id;
	
	@Enumerated
	private Region region;
	private String name;

	public long getId() {
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
