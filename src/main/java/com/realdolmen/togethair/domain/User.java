package com.realdolmen.togethair.domain;

import javax.faces.bean.ManagedBean;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


	private String firstName;
	private String lastName;


	private String name;
	private String password;
	private String email;

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
}
