package com.tenwa.leasing.controller.cust;


import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.service.cust.CustAccountService;


@Controller(value = "custAccountController")
@RequestMapping(value = "/**/acl")
/**
 * 银行信息
 * @Title: CustContactController.java
 * @package: com.tenwa.leasing.controller.cust
 * @author: tpf
 * @date 2014年11月20日 上午9:27:24 
 * @version V5.1
 */
public class CustAccountController {
	@Resource(name = "custAccountService")
	private CustAccountService baseService;
	/**
	  * 添加银行信息
	  * @param request
	  * @param response
	  * @return 返回响应信息
	  * @throws Exception
	  */ 
	@RequestMapping(value = "/addCustAccount.acl")
	@ResponseBody
	public String addCustContact(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.baseService.addCustAccount(model);
		}catch (Exception e) {
			return "添加失败!["+e.getMessage()+"]";
		}
		return null;
	}
	/**
	  * 修改银行信息
	  * @param request
	  * @param response
	  * @return 返回响应信息
	  * @throws Exception
	  */ 
	@RequestMapping(value = "/updateCustAccount.acl")
	@ResponseBody
	public String updateCustContact(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.baseService.updateCustAccount(model);
		}catch (Exception e) {
			return "删除失败!["+e.getMessage()+"]";
		}
		return null;
	}
	/**
	  * 删除银行信息
	  * @param request
	  * @param response
	  * @return 返回响应信息
	  * @throws Exception
	  */ 
	@RequestMapping(value = "/removeCustAccount.acl")
	@ResponseBody
	public String removeCustContact(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.baseService.removeCustAccount(model);
		}catch (Exception e) {
			return "删除失败!["+e.getMessage()+"]";
		}
		return null;
	}
}
