package com.realdolmen.togethair.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.UUID;

/**
 * Created by GWTBF10 on 6/11/2017.
 */
@Entity
public class Booking {
	@Id
	private UUID Id;
	private List<Passenger> passengers;
}
