package com.realdolmen.togethair.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;


@Entity
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Passenger passenger = (Passenger) o;

		if (id != null ? !id.equals(passenger.id) : passenger.id != null) return false;
		if (lastName != null ? !lastName.equals(passenger.lastName) : passenger.lastName != null) return false;
		if (firstName != null ? !firstName.equals(passenger.firstName) : passenger.firstName != null) return false;
		if (birthDate != null ? !birthDate.equals(passenger.birthDate) : passenger.birthDate != null) return false;
		return seat != null ? seat.equals(passenger.seat) : passenger.seat == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
		result = 31 * result + (seat != null ? seat.hashCode() : 0);
		return result;
	}
}
