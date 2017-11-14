package com.realdolmen.togethair.repositories;

import com.realdolmen.togethair.domain.Airport;
import com.realdolmen.togethair.domain.City;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class AirportRepositoryImpl implements AirportRepository {

    @PersistenceContext
    private EntityManager em;
    
    public AirportRepositoryImpl() {
    }
    
    public AirportRepositoryImpl(EntityManager em) {
        this.em=em;
    }
    
    public List<Airport> findAll() {
        return em.createQuery("select a from Airport a", Airport.class).getResultList();
    }

    public Airport findById(Long id) {
        return em.find(Airport.class, id);
    }

    public Airport create(Airport airport) {
        em.persist(airport);
        return airport;
    }

    @Override
    public Airport getByName(String name) {
        return em.createQuery("Select A from Airport A where A.name = :name", Airport.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public List<Airport> findByCity(City city){
        return em.createQuery("select a from Airport a where a.city = :city")
        .setParameter("city",city)
                .getResultList();
    }
}
