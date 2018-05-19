package com.tenwa.leasing.controller.remindTask;

import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.constant.JsonReturnResultTypeEnum;
import com.tenwa.business.model.JsonReturnResult;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.service.remindTask.RemindTaskService;



/**
 * @author lichaojie
 * @date 2014-12-01
 * @info 
 */
@Controller(value="remindTaskController")
@RequestMapping(value = "/**/acl")
public class RemindTaskController {
	@Resource(name = "remindTaskService")
	private RemindTaskService remService;
	
	@RequestMapping(value = "/addRemindTaskInfo.acl")
	@ResponseBody
	public JsonReturnResult addRemindTaskInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		try{
			this.remService.addRemindTast(model);
			
		}catch(Exception e){
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}
	
	@RequestMapping(value = "/updateRemindTaskInfo.acl")
	@ResponseBody
	public JsonReturnResult updateRemindTaskInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		try{
			this.remService.updateRemindTast(model);
			
		}catch(Exception e){
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}
}
