package com.realdolmen;

import com.realdolmen.togethair.domain.Partner;
import com.realdolmen.togethair.domain.User;
import org.junit.Before;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Test {

    protected static EntityManagerFactory emf;

    protected EntityManager em;

    @BeforeClass
    public static void initializeEntityManagerFactory() {
        emf = Persistence.createEntityManagerFactory("persistenceUnit");
    }

    @Before
    public void initializeEntityManagerWithTransaction() {
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    @org.junit.Test
    public void testy(){
        User u = new Partner();
        u.setEmail("test");
        em.persist(u);
        em.flush();
        em.close();
        emf.close();
    }

}
