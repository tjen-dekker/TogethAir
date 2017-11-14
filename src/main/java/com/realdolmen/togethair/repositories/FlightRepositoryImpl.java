package com.realdolmen.togethair.repositories;

import com.realdolmen.togethair.domain.Airport;
import com.realdolmen.togethair.domain.Flight;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by TDKBG57 on 2017-11-07.
 */
@Named("FlightServiceImpl")
@Transactional
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
	
	public List<Flight> findFromToBetweenDates(List<Airport> from, List<Airport> to,Date date1, Date date2){
		LocalDateTime localDateTime1 = LocalDateTime.ofInstant(date1.toInstant(), ZoneId.systemDefault());
		LocalDateTime localDateTime2 = LocalDateTime.ofInstant(date2.toInstant(), ZoneId.systemDefault()).plusHours(24);
		LocalDateTime now = LocalDateTime.now();
		List<Flight> tempList=  findFromTo(from,to);
		
		List<Flight> resultList = new ArrayList<>();
		for(Flight f : tempList){
			LocalDateTime localDateOfFlight = LocalDateTime.ofInstant(f.getDepartureDateTime().toInstant(), ZoneId.systemDefault());
			if(localDateOfFlight.isAfter(localDateTime1) && localDateOfFlight.isAfter(now) && localDateOfFlight.isBefore(localDateTime2) ||
					localDateOfFlight.isEqual(localDateTime1) || localDateOfFlight.isEqual(localDateTime2))
				resultList.add(f);
		}
		return resultList;
	}
	
	public List<Flight> findFromToOnDate(List<Airport> from, List<Airport> to, Date date1){
		LocalDate localDate1 =LocalDateTime.ofInstant(date1.toInstant(), ZoneId.systemDefault()).toLocalDate();
		List<Flight> tempList=  findFromTo(from,to);
		
		List<Flight> resultList = new ArrayList<>();
		for(Flight f : tempList){
			LocalDate localDateOfFlight = LocalDateTime.ofInstant(f.getDepartureDateTime().toInstant(), ZoneId.systemDefault()).toLocalDate();
			if(localDateOfFlight.isEqual(localDate1))
				resultList.add(f);
		}
		
		return resultList;
	}
	
	public List<Flight> findFromTo(List<Airport> from, List<Airport> to){
		List<Flight> resultList=  em.createQuery("select f from Flight f where f.from in :fromAirports and f.to in :toAirports", Flight.class)
				.setParameter("fromAirports", from)
				.setParameter("toAirports",to)
				.getResultList();
		return resultList;
	}

	
	public Flight create(Flight flight){
        em.persist(flight);
        return flight;
    }
}
