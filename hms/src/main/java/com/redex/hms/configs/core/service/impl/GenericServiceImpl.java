/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redex.hms.configs.core.service.impl;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;

import com.redex.hms.configs.core.dao.GenericDao;
import com.redex.hms.configs.core.service.GenericService;

/**
 *
 * @author Justin
 */
public class GenericServiceImpl<T, PK extends Serializable> implements GenericService<T, PK> {

    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass())
     * from Commons Logging
     */
    protected final Log log = LogFactory.getLog(getClass());
    /**
     * GenericDao instance, set by constructor of this class
     */
    protected GenericDao<T, PK> genericDao;

    /**
     * Public constructor for creating a new GenericManagerImpl.
     *
     * @param genericDao the GenericDao to use for persistence
     */
    public GenericServiceImpl() {
    }

    public GenericServiceImpl(final GenericDao<T, PK> genericDao) {
        this.genericDao = genericDao;
    }

    public List<T> readAll() throws Exception {
        return genericDao.readAll();
    }

    public T read(PK id) throws Exception {
        return genericDao.read(id);
    }

    public boolean exists(PK id) throws Exception {
        return genericDao.exists(id);
    }

    public PK save(T object) throws Exception {
        return genericDao.save(object);
    }

    public void remove(PK id) throws Exception {
        genericDao.remove(id);
    }

    public void saveOrUpdate(T object) throws Exception {
        genericDao.saveOrUpdate(object);
    }

//    public <T> ArrayList<T> readListByCriterionsAndProjections(Criterion[] criterions, ProjectionList[] projections) throws Exception {
//        return genericDao.readListByCriterionsAndProjections(criterions, projections);
//    }
    public List<T> readListByCriterion(Criterion criterion) throws Exception {
        return genericDao.readListByCriterion(criterion);
    }

    public List<T> readListByCriterion(Criterion criterion, Order order) throws Exception {
        return genericDao.readListByCriterion(criterion, order);
    }

    public List<T> readListByCriterions(Criterion[] criterions, Order order) throws Exception {
        return genericDao.readListByCriterions(criterions, order);
    }

    public T readByCriterion(Criterion criterion) throws Exception {
        return genericDao.readByCriterion(criterion);
    }

    public T readByCriterion(Criterion criterion, Order order) throws Exception {
        return genericDao.readByCriterion(criterion, order);
    }

    public void update(T object) throws Exception {
        genericDao.update(object);
    }

    public List readListByQuery(String query, Map<String, Object> queryParams) throws Exception {
        return genericDao.readListByQuery(query, queryParams);
    }

    public List readBeanListByQuery(String query, Map<String, Object> queryParams, Class resultClass) throws Exception {
        return genericDao.readBeanListByQuery(query, queryParams, resultClass);
    }

    public void executeQuery(String query) throws Exception {
         genericDao.executeQuery(query);
    }

    public List readBeanListByQuery(String query, Class resultClass) throws Exception {
       return genericDao.readBeanListByQuery(query, resultClass);
    }

    public List readBeanListByCriterion(Class resultClass, Criterion[] criterions, Map<String, String> aliasMap, Map<Projection, String> projectionMap) throws Exception {
       return genericDao.readBeanListByCriterion(resultClass, criterions, aliasMap, projectionMap);
    }

    public Object getUniqueResult(String query) {
       return genericDao.getUniqueResult(query);
    }
}
