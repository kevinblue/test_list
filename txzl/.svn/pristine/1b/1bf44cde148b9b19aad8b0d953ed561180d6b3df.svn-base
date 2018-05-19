package com.tenwa.leasing.controller.workweekreport;

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
import com.tenwa.leasing.entity.cust.WorkWeekReport;
import com.tenwa.leasing.service.workweekreport.WorkWeekReportService;

/**
 * 周报信息
 * 
 * @Title: AssetsNetMonitorController.java
 * @package: com.tenwa.leasing.controller.AssetsNetMonitorController
 * @author: ganwei
 * @date 2014年11月20日 上午9:27:24
 * @version V5.1
 */
@Controller(value = "workWeekReportController")
@RequestMapping(value = "/**/acl")
public class WorkWeekReportController {

	@Resource(name = "tableService")
	private TableService tableService;

	@Resource(name = "workWeekReportService")
	private WorkWeekReportService workWeekReportService;
	
	public synchronized String createCalNum() throws Exception {
		try{
			
			String year=DateUtil.getSystemDate().substring(0, 4);
			String month=DateUtil.getSystemDate().substring(5, 7);
			String day=DateUtil.getSystemDate().substring(8, 10);
			List<Map<String,Object>> numberlist=this.tableService.queryListBySql("select * from T_SERIAL_NUMBER where type_='周报表流水号' ");
			if(numberlist!=null&&numberlist.size()>0){
				if(new BigDecimal(year).compareTo(new BigDecimal(numberlist.get(0).get("year_").toString()))!=0||new BigDecimal(month).compareTo(new BigDecimal(numberlist.get(0).get("month_").toString()))!=0){
					this.tableService.updateBySql("update T_SERIAL_NUMBER set year_=?, month_=?, order_number_=? where  type_='周报表流水号' ", Integer.parseInt(year),Integer.parseInt(month),000001);
					return year+month+day+"000001";
				}else{
					BigDecimal number=new BigDecimal(numberlist.get(0).get("order_number_").toString()).add(BigDecimal.ONE) ;
					String format=new DecimalFormat("000000").format(number);
					this.tableService.updateBySql("update T_SERIAL_NUMBER set  order_number_=? where  type_='周报表流水号' ", Integer.parseInt(format));
					return year+month+day+format;
				}
			}else{
				this.tableService.updateBySql("insert into T_SERIAL_NUMBER ( id_,type_,year_,month_,order_number_) values(sys_guid(),'周报表流水号',?,?,?)", Integer.parseInt(year),Integer.parseInt(month),000001);
				return year+month+day+"000001";
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 申请页面展示
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showWorkWeekReport.acl")
	public String showWorkWeekReport(HttpServletRequest request,
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
			String jsonworkweekone=this.tableService.getJsonArrayData("/eleasing/jsp/other/get_work_week_report_list01.xml", queryMap).toString();
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
		/*	List<WorkWeekReport> worklist=this.tableService.findEntityByProperties(WorkWeekReport.class, queryMap);
			variablesMap.put("registername", user.getRealname());
			variablesMap.put("registerid", user.getId());
			variablesMap.put("workid", worklist.get(0).getId());
			variablesMap.put("serialid", worklist.get(0).getSerialid());
			variablesMap.put("startdate", worklist.get(0).getStartDate());
			variablesMap.put("enddate", worklist.get(0).getEndDate());
			variablesMap.put("weekstyle", "草稿");*/
							
		} else {
			String workid = model.get("workid");		
			Map<String,String> queryMainObjectMap = new HashMap<String,String>();
			queryMainObjectMap.put("id", workid);
			String jsonworkweek=this.tableService.getJsonArrayData("/eleasing/jsp/other/get_work_week_report_list01.xml", queryMainObjectMap).toString();
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
		cal.set(Calendar.DAY_OF_MONTH,cal.get(Calendar.DAY_OF_MONTH)+1);//让日期加1
		int day5=cal.get(Calendar.DATE);
		if(day5<10){
			variablesMap.put("weeksix", mon+"-0"+String.valueOf(day5));
		}else{
			variablesMap.put("weeksix", mon+"-"+String.valueOf(day5));
		}
		cal.set(Calendar.DAY_OF_MONTH,cal.get(Calendar.DAY_OF_MONTH)+1);//让日期加1
		int day6=cal.get(Calendar.DATE);
		if(day6<10){
			variablesMap.put("weekseven", mon+"-0"+String.valueOf(day6));
		}else{
			variablesMap.put("weekseven", mon+"-"+String.valueOf(day6));
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
		gr.add(5,+1);
		if(gr.get(Calendar.DATE)<10){
			variablesMap.put("nextweeksix", mm+"-0"+gr.get(Calendar.DATE));			
		}else{
			variablesMap.put("nextweeksix", mm+"-"+gr.get(Calendar.DATE));
		}
		gr.add(5,+1);
		if(gr.get(Calendar.DATE)<10){
			variablesMap.put("nextweekseven", mm+"-0"+gr.get(Calendar.DATE));			
		}else{
			variablesMap.put("nextweekseven", mm+"-"+gr.get(Calendar.DATE));
		}
		
		
		for (Entry entry : variablesMap.entrySet()) {
			request.setAttribute(entry.getKey().toString(), entry.getValue());
		}

		if ("view".equals(opertype)) {
			return "solutions/workflow/forms/other/assets_network_monitor/work_week_report.jsp";
		} else{			
			return "solutions/workflow/forms/other/assets_network_monitor/work_week_report.jsp";
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
	@RequestMapping(value = "/deleteWorkWeekReport.acl")
	@ResponseBody
	public String deleteWorkWeekReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil
				.getRequestParameterMapByAjax(request);
		
		String returnstr = "";
		try {		
			this.workWeekReportService.deleteWorkWeekReport(model);
			returnstr = "{flag:\"true\",msg:\"登记成功！\",opertype:\"add\"}";
		} catch (Exception e) {
			e.printStackTrace();
			returnstr = "{flag:\"false\",msg:\"" + e.getMessage()
					+ "\",opertype:\"add\"}";
		}

		return returnstr;
	}
	/**
	 * 修改申请
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateWorkWeekReport.acl")
	@ResponseBody
	public String updateWorkWeekReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil
				.getRequestParameterMapByAjax(request);
		
		String returnstr = "";
		try {		
			this.workWeekReportService.updateWorkWeekReport(model);
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
	@RequestMapping(value = "/addWorkWeekReportlication.acl")
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
			List<WorkWeekReport> work=this.tableService.findEntityByProperties(WorkWeekReport.class, queryMainObjectMap);
			Map<String,Object> queryMap = new HashMap<String,Object>();
			queryMap.put("registerid", user);
			queryMap.put("weekstyle", "草稿");
			List<WorkWeekReport> worklist=this.tableService.findEntityByProperties(WorkWeekReport.class, queryMap);
			if(work.size()>0){
				returnstr = "{flag:\"tru\",msg:\"周报已存在，不允许重复录入！\",opertype:\"add\"}";
			}else if(worklist.size()>0){
				returnstr = "{flag:\"tru\",msg:\"你草稿稿还未完成，请先提交完成！\",opertype:\"add\"}";
			}else{				
				this.workWeekReportService.addWorkWeekReportApplication(model);				
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
	 * 修改申请
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updWorkWeekReportlication.acl")
	@ResponseBody
	public String updWorkWeekReportlication(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil
				.getRequestParameterMapByAjax(request);
		String returnstr = "";
		try {		
			this.workWeekReportService.updateWorkWeekReportApplication(model);
			returnstr = "{flag:\"true\",msg:\"修改成功！\",opertype:\"add\"}";
		} catch (Exception e) {
			e.printStackTrace();
			returnstr = "{flag:\"false\",msg:\"" + e.getMessage()
					+ "\",opertype:\"add\"}";
		}

		return returnstr;
	}
	
	/**
	 * 本周删除详情
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delWorkWeekReportDetaillication.acl")
	@ResponseBody
	public String delWorkWeekReportDetaillication(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil
				.getRequestParameterMapByAjax(request);
		System.out.println("model="+model);
		String returnstr = "";
		try {		
			this.workWeekReportService.deleteWorkWeekReportDetailApplication(model);
			returnstr = "{flag:\"true\",msg:\"删除成功！\",opertype:\"add\"}";
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
	 */
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

	
}
