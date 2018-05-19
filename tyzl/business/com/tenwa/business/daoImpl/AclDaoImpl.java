package com.tenwa.business.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.tenwa.business.dao.AclDao;
import com.tenwa.business.entity.User;

@SuppressWarnings("unchecked")
@Repository(value="aclDao")
public class AclDaoImpl extends AbstractBaseDaoImpl   implements AclDao
{
	@Resource(name="jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	@Resource(name="redisTemplate")
	private RedisTemplate<String, String> redisTemplate;
	
	
	@Override
	@SuppressWarnings("unchecked")
	public RedisTemplate<String, String> getRedisTemplate() throws Exception {
		return this.redisTemplate;
	}
	
	@Override
	public User findUserByUserName(String username) throws Exception {
		if(null == username)return null;
		String hsqlQueryUserByUserName = "from User u where UPPER(u.username)=?";
		List<User> l = this.findResultsByHSQL(hsqlQueryUserByUserName,username.toUpperCase());
		if(l.size()>0)
		{
		   return l.get(0);
		}
		return null;
	}
	@Override
	public String getCurrentUserIndexPageUrl() throws Exception 
	{
        String queryAllIndexMenuUrl = "acl/index.bi";
		return queryAllIndexMenuUrl;
	}
	@Override
	public HibernateTemplate getHibernateTemplate() throws Exception {
		return this.hibernateTemplate;
	}
	
	@Override
	public JdbcTemplate getJdbcTemplate() throws Exception {
		return this.jdbcTemplate;
	}
}
