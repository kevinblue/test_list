
 /**
 * 项目名称：    系统名称
 * 包名：              com.business.util
 * 文件名：         WorkflowUtil.java
 * 版本信息：    1.0.0
 * 创建日期：     2012-12-26-上午10:43:02
 * Copyright：2012XX公司-版权所有
 **/

package com.tenwa.leasing.utils;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.util.Assert;

import com.tenwa.business.entity.User;
import com.tenwa.business.entity.WorkFlowFlag;
import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.exception.BusinessException;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.proj.ProjInfo;
import com.tenwa.leasing.entity.util.TSerialDiscardNumber;
import com.tenwa.leasing.entity.util.TSerialNumber;




 /**
 * 类名称：     WorkflowUtil
 * 类描述：     
 * 创建人：     tracywindy
 * 修改人：     tracywindy
 * 修改时间：2012-12-26 上午10:43:02
 * 修改备注：
 * @version 1.0.0
 **/

public class WorkflowUtil {

	/**
	 * @method main(这里用一句话描述这个方法的作用)
	 * (这里描述这个方法适用条件 – 可选)
	 * @param args
	 * @returnType void
	 * @exception  
	 * @since      1.0.0
	 **/

	public static String getCustInfoSerialNumber(Map<String,String> paramMap,HibernateTemplate hibernateTemplate,JdbcTemplate jdbcTemplate) throws Exception{
		return WorkflowUtil.getSerialNumber("0100{maxOrderNumber}",7,null, "客户流水号", null, hibernateTemplate, jdbcTemplate);
	}
	public static String getEbankNoInfoSerialNumber(Map<String,String> paramMap,HibernateTemplate hibernateTemplate,JdbcTemplate jdbcTemplate) throws Exception{
		return WorkflowUtil.getSerialNumber("E-{year}{month}{maxOrderNumber}",3,null, "网银流水号", null, hibernateTemplate, jdbcTemplate);
	}
	public static String getFileTemplateNoInfoSerialNumber(Map<String,String> paramMap,HibernateTemplate hibernateTemplate,JdbcTemplate jdbcTemplate) throws Exception{
		return WorkflowUtil.getSerialNumber("F-{year}{month}{maxOrderNumber}",3,null, "文件模板流水号", null, hibernateTemplate, jdbcTemplate);
	}
	public static String getDealerInfoSerialNumber(Map<String,String> paramMap,HibernateTemplate hibernateTemplate,JdbcTemplate jdbcTemplate) throws Exception{
		return WorkflowUtil.getSerialNumber("01{maxOrderNumber}",6,null, "经销商流水号", null, hibernateTemplate, jdbcTemplate);
	}
	public static String getProjInfoSerialNumber(Map<String,String> paramMap,HibernateTemplate hibernateTemplate,JdbcTemplate jdbcTemplate) throws Exception{
		return WorkflowUtil.getSerialNumber("P{year}{maxOrderNumber}",6,null, "项目立项流水号", null, hibernateTemplate, jdbcTemplate);
	}
	
	public static String getReportLazySerialNumber(String name,Integer year ,HibernateTemplate hibernateTemplate,JdbcTemplate jdbcTemplate) throws Exception{
		return getMaxOrderNumber(name,year,hibernateTemplate,jdbcTemplate)+"";
	}
	//收据流水号
	public static String getInvoiceSerialNumber(Map<String,String> paramMap,HibernateTemplate hibernateTemplate,JdbcTemplate jdbcTemplate)  throws Exception{
		String currentDateTime = DateUtil.getSystemDateTime();
		String currentYear = currentDateTime.substring(0,4);
		String currentMonth =  currentDateTime.substring(5,7);
		int year = Integer.parseInt(currentYear);
		int month = Integer.parseInt(currentMonth);
		return WorkflowUtil.getSerialNumberByMonth("{year}{month}{maxOrderNumber}",6,null, "收据流水号", year,month, hibernateTemplate, jdbcTemplate);
	}
	
	/**
	 * 获得租金收据号
	 * @param paramMap
	 * @param hibernateTemplate
	 * @param jdbcTemplate
	 * @return
	 * @throws Exception
	 */
	public static String getRentReceiptSerialNumber(Map<String,String> paramMap,HibernateTemplate hibernateTemplate,JdbcTemplate jdbcTemplate) throws Exception{
		String currentDateTime = DateUtil.getSystemDateTime();
		String currentYear = currentDateTime.substring(0,4);
		String currentMonth =  currentDateTime.substring(5,7);
		String currentDay =  currentDateTime.substring(8,10);
		int year = Integer.parseInt(currentYear);
		int month = Integer.parseInt(currentMonth);
		int day = Integer.parseInt(currentDay);
		return WorkflowUtil.getSerialNumber("{year}{month}{day}{maxOrderNumber}", 6, null , "租金收据号", year, hibernateTemplate, jdbcTemplate);
	}
	/**
	 * 获得合同的contract_id
	 * @param paramMap
	 * @param hibernateTemplate
	 * @param jdbcTemplate
	 * @return
	 * @throws Exception
	 */
	public static String getContractInfoSerialNumber(Map<String,String> paramMap,HibernateTemplate hibernateTemplate,JdbcTemplate jdbcTemplate) throws Exception{
		String currentDateTime = DateUtil.getSystemDateTime();
		String currentYear = currentDateTime.substring(0,4);
		String year=currentDateTime.substring(2,4);
		String projtype = paramMap.get("projtype");
		String contractid="TYJZYW";
		if(projtype.equals("proj_type1"))
		{
			contractid+="ZZ";
		}else{
			contractid+="HZ";
		}
		return WorkflowUtil.getSerialNumber(contractid+"ZL"+year+"{month}{maxOrderNumber}",6,null, "合同流水号", Integer.parseInt(currentYear),hibernateTemplate, jdbcTemplate);
	}
	public static String getAuditingContractId(Map<String,String> paramMap,HibernateTemplate hibernateTemplate,JdbcTemplate jdbcTemplate) throws Exception{
		String currentDateTime = DateUtil.getSystemDateTime();
		String currentYear = currentDateTime.substring(0,4);
		int  year = Integer.parseInt(currentDateTime.substring(2,4));
		String currentMonth =  currentDateTime.substring(5,7);
		int month = Integer.parseInt(currentMonth);
		String lease_form = paramMap.get("leasForm");
		String contractid = "TYJZ_YW_ZL";
		if(lease_form.equals("lease_form1")) 
		{	
			contractid+="(Z)_";
		}else if(lease_form.equals("lease_form2")){		
			contractid+="(H)_";
		}else if(lease_form.equals("lease_form3")){
			contractid+="(C)_";
		}else if(lease_form.equals("lease_form4")){
			contractid+="(J)_";
		}else if(lease_form.equals("lease_form6")){
			contractid+="(W)_";
		}else{
			contractid+="(Q)_";
		}
	 return WorkflowUtil.getSerialNumberByMonth(contractid+"{year}{month}"+"_"+"{maxOrderNumber}",2,null, "合同号", year,month,hibernateTemplate, jdbcTemplate);
	
	}
	
	/**
	 * 获得合同的contract_number
	 * @param paramMap
	 * @param hibernateTemplate
	 * @param jdbcTemplate
	 * @return
	 * @throws Exception
	 */
	public static String getContractInfoSerialNumberForBussiness(Map<String,String> paramMap,HibernateTemplate hibernateTemplate,JdbcTemplate jdbcTemplate) throws Exception{
		String currentDateTime = DateUtil.getSystemDateTime();
		String currentYear = currentDateTime.substring(0,4);
		return WorkflowUtil.getSerialNumberFirstDiscard("德银({year})租字第{maxOrderNumber}号",5,null, "业务合同号", Integer.parseInt(currentYear), hibernateTemplate, jdbcTemplate);
	}
	public static String getContractInfoSerialNumberForBussiness2(Map<String,String> paramMap,HibernateTemplate hibernateTemplate,JdbcTemplate jdbcTemplate) throws Exception{
		String currentDateTime = DateUtil.getSystemDateTime();
		String currentYear = currentDateTime.substring(0,4);
		return WorkflowUtil.getSerialNumberFirstDiscard("德银({year})回租字第{maxOrderNumber}号",5,null, "业务合同号", Integer.parseInt(currentYear), hibernateTemplate, jdbcTemplate);
	}
	/**
	 * 废弃一个合同的contract_number
	 * @param contractNumber
	 * @param hibernateTemplate
	 * @param jdbcTemplate
	 */
	public static void addDiscardContractInfoSerialNumberForBussiness(String contractNumber,String contract_id, HibernateTemplate hibernateTemplate,JdbcTemplate jdbcTemplate){
		//此处截取字符串是根据getContractInfoSerialNumberForBussiness的生成规则写死的.
		String year=contractNumber.substring(3, 7);
		String number=contractNumber.substring(11,16);
		TSerialDiscardNumber discardNumber=new TSerialDiscardNumber();
		discardNumber.setType("业务合同号");
		discardNumber.setTypeId(contract_id);
		discardNumber.setYear(Integer.parseInt(year));
		discardNumber.setDiscardNumber(Integer.parseInt(number));
		hibernateTemplate.save(discardNumber);
	}
	
	/**
	 * 获得保险单编号
	 * @param paramMap
	 * @param hibernateTemplate
	 * @param jdbcTemplate
	 * @return
	 * @throws Exception
	 */
	public static String getInsurInfoSerialNumber(Map<String,String> paramMap,HibernateTemplate hibernateTemplate,JdbcTemplate jdbcTemplate) throws Exception{
		return WorkflowUtil.getSerialNumber("II{year}{month}{maxOrderNumber}",6,null, "保险单编号", null, hibernateTemplate, jdbcTemplate);
	}
	/**
	 * 获得资金发票计划开票流水号
	 * @param paramMap
	 * @param hibernateTemplate
	 * @param jdbcTemplate
	 * @return
	 * @throws Exception
	 */
	public static String getFundInvoiceSerialNumber(Map<String,String> paramMap,HibernateTemplate hibernateTemplate,JdbcTemplate jdbcTemplate) throws Exception{
		//return WorkflowUtil.getSerialNumber("{month}{day}{maxOrderNumber}",6,null, "资金发票计划开票流水号", null, hibernateTemplate, jdbcTemplate);
		String currentDateTime = DateUtil.getSystemDateTime();
		String currentYear = currentDateTime.substring(0,4);
		String currentMonth =  currentDateTime.substring(5,7);
		int year = Integer.parseInt(currentYear);
		int month = Integer.parseInt(currentMonth);
		return WorkflowUtil.getSerialNumberByMonth("{year}{month}{maxOrderNumber}",6,null, "资金发票计划开票流水号", year,month, hibernateTemplate, jdbcTemplate);
	}
	/**
	 * 获得租金计划应开票流水号
	 * @param paramMap
	 * @param hibernateTemplate
	 * @param jdbcTemplate
	 * @return
	 * @throws Exception
	 */
	public static String getRentInvoiceSerialNumber(Map<String,String> paramMap,HibernateTemplate hibernateTemplate,JdbcTemplate jdbcTemplate) throws Exception{
		//return WorkflowUtil.getSerialNumberByMonth("{month}{day}{maxOrderNumber}",7,null, "租金计划应开票流水号", null, hibernateTemplate, jdbcTemplate);
		String currentDateTime = DateUtil.getSystemDateTime();
		String currentYear = currentDateTime.substring(0,4);
		String currentMonth =  currentDateTime.substring(5,7);
		int year = Integer.parseInt(currentYear);
		int month = Integer.parseInt(currentMonth);
		return WorkflowUtil.getSerialNumberByMonth("{year}{month}{maxOrderNumber}",6,null, "租金计划应开票流水号", year,month, hibernateTemplate, jdbcTemplate);
	}
	/***
	 * serialNumberPattern匹配值
	 * {year}->年份，如果参数year为空则取当前年
	 * {month}->当前月
	 * {day}->当前日
	 * {hour}->时
	 * {minute}->分
	 * {second}->秒
	 * {maxOrderNumber}->当前最大序号
	 * 
	 * **/
	protected static synchronized String getSerialNumber(String serialNumberPattern,int numberCount,Map<String,String> model,String type,Integer queryYear,HibernateTemplate hibernateTemplate,JdbcTemplate jdbcTemplate) throws Exception
	{               
		String currentDateTime = DateUtil.getSystemDateTime();
		//2016 0718 11：54：00
		String currentYear = currentDateTime.substring(0,4);
		String month =  currentDateTime.substring(5,7);
		String day =    currentDateTime.substring(8,10);
		String hour =   currentDateTime.substring(11,13);
		String minute =  currentDateTime.substring(14,16);
		String second =  currentDateTime.substring(17,19);
		Integer year = Integer.parseInt(currentYear);
		if(null != queryYear){
			year = queryYear;
		} 
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("year", year+"");
		paramMap.put("month", month+"");
		paramMap.put("day", day+"");
		paramMap.put("hour", hour+"");
		paramMap.put("minute", minute+"");
		paramMap.put("second", second+"");
		
		int maxOrderNumber = getMaxOrderNumber(type, queryYear, hibernateTemplate, jdbcTemplate);
		StringBuffer numberFormat = new StringBuffer();
		for(int i=0;i<numberCount;i++)
		{
			numberFormat.append("0");
		}
		
		DecimalFormat df = new DecimalFormat(numberFormat.toString());
		String serialNumberFormat = df.format(maxOrderNumber);
		paramMap.put("maxOrderNumber", serialNumberFormat);
		if(null != model){
			paramMap.putAll(model);
		}
		
		String serialNumberString = QueryUtil.getQueryString(paramMap, serialNumberPattern);
		
		//throw new BusinessException("1");
		return serialNumberString;
	}
	/***
	 * serialNumberPattern匹配值
	 * {year}->年份，如果参数year为空则取当前年
	 * {month}->当前月
	 * {day}->当前日
	 * {hour}->时
	 * {minute}->分
	 * {second}->秒
	 * {maxOrderNumber}->当前最大序号
	 * 
	 * **/
	protected static synchronized String getSerialNumberByMonth(String serialNumberPattern,int numberCount,Map<String,String> model,String type,Integer queryYear,Integer queryMonth,HibernateTemplate hibernateTemplate,JdbcTemplate jdbcTemplate) throws Exception
	{
		String currentDateTime = DateUtil.getSystemDateTime();
		String currentYear = currentDateTime.substring(0,4);
		String monthStr =  currentDateTime.substring(5,7);
		String day =    currentDateTime.substring(8,10);
		String hour =  currentDateTime.substring(11,13);
		String minute =  currentDateTime.substring(14,16);
		String second =  currentDateTime.substring(17,19);
		int year  = Integer.parseInt(currentYear);
		int month = Integer.parseInt(monthStr);
		if(null != queryYear){
			year = queryYear;
		}
		if(null != queryMonth){
			month = queryMonth ;
		}
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("year", year+"");
		paramMap.put("month", monthStr+"");
		paramMap.put("day", day+"");
		paramMap.put("hour", hour+"");
		paramMap.put("minute", minute+"");
		paramMap.put("second", second+"");
		
		int maxOrderNumber = getMaxOrderNumberByMonth(type, year,month, hibernateTemplate, jdbcTemplate);
		StringBuffer numberFormat = new StringBuffer();
		for(int i=0;i<numberCount;i++)
		{
			numberFormat.append("0");
		}
		
		DecimalFormat df = new DecimalFormat(numberFormat.toString());
		String serialNumberFormat = df.format(maxOrderNumber);
		paramMap.put("maxOrderNumber", serialNumberFormat);
		if(null != model){
			paramMap.putAll(model);
		}
		
		String serialNumberString = QueryUtil.getQueryString(paramMap, serialNumberPattern);
		
		return serialNumberString;
	}
	//考虑并发情况必须采用同步机制
	@SuppressWarnings("unchecked")
	protected static synchronized int getMaxOrderNumber(String type,Integer queryYear,HibernateTemplate hibernateTemplate,JdbcTemplate jdbcTemplate) throws Exception
	{
		Assert.notNull(type);
		int currentYear =  Calendar.getInstance().get(Calendar.YEAR);
		DetachedCriteria criteria = DetachedCriteria.forClass(TSerialNumber.class);
		if(!StringUtils.isBlank(type))
		{
			criteria.add(Restrictions.eq("type",type));
		}
		if(null != queryYear)
		{
			criteria.add(Restrictions.eq("year",queryYear));
		}
		TSerialNumber  serialNumber = null;
		List maxOrderNumberList = hibernateTemplate.findByCriteria(criteria);
		if(maxOrderNumberList.size()>0)
		{
			serialNumber = (TSerialNumber)maxOrderNumberList.get(0);
		}
		else
		{
			serialNumber = new TSerialNumber();
			if(null != queryYear)
			{
				currentYear = queryYear;
			}
			serialNumber.setYear(currentYear);
			serialNumber.setType(type);
			serialNumber.setOrderNumber(0);
		}
		int maxOrderNumber = serialNumber.getOrderNumber() + 1;
		serialNumber.setOrderNumber(maxOrderNumber);
		hibernateTemplate.saveOrUpdate(serialNumber);
		return maxOrderNumber;
	}
	//考虑并发情况必须采用同步机制
	@SuppressWarnings("unchecked")
	protected static synchronized int getMaxOrderNumberByMonth(String type,Integer queryYear,Integer queryMonth,HibernateTemplate hibernateTemplate,JdbcTemplate jdbcTemplate) throws Exception
	{
		Assert.notNull(type);
		int currentYear =  Calendar.getInstance().get(Calendar.YEAR);
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
		DetachedCriteria criteria = DetachedCriteria.forClass(TSerialNumber.class);
		if(!StringUtils.isBlank(type))
		{
			criteria.add(Restrictions.eq("type",type));
		}
		if((null != queryYear)||(null != queryMonth))
		{
			criteria.add(Restrictions.eq("year",queryYear));
			criteria.add(Restrictions.eq("month",queryMonth));
		}
		TSerialNumber  serialNumber = null;
		List maxOrderNumberList = hibernateTemplate.findByCriteria(criteria);
		if(maxOrderNumberList.size()>0)
		{
			serialNumber = (TSerialNumber)maxOrderNumberList.get(0);
		}
		else
		{
			serialNumber = new TSerialNumber();
			if(null != queryYear)
			{
				currentYear = queryYear;
			}
			if(null != queryMonth){
				currentMonth = queryMonth;
			}
			serialNumber.setYear(currentYear);
			serialNumber.setMonth(currentMonth);
			serialNumber.setType(type);
			serialNumber.setOrderNumber(0);
		}
		int maxOrderNumber = serialNumber.getOrderNumber() + 1;
		serialNumber.setOrderNumber(maxOrderNumber);
		hibernateTemplate.saveOrUpdate(serialNumber);
		return maxOrderNumber;
	}
	/**
	 * 优先从号码废弃池中取号,如果号码废弃池中没有待取号吗,则根据序列号生成
	 * @param serialNumberPattern
	 * @param numberCount
	 * @param model
	 * @param type
	 * @param queryYear
	 * @param hibernateTemplate
	 * @param jdbcTemplate
	 * @return
	 * @throws Exception
	 */
	protected static synchronized String getSerialNumberFirstDiscard(String serialNumberPattern,int numberCount,Map<String,String> model,String type,Integer queryYear,HibernateTemplate hibernateTemplate,JdbcTemplate jdbcTemplate) throws Exception
	{
		String currentDateTime = DateUtil.getSystemDateTime();
		String currentYear = currentDateTime.substring(0,4);
		String month =  currentDateTime.substring(5,7);
		String day =    currentDateTime.substring(8,10);
		String hour =  currentDateTime.substring(11,13);
		String minute =  currentDateTime.substring(14,16);
		String second =  currentDateTime.substring(17,19);
		
		int year = Integer.parseInt(currentYear);
		if(null != queryYear){
			year = queryYear;
		}
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("year", year+"");
		paramMap.put("month", month+"");
		paramMap.put("day", day+"");
		paramMap.put("hour", hour+"");
		paramMap.put("minute", minute+"");
		paramMap.put("second", second+"");
		
		int maxOrderNumber =0;
		//从废弃池查找可用号码
		maxOrderNumber=getMinDiscardNumber(type, queryYear, hibernateTemplate, jdbcTemplate);
		if(maxOrderNumber<0){//废弃池中没有数据
			maxOrderNumber=getMaxOrderNumber(type, year, hibernateTemplate, jdbcTemplate);
		}
		StringBuffer numberFormat = new StringBuffer();
		for(int i=0;i<numberCount;i++)
		{
			numberFormat.append("0");
		}
		DecimalFormat df = new DecimalFormat(numberFormat.toString());
		String serialNumberFormat = df.format(maxOrderNumber);
		paramMap.put("maxOrderNumber", serialNumberFormat);
		if(null != model){
			paramMap.putAll(model);
		}
		
		String serialNumberString = QueryUtil.getQueryString(paramMap, serialNumberPattern);
		
		return serialNumberString;
	}
	@SuppressWarnings("unchecked")
	protected static synchronized int getMinDiscardNumber(String type,Integer queryYear,HibernateTemplate hibernateTemplate,JdbcTemplate jdbcTemplate) throws Exception
	{
		Assert.notNull(type);
		DetachedCriteria criteria = DetachedCriteria.forClass(TSerialDiscardNumber.class);
		if(!StringUtils.isBlank(type))
		{
			criteria.add(Restrictions.eq("type",type));
		}
		if(null != queryYear)
		{
			criteria.add(Restrictions.eq("year",queryYear));
		}
		TSerialDiscardNumber  discardNumber = null;
		List<TSerialDiscardNumber> minOrderNumberList = hibernateTemplate.findByCriteria(criteria);
		if(minOrderNumberList.size()>0)
		{
			//Collections.sort(minOrderNumberList);
			discardNumber = minOrderNumberList.get(0);
			int minOrderNumber = discardNumber.getDiscardNumber();
			hibernateTemplate.delete(discardNumber);
			return minOrderNumber;
		}
		return -1;
	}

	public static void  deleteWorkFlowConflict(BaseService tableService,Map<String, String> variablesMap) throws Exception{
		String workFlowFlags=variablesMap.get("workFlowFlag");
		/** 流程冲突第三步-结束 */
		if (StringUtils.isNotEmpty(workFlowFlags)) {
			/** 流程冲突第二步-结束 */
			List<WorkFlowFlag> workFlags = tableService.findEntityByIDArray(WorkFlowFlag.class, workFlowFlags.split(","));
			if(null!=workFlags&&workFlags.size()>0){
			     tableService.removeAllEntites(workFlags);
			}
			/** 流程冲突第二步-结束 */
		}
	}
	
	/**
	 * 
	 * checkWorkFlowConflict(检查流程冲突)
	 * (这里描述这个方法适用条件 – 可选)
	 * @param tableService  操作数据库
	 * @param workFlowName  流程名
	 * @param flowunid      流程实列
	 * @param flowkey       流程冲突关键字。项目为项目id，合同的id
	 * @return
	 * @throws Exception 
	 *String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String  checkWorkFlowConflict(BaseService tableService,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo,String flowkey) throws Exception{
		/**
		 * 流程冲突共三步
		 * 1、起始位置添加第一步，如**startAction
		 * 2、删除流程实例时添加第二部 ,**startAction中delete方法
		 * 3、结束流程里添加第三步, 例**endAction中添加
		 * 注意：例如经销资金收款中，第一步插入冲突表时，需要插入多个，不需要第二步，第三步也需要删除多个
		 * 		workFlowFlag插入时已逗号分隔。
		 * 
		 * 流程冲突第一步-开始*/
		
		String workFlowName = jbpmWorkflowHistoryInfo.getWorkflowName(); 
	    String flowunid=jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid()+""; 
		
		
		String hql = "SELECT new Map(WF.workNumber as workNumber, WF.workFlowName as workFlowName,WF.workFlowInstance AS workFlowInstance, WF.beginUser AS beginUser) FROM  WorkFlowFlag AS WF , ExclusionInfo AS EI  WHERE WF.workFlowName = EI.workFlowNameB AND EI.workFlowNameA = '"+workFlowName+"' AND WF.workNumber = '"+ flowkey +"'";
		System.out.println(hql);
		List<Map<String,String>> workFlowFlagList =  tableService.findResultsByHSQL(hql);
		if (workFlowFlagList.size() > 0){
			System.out.println(workFlowFlagList);
			Map<String, String> workFlowInfoMap = workFlowFlagList.get(0);
			User beginUser = tableService.findEntityByID(User.class, workFlowInfoMap.get("beginUser"));
			String workFlowNameB = workFlowInfoMap.get("workFlowName");
			throw new BusinessException("该条流程与【"+workFlowNameB+"】流程不能同时发起,请与流程发起人:【"+beginUser.getRealname()+"】联系查看详情");
		}
		
		//将本条流程记录插入到冲突表
		WorkFlowFlag workFlowFlag = new WorkFlowFlag();
		workFlowFlag.setBeginUser(SecurityUtil.getPrincipal().getId());
		workFlowFlag.setStatus(1);
		workFlowFlag.setWorkFlowInstance(flowunid+"");
		workFlowFlag.setWorkFlowName(workFlowName);
		workFlowFlag.setWorkNumber(flowkey);
		tableService.saveEntity(workFlowFlag);
        return workFlowFlag.getId();
}}
