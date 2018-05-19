package com.tenwa.business.service;

/**
 * 项目名称：    系统名称
 * 包名：              com.business.service
 * 文件名：         BaseService.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-1-15-下午03:02:27
 * Copyright：2013XX公司-版权所有
 **/


import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.dao.DataAccessException;

import com.tenwa.business.dao.BaseDao;


/**
 * 类名称： BaseService 类描述： 创建人： Administrator 修改人： Administrator 修改时间：2013-1-15 下午03:02:27 修改备注：
 * 
 * @version 1.0.0
 **/
public interface BaseService {
	public BaseDao getBussinessDao() throws Exception;
    
//	/**
//	 * 根据propertiesMap查询结果新建或更新实体，自动保存时间，自动保存执行人，保存现有的引用类型，不保存引用集合类型
//	 * @param mainObjectClass 实体对象Class
//	 * @param propertiesMap 查询字段集合
//	 * @param sourceMapModel 创建或更新的数据源
//	 * @param classFieldMapping 引用类型字段查询映射
//	 * @param entityIdentifier 数据源中数据的key
//	 * @return 新建或更新完的实体实例
//	 * @throws Exception
//	 */
//	public Object      updateMainEntity(Class mainObjectClass,Map<String,Object> propertiesMap,Map sourceMapModel,Map<String,String> classFieldMapping,String... entityIdentifier) throws Exception;
//	
//	/**
//	 * 保存实体中字段是引用对象类型值 有保存方法
//	 * @param mainObject 实体对象实例(主实体)
//	 * @param toUpdatedObjectFieldName 实体中需要保存的字段(引用类型，子实体)
//	 * @param toMainObjectFieldName 字段(引用类型，子实体)中的对应实体对象实例(主实体)字段名称
//	 * @param sourceMapModel 数据源
//	 * @param classFieldMapping 引用类型字段查询映射
//	 * @param entityIdentifier 数据源中数据的key
//	 * @return 已保存的实体对象(主实体)
//	 * @throws Exception
//	 */
//	public Object      updateOneToOneEntity(Object mainObject,String toUpdatedObjectFieldName,String toMainObjectFieldName,Map sourceMapModel,Map<String,String> classFieldMapping,String... entityIdentifier) throws Exception;
//	
//	/**
//	 * 保存实体中字段是引用对象集合类型值 有保存方法
//	 * @param OneToManyObj 实体对象实例(主实体)
//	 * @param OneToManyFieldName 实体中需要保存的字段(引用类型，子实体集合)
//	 * @param ManyToOneObjClass 字段(子实体)类型
//	 * @param ManyToOneFieldName 字段(引用类型，子实体)中的对应实体对象实例(主实体)字段名称
//	 * @param jsonArrayStr 数据值
//	 * @param classFieldMapping 引用类型字段查询映射
//	 * @return 已保存实体的对象(主实体)
//	 * @throws Exception
//	 */
//	public <V,T> Collection<T>   updateOneToManyCollections(T OneToManyObj,String OneToManyFieldName,Class<V> ManyToOneObjClass,String ManyToOneFieldName,String jsonArrayStr,Map<String,String> classFieldMapping,String... entityIdentifier ) throws Exception;
//	public <T> Collection<T>   updateOneToManyCollectionsNoRemoved(Object OneToManyObj,String OneToManyFieldName,Class<?> ManyToOneObjClass,String ManyToOneFieldName,String jsonArrayStr,Map<String,String> classFieldMapping,String... entityIdentifier ) throws Exception;
//	/**
//	 * 将实体的字段值保存到Map中，不包含引用类型及引用类型的集合
//	 * @param entity 需要保存的实体对象
//	 * @param entityIdentifier 实体对象保存到Map中的key
//	 * @return 保存的实体对象Map
//	 * @throws Exception
//	 */
//	public <T> Map<String,String> getEntityPropertiesToStringMap(T entity,Map<String,String> classFieldMapping,String... entityIdentifier ) throws Exception;
//	/**
//	 * 将
//	 * @param entities
//	 * @param entityIdentifier
//	 * @return
//	 * @throws Exception
//	 */
//	public <T> JSONArray getCollectionEntitiesPropertiesToJsonArray(Collection<T>  entities,Map<String,String> fieldClassMapping,String... entityIdentifier ) throws Exception;
//	public <T>  String    getCollectionEntitiesPropertiesToJsonArrayString(Collection<T>  entities,Map<String,String> fieldClassMapping,String... entityIdentifier ) throws Exception;
	// basic update operation
	// basic update operation
	public <T> void saveEntity(T entity) throws Exception;

	public <T> void saveAllEntities(Collection<T>  entities) throws Exception;

	public <T> void updateEntity(T entity) throws Exception;

	public <T>  void updateAllEntities(Collection<T>  entities) throws Exception;

	public <T> void mergeEntity(T entity) throws Exception;

	public <T>  void mergeAllEntities(Collection<T>  entities) throws Exception;

	public <T> void saveOrUpdateEntity(T entity) throws Exception;

	public <T> void saveOrUpdateAllEntities(Collection<T> entities) throws Exception;

	public <T> void removeEntity(T entity) throws Exception;

	public <T> void  removeEntityById(Class<T> entityClass, String id) throws Exception;

	public <T> void removeAllEntites(Collection<T>  entities) throws Exception;

	public int updateByHSQL(String hsql, Object... values) throws Exception;

	public void updateFlush() throws Exception;

	public <T> T getNewOrUpdateObject(Class<T> clazz, Map<String, Object> propertiesMap) throws Exception;

	// 高级更新
	public <T> T copyAndOverrideExistedValueFromJSONObject(JSONObject jsonObj, T targetObject,Map<String, String> classFieldMapping, String... entityIdentifier) throws Exception;
	public <T> T copyAndOverrideExistedValueFromJSONObject(JSONObject jsonObj, T targetObject, Map<String, String> classFieldMapping,
			boolean withUserInfo, String... entityIdentifier) throws Exception;
	public <T> T copyAndOverrideExistedValueFromMap(Map<String, ? extends Object> sourceMapModel, T targetObject)
			throws Exception;

	public <T> T copyAndOverrideExistedValueFromObject(Object sourceObject, T targetObject) throws Exception;
	public <T> T copyAndOverrideExistedValueFromObjectNoSet(Object sourceObject, T targetObject) throws Exception;

	public <T> T copyAndOverrideExistedValueFromStringMap(Map<String,String> sourceMapModel, T targetObject,
			Map<String, String> classFieldMapping, String... entityIdentifier) throws Exception;

	public <T> T copyAndOverrideExistedValueFromStringMap(Map<String,String> sourceMapModel, T targetObject,
			Map<String, String> classFieldMapping, boolean withUserInfo, String... entityIdentifier)
			throws Exception;

	public JSONObject getJsonObjectByParameterMap(Map map, boolean allownNull) throws Exception;

	public <T> T updateMainEntity(Class<T> mainObjectClass, Map<String, Object> propertiesMap, Map sourceMapModel,
			Map<String, String> classFieldMapping, String... entityIdentifier) throws Exception;

	public <T,V> V updateOneToOneEntity(T mainObject, String toUpdatedObjectFieldName,
			String toMainObjectFieldName, Map sourceMapModel, Map<String, String> classFieldMapping,
			String... entityIdentifier) throws Exception;

	public <T,V> Collection<V> updateOneToManyCollections(T OneToManyObj, String OneToManyFieldName,
			Class<V> ManyToOneObjClass, String ManyToOneFieldName, String jsonArrayStr,
			Map<String, String> classFieldMapping,String... entityIdentifier) throws Exception;
	
	public <T,V> Collection<V> updateOneToManyCollectionsNoRemoved(T OneToManyObj, String OneToManyFieldName,
			Class<V> ManyToOneObjClass, String ManyToOneFieldName, String jsonArrayStr,
			Map<String, String> classFieldMapping,String... entityIdentifier) throws Exception;
	//
	public <T> Map<String, String> getEntityPropertiesToStringMap(T entity, Map<String, String> fieldClassMapping,
			String... entityIdentifier) throws Exception;

	public <T>  JSONArray getCollectionEntitiesPropertiesToJsonArray(Collection<T>  entities,
			Map<String, String> fieldClassMapping, String... entityIdentifier) throws Exception;

	public <T> String getCollectionEntitiesPropertiesToJsonArrayString(Collection<T>  entities,
			Map<String, String> fieldClassMapping, String... entityIdentifier) throws Exception;

	// basic query operation
	public <T>  List<T>  findEntities(Class<T> entityClass) throws Exception;

	public <T> Integer getEntitiesRowCount(Class<T> entityClass) throws Exception;

	public <T> List<T>  findEntitiesPage(Class<T> entityClass, final int start, final int limit) throws Exception;

	public <T> List<T>  findEntityByProperties(Class<T> entityClass, Map<String, Object> propertiesMap) throws Exception;

	public <T> Integer getEntityByPropertiesRowCount(Class<T> entityClass, Map<String, Object> propertiesMap)
			throws Exception;

	public <T> List<T>  findEntityByPropertiesPage(Class<T> entityClass, Map<String, Object> propertiesMap, int start, int limit)
			throws Exception;

	public <T> List<T> findResultsByHSQL(String hsql, Object... values) throws Exception;

	public Integer getResultsByHSQLRowCount(String countHSql, Object... values) throws Exception;

	public <T> List<T>  findResultsByHSQLPage(String hsql, int start, int limit, Object... values) throws Exception;

	public <T> List<T> findResultsByNamedParamHSQL(String hsql, String[] paramNames, Object[] values) throws Exception;

	public Integer getResultsByNamedParamHSQLRowCount(String countHsql, String[] paramNames, Object[] values)
			throws Exception;

	public <T> List<T> findResultsByNamedParamHSQLPage(String hsql, String[] paramNames, Object[] values, int start, int limit)
			throws Exception;

	public <T> T findEntityByID(Class<T> entityClass, String id) throws DataAccessException, Exception;

	public void updateOrderPosition(String tablename, String currentId, String parentId, String currentPosition)
			throws Exception;
    public void updateBySql(String sql ,Object... values) throws Exception;
    public List<Map<String,Object>> queryListBySql( String sql ,Object... values) throws Exception;
	/**
	 * 
	 * @date 2013-4-17
	 *       xuyunlong
	 * @param entityClass
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> findEntityByIDArray(Class<T> entityClass, String[] ids) throws Exception;
	public Map<String, String> getStringMapByJsonObject(JSONObject jsonObj) throws Exception;
	public <T> JSONObject getEntityPropertiesToJsonEntity(T entity,Map<String, String> fieldClassMapping,String... entityIdentifier) throws Exception;
   
	
	public <T> JSONObject getEntityPropertiesToJsonEntityWithOtherEntityFields(
			T entity, Map<String, String> fieldClassMapping,
			Map<String, String> otherEntityFieldsMapping,
			String... entityIdentifier) throws Exception;

	public Object recursionEntityFieldValue(Object topEntity,
			String recursionFieldStr) throws Exception;

	public <T> Map<String, String> getEntityPropertiesToStringMapWithOtherEntityFields(
			T entity, Map<String, String> fieldClassMapping,
			Map<String, String> otherEntityFieldsMapping,
			String... entityIdentifier) throws Exception;
	
	public <T> JSONArray getCollectionEntitiesPropertiesToJsonArrayWithOtherEntityFields(
			Collection<T> entities, Map<String, String> fieldClassMapping,
			Map<String, String> otherEntityFieldsMapping,
			String... entityIdentifier) throws Exception;
	
	public <T> String getCollectionEntitiesPropertiesToJsonArrayStringWithOtherEntityFields(
			Collection<T> entities, Map<String, String> fieldClassMapping,
			Map<String, String> otherEntityFieldsMapping,
			String... entityIdentifier) throws Exception;
	
	public <T> String getEntityPropertiesToJsonEntityString(T entity, Map<String, String> fieldClassMapping, String... entityIdentifier)
			throws Exception;
	
	public <T> Collection<T> saveOrUpdateEntitiesByJSONArrayString(Class<T> saveEntityClass,String jsonArrayString,Map<String, String> classFieldMapping, String... entityIdentifier) throws Exception;
    public <T> Collection<T> getEntitiesByJSONArrayString(Class<T> saveEntityClass,String jsonArrayString,Map<String, String> classFieldMapping, String... entityIdentifier) throws Exception;
    public void hisEntityByProcedures(String fromtablename,String totablename,String condition,Map<String,String> otherproperty,List<String>excludeColumn) throws Exception;
    public void saveorUpdateTradeBasedTransactions(Map<String, String> variablesMap) throws Exception;//保理业务贸易基础交易情况保存或更新数据
    
}
