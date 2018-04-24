package ru.it.dao;

import ru.it.Exception.TooManyItemsException;
import ru.it.model.Person;

import javax.ejb.Local;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;
import java.util.Map;

@Local
public abstract class AbstractDao<T> {

    @PersistenceContext(unitName = "postgreUnit")
    EntityManager entityManager;

    public abstract T read(Long id);

    public abstract List<T> list(Map<String, String> filter);

    public abstract void update(T entity);

    public abstract void remove(Long id);

    public abstract Long create(T entity);

    public abstract Integer count();

    public abstract void buildWhere(StringBuilder sql, Map<String, String> filter);
}
