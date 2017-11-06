package main.domain;

import java.time.LocalDate;

/**
 * Created by GWTBF10 on 6/11/2017.
 */
public class Passenger {
	private String name;
	private String firstName;
	private LocalDate birthDate;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
