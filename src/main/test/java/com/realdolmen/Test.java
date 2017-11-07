package java.com.realdolmen;

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

    @org.junit.Test
    public void testy(){}

}
