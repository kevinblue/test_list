package com.reckon.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.cxf.binding.corba.wsdl.Array;
import org.apache.log4j.Logger;

import com.reckon.bean.ConditionBean;
import com.reckon.calculation.utils.IRRCalculateUtil;
import com.reckon.calculation.utils.Scale;
import com.reckon.util.IrrTools;
import com.tenwa.leasing.utils.Tools;


public class RentCalculateControllerHelper {
	
	/**
	 * LOG4J日志
	 */
	private static Logger log = Logger.getLogger(RentCalculateControllerHelper.class);
	
	/**
	 * 
	 * <p>根据指定的租金计算需要的年利率。</p>
	 * @author 谢永伦
	 * @param modelData 前端租金测算条件构建的JSON字符串转换的MAP集合
	 * <p>2014-11-7</p>
	 */
	public static void calculateYearRateFromRent(Map<String, String> modelData){
		//取值时，不确定在5.1的前端JSP使用的是：framework_condition.小写属性名 这种格式，还是使用直接小写属性名格式传值，因此取值时做下判断
		//租金计算方式:等额租金even_rent、均息法even_interest、不规则irregular_rent
		String selectMethod = Tools.isNullOrEmpty(modelData.get("framework_condition.settlemethod")) ? modelData.get("settlemethod") : modelData.get("framework_condition.settlemethod");
		//租金推算方法:按年利率计算租金rent、按租金计算年利率rate、已知租借规则测算knowing_rent
		String rentOrRate = Tools.isNullOrEmpty(modelData.get("framework_condition.rentorrate")) ?  modelData.get("rentorrate") : modelData.get("framework_condition.rentorrate") ;
		
		//按租金计算年利率情况下才会计算年利率
		if("rent".equals(rentOrRate)){
			if("even_interest".equals(selectMethod)){
				calculateYearRateFromEventInterest(modelData);//均息法
			}
			if("even_rent".equals(selectMethod)){
				calculateYearRateFromEventRent(modelData);//等额租金
			}
		}
	}
	
	/**
	 * 
	 * <p>等额租金情况下，根据指定的租金计算需要的年利率。</p>
	 * @author 谢永伦
	 * @param modelData前端租金测算条件构建的JSON字符串转换的MAP集合
	 * <p>2014-11-7</p>
	 */
	public static void calculateYearRateFromEventRent(Map<String, String> modelData){
		//period_type_1 前收  期初1/期末0
		String periodType = Tools.isNullOrEmpty(modelData.get("framework_condition.periodtype")) ? modelData.get("periodtype") : modelData.get("framework_condition.periodtype") ;
		//每期租金
		String rent = Tools.isNullOrEmpty(modelData.get("framework_condition.rentorratevalue")) ? modelData.get("rentorratevalue") : modelData.get("framework_condition.rentorratevalue") ;
		//租金支付类型
		String yearIncomeNumber = Tools.isNullOrEmpty(modelData.get("framework_condition.incomenumberyear")) ? modelData.get("incomenumberyear") : modelData.get("framework_condition.incomenumberyear") ;
		//还租次数
		String incomenumber = Tools.isNullOrEmpty(modelData.get("framework_condition.incomenumber")) ? modelData.get("incomenumber") : modelData.get("framework_condition.incomenumber") ;
		//融资额 【注意：该值的取值仅适用于TENWA5.1的前台代码的逻辑，因为在OCC或者德银等项目中该值的字段名称为：framework_condition.leaseamt】
		String leaseMoney = Tools.isNullOrEmpty(modelData.get("framework_condition.cleanleasemoney")) ? modelData.get("cleanleasemoney") : modelData.get("framework_condition.cleanleasemoney") ;
		//期末余值
		String equipendvalue = Tools.isNullOrEmpty(modelData.get("framework_condition.equipendvalue") ) ? modelData.get("equipendvalue") : modelData.get("framework_condition.equipendvalue") ;
		
		//循环封装租金用于构建现金流
		List<String> rentList = new ArrayList<String>();
		for (int i = 0; i < Integer.parseInt(incomenumber); i++) {
			rentList.add( rent);
		}
		
		/**
		 *起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
		 *1：期初情况下，第一期租金应算入第0期净流量中 
		 *0：期末情况下，融资额直接作为第0期净流量 
		 */
		if("period_type_1".equals(periodType) || "1".equals(periodType)){// 期初
			BigDecimal leaseAmt = new BigDecimal(leaseMoney).multiply(new BigDecimal(-1));
			BigDecimal first = new BigDecimal(rentList.get(0));
			rentList.set(0,leaseAmt.add(first).toString());
		}
		if("period_type_0".equals(periodType) || "0".equals(periodType)){// 期末
			rentList.add(0,new BigDecimal(leaseMoney).multiply(new BigDecimal(-1)).toString());
		}
		
		//期末余值处理:计算年利率时，该值加在最后一期上
		BigDecimal endMoney =  new BigDecimal(rentList.get(rentList.size()-1));
		endMoney = endMoney.add(new BigDecimal(equipendvalue));
		
		if("period_type_1".equals(periodType) || "1".equals(periodType)){// 期初
			rentList.add(rentList.size(),equipendvalue);
		}
		if("period_type_0".equals(periodType) || "0".equals(periodType)){// 期末
			rentList.set(rentList.size()-1,endMoney.toString());
		}
		
		/**
		 * select * from t_dicts_datas where pid_ like '%income_number_year%';
		 * NAME_	CODE_
		 * 月  付	income_1
		 * 季  付	income_3
		 * 半年付	income_6
		 * 年  付	income_12
		 * 双月付	income_2
		 * JSON格式：incomenumberyear=income_1
		 */
		yearIncomeNumber = yearIncomeNumber.replace("income_", "");
	/*	List<String> rentlist2=new ArrayList<String>();
		for(int i=0;i<rentList.size();i++){
			rentlist2.add(rentList.get(i));
			for(int j=0;j<Integer.parseInt(yearIncomeNumber)-1;j++){
				rentlist2.add("0");
			}
		}*/
		//期IRR乘以还款间隔即为年利率
		String newYearRate=IrrTools.getIRRCalYearRate(rentList, yearIncomeNumber,"12");
		log.info("newYearRate:"+newYearRate);
		//返回年利率
		modelData.put("framework_condition.yearrate", newYearRate.toString());
		modelData.put("yearrate", newYearRate.toString());
	}
	
	public static Map<String, Object> calculateYearRateFromKnowing(Map[]FundRentMap,ConditionBean cb){
		Map<String, Object> returnInfo = new HashMap<String, Object>();
		//period_type_1 前收  期初1/期末0
		String periodType = cb.getPeriodType();
		//租金支付类型
		String yearIncomeNumber = cb.getIncomeNumberYear();
		//还租次数
		String incomenumber = cb.getIncomeNumber()+"";
		//融资额 【注意：该值的取值仅适用于TENWA5.1的前台代码的逻辑，因为在OCC或者德银等项目中该值的字段名称为：framework_condition.leaseamt】
		String leaseMoney = cb.getCleanLeaseMoney();
		//期末余值
		String equipendvalue = cb.getEquipEndValue();
		List<String> rentListStr = new ArrayList<String>();		
		//循环封装租金用于构建现金流
		List<BigDecimal> rentList = new ArrayList<BigDecimal>();
		for (Map fund: FundRentMap) {
			if(fund.get("rentadjust")!=null &&  (fund.get("rentadjust")+"").matches("\\d+")){
				rentList.add(new BigDecimal(fund.get("rentadjust")+""));
				rentListStr.add(fund.get("rentadjust")+"");
			}else{
				rentList.add(new BigDecimal(fund.get("rent")+""));
				rentListStr.add(fund.get("rent")+"");
			}
		}
		returnInfo.put("rentList", rentListStr);
		/**
		 *起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
		 *1：期初情况下，第一期租金应算入第0期净流量中 
		 *0：期末情况下，融资额直接作为第0期净流量 
		 */
		if("period_type_1".equals(periodType) || "1".equals(periodType)){// 期初
			BigDecimal leaseAmt = new BigDecimal(leaseMoney).multiply(new BigDecimal(-1));
			BigDecimal first = rentList.get(0);
			rentList.set(0,leaseAmt.add(first));
		}
		if("period_type_0".equals(periodType) || "0".equals(periodType)){// 期末
			rentList.add(0,new BigDecimal(leaseMoney).multiply(new BigDecimal(-1)));
		}
		
		//期末余值处理:计算年利率时，该值加在最后一期上
		BigDecimal endMoney =  rentList.get(rentList.size()-1);
		endMoney = endMoney.add(new BigDecimal(equipendvalue));
		
		if("period_type_1".equals(periodType) || "1".equals(periodType)){// 期初
			rentList.add(rentList.size(),new BigDecimal(equipendvalue));
		}
		if("period_type_0".equals(periodType) || "0".equals(periodType)){// 期末
			rentList.set(rentList.size()-1,endMoney);
		}
		
		//计算期IRR
		BigDecimal issueRate = IRRCalculateUtil.getIRR(rentList);
		log.info("issueRate:"+issueRate);
		/**
		 * select * from t_dicts_datas where pid_ like '%income_number_year%';
		 * NAME_	CODE_
		 * 月  付	income_1
		 * 季  付	income_3
		 * 半年付	income_6
		 * 年  付	income_12
		 * 双月付	income_2
		 * JSON格式：incomenumberyear=income_1
		 */
		yearIncomeNumber = yearIncomeNumber.replace("income_", "");
		//期IRR乘以还款间隔即为年利率
		BigDecimal newYearRate = issueRate.multiply(new BigDecimal(1200/Integer.parseInt(yearIncomeNumber)));
		log.info("newYearRate:"+newYearRate);
		returnInfo.put("yearRate", newYearRate+"");
		return returnInfo;
	}
	
	
	/**
	 * 
	 * <p>均息法情况下：根据指定的租金计算需要的年利率。</p>
	 * @author sea
	 * @param modelData
	 * <p>2014-11-7</p>
	 */
	public static void calculateYearRateFromEventInterest(Map<String, String> modelData){
		//每期租金
		String rent = Tools.isNullOrEmpty(modelData.get("framework_condition.rentorratevalue")) ? modelData.get("rentorratevalue") : modelData.get("framework_condition.rentorratevalue") ;
		//租赁期限(月)
		String leaseTerm = Tools.isNullOrEmpty(modelData.get("framework_condition.leaseterm")) ? modelData.get("leaseterm") : modelData.get("framework_condition.leaseterm") ;
		//还租次数
		String incomeNumber = Tools.isNullOrEmpty(modelData.get("framework_condition.incomenumber")) ? modelData.get("incomenumber") : modelData.get("framework_condition.incomenumber") ;
		//融资额【注意：该值的取值仅适用于TENWA5.1的前台代码的逻辑，因为在OCC或者德银等项目中该值的字段名称为：framework_condition.leaseamt】
		String leaseMoney = Tools.isNullOrEmpty(modelData.get("framework_condition.cleanleasemoney")) ? modelData.get("cleanleasemoney") : modelData.get("framework_condition.cleanleasemoney") ;
		//宽限期数
		String grace = Tools.isNullOrEmpty(modelData.get("framework_condition.grace")) ? modelData.get("grace") : modelData.get("framework_condition.grace") ;
		
		//计算每期本金：总融资额/还租次数
		BigDecimal issueCorpus = new BigDecimal(leaseMoney).divide(new BigDecimal(incomeNumber), Scale.RATE_SCALE, BigDecimal.ROUND_HALF_UP);
		//利息 = 租金 - 本金
		BigDecimal issueInterest = new BigDecimal(rent).subtract(issueCorpus);
		//计算利息总额
		BigDecimal allInterest = issueInterest.multiply(new BigDecimal(incomeNumber).add(new BigDecimal(grace)));
		
		//总利息/总本金/年限（租赁期限/12）
		BigDecimal newYearRate = allInterest.multiply(new BigDecimal(100)).divide(new BigDecimal(leaseMoney), Scale.RATE_SCALE, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(leaseTerm).divide(new BigDecimal(12), 20, BigDecimal.ROUND_HALF_UP), Scale.RATE_SCALE, BigDecimal.ROUND_HALF_UP);
		//返回均息法情况下的年利率
		modelData.put("framework_condition.yearrate", newYearRate.toString());
		modelData.put("yearrate", newYearRate.toString());
	}
}
