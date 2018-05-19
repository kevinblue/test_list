package com.tenwa.leasing.controller.Finance;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.reckon.entity.contract.reckon.fund.ContractPaymentPremiseCondition;
import com.tenwa.business.constant.JsonReturnResultTypeEnum;
import com.tenwa.business.controller.BaseController;
import com.tenwa.business.entity.User;
import com.tenwa.business.model.JsonReturnResult;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.SaleWeekReport;
import com.tenwa.leasing.entity.finacial.CashDeposit;
import com.tenwa.leasing.entity.finacial.CashDepositBase;
import com.tenwa.leasing.entity.finacial.DepositInterest;
import com.tenwa.leasing.service.finance.FinanceService;

@Controller(value = "financeReportController")
@RequestMapping(value = "/**/leasing")

public class FinanceReportController extends BaseController {

	@Resource(name = "tableService")
	private TableService tableService;

	@Resource(name = "financeServiceImpl")
	private FinanceService fs;

	@RequestMapping(value = "/finance/importFinance.action")
	@ResponseBody
	public String importFinance(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String message = "";
		String jscallback = model.get("jscallback");
		try {
			fs.importFinance(request, model);
			message = "成功上传财务报表";
		} catch (Exception e) {
			e.printStackTrace();
			message = "上传财务报表失败 内容：" + e.getMessage();
		}
		String ajaxCallBackScript = "<script type='text/javascript'>window.parent." + jscallback + "('" + message
				+ "');</script>";
		this.ajaxReturn(response, ajaxCallBackScript);
		return null;
	}

	@RequestMapping(value = "/editFinancialData.acl")
	@ResponseBody
	public ModelAndView getProjectYear(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		List<Map<String, Object>> years = this.fs.getProjectYearNew(model);
		/*
		 * for ( int i = 0 ; i < years.size() - 1 ; i ++ ) { for ( int j =
		 * years.size() - 1 ; j > i; j -- ) { if
		 * (years.get(j).equals(years.get(i))) { years.remove(j); } } }
		 */
		List<Map<String, Object>> year = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < years.size(); i++) {
			Map<String, Object> str = years.get(i);
			if (!year.contains(years.get(i))) {
				year.add(str);
			}
		}
		System.out.println(year);
		ModelAndView mav = new ModelAndView("solutions/leasing/cust_info/cust_finance/financial_data_edit.jsp");
		mav.addObject("list", year);
		return mav;
	}

	@RequestMapping(value = "/modifyFinanceData.acl")
	@ResponseBody
	public JsonReturnResult modifyFinanceData(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS, "");
		try {
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
	public JsonReturnResult removeFinancialData(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS, "");
		try {
			this.fs.removeFinanceData(model);
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}

	/**
	 * 新增申请
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveFundPlanReportlication.acl")
	@ResponseBody
	public String saveFundPlanReportlication(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		System.out.println("model=" + model);
		String returnstr = "";
		try {
			// 判断月报是否存在
			Map<String, Object> queryMainObjectMap = new HashMap<String, Object>();
			queryMainObjectMap.put("didate", model.get("didate"));
			// 判断是否为草稿
			List<DepositInterest> work = this.tableService.findEntityByProperties(DepositInterest.class,
					queryMainObjectMap);
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("reportStyle", "草稿");
			List<DepositInterest> worklist = this.tableService.findEntityByProperties(DepositInterest.class, queryMap);
			// 判断是否为数字
			if (work.size() > 0) {
				returnstr = "{flag:\"tru\",msg:\"资金预实月报已存在，不允许重复录入！\",opertype:\"add\"}";
				return returnstr;
			} else if (worklist.size() > 0) {
				returnstr = "{flag:\"tru\",msg:\"有草稿还未完成，请先提交完成！\",opertype:\"add\"}";
				return returnstr;
			} else {
				this.fs.addFundPlanReportApplication(model);
				returnstr = "{flag:\"true\",msg:\"登记成功！\",opertype:\"add\"}";
				return returnstr;
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnstr = "{flag:\"false\",msg:\"" + e.getMessage() + "\",opertype:\"add\"}";
		}

		return returnstr;
	}

	/**
	 * 资金月报申请页面展示
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showFundPlanReport.acl")
	public String showFundPlanReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		User user = SecurityUtil.getPrincipal();
		String opertype = model.get("opertype");
		Map<String, String> variablesMap = new HashMap<String, String>();
		variablesMap.put("opertype", opertype);
		if ("add".equals(opertype)) {
			Map<String, String> queryMap = new HashMap<String, String>();
			queryMap.put("reportstyle", "草稿");
			queryMap.put("creator", user.getId());
			queryMap.put("didate", model.get("didate"));
			queryMap.put("note", model.get("note"));
			String jsonwormonthreport = this.tableService
					.getJsonArrayData("/eleasing/jsp/finacial/fund_plan/fund_plan_get_report.xml", queryMap).toString();
			JSONArray jsonwormonth = new JSONArray(jsonwormonthreport);
			for (int i = 0; i < jsonwormonth.length(); i++) {
				JSONObject jsonmonth = jsonwormonth.getJSONObject(i);
				variablesMap.put("id", jsonmonth.getString("id"));
				variablesMap.put("sn", jsonmonth.getString("sn"));
				variablesMap.put("didate", jsonmonth.getString("didate"));
				variablesMap.put("creator", jsonmonth.getString("creator"));
				variablesMap.put("tuid", jsonmonth.getString("tuid"));
				variablesMap.put("reportstyle", jsonmonth.getString("reportstyle"));
				variablesMap.put("note", jsonmonth.getString("note"));
				variablesMap.put("accountbalance", jsonmonth.getString("accountbalance"));
				variablesMap.put("balancedeposit", jsonmonth.getString("balancedeposit"));
				variablesMap.put("createdate", jsonmonth.getString("createdate"));
			}

		} else {
			String id = model.get("id");
			Map<String, String> queryMainObjectMap = new HashMap<String, String>();
			queryMainObjectMap.put("id", id);
			String jsonworkmonthreport = this.tableService
					.getJsonArrayData("/eleasing/jsp/finacial/fund_plan/fund_plan_get_report.xml", queryMainObjectMap)
					.toString();
			JSONArray jsonworkmonth = new JSONArray(jsonworkmonthreport);
			for (int i = 0; i < jsonworkmonth.length(); i++) {
				JSONObject jsonmonth = jsonworkmonth.getJSONObject(i);
				variablesMap.put("id", jsonmonth.getString("id"));
				variablesMap.put("sn", jsonmonth.getString("sn"));
				variablesMap.put("didate", jsonmonth.getString("didate"));
				variablesMap.put("creator", jsonmonth.getString("creator"));
				variablesMap.put("tuid", jsonmonth.getString("tuid"));
				variablesMap.put("reportstyle", jsonmonth.getString("reportstyle"));
				variablesMap.put("note", jsonmonth.getString("note"));
				variablesMap.put("accountbalance", jsonmonth.getString("accountbalance"));
				variablesMap.put("balancedeposit", jsonmonth.getString("balancedeposit"));
				variablesMap.put("createdate", jsonmonth.getString("createdate"));
			}

		}
		for (Entry entry : variablesMap.entrySet()) {
			request.setAttribute(entry.getKey().toString(), entry.getValue());
		}
		if ("view".equals(opertype)) {
			return "solutions/leasing/financial/fund_plan/fund_plan_show.jsp";
		} else {
			return "solutions/leasing/financial/fund_plan/fund_plan_show.jsp";
		}
	}

	/**
	 * 删除申请
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteFundPlanReport.acl")
	@ResponseBody
	public String deleteFundPlanReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String returnstr = "";
		try {
			this.fs.deleteFundPlanReportApplication(model);
			returnstr = "{flag:\"true\",msg:\"登记成功！\",opertype:\"add\"}";
		} catch (Exception e) {
			e.printStackTrace();
			returnstr = "{flag:\"false\",msg:\"" + e.getMessage() + "\",opertype:\"add\"}";
		}
		return returnstr;
	}

	/**
	 * 完成草稿
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateFundPlanReport.acl")
	@ResponseBody
	public String updateFundPlanReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String returnstr = "";
		try {
			this.fs.updateFundPlanReport(model);
			returnstr = "{flag:\"true\",msg:\"修改成功！\",opertype:\"add\"}";
		} catch (Exception e) {
			e.printStackTrace();
			returnstr = "{flag:\"false\",msg:\"" + e.getMessage() + "\",opertype:\"add\"}";
		}
		return returnstr;
	}

	/**
	 * 修改收款统计表数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/editReceiptCountData.acl")
	@ResponseBody
	public JsonReturnResult editReceiptCountData(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS, "");
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		try {
			this.fs.editReceiptCountData(model);
			returnResult.setReturnType(JsonReturnResultTypeEnum.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}

	/**
	 * 修改付款统计表表数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updatePaymentCountData.acl")
	@ResponseBody
	public JsonReturnResult updatePaymentCountData(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS, "");
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		try {
			this.fs.updatePaymentCountData(model);
			returnResult.setReturnType(JsonReturnResultTypeEnum.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}

	/**
	 * 天信付款流程，查询对应付付款前提
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showPaymentPremiseData.acl")
	@ResponseBody
	public String findPaymentPremiseData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String parameters = model.get("parameterStr");
		String contractid = model.get("contractid");
		String[] parameterArray = parameters.split(",");
		try {
			Map<String, String> queryMainObjectMap = new HashMap<String, String>();
			queryMainObjectMap.put("contract_id", contractid);
			String json_premise_condition_str = this.tableService.getJsonArrayData(
					"eleasing/workflow/contract/contract_comm/contract_premise.xml", queryMainObjectMap).toString();
			JSONArray premiseCondition = new JSONArray(json_premise_condition_str);
			JSONArray premiseConditioncopy = new JSONArray();
			for (int y = 0; y < parameterArray.length; y++) {
				for (int x = 0; x < premiseCondition.length(); x++) {
					JSONObject ee = (JSONObject) premiseCondition.get(x);
					String paymentid = (String) ee.get("paymentid");
					String rr = parameterArray[y];
					if (paymentid.equals(parameterArray[y])) {
						premiseConditioncopy.put(ee);
					}
				}
			}
			String ee = premiseConditioncopy.toString();
			return premiseConditioncopy.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "[]";
	}

	/**
	 * 导入月利息单后更新预提利息表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateInterest.acl")
	@ResponseBody
	public String updateInterest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String returnstr = "";
		try {
			this.fs.updateInterest(model);
			returnstr = "{flag:\"true\",msg:\"更新成功！\",opertype:\"add\"}";
		} catch (Exception e) {
			e.printStackTrace();
			returnstr = "{flag:\"false\",msg:\"" + e.getMessage() + "\",opertype:\"add\"}";
		}
		return returnstr;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(value = "/saveCashDepositData.acl")
	@ResponseBody
	public JsonReturnResult saveCashDepositData(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS, "");
		try {
			this.fs.addCashDepositData(model);
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(value = "/removeCashDepositData.acl")
	@ResponseBody
	public JsonReturnResult removeCashDepositData(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS, "");
		String[]  ids= model.get("ids").split(",");
		List<CashDeposit> cashDeposit=new ArrayList<CashDeposit>();
		String hsql="from CashDeposit  where chargeId= ?";
		try {
			for (int i = 0; i < ids.length; i++) {
				String id = ids[i];
				List<CashDeposit> cashDepositTemp=	this.tableService.findResultsByHSQL(hsql, this.tableService.findEntityByID(CashDepositBase.class, id));
				cashDeposit.addAll(cashDepositTemp);
			}
			this.tableService.removeAllEntites(cashDeposit);
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}

}
