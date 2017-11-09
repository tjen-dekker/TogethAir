package com.realdolmen.togethair.domain;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * Created by GWTBF10 on 6/11/2017.
 */
@Entity
public class Booking {
	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy = "uuid")
	private UUID Id;

	@NotNull
	@OneToMany
	private List<Passenger> passengers = new ArrayList<>();

	public UUID getId() {
		return Id;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	public void addPassenger(Passenger p){
		passengers.add(p);
	}
}
