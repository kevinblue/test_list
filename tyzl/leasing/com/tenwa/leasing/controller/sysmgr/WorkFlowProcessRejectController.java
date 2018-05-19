package com.tenwa.leasing.controller.sysmgr;

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
import com.tenwa.leasing.service.sysmgr.WorkFlowProcessRejectService;


/**
 * 
 * @author tpf
 * @date 2014-8-11上午10:30:01
 * @info 省市信息维护
 * @Copyright Tenwa
 */
@Controller(value = "WorkFlowProcessRejectController")
@RequestMapping(value = "/**/acl")
public class WorkFlowProcessRejectController {
	@Resource(name = "workFlowProcessRejectService")
	private WorkFlowProcessRejectService workFlowProcessRejectService;

	@RequestMapping(value="/addOrRemoveReject.acl")
	@ResponseBody
	public JsonReturnResult addOrRemoveRejectRecord(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try{
			this.workFlowProcessRejectService.addOrRemoveReject(model);
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}
	
}
