package com.tenwa.leasing.controller.fund.overdue;


import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.service.cust.CustAccountService;
import com.tenwa.leasing.service.fund.overdue.OverdueDunningInfoService;


@Controller(value = "OverdueDunningInfoController")
@RequestMapping(value = "/**/acl")
/**
 * 催款员负责合同页面列表
 * @Title: OverdueDunningInfoController.java
 * @package: com.tenwa.leasing.controller.fund.overdue
 * @author: tpf
 * @date 2014年11月26日 下午3:18:25 
 * @version V5.1
 */
public class OverdueDunningInfoController {
	@Resource(name = "overdueDunningInfoService")
	private OverdueDunningInfoService baseService;
	/**
	  * 修改催款员负责合同列表
	  * @param request
	  * @param response
	  * @return 返回响应信息
	  * @throws Exception
	  */ 
	@RequestMapping(value = "/saveOverdueDunningInfo.acl")
	@ResponseBody
	public String saveOverdueDunningInfo(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.baseService.saveOverdueDunningInfo(model);
		}catch (Exception e) {
			return "修改失败!["+e.getMessage()+"]";
		}
		return null;
	}
	/**
	  * 批量修改催款员负责合同列表
	  * @param request
	  * @param response
	  * @return 返回响应信息
	  * @throws Exception
	  */ 
	@RequestMapping(value = "/saveMultiOverdueDunningInfo.acl")
	@ResponseBody
	public String saveMultiOverdueDunningInfo(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.baseService.saveMultiOverdueDunningInfo(model);
		}catch (Exception e) {
			return "修改失败!["+e.getMessage()+"]";
		}
		return null;
	}
	/**
	  * 批量添加催收记录
	  * @param request
	  * @param response
	  * @return 返回响应信息
	  * @throws Exception
	  */ 
	@RequestMapping(value = "/saveOverdueDunningRecord.acl")
	@ResponseBody
	public String saveOverdueDunningRecord(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.baseService.saveOverdueDunningRecord(model);
		}catch (Exception e) {
			return "修改失败!["+e.getMessage()+"]";
		}
		return null;
	}
}
