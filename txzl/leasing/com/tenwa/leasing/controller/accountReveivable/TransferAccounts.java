package com.tenwa.leasing.controller.accountReveivable;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.service.asset.AssetsMngService;

@Controller(value = "transferAccounts")
@RequestMapping(value = "/**/acl")
public class TransferAccounts {

	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "assetsMngService")
	private AssetsMngService assetsMngService;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/showTransferAccounts.acl")
	public String showAccountReceivable(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil
				.getRequestParameterMapByAjax(request);
		String opertype = model.get("opertype");
		//this.tableService.findEntityByID(entityClass, id)
		Map<String, String> variablesMap = new HashMap<String, String>();
			String contractid = model.get("contractid");
			ContractInfo contractinfo = this.tableService
					.findEntityByID(ContractInfo.class, contractid);
			variablesMap = this.tableService
					.getEntityPropertiesToStringMap(contractinfo, null, "contract_info");
			
			
			variablesMap.put("contract_info.proj_id", contractinfo.getProjId().getProjId());
			variablesMap.put("contract_info.projdate", contractinfo.getProjId().getProjDate());
			variablesMap.put("contract_info.custname", contractinfo.getCustId().getCustName());
			variablesMap.put("contract_info.projname", contractinfo.getProjectName());
			variablesMap.put("contract_info.custclass", contractinfo.getCustId().getCustClass().getCode());
			
			
		for (Entry entry : variablesMap.entrySet()) {
			request.setAttribute(entry.getKey().toString(), entry.getValue());
		}
		
		if ("view".equals(opertype)) {                                             
			        
			return "solutions/workflow/forms/factoring/transfer_accounts_receivable/account_transfer_info1.jsp";
			//return "solutions/workflow/forms/factoring/factoring_comm/contract_factoring_base_info.jsp";
		}else{
			return null;
		}
	}
}
