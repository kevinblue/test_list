package com.tenwa.business.dao;

/**
 * 项目名称：    系统名称
 * 包名：              com.business.dao
 * 文件名：         BaseDao.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-1-12-下午08:41:31
 * Copyright：2013XX公司-版权所有
 **/


import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.tenwa.business.model.CaseEnum;


/**
 * 类名称： BaseDao
 * 类描述：
 * 创建人： Administrator
 * 修改人： Administrator
 * 修改时间：2013-1-12 下午08:41:31
 * 修改备注：
 * 
 * @version 1.0.0
 **/
@SuppressWarnings({ "unchecked" })
public interface BaseDao
{
	public JdbcTemplate getJdbcTemplate() throws Exception;

	public HibernateTemplate getHibernateTemplate() throws Exception;

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
	public <T> T copyAndOverrideExistedValueFromJSONObject(JSONObject jsonObj, T targetObject,Map<String, String> classFieldMapping, String... entityIdentifier)
	throws Exception;
	
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
			Map<String, String> classFieldMapping, boolean isRemovedRecord, String... entityIdentifier) throws Exception;

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
	public <T> JSONObject getEntityPropertiesToJsonEntityWithCase(T entity, Map<String, String> fieldClassMapping,CaseEnum caseFlag,String... entityIdentifier) throws Exception;
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
	
	public <T> Collection<T> saveOrUpdateEntitiesByJSONArrayString(Class<T> saveEntityClass,String jsonArrayString,Map<String, String> classFieldMapping, String... entityIdentifier) throws Exception;
	public <T> Collection<T> getEntitiesByJSONArrayString(Class<T> saveEntityClass,String jsonArrayString,Map<String, String> classFieldMapping, String... entityIdentifier) throws Exception;

	public <T> T copyAndSetValueFromStringMap(Map<String,String> sourceMapModel, T targetObject,
			Map<String, String> classFieldMapping, String... entityIdentifier) throws Exception;
	public void hisEntityByProcedures(String fromtablename,String totablename,String condition,Map<String,String> otherproperty,List<String>excludeColumn) throws Exception;
	/**
	 * redis
	 * add by zhangc
	 */
	public <V> RedisTemplate<String,V> getRedisTemplate() throws Exception;	
	public RedisSerializer<String> getRedisSerializer() throws Exception;
	public String getByRedis(final String keyId)  throws Exception;
	public void deleteByRedis(String keyId)  throws Exception;
	public void deleteByRedis(List<String> keyIds)  throws Exception;
	public void addByRedis(final Map<String, String> valueMap)  throws Exception;
	/**
	 * 压栈
	 */
	public Long  pushByRedis(String key, List<String> values)  throws Exception;
	/**
	 * 出栈
	 */
	public String  popByRedis(String key)  throws Exception;
	/**
	 * 入队
	 * @param key
	 * @param valueMap
	 * @throws Exception
	 */
	public Long  inByRedis(String key,  List<String> values)  throws Exception;
	/**
	 * 出队
	 * @param key
	 * @param values
	 * @throws Exception
	 */
	public String  outByRedis(String key)  throws Exception;
	/**
	 * redis 链表长度
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Long length(String key)throws Exception;
	/**
	 * 范围检索
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public List<String> rangeForListByRedis(String key,int start,int end) throws Exception;

    /** 
     * 移除 
     *  
     * @param key 
     * @param i 
     * @param value 
     */  
    public void removeByRedis(String key, long i, String value)throws Exception;
    /** 
     * 检索 
     *  
     * @param key 
     * @param index 
     * @return 
     */  
    public String indexForRedisList(String key, long index)throws Exception;
  
    /** 
     * 置值 
     *  
     * @param key 
     * @param index 
     * @param value 
     */  
    public void setForRedisList(String key, long index, String value) throws Exception;
  
    /** 
     * 裁剪 
     *  
     * @param key 
     * @param start 
     * @param end 
     */  
    public void trimForRedisList(String key, long start, int end)throws Exception;
}
