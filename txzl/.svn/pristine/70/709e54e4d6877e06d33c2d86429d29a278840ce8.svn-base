package com.tenwa.report.daoImpl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.tenwa.business.daoImpl.AbstractBaseDaoImpl;
import com.tenwa.report.dao.ReportDao;

@Repository("reportDao")
public class ReportDaoImpl extends AbstractBaseDaoImpl implements ReportDao {

	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	@Resource(name = "hibernateTemplate")
	private HibernateTemplate hibernateTemplate;


	@Resource(name="redisTemplate")
	private RedisTemplate<String, String> redisTemplate;
	
	@SuppressWarnings("unchecked")
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
