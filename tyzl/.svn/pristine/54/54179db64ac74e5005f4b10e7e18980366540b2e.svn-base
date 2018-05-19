package com.tenwa.leasing.controller.sysmgr;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.cust.CustInfoCompany;
import com.tenwa.leasing.service.cust.CustService;
import com.tenwa.leasing.service.sysmgr.SysDataMgrService;


/**
 * 
 * @author tpf
 * @date 2014-8-11上午10:30:01
 * @info 省市信息维护
 * @Copyright Tenwa
 */
@Controller(value = "SysDataMgrController")
@RequestMapping(value = "/**/acl")
public class SysDataMgrController {
	@Resource(name = "sysDataMgrService")
	private SysDataMgrService baseService;

	// #################
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addDistrict.acl")
	@ResponseBody
	public String addCustLaw(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.baseService.addDistrict(model);
		}catch (Exception e) {
			return "添加失败!["+e.getMessage()+"]";
		}
		return "添加成功";
	}

	@RequestMapping(value = "/updateDistrict.acl")
	@ResponseBody
	public String updateCustLaw(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.baseService.updateDistrict(model);
		}catch (Exception e) {
			return "修改失败!["+e.getMessage()+"]";
		}
		return null;
	}
	@RequestMapping(value = "/removeDistrict.acl")
	@ResponseBody
	public String removeCustLaw(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.baseService.removeDistrict(model);
		}catch (Exception e) {
			return "删除失败!["+e.getMessage()+"]";
		}
		return null;
	}
}
