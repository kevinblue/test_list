package com.tenwa.leasing.controller.cust;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.service.cust.CustShareCompanyService;

@Controller(value = "custShareCompanyController")
@RequestMapping(value = "/**/acl")
/**
 * 关联企业
 * @Title: CustShareCompanyController.java
 * @package: com.tenwa.leasing.controller.cust
 * @author: tpf
 * @date 2014年11月20日 上午9:21:06 
 * @version V5.1
 */
public class CustShareCompanyController {
	@Resource(name = "custShareCompanyService")
	private CustShareCompanyService baseService;
	/**
	  * 添加关联企业
	  * @param request
	  * @param response
	  * @return 返回响应信息
	  * @throws Exception
	  */
	@RequestMapping(value = "/addCustShareCompany.acl")
	@ResponseBody
	public String addCustShareCompany(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.baseService.addCustShareCompany(model);
		}catch (Exception e) {
			return "添加失败!["+e.getMessage()+"]";
		}
		return null;
	}
	/**
	  * 修改关联企业
	  * @param request
	  * @param response
	  * @return 返回响应信息
	  * @throws Exception
	  */
	@RequestMapping(value = "/updateCustShareCompany.acl")
	@ResponseBody
	public String updateCustShareCompany(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.baseService.updateCustShareCompany(model);
		}catch (Exception e) {
			return "删除失败!["+e.getMessage()+"]";
		}
		return null;
	}
	/**
	  * 删除关联企业
	  * @param request
	  * @param response
	  * @return 返回响应信息
	  * @throws Exception
	  */
	@RequestMapping(value = "/removeCustShareCompany.acl")
	@ResponseBody
	public String removeCustShareCompany(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.baseService.removeCustShareCompany(model);
		}catch (Exception e) {
			return "删除失败!["+e.getMessage()+"]";
		}
		return null;
	}
}
