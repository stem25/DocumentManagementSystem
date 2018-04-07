package ru.it.dao;

import java.util.List;

public interface Dao<T> {

    public T read(Long id);
    public List<T> list();
    public void update(T entity);
    public void remove(Long id);
    public Long create(T entity);
}
