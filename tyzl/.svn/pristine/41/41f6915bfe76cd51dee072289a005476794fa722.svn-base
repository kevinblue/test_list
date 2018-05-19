package com.tenwa.leasing.controller.Finance;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tenwa.business.constant.JsonReturnResultTypeEnum;
import com.tenwa.business.controller.BaseController;
import com.tenwa.business.model.JsonReturnResult;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.service.finance.FinanceService;

@Controller(value = "financeReportController")
@RequestMapping(value = "/**/leasing")

public class FinanceReportController extends BaseController {
	
	@Resource(name="financeServiceImpl") 
	 private FinanceService fs;
	
	@RequestMapping(value = "/finance/importFinance.action")
	@ResponseBody
	public String importFinance(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String message="";
		String jscallback=model.get("jscallback");
		try {
			fs.importFinance(request, model);
			message="成功上传财务报表";
		} catch (Exception e) {
			e.printStackTrace();
			message="上传财务报表失败 内容："+e.getMessage();
		}
		String ajaxCallBackScript = "<script type='text/javascript'>window.parent."+jscallback+"('"+message+"');</script>";
		this.ajaxReturn(response, ajaxCallBackScript);
		return null;
	}
	
	@RequestMapping(value = "/finance/addFinance.action")
	@ResponseBody
	public String addFinance(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String message="";
		String jscallback=model.get("jscallback");
		try {
			fs.addFinance(request, model);
			message="成功上传财务报表";
		} catch (Exception e) {
			e.printStackTrace();
			message="上传财务报表失败 内容："+e.getMessage();
		}
		String ajaxCallBackScript = "<script type='text/javascript'>window.parent."+jscallback+"('"+message+"');</script>";
		this.ajaxReturn(response, ajaxCallBackScript);
		return null;
	}
	
	@RequestMapping(value = "/editFinancialData.acl")
	@ResponseBody
	public ModelAndView getProjectYear(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
		List<Map<String, Object>> years = this.fs.getProjectYear(model);
		ModelAndView mav = new ModelAndView("solutions/leasing/cust_info/cust_finance/financial_data_edit.jsp");
		mav.addObject("list",years);
		return mav;
	}
	
	@RequestMapping(value = "/modifyFinanceData.acl")
	@ResponseBody
	public JsonReturnResult modifyFinanceData(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try{
			this.fs.modifyFinanceData(model);
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}
	@RequestMapping(value = "/removeFinancialData.acl")
	@ResponseBody
	public JsonReturnResult removeFinancialData(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try{
			this.fs.removeFinanceData(model);
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}
}
