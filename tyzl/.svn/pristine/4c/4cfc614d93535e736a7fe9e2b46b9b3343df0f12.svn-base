package com.tenwa.business.daoImpl;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.tenwa.business.dao.RelationDao;


@Repository("relationDao")
public class RelationDaoImpl extends AbstractBaseDaoImpl implements RelationDao {

	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	@Resource(name = "hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	@Resource(name="redisTemplate")
	private RedisTemplate<String, String> redisTemplate;
	
	
	@SuppressWarnings({"unchecked"})
	@Override
	public RedisTemplate<String, String> getRedisTemplate() throws Exception {
		return this.redisTemplate;
	}
	
	
	@Override
	public JdbcTemplate getJdbcTemplate() throws Exception {
		return this.jdbcTemplate;
	}

	@Override
	public HibernateTemplate getHibernateTemplate() throws Exception {
		return this.hibernateTemplate;
	}

}
