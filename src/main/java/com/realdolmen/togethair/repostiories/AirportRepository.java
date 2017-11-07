package com.realdolmen.togethair.repostiories;

import com.realdolmen.togethair.domain.Airport;

import javax.persistence.EntityManager;
import java.util.List;

public class AirportRepository {

    EntityManager em;

    public List<Airport> findAll(){
        return em.createQuery("select a from Airport a", Airport.class).getResultList();
    }

    public Airport findById(long id){
        return em.find(Airport.class, id);
    }

    public Airport create(Airport airport){
        em.persist(airport);
        return airport;
    }


    public void setEm(EntityManager em) {
        this.em = em;
    }
}
