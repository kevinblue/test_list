package com.tenwa.leasing.controller.SaleWeekReportController;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.entity.User;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.cust.SaleWeekReport;
import com.tenwa.leasing.service.SaleWeekReportService.SaleWeekReportService;

/**
 * 周报信息
 * 
 * @Title: AssetsNetMonitorController.java
 * @package: com.tenwa.leasing.controller.AssetsNetMonitorController
 * @author: ganwei
 * @date 2014年11月20日 上午9:27:24
 * @version V5.1
 */
@Controller(value = "saleWeekReportController")
@RequestMapping(value = "/**/acl")
public class SaleWeekReportController {

	@Resource(name = "tableService")
	private TableService tableService;

	@Resource(name = "saleWeekReportService")
	private SaleWeekReportService saleWeekReportService;
		
/**
	 * 我的周报申请页面展示
	 * 		
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showSaleWeekReport.acl")
	public String showSaleWeekReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil		
				.getRequestParameterMapByAjax(request);
		User user = SecurityUtil.getPrincipal();
		String opertype = model.get("opertype");
		Map<String, String> variablesMap = new HashMap<String, String>();
		variablesMap.put("opertype", opertype);
		String t="";
		if ("add".equals(opertype)) {
			
			Map<String, String> queryMap =new HashMap<String, String>();			
			queryMap.put("weekstyle","草稿");
			queryMap.put("registerid",user.getId());
			queryMap.put("startDate", model.get("startdate"));
			String jsonworkweekone=this.tableService.getJsonArrayData("/eleasing/jsp/other/get_sale_week_report_list01.xml", queryMap).toString();
			JSONArray jsonworkone=new JSONArray(jsonworkweekone);
			for(int i=0;i<jsonworkone.length();i++){
				JSONObject jsonweekone=jsonworkone.getJSONObject(i);
				variablesMap.put("workid", jsonweekone.getString("id"));
				variablesMap.put("registername", jsonweekone.getString("realname"));
				variablesMap.put("registerid", jsonweekone.getString("tuid"));
				variablesMap.put("deptname", jsonweekone.getString("deptname"));
				variablesMap.put("childdeptname", jsonweekone.getString("childdeptname"));
				variablesMap.put("startdate", jsonweekone.getString("startdate"));
				variablesMap.put("enddate", jsonweekone.getString("enddate"));
				variablesMap.put("serialid", jsonweekone.getString("serialid"));
				variablesMap.put("weekstyle", jsonweekone.getString("weekstyle"));
				t= jsonweekone.getString("startdate");
			}
							
		} else {
			String workid = model.get("workid");		
			Map<String,String> queryMainObjectMap = new HashMap<String,String>();
			queryMainObjectMap.put("id", workid);
			String jsonworkweek=this.tableService.getJsonArrayData("/eleasing/jsp/other/get_sale_week_report_list01.xml", queryMainObjectMap).toString();
			JSONArray jsonwork=new JSONArray(jsonworkweek);
			for(int i=0;i<jsonwork.length();i++){
				JSONObject jsonweek=jsonwork.getJSONObject(i);
				variablesMap.put("workid", jsonweek.getString("id"));
				variablesMap.put("registername", jsonweek.getString("realname"));
				variablesMap.put("registerid", jsonweek.getString("tuid"));
				variablesMap.put("deptname", jsonweek.getString("deptname"));
				variablesMap.put("childdeptname", jsonweek.getString("childdeptname"));
				variablesMap.put("startdate", jsonweek.getString("startdate"));
				variablesMap.put("enddate", jsonweek.getString("enddate"));
				variablesMap.put("serialid", jsonweek.getString("serialid"));
				variablesMap.put("weekstyle", jsonweek.getString("weekstyle"));
				t= jsonweek.getString("startdate");
			}
	
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(t); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day=cal.get(Calendar.DATE);
		int month=cal.get(Calendar.MONTH)+1;
		String mon="";
		if(month<10){
			 mon=0+String.valueOf(month);
		}else{
			mon=String.valueOf(month);
		}
		if(day<10){
			variablesMap.put("weekone", mon+"-0"+String.valueOf(day));			
		}else{
			variablesMap.put("weekone", mon+"-"+String.valueOf(day));
		}
		cal.set(Calendar.DAY_OF_MONTH,cal.get(Calendar.DAY_OF_MONTH)+1);//让日期加1
		int day1=cal.get(Calendar.DATE);
		if(day1<10){
			variablesMap.put("weektwo", mon+"-0"+String.valueOf(day1));
		}else{
			variablesMap.put("weektwo", mon+"-"+String.valueOf(day1));
		}		
		cal.set(Calendar.DAY_OF_MONTH,cal.get(Calendar.DAY_OF_MONTH)+1);//让日期加1
		int day2=cal.get(Calendar.DATE);
		if(day2<10){
			variablesMap.put("weekthree", mon+"-0"+String.valueOf(day2));
		}else{
			variablesMap.put("weekthree", mon+"-"+String.valueOf(day2));
		}		
		cal.set(Calendar.DAY_OF_MONTH,cal.get(Calendar.DAY_OF_MONTH)+1);//让日期加1
		int day3=cal.get(Calendar.DATE);
		if(day3<10){
			variablesMap.put("weekfour", mon+"-0"+String.valueOf(day3));
		}else{
			variablesMap.put("weekfour", mon+"-"+String.valueOf(day3));
		}		
		cal.set(Calendar.DAY_OF_MONTH,cal.get(Calendar.DAY_OF_MONTH)+1);//让日期加1
		int day4=cal.get(Calendar.DATE);
		if(day4<10){
			variablesMap.put("weekfive", mon+"-0"+String.valueOf(day4));
		}else{
			variablesMap.put("weekfive", mon+"-"+String.valueOf(day4));
		}		
		GregorianCalendar gc=new GregorianCalendar(); 
		gc.setTime(date); 
		gc.add(3,+1);
		variablesMap.put("nextstartdate", gc.get(Calendar.YEAR)+"-"+(gc.get(Calendar.MONTH)+1)+"-"+gc.get(Calendar.DATE));
		gc.add(5,+6);
		variablesMap.put("nextenddate", gc.get(Calendar.YEAR)+"-"+(gc.get(Calendar.MONTH)+1)+"-"+gc.get(Calendar.DATE));
		GregorianCalendar gr=new GregorianCalendar(); 
		gr.setTime(date); 
		gr.add(3,+1);
		gr.add(5,+0);
		int months=gc.get(Calendar.MONTH)+1;
		String mm="";
		if(months<10){
			mm=0+String.valueOf(months);
		}else{
			mm=String.valueOf(months);
		}
		if(gr.get(Calendar.DATE)<10){
			variablesMap.put("nextweekone", mm+"-0"+gr.get(Calendar.DATE));			
		}else{
			variablesMap.put("nextweekone", mm+"-"+gr.get(Calendar.DATE));
		}
		gr.add(5,+1);
		if(gr.get(Calendar.DATE)<10){
			variablesMap.put("nextweektwo", mm+"-0"+gr.get(Calendar.DATE));			
		}else{
			variablesMap.put("nextweektwo", mm+"-"+gr.get(Calendar.DATE));	
		}
		gr.add(5,+1);
		if(gr.get(Calendar.DATE)<10){
			variablesMap.put("nextweekthree", mm+"-0"+gr.get(Calendar.DATE));			
		}else{
			variablesMap.put("nextweekthree", mm+"-"+gr.get(Calendar.DATE));	
		}
		gr.add(5,+1);
		if(gr.get(Calendar.DATE)<10){
			variablesMap.put("nextweekfour", mm+"-0"+gr.get(Calendar.DATE));			
		}else{
			variablesMap.put("nextweekfour", mm+"-"+gr.get(Calendar.DATE));			
		}
		gr.add(5,+1);
		if(gr.get(Calendar.DATE)<10){
			variablesMap.put("nextweekfive", mm+"-0"+gr.get(Calendar.DATE));			
		}else{
			variablesMap.put("nextweekfive", mm+"-"+gr.get(Calendar.DATE));
		}
		
		for (Entry entry : variablesMap.entrySet()) {
			request.setAttribute(entry.getKey().toString(), entry.getValue());
		}

		if ("view".equals(opertype)) {
			return "solutions/workflow/forms/other/assets_network_monitor/sale_week_show.jsp";
		} else{			
			return "solutions/workflow/forms/other/assets_network_monitor/sale_week_show.jsp";
		}
	}

	
	/**
	 * 团队周报申请页面展示
	 * 		
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showSaleTeamWeekReport.acl")
	public String showSaleTeamWeekReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil		
				.getRequestParameterMapByAjax(request);
		User user = SecurityUtil.getPrincipal();
		String opertype = model.get("opertype");
		Map<String, String> variablesMap = new HashMap<String, String>();
		variablesMap.put("opertype", opertype);
		String t="";
		if ("add".equals(opertype)) {
			
			Map<String, String> queryMap =new HashMap<String, String>();			
			queryMap.put("weekstyle","草稿");
			queryMap.put("registerid",user.getId());
			queryMap.put("startDate", model.get("startdate"));
			String jsonworkweekone=this.tableService.getJsonArrayData("/eleasing/jsp/other/get_sale_team_week_report_list01.xml", queryMap).toString();
			JSONArray jsonworkone=new JSONArray(jsonworkweekone);
			for(int i=0;i<jsonworkone.length();i++){
				JSONObject jsonweekone=jsonworkone.getJSONObject(i);
				variablesMap.put("workid", jsonweekone.getString("id"));
				variablesMap.put("registername", jsonweekone.getString("realname"));
				variablesMap.put("registerid", jsonweekone.getString("tuid"));
				variablesMap.put("deptname", jsonweekone.getString("deptname"));
				variablesMap.put("childdeptname", jsonweekone.getString("childdeptname"));
				variablesMap.put("startdate", jsonweekone.getString("startdate"));
				variablesMap.put("enddate", jsonweekone.getString("enddate"));
				variablesMap.put("serialid", jsonweekone.getString("serialid"));
				variablesMap.put("weekstyle", jsonweekone.getString("weekstyle"));
				t= jsonweekone.getString("startdate");
			}
							
		} else {
			String workid = model.get("workid");		
			Map<String,String> queryMainObjectMap = new HashMap<String,String>();
			queryMainObjectMap.put("id", workid);
			String jsonworkweek=this.tableService.getJsonArrayData("/eleasing/jsp/other/get_sale_team_week_report_list01.xml", queryMainObjectMap).toString();
			JSONArray jsonwork=new JSONArray(jsonworkweek);
			for(int i=0;i<jsonwork.length();i++){
				JSONObject jsonweek=jsonwork.getJSONObject(i);
				variablesMap.put("workid", jsonweek.getString("id"));
				variablesMap.put("registername", jsonweek.getString("realname"));
				variablesMap.put("registerid", jsonweek.getString("tuid"));
				variablesMap.put("deptname", jsonweek.getString("deptname"));
				variablesMap.put("childdeptname", jsonweek.getString("childdeptname"));
				variablesMap.put("startdate", jsonweek.getString("startdate"));
				variablesMap.put("enddate", jsonweek.getString("enddate"));
				variablesMap.put("serialid", jsonweek.getString("serialid"));
				variablesMap.put("weekstyle", jsonweek.getString("weekstyle"));
				t= jsonweek.getString("startdate");
			}
	
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(t); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day=cal.get(Calendar.DATE);
		int month=cal.get(Calendar.MONTH)+1;
		String mon="";
		if(month<10){
			 mon=0+String.valueOf(month);
		}else{
			mon=String.valueOf(month);
		}
		if(day<10){
			variablesMap.put("weekone", mon+"-0"+String.valueOf(day));			
		}else{
			variablesMap.put("weekone", mon+"-"+String.valueOf(day));
		}
		cal.set(Calendar.DAY_OF_MONTH,cal.get(Calendar.DAY_OF_MONTH)+1);//让日期加1
		int day1=cal.get(Calendar.DATE);
		if(day1<10){
			variablesMap.put("weektwo", mon+"-0"+String.valueOf(day1));
		}else{
			variablesMap.put("weektwo", mon+"-"+String.valueOf(day1));
		}		
		cal.set(Calendar.DAY_OF_MONTH,cal.get(Calendar.DAY_OF_MONTH)+1);//让日期加1
		int day2=cal.get(Calendar.DATE);
		if(day2<10){
			variablesMap.put("weekthree", mon+"-0"+String.valueOf(day2));
		}else{
			variablesMap.put("weekthree", mon+"-"+String.valueOf(day2));
		}		
		cal.set(Calendar.DAY_OF_MONTH,cal.get(Calendar.DAY_OF_MONTH)+1);//让日期加1
		int day3=cal.get(Calendar.DATE);
		if(day3<10){
			variablesMap.put("weekfour", mon+"-0"+String.valueOf(day3));
		}else{
			variablesMap.put("weekfour", mon+"-"+String.valueOf(day3));
		}		
		cal.set(Calendar.DAY_OF_MONTH,cal.get(Calendar.DAY_OF_MONTH)+1);//让日期加1
		int day4=cal.get(Calendar.DATE);
		if(day4<10){
			variablesMap.put("weekfive", mon+"-0"+String.valueOf(day4));
		}else{
			variablesMap.put("weekfive", mon+"-"+String.valueOf(day4));
		}		
		GregorianCalendar gc=new GregorianCalendar(); 
		gc.setTime(date); 
		gc.add(3,+1);
		variablesMap.put("nextstartdate", gc.get(Calendar.YEAR)+"-"+(gc.get(Calendar.MONTH)+1)+"-"+gc.get(Calendar.DATE));
		gc.add(5,+6);
		variablesMap.put("nextenddate", gc.get(Calendar.YEAR)+"-"+(gc.get(Calendar.MONTH)+1)+"-"+gc.get(Calendar.DATE));
		GregorianCalendar gr=new GregorianCalendar(); 
		gr.setTime(date); 
		gr.add(3,+1);
		gr.add(5,+0);
		int months=gc.get(Calendar.MONTH)+1;
		String mm="";
		if(months<10){
			mm=0+String.valueOf(months);
		}else{
			mm=String.valueOf(months);
		}
		if(gr.get(Calendar.DATE)<10){
			variablesMap.put("nextweekone", mm+"-0"+gr.get(Calendar.DATE));			
		}else{
			variablesMap.put("nextweekone", mm+"-"+gr.get(Calendar.DATE));
		}
		gr.add(5,+1);
		if(gr.get(Calendar.DATE)<10){
			variablesMap.put("nextweektwo", mm+"-0"+gr.get(Calendar.DATE));			
		}else{
			variablesMap.put("nextweektwo", mm+"-"+gr.get(Calendar.DATE));	
		}
		gr.add(5,+1);
		if(gr.get(Calendar.DATE)<10){
			variablesMap.put("nextweekthree", mm+"-0"+gr.get(Calendar.DATE));			
		}else{
			variablesMap.put("nextweekthree", mm+"-"+gr.get(Calendar.DATE));	
		}
		gr.add(5,+1);
		if(gr.get(Calendar.DATE)<10){
			variablesMap.put("nextweekfour", mm+"-0"+gr.get(Calendar.DATE));			
		}else{
			variablesMap.put("nextweekfour", mm+"-"+gr.get(Calendar.DATE));			
		}
		gr.add(5,+1);
		if(gr.get(Calendar.DATE)<10){
			variablesMap.put("nextweekfive", mm+"-0"+gr.get(Calendar.DATE));			
		}else{
			variablesMap.put("nextweekfive", mm+"-"+gr.get(Calendar.DATE));
		}
		
		for (Entry entry : variablesMap.entrySet()) {
			request.setAttribute(entry.getKey().toString(), entry.getValue());
		}

		if ("view".equals(opertype)) {
			return "solutions/workflow/forms/other/team_network_report/sale_team_week_show.jsp";
		} else{			
			return "solutions/workflow/forms/other/team_network_report/sale_team_week_show.jsp";
		}
	}

	
	/**
	 * 周报汇总申请页面展示
	 * 		
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showSumSaleWeekReport.acl")
	public String showSumSaleWeekReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil		
				.getRequestParameterMapByAjax(request);
		User user = SecurityUtil.getPrincipal();
		String opertype = model.get("opertype");
		Map<String, String> variablesMap = new HashMap<String, String>();
		variablesMap.put("opertype", opertype);
		String t="";
		if ("add".equals(opertype)) {
			
			Map<String, String> queryMap =new HashMap<String, String>();			
			queryMap.put("weekstyle","草稿");
			queryMap.put("registerid",user.getId());
			queryMap.put("startDate", model.get("startdate"));
			String jsonworkweekone=this.tableService.getJsonArrayData("/eleasing/jsp/other/get_sum_sale_week_report_list01.xml", queryMap).toString();
			JSONArray jsonworkone=new JSONArray(jsonworkweekone);
			for(int i=0;i<jsonworkone.length();i++){
				JSONObject jsonweekone=jsonworkone.getJSONObject(i);
				variablesMap.put("workid", jsonweekone.getString("id"));
				variablesMap.put("registername", jsonweekone.getString("realname"));
				variablesMap.put("registerid", jsonweekone.getString("tuid"));
				variablesMap.put("deptname", jsonweekone.getString("deptname"));
				variablesMap.put("childdeptname", jsonweekone.getString("childdeptname"));
				variablesMap.put("startdate", jsonweekone.getString("startdate"));
				variablesMap.put("enddate", jsonweekone.getString("enddate"));
				variablesMap.put("serialid", jsonweekone.getString("serialid"));
				variablesMap.put("weekstyle", jsonweekone.getString("weekstyle"));
				t= jsonweekone.getString("startdate");
			}
							
		} else {
			String workid = model.get("workid");
			String starttime = model.get("startdate");
			Map<String,String> queryMainObjectMap = new HashMap<String,String>();
			queryMainObjectMap.put("id", workid);
			queryMainObjectMap.put("startdate", starttime);
			String jsonworkweek=this.tableService.getJsonArrayData("/eleasing/jsp/other/get_sum_sale_week_report_list01.xml", queryMainObjectMap).toString();
			JSONArray jsonwork=new JSONArray(jsonworkweek);
			for(int i=0;i<jsonwork.length();i++){
				JSONObject jsonweek=jsonwork.getJSONObject(i);
				variablesMap.put("workid", jsonweek.getString("id"));
				variablesMap.put("registername", jsonweek.getString("realname"));
				variablesMap.put("registerid", jsonweek.getString("tuid"));
				variablesMap.put("deptname", jsonweek.getString("deptname"));
				variablesMap.put("childdeptname", jsonweek.getString("childdeptname"));
				variablesMap.put("startdate", jsonweek.getString("startdate"));
				variablesMap.put("enddate", jsonweek.getString("enddate"));
				variablesMap.put("serialid", jsonweek.getString("serialid"));
				variablesMap.put("weekstyle", jsonweek.getString("weekstyle"));
				t= jsonweek.getString("startdate");
			}
	
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(t); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day=cal.get(Calendar.DATE);
		int month=cal.get(Calendar.MONTH)+1;
		String mon="";
		if(month<10){
			 mon=0+String.valueOf(month);
		}else{
			mon=String.valueOf(month);
		}
		if(day<10){
			variablesMap.put("weekone", mon+"-0"+String.valueOf(day));			
		}else{
			variablesMap.put("weekone", mon+"-"+String.valueOf(day));
		}
		cal.set(Calendar.DAY_OF_MONTH,cal.get(Calendar.DAY_OF_MONTH)+1);//让日期加1
		int day1=cal.get(Calendar.DATE);
		if(day1<10){
			variablesMap.put("weektwo", mon+"-0"+String.valueOf(day1));
		}else{
			variablesMap.put("weektwo", mon+"-"+String.valueOf(day1));
		}		
		cal.set(Calendar.DAY_OF_MONTH,cal.get(Calendar.DAY_OF_MONTH)+1);//让日期加1
		int day2=cal.get(Calendar.DATE);
		if(day2<10){
			variablesMap.put("weekthree", mon+"-0"+String.valueOf(day2));
		}else{
			variablesMap.put("weekthree", mon+"-"+String.valueOf(day2));
		}		
		cal.set(Calendar.DAY_OF_MONTH,cal.get(Calendar.DAY_OF_MONTH)+1);//让日期加1
		int day3=cal.get(Calendar.DATE);
		if(day3<10){
			variablesMap.put("weekfour", mon+"-0"+String.valueOf(day3));
		}else{
			variablesMap.put("weekfour", mon+"-"+String.valueOf(day3));
		}		
		cal.set(Calendar.DAY_OF_MONTH,cal.get(Calendar.DAY_OF_MONTH)+1);//让日期加1
		int day4=cal.get(Calendar.DATE);
		if(day4<10){
			variablesMap.put("weekfive", mon+"-0"+String.valueOf(day4));
		}else{
			variablesMap.put("weekfive", mon+"-"+String.valueOf(day4));
		}		
		GregorianCalendar gc=new GregorianCalendar(); 
		gc.setTime(date); 
		gc.add(3,+1);
		variablesMap.put("nextstartdate", gc.get(Calendar.YEAR)+"-"+(gc.get(Calendar.MONTH)+1)+"-"+gc.get(Calendar.DATE));
		gc.add(5,+6);
		variablesMap.put("nextenddate", gc.get(Calendar.YEAR)+"-"+(gc.get(Calendar.MONTH)+1)+"-"+gc.get(Calendar.DATE));
		GregorianCalendar gr=new GregorianCalendar(); 
		gr.setTime(date); 
		gr.add(3,+1);
		gr.add(5,+0);
		int months=gc.get(Calendar.MONTH)+1;
		String mm="";
		if(months<10){
			mm=0+String.valueOf(months);
		}else{
			mm=String.valueOf(months);
		}
		if(gr.get(Calendar.DATE)<10){
			variablesMap.put("nextweekone", mm+"-0"+gr.get(Calendar.DATE));			
		}else{
			variablesMap.put("nextweekone", mm+"-"+gr.get(Calendar.DATE));
		}
		gr.add(5,+1);
		if(gr.get(Calendar.DATE)<10){
			variablesMap.put("nextweektwo", mm+"-0"+gr.get(Calendar.DATE));			
		}else{
			variablesMap.put("nextweektwo", mm+"-"+gr.get(Calendar.DATE));	
		}
		gr.add(5,+1);
		if(gr.get(Calendar.DATE)<10){
			variablesMap.put("nextweekthree", mm+"-0"+gr.get(Calendar.DATE));			
		}else{
			variablesMap.put("nextweekthree", mm+"-"+gr.get(Calendar.DATE));	
		}
		gr.add(5,+1);
		if(gr.get(Calendar.DATE)<10){
			variablesMap.put("nextweekfour", mm+"-0"+gr.get(Calendar.DATE));			
		}else{
			variablesMap.put("nextweekfour", mm+"-"+gr.get(Calendar.DATE));			
		}
		gr.add(5,+1);
		if(gr.get(Calendar.DATE)<10){
			variablesMap.put("nextweekfive", mm+"-0"+gr.get(Calendar.DATE));			
		}else{
			variablesMap.put("nextweekfive", mm+"-"+gr.get(Calendar.DATE));
		}
		
		for (Entry entry : variablesMap.entrySet()) {
			request.setAttribute(entry.getKey().toString(), entry.getValue());
		}

		if ("view".equals(opertype)) {
			return "solutions/workflow/forms/other/sum_network_report/sum_sale_week_show.jsp";
		} else{			
			return "solutions/workflow/forms/other/sum_network_report/sum_sale_week_show.jsp";
		}
	}

	
	/**
	 * 超级管理员申请页面展示
	 * 		
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showAdminSaleWeekReport.acl")
	public String showAdminSaleWeekReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil		
				.getRequestParameterMapByAjax(request);
		User user = SecurityUtil.getPrincipal();
		String opertype = model.get("opertype");
		Map<String, String> variablesMap = new HashMap<String, String>();
		variablesMap.put("opertype", opertype);
		String t="";
		if ("add".equals(opertype)) {
			
			Map<String, String> queryMap =new HashMap<String, String>();			
			queryMap.put("weekstyle","草稿");
			queryMap.put("registerid",user.getId());
			queryMap.put("startDate", model.get("startdate"));
			String jsonworkweekone=this.tableService.getJsonArrayData("/eleasing/jsp/other/get_admin_sale_week_report_list01.xml", queryMap).toString();
			JSONArray jsonworkone=new JSONArray(jsonworkweekone);
			for(int i=0;i<jsonworkone.length();i++){
				JSONObject jsonweekone=jsonworkone.getJSONObject(i);
				variablesMap.put("workid", jsonweekone.getString("id"));
				variablesMap.put("registername", jsonweekone.getString("realname"));
				variablesMap.put("registerid", jsonweekone.getString("tuid"));
				variablesMap.put("deptname", jsonweekone.getString("deptname"));
				variablesMap.put("childdeptname", jsonweekone.getString("childdeptname"));
				variablesMap.put("startdate", jsonweekone.getString("startdate"));
				variablesMap.put("enddate", jsonweekone.getString("enddate"));
				variablesMap.put("serialid", jsonweekone.getString("serialid"));
				variablesMap.put("weekstyle", jsonweekone.getString("weekstyle"));
				t= jsonweekone.getString("startdate");
			}
							
		} else {
			String workid = model.get("workid");		
			Map<String,String> queryMainObjectMap = new HashMap<String,String>();
			queryMainObjectMap.put("id", workid);
			String jsonworkweek=this.tableService.getJsonArrayData("/eleasing/jsp/other/get_admin_sale_week_report_list01.xml", queryMainObjectMap).toString();
			JSONArray jsonwork=new JSONArray(jsonworkweek);
			for(int i=0;i<jsonwork.length();i++){
				JSONObject jsonweek=jsonwork.getJSONObject(i);
				variablesMap.put("workid", jsonweek.getString("id"));
				variablesMap.put("registername", jsonweek.getString("realname"));
				variablesMap.put("registerid", jsonweek.getString("tuid"));
				variablesMap.put("deptname", jsonweek.getString("deptname"));
				variablesMap.put("childdeptname", jsonweek.getString("childdeptname"));
				variablesMap.put("startdate", jsonweek.getString("startdate"));
				variablesMap.put("enddate", jsonweek.getString("enddate"));
				variablesMap.put("serialid", jsonweek.getString("serialid"));
				variablesMap.put("weekstyle", jsonweek.getString("weekstyle"));
				t= jsonweek.getString("startdate");
			}
	
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(t); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day=cal.get(Calendar.DATE);
		int month=cal.get(Calendar.MONTH)+1;
		String mon="";
		if(month<10){
			 mon=0+String.valueOf(month);
		}else{
			mon=String.valueOf(month);
		}
		if(day<10){
			variablesMap.put("weekone", mon+"-0"+String.valueOf(day));			
		}else{
			variablesMap.put("weekone", mon+"-"+String.valueOf(day));
		}
		cal.set(Calendar.DAY_OF_MONTH,cal.get(Calendar.DAY_OF_MONTH)+1);//让日期加1
		int day1=cal.get(Calendar.DATE);
		if(day1<10){
			variablesMap.put("weektwo", mon+"-0"+String.valueOf(day1));
		}else{
			variablesMap.put("weektwo", mon+"-"+String.valueOf(day1));
		}		
		cal.set(Calendar.DAY_OF_MONTH,cal.get(Calendar.DAY_OF_MONTH)+1);//让日期加1
		int day2=cal.get(Calendar.DATE);
		if(day2<10){
			variablesMap.put("weekthree", mon+"-0"+String.valueOf(day2));
		}else{
			variablesMap.put("weekthree", mon+"-"+String.valueOf(day2));
		}		
		cal.set(Calendar.DAY_OF_MONTH,cal.get(Calendar.DAY_OF_MONTH)+1);//让日期加1
		int day3=cal.get(Calendar.DATE);
		if(day3<10){
			variablesMap.put("weekfour", mon+"-0"+String.valueOf(day3));
		}else{
			variablesMap.put("weekfour", mon+"-"+String.valueOf(day3));
		}		
		cal.set(Calendar.DAY_OF_MONTH,cal.get(Calendar.DAY_OF_MONTH)+1);//让日期加1
		int day4=cal.get(Calendar.DATE);
		if(day4<10){
			variablesMap.put("weekfive", mon+"-0"+String.valueOf(day4));
		}else{
			variablesMap.put("weekfive", mon+"-"+String.valueOf(day4));
		}		
		GregorianCalendar gc=new GregorianCalendar(); 
		gc.setTime(date); 
		gc.add(3,+1);
		variablesMap.put("nextstartdate", gc.get(Calendar.YEAR)+"-"+(gc.get(Calendar.MONTH)+1)+"-"+gc.get(Calendar.DATE));
		gc.add(5,+6);
		variablesMap.put("nextenddate", gc.get(Calendar.YEAR)+"-"+(gc.get(Calendar.MONTH)+1)+"-"+gc.get(Calendar.DATE));
		GregorianCalendar gr=new GregorianCalendar(); 
		gr.setTime(date); 
		gr.add(3,+1);
		gr.add(5,+0);
		int months=gc.get(Calendar.MONTH)+1;
		String mm="";
		if(months<10){
			mm=0+String.valueOf(months);
		}else{
			mm=String.valueOf(months);
		}
		if(gr.get(Calendar.DATE)<10){
			variablesMap.put("nextweekone", mm+"-0"+gr.get(Calendar.DATE));			
		}else{
			variablesMap.put("nextweekone", mm+"-"+gr.get(Calendar.DATE));
		}
		gr.add(5,+1);
		if(gr.get(Calendar.DATE)<10){
			variablesMap.put("nextweektwo", mm+"-0"+gr.get(Calendar.DATE));			
		}else{
			variablesMap.put("nextweektwo", mm+"-"+gr.get(Calendar.DATE));	
		}
		gr.add(5,+1);
		if(gr.get(Calendar.DATE)<10){
			variablesMap.put("nextweekthree", mm+"-0"+gr.get(Calendar.DATE));			
		}else{
			variablesMap.put("nextweekthree", mm+"-"+gr.get(Calendar.DATE));	
		}
		gr.add(5,+1);
		if(gr.get(Calendar.DATE)<10){
			variablesMap.put("nextweekfour", mm+"-0"+gr.get(Calendar.DATE));			
		}else{
			variablesMap.put("nextweekfour", mm+"-"+gr.get(Calendar.DATE));			
		}
		gr.add(5,+1);
		if(gr.get(Calendar.DATE)<10){
			variablesMap.put("nextweekfive", mm+"-0"+gr.get(Calendar.DATE));			
		}else{
			variablesMap.put("nextweekfive", mm+"-"+gr.get(Calendar.DATE));
		}
		
		for (Entry entry : variablesMap.entrySet()) {
			request.setAttribute(entry.getKey().toString(), entry.getValue());
		}

		if ("view".equals(opertype)) {
			return "solutions/workflow/forms/other/admin_network_report/admin_sale_week_show.jsp";
		} else{			
			return "solutions/workflow/forms/other/admin_network_report/admin_sale_week_show.jsp";
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
	@RequestMapping(value = "/deleteSaleWeekReport.acl")
	@ResponseBody
	public String deleteSaleWeekReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil
				.getRequestParameterMapByAjax(request);
		
		String returnstr = "";
		try {		
			this.saleWeekReportService.deleteSaleWeekReport(model);
			returnstr = "{flag:\"true\",msg:\"登记成功！\",opertype:\"add\"}";
		} catch (Exception e) {
			e.printStackTrace();
			returnstr = "{flag:\"false\",msg:\"" + e.getMessage()
					+ "\",opertype:\"add\"}";
		}

		return returnstr;
	}
	
	/**
	 * 新增申请
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addSaleWeekReportlication.acl")
	@ResponseBody
	public String addWorkWeekReportlication(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil
				.getRequestParameterMapByAjax(request);
		System.out.println("model="+model);
		String returnstr = "";
		try {		
			User user = SecurityUtil.getPrincipal();
			Map<String,Object> queryMainObjectMap = new HashMap<String,Object>();
			queryMainObjectMap.put("registerid", user);
			queryMainObjectMap.put("startDate", model.get("startdate"));	
			List<SaleWeekReport> work=this.tableService.findEntityByProperties(SaleWeekReport.class, queryMainObjectMap);
			Map<String,Object> queryMap = new HashMap<String,Object>();
			queryMap.put("registerid", user);
			queryMap.put("weekstyle", "草稿");			
			List<SaleWeekReport> worklist=this.tableService.findEntityByProperties(SaleWeekReport.class, queryMap);			
			if(work.size()>0){
				returnstr = "{flag:\"tru\",msg:\"周报已存在，不允许重复录入！\",opertype:\"add\"}";
			}else if(worklist.size()>0){
				returnstr = "{flag:\"tru\",msg:\"你草稿还未完成，请先提交完成！\",opertype:\"add\"}";
			}else{				
				this.saleWeekReportService.addSaleWeekReportApplication(model);				
				returnstr = "{flag:\"true\",msg:\"登记成功！\",opertype:\"add\"}";
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnstr = "{flag:\"false\",msg:\"" + e.getMessage()
					+ "\",opertype:\"add\"}";
		}

		return returnstr;
	}

	/**
	 *完成草稿  
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateSaleWeekReport.acl")
	@ResponseBody
	public String updateSaleWeekReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil
				.getRequestParameterMapByAjax(request);
		String returnstr = "";
		try {	
			this.saleWeekReportService.updateSaleWeekReport(model);
			returnstr = "{flag:\"true\",msg:\"修改成功！\",opertype:\"add\"}";
		} catch (Exception e) {
			e.printStackTrace();
			returnstr = "{flag:\"false\",msg:\"" + e.getMessage()
					+ "\",opertype:\"add\"}";
		}
		return returnstr;
	}
	

	
	/**
	 * 下周删除详情
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	@RequestMapping(value = "/delNextWorkWeekReportDetaillication.acl")
	@ResponseBody
	public String delNextWorkWeekReportDetaillication(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil
				.getRequestParameterMapByAjax(request);
		System.out.println("model="+model);
		String returnstr = "";
		try {		
			this.workWeekReportService.deleteNextWorkWeekReportDetailApplication(model);
			returnstr = "{flag:\"true\",msg:\"删除成功！\",opertype:\"add\"}";
		} catch (Exception e) {
			e.printStackTrace();
			returnstr = "{flag:\"false\",msg:\"" + e.getMessage()
					+ "\",opertype:\"add\"}";
		}

		return returnstr;
	}
*/
	
}
