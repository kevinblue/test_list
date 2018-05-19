package com.tenwa.leasing.action.contract.contractOnhire;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.reckon.entity.contract.reckon.fund.ContractFundRentPlanBefore;
import com.reckon.entity.contract.reckon.fund.ContractFundRentPlanTemp;
import com.reckon.service.RentConditionDataOperator;
import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.service.fund.fundcomm.FundCommMethod;
import com.tenwa.leasing.utils.WorkflowUtil;

/**
 * @author Jason
 * @date 2013-4-24
 * @info
 */
@WorkflowAction(name = "contractOnhireStartAction", description = "流程开始动作", workflowName = "合同起租流程")
@Component(value = "contractOnhireStartAction")
public class ContractOnhireStartAction implements JbpmBaseAction {
	@Resource(name = "tableService")
	private TableService tableService;

	@Resource(name = "RentConditionDataService")
	private RentConditionDataOperator rentConditionData;
	
	@Resource(name = "fundCommMethod")
	private FundCommMethod fundCommMethod;
	
	
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	
	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		//删除流程互斥
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
		String contract_id = variablesMap.get("contract_id");
		
		variablesMap.put("workFlowFlag", WorkflowUtil.checkWorkFlowConflict(this.tableService, jbpmWorkflowHistoryInfo, contract_id)); 
		//将合同基本信息放入variablesMap
		ContractInfo contractInfo=this.contractCommService.loadContractInfo( variablesMap);
		CustInfo customerInfo=contractInfo.getCustId();
		
		//联合承租人
		contractCommService.loadContractUnionCust(contractInfo, variablesMap);
		

		//投放明细
		this.contractCommService.loadContractFundPut(contractInfo, variablesMap);
		
		//查询出原始合同的基本信息
		ContractInfo oldcinfo=this.tableService.findEntityByID(ContractInfo.class, contractInfo.getSupContractId());
		variablesMap.put("contract_info.contractid",oldcinfo.getContractId());
		variablesMap.put("contract_info.contractnumber",oldcinfo.getContractNumber());
		

		//租赁物件明细
		contractCommService.loadContractEquip(oldcinfo, variablesMap);
		//租金开票类型
		contractCommService.loadContractInvoice(oldcinfo, variablesMap);
		
		 //设置租金测算参数为合同审批
		String flowunid=jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid()+ "";;
		contractCommService.loadContractRentCalculationParam(contractInfo.getId(), customerInfo.getCustName(), customerInfo.getId(), flowunid,"onHire_process", variablesMap);
		//加载合同层的交易结构，租金计划，现在金流，财务租金计划，财务现金流	
		contractCommService.loadContractRentCalculationFromBefore(contractInfo, variablesMap);
		//将业务/财务租金计划从before表移往contract_fund_rent_plan_temp表中
		this.savaConditionDataFromBeforeToContract(contract_id, flowunid,contractInfo);
		
		Map<String,String>  queryMainObjectMap =new HashMap<String,String>();
		queryMainObjectMap.put("contractid", contractInfo.getId());
	    //资金计划历史
		this.fundCommMethod.initFundFundPlan("json_fund_plan_old_str", variablesMap, queryMainObjectMap);
	}

	@Override
	public void back(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		
	}
	
	/**
	 * 把涉及到租金计划的相关的数据从BEFORE表复制到合同的临时的表contract_fund_rent_plan_temp中
	 * @param contract_id
	 * @param doc_id
	 * @param projInfo
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public void savaConditionDataFromBeforeToContract(String contract_id, String doc_id,ContractInfo contract) throws Exception {
		
		Map<String, Object>	propertiesMap = new HashMap<String, Object>();
		propertiesMap.put("contractId", contract_id);
		
		propertiesMap.put("docId", doc_id);
		//移除临时表中的数据  CONTRACT_FUND_RENT_PLAN_TEMP
		String sql = " DELETE CONTRACT_FUND_RENT_PLAN_TEMP WHERE  DOC_ID = ? ";
		
		this.tableService.getBussinessDao().getJdbcTemplate().update(sql, doc_id);
		
		//查询BEFORE表租金计划
		String HSQL = " FROM com.reckon.entity.contract.reckon.fund.ContractFundRentPlanBefore BF WHERE  BF.contractId.id =  '"+contract_id+"'   ";
		
		List<ContractFundRentPlanBefore> before_l =  this.tableService.findResultsByHSQL(HSQL);
		for(ContractFundRentPlanBefore before : before_l){
			// 租金计划/会计租金计划
			List<ContractFundRentPlanTemp> contractFundRentPlanTemps = new ArrayList<ContractFundRentPlanTemp>();
			//复制数据
			ContractFundRentPlanTemp contractFundRentPlanTemp = new ContractFundRentPlanTemp();
			contractFundRentPlanTemp = (ContractFundRentPlanTemp)this.tableService.copyAndOverrideExistedValueFromObject(before, contractFundRentPlanTemp);
			contractFundRentPlanTemp.setContractId(contract.getContractId());
			contractFundRentPlanTemp.setDocId(doc_id);
			contractFundRentPlanTemps.add(contractFundRentPlanTemp);
			//写入
			this.tableService.saveAllEntities(contractFundRentPlanTemps);
		}
		if(before_l.size() <= 0){
			
		}
		
		
	} 
}
