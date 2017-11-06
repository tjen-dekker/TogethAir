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
	
	@ManyToOne
	private Region region;
	private String name;
	
	
}
