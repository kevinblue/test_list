package com.tenwa.business.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 抽象的Hibernate DataAccessObject
 * 
 * @author tracywindy
 * @since 
 * @param <ENTITY> 实体的类型
 * @param <ID> 主键的类型
 */
public abstract class AbstractHibernateBaseDao<ENTITY, ID extends Serializable> extends HibernateDaoSupport 
{
	/**
	 * 定义T的实际类型
	 */
	protected Class<ENTITY> entityClass;
	/**
	 * 构造器，通过反射获取T的实际类型，以供其它方法使用
	 */
	@SuppressWarnings("unchecked")
	public AbstractHibernateBaseDao() {
		entityClass = (Class<ENTITY>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	/**
	 * {@inheritDoc}
	 */
	public void delete(ENTITY entity) {
		getHibernateTemplate().delete(entity);
	}
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<ENTITY> findByExample(ENTITY entity) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass).add(
				Example.create(entity));
		return getHibernateTemplate().findByCriteria(criteria);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<ENTITY> findByExample(ENTITY entity, int firstResult,
			int maxResults, MatchMode matchMode) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass).add(
				Example.create(entity).enableLike(matchMode));
		return getHibernateTemplate().findByCriteria(criteria, firstResult,
				maxResults);
	}
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	
	public List<ENTITY> findByProperty(String propertyName, Object value) {
		String queryString = "from " + entityClass.getName()
				+ " as model where model." + propertyName + "= ?";
		return getHibernateTemplate().find(queryString, value);
	}

	/**
	 * {@inheritDoc}
	 */
	
	public List<ENTITY> findByProperties(Map<String, Object> properties) {
		return findByProperties(properties, -1, -1);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	
	public List<ENTITY> findByProperties(Map<String, Object> properties,
			int firstResult, int maxResults) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
		for (Entry<String, Object> entry : properties.entrySet()) {
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		return getHibernateTemplate().findByCriteria(criteria, firstResult,
				maxResults);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	
	public List<ENTITY> findbyPageCriteriaOrder(Map<String, Object> properties,
			int firstResult, int maxResults, String columnname, String orderType) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
		for (Entry<String, Object> entry : properties.entrySet()) {
			if ("com.doer.baseInfoManage.bean.Dictionary".equals(entityClass
					.getName())
					&& "CDictionaryType".equals(entry.getKey())) {
				criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
			} else {
				criteria.add(Restrictions.like(entry.getKey(), "%"
						+ entry.getValue() + "%"));
			}

		}
		if ("".equals(columnname) || columnname == null) {

		} else {
			if ("asc".equals(orderType)) {
				criteria.addOrder(Order.asc(columnname));
			} else {
				criteria.addOrder(Order.desc(columnname));
			}
		}

		return getHibernateTemplate().findByCriteria(criteria, firstResult,
				maxResults);
	}

	/**
	 * {@inheritDoc}
	 */
	
	public void saveOrUpdate(ENTITY entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	
	public List<ENTITY> findAll() {
		String queryString = "from " + entityClass.getName();
		return (List<ENTITY>) getHibernateTemplate().find(queryString);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	
	public List<ENTITY> findAll(int firstResult, int maxResults) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
		return getHibernateTemplate().findByCriteria(criteria, firstResult,
				maxResults);
	}

	/**
	 * {@inheritDoc}
	 */
	public ENTITY findById(ID id, LockMode lockMode) {
		return (ENTITY) getHibernateTemplate().load(entityClass, id, lockMode);
	}

	/**
	 * {@inheritDoc}
	 */
	public ENTITY findById(ID id) {
		return (ENTITY) getHibernateTemplate().load(entityClass, id);
	}

	/**
	 * {@inheritDoc}
	 */
	
	public void update(ENTITY entity) {
		getHibernateTemplate().update(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	public ENTITY merge(ENTITY entity) {
		return (ENTITY) getHibernateTemplate().merge(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	
	public ID save(ENTITY entity) {
		return (ID) getHibernateTemplate().save(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	
	public void refresh(ENTITY entity) {
		getHibernateTemplate().refresh(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	
	public Integer count() {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass)
				.setProjection(Projections.rowCount());
		return (Integer) getHibernateTemplate().findByCriteria(criteria).get(0);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<ENTITY> findByExampleLikeAnyWhere(ENTITY entity) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass).add(
				Example.create(entity).enableLike(MatchMode.ANYWHERE));
		return getHibernateTemplate().findByCriteria(criteria);
	}

	/**
	 * {@inheritDoc}
	 */
	public Integer countByProperty(String propertyName, Object value) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass)
				.setProjection(Projections.rowCount());
		criteria.add(Restrictions.eq(propertyName, value));
		return (Integer) getHibernateTemplate().findByCriteria(criteria).get(0);
	}
	/**
	 * 实用两级类型 分页模糊查询
	 * 
	 * @param properties
	 * @param firstResult
	 * @param maxResults
	 * @return list
	 */
	@SuppressWarnings("unchecked")
	public List<ENTITY> findbyPageCriteria(Map<String, Object> properties,
			int firstResult, int maxResults) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
		for (String key : properties.keySet()) {
			if (properties.get(key) != null && properties.get(key) != "") {
				/**
				 * 如果属性的值是String类型
				 */
				if (String.class.equals(properties.get(key).getClass())) {
					criteria.add(Restrictions.ilike(key, "%"
							+ properties.get(key) + "%", MatchMode.ANYWHERE));
				} else if (Long.class.equals(properties.get(key).getClass())
						|| Integer.class.equals(properties.get(key).getClass())) {
					criteria.add(Restrictions.eq(key, properties.get(key)));
				} else if (Date.class.equals(properties.get(key).getClass())) {
					/**
					 * 时间模糊查询具体实现
					 */
				} else { // 如果不是
					String[] longClass = key.split("\\.");
					Class<?> class1 = properties.get(key).getClass();
					DetachedCriteria criteria2 = criteria
							.createCriteria(longClass[0]);
					Method method;
					try {
						/**
						 * 获得属性的get方法
						 */
						String str = longClass[1].substring(0, 1);
						str = str.toUpperCase()
								+ longClass[1].substring(1, longClass[1]
										.length());
						method = class1.getMethod("get" + str);
						/**
						 * 调用方法
						 */
						Object returnValue = method.invoke(properties.get(key));
						if (returnValue != null && returnValue != "") {
							if (Long.class.equals(returnValue.getClass())) {
								criteria2.add(Restrictions.eq(longClass[1],
										returnValue));
							} else {
								criteria2.add(Restrictions.ilike(longClass[1],
										"%" + returnValue + "%",
										MatchMode.ANYWHERE));
							}
						}
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return this.getHibernateTemplate().findByCriteria(criteria,
				(firstResult - 1) * maxResults, maxResults);
	}

	/**
	 * {@inheritDoc}
	 */
	public Integer countByCriteria(Map<String, Object> properties) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);

		for (String key : properties.keySet()) {
			if (properties.get(key) != null && properties.get(key) != "") {
				/**
				 * 如果属性的值是String类型
				 */
				if (String.class.equals(properties.get(key).getClass())) {
					if ("com.doer.baseInfoManage.bean.Dictionary"
							.equals(entityClass.getName())
							&& "CDictionaryType".equals(key)) {
						criteria.add(Restrictions.eq(key, properties.get(key)));
					} else {
						criteria.add(Restrictions.ilike(key, "%"
								+ properties.get(key) + "%"));
					}

				} else if (Long.class.equals(properties.get(key).getClass())
						|| Integer.class.equals(properties.get(key).getClass())) {
					criteria.add(Restrictions.eq(key, properties.get(key)));
				} else if (Date.class.equals(properties.get(key).getClass())) {
					/**
					 * 时间模糊查询具体实现
					 */
				} else { // 如果不是
					String[] longClass = key.split("\\.");
					Class<?> class1 = properties.get(key).getClass();
					DetachedCriteria criteria2 = criteria
							.createCriteria(longClass[0]);
					Method method;
					try {
						/**
						 * 获得属性的get方法
						 */
						String str = longClass[1].substring(0, 1);
						str = str.toUpperCase()
								+ longClass[1].substring(1, longClass[1]
										.length());
						method = class1.getMethod("get" + str);
						/**
						 * 调用方法
						 */
						Object returnValue = method.invoke(properties.get(key));
						if (returnValue != null && returnValue != "") {
							if (Long.class.equals(returnValue.getClass())) {
								criteria2.add(Restrictions.eq(longClass[1],
										returnValue));
							} else {
								criteria2.add(Restrictions.ilike(longClass[1],
										"%" + returnValue + "%"));
							}
						}
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
		criteria.setProjection((Projections.rowCount()));

		return (Integer) this.getHibernateTemplate().findByCriteria(criteria).get(0);
	}

	/**
	 * 通过给定的一个对象和一个需要排序的列，查找与其匹配的对象并按照指定列进行排序。
	 * 
	 * @param entity
	 *            实体
	 * @return 实体集合
	 */
	@SuppressWarnings("unchecked")
	public List<ENTITY> findByExampleByOrder(ENTITY entity, String property) {

		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass).add(
				Example.create(entity)).addOrder(Order.asc(property));
		return (List<ENTITY>)getHibernateTemplate().findByCriteria(criteria);
	}

}
