package com.realdolmen.togethair.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * Created by GWTBF10 on 6/11/2017.
 */
@Entity
public class Passenger {
	@Id
	@GeneratedValue
	private long id;
	
	private String lastName;
	private String firstName;
	private LocalDate birthDate;
	
	public String getName() {
		return lastName;
	}
	
	public void setName(String name) {
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public LocalDate getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
}
