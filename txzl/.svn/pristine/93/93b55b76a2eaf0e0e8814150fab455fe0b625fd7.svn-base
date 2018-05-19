package com.tenwa.leasing.controller.lawlog;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tenwa.business.entity.User;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.cust.CustInfoCompany;
import com.tenwa.leasing.entity.lawmng.LawApproval;
import com.tenwa.leasing.service.lawImplemention.LawImplementionService;

@Controller(value = "lawLogController")
@RequestMapping(value = "/**/acl")
public class LawLogController {

	@Resource(name = "tableService")
	private TableService tableService;

	
	@RequestMapping(value = "/showLawLog.acl")
	public String showLawLog(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil
				.getRequestParameterMapByAjax(request);//获取请求数据
		User user = SecurityUtil.getPrincipal();
		String opertype = model.get("opertype");

		Map<String, String> variablesMap = new HashMap<String, String>();

		
		if("add".equals(opertype))	{
			
			variablesMap.put("lawnum",model.get("lawnum"));
			Map<String, Object> propertiesMap = new HashMap<String,Object>();
			propertiesMap.put("lawnum",model.get("lawnum"));
			LawApproval lawApproval = this.tableService
					.findEntityByProperties(LawApproval.class, propertiesMap).get(0);
			ContractInfo contractInfo = lawApproval.getContractInfo();  
			CustInfo custInfo =contractInfo.getCustId();
			CustInfoCompany custInfoCompany =custInfo.getCustInfoCompany();
			variablesMap.put("lawApproval", lawApproval.getId());
			variablesMap.put("contract_id", contractInfo.getContractId());
			variablesMap.put("contract_number",contractInfo.getContractNumber());
			variablesMap.put("cust_name", custInfo.getCustName());
			variablesMap.put("card_no", custInfoCompany.getOrgCode());
			variablesMap.put("projmanage",null);
			variablesMap.put("transfer",lawApproval.getTransfer());
			variablesMap.put("staffsugs",lawApproval.getStaffsugs());
			
		}
		
		for (Entry entry : variablesMap.entrySet()) {
			request.setAttribute(entry.getKey().toString(), entry.getValue());
		}
		
		return "solutions/workflow/forms/law_mng/law_log/law_log_list.jsp";
		
	}
	
}