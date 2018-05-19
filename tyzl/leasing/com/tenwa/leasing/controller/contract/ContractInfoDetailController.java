package com.tenwa.leasing.controller.contract;


import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.contract.ContractInvoiceType;
import com.tenwa.leasing.entity.cust.CustInfo;



/**
 * 银行信息
 * @Title: ContractInfoDetailController.java
 * @package: com.tenwa.leasing.controller.contract
 * @author: lichaojie
 * @date 2014年11月20日 上午9:27:24
 * @version V5.1
 */
@Controller(value = "contractInfoDetailController")
@RequestMapping(value = "/**/acl")
public class ContractInfoDetailController {
	
	@Resource(name = "tableService")
	private TableService tableService;
	
	@RequestMapping(value = "/queryContractInfoDetail.acl")
	public String queryContractInfoDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		String contractid = model.get("contractid");
		ContractInfo contractInfo =(ContractInfo) this.tableService.findEntityByID(ContractInfo.class, contractid);
		CustInfo customerInfo = contractInfo.getCustId();
		
		Map<String,String> variablesMap = new HashMap<String, String>();
		
		variablesMap.putAll(this.tableService.getEntityPropertiesToStringMap(contractInfo, null, "contract_info"));
		String cust_class = customerInfo.getCustClass().getCode();
		variablesMap.put("contract_info.custname", customerInfo.getCustName());
		variablesMap.put("contract_info.custclass", cust_class);
		variablesMap.put("contract_info.oldcustname", customerInfo.getCustName());//旧承租人name(起租后合同变更使用)
		variablesMap.put("contract_info.oldcustid", customerInfo.getId());//旧承租人id(起租后合同变更使用)
		variablesMap.put("contract_info.custid", customerInfo.getId());//新承租人id
		variablesMap.put("rawValue_contract_info.custid", customerInfo.getCustName());//新承租人名字
		variablesMap.put("contract_info.custnumber", customerInfo.getCustNumber());
		variablesMap.put("contract_info.proj_id", contractInfo.getProjId() == null ? "" : contractInfo.getProjId().getProjId());
		variablesMap.put("contract_info.projdate", contractInfo.getProjId() == null ? "" : contractInfo.getProjId().getProjDate());
		variablesMap.put("contract_info.othercondition", contractInfo.getOtherCondition());
		
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		
		//租金计划
		queryMainObjectMap.put("contract_id", contractid);
		queryMainObjectMap.put("plan_date",variablesMap.get("fund_ebank_data.factdate"));
		String json_rent_income_plan_str = this.tableService.getJsonArrayData("eleasing/workflow/rent/rent_comm/rent_plan.xml", queryMainObjectMap).toString();
		variablesMap.put("json_rent_income_plan_str", json_rent_income_plan_str);
		
		//租金回笼历史
		queryMainObjectMap.put("orderbyclause", "order by income.planlist asc");
		String json_rent_income_his_str = this.tableService.getJsonArrayData("eleasing/workflow/rent/rent_comm/rent_income.xml", queryMainObjectMap).toString();
		variablesMap.put("json_rent_income_his_str", json_rent_income_his_str);
		
		//资金收付款计划
		queryMainObjectMap.clear();
		queryMainObjectMap.put("contractid", contractid);
		queryMainObjectMap.put("paytype", "pay_type_in");
		String json_collection_plan_str = this.tableService.getJsonArrayData("eleasing/workflow/fund/fundcomm/fund_fund_plan_list.xml", queryMainObjectMap).toString();
		variablesMap.put("json_collection_plan_str", json_collection_plan_str);
		
		//资金收付款历史
		queryMainObjectMap.clear();
	    queryMainObjectMap.put("contractid", contractid);
	    queryMainObjectMap.put("paytype", "pay_type_in");
		String json_collection_his_str = this.tableService.getJsonArrayData("eleasing/workflow/fund/fundcomm/fund_fund_charge_list.xml", queryMainObjectMap).toString();
		variablesMap.put("json_collection_his_str", json_collection_his_str);
		
		//租赁物件明细
		queryMainObjectMap.clear();
	    queryMainObjectMap.put("contract_id", contractid);
		String json_contract_equip_str = this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_change/contract_equip.xml", queryMainObjectMap).toString();
		variablesMap.put("json_contract_equip_str",json_contract_equip_str);

		//担保单位信息
		String json_contract_guarantee_method_str = this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_sup/contract_guarantee_method.xml", queryMainObjectMap).toString();
		variablesMap.put("json_contract_guarantee_method_str", json_contract_guarantee_method_str);
		
		//抵押物信息
		String json_contract_guarantee_equip_str = this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_sup/contract_guarantee_equip.xml", queryMainObjectMap).toString();
		variablesMap.put("json_contract_guarantee_equip_str", json_contract_guarantee_equip_str);

		//租金开票类型
		ContractInvoiceType invoiceType = contractInfo.getContractInvoiceType();
		variablesMap.putAll(this.tableService.getEntityPropertiesToStringMap(invoiceType, null, "contract_invoice_type"));
		
		//投放历史明细
		queryMainObjectMap.put("contractid", contractid);
		queryMainObjectMap.put("feetype", "feetype10");
		queryMainObjectMap.put("paytype", "pay_type_out");
		String json_put_plan_str = this.tableService.getJsonArrayData("eleasing/workflow/fund/fundcomm/fund_fund_charge_list.xml", queryMainObjectMap).toString();
		variablesMap.put("json_put_his_str",json_put_plan_str);
		
		try {
			queryMainObjectMap.clear();
			queryMainObjectMap.put("contract_id", contractid);
			JSONArray json= this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_five/contract_five.xml", queryMainObjectMap);	
			if(json.length()>0){
				JSONObject jsonObj = json.getJSONObject(0);
				variablesMap.put("five_category.contractfive_business",jsonObj.getString("contractfive_business"));
				variablesMap.put("rawValue_five_category.contractfive_business",jsonObj.getString("contractfive_businessname"));
				variablesMap.put("five_category.contractfivedate_business",jsonObj.getString("contractfivedate_business"));
				variablesMap.put("five_category.explain_business",jsonObj.getString("explain_business"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Entry entry : variablesMap.entrySet()){
			request.setAttribute(entry.getKey().toString(), entry.getValue());
		}
		return "solutions/workflow/forms/contract/contract_search/contract_info_detail.jsp";
		
	}
	
}
