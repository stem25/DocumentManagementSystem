package it.ru.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import ru.it.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.Properties;

public class HibernateConnectionTest {

    @PersistenceContext(name = "postgreUnit")
    EntityManager entityManager;


    public void connectionTest(){
        entityManager = Persistence.createEntityManagerFactory("postgreUnit").createEntityManager();
        try{
            entityManager.getTransaction().begin();
            Person person = new Person();
            person.setFirstName("test1");
            entityManager.persist(person);
            entityManager.getTransaction().commit();
        } catch (Exception e){
            throw new IllegalArgumentException(e);
        } finally {
           entityManager.close();
        }
    }
}
