package com.tenwa.business.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;


public interface CommDao {

	/**
	 * <p/>
	 * 查找全部实体
	 * <p/>
	 * @return 所有实体的列表
	 */
	public List<Object> findAll(String objectType);

	/**
	 * <p>
	 * 通过名字查找
	 * </p>
	 * @param id
	 * @return 找到的实体
	 * @throws ClassNotFoundException 
	 * @throws DataAccessException 
	 * @throws NoSuchFieldException 
	 * @throws SecurityException 
	 */
	public Object findById(String objectType, String id) throws DataAccessException, ClassNotFoundException, SecurityException, NoSuchFieldException;

	/**
	 * 根据给定的ID数组查找
	 * 
	 * @param idList
	 *            给的id数组
	 * @return 匹配的实体列表
	 * @throws ClassNotFoundException 
	 */
	public List<Object> findPageList(String objectType, Object[] params) throws ClassNotFoundException;

	/**
	 * <p/>
	 * 根据给定的页码进行分页查找,这是纯Hibernate分页.
	 * <p/>
	 * 
	 * @param page
	 *            : 要查询的页码
	 * @param size
	 *            : 每页记录数
	 * @return 匹配的实体总数和列表map.get("totalRows"),map.get("dataList");
	 */
	public Map<String, Object> findPageList(String objectType, final int page, final int size);

	/**
	 * <p/>
	 * 根据给定的页码进行分页查找,这是纯Hibernate分页.
	 * <p/>
	 * 
	 * @param page
	 *            : 要查询的页码
	 * @param size
	 *            : 每页记录数
	 * @param queryString
	 *            查询语句
	 * @return 匹配的实体总数和列表map.get("totalRows"),map.get("dataList");
	 */
	public Map<String, Object> findPageList(int page, int size, String queryString);


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
	 * 计算匹配查询条件的记录总数,如果没有注入或者设置hql语句,将使用默认的查询语句返回数据库中所有记录
	 * </p>
	 * 
	 * @param 查询语句
	 * @return 记录总数
	 */
	public int getTotalRows(String queryString);

	/**
	 * <p/>
	 * 获取总资源数和总分页数
	 * <p/>
	 * 
	 * @param size
	 * @return int[0]总资源数，int[1]总分页数
	 */
	public int[] getTotalRowsAndPageSize(String objectType, int size);

	/**
	 * <p/>
	 * 获取总资源数和总分页数
	 * <p/>
	 * @param size
	 * @param queryString 查询语句
	 * @return int[0]总资源数，int[1]总分页数
	 */
	public int[] getTotalRowsAndPageSize(int size, String queryString);


	public void remove(String objectType,String id) throws Exception;

	/**
	 * <p>
	 * 删除实体集合
	 * </p>
	 * @param entities: 实体
	 * @throws ClassNotFoundException 
	 */
	public void removeAll(String objectType, Collection<Object> objects) throws ClassNotFoundException;

	/**
	 * 保存实体
	 * @param entity : 实体
	 * @return 保存后得到的id
	 */
	public Serializable save(String objectType, Object object);

	Class<?> getType(String objectType) throws ClassNotFoundException;

	public Serializable getSerializableId(String objectType, String id) throws ClassNotFoundException, SecurityException, NoSuchFieldException;

	public void modify(String objectType, Object object, Serializable id) throws Exception;

	public Serializable saveOrUpdata(String objectType, Object object) throws Exception;

	public List<Object> findByColumn(String objectType, String columnName, String columnValue) throws Exception;
	

	/**
	 * 获取实体的原子及引用字段的集合
	 * @param type 类名称
	 * @return 所取字段的集合
	 * @throws Exception
	 */
	public List<Map<String, String>> getColumnsProperty(Class<?> type) throws Exception;

	List<Map<String, String>> getAllEntityProperty(String key) throws Exception;
}
