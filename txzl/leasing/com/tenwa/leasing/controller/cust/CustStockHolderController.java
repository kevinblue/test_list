package com.tenwa.leasing.controller.cust;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.service.cust.CustStockHolderService;

@Controller(value = "custStockHolderController")
@RequestMapping(value = "/**/acl")
/**
 * 企业股本结构信息
 * @Title: CustStockHolderController.java
 * @package: com.tenwa.leasing.controller.cust
 * @author: tpf
 * @date 2014年11月20日 上午9:19:38 
 * @version V5.1
 */
public class CustStockHolderController {
	@Resource(name = "custStockHolderService")
	private CustStockHolderService baseService;
	 /**
	  * 添加企业股本结构信息
	  * @param request
	  * @param response
	  * @return 返回响应信息
	  * @throws Exception
	  */
	@RequestMapping(value = "/addCustStockHolder.acl")
	@ResponseBody
	public String addCustStockHolder(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.baseService.addCustStockHolder(model);
		}catch (Exception e) {
			return "添加失败!["+e.getMessage()+"]";
		}
		return null;
	}
	/**
	  * 修改企业股本结构信息
	  * @param request
	  * @param response
	  * @return 返回响应信息
	  * @throws Exception
	  */
	@RequestMapping(value = "/updateCustStockHolder.acl")
	@ResponseBody
	public String updateCustStockHolder(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.baseService.updateCustStockHolder(model);
		}catch (Exception e) {
			return "删除失败!["+e.getMessage()+"]";
		}
		return null;
	}
	/**
	  * 删除企业股本结构信息
	  * @param request
	  * @param response
	  * @return 返回响应信息
	  * @throws Exception
	  */
	@RequestMapping(value = "/removeCustStockHolder.acl")
	@ResponseBody
	public String removeCustStockHolder(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.baseService.removeCustStockHolder(model);
		}catch (Exception e) {
			return "删除失败!["+e.getMessage()+"]";
		}
		return null;
	}
}
