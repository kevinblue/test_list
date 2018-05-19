package com.tenwa.business.daoImpl;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.poi.ss.formula.functions.T;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.hibernate.type.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.tenwa.business.dao.CommDao;
import com.tenwa.kernal.utils.BeanFieldUtil;

@Repository(value = "commDao")
public class CommDaoImpl implements CommDao {
	private HibernateTemplate hibernateTemplate;

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	public List<Object> findAll(String objectType) {
		String hql = "from " + objectType;
		return (List<Object>) hibernateTemplate.find(hql);
	}

	@Override
	public Object findById(String objectType, String id) throws DataAccessException, ClassNotFoundException, SecurityException, NoSuchFieldException {
		return (Object) hibernateTemplate.get(objectType, this.getSerializableId(objectType, id));
	}

	@Override
	public void modify(String objectType, Object object, Serializable id) throws Exception {
		hibernateTemplate.load(this.getType(objectType), id);
		hibernateTemplate.update(objectType, object);
	}

	@Override
	public Serializable saveOrUpdata(String objectType, Object object) throws Exception {
		Method getId = this.getType(objectType).getDeclaredMethod("getId");
		Serializable id = (Serializable) getId.invoke(object);

		if (id == null || "".equals(id.toString().trim()) || id.toString() == "0") {
			return this.save(objectType, object);
		} else {
			this.modify(objectType, object, id);
			return id;
		}
	}

	@Override
	public void remove(String objectType, String id) throws Exception {
		Object o = Class.forName(objectType).newInstance();
		PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(o.getClass(), "id");
		pd.getWriteMethod().invoke(o, id);
		hibernateTemplate.delete(o);
	}

	@Override
	public void removeAll(String objectType, Collection<Object> entities) throws ClassNotFoundException {
		Class<?> clazz = this.getType(objectType);
		for (Object object : entities) {
			clazz.cast(object);
		}
		hibernateTemplate.deleteAll(entities);
	}

	@Override
	public Serializable save(String objectType, Object object) {
		Serializable id = hibernateTemplate.save(objectType, object);
		return id;
	}

	@Override
	public int getTotalRows(String queryString) {
		int fromIndex = queryString.toUpperCase().indexOf("FROM");
		String actualHql = "select count(*) " + queryString.substring(fromIndex);
		int totalRows = ((Long) this.hibernateTemplate.find(actualHql).get(0)).intValue();
		return totalRows;
	}

	@Override
	public int getPageSize(int size, String queryString) {
		int pageSize;
		int actualSize;
		int totalRows = this.getTotalRows(queryString);
		// 计算实际每页的条数,如果请求的每页数据条数大于总条数, 则等于总条数
		actualSize = (size > totalRows) ? totalRows : size;
		if (totalRows > 0) {
			pageSize = (totalRows % size == 0) ? (totalRows / actualSize) : (totalRows / actualSize + 1);
		} else {
			pageSize = 0;
		}
		return pageSize;
	}

	@Override
	public int[] getTotalRowsAndPageSize(int size, String queryString) {
		int[] totalRowsAndPageSize = new int[2];
		// 最大页数
		int pageSize;
		// 实际每页数据条数
		int actualSize;
		// 总记录数
		int totalRows = this.getTotalRows(queryString);
		// 计算实际每页的条数,如果请求的每页数据条数大于总条数, 则等于总条数
		actualSize = (size > totalRows) ? totalRows : size;
		if (totalRows > 0) {
			pageSize = (totalRows % size == 0) ? (totalRows / actualSize) : (totalRows / actualSize + 1);
		} else {
			pageSize = 0;
		}
		totalRowsAndPageSize[0] = totalRows;
		totalRowsAndPageSize[1] = pageSize;
		return totalRowsAndPageSize;
	}

	@Override
	public Map<String, Object> findPageList(String objectType, final int page, final int size) {
		return this.findPageList(page, size, this.getCommHql(objectType));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Object> findPageList(final int page, final int size, String queryString) {
		Map<String, Object> findByPageMap = new java.util.HashMap<String, Object>();
		int[] totalRowsAndPageSize = this.getTotalRowsAndPageSize(size, queryString);
		final int pageSize = totalRowsAndPageSize[1];
		final int totalRows = totalRowsAndPageSize[0];
		final String execuHql = queryString;

		List<T> dataList = hibernateTemplate.executeFind(new HibernateCallback() {
			public List<T> doInHibernate(Session session) throws HibernateException, SQLException {
				// 实际页码
				int actualPage = (page > pageSize) ? pageSize : page;
				// 计算实际每页的条数,如果请求的每页数据条数大于总条数, 则等于总条数
				int actualSize = (size > totalRows) ? totalRows : size;
				// 计算请求页码的第一条记录的索引值,如果
				int startRow = (actualPage > 0) ? (actualPage - 1) * actualSize : 0;
				Query query = session.createQuery(execuHql);
				query.setFirstResult(startRow);
				query.setMaxResults(actualSize);
				return (List<T>) query.list();
			}
		});
		findByPageMap.put("total", totalRows);
		findByPageMap.put("rows", dataList);
		return findByPageMap;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findPageList(String objectType, Object[] params) throws ClassNotFoundException {
		DetachedCriteria criteria = DetachedCriteria.forClass(this.getType(objectType));
		criteria.add(Restrictions.in("id", params));
		List<Object> list = (List<Object>) hibernateTemplate.findByCriteria(criteria);
		return list;
	}


	@Override
	public int[] getTotalRowsAndPageSize(String objectType, int size) {
		return this.getTotalRowsAndPageSize(size, this.getCommHql(objectType));
	}

	@Override
	public Class<?> getType(String objectType) throws ClassNotFoundException {
		Class<?> type = Class.forName(objectType);
		return type;

	}

	public String getCommHql(String objectType) {
		String hql = "from " + objectType;
		return hql;
	}

	/**
	 * 判断id是String还是Integer类型
	 * 
	 * @param objectType
	 * @param id
	 * @return
	 * @throws ClassNotFoundException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	@Override
	public Serializable getSerializableId(String objectType, String id) throws ClassNotFoundException, SecurityException, NoSuchFieldException {
		Class<?> clazz = this.getType(objectType);
		Serializable idcast = id;
		Field idFiled = clazz.getDeclaredField("id");
		if (!(idFiled.getType().equals(id.getClass()))) {
			Integer ids = Integer.parseInt(id);
			idcast = ids;
		}
		return idcast;
	}

	@Override
	public List<Object> findByColumn(String objectType, String columnName, String columnValue) throws Exception {
		Object value = null;
		if (!columnValue.toLowerCase().equals("null") && !columnValue.toLowerCase().equals("")) {
			Class<?> object = this.getType(objectType);
			Field objectField = object.getDeclaredField(columnName);
			String objectName = objectField.getType().getName();
			if (objectName.contains("entity.")) {
				value = Class.forName(objectName).newInstance();
				Method m = value.getClass().getDeclaredMethod("setId", Class.forName("java.lang.String"));
				m.invoke(value, columnValue);
			} else {
				value = columnValue;
			}
		}

		DetachedCriteria criteria = DetachedCriteria.forClass(this.getType(objectType));
		if (value == null) {
			criteria.add(Restrictions.isNull(columnName));
		} else {
			criteria.add(Restrictions.eq(columnName, value));
		}

		List<Object> list = hibernateTemplate.findByCriteria(criteria);
		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> getAllEntityProperty(String key) throws Exception {

		SessionFactory sessionFactory = getHibernateTemplate().getSessionFactory();

		Map<String, ClassMetadata> entitys = sessionFactory.getAllClassMetadata();
		List<Map<String, String>> tableNames = new ArrayList<Map<String, String>>();
		List<Map<String, String>> tablePropertyList = new ArrayList<Map<String, String>>();

		Set<String> keys = entitys.keySet();
		for (Iterator it = keys.iterator(); it.hasNext();) {
			String keyStr = (String) it.next();
			
			Map<String, String> tableProperty = new HashMap<String, String>();

			AbstractEntityPersister classMetadata = (SingleTableEntityPersister) entitys.get(keyStr);
			String entityName = classMetadata.getEntityName();
			Class clazz = Class.forName(entityName);
			String showName = BeanFieldUtil.parseClassName(clazz);
			if(key!= null && !key.equals("") && !showName.contains(key)){
				continue;
			}
			
			if (null != showName && !showName.equals("")) {
				tableProperty.put("entityName", entityName);
				tableProperty.put("showName", showName);
				tableProperty.put("tableName", classMetadata.getTableName());
				tablePropertyList.add(tableProperty);
			}
		}
		return tablePropertyList;
	}

	public List<Map<String, String>> getColumnsProperty(Class clazz) throws Exception {
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		SessionFactory factory = getHibernateTemplate().getSessionFactory();
		AbstractEntityPersister classMetadata = (SingleTableEntityPersister) factory.getClassMetadata(clazz);
		Map<String,String> showNames = BeanFieldUtil.parseFieldNames(clazz);
		// 添加主键
		
		for (String s : classMetadata.getIdentifierColumnNames()) {
			Map<String, String> columnProperty = new HashMap<String, String>();
			columnProperty.put("property", "identified");
			columnProperty.put("columnName", s);
			columnProperty.put("fieldName", classMetadata.getIdentifierPropertyName());
			columnProperty.put("showName", showNames.get(s));
			resultList.add(columnProperty);
		}

		// 添加普通字段
		for (String propertyName : classMetadata.getPropertyNames()) {
			Map<String, String> columnProperty = new HashMap<String, String>();
			Type propertyType = classMetadata.getClassMetadata().getPropertyType(propertyName);
			//字段名
			String[] columnName = classMetadata.getPropertyColumnNames(propertyName);
			
			
			columnProperty.put("fieldName", propertyName);
			columnProperty.put("columnName", columnName[0]);
			columnProperty.put("showName", showNames.get(propertyName));

			if (propertyType.isEntityType()) {
				Class fkClazz = Class.forName(propertyType.getName());
				AbstractEntityPersister fKclassMetadata = (AbstractEntityPersister) this.getHibernateTemplate().getSessionFactory().getClassMetadata(fkClazz);
				
				columnProperty.put("property", "entity");
				columnProperty.put("fkEntityName", fKclassMetadata.getEntityName());
				columnProperty.put("fkTableName", fKclassMetadata.getTableName());

			} else if (propertyType.isCollectionType()) {
				//注：如果是集合类型的暂不处理
				//continue;
				
				ParameterizedType pt = (ParameterizedType) Class.forName(classMetadata.getEntityName()).getDeclaredField(propertyName).getGenericType();
				Class fkClazz = (Class) pt.getActualTypeArguments()[0];
				AbstractEntityPersister fKclassMetadata = (AbstractEntityPersister) this.getHibernateTemplate().getSessionFactory().getClassMetadata(fkClazz);
		
				columnProperty.put("property", "collection");
				columnProperty.put("fkEntityName", fKclassMetadata.getEntityName());
				columnProperty.put("fkTableName", fKclassMetadata.getTableName());
				
			} else{
				columnProperty.put("property", "column");
			}
			
			resultList.add(columnProperty);
		}

		return resultList;
	}
}