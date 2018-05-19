package com.tenwa.leasing.action.fund.fund_account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.entity.User;
import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.kernal.utils.AppStaticUtil;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.contract.ContractSignatory;
import com.tenwa.leasing.entity.contract.ContractSupplierInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.fund.ContractFundChange;
import com.tenwa.leasing.service.contract.contractChange.ContractChangeService;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.utils.WorkflowUtil;

@WorkflowAction(name = "fundAccountEndAction", description = "流程结束动作", workflowName = "收款帐号变更")
@Component(value = "fundAccountEndAction")
public class FundAccountEndAction implements JbpmBaseAction {
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	
	@Override
	public void back(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		User user  =  SecurityUtil.getPrincipal();//当前人
		String doc_id = jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid()+"";
		variablesMap.put("docId", doc_id);
		WorkflowUtil.deleteWorkFlowConflict(this.baseService, variablesMap);
		ContractInfo contractInfo=this.contractCommService.loadContractInfo( variablesMap);
		ContractFundChange cfc = new ContractFundChange();
		
		cfc.setCreator(user);
		String id = variablesMap.get("contract_id");
		ContractInfo oo = new ContractInfo();
		oo.setId(id);
		cfc.setContractId(oo);
		cfc.setCreateDate(DateUtil.getSystemDateTime());
		cfc.setAccBank(variablesMap.get("contract_signatory.clientaccbank"));
		cfc.setAccName(variablesMap.get("contract_signatory.clientaccname"));
		cfc.setAccNumber(variablesMap.get("contract_signatory_clientaccnumber"));
		if(contractInfo.getLeasForm().getCode().equals("lease_form2")){
			cfc.setLessee(variablesMap.get("contract_signatory.client"));
		}else if(contractInfo.getLeasForm().getCode().equals("lease_form6")){
			cfc.setLessee(variablesMap.get("contract_signatory.client"));
		}else{
			cfc.setSeller(variablesMap.get("contract_signatory.client"));
		}
		tableService.saveOrUpdateEntity(cfc);
		
		 String  leas_form=contractInfo.getLeasForm().getId();
		 Map<String,Object> propertiesMap = new HashMap<String,Object>();
		 if(leas_form.matches("lease_form2|lease_form6")){//承租人
		      propertiesMap.clear();
		      propertiesMap.put("contractId", contractInfo);//条件根据合同号ID查询出合同各方表中的数据ContractSignatory
		      List<ContractSignatory> csList = this.tableService.findEntityByProperties(ContractSignatory.class, propertiesMap);
		      ContractSignatory cst=null;
		      for(int i=0;csList.size()>i;i++){
		    	  cst=csList.get(0);
		    	  if(cst!=null){
		    		  cst.setClient(variablesMap.get("contract_signatory.client1"));
		    		  cst.setClientAccBank(variablesMap.get("contract_signatory.clientaccbank1"));
		    		  cst.setClientAccName(variablesMap.get("contract_signatory.clientaccname1"));
		    		  cst.setClientAccNumber(variablesMap.get("contract_signatory.clientaccnumber1"));
		    		  tableService.updateEntity(cst);
		    		  break;
		    	  }
		      }

		 }
		else  if(leas_form.matches("lease_form1|leas_form7")){//供应商
		      propertiesMap.clear();
		      propertiesMap.put("contractId", contractInfo);//条件根据合同号ID查询出多个供应商
		      List<ContractSupplierInfo> accountList = this.tableService.findEntityByProperties(ContractSupplierInfo.class, propertiesMap);
		      ContractSupplierInfo csinfo=null;
		       for(int i=0;accountList.size()>i;i++){
		          csinfo=accountList.get(0);
		          if(csinfo!=null){
		              CustInfo custinfo=this.tableService.findEntityByID(CustInfo.class, csinfo.getSeller());
		              custinfo.setCustName(variablesMap.get("contract_signatory.client1"));
		              csinfo.setSellerAccBank(variablesMap.get("contract_signatory.clientaccbank1"));
		              csinfo.setSellerAccName(variablesMap.get("contract_signatory.clientaccname1"));
		              csinfo.setSellerAccNumber(variablesMap.get("contract_signatory.clientaccnumber1"));
		              tableService.updateEntity(custinfo);
		              tableService.updateEntity(csinfo);
		          }
		     }
		}
	}
	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

		return null;
	}

	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

}
