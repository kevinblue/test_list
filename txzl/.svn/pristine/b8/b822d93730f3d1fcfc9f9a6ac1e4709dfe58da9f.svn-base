package com.tenwa.leasing.action.windfarmreport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.contract.ContractInvoiceType;
import com.tenwa.leasing.entity.contract.ContractSignatory;
import com.tenwa.leasing.entity.contract.ContractSupplierInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.global.AnnualOperation;
import com.tenwa.leasing.entity.global.GlobalContractMessage;
import com.tenwa.leasing.entity.proj.ContractNumberSetting;
import com.tenwa.leasing.entity.proj.ProjDevelopInfo;
import com.tenwa.leasing.entity.proj.ProjEquip;
import com.tenwa.leasing.entity.proj.ProjInfo;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.service.contractcomm.ContractInfoExtends;
import com.tenwa.leasing.utils.WorkflowUtil;

/**
 * @author Jason
 * @date 2013-4-24
 * @info
 */
@WorkflowAction(name = "windFarmReporttartAction", description = "流程开始动作", workflowName = "风电场报告")
@Component(value = "windFarmReporttartAction")
public class WindFarmReportStartAction implements JbpmBaseAction {
	@Resource(name = "tableService")
	private TableService tableService;
		
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	
	

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
		String getvalue = variablesMap.get("proj_id");
		String[] getvalueArray = getvalue.split(";");
		Map<String,Object>propertiesMap=new HashMap<String,Object>();
		if(getvalueArray.length==2){
			variablesMap.put("reportkeyword","年度报告");
			variablesMap.put("reportdate",getvalueArray[1]);
			variablesMap.put("reportkeywordview",getvalueArray[1]+"风电场年报");
		}else{
			String projid = getvalueArray[0];
			if(getvalueArray.length==4){
				String globalcontractmessageid = getvalueArray[3];
				GlobalContractMessage GlobalContractMessage = this.tableService.findEntityByID(GlobalContractMessage.class, globalcontractmessageid);
				variablesMap.put("json_longitude_str",GlobalContractMessage.getWfX());//经度
				variablesMap.put("json_latitude_str",GlobalContractMessage.getWfY());//纬度
			}
			String windfarmreportmonth = getvalueArray[1];
			variablesMap.put("proj_id", projid);
			ProjInfo projinfo =this.tableService.findEntityByID(ProjInfo.class, projid);
			String w = projinfo.getCustInfo().getId();
			CustInfo custinfo = this.tableService.findEntityByID(CustInfo.class,w);
			String developid =  projinfo.getDevelopid().getId();
			ProjDevelopInfo projdevelopinfo = this.tableService.findEntityByID(ProjDevelopInfo.class,developid);
			variablesMap.put("windfarmplace",projdevelopinfo.getProvince().getName()+","+projdevelopinfo.getCity().getName());//风场位置
			variablesMap.put("windtopography",projdevelopinfo.getWindTopography().getName());//地形
			variablesMap.put("fantype",projdevelopinfo.getFanType());//机型
			variablesMap.put("fansum",projdevelopinfo.getFanSum());//数量
			variablesMap.put("productiontime",projdevelopinfo.getProductionTime());//投产时间
			propertiesMap.put("projId", projinfo);
			List<ContractInfo>  contractinfolist=this.tableService.findEntityByProperties(ContractInfo.class, propertiesMap);
			ContractInfo contractinfo = contractinfolist.get(0);
			variablesMap.put("reportkeyword","月度报告");//报表关键字
			variablesMap.put("reportkeywordview",windfarmreportmonth+"风电场月报");
		    variablesMap.put("reportdate",windfarmreportmonth);//报表日期
			variablesMap.put("selectedprojid",projid);//项目id
			variablesMap.put("json_contract_number_str",contractinfo.getContractNumber());//业务合同编号
			variablesMap.put("json_proj_name_str",projinfo.getProjectName());//项目名称
			String ee = custinfo.getCustName();
			variablesMap.put("json_lessee_str", custinfo.getCustName());//承租人
		}
	}
	@Override
	public void back(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		 

	}
}
