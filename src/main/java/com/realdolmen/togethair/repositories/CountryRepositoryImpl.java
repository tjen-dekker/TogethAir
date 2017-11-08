package com.realdolmen.togethair.repositories;

import com.realdolmen.togethair.domain.Country;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by TDKBG57 on 2017-11-07.
 */
public class CountryRepositoryImpl implements CountryRepository{

    @PersistenceContext
    private EntityManager em;

    public List<Country> findAll(){
        return em.createQuery("select c from Country c", Country.class).getResultList();
    }

    public Country findById(Long id){
        return em.find(Country.class, id);
    }

    public Country create(Country country){
        em.persist(country);
        return country;
    }
}
