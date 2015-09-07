/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redex.hms.configs.core.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.redex.hms.configs.core.dao.GenericDao;

/**
 *
 * @author Justin
 */
@Transactional
public class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK> {

	protected final Log log = LogFactory.getLog(getClass());
	private Class<T> entityClass;

	protected GenericDaoImpl(final Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected GenericDaoImpl() {
	}
	@Autowired
	private SessionFactory sessionFactory;

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<T> readAll() throws Exception {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		return criteria.list();
	}

	@Override
	public T read(final PK id) throws Exception {
		return (T) getCurrentSession().get(entityClass, id);
	}

	@Override
	public boolean exists(final PK id) throws Exception {
		T entity = (T) getCurrentSession().get(entityClass, id);
		return entity != null;
	}

	@Override
	public PK save(final T object) throws Exception {
		return (PK) getCurrentSession().save(object);
	}

	@Override
	public void remove(final PK id) throws Exception {
		T entity = read(id);
		getCurrentSession().delete(entity);
	}

	@Override
	public void saveOrUpdate(final T object) throws Exception {
		getCurrentSession().saveOrUpdate(object);
	}

	//    public <T> ArrayList<T> readListByCriterionsAndProjections(Criterion[] criterions, Projection[] projections) throws Exception {
	//      Criteria criteria=getCurrentSession().createCriteria(entityClass);
	//      if(criterions!=null && criterions.length>0){
	//          for(Criterion criterion: criterions){
	//              criteria.add(criterion);
	//          }
	//      }
	//      if(projections!=null && projections.length>0){
	//          for(Projection  projection: projections){
	//              criteria.setProjection(projection);
	//          }
	//      }
	//      return (ArrayList<T>) criteria.list();
	//    }
	@Override
	public List<T> readListByCriterion(final Criterion criterion) throws Exception {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		criteria.add(criterion);
		return criteria.list();
	}

	@Override
	public List<T> readListByCriterion(final Criterion criterion, final Order order) throws Exception {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		criteria.add(criterion);
		criteria.addOrder(order);
		return criteria.list();
	}

	@Override
	public List<T> readListByCriterions(final Criterion[] criterions, final Order order) throws Exception {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		if (criterions != null && criterions.length > 0) {
			for (Criterion criterion : criterions) {
				criteria.add(criterion);
			}
		}
		criteria.addOrder(order);
		return criteria.list();
	}

	@Override
	public T readByCriterion(final Criterion criterion) throws Exception {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		criteria.add(criterion);
		List result = criteria.list();
		if (result != null && !result.isEmpty()) {
			return (T) result.get(0);
		}
		return null;
	}

	@Override
	public T readByCriterion(final Criterion criterion, final Order order) throws Exception {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		criteria.add(criterion);
		criteria.addOrder(order);
		List result = criteria.list();
		if (result != null && !result.isEmpty()) {
			return (T) result.get(0);
		}
		return null;
	}

	@Override
	public void update(final T object) throws Exception {
		getCurrentSession().update(object);
	}

	@Override
	public List readBeanListByCriterion(final Class resultClass, final Criterion[] criterions, final Map<String, String> aliasMap, final Map<Projection, String> projectionMap) throws Exception {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);

		if (criterions != null) {
			for (Criterion criterion : criterions) {
				criteria.add(criterion);
			}
		}

		if (aliasMap != null) {
			for (Map.Entry<String, String> entry : aliasMap.entrySet()) {
				criteria.createAlias(entry.getKey(), entry.getValue());
			}
		}
		if (projectionMap != null) {
			ProjectionList projectionList = Projections.projectionList();
			for (Map.Entry<Projection, String> entry : projectionMap.entrySet()) {
				projectionList.add(entry.getKey(), entry.getValue());
			}
			criteria.setProjection(projectionList).setResultTransformer(Transformers.aliasToBean(resultClass));
		}
		return criteria.list();
	}

	@Override
	public List readListByQuery(final String query, final Map<String, Object> queryParams) throws Exception {
		SQLQuery sQLQuery = getCurrentSession().createSQLQuery(query);
		for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
			sQLQuery.setParameter(entry.getKey(), entry.getValue());
		}
		return sQLQuery.list();
	}

	@Override
	public List readBeanListByQuery(final String query, final Map<String, Object> queryParams, final Class resultClass) throws Exception {
		SQLQuery sQLQuery = getCurrentSession().createSQLQuery(query);
		for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
			sQLQuery.setParameter(entry.getKey(), entry.getValue());
		}
		sQLQuery.setResultTransformer(Transformers.aliasToBean(resultClass));
		return sQLQuery.list();
	}

	@Override
	public void executeQuery(final String query) throws Exception {
		SQLQuery sQLQuery = getCurrentSession().createSQLQuery(query);
		sQLQuery.executeUpdate();
	}

	/**
	 * This method is for returning the List<resultClass> objects by passing the
	 * query. no query - parameters are accepting in this method.
	 *
	 * @param query
	 * @param resultClass
	 * @return
	 * @throws Exception
	 */
	@Override
	public List readBeanListByQuery(final String query, final Class resultClass) throws Exception {
		SQLQuery sQLQuery = getCurrentSession().createSQLQuery(query);
		sQLQuery.setResultTransformer(Transformers.aliasToBean(resultClass));
		return sQLQuery.list();
	}

	public List readEntityListByQuery(final String query, final Class resultClass) throws Exception {
		SQLQuery sQLQuery = getCurrentSession().createSQLQuery(query);
		sQLQuery.addEntity(resultClass);
		return sQLQuery.list();
	}

	public List readEntityListByQuery(final String query, final Map<String, Object> queryParams, final Class resultClass) throws Exception {
		SQLQuery sQLQuery = getCurrentSession().createSQLQuery(query);
		for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
			sQLQuery.setParameter(entry.getKey(), entry.getValue());
		}
		sQLQuery.addEntity(resultClass);
		return sQLQuery.list();
	}

	@Override
	public Object getUniqueResult(final String query) {
		SQLQuery sQLQuery = getCurrentSession().createSQLQuery(query);
		return sQLQuery.uniqueResult();
	}
}
