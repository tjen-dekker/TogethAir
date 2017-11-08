package com.realdolmen;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AbstractRepositoryTest {

    protected static EntityManagerFactory emf;

    protected EntityManager em;

    @BeforeClass
    public static void initializeEntityManagerFactory() {
        emf = Persistence.createEntityManagerFactory("testUnit");
    }

    @Before
    public void initializeEntityManagerWithTransaction() {
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    @After
    public void rollbackTransactionAndCloseEntityManager() {
        if(em != null) {
            em.getTransaction().rollback();
            em.close();
        }
    }

    @AfterClass
    public static void destroyEntityManagerFactory() {
        if(emf != null) {
            emf.close();
        }
    }

    protected void flushAndClear() {
        em.flush();
        em.clear();
    }

}
