
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.dao
 * 文件名：         JbpmDao.java
 * 版本信息：    1.0.0
 * 创建日期：     2012-10-23-下午03:26:43
 * Copyright：2012XX公司-版权所有
 **/

package com.tenwa.jbpm.dao;

import java.util.List;
import java.util.Map;

import org.jbpm.pvm.internal.repository.DeploymentImpl;

import com.tenwa.business.dao.BaseDao;
import com.tenwa.jbpm.entity.ActivityDetail;


 /**
 * 类名称：     JbpmDao
 * 类描述：     
 * 创建人：     tracywindy
 * 修改人：     tracywindy
 * 修改时间：2012-10-23 下午03:26:43
 * 修改备注：
 * @version 1.0.0
 **/

public interface JbpmDao extends BaseDao
{
	public Object[] getListByHSQLPage(String querySql,String countSql,int start,int limit,Object... values) throws Exception ;
	public Object[] getListByMultiHSQLPage(String[] querySqls,String[] countSqls,String[] paramPositions,int start,int limit,Object... values) throws Exception ;
	public List<Object>   getSinglObjectListByHSQL(String querySql,Object... values) throws Exception ;
	public Object[]   getSinglObjectListByHSQLPage(String querySql,String countSql,int start,int limit,Object... values) throws Exception ;
	public List<Map<String,String>> queryDeployedWorkflows() throws Exception;
	public DeploymentImpl deployWorkflow(String designedId) throws Exception ;
	public void removeDeployedWorkflow(String designedId,long deployId) throws Exception ;
	public byte[] getDeployedWorkflowDiagramDatas(String deployId) throws Exception ;
	public void saveChangeToActivity(ActivityDetail activityDetail) throws Exception ;
	public List<ActivityDetail> getActivitiesDetailListByDeployId(String deployId) throws Exception ;
}
