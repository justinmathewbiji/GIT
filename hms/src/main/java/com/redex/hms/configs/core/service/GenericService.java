
package com.redex.hms.configs.core.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;

/**
 *
 * @author Justin
 */
public interface GenericService<T, PK extends Serializable> {

    public List<T> readAll() throws Exception;

    public T read(PK id) throws Exception;

    public boolean exists(PK id) throws Exception;

    public PK save(T object) throws Exception;

    public void remove(PK id) throws Exception;

    public void saveOrUpdate(T object) throws Exception;

//    public <T> ArrayList<T> readListByCriterionsAndProjections(Criterion[] criterions, ProjectionList[] projections) throws Exception;
    public List<T> readListByCriterion(Criterion criterion) throws Exception;

    public List<T> readListByCriterion(Criterion criterion, Order order) throws Exception;

    public List<T> readListByCriterions(Criterion[] criterions, Order order) throws Exception;

    public T readByCriterion(Criterion criterion) throws Exception;

    public T readByCriterion(Criterion criterion, Order order) throws Exception;

    public void update(T object) throws Exception;

    public List readListByQuery(String query, Map<String, Object> queryParams) throws Exception;

    public List readBeanListByQuery(String query, Map<String, Object> queryParams, Class resultClass) throws Exception;
    
    public List readBeanListByQuery(String query, Class resultClass) throws Exception;

    public void executeQuery(String query) throws Exception;
    
    public List readBeanListByCriterion(Class resultClass, Criterion[] criterions, Map<String, String> aliasMap, Map<Projection, String> projectionMap) throws Exception;
    
    public Object getUniqueResult(String query);
}