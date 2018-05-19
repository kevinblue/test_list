/**
 * 
 */
package com.b510.base.service.impl;

import com.b510.base.dao.BaseDao;
import com.b510.base.service.BaseService;

/**
 * @author hongten(hongtenzone@foxmail.com)
 * @date 2013-2-24
 */
public abstract class AbstractBaseServiceImpl<T> implements BaseService<T>{

	public abstract BaseDao<T> getBaseDao();

}
