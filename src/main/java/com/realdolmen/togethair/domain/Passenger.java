package com.realdolmen.togethair.domain;

import com.realdolmen.togethair.Exceptions.SeatAlreadyTakenException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;
import java.util.Date;


@Entity
@ManagedBean
@SessionScoped
//todo check if this is rly necessary
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

	public void setSeat(Seat seat) throws SeatAlreadyTakenException {
		if(seat.isAvailable()) {
			this.seat = seat;
			seat.setAvailable(false);
		}
		else
			throw new SeatAlreadyTakenException();
		
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
