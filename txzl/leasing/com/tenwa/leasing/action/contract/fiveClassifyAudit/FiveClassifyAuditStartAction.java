package com.tenwa.leasing.action.contract.fiveClassifyAudit;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.json.JSONArray;
import org.json.JSONObject;

import com.tenwa.business.entity.base.BaseRoleBlock;
import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.fivecategoryapply.FiveCategoryApply;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.service.fund.fundcomm.FundCommMethod;
import com.tenwa.leasing.utils.WorkflowUtil;

/**   
*    
* 项目名称：tls5.1   
* 类名称：FiveCategoryStartAction   
* 类描述：   
* 创建人：rovine   
* 创建时间：2015年1月12日 上午10:33:19   
* @version        
*/
@WorkflowAction(name = "fiveClassifyAuditStartAction", description = "流程开始动作", workflowName = "资产分类审核上报")
@Component(value = "fiveClassifyAuditStartAction")
public class FiveClassifyAuditStartAction implements JbpmBaseAction {


	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	
	@Resource(name = "fundCommMethod")
	private FundCommMethod fundCommMethod;
	
	@Override
	public void back(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		WorkflowUtil.deleteWorkFlowConflict(this.tableService, variablesMap);  
		return null;
	}

	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		String startdatenew = variablesMap.get("startdate");
		String enddatenew = variablesMap.get("enddate");
		variablesMap.put("startdatenew",startdatenew);
		variablesMap.put("enddatenew",enddatenew);
		
		String contract_id = variablesMap.get("contract_id");
		String flowunid=jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid()+ "";
		variablesMap.put("workFlowFlag", WorkflowUtil.checkWorkFlowConflict(this.tableService, jbpmWorkflowHistoryInfo, contract_id)); 
		//1申请编号
		String applyid =WorkflowUtil.getAssetCategorySerialNumber(variablesMap, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
		//2申请人
		String user=variablesMap.get("login_realname");	
		System.out.println(user);
		//3申请时间
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式
		String applydate = dateFormat.format( now ); 
		System.out.println(applydate); 
		//4开始时间
		String startdate=variablesMap.get("startdate");		
		//5结束时间
		String enddate=variablesMap.get("enddate");	
		FiveCategoryApply fiveCategoryApply = new FiveCategoryApply();
		 variablesMap.putAll(this.tableService.getEntityPropertiesToStringMap(fiveCategoryApply, null, "fivecategoryapply"));
		 variablesMap.put("fivecategoryapply.applyuser",user);
		 variablesMap.put("fivecategoryapply.docid", flowunid);
		 variablesMap.put("fivecategoryapply.applynumber", applyid);
		 variablesMap.put("fivecategoryapply.applydate", applydate);
		 variablesMap.put("fivecategoryapply.startdate", startdate);
		 variablesMap.put("fivecategoryapply.enddate", enddate);
		/* SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd" );
		 Date date1 = sdf.parse( startdate );
		 Date date2 = sdf.parse( enddate );*/
		 Map<String,String> querymap = new HashMap<String,String>();
		 querymap.put("startdate",startdate);
		 querymap.put("enddate",enddate);
		 System.out.println(querymap);
		 
		 String json_five_classfy_apply_str=this.tableService.getJsonArrayData( "eleasing/jsp/other/five_category_apply_audit_detail.xml", querymap).toString();
		 JSONArray  jsa=new JSONArray(json_five_classfy_apply_str);
		 StringBuffer str=new StringBuffer();
		 int a=jsa.length();
		 System.out.println(a);
		 if(a!=0){
		   for(int i=0;i<jsa.length();i++){
			   JSONObject jsonobj = jsa.getJSONObject(i); 
			    	  str.append(jsonobj.getString("docid")).append(",");
			  
		   }
		 str.deleteCharAt(str.length()-1);
		 variablesMap.put("json_str", str.toString());
		 }
		 
		 variablesMap.put("json_five_classfy_apply_audit_str", json_five_classfy_apply_str);
		  
	
				
		
	}
}
