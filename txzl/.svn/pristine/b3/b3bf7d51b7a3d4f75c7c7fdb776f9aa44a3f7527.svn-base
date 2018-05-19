package com.tenwa.business.daoImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.tenwa.business.dao.ResourceDao;

@Repository(value = "resourceDao")
public class ResourceDaoImpl extends AbstractBaseDaoImpl implements ResourceDao {
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Resource(name = "hibernateTemplate")
	private HibernateTemplate hibernateTemplate;

	@Override
	public HibernateTemplate getHibernateTemplate() throws Exception {
		return this.hibernateTemplate;
	}

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
	public List<Map<String, String>> getAllWorkFlowName(String key ) throws Exception {
		String condition = "";
		if(key != null && !key.equals("")){
			condition = " WHERE t2.workflowName LIKE '%" + key + "%'";
		}
		String queryString = "SELECT new Map(t2.id as value, t2.workflowName as name) FROM DeploymentImpl t1 LEFT JOIN t1.jbpmWorkflowDesigner t2" + condition;
		return this.hibernateTemplate.find(queryString);
	}

	@Override
	public List<Map<String, String>> getWorkFlowPointByID(String id) throws Exception {
		String queryString = "select new Map(t1.id as value,  CONCAT(t1.activityName,'(', t1.activityAction, ',', t1.activityType,')') as name) from ActivityDetail t1   left join t1.deploymentImpl t2 left join t2.jbpmWorkflowDesigner t3 where t3.id = '"+id+"'";
		return this.hibernateTemplate.find(queryString);
	}
	
	
	
	
}
