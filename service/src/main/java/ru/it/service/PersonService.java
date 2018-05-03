package ru.it.service;

import ru.it.dao.DepartmentDao;
import ru.it.dao.PersonDao;
import ru.it.model.Department;
import ru.it.model.Person;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Map;

@Stateless
@LocalBean
public class PersonService implements Crud<Person> {
    @EJB
    private PersonDao dao;

    @Override
    public Person create(Person entity) {
        Long id = dao.create(entity);
        return dao.read(id);
    }

    @Override
    public Person read(Long id) {
        return dao.read(id);
    }

    @Override
    public List<Person> list(Map<String, String> filter) {
        return dao.list(filter);
    }

    @Override
    public Integer count(Map<String, String> filter) {
        return dao.count(filter);
    }

    @Override
    public Person update(Person entity) {
        dao.update(entity);
        return entity;
    }

    @Override
    public void remove(Long id) {
        dao.remove(id);
    }
}
