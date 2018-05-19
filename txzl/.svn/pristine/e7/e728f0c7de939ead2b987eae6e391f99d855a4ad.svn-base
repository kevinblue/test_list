package com.tenwa.leasing.controller.cust;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.service.cust.CustSalesInfoService;

@Controller(value = "custSalesInfoController")
@RequestMapping(value = "/**/acl")
/**
 * 销货厂商资料信息
 * @Title: CustSalesInfoController.java
 * @package: com.tenwa.leasing.controller.cust
 * @author: tpf
 * @date 2014年11月20日 上午9:22:43 
 * @version V5.1
 */
public class CustSalesInfoController {
	@Resource(name = "custSalesInfoService")
	private CustSalesInfoService baseService;
	/**
	  * 添加销货厂商资料信息
	  * @param request
	  * @param response
	  * @return 返回响应信息
	  * @throws Exception
	  */
	@RequestMapping(value = "/addCustSalesInfo.acl")
	@ResponseBody
	public String addCustSalesInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.baseService.addCustSalesInfo(model);
		}catch (Exception e) {
			return "添加失败!["+e.getMessage()+"]";
		}
		return null;
	}
	/**
	  * 修改销货厂商资料信息
	  * @param request
	  * @param response
	  * @return 返回响应信息
	  * @throws Exception
	  */
	@RequestMapping(value = "/updateCustSalesInfo.acl")
	@ResponseBody
	public String updateCustSalesInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.baseService.updateCustSalesInfo(model);
		}catch (Exception e) {
			return "删除失败!["+e.getMessage()+"]";
		}
		return null;
	}
	/**
	  * 删除销货厂商资料信息
	  * @param request
	  * @param response
	  * @return 返回响应信息
	  * @throws Exception
	  */
	@RequestMapping(value = "/removeCustSalesInfo.acl")
	@ResponseBody
	public String removeCustSalesInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.baseService.removeCustSalesInfo(model);
		}catch (Exception e) {
			return "删除失败!["+e.getMessage()+"]";
		}
		return null;
	}
}
