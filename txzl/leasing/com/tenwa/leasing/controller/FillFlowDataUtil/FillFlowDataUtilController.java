package com.tenwa.leasing.controller.FillFlowDataUtil;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.service.FillFlowDataUtil.FillFlowDataUtil;
@Controller(value = "FillFlowDataUtilController")
@RequestMapping(value = "/**/leasing")
public class FillFlowDataUtilController {
	
	@Resource(name="FillFlowDataUtilImp")
	private FillFlowDataUtil fillFlowDataUtil;
	
	@RequestMapping(value = "/FillFlowData.action")
	@ResponseBody
	public String updateFillFlowDataUtil(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
			
			String flowunid=model.get("flowunid").toString();
			String workhisid=model.get("workhisid").toString();
			String action=model.get("action").toString();
			fillFlowDataUtil.saveFillFlowDataUtil(request, flowunid, workhisid,action);
		} catch (Exception e) {
			e.printStackTrace();
		}
//select * from t_jbpm_workflow_info b where b.jbpm4_hist_procinst_dbid_ = '27563'		
//http://localhost:9088/occ/leasing/FillFlowData.action?flowunid=6900281&workhisid=4028819146c706350146c7cf7c640eb0&action=contractOnHireEndAction		
		return null;
	}
	@RequestMapping(value = "/FillFlowDataByParam.action")
	@ResponseBody
	public String updateFillFlowData(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			fillFlowDataUtil.saveFillFlowData(request);
		} catch (Exception e) {
			e.printStackTrace();
		}	
//http://localhost:8080/ebfil/leasing/FillFlowDataByParam.action?currentTaskId=402811814e3f56e7014e3f66be9b0019&changedate=**@**	
		return null;
	}
}

