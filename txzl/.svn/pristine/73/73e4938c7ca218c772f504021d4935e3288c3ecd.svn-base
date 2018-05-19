package com.tenwa.leasing.controller.sealregistra;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.constant.JsonReturnResultTypeEnum;
import com.tenwa.business.model.JsonReturnResult;
import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.entity.base.FundEbankData;
import com.tenwa.leasing.entity.base.FundEbankProcess;
import com.tenwa.leasing.entity.contract.ContractInfo;



/**
 * @author lichaojie
 * @date 2014-12-01
 * @info 
 */
@Controller(value="sealRegistraController")
@RequestMapping(value = "/**/leasing")
public class SealRegistraController {
	@Resource(name = "baseService")
	private BaseService baseService;
	
	@RequestMapping(value = "/getSealWorkflowStr.acl")
	@ResponseBody
	public String  getSealWorkflowStr(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String currentStr=model.get("currentStr");
		String rowData=model.get("rowData");
		JSONArray  currentArr=new JSONArray(currentStr);
		try {	
			
			
			return currentArr.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "[]";
	}
}
