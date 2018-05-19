package com.tenwa.leasing.controller.asset;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.entity.asset.AssetMngApply;

@Controller(value = "assetsReviewController")
@RequestMapping(value = "/**/acl")
public class AssetsReviewController {

	@Resource(name = "tableService")
	private TableService tableService;
	
	
	@RequestMapping(value = "/okAssetsReview.acl")
	@ResponseBody
	public String okAssetsReview(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String applynumbers = model.get("applynumbers");	
		String[] applymum = applynumbers.split(",");
		
		HashMap<String, Object> propertiesMap = new HashMap<String,Object>();
		for(String key : applymum){
			
			propertiesMap.put("applynumber", key);
			AssetMngApply assetMngApply = this.tableService
					.findEntityByProperties(AssetMngApply.class, propertiesMap).get(0);
			
			assetMngApply.setApplystatus("审核通过");
			propertiesMap.clear();
			this.tableService.updateEntity(assetMngApply);
		}
		return "yes";
	}
	
	@RequestMapping(value = "/backAssetsReview.acl")
	@ResponseBody
	public String backAssetsReview(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String applynumbers = model.get("applynumbers");	
		String[] applymum = applynumbers.split(",");
		
		HashMap<String, Object> propertiesMap = new HashMap<String,Object>();
		for(String key : applymum){
			
			propertiesMap.put("applynumber", key);
			AssetMngApply assetMngApply = this.tableService
					.findEntityByProperties(AssetMngApply.class, propertiesMap).get(0);
			
			assetMngApply.setApplystatus("未审核");
			propertiesMap.clear();
			this.tableService.updateEntity(assetMngApply);
		}
		return "yes";
	}
	 
 
}
