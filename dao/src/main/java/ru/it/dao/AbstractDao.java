package ru.it.dao;

import ru.it.Exception.TooManyItemsException;
import ru.it.model.Person;

import javax.ejb.Local;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

@Local
public abstract class AbstractDao<T> {

    @PersistenceContext(unitName = "postgreUnit")
    EntityManager entityManager;

    public abstract T read(Long id);

    public abstract List<T> list(Map<String, String> filter);

    public abstract void update(T entity);

    public void remove(Long id){
        entityManager.remove(read(id));
    }

    public abstract Long create(T entity);

    public Integer count(Map<String, String> filter){
        return list(filter).size();
    }

    protected abstract void buildWhere(StringBuilder sql, Map<String, String> filter);

    protected void buildOrderBy(StringBuilder sql, Map<String, String> filter, String prefix){
        if(filter.containsKey("order") && filter.containsKey("orderType")){
            String orderType = "asc";
            if(filter.get("orderType").equals("0")) orderType = "asc";
            if(filter.get("orderType").equals("1")) orderType = "desc";
            sql.append(" ORDER BY ").append(prefix).append(".").append(filter.get("order")).append(" ").append(orderType);
        }
    }

   protected void buildPaging(TypedQuery<T> query, Map<String, String> filter){
       if(filter.containsKey("paging")){
           Integer min = Integer.valueOf(filter.get("paging").split(";")[0]);
           Integer max = Integer.valueOf(filter.get("paging").split(";")[1]);
           query.setFirstResult(min);
           query.setMaxResults(max-min);
       }
   }
}
