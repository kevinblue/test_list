package com.tenwa.kernal.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

public class EntityUtil {
	private static Logger log = LoggerFactory.getLogger(EntityUtil.class);

	public static Map<String, String> getEntityPropertiesToStringMap(Object entity, Map<String, String> fieldClassMapping, String... entityIdentifier) {
		Map<String, String> propertiesMap = new HashMap<String, String>();
		StringBuffer sb = new StringBuffer();
		if (entityIdentifier.length > 0) {
			for (String ei : entityIdentifier) {
				sb.append(ei + ".");
			}
		}
		String prefixStr = sb.toString();
		PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(entity.getClass());
		for (PropertyDescriptor pd : pds) {
			Method readMethod = pd.getReadMethod();
			if (null == readMethod)
				continue;
			Class<?> returnType = readMethod.getReturnType();
			Object returnValue = null;
			try {
				returnValue = readMethod.invoke(entity);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			if (null != returnValue) {
				String value = "";
				if (returnType.isAssignableFrom(Set.class)) {
					continue;
				} else if (isTenwaEntity(returnType)) {
					String fieldName = null;
					if (null != fieldClassMapping) {
						fieldName = fieldClassMapping.get(returnType.getSimpleName());
					}
					if (StringUtils.isBlank(fieldName)) {
						fieldName = "id";
					}
					Method method = BeanUtils.getPropertyDescriptor(returnType, fieldName).getReadMethod();
					// System.out.println("####："+method.getName()+","+returnValue);
					try {
						value = StringUtil.nullToString(method.invoke(returnValue));
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				} else {
					if (returnType.getSimpleName().equalsIgnoreCase("double")) {
						value = MathUtil.decimal((Double) returnValue, 8);
					} else {
						value = returnValue.toString();
					}
				}
				propertiesMap.put(prefixStr + pd.getName().toLowerCase(), value);
			}
		}
		return propertiesMap;
	}

	public static boolean isTenwaEntity(Class<?> entityClass) {
		boolean isTenwaEntity = false;
		try {
			if (entityClass != null) {
				String entityClassName = entityClass.getName();
				Entity entity = entityClass.getAnnotation(Entity.class);
				if (entity != null) {
					isTenwaEntity = entityClassName.matches("com.*.*.entity.*");
				}
			}
		} catch (Exception e) {
			log.warn("", e);
		}
		return isTenwaEntity;

	}

	public static boolean isTenwaDictDataEntity(Class<?> entityClass) {
		if (entityClass == null)
			return false;
		return "com.tenwa.business.entity.DictionaryData".equals(entityClass.getName());
	}
	
	public static boolean isTenwaCustEntity(Class<?> entityClass) {
		if (entityClass == null)
			return false;
		return "com.tenwa.leasing.entity.cust.CustInfo".equals(entityClass.getName());
	}
	
	public static boolean isTenwaUserEntity(Class<?> entityClass) {
		if (entityClass == null)
			return false;
		return "com.tenwa.business.entity.User".equals(entityClass.getName());
	}
	public static boolean isTenwaDepEntity(Class<?> entityClass) {
		if (entityClass == null)
			return false;
		return "com.tenwa.business.entity.Department".equals(entityClass.getName());
	}
	

}
