package com.tenwa.business.serviceImpl;

/**
 * 项目名称：    系统名称
 * 包名：              com.business.serviceImpl
 * 文件名：         BaseServiceImpl.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-1-15-下午03:02:54
 * Copyright：2013XX公司-版权所有
 **/


import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.dao.DataAccessException;

import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.service.BaseService;


/**
 * 类名称： BaseServiceImpl
 * 类描述：
 * 创建人： Administrator
 * 修改人： Administrator
 * 修改时间：2013-1-15 下午03:02:54
 * 修改备注：
 * 
 * @version 1.0.0
 **/
public abstract class AbstractBaseServiceImpl implements BaseService {

	

	public abstract BaseDao getBussinessDao() throws Exception;

	public <T> void saveEntity(T entity) throws Exception {
		this.getBussinessDao().saveEntity(entity);
	}

	public <T> void saveAllEntities(Collection<T> entities) throws Exception {
		this.getBussinessDao().saveAllEntities(entities);
	}

	public <T> void updateEntity(T entity) throws Exception {
		this.getBussinessDao().updateEntity(entity);
	}

	public <T> void updateAllEntities(Collection<T> entities) throws Exception {
		this.getBussinessDao().updateAllEntities(entities);
	}

	public <T> void mergeEntity(T entity) throws Exception {
		this.getBussinessDao().mergeEntity(entity);
	}

	public <T> void mergeAllEntities(Collection<T> entities) throws Exception {
		this.getBussinessDao().mergeAllEntities(entities);
	}

	public <T> void saveOrUpdateEntity(T entity) throws Exception {
		this.getBussinessDao().saveOrUpdateEntity(entity);
	}

	public <T> void saveOrUpdateAllEntities(Collection<T> entities) throws Exception {
		this.getBussinessDao().saveOrUpdateAllEntities(entities);
	}

	public <T> void removeEntity(T entity) throws Exception {
		this.getBussinessDao().removeEntity(entity);
	}

	public <T> void removeEntityById(Class<T> entityClass, String id) throws Exception {
		this.getBussinessDao().removeEntityById(entityClass, id);
	}

	public <T> void removeAllEntites(Collection<T> entities) throws Exception {
		this.getBussinessDao().removeAllEntites(entities);
	}

	public int updateByHSQL(String hsql, Object... values) throws Exception {
		return this.getBussinessDao().updateByHSQL(hsql, values);
	}

	public void updateFlush() throws Exception {
		this.getBussinessDao().updateFlush();
	}

	public <T> T getNewOrUpdateObject(Class<T> clazz, Map<String, Object> propertiesMap) throws Exception {
		return this.getBussinessDao().getNewOrUpdateObject(clazz, propertiesMap);
	}
	
	@Override
	public <T> T copyAndOverrideExistedValueFromJSONObject(JSONObject jsonObj, T targetObject, Map<String, String> classFieldMapping,
			boolean withUserInfo, String... entityIdentifier) throws Exception {
		return this.getBussinessDao().copyAndOverrideExistedValueFromJSONObject(jsonObj, targetObject, classFieldMapping,withUserInfo, entityIdentifier);
		
	} 
	
	public <T> String getEntityPropertiesToJsonEntityString(T entity,
			Map<String, String> fieldClassMapping, String... entityIdentifier) throws Exception {
		return this.getBussinessDao().getEntityPropertiesToJsonEntity(entity, fieldClassMapping, entityIdentifier).toString();
	}

	// 高级更新
	public <T> T copyAndOverrideExistedValueFromJSONObject(JSONObject jsonObj, T targetObject, Map<String, String> classFieldMapping,
			String... entityIdentifier)
			throws Exception {
		return this.getBussinessDao().copyAndOverrideExistedValueFromJSONObject(jsonObj, targetObject, classFieldMapping, entityIdentifier);
	}

	public <T> T copyAndOverrideExistedValueFromMap(Map<String, ? extends Object> sourceMapModel, T targetObject)
			throws Exception {
		return this.getBussinessDao().copyAndOverrideExistedValueFromMap(sourceMapModel, targetObject);
	}

	public <T> T copyAndOverrideExistedValueFromObject(Object sourceObject, T targetObject) throws Exception {
		return this.getBussinessDao().copyAndOverrideExistedValueFromObject(sourceObject, targetObject);
	}
	public <T> T copyAndOverrideExistedValueFromObjectNoSet(Object sourceObject, T targetObject) throws Exception {
		return this.getBussinessDao().copyAndOverrideExistedValueFromObjectNoSet(sourceObject, targetObject);
	}

	public <T> T copyAndOverrideExistedValueFromStringMap(Map<String, String> sourceMapModel, T targetObject,
			Map<String, String> classFieldMapping, String... entityIdentifier) throws Exception {
		return this.getBussinessDao().copyAndOverrideExistedValueFromStringMap(sourceMapModel, targetObject, classFieldMapping,
				entityIdentifier);
	}

	public <T> T copyAndOverrideExistedValueFromStringMap(Map<String, String> sourceMapModel, T targetObject,
			Map<String, String> classFieldMapping, boolean withUserInfo, String... entityIdentifier)
			throws Exception {
		return this.getBussinessDao().copyAndOverrideExistedValueFromStringMap(sourceMapModel, targetObject, classFieldMapping,
				withUserInfo, entityIdentifier);
	}

	public JSONObject getJsonObjectByParameterMap(Map map, boolean allownNull) throws Exception {
		return this.getBussinessDao().getJsonObjectByParameterMap(map, allownNull);
	}

	public <T> T updateMainEntity(Class<T> mainObjectClass, Map<String, Object> propertiesMap, Map sourceMapModel,
			Map<String, String> classFieldMapping, String... entityIdentifier) throws Exception {
		return this.getBussinessDao().updateMainEntity(mainObjectClass, propertiesMap, sourceMapModel, classFieldMapping, entityIdentifier);
	}

	public <T, V> V updateOneToOneEntity(T mainObject, String toUpdatedObjectFieldName,
			String toMainObjectFieldName, Map sourceMapModel, Map<String, String> classFieldMapping,
			String... entityIdentifier) throws Exception {
		return this.getBussinessDao().updateOneToOneEntity(mainObject, toUpdatedObjectFieldName, toMainObjectFieldName, sourceMapModel,
				classFieldMapping, entityIdentifier);
	}

	public <T, V> Collection<V> updateOneToManyCollections(T OneToManyObj, String OneToManyFieldName,
			Class<V> ManyToOneObjClass, String ManyToOneFieldName, String jsonArrayStr,
			Map<String, String> classFieldMapping, String... entityIdentifier) throws Exception {
		return this.getBussinessDao().updateOneToManyCollections(OneToManyObj, OneToManyFieldName, ManyToOneObjClass, ManyToOneFieldName,
				jsonArrayStr, classFieldMapping, true, entityIdentifier);
	}

	public <T, V> Collection<V> updateOneToManyCollectionsNoRemoved(T OneToManyObj, String OneToManyFieldName,
			Class<V> ManyToOneObjClass, String ManyToOneFieldName, String jsonArrayStr,
			Map<String, String> classFieldMapping, String... entityIdentifier) throws Exception {
		return this.getBussinessDao().updateOneToManyCollections(OneToManyObj, OneToManyFieldName, ManyToOneObjClass, ManyToOneFieldName,
				jsonArrayStr, classFieldMapping, false, entityIdentifier);
	}

	//
	public <T> Map<String, String> getEntityPropertiesToStringMap(T entity, Map<String, String> fieldClassMapping,
			String... entityIdentifier) throws Exception {
		return this.getBussinessDao().getEntityPropertiesToStringMap(entity, fieldClassMapping, entityIdentifier);
	}

	public <T> JSONArray getCollectionEntitiesPropertiesToJsonArray(Collection<T> entities,
			Map<String, String> fieldClassMapping, String... entityIdentifier) throws Exception {
		return this.getBussinessDao().getCollectionEntitiesPropertiesToJsonArray(entities, fieldClassMapping, entityIdentifier);
	}

	public <T> String getCollectionEntitiesPropertiesToJsonArrayString(Collection<T> entities,
			Map<String, String> fieldClassMapping, String... entityIdentifier) throws Exception {
		return this.getBussinessDao().getCollectionEntitiesPropertiesToJsonArrayString(entities, fieldClassMapping, entityIdentifier);
	}

	// basic query operation
	public <T> List<T> findEntities(Class<T> entityClass) throws Exception {
		return this.getBussinessDao().findEntities(entityClass);
	}

	public <T> Integer getEntitiesRowCount(Class<T> entityClass) throws Exception {
		return this.getBussinessDao().getEntitiesRowCount(entityClass);
	}

	public <T> List<T> findEntitiesPage(Class<T> entityClass, final int start, final int limit) throws Exception {
		return this.getBussinessDao().findEntitiesPage(entityClass, start, limit);
	}

	public <T> List<T> findEntityByProperties(Class<T> entityClass, Map<String, Object> propertiesMap) throws Exception {
		return this.getBussinessDao().findEntityByProperties(entityClass, propertiesMap);
	}

	public <T> Integer getEntityByPropertiesRowCount(Class<T> entityClass, Map<String, Object> propertiesMap)
			throws Exception {
		return this.getBussinessDao().getEntityByPropertiesRowCount(entityClass, propertiesMap);
	}

	public <T> List<T> findEntityByPropertiesPage(Class<T> entityClass, Map<String, Object> propertiesMap, int start, int limit)
			throws Exception {
		return this.getBussinessDao().findEntityByPropertiesPage(entityClass, propertiesMap, start, limit);
	}

	public <T> List<T> findResultsByHSQL(String hsql, Object... values) throws Exception {
		return this.getBussinessDao().findResultsByHSQL(hsql, values);
	}

	public Integer getResultsByHSQLRowCount(String countHSql, Object... values) throws Exception {
		return this.getBussinessDao().getResultsByHSQLRowCount(countHSql, values);
	}

	public <T> List<T> findResultsByHSQLPage(String hsql, int start, int limit, Object... values) throws Exception {
		return this.getBussinessDao().findResultsByHSQLPage(hsql, start, limit, values);
	}

	public <T> List<T> findResultsByNamedParamHSQL(String hsql, String[] paramNames, Object[] values) throws Exception {
		return this.getBussinessDao().findResultsByNamedParamHSQL(hsql, paramNames, values);
	}

	public Integer getResultsByNamedParamHSQLRowCount(String countHsql, String[] paramNames, Object[] values)
			throws Exception {
		return this.getBussinessDao().getResultsByNamedParamHSQLRowCount(countHsql, paramNames, values);
	}

	public <T> List<T> findResultsByNamedParamHSQLPage(String hsql, String[] paramNames, Object[] values, int start, int limit)
			throws Exception {
		return this.getBussinessDao().findResultsByNamedParamHSQLPage(hsql, paramNames, values, start, limit);
	}

	public <T> T findEntityByID(Class<T> entityClass, String id) throws DataAccessException, Exception {
		return this.getBussinessDao().findEntityByID(entityClass, id);
	}

	public void updateOrderPosition(String tablename, String currentId, String parentId, String currentPosition)
			throws Exception {
		this.getBussinessDao().updateOrderPosition(tablename, currentId, parentId, currentPosition);
	}

	public void updateBySql(String sql, Object... values) throws Exception {
		this.getBussinessDao().updateBySql(sql, values);
	}

	public List<Map<String, Object>> queryListBySql(String sql, Object... values) throws Exception {
		return this.getBussinessDao().queryListBySql(sql, values);
	}

	/**
	 * @date 2013-4-17
	 *       xuyunlong
	 * @param entityClass
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> findEntityByIDArray(Class<T> entityClass, String[] ids) throws Exception {
		return this.getBussinessDao().findEntityByIDArray(entityClass, ids);
	}

	public Map<String, String> getStringMapByJsonObject(JSONObject jsonObj) throws Exception {
		return this.getBussinessDao().getStringMapByJsonObject(jsonObj);
	}

	public <T> JSONObject getEntityPropertiesToJsonEntity(T entity, Map<String, String> fieldClassMapping, String... entityIdentifier)
			throws Exception {
		return this.getBussinessDao().getEntityPropertiesToJsonEntity(entity, fieldClassMapping, entityIdentifier);
	}

	public <T> JSONObject getEntityPropertiesToJsonEntityWithOtherEntityFields(
			T entity, Map<String, String> fieldClassMapping,
			Map<String, String> otherEntityFieldsMapping,
			String... entityIdentifier) throws Exception {
		return this.getBussinessDao().getEntityPropertiesToJsonEntityWithOtherEntityFields(entity, fieldClassMapping,
				otherEntityFieldsMapping, entityIdentifier);
	}

	public Object recursionEntityFieldValue(Object topEntity,
			String recursionFieldStr) throws Exception {
		return this.getBussinessDao().recursionEntityFieldValue(topEntity, recursionFieldStr);
	}

	public <T> Map<String, String> getEntityPropertiesToStringMapWithOtherEntityFields(
			T entity, Map<String, String> fieldClassMapping,
			Map<String, String> otherEntityFieldsMapping,
			String... entityIdentifier) throws Exception {
		return this.getBussinessDao().getEntityPropertiesToStringMapWithOtherEntityFields(entity, fieldClassMapping,
				otherEntityFieldsMapping, entityIdentifier);
	}

	public <T> JSONArray getCollectionEntitiesPropertiesToJsonArrayWithOtherEntityFields(
			Collection<T> entities, Map<String, String> fieldClassMapping,
			Map<String, String> otherEntityFieldsMapping,
			String... entityIdentifier) throws Exception {
		return this.getBussinessDao().getCollectionEntitiesPropertiesToJsonArrayWithOtherEntityFields(entities, fieldClassMapping,
				otherEntityFieldsMapping, entityIdentifier);
	}

	public <T> String getCollectionEntitiesPropertiesToJsonArrayStringWithOtherEntityFields(
			Collection<T> entities, Map<String, String> fieldClassMapping,
			Map<String, String> otherEntityFieldsMapping,
			String... entityIdentifier) throws Exception {
		return this.getBussinessDao().getCollectionEntitiesPropertiesToJsonArrayStringWithOtherEntityFields(entities, fieldClassMapping,
				otherEntityFieldsMapping, entityIdentifier);
	}

	public <T> Collection<T> saveOrUpdateEntitiesByJSONArrayString(Class<T> saveEntityClass, String jsonArrayString,
			Map<String, String> classFieldMapping, String... entityIdentifier) throws Exception {
		return this.getBussinessDao().saveOrUpdateEntitiesByJSONArrayString(saveEntityClass, jsonArrayString, classFieldMapping,
				entityIdentifier);
	}
	@Override
	public <T> Collection<T> getEntitiesByJSONArrayString(Class<T> saveEntityClass, String jsonArrayString,
			Map<String, String> classFieldMapping, String... entityIdentifier)
			throws Exception {
		// TODO Auto-generated method stub
		return this.getBussinessDao().getEntitiesByJSONArrayString(saveEntityClass, jsonArrayString, classFieldMapping, entityIdentifier);
	}

	@Override
	public void hisEntityByProcedures(String fromtablename, String totablename,
			String condition, Map<String, String> otherproperty,List<String>excludeColumn)
			throws Exception {
		this.getBussinessDao().hisEntityByProcedures(fromtablename, totablename, condition, otherproperty,excludeColumn);
		
	}
	
}
