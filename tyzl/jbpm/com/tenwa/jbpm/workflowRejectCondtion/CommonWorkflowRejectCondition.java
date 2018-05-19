
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.workflowRejectCondtion
 * 文件名：         CommonWorkflowRejectCondition.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-10-21-下午04:58:07
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.jbpm.workflowRejectCondtion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tenwa.business.service.BaseService;
import com.tenwa.jbpm.entity.WorkflowDesignerReject;
import com.tenwa.kernal.annotation.WorkflowRejectCondition;
import com.tenwa.kernal.utils.StringUtil;


 /**
 * 类名称：     CommonWorkflowRejectCondition
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-10-21 下午04:58:07
 * 修改备注：
 * @version 1.0.0
 **/
@Component(value="commonWorkflowRejectCondition")
@WorkflowRejectCondition(
	name="commonWorkflowRejectCondition",
	description="通用互斥CLASS",
	sourceWorkflowName = "流程",
	rejectWorkflowName="流程"
)
public class CommonWorkflowRejectCondition implements RejectCondition{
	@Resource(name="baseService")
	private BaseService baseService;
	
	public BaseService getBaseService() {
		return baseService;
	}
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	@Override
	public RejectInfo reject(WorkflowDesignerReject workflowDesignerReject,Map<String, String> parameterMap) throws Exception 
	{
		  String rejectWorkflowDefinitionId  = workflowDesignerReject.getRejectJbpmWorkflowDesigner().getWorkflowDefinition();
   	      String hsql="select distinct jhi.workflowName from JBPMWorkflowHistoryInfo jhi left join jhi.jbpmWorkflowHistoryInfoUsers jhiuser" +
	   		" where  jhi.historyTaskInstanceImpl is not null " +
	   		" and jhiuser.endTime is null " +
	   		" and jhi.processDefinitionId = :processDefinitionId ";
   	      //String[] keys = new  String[]{"One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten"};
		  String keyOneParam = StringUtil.nullToString(workflowDesignerReject.getQueryConditions()).trim();
		  
		  List<String> paramNamesList = new ArrayList<String>();
		  List<Object> valuesList = new ArrayList<Object>();
		  paramNamesList.add("processDefinitionId");
		  valuesList.add(rejectWorkflowDefinitionId);
		  if(!keyOneParam.isEmpty()){
			  //keyOneParam = "keyOne";
			  hsql+="and jhi.keyTen = :"+keyOneParam;
			  paramNamesList.add(keyOneParam);
			  String keyOneValue = parameterMap.get(keyOneParam);
			  valuesList.add(keyOneValue);
		  }
		  String[] paramNames = new String[paramNamesList.size()];
		  paramNames = paramNamesList.toArray(paramNames);
		  Object[] values = new Object[valuesList.size()];
		  values = valuesList.toArray(values);
		  
	      final List<String> rs_list = baseService.findResultsByNamedParamHSQL(hsql, paramNames, values);
	     
	      return new RejectInfo(){
			@Override
			public boolean isRejected() throws Exception {
				if(rs_list.size()>0){
					return true;
			    }
				return false;
			}
			@Override
			public String returnedRejectInfo() throws Exception {
				return StringUtil.join(rs_list, ",");
			}
	     };
	}

}
