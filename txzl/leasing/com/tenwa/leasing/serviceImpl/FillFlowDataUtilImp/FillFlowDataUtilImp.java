package com.tenwa.leasing.serviceImpl.FillFlowDataUtilImp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.tenwa.business.service.BaseService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.jbpm.utils.JBPMUtil;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.leasing.service.FillFlowDataUtil.FillFlowDataUtil;

@Service(value="FillFlowDataUtilImp")
public class FillFlowDataUtilImp implements FillFlowDataUtil {
	@Resource(name = "baseService")
	private BaseService baseService;
	@Override
	public void saveFillFlowDataUtil(HttpServletRequest request,String flowunid, String histaskid,String action)
			throws Exception {
		// TODO Auto-generated method stub
		JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo=null;
		jbpmWorkflowHistoryInfo=this.baseService.findEntityByID(JBPMWorkflowHistoryInfo.class,histaskid);
		Map<String, String> variablesMap=new HashMap<String, String>();
		variablesMap = JBPMUtil.getProcessInstanceHistoryData(this.baseService.getBussinessDao().getHibernateTemplate(), flowunid);
		JbpmBaseAction jbpmBaseAction = (JbpmBaseAction)WebUtil.getApplicationContext().getBean(action);
		jbpmBaseAction.end(request, variablesMap, jbpmWorkflowHistoryInfo)	 ;

	}
	@Override
	public void saveFillFlowData(HttpServletRequest request) throws Exception {
		Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String userid=model.get("currentTaskId");
		String cfield=model.get("changefield");
		String cdata=model.get("changedate");
		if(cfield.length()>1){
		String sql="select tj.id_ id FROM JBPM4_HIST_ACTINST  jh ";
		sql=sql+"  left outer join T_JBPM_WORKFLOW_INFO tj ";
		sql=sql+"  on tj.JBPM4_HIST_PROCINST_DBID_=jh.HPROCI_ ";
		sql=sql+"  left outer join t_jbpm_workflow_infos_users tu "; 
		sql=sql+"  on tu.jbpmworkflowhistoryinfo_id_=tj.id_ ";
		sql=sql+"  where jh.HTASK_='"+userid+"'  ";
		List rows = this.baseService.getBussinessDao().getJdbcTemplate().queryForList(sql);
		Iterator it = rows.iterator();
		String id="";	
		 //修改的域
		String[] vdata_key=cfield.split(",");
		//修改的值
		String[] vdata_value=cdata.split(",");
		if (it.hasNext()) {
			while (it.hasNext()) {
				Map dataMap = (Map) it.next();
			id=dataMap.get("id").toString();
		Map<String, String> variablesMap=new HashMap<String, String>();
		JBPMWorkflowHistoryInfo jwhi = (JBPMWorkflowHistoryInfo)this.baseService.findEntityByID(JBPMWorkflowHistoryInfo.class, id);
		String sourceKeyStr = new String(jwhi.getProcessedFormVariables());
		String sourceValueStr = new String(jwhi.getProcessedFormValues());
		Map<String,String>  map = JBPMUtil.getVaribalesMapByAllString(sourceKeyStr,sourceValueStr);
		//保存修改信息
		for(int i=0;i<vdata_key.length;i++){
		   map.put(vdata_key[i], vdata_value[i]);
		}
		StringBuffer sb_sourceKeyStr = new StringBuffer();
	    StringBuffer sb_sourceValueStr = new StringBuffer();
		JBPMUtil.getVaribalesAllStringByMap(map, sb_sourceKeyStr, sb_sourceValueStr);
	    String processedFormVariables =    sb_sourceKeyStr.toString();	
	    String processedFormValues    =    sb_sourceValueStr.toString();
	    jwhi.setProcessedFormVariables(processedFormVariables);
	    jwhi.setProcessedFormValues(processedFormValues);
	    this.baseService.saveEntity(jwhi);
		}	}
		}
	}

}