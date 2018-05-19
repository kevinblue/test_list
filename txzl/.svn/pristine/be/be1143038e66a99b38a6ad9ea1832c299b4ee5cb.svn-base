package com.tenwa.leasing.action.lawmng.law_approve;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.cust.CustInfoCompany;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.utils.WorkflowUtil;

@WorkflowAction(name = "lawApprovalStartAction", description = "流程开始动作", workflowName = "法务申请流程")
@Component(value = "lawApprovalStartAction")
public class LawApprovalStartAction implements JbpmBaseAction {
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		 /* 合同基本信息 */
		ContractInfo contractInfo = this.contractCommService.loadContractInfo(variablesMap);
		String lawRegSerialNumber = WorkflowUtil.getLawRegSerialNumber(null,tableService.getBussinessDao().getHibernateTemplate()
				, tableService.getBussinessDao().getJdbcTemplate());
		variablesMap.put("law_approval.lawnum",lawRegSerialNumber);
		this.contractCommService.loadContractEquip(contractInfo, variablesMap);
		
		/*处理法人代表内容初始化*/
		CustInfo custInfo = contractInfo.getCustId();
		Map<String, Object> propertiesMap = new HashMap<String,Object>();
		propertiesMap.put("custId", custInfo);
		if (custInfo.getCustClass().getCode().equals("CUST_INFO_COMPANY")) {
			CustInfoCompany custInfoCompany = this.tableService
					.findEntityByProperties(CustInfoCompany.class, propertiesMap).get(0);
			variablesMap.put("law_approval.linkad", custInfoCompany.getMailAddress());
			variablesMap.put("law_approval.legalperson", custInfoCompany.getPersonRep());
			variablesMap.put("law_approval.dbphone", custInfoCompany.getMoblie());
		}
		/*得出剩余租金*/
		String sql = "select rentplan.planrent-nvl(rentincome.incomerent,0) as balancerent "+
				"From VI_CONTRACT_INFO vic " +
				"left join "+
				"(select cfrp.contract_id,max(cfrp.rent_list) as planlist,sum(cfrp.rent) as planrent,sum(cfrp.corpus) as plancorpus,sum(cfrp.interest) as planinterest from contract_fund_rent_plan cfrp "+
				"group by cfrp.contract_id) rentplan on vic.ID=rentplan.contract_id "+
				"left join "+
				"(select cfri.contract_id,max(cfri.plan_list) as incomelist,sum(cfri.rent) as incomerent,sum(cfri.corpus) as incomecorpus,sum(cfri.interest) as incomeinterest from contract_fund_rent_income cfri "+
				"group by cfri.contract_id) rentincome on vic.ID=rentincome.contract_id "+
				"where vic.id='"+contractInfo.getId()+"'";
		Map<String, Object> map = this.tableService.queryListBySql(sql).get(0);
		variablesMap.put("law_approval.rent", map.get("balancerent").toString());
	}
	
	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		 
		return null;
	}

	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {}

	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}
	
	@Override
	public void back(HttpServletRequest request,
			Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
	}
}
