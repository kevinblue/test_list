package com.tenwa.leasing.controller.fund.reminder;


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
import com.tenwa.leasing.service.fund.reminder.GeneratedReminderLetterService;


@Controller(value = "GeneratedReminderLetterController")
@RequestMapping(value = "/**/acl")
/**
 * 生成催款函列表
 * @Title: GeneratedReminderLetterController.java
 * @package: com.tenwa.leasing.controller.fund.reminder
 * @author: tpf
 * @date 2014年11月30日 下午5:55:37 
 * @version V5.1
 */
public class GeneratedReminderLetterController {
	@Resource(name = "generatedReminderLetterService")
	private GeneratedReminderLetterService baseService;
	/**
	  * 批量维护发送日期
	  * @param request
	  * @param response
	  * @return 返回响应信息
	  * @throws Exception
	  */ 
	@RequestMapping(value = "/saveGeneratedReminderLetter.acl")
	@ResponseBody
	public String saveGeneratedReminderLetter(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.baseService.saveGeneratedReminderLetter(model);
		}catch (Exception e) {
			return "修改失败!["+e.getMessage()+"]";
		}
		return null;
	}
	/**
	  * 批量删除发送日期
	  * @param request
	  * @param response
	  * @return 返回响应信息
	  * @throws Exception
	  */ 
	@RequestMapping(value = "/removeGeneratedReminderLetter.acl")
	@ResponseBody
	public String removeGeneratedReminderLetter(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.baseService.removeGeneratedReminderLetter(model);
		}catch (Exception e) {
			return "删除失败!["+e.getMessage()+"]";
		}
		return null;
	}
	/**
	  * 打印催款函
	  * @param request
	  * @param response
	  * @return 返回响应信息
	  * @throws Exception
	  */ 
	@RequestMapping(value = "/updateGeneratedReminderLetter.acl")
	@ResponseBody
	public String updateGeneratedReminderLetter(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.baseService.updateGeneratedReminderLetter(model);
		}catch (Exception e) {
			return "修改失败!["+e.getMessage()+"]";
		}
		return null;
	}
}
