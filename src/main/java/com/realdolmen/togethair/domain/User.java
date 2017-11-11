package com.realdolmen.togethair.domain;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@ManagedBean
@SessionScoped
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@OneToMany(orphanRemoval = true)
	private List<Booking> bookings;

	@NotNull
	@Column(length = 35)
	private String firstName;
	
	@NotNull
	@Column(length = 35)
	private String lastName;
	
	@NotNull
	private String password;
	
	@NotNull
	@Column(length = 80)
	@Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])")
	private String email;
	private String salt;

	@ManyToOne(optional = true)
	private FlightCompany company;


	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public String getFirstName() {return firstName;}

	public void setFirstName(String firstName) {this.firstName = firstName;}

	public String getLastName() {return lastName;}

	public void setLastName(String lastName) {this.lastName = lastName;}
	
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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
}
