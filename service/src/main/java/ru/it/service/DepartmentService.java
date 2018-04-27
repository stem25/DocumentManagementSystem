package ru.it.service;

import ru.it.dao.DepartmentDao;
import ru.it.dao.OrganizationDao;
import ru.it.model.Department;
import ru.it.model.Organization;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Map;

@Stateless
@LocalBean
public class DepartmentService implements Crud<Department> {

    @EJB
    private DepartmentDao dao;


    @Override
    public Department create(Department entity) {
        Long id = dao.create(entity);
        return dao.read(id);
    }

    @Override
    public Department read(Long id) {
        return dao.read(id);
    }

    @Override
    public List<Department> list(Map<String, String> filter) {
        return dao.list(filter);
    }

    @Override
    public Department update(Department entity) {
        dao.update(entity);
        return dao.read(entity.getId());
    }

    @Override
    public void remove(Long id) {
        dao.remove(id);
    }
}
