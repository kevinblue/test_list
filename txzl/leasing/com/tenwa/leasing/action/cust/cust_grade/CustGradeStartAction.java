package com.tenwa.leasing.action.cust.cust_grade;

import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.leasing.entity.cust.CustGrade;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.cust.CustTypeInfo;
import com.tenwa.leasing.entity.proj.ProjInfo;
import com.tenwa.leasing.service.Proj.proComm.ProCommService;
import com.tenwa.leasing.service.Proj.projCredit.ProjCreditService;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.utils.WorkflowUtil;


@WorkflowAction(name = "custGradeStartAction", description = "流程开始动作", workflowName = "客户信用评级审核")
@Component(value = "custGradeStartAction")
public class CustGradeStartAction implements JbpmBaseAction {

	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	
	@Override
	public void start(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		String custid = variablesMap.get("custid");
		
		//流程冲突
		variablesMap.put("workFlowFlag", WorkflowUtil.checkWorkFlowConflict(this.tableService, jbpmWorkflowHistoryInfo, custid)); 
	    //String  number_no = WorkflowUtil.getCustInfoSerialNumber(variablesMap,this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
		//variablesMap.put("cust_info.number_no", number_no);
		CustInfo custInfo = this.tableService.findEntityByID(CustInfo.class, custid);
		//客户类别，尹神指导
		Set<CustTypeInfo> custTypeInfo=custInfo.getCustType();
		String str="";
		for(CustTypeInfo type:custTypeInfo){
			String str1= type.getCustType().getName();
			str+=str1+",";
		}
		str = str.substring(0,str.length()-1);
		variablesMap.put("custInfoTojsp", custInfo.getId());
		variablesMap.put("cust_info.custname", custInfo.getCustName());
		variablesMap.put("cust_info.custnumber", custInfo.getCustNumber());
		String time = DateUtil.getSystemDate();
		variablesMap.put("cust_grade.createdate",time);
		if (custInfo.getCustClass().getCode().equals("CUST_INFO_COMPANY")) {
			if(custInfo.getCustInfoCompany().getCustScale()!=null){
				String cust_scale = custInfo.getCustInfoCompany().getCustScale().getName();
				variablesMap.put("cust_info.custscale", cust_scale);
			}
			//周神更改后存在一定的问题，这个解封可以添加与金风关系，但是供应商里这个字段不是必填所以有点问题，先隐藏了
			String group_relationship = null;
			if(custInfo.getCustInfoCompany().getGroupRelationship()==null){
				group_relationship =" ";
			}else{
			group_relationship = custInfo.getCustInfoCompany().getGroupRelationship().getName();
			}
			variablesMap.put("cust_info.group_relationship", group_relationship);
			variablesMap.put("cust_info.custkind",
					custInfo.getCustInfoCompany().getCustKind() == null ? "" : 
					custInfo.getCustInfoCompany().getCustKind().getName() );
			variablesMap.put("cust_info.personrep",custInfo.getCustInfoCompany().getPersonRep());
			variablesMap.put("cust_info.code",custInfo.getCustInfoCompany().getOrgCode());
			variablesMap.put("cust_info.custclass",str);
		} else {
			variablesMap.put("cust_info.custkind",
					custInfo.getCustInfoPerson().getCustKind() == null ? "" : 
					custInfo.getCustInfoPerson().getCustKind().getName() );
			variablesMap.put("cust_info.code",custInfo.getCustInfoPerson().getIdCardNo());
			variablesMap.put("cust_info.custclass",str);
		}
		
		
/*		String custinfo=contractCommService.getcustinfo(custid);
		JSONArray jsonarray=new JSONArray(custinfo);
		for(int i=0;i<jsonarray.length();i++){
			JSONObject jsonobject=jsonarray.getJSONObject(i);
			variablesMap.put("cust_info.belongingpeoplename", jsonobject.getString("username1"));
			variablesMap.put("cust_info.deptname", jsonobject.getString("deptname1"));
			variablesMap.put("cust_info.deptrote", jsonobject.getString("deptroute1"));*/
			//variablesMap.put("cust_info.rentmoney", jsonobject.getString("rent"));
			//variablesMap.put("cust_info.corpusmoney", jsonobject.getString("corpus"));}
	}
	

	@Override
	public void back(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		 
		
	}

	@Override
	public void end(HttpServletRequest request,	Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		 
		
	}

	@Override
	public String save(HttpServletRequest request,	Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
	
		return null;
	}

	@Override
	public String delete(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		//删除流程冲突第二步
		WorkflowUtil.deleteWorkFlowConflict(this.tableService, variablesMap); 
		return null;
	}

	
	
	
}
