package com.tenwa.business.service;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.springframework.dao.DataAccessException;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.model.UIColumns;


public interface CommService {
	/**
	 * 保存实体
	 * @param modelType 
	 * @param xml 
	 * 
	 * @param entity :
	 *            实体
	 * @return 保存后得到的id
	 * @throws Exception 
	 */
	public Serializable save(String objectType, HttpServletRequest request) throws Exception;
	public void remove(String objectType, String id) throws Exception;
	
	
	/**
	 * <p>
	 * 删除实体集合
	 * </p>
	 * 
	 * @param entities :
	 *            实体
	 * @throws ClassNotFoundException 
	 */
	public void removeAll(String objectType, Collection<Object> objects) throws ClassNotFoundException;
	/**
	 * <p>
	 * 通过名字查找
	 * </p>
	 * 
	 * @param i :
	 *            id
	 * @return 找到的实体
	 * @throws NoSuchFieldException 
	 * @throws ClassNotFoundException 
	 * @throws SecurityException 
	 * @throws DataAccessException 
	 */
	public Object findById(String objectType, String id) throws DataAccessException, SecurityException, ClassNotFoundException, NoSuchFieldException;
	/**
	 * <p>
	 * 查找全部实体
	 * <p>
	 * 
	 * @return 所有实体的列表
	 */
	public List<Object> findAll(String objectType);
	/**
	 * <p>
	 * 根据给定的hql语句进行分页查找
	 * <p>
	 * @param page : 要查询的页码
	 * @param size : 每页记录条数
	 * @return 匹配的实体列表
	 */
	public Map<String, Object> findPageList(String objectType, final int page, final int size);
	/**
	 * <p>
	 * 根据每页记录的数量,计算出总的分页数
	 * </p>
	 * @param size  每页记录的数量
	 * @return 分页总数
	 */
	public int getPageSize(String objectType,int size);
	
	/**
	 * 根据给定的ID数组查找
	 * @param idList 给的id数组
	 * @return 匹配的实体列表
	 * @throws ClassNotFoundException 
	 */
	public List<Object> findByList(String objectType, Object[] roleIdList) throws ClassNotFoundException;
	
	/**
	 * 模糊查询
	 * @param objectType 前台传递查询的值
	 * @param columnsString 模糊查询字段 
	 * @param page 当前page
	 * @param pageSize 当前rows
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws JSONException 
	 */
	public Map<String, Object> findFuzzy(String column, String objectType, String condition, int page, int pageSize) throws ClassNotFoundException, JSONException;
	
	/**
	 * <p>
	 * 计算匹配查询条件的记录总数,如果没有注入或者设置hql语句,将使用默认的查询语句返回数据库中所有记录
	 * </p>
	 * @param queryStrintg 查询语句
	 * @return 记录总数
	 */
	public int getTotalRows(String queryStrintg);
	
	/**
	 * <p>
	 * 根据每页记录的数量,计算出总的分页数
	 * </p>
	 * @param size  每页记录的数量
	 * @param queryString 查询语句
	 * @return 分页总数
	 */
	public int getPageSize(int size, String queryString);
	
	/**
	 * <p>
	 * 根据给定的hql语句进行分页查找
	 * <p>
	 * @param page : 要查询的页码
	 * @param size : 每页记录条数
	 * @param queryString : 差序语句
	 * @return 匹配的实体列表
	 */
	public Map<String, Object> findPageList(int page, int size, String queryString);
	public Class<?> getType(String objectType) throws ClassNotFoundException;
	public List<UIColumns> findFieldNames(String objectType, String xml, String modelType) throws ClassNotFoundException, IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException;
	public Serializable getSerializableId(String objectType, String id) throws SecurityException, ClassNotFoundException, NoSuchFieldException;
	public void modify(String objectType, Object object, Serializable id) throws Exception;
	public Map<String, String> saveOrUpdataReturnCasecadeID(String objectType, HttpServletRequest request) throws Exception;
	public List<Object> findByColumn(String objectType, String columnName, String columnValue) throws Exception;
	public List<DictionaryData> findDictValue(String columnValue) throws Exception;
	public String readFile(String fileName) throws Exception;
	public void saveCommGrid(String param) throws Exception;
	public String getJsonString(String jsonStringName) throws Exception;
	public List<Map<String, String>> getExistedFile(String fileName) throws Exception;
	public List<Map<String, String>> getColumnsProperty(HttpServletRequest request) throws Exception;
	List<Map<String, String>> getAllEntityProperty(String key) throws Exception;
	public void removeByIDs(String objectType, String ids) throws Exception;
}