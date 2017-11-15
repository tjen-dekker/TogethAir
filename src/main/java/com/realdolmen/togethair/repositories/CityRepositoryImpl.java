package com.realdolmen.togethair.repositories;

import com.realdolmen.togethair.domain.City;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by TDKBG57 on 2017-11-07.
 */
public class CityRepositoryImpl implements CityRepository{

    @PersistenceContext
    private EntityManager em;

    public List<City> findAll(){
        return em.createQuery("select c from City c", City.class).getResultList();
    }

    public City findById(Long id){
        return em.find(City.class, id);
    }
    
    public City findByName(String cityName){
        return em.createQuery("select c from City c where c.name = :cityName", City.class)
                .setParameter("cityName",cityName)
                .getSingleResult();
    }

    public City create(City city){
        em.persist(city);
        return city;
    }

    @Override
    public void merge(City city) {
        em.merge(city);
    }
}
