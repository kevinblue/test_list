package com.tenwa.leasing.controller.asset;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.entity.User;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.asset.AssetMngApply;
import com.tenwa.leasing.service.asset.AssetsMngService;

@Controller(value = "assetsMngController")
@RequestMapping(value = "/**/acl")
public class AssetsMngController {

	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "assetsMngService")
	private AssetsMngService assetsMngService;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/showAssetsMng.acl")
	public String showAssetsMng(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil
				.getRequestParameterMapByAjax(request);
		User user = SecurityUtil.getPrincipal();
		String opertype = model.get("opertype");
		Map<String, String> variablesMap = new HashMap<String, String>();
		if (opertype.equals("add")) {
			variablesMap.put("applyusername", user.getRealname());
			variablesMap.put("createdate", DateUtil.getSystemDateTime());
			this.assetsMngService.addloadAssetMng(variablesMap);
		} else {
			String applyid = model.get("applyid");
			AssetMngApply assetNetMonitorApply = this.tableService
					.findEntityByID(AssetMngApply.class, applyid);
			variablesMap = this.tableService
					.getEntityPropertiesToStringMap(assetNetMonitorApply, null, "");
	
			variablesMap.put("applynumber", assetNetMonitorApply.getApplynumber());
			Map<String, Object> propertiesMap = new HashMap<String, Object>();
			propertiesMap.put("applyid", assetNetMonitorApply);
	
			variablesMap.put("creatorname", assetNetMonitorApply.getCreator()== null ? ""
					:assetNetMonitorApply.getCreator().getRealname());
			variablesMap.put("applyusername", assetNetMonitorApply
					.getApplyuser().getRealname());
			variablesMap.put("assetMngApply", assetNetMonitorApply.getId());
			variablesMap.put("modificator", assetNetMonitorApply.getModificator()== null ? ""
					:assetNetMonitorApply.getModificator().getRealname());
		}

		for (Entry entry : variablesMap.entrySet()) {
			request.setAttribute(entry.getKey().toString(), entry.getValue());
		}
		if ("view".equals(opertype)) {
			return "solutions/workflow/forms/other/asset_mng_plan/asset_mng_info_view.jsp";
		} else {
			return "solutions/workflow/forms/other/asset_mng_plan/assets_mng_info.jsp";
		}
	}
	
	
	@RequestMapping(value = "/showAssetsMngOk.acl")
	@ResponseBody
	public String showAssetsMngOk(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);

		String applynumbers = model.get("applynumbers");	
		String[] applymum = applynumbers.split(",");
		
		HashMap<String, Object> propertiesMap = new HashMap<String,Object>();
		for(String key : applymum){
			
			propertiesMap.put("applynumber", key);
			AssetMngApply assetMngApply = this.tableService
					.findEntityByProperties(AssetMngApply.class, propertiesMap).get(0);
			
			assetMngApply.setApplystatus("审核中");
			propertiesMap.clear();
			this.tableService.updateEntity(assetMngApply);
		}
		return "yes";
	}
	@RequestMapping(value = "/addAssetsMng.acl")
	@ResponseBody
	public String addAssetsMng(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String returnstr = "";
		try {
			this.assetsMngService.addAssetMng(model);
			returnstr = "{flag:\"true\",msg:\"申请成功！\",opertype:\"add\"}";
		} catch (Exception e) {
			e.printStackTrace();
			returnstr = "{flag:\"false\",msg:\"" + e.getMessage()
					+ "\",opertype:\"add\"}";
		}
		
		return returnstr;
	}
	@RequestMapping(value = "/updateAssetsMng.acl")
	@ResponseBody
	public String updateAssetsMng(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil
				.getRequestParameterMapByAjax(request);
		String returnstr = "";
		try {
			this.assetsMngService.updateAssetMng(model);
			returnstr = "{flag:\"true\",msg:\"操作成功！\"}";
		} catch (Exception e) {
			e.printStackTrace();
			returnstr = "{flag:\"false\",msg:\"" + e.getMessage() + "\"}";
		}
		return returnstr;
	}

	@RequestMapping(value = "/deleteAssetsMng.acl")
	@ResponseBody
	public String deleteAssetsMng(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil
				.getRequestParameterMapByAjax(request);
		String returnstr = "yes";
		try{
			this.assetsMngService.deleteMng(model);
		} catch (Exception e) {
			e.printStackTrace();
			returnstr = "no";
		}
		return returnstr;
	}
 
}
