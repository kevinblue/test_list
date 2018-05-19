package com.tenwa.leasing.controller.cust;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.service.cust.CustManufacturersInfoService;

/**
 * 进货厂商资料
 * @Title: CustSalesInfoController.java
 * @package: com.tenwa.leasing.controller.cust
 * @author: tpf
 * @date 2014年11月20日 上午9:22:43 
 * @version V5.1
 */
@Controller(value = "custManufacturersInfoController")
@RequestMapping(value = "/**/acl")
public class CustManufacturersInfoController {
	@Resource(name = "custManufacturersInfoService")
	private CustManufacturersInfoService baseService;
	/**
	  * 添加进货厂商资料
	  * @param request
	  * @param response
	  * @return 返回响应信息
	  * @throws Exception
	  */  
	@RequestMapping(value = "/addCustManufacturersInfo.acl")
	@ResponseBody
	public String addCustManufacturersInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.baseService.addCustManufacturersInfo(model);
		}catch (Exception e) {
			return "添加失败!["+e.getMessage()+"]";
		}
		return null;
	}
	/**
	  * 修改进货厂商资料
	  * @param request
	  * @param response
	  * @return 返回响应信息
	  * @throws Exception
	  */ 
	@RequestMapping(value = "/updateCustManufacturersInfo.acl")
	@ResponseBody
	public String updateCustManufacturersInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.baseService.updateCustManufacturersInfo(model);
		}catch (Exception e) {
			return "删除失败!["+e.getMessage()+"]";
		}
		return null;
	}
	/**
	  * 删除进货厂商资料
	  * @param request
	  * @param response
	  * @return 返回响应信息
	  * @throws Exception
	  */ 
	@RequestMapping(value = "/removeCustManufacturersInfo.acl")
	@ResponseBody
	public String removeCustManufacturersInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.baseService.removeCustManufacturersInfo(model);
		}catch (Exception e) {
			return "删除失败!["+e.getMessage()+"]";
		}
		return null;
	}
}
