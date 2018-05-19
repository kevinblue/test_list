package com.tenwa.business.daoImpl;

/**
 * 项目名称：    系统名称
 * 包名：              com.business.daoImpl
 * 文件名：         BaseDaoImpl.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-1-14-上午10:01:10
 * Copyright：2013XX公司-版权所有
 **/


import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.Assert;
import oracle.jdbc.OracleTypes;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.entity.User;
import com.tenwa.business.model.CaseEnum;
import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.EntityUtil;
import com.tenwa.kernal.utils.MathUtil;
import com.tenwa.kernal.utils.ResourceUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.kernal.utils.StringUtil;


/**
 * 类名称： BaseDaoImpl
 * 类描述：
 * 创建人： Administrator
 * 修改人： Administrator
 * 修改时间：2013-1-14 上午10:01:10
 * 修改备注：
 * 
 * @version 1.0.0
 **/
@SuppressWarnings({ "unchecked" })
public abstract class AbstractBaseDaoImpl implements BaseDao {

	private static ObjectMapper	jsonMapper	= new ObjectMapper();

	private static Log			log			= LogFactory.getLog(AbstractBaseDaoImpl.class);

	@Override
	public abstract JdbcTemplate getJdbcTemplate() throws Exception;

	@Override
	public abstract HibernateTemplate getHibernateTemplate() throws Exception;
	
	@Override
	public abstract RedisTemplate<String, String> getRedisTemplate() throws Exception;

	@Override
	public void saveEntity(Object entity) throws Exception
	{
		this.getHibernateTemplate().save(entity);
	}

	@Override
	public void saveAllEntities(Collection entities) throws Exception
	{
		for (Object entity : entities)
		{
			this.getHibernateTemplate().save(entity);
		}
	}

	@Override
	public void updateEntity(Object entity) throws Exception
	{
		this.getHibernateTemplate().update(entity);
	}

	@Override
	public void updateAllEntities(Collection entities) throws Exception
	{
		for (Object entity : entities)
		{
			this.getHibernateTemplate().update(entity);
		}
	}

	@Override
	public void mergeEntity(Object entity) throws Exception
	{
		this.getHibernateTemplate().merge(entity);
	}

	@Override
	public void mergeAllEntities(Collection entities) throws Exception
	{
		for (Object entity : entities)
		{
			this.getHibernateTemplate().merge(entity);
		}
	}

	@Override
	public void saveOrUpdateEntity(Object entity) throws Exception
	{
		this.getHibernateTemplate().saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAllEntities(Collection entities) throws Exception
	{
		this.getHibernateTemplate().saveOrUpdateAll(entities);
	}

	@Override
	public void removeEntity(Object entity) throws Exception
	{
		this.getHibernateTemplate().delete(entity);
	}

	@Override
	public void removeAllEntites(Collection entities) throws Exception
	{
		if(entities!= null){
			this.getHibernateTemplate().deleteAll(entities);
		}
	}

	@Override
	public int updateByHSQL(String hsql, Object... values) throws Exception
	{
		return this.getHibernateTemplate().bulkUpdate(hsql, values);
	}

	public void updateFlush() throws Exception {
		this.getHibernateTemplate().flush();
	}

	@Override
	public <T> List<T> findEntities(Class<T> entityClass) throws Exception
	{
		/*DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
		return this.getHibernateTemplate().findByCriteria(criteria);*/
		String entityClassName = entityClass.getSimpleName();
		String hsql = "select u from "+entityClassName+" u";
		return this.findResultsByHSQL(hsql);
	}

	@Override
	public <T> T findEntityByID(Class<T> entityClass, String id) throws DataAccessException, Exception {
		return this.getHibernateTemplate().get(entityClass, id);
	}

	@Override
	public <T> List<T> findEntityByIDArray(Class<T> entityClass, String[] ids) throws DataAccessException, Exception {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
		criteria.add(Restrictions.in("id", ids));
		List<T> list = (List<T>)this.getHibernateTemplate().findByCriteria(criteria);
		return list;

	}

	@Override
	public <T> Integer getEntitiesRowCount(Class<T> entityClass) throws Exception
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
		criteria.setProjection(Projections.rowCount());
		return (Integer) this.getHibernateTemplate().findByCriteria(criteria).get(0);
	}

	@Override
	public <T> List<T> findEntitiesPage(final Class<T> entityClass, final int start, final int limit) throws Exception
	{
		return this.getHibernateTemplate().executeFind(new HibernateCallback<List<Object>>() {
			@Override
			public List doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria criteria = session.createCriteria(entityClass);
				criteria.setFirstResult(start);
				criteria.setMaxResults(limit);
				return criteria.list();
			}
		});
	}

	@Override
	public <T> List<T>  findEntityByProperties(Class<T> entityClass, Map<String, Object> propertiesMap) throws Exception
	{
		int start = -1;
		int limit = -1;
		return this.findEntityByPropertiesPage(entityClass, propertiesMap, start, limit);
	}

	/**
	 * 
	 * @date 2013-4-17
	 *       保存mainObjectClass的数据到数据库
	 *       xuyunlong
	 * @param mainObjectClass
	 *            ：要保存实体的class 例projInfo.class
	 * @param propertiesMap
	 *            :类属性来查找类Map 关键字来找数据
	 * @param sourceMapModel
	 *            :数据源Map
	 * @param classFieldMapping
	 *            :map<类名,"属性"> 是指当类属性为实体时来查到到这个实体，实体.属性
	 * @param entityIdentifier
	 *            ：字符串前台字段的Key 和mainObject对应该的字符串
	 * @return object :返回操作的类
	 * @throws Exception
	 */
	@Override
	public <T> T updateMainEntity(Class<T> mainObjectClass, Map<String, Object> propertiesMap, Map sourceMapModel,
			Map<String, String> classFieldMapping, String... entityIdentifier) throws Exception
	{
		StringBuffer sb = new StringBuffer();
		if (entityIdentifier.length > 0) {
			for (String ei : entityIdentifier)
			{
				sb.append(ei + ".");
			}
		}
		sourceMapModel.remove(sb.toString() + "id");
		T mainObject = this.getNewOrUpdateObject(mainObjectClass, propertiesMap);
		this.copyAndOverrideExistedValueFromStringMap(sourceMapModel, mainObject, classFieldMapping, entityIdentifier);
		this.getHibernateTemplate().saveOrUpdate(mainObject);
		return mainObject;
	}

	/**
	 * 
	 * @date 2013-4-17
	 *       更新实体的一个属性对象
	 *       xuyunlong
	 * @param mainObject
	 *            ：主实体对照
	 * @param toUpdatedObjectFieldName
	 *            :主实体的属性
	 * @param toMainObjectFieldName
	 *            :主实体中某个属性的对象 相对主实体的属性
	 *            @ sourceMapModel :数据源Map
	 * @param classFieldMapping
	 *            :map<类名,"属性"> 是指当类属性为实体时来查到到这个实体，实体.属性
	 * @param entityIdentifier
	 *            ：字符串前台字段的Key 和mainObject对应该的字符串
	 * @return object :返回操作的类
	 * @throws Exception
	 */
	@Override
	public <T,V> V updateOneToOneEntity(T mainObject, String toUpdatedObjectFieldName,
			String toMainObjectFieldName, Map sourceMapModel, Map<String, String> classFieldMapping,
			String... entityIdentifier) throws Exception
	{
		StringBuffer sb = new StringBuffer();
		if (entityIdentifier.length > 0) {
			for (String ei : entityIdentifier)
			{
				sb.append(ei + ".");
			}
		}
		sourceMapModel.remove(sb.toString() + "id");
		Assert.assertNotNull(mainObject);
		PropertyDescriptor toUpdatedObjectFieldNamePD = BeanUtils.getPropertyDescriptor(mainObject.getClass(),
				toUpdatedObjectFieldName);
		Method toUpdatedObjectFieldNameReadMethod = toUpdatedObjectFieldNamePD.getReadMethod();
		V updatedObject = (V)toUpdatedObjectFieldNameReadMethod.invoke(mainObject);
		Class<V> updatedObjectClass = (Class<V>)toUpdatedObjectFieldNameReadMethod.getReturnType();
		PropertyDescriptor toMainObjectFieldNamePD = BeanUtils.getPropertyDescriptor(updatedObjectClass,
				toMainObjectFieldName);
		Method toMainObjectFieldNameMethod = toMainObjectFieldNamePD.getWriteMethod();
		if (null == updatedObject)
		{
			updatedObject = updatedObjectClass.newInstance();
		}
		this.copyAndOverrideExistedValueFromStringMap(sourceMapModel, updatedObject, classFieldMapping, entityIdentifier);
		toMainObjectFieldNameMethod.invoke(updatedObject, mainObject);
		this.saveOrUpdateEntity(updatedObject);
		return updatedObject;
	}

	/**
	 * 
	 * @date 2013-4-17
	 *       获得要更新对象，如果数据库中没有，则新建一个
	 *       xuyunlong
	 * @param entityClass
	 *            ：主实体对象
	 * @param propertiesMap
	 *            :属性，值。查找对象
	 * @return object :返回操作的类
	 * @throws Exception
	 * 
	 */
	@Override
	public <T> T getNewOrUpdateObject(Class<T> entityClass, Map<String, Object> propertiesMap) throws Exception
	{
		T obj = null;
		if (propertiesMap != null) {
			List<T> l = this.findEntityByProperties(entityClass, propertiesMap);
			if (l.size() > 0) {
				obj = l.get(0);
			} else {
				obj = entityClass.newInstance();
			}
		} else {
			obj = entityClass.newInstance();
		}
		return obj;
	}

	/**
	 * 
	 * @date 2013-4-17
	 *       查对实体数量。通过属性Map
	 *       xuyunlong
	 * @param entityClass
	 *            ：主实体对象
	 * @param propertiesMap
	 *            :属性，值。查找对象
	 * @return integer :返回操作的类
	 * @throws Exception
	 */
	@Override
	public <T> Integer getEntityByPropertiesRowCount(Class<T> entityClass, Map<String, Object> propertiesMap)
			throws Exception
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
		Set<String> propertiesSet = propertiesMap.keySet();
		for (String propertyName : propertiesSet)
		{
			Object value = propertiesMap.get(propertyName);
			criteria.add(Restrictions.eq(propertyName, value));
		}
		criteria.setProjection(Projections.rowCount());
		return (Integer) this.getHibernateTemplate().findByCriteria(criteria).get(0);
	}

	/**
	 * 
	 * @date 2013-4-17
	 *       分页查找
	 *       xuyunlong
	 * @param entityClass
	 *            ：主实体对象
	 * @param propertiesMap
	 *            :属性，值。查找对象
	 * @param start
	 *            :开始
	 * @param limit
	 *            :条数
	 * @return List
	 * @throws Exception
	 */
	@Override
	public <T> List<T> findEntityByPropertiesPage(Class<T> entityClass, Map<String, Object> propertiesMap, int start, int limit)
			throws Exception
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
		Set<String> propertiesSet = propertiesMap.keySet();
		for (String propertyName : propertiesSet)
		{
			Object value = propertiesMap.get(propertyName);
			criteria.add(Restrictions.eq(propertyName, value));
		}
		return this.getHibernateTemplate().findByCriteria(criteria, start, limit);
	}

	/**
	 * 
	 * @date 2013-4-17
	 *       通过HSQL来查找
	 *       xuyunlong
	 * @param hsql
	 *            ：HSQL语句
	 * @param values
	 *            :值
	 * @return List
	 * @throws Exception
	 */
	@Override
	public <T> List<T> findResultsByHSQL(String hsql, Object... values) throws Exception
	{
		return this.getHibernateTemplate().find(hsql, values);
	}

	/**
	 * 
	 * @date 2013-4-17
	 *       通过HSQL数量
	 *       xuyunlong
	 * @param hsql
	 *            ：HSQL语句
	 * @param values
	 *            :值
	 * @return List
	 * @throws Exception
	 */
	@Override
	public Integer getResultsByHSQLRowCount(String countHSql, Object... values) throws Exception
	{
		return (Integer) this.findResultsByHSQL(countHSql, values).get(0);
	}

	/**
	 * 
	 * @date 2013-4-17
	 *       分页查找
	 *       xuyunlong
	 * @param hsql
	 *            ：查询语句
	 * @param start
	 *            :开始
	 * @param limit
	 *            :条数
	 * @param values
	 *            ：值
	 * @return List
	 * 
	 * @throws Exception
	 */

	@Override
	public <T> List<T> findResultsByHSQLPage(final String hsql, final int start, final int limit, final Object... values)
			throws Exception
	{
		return this.getHibernateTemplate().executeFind(new HibernateCallback<List<Object>>() {
			@Override
			public List<Object> doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query queryObject = session.createQuery(hsql);
				if (values != null)
				{
					for (int i = 0; i < values.length; i++)
					{
						queryObject.setParameter(i, values[i]);
					}
				}
				queryObject.setFirstResult(start);
				queryObject.setMaxResults(limit);
				return queryObject.list();
			}
		});
	}

	@Override
	public <T> List<T> findResultsByNamedParamHSQL(String hsql, String[] paramNames, Object[] values) throws Exception
	{
		return this.getHibernateTemplate().findByNamedParam(hsql, paramNames, values);
	}

	@Override
	public Integer getResultsByNamedParamHSQLRowCount(String countHsql, String[] paramNames, Object[] values)
			throws Exception
	{
		return (Integer) this.getHibernateTemplate().findByNamedParam(countHsql, paramNames, values).get(0);
	}

	@Override
	public <T> List<T> findResultsByNamedParamHSQLPage(final String hsql, final String[] paramNames, final Object[] values,
			final int start, final int limit) throws Exception
	{
		final int paramsLen = paramNames.length;
		final int valuesLen = values.length;
		Assert.assertEquals(paramsLen, valuesLen);

		return this.getHibernateTemplate().executeFind(new HibernateCallback<List<Object>>() {
			@Override
			public List<Object> doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query queryObject = session.createQuery(hsql);
				if (values != null)
				{
					for (int i = 0; i < valuesLen; i++)
					{
						queryObject.setParameter(paramNames[i], values[i]);
					}
				}
				queryObject.setFirstResult(start);
				queryObject.setMaxResults(limit);
				return queryObject.list();
			}
		});
	}
	@Override
	public <T> T copyAndOverrideExistedValueFromJSONObject(JSONObject jsonObj, T targetObject,Map<String, String> classFieldMapping, String... entityIdentifier)
	throws Exception{
		Map<String,String>  sourceMapModel = new HashMap<String,String>();
		Iterator iterKeys = jsonObj.keys();
		while(iterKeys.hasNext()){
			String key = iterKeys.next().toString().trim();
			String value = StringUtil.nullToString(jsonObj.getString(key)).trim();
			sourceMapModel.put(key, value);
		}
		this.copyAndOverrideExistedValueFromStringMap(sourceMapModel, targetObject, classFieldMapping, entityIdentifier);
		return targetObject;
	}
	@Override
	public <T> Collection<T> saveOrUpdateEntitiesByJSONArrayString(Class<T> saveEntityClass,String jsonArrayString,Map<String, String> classFieldMapping, String... entityIdentifier)
	throws Exception{
		Collection<T> entities = this.getEntitiesByJSONArrayString(saveEntityClass, jsonArrayString, classFieldMapping, entityIdentifier);
		this.saveOrUpdateAllEntities(entities);
		return entities;
	}
	public <T> T copyAndOverrideExistedValueFromJSONObject(JSONObject jsonObj, T targetObject, Map<String, String> classFieldMapping,
			boolean withUserInfo, String... entityIdentifier) throws Exception {
		Map<String, String> sourceMapModel = new HashMap<String, String>();
		Iterator iterKeys = jsonObj.keys();
		while (iterKeys.hasNext()) {
			String key = iterKeys.next().toString().trim();
			String value = StringUtil.nullToString(jsonObj.getString(key)).trim();
			sourceMapModel.put(key, value);
		}
		this.copyAndOverrideExistedValueFromStringMap(sourceMapModel, targetObject, classFieldMapping, withUserInfo, entityIdentifier);
		return targetObject;
	}
	
	public <T> Collection<T> getEntitiesByJSONArrayString(Class<T> saveEntityClass,String jsonArrayString,Map<String, String> classFieldMapping, String... entityIdentifier)
	throws Exception{
		List<T> c = new ArrayList<T>();
		JSONArray jsonArray = new JSONArray(jsonArrayString);
		for(int i=0;i<jsonArray.length();i++){
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			T targetObject = saveEntityClass.newInstance();
			this.copyAndOverrideExistedValueFromJSONObject(jsonObj, targetObject, classFieldMapping, entityIdentifier);
			c.add(targetObject);
		}
		return c;
	}
	/**
	 * 
	 * @date 2013-4-17
	 *       xuyunlong
	 *       对象属性值拷贝
	 * @param sourceObject
	 *            ：原对象
	 * @param targetObject
	 *            ：目标对象
	 * @return Object：目标对象
	 * @throws Exception
	 */
	@Override
	public <T> T copyAndOverrideExistedValueFromObject(Object sourceObject, T targetObject) throws Exception
	{
		Map<String, String> sourceMapModel = new HashMap<String, String>();
		PropertyDescriptor[] propertyDescriptors_source = BeanUtils.getPropertyDescriptors(sourceObject.getClass());
		for (PropertyDescriptor propertyDescriptor_source : propertyDescriptors_source)
		{
			// 获取读get方法
			Method readMethod = propertyDescriptor_source.getReadMethod();
			if (null == readMethod)
				throw new Exception(sourceObject.getClass().getName() + "中字段 < " + propertyDescriptor_source.getName()
						+ " > 不存在read方法");
			Class<?> clazz = readMethod.getReturnType();
			// 调用(get)方法
			Object obj = readMethod.invoke(sourceObject);
			Object value = null;
			if (clazz.getName().startsWith(("com.business.entity"))) {
				if (null != obj) {
					value = clazz.getMethod("getId").invoke(obj);
				}
			} else {
				value = obj;
			}
			System.out.println(propertyDescriptor_source.getName()+":"+
					StringUtil.empty2Other(StringUtil.nullToString(value), null));
			sourceMapModel.put(propertyDescriptor_source.getName(),
					StringUtil.empty2Other(StringUtil.nullToString(value), null));
		}
		this.copyAndOverrideExistedValueFromStringMap(sourceMapModel, targetObject, null);
		return targetObject;
	}
	/**
	 * 
	 * @date 2013-4-17
	 *       xuyunlong
	 *       对象属性值拷贝
	 * @param sourceObject
	 *            ：原对象
	 * @param targetObject
	 *            ：目标对象
	 * @return Object：目标对象
	 * @throws Exception
	 */
	@Override
	public <T> T copyAndOverrideExistedValueFromObjectNoSet(Object sourceObject, T targetObject) throws Exception
	{
		Map<String, String> sourceMapModel = new HashMap<String, String>();
		PropertyDescriptor[] propertyDescriptors_source = BeanUtils.getPropertyDescriptors(sourceObject.getClass());
		for (PropertyDescriptor propertyDescriptor_source : propertyDescriptors_source)
		{
			// 获取读get方法
			Method readMethod = propertyDescriptor_source.getReadMethod();
			if (null == readMethod)
				throw new Exception(sourceObject.getClass().getName() + "中字段 < " + propertyDescriptor_source.getName()
						+ " > 不存在read方法");
			Class<?> clazz = readMethod.getReturnType();
			// 调用(get)方法
			Object obj = readMethod.invoke(sourceObject);
			Object value = null;
			if (clazz.getName().indexOf(".entity.")>0) {
				if (null != obj) {
					value = clazz.getMethod("getId").invoke(obj);
				}
			} else {
				value = obj;
			}
			if(!clazz.getName().startsWith("java.util.Set")){
			sourceMapModel.put(propertyDescriptor_source.getName(),
					StringUtil.empty2Other(StringUtil.nullToString(value), null));
			}
		}
		this.copyAndOverrideExistedValueFromStringMap(sourceMapModel, targetObject, null,false);
		return targetObject;
	}
	/**
	 * @method copyAndOverrideExistedValueFromMap(这里用一句话描述这个方法的作用)
	 *         (这里描述这个方法适用条件 – 可选) 给目标对象赋值，值从MAP中取
	 * @param sourceMapModel
	 *            ：数据源
	 * @param targetObject
	 *            ：目标对象
	 * @return
	 * @throws Exception
	 * @returnType Object
	 * @exception
	 * @since 1.0.0
	 **/
	public <T> T copyAndOverrideExistedValueFromMap(Map<String, ? extends Object> sourceMapModel, T targetObject)
			throws Exception
	{
		PropertyDescriptor[] propertyDescriptors_target = BeanUtils.getPropertyDescriptors(targetObject.getClass());
		Set<String> overrideFieldsName = sourceMapModel.keySet();
		for (String overrideFieldName : overrideFieldsName)
		{
			for (PropertyDescriptor propertyDescriptor_target : propertyDescriptors_target)
			{
				String name_target = propertyDescriptor_target.getName();
				if (overrideFieldName.equalsIgnoreCase(name_target))
				{
					Object value = sourceMapModel.get(overrideFieldName);
					// 获取写set方法
					Method writeMethod = propertyDescriptor_target.getWriteMethod();
					// 调用(set)方法
					if (null == writeMethod)
						break;
					try
					{
						writeMethod.invoke(targetObject, value);
					} catch (Exception e)
					{
						System.out.println("异常字段：" + overrideFieldName);
						e.printStackTrace();
						throw new Exception("字段【" + overrideFieldName + "】覆写异常");
					}
					break;
				}
			}
		}
		return targetObject;
	}

	public <T> T copyAndOverrideExistedValueFromStringMap(Map<String,String> sourceMapModel, T targetObject,
			Map<String, String> classFieldMapping, String... entityIdentifier) throws Exception {
		return copyAndOverrideExistedValueFromStringMap(sourceMapModel, targetObject, classFieldMapping, true, entityIdentifier);
	}

	/**
	 * 
	 * @date 2014-10-28
	 *       更新目标对象的值
	 *       tpf
	 * @param sourceMapModel
	 *            ：数据源
	 * @param targetObject
	 *            ：目标对象
	 * @param classFieldMapping
	 *            ：查找类对象的KEY例<CustInfo,custname>
	 * @param withUserInfo
	 *            : 是否需要附加用户信息
	 * @param entityIdentifier
	 *            :map中KEY中标识部份和目标对象相对应
	 * @return
	 * @throws Exception
	 */
	public <T> T copyAndSetValueFromStringMap(Map<String,String> sourceMapModel, T targetObject,
			Map<String, String> classFieldMapping, String... entityIdentifier) throws Exception
	{
		Class targetClass = targetObject.getClass();
		String targetClassName = targetClass.getName();
		PropertyDescriptor[] propertyDescriptors_target = BeanUtils.getPropertyDescriptors(targetObject.getClass());
		Set<String> overrideFieldsName = (Set<String>) sourceMapModel.keySet();
		Set<String> notOverrideFieldsName = new HashSet<String>();
		StringBuffer sb = new StringBuffer();
		if (entityIdentifier.length > 0) {
			for (String ei : entityIdentifier)
			{
				sb.append(ei + ".");
			}
		}
		String entityIdentifierStr = sb.toString();
		for (PropertyDescriptor propertyDescriptor_target : propertyDescriptors_target)
		{
			String name_target = propertyDescriptor_target.getName();
			if ("class".equals(name_target))
				continue;
			Method readMethod = propertyDescriptor_target.getReadMethod();
			if(null == readMethod){
				if(log.isWarnEnabled()){
					log.warn(propertyDescriptor_target.getName());
				}
				continue;
			}
			Class<?> targetFieldClass = readMethod.getReturnType();
			
			String targetFieldClassName = targetFieldClass.getName();
			boolean isSuccessField = false;
			if ("boolean".equals(targetFieldClassName))
			{
				targetFieldClass = Boolean.class;
			}
			if ("short".equals(targetFieldClassName))
			{
				targetFieldClass = Short.class;
			}
			if ("long".equals(targetFieldClassName))
			{
				targetFieldClass = Long.class;
			}
			if ("int".equals(targetFieldClassName))
			{
				targetFieldClass = Integer.class;
			}
			if ("float".equals(targetFieldClassName))
			{
				targetFieldClass = Float.class;
			}
			if ("double".equals(targetFieldClassName))
			{
				targetFieldClass = Double.class;
			}

			targetFieldClassName = targetFieldClass.getName();
			// if(!targetFieldClass.isPrimitive())
			{
				//name_target = (entityIdentifierStr + name_target);
				for (String overrideFieldName : overrideFieldsName)
				{
					
					overrideFieldName = overrideFieldName.substring(overrideFieldName.indexOf(".")+1, overrideFieldName.length());
					if (overrideFieldName.equalsIgnoreCase(name_target))
					{
						Object valueObj = sourceMapModel.get(overrideFieldName);
						if (overrideFieldName.equals(entityIdentifierStr + "id") && (new Integer(0).equals(valueObj))) {
							break;
						}
						String value   = StringUtil.nullToString(valueObj);
						// 获取写set方法
						Method writeMethod = propertyDescriptor_target.getWriteMethod();
						// 调用(set)方法
						if ((null == writeMethod)) {
							writeMethod = targetClass.getMethod(
									"set"
											+ name_target.replaceFirst(name_target.substring(0, 1), name_target
													.substring(0, 1).toUpperCase()), targetFieldClass);
							if (null == writeMethod) {
								if (log.isErrorEnabled())
								{
									log.error("类型：" + targetFieldClassName + ",【" + targetClassName + "." + name_target
											+ "没有对应的set方法】");
									// throw new Exception();
								}
								break;
							}
						}
						try
						{
							if (StringUtils.isBlank(value)) {
								value = null;
								if("position".equalsIgnoreCase(name_target)){
									writeMethod.invoke(targetObject, 0);
									break;
								}
								System.out.println("targetObject"+targetObject +"-----value" + value);
								writeMethod.invoke(targetObject, value);
								isSuccessField = true;
								if (log.isWarnEnabled() && ResourceUtil.isDebug())
								{
									log.warn("【 " + targetObject.getClass().getSimpleName() + "." + name_target
											+ " 】被强制赋予了空值");
								}
								break;
							}
							Object castValue = null;

							// 判断是否为实体
							
							if (EntityUtil.isTenwaEntity(targetFieldClass))
							{
								try {

									String fieldName = null;
									if (null != classFieldMapping) {
										fieldName = classFieldMapping.get(targetFieldClass.getSimpleName());
									}

									if (StringUtils.isBlank(fieldName)) {
										castValue = this.getHibernateTemplate().get(targetFieldClass,
												StringUtil.nullToString(value));
									}
									else {
										Map<String, Object> propertiesMap = new HashMap<String, Object>();
										propertiesMap.put(fieldName, StringUtil.nullToString(value));
										List l = this.findEntityByProperties(targetFieldClass, propertiesMap);
										if (l.size() > 0) {
											castValue = l.get(0);
										}
									}

								} catch (Exception e) {
									e.printStackTrace();
//									if (log.isErrorEnabled())
//									{
//										log.error("类型：" + targetFieldClassName + ",【" + targetClassName + "."
//												+ name_target + "的主键类型必须为String】");
//										throw new Exception();
//									}
								}
							}
							else
							{
								try {
									if (targetFieldClass.isEnum()) {//对Enum类型进行特殊处理
										Class<? extends Enum> clazz= (Class<? extends Enum>) targetFieldClass;
										castValue = Enum.valueOf(clazz, value.toString());
									} else {
										if(!"string".equalsIgnoreCase(targetFieldClass.getSimpleName())){
											value = StringUtil.nullToString(value).replaceAll(",", "");
										}
										if (value.getClass().equals(targetFieldClass))
										{
											castValue = value;
										}
										else
										{
											castValue = targetFieldClass.getConstructor(value.getClass())
													.newInstance(value);
										}
									}
								} catch (Exception e) {
									if (log.isErrorEnabled())
									{
										log.error("【" + targetObject.getClass() + "." + name_target + "[类型"+targetFieldClass+"],必须具有public("
												+ value.getClass() + "){//方法体}】");
										System.out.println("【" + targetObject.getClass() + "." + name_target + "[类型"+targetFieldClass+"],必须具有public("
												+ value.getClass() + "){//方法体}】");
										throw new Exception();
									}
								}
							}

							writeMethod.invoke(targetObject, castValue);
							isSuccessField = true;
						} catch (Exception e)
						{
							e.printStackTrace();
							throw new Exception("name_target:"+name_target);
						}
						break;
					}
				}
			}
			if (!isSuccessField)
				notOverrideFieldsName.add(name_target);
		}
		if (log.isWarnEnabled() && ResourceUtil.isDebug()) {
			log.warn("对象【" + targetClassName + "】不能赋值的属性：");
			notOverrideFieldsName.remove(entityIdentifierStr + "creator");
			notOverrideFieldsName.remove(entityIdentifierStr + "modificator");
			notOverrideFieldsName.remove(entityIdentifierStr + "create_date");
			notOverrideFieldsName.remove(entityIdentifierStr + "modify_date");
			for (String name : notOverrideFieldsName) {
				log.warn(">>>>>> " + name);
			}
		}
		User user = null;
		boolean isWithUser = true;
		try {
			user = (User) SecurityUtil.getPrincipal();
		} catch (Exception e) {
			isWithUser = false;
		}
		if(isWithUser){
			String systemDate = DateUtil.getSystemDateTime();
			if (StringUtils.isBlank(StringUtil.nullToString(targetClass.getMethod("getId").invoke(targetObject))))
			{
				Method creatorMethod = targetClass.getDeclaredMethod("setCreator", User.class);
				Method createDateMethod = targetClass.getDeclaredMethod("setCreateDate", String.class);
				creatorMethod.invoke(targetObject, user);
				createDateMethod.invoke(targetObject, systemDate);

			}
			else
			{
				Method modificatorMethod = targetClass.getDeclaredMethod("setModificator", User.class);
				Method modifyDateMethod = targetClass.getDeclaredMethod("setModifyDate", String.class);
				modificatorMethod.invoke(targetObject, user);
				modifyDateMethod.invoke(targetObject, systemDate);
			}
		}
		return targetObject;
	}
	
	@Override
	public <T> T copyAndOverrideExistedValueFromStringMap(Map<String,String> sourceMapModel, T targetObject,
			Map<String, String> classFieldMapping, boolean withUserInfo, String... entityIdentifier) throws Exception
	{

		Class targetClass = targetObject.getClass();
		String targetClassName = targetClass.getName();
		PropertyDescriptor[] propertyDescriptors_target = BeanUtils.getPropertyDescriptors(targetObject.getClass());
		Set<String> overrideFieldsName = (Set<String>) sourceMapModel.keySet();
		Set<String> notOverrideFieldsName = new HashSet<String>();
		StringBuffer sb = new StringBuffer();
		if (entityIdentifier.length > 0) {
			if(entityIdentifier[0]!=""){
				for (String ei : entityIdentifier)
				{
					sb.append(ei + ".");
				}
			}else{
				sb.append("");
			}
		}
		String entityIdentifierStr = sb.toString();
		for (PropertyDescriptor propertyDescriptor_target : propertyDescriptors_target)
		{
			String name_target = propertyDescriptor_target.getName();

			if ("class".equals(name_target))
				continue;
			Method readMethod = propertyDescriptor_target.getReadMethod();
			if(null == readMethod){
				if(log.isWarnEnabled()){
					log.warn(propertyDescriptor_target.getName());
				}
				continue;
			}
			Class<?> targetFieldClass = readMethod.getReturnType();
			
			String targetFieldClassName = targetFieldClass.getName();
			boolean isSuccessField = false;
			if ("boolean".equals(targetFieldClassName))
			{
				targetFieldClass = Boolean.class;
			}
			if ("short".equals(targetFieldClassName))
			{
				targetFieldClass = Short.class;
			}
			if ("long".equals(targetFieldClassName))
			{
				targetFieldClass = Long.class;
			}
			if ("int".equals(targetFieldClassName))
			{
				targetFieldClass = Integer.class;
			}
			if ("float".equals(targetFieldClassName))
			{
				targetFieldClass = Float.class;
			}
			if ("double".equals(targetFieldClassName))
			{
				targetFieldClass = Double.class;
			}

			targetFieldClassName = targetFieldClass.getName();
			// if(!targetFieldClass.isPrimitive())
			{
				name_target = (entityIdentifierStr + name_target);
				for (String overrideFieldName : overrideFieldsName)
				{
					if (overrideFieldName.equalsIgnoreCase(name_target))
					{
						Object valueObj = sourceMapModel.get(overrideFieldName);
						if (overrideFieldName.equals(entityIdentifierStr + "id") && (new Integer(0).equals(valueObj))) {
							break;
						}
						String value   = StringUtil.nullToString(valueObj);
						// 获取写set方法
						Method writeMethod = propertyDescriptor_target.getWriteMethod();
						// 调用(set)方法
						if ((null == writeMethod)) {
							writeMethod = targetClass.getMethod(
									"set"
											+ name_target.replaceFirst(name_target.substring(0, 1), name_target
													.substring(0, 1).toUpperCase()), targetFieldClass);
							if (null == writeMethod) {
								if (log.isErrorEnabled())
								{
									log.error("类型：" + targetFieldClassName + ",【" + targetClassName + "." + name_target
											+ "没有对应的set方法】");
									// throw new Exception();
								}
								break;
							}
						}
						try
						{
							if (StringUtils.isBlank(value)) {
								value = null;
								if("position".equalsIgnoreCase(name_target)){
									writeMethod.invoke(targetObject, 0);
									break;
								}
								writeMethod.invoke(targetObject, value);
								isSuccessField = true;
								if (log.isWarnEnabled() && ResourceUtil.isDebug())
								{
									log.warn("【 " + targetObject.getClass().getSimpleName() + "." + name_target
											+ " 】被强制赋予了空值");
								}
								break;
							}
							Object castValue = null;

							// 判断是否为实体
							
							if (EntityUtil.isTenwaEntity(targetFieldClass))
							{
								try {

									String fieldName = null;
									if (null != classFieldMapping) {
										fieldName = classFieldMapping.get(targetFieldClass.getSimpleName());
									}

									if (StringUtils.isBlank(fieldName)) {
										castValue = this.getHibernateTemplate().get(targetFieldClass,
												StringUtil.nullToString(value));
									}
									else {
										Map<String, Object> propertiesMap = new HashMap<String, Object>();
										propertiesMap.put(fieldName, StringUtil.nullToString(value));
										List l = this.findEntityByProperties(targetFieldClass, propertiesMap);
										if (l.size() > 0) {
											castValue = l.get(0);
										}
									}

								} catch (Exception e) {
									e.printStackTrace();
//									if (log.isErrorEnabled())
//									{
//										log.error("类型：" + targetFieldClassName + ",【" + targetClassName + "."
//												+ name_target + "的主键类型必须为String】");
//										throw new Exception();
//									}
								}
							}
							else
							{
								try {
									if (targetFieldClass.isEnum()) {//对Enum类型进行特殊处理
										Class<? extends Enum> clazz= (Class<? extends Enum>) targetFieldClass;
										castValue = Enum.valueOf(clazz, value.toString());
									} else {
										if(!"string".equalsIgnoreCase(targetFieldClass.getSimpleName())){
											value = StringUtil.nullToString(value).replaceAll(",", "");
										}
										if (value.getClass().equals(targetFieldClass))
										{
											castValue = value;
										}
										else
										{
											castValue = targetFieldClass.getConstructor(value.getClass())
													.newInstance(value);
										}
									}
								} catch (Exception e) {
									e.printStackTrace();
									if (log.isErrorEnabled())
									{
										log.error("【" + targetObject.getClass() + "." + name_target + "[类型"+targetFieldClass+"],必须具有public("
												+ value.getClass() + "){//方法体}】");
										System.out.println("【" + targetObject.getClass() + "." + name_target + "[类型"+targetFieldClass+"],必须具有public("
												+ value.getClass() + "){//方法体}】");
										throw new Exception();
									}
								}
							}

							writeMethod.invoke(targetObject, castValue);
							isSuccessField = true;
						} catch (Exception e)
						{
							e.printStackTrace();
							throw new Exception("name_target:"+name_target);
						}
						break;
					}
				}
			}
			if (!isSuccessField)
				notOverrideFieldsName.add(name_target);
		}
		if (log.isWarnEnabled() && ResourceUtil.isDebug()) {
			log.warn("对象【" + targetClassName + "】不能赋值的属性：");
			notOverrideFieldsName.remove(entityIdentifierStr + "creator");
			notOverrideFieldsName.remove(entityIdentifierStr + "modificator");
			notOverrideFieldsName.remove(entityIdentifierStr + "create_date");
			notOverrideFieldsName.remove(entityIdentifierStr + "modify_date");
			for (String name : notOverrideFieldsName) {
				log.warn(">>>>>> " + name);
			}
		}
		if (withUserInfo) {
			User user = null;
			boolean isWithUser = true;
			try {
				user = (User) SecurityUtil.getPrincipal();
			} catch (Exception e) {
				isWithUser = false;
			}
			if(isWithUser){
				String systemDate = DateUtil.getSystemDateTime();
				if (StringUtils.isBlank(StringUtil.nullToString(targetClass.getMethod("getId").invoke(targetObject))))
				{
					Method creatorMethod = targetClass.getDeclaredMethod("setCreator", User.class);
					Method createDateMethod = targetClass.getDeclaredMethod("setCreateDate", String.class);
					creatorMethod.invoke(targetObject, user);
					createDateMethod.invoke(targetObject, systemDate);

				}
				else
				{
					Method modificatorMethod = targetClass.getDeclaredMethod("setModificator", User.class);
					Method modifyDateMethod = targetClass.getDeclaredMethod("setModifyDate", String.class);
					modificatorMethod.invoke(targetObject, user);
					modifyDateMethod.invoke(targetObject, systemDate);
				}
			}
		}
		return targetObject;
	}
	
	/**
	 * 
	 * @date 2013-4-17
	 *       xuyunlong
	 *       将Map数据转为JSONObject对象
	 * @param map
	 *            ：
	 * @param allownNull
	 *            ：
	 * @return JSONObject：
	 * @throws Exception
	 */
	@Override
	public JSONObject getJsonObjectByParameterMap(Map map, boolean allownNull) throws Exception
	{
		JSONObject jsonObj = new JSONObject();
		for (Object key : map.keySet())
		{
			String name = StringUtil.nullToString(key);
			if (StringUtils.isBlank(name))
				continue;
			Object value = StringUtil.nullToString(map.get(key));
			if (!allownNull && StringUtils.isBlank(StringUtil.nullToString(value)))
			{
				continue;
			}
			JSONObject currentObj = new JSONObject();
			if (name.indexOf(".") > -1)
			{
				String[] names = name.split("\\.");
				int len = names.length;
				for (int i = 0; i < len; i++)
				{
					String attrName = names[i];
					if (0 == i)
					{
						if (!jsonObj.has(attrName))
						{
							jsonObj.put(attrName, new JSONObject());
						}
						currentObj = jsonObj.getJSONObject(attrName);
					}
					else if ((len - 1) == i)
					{
						currentObj.put(attrName, value);
					}
					else
					{
						if (!currentObj.has(attrName))
						{
							currentObj.put(attrName, new JSONObject());
						}
						currentObj = currentObj.getJSONObject(attrName);
					}
				}
			}
			else
			{
				jsonObj.put(name, value);
			}
		}
		return jsonObj;
	}
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.business.dao.BaseDao#updateOneToManyCollections(java.lang.Object, java.lang.String, java.lang.Class, java.lang.String, java.lang.String)
	 **/

	@Override
	public <T,V> Collection<V> updateOneToManyCollections(T OneToManyObj,
			String OneToManyFieldName, Class<V> ManyToOneObjClass,
			String ManyToOneFieldName, String jsonArrayStr, Map<String, String> classFieldMapping,
			boolean isRemovedRecord, String... entityIdentifier) throws Exception {
		Class OneToManyObjClass = OneToManyObj.getClass();
		// 获取onetomany的属性描述
		PropertyDescriptor OneToManyPD = BeanUtils.getPropertyDescriptor(OneToManyObjClass, OneToManyFieldName);
		// 获取manytoone的属性描述
		PropertyDescriptor ManyToOnePD = BeanUtils.getPropertyDescriptor(ManyToOneObjClass, ManyToOneFieldName);
		/*
		 * Field OneToManyField = OneToManyObjClass.getDeclaredField(OneToManyFieldName);
		 * Class fieldClazz = OneToManyField.getType(); // 得到field的class及类型全路径
		 * if(fieldClazz.isAssignableFrom(Set.class)) //【2】
		 * {
		 * Type fc = OneToManyField.getGenericType(); // 关键的地方，如果是List类型，得到其Generic的类型
		 * if(fc == null)
		 * {
		 * if(log.isErrorEnabled())
		 * {
		 * log.error("【字段"+OneToManyField.getName()+"必须使用Collection的泛型获取注入的实际类型】");
		 * throw new Exception();
		 * }
		 * }
		 * if(fc instanceof ParameterizedType) // 【3】如果是泛型参数的类型
		 * {
		 * ParameterizedType pt = (ParameterizedType) fc;
		 * Class genericClazz = (Class)pt.getActualTypeArguments()[0]; //【4】 得到泛型里的class类型对象。
		 * System.out.println(genericClazz);
		 * ManyToOneObjClass = genericClazz;
		 * ManyToOnePD = BeanUtils.getPropertyDescriptor(ManyToOneObjClass, ManyToOneFieldName);
		 * }
		 * }
		 */
		Set oldOneToManySet = (Set) OneToManyPD.getReadMethod().invoke(OneToManyObj);
		Map<String, Object> oldMap = new HashMap<String, Object>();
		// 查询出原有的equips
		for (Object obj : oldOneToManySet) {
			oldMap.put(ManyToOneObjClass.getMethod("getId").invoke(obj).toString(), obj);
		}
		Map[] newSetMaps = jsonMapper.readValue(jsonArrayStr, Map[].class);
		Set<String> updatedIds = new HashSet<String>();

		Method creatorMethod = ManyToOneObjClass.getDeclaredMethod("setCreator", User.class);
		Method createDateMethod = ManyToOneObjClass.getDeclaredMethod("setCreateDate", String.class);
		Method modificatorMethod = ManyToOneObjClass.getDeclaredMethod("setModificator", User.class);
		Method modifyDateMethod = ManyToOneObjClass.getDeclaredMethod("setModifyDate", String.class);

		User user = (User) SecurityUtil.getPrincipal();
		String systemDate = DateUtil.getSystemDateTime();

		Set newOneToManySet = new HashSet();
		for (int i = 0; i < newSetMaps.length; i++)
		{
			Object ManyToOneObj = null;
			Map sourceMapModel = newSetMaps[i];
			boolean isOld = false;
			String oldId = StringUtil.nullToString(sourceMapModel.get("id"));
			if (oldMap.containsKey(oldId))
			{
				updatedIds.add(oldId);
				ManyToOneObj = oldMap.get(oldId);
				isOld = true;
			}
			else
			{
				StringBuffer sb = new StringBuffer();
				if (entityIdentifier.length > 0) {
					for (String ei : entityIdentifier)
					{
						sb.append(ei + ".");
					}
				}
				sourceMapModel.remove(sb.toString() + "id");
				ManyToOneObj = ManyToOneObjClass.newInstance();
			}
			this.copyAndOverrideExistedValueFromStringMap(sourceMapModel, ManyToOneObj, classFieldMapping,
					entityIdentifier);
			if (isOld) {
				modificatorMethod.invoke(ManyToOneObj, user);
				modifyDateMethod.invoke(ManyToOneObj, systemDate);
			} else {
				creatorMethod.invoke(ManyToOneObj, user);
				createDateMethod.invoke(ManyToOneObj, systemDate);
			}
			ManyToOnePD.getWriteMethod().invoke(ManyToOneObj, OneToManyObj);
			newOneToManySet.add(ManyToOneObj);
		}
		// 删除集合中已经移除的items
		for (String oldId : oldMap.keySet())
		{
			if (!updatedIds.contains(oldId))
			{
				Object oldObj = oldMap.get(oldId);
				if (isRemovedRecord) {
					this.removeEntity(oldObj);
				} else {
					newOneToManySet.add(oldObj);
				}

			}
		}
		this.saveOrUpdateAllEntities(newOneToManySet);
		return newOneToManySet;
	}
	@Override
	public <T> Map<String, String> getEntityPropertiesToStringMapWithOtherEntityFields(T entity, Map<String, String> fieldClassMapping,Map<String, String> otherEntityFieldsMapping,
			String... entityIdentifier) throws Exception
	{
		JSONObject jsonObj = this.getEntityPropertiesToJsonEntityWithOtherEntityFields(entity, fieldClassMapping, otherEntityFieldsMapping, entityIdentifier);
		Map<String, String> StringMap = this.getStringMapByJsonObject(jsonObj);
		return StringMap;
	}
	@Override
	public <T> JSONArray getCollectionEntitiesPropertiesToJsonArrayWithOtherEntityFields(Collection<T>  entities,
			Map<String, String> fieldClassMapping,Map<String,String> otherEntityFieldsMapping, String... entityIdentifier) throws Exception {
		JSONArray jsonArray = new JSONArray();
		for (Object entity : entities) {
			JSONObject jsonEntity = this.getEntityPropertiesToJsonEntityWithOtherEntityFields(entity, fieldClassMapping,otherEntityFieldsMapping, entityIdentifier);
			jsonArray.put(jsonEntity);
		}
		return jsonArray;
	}

	@Override
	public <T>  String getCollectionEntitiesPropertiesToJsonArrayStringWithOtherEntityFields(Collection<T>  entities,
			Map<String, String> fieldClassMapping,Map<String,String> otherEntityFieldsMapping, String... entityIdentifier) throws Exception
	{
		return this.getCollectionEntitiesPropertiesToJsonArrayWithOtherEntityFields(entities, fieldClassMapping,otherEntityFieldsMapping, entityIdentifier).toString();
	}
  @Override
  public <T>  JSONObject getEntityPropertiesToJsonEntityWithOtherEntityFields(T entity, Map<String, String> fieldClassMapping,
		  Map<String,String> otherEntityFieldsMapping,String... entityIdentifier) throws Exception{
		StringBuffer sb = new StringBuffer();
		if (entityIdentifier.length > 0) {
			for (String ei : entityIdentifier)
			{
				if(!StringUtil.nullToString(ei).trim().isEmpty()){
					sb.append(ei + ".");
				}
			}
		}
		JSONObject  jsonEntity = this.getEntityPropertiesToJsonEntity(entity, fieldClassMapping, entityIdentifier);
		if(null != otherEntityFieldsMapping){
			for(String otherFieldsStr : otherEntityFieldsMapping.keySet()){
				String otherFieldKeyName =  otherEntityFieldsMapping.get(otherFieldsStr);
				if(otherFieldsStr.indexOf(".")>-1){
					jsonEntity.put(otherFieldKeyName, this.recursionEntityFieldValue(entity, otherFieldsStr));
				}
			}
		}
		return jsonEntity;
  }
  @Override
  public Object recursionEntityFieldValue(Object topEntity,String recursionFieldStr) throws Exception{
	  if(-1 < recursionFieldStr.indexOf(".")){
		  String[] recursionFieldStrArr =  recursionFieldStr.split("\\.");
		  String parentRecursionField = recursionFieldStrArr[0];
		  return this.recursionEntityFieldValue(this.recursionEntityFieldValue(topEntity, parentRecursionField), recursionFieldStr.substring(parentRecursionField.length()+1));
	  }
	  else{
		  return BeanUtils.getPropertyDescriptor(topEntity.getClass(), recursionFieldStr).getReadMethod().invoke(topEntity);
	  }
  }
  @Override  
  public <T> JSONObject getEntityPropertiesToJsonEntityWithCase(T entity, Map<String, String> fieldClassMapping,CaseEnum caseFlag,
			String... entityIdentifier) throws Exception{
		JSONObject jsonEntity = new JSONObject();
		StringBuffer sb = new StringBuffer();
		if (entityIdentifier.length > 0) {
			for (String ei : entityIdentifier)
			{
				if(!StringUtil.nullToString(ei).trim().isEmpty()){
					sb.append(ei + ".");
				}
			}
		}
		String prefixStr = sb.toString();
		PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(entity.getClass());
		for (PropertyDescriptor pd : pds)
		{
			Method readMethod = pd.getReadMethod();
			if (null == readMethod)
				continue;
			Class<?> returnType = readMethod.getReturnType();
			Object returnValue = readMethod.invoke(entity);
			if (null != returnValue)
			{
				String value = "";
				if (returnType.isAssignableFrom(Set.class))
				{
					continue;
				}
				else if (EntityUtil.isTenwaEntity(returnType))
				{
					String fieldName = null;
					if (null != fieldClassMapping) {
						fieldName = fieldClassMapping.get(returnType.getSimpleName());
					}
					if (StringUtils.isBlank(fieldName)) {
						fieldName = "id";
					}
					Method method = BeanUtils.getPropertyDescriptor(returnType, fieldName).getReadMethod();
					//System.out.println("####："+method.getName()+","+returnValue);
					value = StringUtil.nullToString(method.invoke(returnValue));
					
					if(EntityUtil.isTenwaDictDataEntity(returnType)){
						String rawName = BeanUtils.getPropertyDescriptor(returnType, "name").getReadMethod().invoke(returnValue).toString();
						jsonEntity.put("rawValue_"+prefixStr + pd.getName().toLowerCase(), rawName);
					}
					if(EntityUtil.isTenwaDictDataEntity(returnType)){
						String rawName = BeanUtils.getPropertyDescriptor(returnType, "name").getReadMethod().invoke(returnValue).toString();
						jsonEntity.put(prefixStr + pd.getName().toLowerCase()+"name", rawName);
					}
					if(EntityUtil.isTenwaUserEntity(returnType)){
						String rawName = BeanUtils.getPropertyDescriptor(returnType, "realname").getReadMethod().invoke(returnValue).toString();
						jsonEntity.put("rawValue_"+prefixStr + pd.getName().toLowerCase(), rawName);
					}
					if(EntityUtil.isTenwaDepEntity(returnType)){
						String rawName = BeanUtils.getPropertyDescriptor(returnType, "name").getReadMethod().invoke(returnValue).toString();
						jsonEntity.put("rawValue_"+prefixStr + pd.getName().toLowerCase(), rawName);
					}
					if(EntityUtil.isTenwaCustEntity(returnType)){
						String rawName = BeanUtils.getPropertyDescriptor(returnType, "custName").getReadMethod().invoke(returnValue).toString();
						jsonEntity.put(prefixStr + pd.getName().toLowerCase()+"name", rawName);
					}
				}
				else
				{
					if (returnType.getSimpleName().equalsIgnoreCase("double"))
					{
						value = MathUtil.decimal((Double) returnValue, 8);
					}
					else {
						value = returnValue.toString();
					}
				}
				String propertyName = pd.getName();
				if(null != caseFlag){
					switch(caseFlag){
						case UPPERCASE :{
							 propertyName = propertyName.toUpperCase();
							 break;
						}
						case LOWERCASE :{
							 propertyName = propertyName.toLowerCase();
							 break;
						}
						case NORMAL    :{
							 break;
						}
					}
				}
				jsonEntity.put(prefixStr+propertyName, value);
			}
		}
		return jsonEntity;
  }
	@Override
	public <T> JSONObject getEntityPropertiesToJsonEntity(T entity, Map<String, String> fieldClassMapping,
			String... entityIdentifier) throws Exception
	{
           return  this.getEntityPropertiesToJsonEntityWithCase(entity, fieldClassMapping, CaseEnum.LOWERCASE, entityIdentifier);
	}
	@Override 
	public Map<String,String> getStringMapByJsonObject(JSONObject jsonObj) throws Exception
	{
		Map<String, String> propertiesMap = new HashMap<String, String>();
		Iterator iter = jsonObj.keys();
		while(iter.hasNext()){
		   String  key   = (String) iter.next();
		   String  value = StringUtil.nullToString(jsonObj.getString(key));
		   propertiesMap.put(key, value);
		}
		return propertiesMap;
	}
	/**
	 * 
	 * @date 2013-4-17
	 *       xuyunlong
	 *       将实体对象转为Map
	 * @param entity
	 *            :实体对象
	 * @param fieldClassMapping
	 *            :实体中属性为体类则设置对象关系
	 * @param entityIdentifier
	 * @return
	 * @throws Exception
	 */
	@Override
	public <T> Map<String, String> getEntityPropertiesToStringMap(T entity, Map<String, String> fieldClassMapping,
			String... entityIdentifier) throws Exception
	{
		JSONObject jsonObj = this.getEntityPropertiesToJsonEntity(entity, fieldClassMapping, entityIdentifier);
		Map<String, String> StringMap = this.getStringMapByJsonObject(jsonObj);
		return StringMap;
	}

	/**
	 * 
	 * @date 2013-4-17
	 *       xuyunlong
	 *       实体集合对象转为JSONO数据
	 * @param entity
	 *            :实体集合对象
	 * @param fieldClassMapping
	 *            :实体中属性为体类则设置对象关系
	 * @param entityIdentifier
	 * @return
	 * @throws Exception
	 */
	@Override
	public <T> JSONArray getCollectionEntitiesPropertiesToJsonArray(Collection<T> entities,
			Map<String, String> fieldClassMapping, String... entityIdentifier) throws Exception {
		JSONArray jsonArray = new JSONArray();
		for (Object entity : entities) {
			JSONObject jsonEntity = this.getEntityPropertiesToJsonEntity(entity, fieldClassMapping, entityIdentifier);
			jsonArray.put(jsonEntity);
		}
		return jsonArray;
	}

	@Override
	public <T> String getCollectionEntitiesPropertiesToJsonArrayString(Collection<T> entities,
			Map<String, String> fieldClassMapping, String... entityIdentifier) throws Exception
	{
		return this.getCollectionEntitiesPropertiesToJsonArray(entities, fieldClassMapping, entityIdentifier).toString();
	}

	@Override
	public <T> void removeEntityById(Class<T> entityClass, String id) throws Exception {
		Object entity = this.findEntityByID(entityClass, id);
		this.removeEntity(entity);
	}

	@Override
	public void updateOrderPosition(String tablename, String currentId, String parentId, String currentPosition)
			throws Exception
	{
		String updateSql_self = "";
		String updateSql_others = "";
		String sql_create_table = "";
		String sql_drop_table = "";
        boolean cfalg=true;
        boolean dfalg=true;
		String wherePid = "";
		String andPid = "";
		if (null != parentId) {
			wherePid = " where pid_ = ?";
			andPid = " and   pid_ = ?";
		}

		if ("-1".equals(currentPosition))
		{
			updateSql_self = "  update " + tablename + " set position_ = 1 where id_=?";
			updateSql_others = " update " + tablename
					+ " set position_ = ( (case when position_ is null then 0 else position_ end) + 1 ) where  id_!='"
					+ currentId + "'" + andPid;
		}
		else if ("999999".equals(currentPosition))
		{
			String queryMaxPositionSql = "select ((case when max(position_) is null then 0 else max(position_) end)+ 1) as pos from  "
					+ tablename + wherePid;
			if (ResourceUtil.getDBType().indexOf("mysql".toUpperCase()) > -1)
			{
				sql_create_table = " create table " + tablename + "_temp as " + queryMaxPositionSql;
				updateSql_self = " update " + tablename + " set position_ = (select pos from " + tablename
						+ "_temp) where id_=?";
				sql_drop_table = " drop table " + tablename + "_temp";
			}
			else
			{
				updateSql_self = " update " + tablename + " set position_ = (" + queryMaxPositionSql + ") where id_=?";
				cfalg=false;
			}
		}
		else
		{
			dfalg=false;
			updateSql_self = " update " + tablename + " set position_ = (" + (currentPosition) + "+1) where id_=?";
			updateSql_others = " update " + tablename + " set position_ = (position_ + 1) where   position_>? and id_!=? " + andPid;
		    
		}
		if (!"".equals(sql_create_table))
		{
			this.getJdbcTemplate().update(sql_create_table,parentId);
		}
		if (!"".equals(updateSql_self))
		{
			if(cfalg){
				this.getJdbcTemplate().update(updateSql_self,currentId);
			}else{
				this.getJdbcTemplate().update(updateSql_self,parentId,currentId);
			}	
		}
		if (!"".equals(sql_drop_table))
		{
			this.getJdbcTemplate().update(sql_drop_table);
		}
		if (!"".equals(updateSql_others))
		{
			if(dfalg){
				this.getJdbcTemplate().update(updateSql_others,currentId,parentId);
			}
			else{
				if(andPid!=null && andPid.length()>0){
				    this.getJdbcTemplate().update(updateSql_others,currentPosition,currentId,parentId);
				}else{
					this.getJdbcTemplate().update(updateSql_others,currentPosition,currentId);
				}
			}
			
		}
	}
	 @Override
	 public void updateBySql(String sql ,Object... values) throws Exception{
		 this.getJdbcTemplate().update(sql, values);
	 }
	 @Override
	 public List<Map<String,Object>> queryListBySql( String sql ,Object... values) throws Exception{
		 return this.getJdbcTemplate().queryForList(sql, values);
	 }

	@Override
	public void hisEntityByProcedures(final String fromtablename, final String totablename,
			final String condition, Map<String, String> otherproperty,List<String>excludeColumn)
			throws Exception {
		   // String otherconditionkey="his_id";
		    //String otherconditionvalue="sys_guid()";
		  String otherkey="";
		  String othervalue="";
		  String e_cloumn="";
		 
		if(null!=excludeColumn){
			for(String column:excludeColumn){
				if(!"".equals(e_cloumn)){
					e_cloumn+=",";
				}
				e_cloumn+="'"+column.toLowerCase()+"'";
			}
		   }
		    if(null!=otherproperty){
		       for(String key :otherproperty.keySet()){
		    	  if(!"".equals(otherkey)){
		    		  otherkey+=",";
		    		  othervalue+=",";
		    	  }  
		    	  otherkey+=key;
		    	  if("id".equals(otherproperty.get(key).toString())){
		    		  othervalue+="SQLSERVER".equals(ResourceUtil.getDBType())?"replace(newid(), '-', '')":"sys_guid()";//选择的列;
		    	  }else{
		    	      othervalue+="'"+otherproperty.get(key).toString()+"'";
		    	  }
		    }}
		   this.callhisEntityProcedures(fromtablename, totablename, condition, otherkey, othervalue, e_cloumn);
		
	}
	public void callhisEntityProcedures(final String fromtablename, final String totablename,
			final String condition,final String otherPropertityKey,final String otherPropertityValue,final String excludeColumn)throws Exception{
		 try {		    	
		    	String returnValue = (String) this.getJdbcTemplate().execute(  
		    		     new CallableStatementCreator() {  
		    		        public CallableStatement createCallableStatement(Connection con) throws SQLException {  
		    		           String storedProc = "{call proc_his_table (?,?,?,?,?,?,?)}";// 调用的sql  		    		           
		    		           CallableStatement cs = con.prepareCall(storedProc);  
		    		           cs.setString(1, fromtablename);// 设置输入参数的值  
		    		           cs.setString(2, totablename);// 设置输入参数的值  
		    		           cs.setString(3, condition);// 设置输入参数的值  
		    		           cs.setString(4, otherPropertityKey);// 设置输入参数的值  
		    		           cs.setString(5, otherPropertityValue);// 设置输入参数的值  
		    		           cs.setString(6, excludeColumn);// 设置输入参数的值  
		    		           cs.registerOutParameter(7,OracleTypes.VARCHAR);// 注册输出参数的类型  
		    		           return cs;  
		    		        }  
		    		     }, new CallableStatementCallback() {  
		    		         public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {  
		    		           cs.execute();  
		    		           return cs.getString(7);// 获取输出参数的值  
		    		     }  
		    	});  
		    	if(null!=returnValue&&(!"".endsWith(returnValue))){
		    		throw new BusinessException(returnValue);
		    	}
		    } catch (Exception e) {
					throw new BusinessException("his表"+fromtablename+"到"+totablename+"出错  内容:"+e.getMessage());
			}
	} 
	
	@Override
	public RedisSerializer<String> getRedisSerializer() throws Exception   {
		return this.getRedisTemplate().getStringSerializer();  
	}
	
	@Override
	public String getByRedis(final String keyId) throws Exception {
		RedisTemplate<String, String> redisTemplate = getRedisTemplate();
		return redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection)
					throws DataAccessException {
				try {
					RedisSerializer<String> serializer = getRedisSerializer();
					 byte[] key  = serializer.serialize(keyId);  
		             byte[] value = connection.get(key);
		            return serializer.deserialize(value);
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
		});
	}
	@Override
	public void deleteByRedis(String keyId) throws Exception {
		getRedisTemplate().delete(keyId);
	}
	@Override
	public void deleteByRedis(List<String> keyIds) throws Exception {
		getRedisTemplate().delete(keyIds);
	}
	
	@Override
	public void addByRedis(final Map<String, String> valueMap) throws Exception {
		if(
			!getRedisTemplate().execute(new RedisCallback<Boolean>() {
				@Override
				public Boolean doInRedis(RedisConnection connection)
						throws DataAccessException {
					try{
						RedisSerializer<String> serializer = getRedisSerializer();  
						for(String key : valueMap.keySet()){
							String value  = valueMap.get(key);
							byte[]keyByte =  serializer.serialize(key);
							byte[]valueByte = serializer.serialize(value);
							connection.setNX(keyByte, valueByte);
						}
						return true;
					}catch(Exception e){
						e.printStackTrace();
						return false;
					}
				}
			})
		){
			throw new Exception("redis添加数据失败");
		}
	}
	
	@Override
	public Long pushByRedis(String key, List<String> values) throws Exception {
		Long pushSize = 0l;
		for(String value : values){
			pushSize = getRedisTemplate().opsForList().leftPush(key, value);
		}
		return pushSize;
	}
	
	@Override
	public String popByRedis(String key) throws Exception {
		return getRedisTemplate().opsForList().leftPop(key);
	}
	@Override
	public Long inByRedis(String key, List<String> values) throws Exception {
		Long pushSize = 0l;
		for(String value : values){
			pushSize = getRedisTemplate().opsForList().rightPush(key, value);
		}
		return pushSize;
	}
	@Override
	public String outByRedis(String key) throws Exception {
		return getRedisTemplate().opsForList().rightPop(key);
	}
	
	@Override
	public String indexForRedisList(String key, long index) throws Exception {
		 return getRedisTemplate().opsForList().index(key, index);  
	}
	@Override
	public Long length(String key) throws Exception {
		return getRedisTemplate().opsForList().size(key);  
	}
	@Override
	public List<String> rangeForListByRedis(String key, int start, int end)
			throws Exception {
		return getRedisTemplate().opsForList().range(key, start, end);
	}
	@Override
	public void removeByRedis(String key, long i, String value)
			throws Exception {
		getRedisTemplate().opsForList().remove(key, i, value);
	}
	@Override
	public void setForRedisList(String key, long index, String value)
			throws Exception {
		getRedisTemplate().opsForList().set(key, index, value);
	}
	@Override
	public void trimForRedisList(String key, long start, int end)
			throws Exception {
		getRedisTemplate().opsForList().trim(key, start, end);
	}
}
