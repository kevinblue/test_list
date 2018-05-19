
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.daoImpl
 * 文件名：         JbpmDaoImpl.java
 * 版本信息：    1.0.0
 * 创建日期：     2012-10-23-下午03:29:35
 * Copyright：2012XX公司-版权所有
 **/

package com.tenwa.jbpm.daoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jbpm.api.RepositoryService;
import org.jbpm.pvm.internal.repository.DeploymentImpl;
import org.jbpm.pvm.internal.repository.DeploymentProperty;
import org.jdom.Document;
import org.jdom.Element;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.tenwa.business.daoImpl.AbstractBaseDaoImpl;
import com.tenwa.jbpm.utils.JBPMUtil;
import com.tenwa.jbpm.dao.JbpmDao;
import com.tenwa.jbpm.entity.ActivityDetail;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfoUser;
import com.tenwa.jbpm.entity.JBPMWorkflowReadUser;
import com.tenwa.jbpm.entity.JBPMWorkflowSignatureUser;
import com.tenwa.jbpm.entity.JbpmWorkflowDesigner;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.StringUtil;
import com.tenwa.kernal.utils.UUIDUtil;
import com.tenwa.kernal.utils.XmlUtil;


 /**
 * 类名称：     JbpmDaoImpl
 * 类描述：     
 * 创建人：     tracywindy
 * 修改人：     tracywindy
 * 修改时间：2012-10-23 下午03:29:35
 * 修改备注：
 * @version 1.0.0
 **/

@Repository(value="jbpmDao")
public class JbpmDaoImpl extends AbstractBaseDaoImpl    implements JbpmDao
{
	@Resource(name="jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	@Resource(name="redisTemplate")
	private RedisTemplate<String, String> redisTemplate;
	
	
	@SuppressWarnings({"unchecked"})
	@Override
	public RedisTemplate<String, String> getRedisTemplate() throws Exception {
		return this.redisTemplate;
	}
	@Override
	public JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}
	@Override
	public HibernateTemplate getHibernateTemplate() throws Exception {
		return this.hibernateTemplate;
	}
	@SuppressWarnings("unchecked")
	@Override
	public DeploymentImpl deployWorkflow(String designedId) throws Exception 
	{
		RepositoryService repositoryService = JBPMUtil.getRepositoryService();
		JbpmWorkflowDesigner jbpmWorkflowDesigner = this.getHibernateTemplate().load(JbpmWorkflowDesigner.class, designedId);
		String xmlContent = new String(jbpmWorkflowDesigner.getTransferedJpdlXml());
		DeploymentImpl deploymentImpl = (DeploymentImpl)repositoryService.createDeployment();
		deploymentImpl.setJbpmWorkflowDesigner(jbpmWorkflowDesigner);
		String resourceName = UUIDUtil.getUUID()+".jpdl.xml";
		deploymentImpl.addResourceFromString(resourceName, xmlContent).deploy();
		jbpmWorkflowDesigner.setDeploymentImpl(deploymentImpl);
		jbpmWorkflowDesigner.setDeployedTime(DateUtil.getSystemDateTime());
		this.getHibernateTemplate().save(jbpmWorkflowDesigner);
		
		Document document = XmlUtil.readXML(xmlContent, true);
		Element root = document.getRootElement();
		List<Element> childrenElements = (List<Element>)root.getChildren();
		for(int i=0;i<childrenElements.size();i++)
		{
			Element childElement = childrenElements.get(i);
			String activityType = childElement.getName().toLowerCase();
			if(   (!"start".equals(activityType))
				&&(!"task".equals(activityType))
				&&(!activityType.startsWith("end")))
			{
				continue;
			}
			ActivityDetail activityDetail = new ActivityDetail();
			activityDetail.setDeploymentImpl(deploymentImpl);
			String activityName = StringUtil.nullToString(childElement.getAttributeValue("activityName"));
			String activityKey = StringUtil.nullToString(childElement.getAttributeValue("name"));
			String activityPosition = StringUtil.empty2Other(childElement.getAttributeValue("position"),"1");
			activityDetail.setActivityName(activityName);
            activityDetail.setActivityType(activityType);
            activityDetail.setIsAttachmentChecked(false);
            activityDetail.setEnabled(new Integer(1));
            activityDetail.setActivityKey(activityKey);
            activityDetail.setActivityPosition(Integer.valueOf(activityPosition));
            activityDetail.setIsNeedAdviseExclude(false);
            this.getHibernateTemplate().save(activityDetail);
		}
		document = null;
		XmlUtil.closeLocalResources();
		return deploymentImpl;
	}
	@SuppressWarnings("unchecked")
	@Override
	public void removeDeployedWorkflow(String designedId,long deployId) throws Exception 
	{
	   JbpmWorkflowDesigner jbpmWorkflowDesigner = this.getHibernateTemplate().get(JbpmWorkflowDesigner.class,designedId);
	   jbpmWorkflowDesigner.setDeploymentImpl(null);
	   this.getHibernateTemplate().update(jbpmWorkflowDesigner);
	   
	   String queryString_JBPMWorkflowReadUser = " from JBPMWorkflowReadUser jwhiu where jwhiu.jbpmWorkflowHistoryInfo.deploymentImpl.dbid  = ? ";
	   List<JBPMWorkflowReadUser> removed_JBPMWorkflowReadUsers =(List<JBPMWorkflowReadUser>)this.getHibernateTemplate().find(queryString_JBPMWorkflowReadUser, deployId);
	   this.getHibernateTemplate().deleteAll(removed_JBPMWorkflowReadUsers);
	   this.getHibernateTemplate().flush();
	   
	   String queryString_JBPMWorkflowSignatureUser = " from JBPMWorkflowSignatureUser jwhiu where jwhiu.jbpmWorkflowHistoryInfo.deploymentImpl.dbid  = ? ";
	   List<JBPMWorkflowSignatureUser> removed_JBPMWorkflowSignatureUsers =(List<JBPMWorkflowSignatureUser>)this.getHibernateTemplate().find(queryString_JBPMWorkflowSignatureUser, deployId);
	   this.getHibernateTemplate().deleteAll(removed_JBPMWorkflowSignatureUsers);
	   this.getHibernateTemplate().flush();
	   
	   String queryString_JBPMWorkflowHistoryInfoUser = " from JBPMWorkflowHistoryInfoUser jwhiu where jwhiu.jbpmWorkflowHistoryInfo.deploymentImpl.dbid  = ? ";
	   List<JBPMWorkflowHistoryInfoUser> removed_JBPMWorkflowHistoryInfoUsers =(List<JBPMWorkflowHistoryInfoUser>)this.getHibernateTemplate().find(queryString_JBPMWorkflowHistoryInfoUser, deployId);
	   this.getHibernateTemplate().deleteAll(removed_JBPMWorkflowHistoryInfoUsers);
	   this.getHibernateTemplate().flush();
	   
	   this.jdbcTemplate.update("update t_jbpm_activity_detail set JBPM4_DEPLOYMENT_DBID_ = null where JBPM4_DEPLOYMENT_DBID_= ?",deployId);
	   /*String updateString_DeploymentInfo = "delete from  DeploymentInfo di where di.deploymentImpl.dbid =?";
	   this.getHibernateTemplate().bulkUpdate(updateString_DeploymentInfo, deployId);*/
	   JBPMUtil.getRepositoryService().deleteDeploymentCascade(deployId+"");
	   String updateString_JBPMWorkflowHistoryInfo = "delete from  JBPMWorkflowHistoryInfo jwhi where jwhi.deploymentImpl.dbid =?";
	   this.getHibernateTemplate().bulkUpdate(updateString_JBPMWorkflowHistoryInfo, deployId);
	   String updateString_ActivityDetail = "delete from  ActivityDetail ad where ad.deploymentImpl.dbid is null";
	   this.getHibernateTemplate().bulkUpdate(updateString_ActivityDetail);
	}

	@Override
	public byte[] getDeployedWorkflowDiagramDatas(String deployId) throws Exception {
		DeploymentImpl deploymentImpl = (DeploymentImpl)JBPMUtil.getRepositoryService().createDeploymentQuery().deploymentId(deployId).uniqueResult();
		for(String resourceName : deploymentImpl.getResourceNames())
		{
			if(resourceName.toString().indexOf(".png")>-1)
			{
				return deploymentImpl.getBytes(resourceName);
			}
		}
		return new byte[0];
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> queryDeployedWorkflows() throws Exception 
	{
		List<Map<String,String>> deployInfoDetails = new ArrayList<Map<String,String>>();
		
		String queryString = "from DeploymentImpl";
		List<DeploymentImpl> deployImpls = (List<DeploymentImpl>)this.getHibernateTemplate().find(queryString);
		for(int i=0;i<deployImpls.size();i++)
		{
        	Map<String,String> deployQueryInfo = new HashMap<String,String>();
        	deployQueryInfo.put("deployId","");
        	deployQueryInfo.put("processDefinitionId", "");
			deployQueryInfo.put("processDefinitionKey", "");
			deployQueryInfo.put("processDefinitionVersion","");
			deployQueryInfo.put("processDefinitionDescription","");
			
			DeploymentImpl deployImpl = deployImpls.get(i);
			Iterator<DeploymentProperty> DeploymentPropertys = deployImpl.getObjectProperties().iterator();
			
			if(DeploymentPropertys.hasNext())
			{
			   String workflowObjectName = DeploymentPropertys.next().getObjectName();
			   String deployId            = String.valueOf(deployImpl.getDbid());
			   String processDefinitionId = deployImpl.getProcessDefinitionId(workflowObjectName);
			   String processDefinitionKey = deployImpl.getProcessDefinitionKey(workflowObjectName);
			   String processDefinitionVersion = String.valueOf(deployImpl.getProcessDefinitionVersion(workflowObjectName));
			   String processDefinitionDescription = JBPMUtil.getRepositoryService().createProcessDefinitionQuery().deploymentId(deployId).uniqueResult().getDescription();
	        	
			   deployQueryInfo.put("deployId", deployId);
	           deployQueryInfo.put("processDefinitionId", processDefinitionId);
			   deployQueryInfo.put("processDefinitionKey",processDefinitionKey);
			   deployQueryInfo.put("processDefinitionVersion",processDefinitionVersion);
			   deployQueryInfo.put("processDefinitionDescription",processDefinitionDescription);
			   deployInfoDetails.add(deployQueryInfo);
			}
		}
		return deployInfoDetails;
	}
	@Override
	public void saveChangeToActivity(ActivityDetail activityDetail)
			throws Exception {
		this.getHibernateTemplate().update(activityDetail);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityDetail> getActivitiesDetailListByDeployId(String deployId) throws Exception 
	{
		return (List<ActivityDetail>)this.getHibernateTemplate().find("from ActivityDetail ad where  ad.deploymentImpl.dbid =?",Long.parseLong(deployId));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getSinglObjectListByHSQL(String querySql,
			Object... values) throws Exception  {
		return (List<Object>)this.getHibernateTemplate().find(querySql, values);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object[] getListByHSQLPage(final String querySql,final String countSql,final int start,
			final int limit, final Object... values) throws Exception {
		   Object[] objs = new Object[2];
		   objs[0] = this.getHibernateTemplate().find(countSql,values).get(0);
		   objs[1] = this.getHibernateTemplate().executeFind(new HibernateCallback<List<Object[]>>(){
			@Override
			public List<Object[]> doInHibernate(Session session)
					throws HibernateException, SQLException {
                Query queryObject = session.createQuery(querySql);
                if(values != null)
                {
                    for(int i = 0; i < values.length; i++)
                    {
                        queryObject.setParameter(i, values[i]);
                    }
                }
                queryObject.setFirstResult(start);
                queryObject.setMaxResults(limit);
                return (List<Object[]>)queryObject.list();
			}
		});
		return objs;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object[] getSinglObjectListByHSQLPage(final String querySql,final String countSql,
			final int start,final  int limit,final  Object... values) throws Exception {
		   Object[] objs = new Object[2];
		   objs[0] = this.getHibernateTemplate().find(countSql,values).get(0);
		   objs[1] = this.getHibernateTemplate().executeFind(new HibernateCallback<List<Object>>(){
				@Override
				public List<Object> doInHibernate(Session session)
						throws HibernateException, SQLException {
	                Query queryObject = session.createQuery(querySql);
	                if(values != null)
	                {
	                    for(int i = 0; i < values.length; i++)
	                    {
	                        queryObject.setParameter(i, values[i]);
	                    }
	                }
	                queryObject.setFirstResult(start);
	                queryObject.setMaxResults(limit);
	                return (List<Object>)queryObject.list();
				}
			});
		   return objs;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Object[] getListByMultiHSQLPage(final String[] querySqls, String[] countSqls,final String[] paramPositions,
			final int start,final int limit,final Object... values) throws Exception {
		   Object[] objs = new Object[2];
		   long tempLong = 0;
		   final List<Object[]> tempResults = new ArrayList<Object[]>();
		   for(int i=0;i<countSqls.length;i++)
		   {
			   final String countSql = countSqls[i];
			   final String querySql = querySqls[i];
			   
			   String[] pos      = paramPositions[i].split("-");
			   final int startPos = Integer.parseInt(pos[0]);
			   final int endPos  = Integer.parseInt(pos[1]);
			  
			   if( (endPos-startPos)>-1)
			   {
				   final int len = endPos-startPos+1;
				   tempLong += (Long)this.getHibernateTemplate().executeFind(new HibernateCallback<List<Long>>(){
						@Override
						public List<Long> doInHibernate(Session session)
								throws HibernateException, SQLException {
						  Query countObject = session.createQuery(countSql);
			              Query queryObject = session.createQuery(querySql);
			              if(values != null)
			              {
			                  for(int j = 0; j < len; j++)
			                  {
			                	  Object value = values[startPos+j];
			                	  countObject.setParameter(j,value );
			                      queryObject.setParameter(j,value );
			                  }
			              }
			              queryObject.setFirstResult(start);
			              queryObject.setMaxResults(limit);
			              tempResults.addAll(queryObject.list());
			              return (List<Long>)countObject.list();
						}
					}).get(0);
				   continue;
			   }
			   tempLong += (Long)this.getHibernateTemplate().find(countSql).get(0);
			   tempResults.addAll(this.getHibernateTemplate().find(querySql));
		   }
		   objs[0] = Long.valueOf(tempLong);
		   objs[1] = tempResults;
		return objs;
	}
}
