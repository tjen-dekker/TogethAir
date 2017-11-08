package com.realdolmen.togethair.domain;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by GWTBF10 on 6/11/2017.
 */
@Entity
@ManagedBean
@SessionScoped
public class Booking {
	@Id
	private UUID Id;
	@OneToMany
	private List<Passenger> passengers = new ArrayList<>();
}
