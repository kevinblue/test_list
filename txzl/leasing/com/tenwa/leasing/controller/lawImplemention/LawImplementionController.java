package com.tenwa.leasing.controller.lawImplemention;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.entity.User;
import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.asset.AssetMngApply;
import com.tenwa.leasing.entity.assetnetworkmonitor.AssetNetMonitorApply;
import com.tenwa.leasing.entity.assetnetworkmonitor.AssetNetMonitorApplyDetail;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.cust.CustInfoCompany;
import com.tenwa.leasing.entity.fivecategoryapply.FiveCategoryApply;
import com.tenwa.leasing.entity.fivecategoryapply.FiveCategoryApplyDetail;
import com.tenwa.leasing.entity.lawmng.LawApproval;
import com.tenwa.leasing.entity.lawmng.LawImplemention;
import com.tenwa.leasing.service.assetsnetmonitor.AssetsNetMonitorService;
import com.tenwa.leasing.service.fivecategory.FiveCategoryService;
import com.tenwa.leasing.service.lawImplemention.LawImplementionService;
import com.tenwa.leasing.utils.WorkflowUtil;
import com.tenwa.leasing.serviceImpl.fivecategory.FiveCategoryServiceImpl;

/**
 * 五级分类
 * 
 * @Title: AssetsNetMonitorController.java
 * @package: com.tenwa.leasing.controller.AssetsNetMonitorController
 * @author: ganwei
 * @date 2014年11月20日 上午9:27:24
 * @version V5.1
 */
@Controller(value = "lawImplementionController")
@RequestMapping(value = "/**/acl")
public class LawImplementionController {

	@Resource(name = "tableService")
	private TableService tableService;

	@Resource(name = "lawImplementionService")
	private LawImplementionService lawImplementionService;

	/**
	 * 申请页面展示
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showLawImplemention.acl")
	public String showLawImplemention(HttpServletRequest request,
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
			
		}
		
		for (Entry entry : variablesMap.entrySet()) {
			request.setAttribute(entry.getKey().toString(), entry.getValue());
		}
		
		return "solutions/workflow/forms/law_mng/law_implemention/law_implemention_list.jsp";
		
	}
	
}