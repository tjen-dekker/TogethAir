package com.realdolmen.togethair.domain;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;
import java.util.Date;


@Entity
@ManagedBean
@SessionScoped
public class Passenger {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String lastName;
	private String firstName;
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	@OneToOne
	private Seat seat;

	
	public String getlastName() {
		return lastName;
	}
	
	public void setlastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public Date getBirthDate() {return birthDate;}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		//TODO make sure the seat is actually available
		this.seat = seat;
		seat.setAvailable(false);
	}
}
