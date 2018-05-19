package com.tenwa.leasing.controller.cust;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.service.cust.CustService;


/**
 * 集团客户
 * @Title: CustSalesInfoController.java
 * @package: com.tenwa.leasing.controller.cust
 * @author: tpf
 * @date 2014年11月20日 上午9:22:43 
 * @version V5.1
 */
@Controller(value = "CustCliqueController")
@RequestMapping(value = "/**/acl")
public class CustCliqueController {
	@Resource(name = "custService")
	private CustService baseService;

	/**
	  * 添加集团客户
	  * @param request
	  * @param response
	  * @return 返回响应信息
	  * @throws Exception
	  */ 
	@RequestMapping(value = "/addCustClique.acl")
	@ResponseBody
	public String addCustClique(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.baseService.addCustClique(model);
		}catch (Exception e) {
			return "添加失败!["+e.getMessage()+"]";
		}
		return null;
	}
	/**
	  * 修改集团客户
	  * @param request
	  * @param response
	  * @return 返回响应信息
	  * @throws Exception
	  */
	@RequestMapping(value = "/updateCustClique.acl")
	@ResponseBody
	public String updateCustClique(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.baseService.updateCustClique(model);
		}catch (Exception e) {
			return "修改失败!["+e.getMessage()+"]";
		}
		return null;
	}
	/**
	  * 删除集团客户
	  * @param request
	  * @param response
	  * @return 返回响应信息
	  * @throws Exception
	  */
	@RequestMapping(value = "/removeCustClique.acl")
	@ResponseBody
	public String removeCustClique(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.baseService.removeCustClique(model);
		}catch (Exception e) {
			return "删除失败!["+e.getMessage()+"]";
		}
		return null;
	}
}
