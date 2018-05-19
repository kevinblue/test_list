
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.daoImpl
 * 文件名：         BaseDaoImpl.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-1-15-下午03:40:16
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.daoImpl;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;


 /**
 * 类名称：     BaseDaoImpl
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-1-15 下午03:40:16
 * 修改备注：
 * @version 1.0.0
 **/
@Repository(value="baseDao")
public class BaseDaoImpl extends AbstractBaseDaoImpl{
	
	 @Resource(name="jdbcTemplate")
	 private JdbcTemplate jdbcTemplate;
	 @Resource(name="hibernateTemplate")
	 private HibernateTemplate hibernateTemplate;
	 
	 @Resource(name="redisTemplate")
	 private  RedisTemplate<String, String> redisTemplate;
	 
	/**
	 * (non-Javadoc)
	 * @see com.tenwa.business.daoImpl.AbstractBaseDaoImpl#getHibernateTemplate()
	 **/
    
	@Override
	public HibernateTemplate getHibernateTemplate() throws Exception {
		return this.hibernateTemplate;
	}

	/**
	 * (non-Javadoc)
	 * @see com.tenwa.business.daoImpl.AbstractBaseDaoImpl#getJdbcTemplate()
	 **/

	@Override
	public JdbcTemplate getJdbcTemplate() throws Exception {
		return this.jdbcTemplate;
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public RedisTemplate<String, String> getRedisTemplate() throws Exception {
		return this.redisTemplate;
	}
}
