package com.realdolmen.togethair.repositories;

import com.realdolmen.togethair.domain.Airport;
import com.realdolmen.togethair.domain.Flight;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by TDKBG57 on 2017-11-07.
 */
@Named("FlightServiceImpl")
public class FlightRepositoryImpl implements FlightRepository{

    @PersistenceContext
    private EntityManager em;

    public List<Flight> findAll(){
        return em.createQuery("select f from Flight f", Flight.class).getResultList();
    }

    public Flight findById(Long id){
        return em.find(Flight.class, id);
    }
    
    public Flight findByFlightCode(String code) {
        return em.createQuery("select f from Flight f where f.flightCode = :code order by f.flightCode", Flight.class)
                .setParameter("code", code)
                .getSingleResult();
    }
	
    //ordered by flightCompany name
    public List<Flight> findFromTo(List<Airport> from, List<Airport> to){
	    return em.createQuery("select f from Flight f where f.from in :fromAirports and f.to in :toAirports order by f.flightCompany.name", Flight.class)
			    .setParameter("fromAirports", from)
			    .setParameter("toAirports",to)
			    .getResultList();
    }
	
	@Override
	public List<Flight> findFrom(List<Airport> from) {
		return em.createQuery("select f from Flight f where f.from in :fromAirports order by f.flightCompany.name", Flight.class)
				.setParameter("fromAirports", from)
				.getResultList();
	}
	
	@Override
	public List<Flight> findTo(List<Airport> to) {
		return em.createQuery("select f from Flight f where f.to in :toAirports order by f.flightCompany.name", Flight.class)
				.setParameter("toAirports",to)
				.getResultList();
	}
	
	public Flight create(Flight flight){
        em.persist(flight);
        return flight;
    }
}
