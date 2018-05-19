package com.reckon.controller;

import java.io.InputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.KnowingRentBean;
import com.reckon.bean.RentPlan;
import com.reckon.entity.contract.reckon.fund.ContractFundRentPlanTemp;
import com.reckon.rentcalc.service.RentCalcKonwingRentService;
import com.reckon.service.RentCalculateService;
import com.tenwa.business.controller.BaseController;
import com.tenwa.business.service.TableService;
import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.JacksonUtil;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.utils.LeasingException;

/**
 * 
 * @author sea
 * @date 2014-09-30 下午14:01:13
 * @info 租金测算模型的实现类 该类的主要功能为接收前台参数做相应的测算.
 * @Copyright TENWA
 */
@Controller(value = "rentCalculateController")
@RequestMapping(value = "/**/leasing")
public class RentCalculateController extends BaseController {
	/**
	 * LOG4J日志
	 */
	private static Logger log = Logger.getLogger(RentCalculateController.class);
	 
	@Resource(name = "rentCalculateService")
	private RentCalculateService rentCalculateService;

	@Resource(name = "tableService")
	private TableService tableService;
	
	/**
	 * 已知租金法租金测算模型
	 */
	@Resource(name = "rentCalcKonwingRentService")
	private RentCalcKonwingRentService rentCalcKonwingRentService;
	
	
	/**
	 * 起租及起租前所触发的租金测算入口
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/rentCalculate.action")
	@ResponseBody
	public String rentCalculate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> modelData = QueryUtil.getRequestParameterMapByAjax(request,null,true);// 把请求数据转成map
		log.info("modelData:"+modelData);
		
		/**
		 * TODO:
		 * 注意【流程类型】process的值有两种：
		 * 1.所有项目层的租金测算请求提交时，该值默认是：proj_process
		 * 2.所有合同层的租金测算请求提交时，该值默认是：contract_process
		 * 注意【动作类型】reckontype的值有五种种：
		 * 1.condition 立项、信审
		 * 2.onhire
		 * 3.onhire_more
		 * 4.paln
		 * 5.adjust
		 * 
		 */
		
		//租金推算方法:按年利率计算租金rent、按租金计算年利率rate、已知租借规则测算knowing_rent
			String rentOrRate = modelData.get("rentorrate") ;
			log.info("rentOrRate:"+rentOrRate);
			//测试数据----------------------------------------------END！！！
			
			String process = modelData.get("process");
			log.info("process:"+process);
			String ajaxCallBackScript = "";
			Map<String, Object> returnMap = new HashMap<String, Object>();
			try {
				//根据指定的租金计算需要的年利率
				RentCalculateControllerHelper.calculateYearRateFromRent(modelData);
				//如果是已知年利率情况下做租金测算则需要前端‘年利率’的值不为空
				String yearRate = modelData.get("yearrate");
				log.info("租金测算年利率>>>>>>>>>>>>>>>>>>>"+yearRate);
				returnMap = this.rentCalculateService.calculateRentPlanOld(modelData, null);
				if(null == returnMap.get("rentcalculaters")){
					returnMap.put("rentcalculaters", "yes");
				}
			}  catch (Exception e) {
				e.printStackTrace();
				ajaxCallBackScript = e.getMessage();
				returnMap = new HashMap<String, Object>();
				returnMap.put("rentcalculaters", "no");
				if (ajaxCallBackScript == null || ajaxCallBackScript.equals("")) {
					ajaxCallBackScript = "测算已经出错,但是没有捕获到错误消息!";
				}
				returnMap.put("rentcalculatemsg", ajaxCallBackScript.replaceAll("'", " ").replaceAll("\"", " "));
			}
			
			if (returnMap == null || returnMap.size() == 0) {
				returnMap = new HashMap<String, Object>();
				returnMap.put("rentcalculaters", "no");
				returnMap.put("rentcalculatemsg", "测算失败!没有获取测算结果!");
			}
			String jsonResult = "";
			ObjectMapper mapper = new ObjectMapper();
			//mapper.configure(Feature.WRITE_NULL_PROPERTIES, true);// 不写空字符
			JSONArray array = new JSONArray();
			log.info(returnMap.get("fundrentplan"));
			String rentJsonStr =  ""; 
			try {
				jsonResult = mapper.writeValueAsString(returnMap);
			} catch (Exception e) {
				e.printStackTrace();
				returnMap = new HashMap<String, Object>();
				returnMap.put("rentcalculaters", "no");
				returnMap.put("rentcalculatemsg", "测算失败!构建JSON返回结果出错!");
				jsonResult = mapper.writeValueAsString(returnMap);
			}
			log.info("sea返回");
			log.info("jsonResult:"+jsonResult);
			return jsonResult.toLowerCase();
	}

	
	
	/**
	 * 租金计划变更的action
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/adjustCalculateOld.action")
	@ResponseBody
	public String adjustCalculateOld(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> modelData = QueryUtil.getRequestParameterMapByAjax(request);// 把请求数据转成map
		log.info("变更传入的MAP集合:"+modelData);
		String ajaxCallBackScript = "";
		Map<String, Object> returnMap = new HashMap<String, Object>();
		try {
			returnMap = this.rentCalculateService.adjustCalculate(modelData);
		} catch (LeasingException e) {
			e.printStackTrace();
			ajaxCallBackScript = "捕获到异常:" + e.getMessage();
			returnMap = new HashMap<String, Object>();
			returnMap.put("rentcalculaters", "no");
			if (ajaxCallBackScript == null || ajaxCallBackScript.equals("")) {
				ajaxCallBackScript = "发现异常但未抛出!";
			}
			returnMap.put("rentcalculatemsg", ajaxCallBackScript.replaceAll("'", " ").replaceAll("\"", " "));
		} catch (Exception e) {
			e.printStackTrace();
			ajaxCallBackScript = e.getMessage();
			returnMap = new HashMap<String, Object>();
			returnMap.put("rentcalculaters", "no");
			if (ajaxCallBackScript == null || ajaxCallBackScript.equals("")) {
				ajaxCallBackScript = "测算已经出错,但是没有捕获到错误消息!";
			}
			returnMap.put("rentcalculatemsg", ajaxCallBackScript.replaceAll("'", " ").replaceAll("\"", " "));
		}
		if (returnMap == null || returnMap.size() == 0) {
			returnMap = new HashMap<String, Object>();
			returnMap.put("rentcalculaters", "no");
			returnMap.put("rentcalculatemsg", "测算失败!没有获取测算结果!");
		} else {
			returnMap.put("rentcalculaters", "yes");
			if (returnMap.get("rentcalculatemsg") == null) {
				returnMap.put("rentcalculatemsg", "租金计划变更测算成功!");
			}
		}
		String jsonResult = "";
		//mapper 是jackson的工具
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.WRITE_NULL_PROPERTIES, false);// 不写空字符
		try {
			log.info("returnMap:"+returnMap);
			jsonResult = mapper.writeValueAsString(returnMap);
		} catch (Exception e) {
			e.printStackTrace();
			returnMap = new HashMap<String, Object>();
			returnMap.put("rentcalculaters", "no");
			returnMap.put("rentcalculatemsg", "测算失败!构建JSON返回结果出错!");
			jsonResult = mapper.writeValueAsString(returnMap);
		}
		log.info("jsonResult:"+jsonResult);
		return jsonResult.toLowerCase();
	}

	/**
	 * 租金计划修改的action
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/updateCalculateOld.action")
	@ResponseBody
	public String updateCalculateOld(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> modelData = QueryUtil.getRequestParameterMapByAjax(request);// 把请求数据转成map
		String ajaxCallBackScript = "";
		Map<String, Object> returnMap = new HashMap<String, Object>();
		try {
			Map<String, Object> rentPlan = new HashMap<String, Object>();
			rentPlan = this.rentCalculateService.updateCalculateOld(modelData);
			if (rentPlan != null && rentPlan.size() > 0) {
				returnMap.putAll(rentPlan);
			}
		} catch (LeasingException e) {
			e.printStackTrace();
			ajaxCallBackScript = "捕获到异常:" + e.getMessage();
			returnMap = new HashMap<String, Object>();
			returnMap.put("rentcalculaters", "no");
			if (ajaxCallBackScript == null || ajaxCallBackScript.equals("")) {
				ajaxCallBackScript = "发现异常但未抛出!";
			}
			returnMap.put("rentcalculatemsg", ajaxCallBackScript.replaceAll("'", " ").replaceAll("\"", " "));
		} catch (Exception e) {
			e.printStackTrace();
			ajaxCallBackScript = e.getMessage();
			returnMap = new HashMap<String, Object>();
			returnMap.put("rentcalculaters", "no");
			if (ajaxCallBackScript == null || ajaxCallBackScript.equals("")) {
				ajaxCallBackScript = "测算已经出错,但是没有捕获到错误消息!";
			}
			returnMap.put("rentcalculatemsg", ajaxCallBackScript.replaceAll("'", " ").replaceAll("\"", " "));
		}
		if (returnMap == null || returnMap.size() == 0) {
			returnMap = new HashMap<String, Object>();
			returnMap.put("rentcalculaters", "no");
			returnMap.put("rentcalculatemsg", "测算失败!没有获取测算结果!");
		} else {
			if (returnMap.get("rentcalculatemsg") == null) {
				returnMap.put("rentcalculaters", "yes");
				returnMap.put("rentcalculatemsg", "租金计划变更测算成功!");
			}
		}
		
		String jsonResult = "";
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.WRITE_NULL_PROPERTIES, false);// 不写空字符
		try {
			jsonResult = mapper.writeValueAsString(returnMap);
		} catch (Exception e) {
			e.printStackTrace();
			returnMap = new HashMap<String, Object>();
			returnMap.put("rentcalculaters", "no");
			returnMap.put("rentcalculatemsg", "测算失败!构建JSON返回结果出错!");
			jsonResult = mapper.writeValueAsString(returnMap);
		}
		return jsonResult.toLowerCase();
	}


	/**
	 * 
	 * <p>租金计划变更:撤销调整。</p>
	 * <p>操作步骤：。</p>
	 * <pre>
	 * 1.将租金测算相关的所有数据从正式表移往临时表中
	 * 2.查询临时表数据构建返回的JSON（注意：第一步如果正式表没数据，移的是空数据入临时表，则第二步会报错！）。
	 * </pre>
	 * @author sea
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/resetAdjust.action")
	@ResponseBody
	public String resetAdjust(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> modelData = QueryUtil.getRequestParameterMapByAjax(request);// 把请求数据转成map
		String ajaxCallBackScript = "";
		Map<String, Object> returnMap = new HashMap<String, Object>();
		try {
			Map<String, Object> rentPlan = new HashMap<String, Object>();
			rentPlan = this.rentCalculateService.resetConditionData(modelData.get("fund_rent_adjust.contractid"), modelData.get("fund_rent_adjust.docid"));
			if (rentPlan != null && rentPlan.size() > 0) {
				returnMap.putAll(rentPlan);
			}
		} catch (LeasingException e) {
			e.printStackTrace();
			ajaxCallBackScript = "捕获到异常:" + e.getMessage();
			returnMap = new HashMap<String, Object>();
			returnMap.put("rentcalculaters", "no");
			if (ajaxCallBackScript == null || ajaxCallBackScript.equals("")) {
				ajaxCallBackScript = "发现异常但未抛出!";
			}
			returnMap.put("rentcalculatemsg", ajaxCallBackScript.replaceAll("'", " ").replaceAll("\"", " "));
		} catch (Exception e) {
			e.printStackTrace();
			ajaxCallBackScript = e.getMessage();
			returnMap = new HashMap<String, Object>();
			returnMap.put("rentcalculaters", "no");
			if (ajaxCallBackScript == null || ajaxCallBackScript.equals("")) {
				ajaxCallBackScript = "测算已经出错,但是没有捕获到错误消息!";
			}
			returnMap.put("rentcalculatemsg", ajaxCallBackScript.replaceAll("'", " ").replaceAll("\"", " "));
		}
		if (returnMap == null || returnMap.size() == 0) {
			returnMap = new HashMap<String, Object>();
			returnMap.put("rentcalculaters", "no");
			returnMap.put("rentcalculatemsg", "测算失败!没有获取测算结果!");
		} else {
			if (returnMap.get("rentcalculatemsg") == null) {
				returnMap.put("rentcalculatemsg", "撤销成功!");
			}
		}
		String jsonResult = "";
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.WRITE_NULL_PROPERTIES, false);// 不写空字符
		String rentJsonStr =  "";
		try {
			//租金计划的json单独处理
			rentJsonStr =  (String) returnMap.get("fundrentplan"); 
			log.info("租金计划临时最终JSON串:"+rentJsonStr);
			returnMap.remove("fundrentplan");
			
			jsonResult = mapper.writeValueAsString(returnMap);
		} catch (Exception e) {
			e.printStackTrace();
			returnMap = new HashMap<String, Object>();
			returnMap.put("rentcalculaters", "no");
			returnMap.put("rentcalculatemsg", "测算失败!构建JSON返回结果出错!");
			jsonResult = mapper.writeValueAsString(returnMap);
		}
		
		jsonResult = "{"+rentJsonStr+","+jsonResult.substring(1, jsonResult.length()-1)+"}";
		log.info("jsonResult:"+jsonResult);
		
		return jsonResult.toLowerCase();
	}


	/**
	 * 合同中途终止的action
	 * <p>合同终止输入‘约定终止日’后所做的一些查询及租金折现操作。</P>
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/contractMidwayEnd.action")
	@ResponseBody
	public String contractMidwayEnd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "";
	}
	
	/*
	 * 已知租金法action
	 */
	@RequestMapping(value = "/knowRentCalculate.action")
	@ResponseBody
	public String knowRentCalculate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String periodType = request.getParameter("periodType"); //付租方式
		log.info("periodType:"+periodType);
		String incomeNumber = request.getParameter("endRentList"); //年还租次数
		log.info("incomeNumber:"+incomeNumber);
		String leaseAmt = request.getParameter("leaseAmt"); // 起租本金/融资额
		log.info("leaseAmt:"+leaseAmt);
		String incomeNumberYear = request.getParameter("incomeNumberYear"); //付租类型
		log.info("incomeNumberYear:"+incomeNumberYear);
		
		String tempAttr = request.getParameter("tempAttr"); // 已知租金法多行值
		//AJAX已经转码一次，这里只需要解码一次
		tempAttr = URLDecoder.decode(tempAttr, "UTF-8"); //解码获值
		
		ObjectMapper jsonMapper = new ObjectMapper();
		Map[] newSetMaps = jsonMapper.readValue(tempAttr, Map[].class);
		RentPlan temp = new ContractFundRentPlanTemp();//随便找个实现类即可，目的只是为了暂存一下数据而已
		
		for (int j = 0; j < newSetMaps.length; j++) {
			Map<String, String> map = newSetMaps[j];
			String rentAdjust = map.get("rentadjust");
		}
		
		//
		log.info("tempAttr:"+tempAttr);
		//获取固定格式的对象
		List<KnowingRentBean> kniwnRentBeans = this.rentCalculateService.getList(tempAttr);
		
		//方式2：json转换成list集合 
		ObjectMapper mapper = JacksonUtil.newObjectMapper();
		List<String> list = mapper.readValue(tempAttr, List.class);
		
		 /*
		 参数1：periodType 付租方式  期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
		  参数2：leaseAmt 起租本金/融资额
		  参数3：incomeNumberYear 年还租次数
		  */ 
		 ConditionBean cb = new ConditionBean();
		 cb.setPeriodType(periodType);
		 cb.setCleanLeaseMoney(leaseAmt);
		 cb.setIncomeNumberYear(incomeNumberYear);
		 
		 //计算年利率
		 String rate = rentCalcKonwingRentService.getYearRate(cb, kniwnRentBeans);
		 log.info("rate:"+rate);
		 return rate;
	}
	
	/**
	 * 商务条件报价查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectContractCondition.action")
	public String selectContractCondition(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> modelData = QueryUtil.getRequestParameterMapNoAjax(request);// 把请求数据转成map
		String contractid = modelData.get("contractid").toString();
		request.setAttribute("isWhere", "true");
		if (contractid != null && !contractid.isEmpty()) {
			request.setAttribute("isRs", "true");
			ContractInfo cinfo = this.tableService.findEntityByID(ContractInfo.class, contractid);
			if (cinfo != null && !cinfo.getContractPutNumber().isEmpty()) {
				//request.setAttribute("business_type", cinfo.getBusinessType().getCode());// 设置业务类型
																							// 决定显示那个jsp页面
				// 查询数据
				Map<String, String> variablesMap = new HashMap<String, String>();// 存入租金测算的数据
				if (cinfo.getContractCondition() != null) {
					variablesMap.putAll(this.tableService.getEntityPropertiesToStringMap(cinfo.getContractCondition(), null, ""));
				}
				
				variablesMap.put("contractid", cinfo.getId());
				variablesMap.put("process", "cont_ready_process");
				variablesMap.put("custname", cinfo.getCustId().getCustName());
				variablesMap.put("custid", cinfo.getCustId().getId());
				Map<String, String> queryMainObjectMap = new HashMap<String, String>();
				queryMainObjectMap.put("contract_id", contractid);
				if (!cinfo.getContractFundRentPlans().isEmpty()) {// 租金计划
					String json_fund_rent_plan_str = this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_fund_rent_plan.xml", queryMainObjectMap).toString();
					variablesMap.put("json_fund_rent_plan_str", json_fund_rent_plan_str);
				}
				if (!cinfo.getContractCashDetails().isEmpty()) {// 合同现金流
					String json_fund_cash_flow_str = this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_planchange/contract_fund_cash_flow.xml", queryMainObjectMap).toString();
					variablesMap.put("json_fund_cash_flow_str", json_fund_cash_flow_str);
				}
				if (!cinfo.getFundFundChargePlans().isEmpty()) {
					String json_fund_charge_plan_str = this.tableService.getJsonArrayData("eleasing/workflow/contract/fund_fund_charge_plan.xml", queryMainObjectMap).toString();
					variablesMap.put("json_fund_fund_charge_str", json_fund_charge_plan_str);
				}
				//分段测算配置
				JSONArray array1= this.tableService.getJsonArrayData("eleasing/workflow/proj/proj_approval/special_regular.xml",queryMainObjectMap);
				if(null!=array1&&array1.length()>0){
					variablesMap.put("json_special_regular_str",array1.getJSONObject(0).optString("special_regular_jsons").replaceAll("\'", "\""));
				}
				// 把数据切入到request中
				for (String key : variablesMap.keySet()) {
					request.setAttribute(key, variablesMap.get(key));
				}
			} else {
				request.setAttribute("isRs", "false");
			}
		} else {
			request.setAttribute("isWhere", "false");
		}
		return "solutions/workflow/forms/reckon/rent_readonly/condition_read.jsp";
		
	}
	
	@RequestMapping(value = "/updateFundFundPlan.action")
	@ResponseBody
	public String updateFundFundPlan(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> modelData = QueryUtil.getRequestParameterMapNoAjax(request);// 把请求数据转成map
		Map<String, Object> result  = new HashMap<String, Object>();
		ObjectMapper om = new ObjectMapper();
		try{
			result =  this.rentCalculateService.updateFundFundPlan(modelData);
			result.put("updateInfo", "成功");
		}catch(Exception e){
			e.printStackTrace();
			result.put("updateInfo", "失败");
		}
		String returnStr = om.writeValueAsString(result);
		return returnStr.toLowerCase();
	}
	@RequestMapping(value = "/updateCashFlow.action")
	@ResponseBody
	public String updateCashFlow(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> modelData = QueryUtil.getRequestParameterMapNoAjax(request);// 把请求数据转成map
		Map<String, Object> result  = new HashMap<String, Object>();
		ObjectMapper om = new ObjectMapper();
		try{
			result =  this.rentCalculateService.updateCashFlow(modelData);
			result.put("updateInfo", "成功");
		}catch(Exception e){
			e.printStackTrace();
			result.put("updateInfo", "失败");
		}
		String returnStr = om.writeValueAsString(result);
		return returnStr.toLowerCase();
	}
	/**
	 * excel 导入
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/IrrRentCalculate.action")
	@ResponseBody
	public String IrrRentCalculate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> modelData = QueryUtil.getRequestParameterMapNoAjax(request);// 把请求数据转成map
		String uploadSuccessCallbackFunction = modelData.get("_success");
		String uploadFailtureCallbackFunction = modelData.get("_failture");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile("tableImportExcel");
		String suffix =  multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".")+1);
		String callBackScript = "";
		if(!suffix.equals("xls") && !suffix.equals("xlsx")){
			callBackScript = "<script type='text/javascript'>" + uploadFailtureCallbackFunction + "('文件格式不正确,请先下载模版在进行导入！');</script>";
		}else{
			InputStream source = multipartFile.getInputStream();
			try{
				//商务报价参数数据格式化
				String conditionitem =  modelData.get("conditionitem");
				JSONObject conditionJson = new JSONObject(conditionitem);
				Map<String, String> conditionMap = new HashMap<String, String>();
				for(Iterator<String> ite = conditionJson.keys() ; ite.hasNext();){
					String key = ite.next();
					conditionMap.put(key, conditionJson.getString(key));
				}
				//传递Excel的格式
				String excelType = "VERSION2003";
				if(suffix.equals("xlsx")){
					excelType = "VERSION2007";
				}
				conditionMap.put("excelType", excelType);
				Map<String, Object> resultMap = this.rentCalculateService.calculateRentByIrr(conditionMap, source);
				ObjectMapper mapper = new ObjectMapper();
				String resultInfo =  mapper.writeValueAsString(resultMap).toLowerCase();
				callBackScript = "<script type='text/javascript'>" + uploadSuccessCallbackFunction + "('"+resultInfo+"');</script>";
			}catch(NumberFormatException e){
				e.printStackTrace();
				callBackScript = "<script type='text/javascript'>" + uploadFailtureCallbackFunction + "('上传的租金计划中，金额格式不正确，请检查后重新上传！');</script>";
			}catch(BusinessException e){
				callBackScript = "<script type='text/javascript'>" + uploadFailtureCallbackFunction + "('"+e.getMessage()+"');</script>";
			}catch (Exception e) {
				e.printStackTrace();
				callBackScript = "<script type='text/javascript'>" + uploadFailtureCallbackFunction + "('服务器繁忙，请稍后进行上传！！');</script>";
			}
		}
		
		this.ajaxReturn(response, callBackScript); 
		return null;
	}
}
