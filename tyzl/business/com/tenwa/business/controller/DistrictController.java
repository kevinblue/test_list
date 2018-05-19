/**
 * 
 */
package com.tenwa.business.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.entity.base.District;
import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.utils.QueryUtil;

/**
 * @author 李超杰
 * @date 2014年2月28日
 * @info 
 * @Copyright
 * Tenwa
 */
@Controller(value="districtController")
@RequestMapping(value = "/**/district")
public class DistrictController {
	private Logger logger = Logger.getLogger(DistrictController.class);
	@Resource(name = "baseService")
	private BaseService baseService;
	
	@RequestMapping(value = "/addDistrict.acl")
	@ResponseBody
	public String addDistrict(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> districtMap = QueryUtil.getRequestParameterMapByAjax(request);
		// Map<String,String> dictDataClassMapping = new
		// HashMap<String,String>();
		// dictDataClassMapping.put("DictionaryData", "code");
		District dist = new District();
		dist.setParentDistrict(this.baseService.findEntityByID(District.class, districtMap.get("pid")));
		this.baseService.copyAndOverrideExistedValueFromStringMap(districtMap, dist, null, false);
		this.baseService.saveEntity(dist);
		if (logger.isInfoEnabled()) {
			logger.info("新增成功!");
		}
		return null;
	}
	
	@RequestMapping(value = "/updateDistrict.acl")
	@ResponseBody
	public String updateDistrict(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> districtMap = QueryUtil.getRequestParameterMapByAjax(request);
		District dist = new District();
		dist = (District) this.baseService.findEntityByID(District.class, districtMap.get("id"));
		this.baseService.copyAndOverrideExistedValueFromStringMap(districtMap, dist, null, false);
		this.baseService.updateEntity(dist);
		if (logger.isInfoEnabled()) {
			logger.info("修改成功!");
		}
		return null;
	}
	
	@RequestMapping(value = "/removeDistrict.acl")
	@ResponseBody
	public String removeDistrict(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> districtMap = QueryUtil.getRequestParameterMapByAjax(request);
		District dist = new District();
		dist = (District) this.baseService.findEntityByID(District.class, districtMap.get("id"));
		//this.baseService.copyAndOverrideExistedValueFromStringMap(districtMap, dist, null);
		// 删除时状态设置为禁用
		// info.setEnable("1");
		// this.baseService.saveOrUpdateEntity(cust);
		this.baseService.removeEntity(dist);
		if (logger.isInfoEnabled()) {
			logger.info("删除成功!");
		}
		return null;
	}
}
