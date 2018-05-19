package com.tenwa.leasing.controller.fivecategoryapply;

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
import com.tenwa.leasing.entity.fivecategoryapply.FiveCategoryApply;

@Controller(value = "fiveCatgoryReviewController")
@RequestMapping(value = "/**/review")
public class FiveCatgoryReviewController {

	@Resource(name = "tableService")
	private TableService tableService;
	
	
	@RequestMapping(value = "/okFiveCatgoryReview.acl")
	@ResponseBody
	public String okFiveCatgoryReview(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String applynumbers = model.get("applynumbers");	
		String[] applymum = applynumbers.split(",");
		System.out.println(model);
		HashMap<String, Object> propertiesMap = new HashMap<String,Object>();
		for(String key : applymum){
			
			propertiesMap.put("id", key);
			FiveCategoryApply fivecategoryapply= this.tableService
					.findEntityByProperties(FiveCategoryApply.class, propertiesMap).get(0);
			
			fivecategoryapply.setApplystatus("审核通过");
			propertiesMap.clear();
			this.tableService.updateEntity(fivecategoryapply);
		}
		return "yes";
	}
	
	@RequestMapping(value = "/backFiveCatgoryReview.acl")
	@ResponseBody
	public String backFiveCatgoryReview(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String applynumbers = model.get("applynumbers");	
		String[] applymum = applynumbers.split(",");
		System.out.println(model);
		HashMap<String, Object> propertiesMap = new HashMap<String,Object>();
		for(String key : applymum){
			
			propertiesMap.put("id", key);
			FiveCategoryApply fivecategoryapply= this.tableService
					.findEntityByProperties(FiveCategoryApply.class, propertiesMap).get(0);
			
			fivecategoryapply.setApplystatus("已退回");
			propertiesMap.clear();
			this.tableService.updateEntity(fivecategoryapply);
		}
		return "yes";
	}
	 
 
}