package com.realdolmen.togethair.DTO;

import com.realdolmen.togethair.domain.Booking;

import java.util.List;

/**
 * Created by GWTBF10 on 9/11/2017.
 */
public class UserDTO {
	private List<BookingDTO> bookings;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	
	public List<BookingDTO> getBookings() {
		return bookings;
	}
	
	public void setBookings(List<BookingDTO> bookings) {
		this.bookings = bookings;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}
