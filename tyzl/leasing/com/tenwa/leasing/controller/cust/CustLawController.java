package com.tenwa.leasing.controller.cust;

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


/**
 * 法人客户
 * @Title: CustSalesInfoController.java
 * @package: com.tenwa.leasing.controller.cust
 * @author: tpf
 * @date 2014年11月20日 上午9:22:43 
 * @version V5.1
 */
@Controller(value = "CustLawController")
@RequestMapping(value = "/**/acl")
public class CustLawController {
	@Resource(name = "custService")
	private CustService baseService;

	/**
	  * 添加法人客户
	  * @param request
	  * @param response
	  * @return 返回响应信息
	  * @throws Exception
	  */ 
	@RequestMapping(value = "/addCustLaw.acl")
	@ResponseBody
	public String addCustLaw(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.baseService.addCustLaw(model);
		}catch (Exception e) {
			return "添加失败!["+e.getMessage()+"]";
		}
		return null;
	}
	/**
	  * 修改法人客户
	  * @param request
	  * @param response
	  * @return 返回响应信息
	  * @throws Exception
	  */
	@RequestMapping(value = "/updateCustLaw.acl")
	@ResponseBody
	public String updateCustLaw(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.baseService.updateCustLaw(model);
		}catch (Exception e) {
			return "修改失败!["+e.getMessage()+"]";
		}
		return null;
	}
	/**
	  * 删除法人客户
	  * @param request
	  * @param response
	  * @return 返回响应信息
	  * @throws Exception
	  */
	@RequestMapping(value = "/removeCustLaw.acl")
	@ResponseBody
	public String removeCustLaw(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.baseService.removeCustLaw(model);
		}catch (Exception e) {
			return "删除失败!["+e.getMessage()+"]";
		}
		return null;
	}
}
