package it.ru.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import ru.it.model.Person;

import java.util.Properties;

public class HibernateConnectionTest {

    protected SessionFactory newSessionFactory() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        //log settings
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.show_sql", "true");
        //driver settings
        properties.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        properties.put("hibernate.connection.url", "jdbc:postgresql:test");
        properties.put("hibernate.connection.username", "postgres");
        properties.put("hibernate.connection.password", "123");

        return new Configuration()
                .addProperties(properties)
                .addAnnotatedClass(Person.class)
                .buildSessionFactory(
                        new StandardServiceRegistryBuilder()
                                .applySettings(properties)
                                .build()
                );
    }

    @Test
    public void connectionTest(){
        Session session = null;
        Transaction transaction = null;
        SessionFactory sf = newSessionFactory();
        try{
            session = sf.openSession();
            transaction = session.beginTransaction();
            Person person = new Person();
            person.setFirstName("test1");
            session.persist(person);
            transaction.commit();
        } catch (Exception e){
            throw new IllegalArgumentException(e);
        } finally {
            if(session != null){
                session.close();
            }
        }
    }
}
