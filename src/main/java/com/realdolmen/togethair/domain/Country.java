package com.realdolmen.togethair.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by GWTBF10 on 6/11/2017.
 */
@Entity
public class Country {
	@Id
	@GeneratedValue
	private long id;
	
	private Region region;
	private String name;
	
	
}
