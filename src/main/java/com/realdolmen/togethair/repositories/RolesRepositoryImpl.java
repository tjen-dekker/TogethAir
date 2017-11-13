package com.realdolmen.togethair.repositories;


import com.realdolmen.togethair.domain.Role;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@ApplicationScoped
@ManagedBean
public class RolesRepositoryImpl implements RolesRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    public List<Role> findAll() {
        return em.createQuery("select R from Role R", Role.class).getResultList();
    }

    @Override
    public Role getByName(String roleName) {
        return em.createQuery("select R from Role R where roleName = :rolename", Role.class)
                .setParameter("rolename", roleName)
                .getSingleResult();
    }

    @Override
    public Role create(Role role) {
        em.persist(role);
        return role;
    }
}
