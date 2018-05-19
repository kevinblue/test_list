package com.tenwa.leasing.controller.cust;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.cust.CustInfoCompany;
import com.tenwa.leasing.service.cust.CustContactService;
import com.tenwa.leasing.service.cust.CustService;


@Controller(value = "custContactController")
@RequestMapping(value = "/**/acl")
/**
 * 走访调研信息
 * @Title: CustContactController.java
 * @package: com.tenwa.leasing.controller.cust
 * @author: tpf
 * @date 2014年11月20日 上午9:27:24 
 * @version V5.1
 */
public class CustContactController {
	@Resource(name = "custContactService")
	private CustContactService baseService;
	/**
	  * 添加走访调研信息
	  * @param request
	  * @param response
	  * @return 返回响应信息
	  * @throws Exception
	  */  
	@RequestMapping(value = "/addCustContact.acl")
	@ResponseBody
	public String addCustContact(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.baseService.addCustContact(model);
		}catch (Exception e) {
			return "添加失败!["+e.getMessage()+"]";
		}
		return null;
	}
	/**
	  * 修改走访调研信息
	  * @param request
	  * @param response
	  * @return 返回响应信息
	  * @throws Exception
	  */  
	@RequestMapping(value = "/updateCustContact.acl")
	@ResponseBody
	public String updateCustContact(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.baseService.updateCustContact(model);
		}catch (Exception e) {
			return "删除失败!["+e.getMessage()+"]";
		}
		return null;
	}
	/**
	  * 删除走访调研信息
	  * @param request
	  * @param response
	  * @return 返回响应信息
	  * @throws Exception
	  */  
	@RequestMapping(value = "/removeCustContact.acl")
	@ResponseBody
	public String removeCustContact(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.baseService.removeCustContact(model);
		}catch (Exception e) {
			return "删除失败!["+e.getMessage()+"]";
		}
		return null;
	}
}
