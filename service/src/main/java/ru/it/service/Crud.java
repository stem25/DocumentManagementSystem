package ru.it.service;

import java.util.List;
import java.util.Map;

public interface Crud<T> {

    T create(T entity);

    T read(Long id);

    List<T> list(Map<String, String> filter);

    Integer count(Map<String, String> filter);

    T update(T entity);

    void remove(Long id);
}
