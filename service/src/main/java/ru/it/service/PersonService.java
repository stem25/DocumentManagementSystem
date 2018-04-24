package ru.it.service;

import ru.it.Exception.NotFoundException;
import ru.it.dao.DepartmentDao;
import ru.it.dao.OrganizationDao;
import ru.it.dao.PersonDao;
import ru.it.model.Person;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Stateless
@LocalBean
public class PersonService implements Crud<Person> {
    @EJB
    private PersonDao personDao;

    @EJB
    private DepartmentDao departmentDao;

    @Override
    public Person create(Person entity) {
        Long id = personDao.create(entity);
        return personDao.read(id);
    }

    @Override
    public Person read(Long id) {
        return personDao.read(id);
    }

    @Override
    public List<Person> list(Map<String, String> filter) {
        return personDao.list(filter);
    }

    @Override
    public Person update(Person entity) {
        personDao.update(entity);
        return entity;
    }

    @Override
    public void remove(Long id) {
        personDao.remove(id);
    }
}
