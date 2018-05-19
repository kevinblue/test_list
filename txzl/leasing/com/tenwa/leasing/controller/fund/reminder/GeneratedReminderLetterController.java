package com.tenwa.leasing.controller.fund.reminder;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.DateUtil;
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
	
	@Resource(name = "tableService")
	private TableService tableService;
	
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
	@RequestMapping(value = "/getReminderLetterRunningWater.acl")
	@ResponseBody
	public String getPenaltyRunningWater(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
             String number="CSH—"+createNum();
			return number;
		}catch (Exception e) {
			return "调用失败!["+e.getMessage()+"]";
		}
	}
	public synchronized String createNum() throws Exception {
		try{
			String year=DateUtil.getSystemDate().substring(0, 4);
			String month=DateUtil.getSystemDate().substring(5, 7);
			String day=DateUtil.getSystemDate().substring(8, 10);
			List<Map<String,Object>> numberlist=this.tableService.queryListBySql("select * from T_SERIAL_NUMBER where type_='催款通知书编号' ");
			if(numberlist!=null&&numberlist.size()>0){
				if(new BigDecimal(year).compareTo(new BigDecimal(numberlist.get(0).get("year_").toString()))!=0||new BigDecimal(month).compareTo(new BigDecimal(numberlist.get(0).get("month_").toString()))!=0){
					this.tableService.updateBySql("update T_SERIAL_NUMBER set year_=?, month_=?, order_number_=? where type_='催款通知书编号' ", Integer.parseInt(year),Integer.parseInt(month),001);
					return year+"001";
				}else{
					BigDecimal number=new BigDecimal(numberlist.get(0).get("order_number_").toString()).add(BigDecimal.ONE) ;
					String format=new DecimalFormat("000").format(number);
					this.tableService.updateBySql("update T_SERIAL_NUMBER set order_number_=? where type_='催款通知书编号' ", Integer.parseInt(format));
					return year+format;
				}
			}else{
				this.tableService.updateBySql("insert into T_SERIAL_NUMBER ( id_,type_,year_,month_,order_number_) values(sys_guid(),'催款通知书编号',?,?,?)", Integer.parseInt(year),Integer.parseInt(month),001);
				return year+"001";
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	
}
