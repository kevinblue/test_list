package com.tenwa.leasing.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tenwa.leasing.entity.fund.ContractFundRentInCome;
import com.tenwa.leasing.entity.fund.ContractFundRentPlan;


/**
 * 
 * @author 计算租罚息
 * 
 */
public class PenaltyUtil {
	private static Logger  log=Logger.getLogger(PenaltyUtil.class);
	/**
	 * 
	* @author 作者 xuyunlong
	* @version 创建时间：2013-10-29 下午07:22:54 
	* 租金计划为com.business.entity.contract.reckon.fund.ContractFundRentPlan
	* 参数rentplan:租金计划
	*    cur_date:计划罚息日
	*    rate:罚息利率
	*    freeDefaInterDay：免罚息天数据
	*    isRent：true以租金计算罚息 false以本金计算罚息
	*    返回数组[罚息余额，已收罚息+减免]
	 */
	public static List<BigDecimal> getRentPenalty(ContractFundRentPlan rentPlan,String cur_date, BigDecimal rate,int freeDefaInterDay,boolean isRent) {
	   
		 String plan_date=rentPlan.getPlanDate();//租金计划日期
	     String hireDate="";//核销日期
	     List<BigDecimal> Penaltys=new ArrayList<BigDecimal>();
	     //计算租金计划日期和当前时间的时间差
		 long difC=Tools.dateDiff("day", plan_date, cur_date);
		 long difp=0;
		 BigDecimal bdifc=new BigDecimal(difC);
		 BigDecimal recieverpenalty= new BigDecimal(0.00);//实收罚息
		 BigDecimal penalty= new BigDecimal(0.00);//罚息
		 BigDecimal rentOver=rentPlan.getRent();//租金
		 if(isRent==true){
			 rentOver=rentPlan.getRent();
		 }else{
			 rentOver=rentPlan.getCorpus();
		 }
		 Map<String,BigDecimal> rentincome=new HashMap<String,BigDecimal>();//时收
		 if (difC<=0 || difC<freeDefaInterDay){
			 //计划日期大于当前给定日期
			 Penaltys.add(BigDecimal.ZERO);
			 Penaltys.add(BigDecimal.ZERO);
		 }else{
			 if(rentPlan.getContractFundRentInComes().size()>0){
				 for(ContractFundRentInCome fc:rentPlan.getContractFundRentInComes()){
					 hireDate=fc.getHireDate();
					 if(null!=fc.getBalanceMode()){
						 if(fc.getBalanceMode().getCode().equals("payfund11")){
							 hireDate=plan_date;
						 }
					 }
					 difC=Tools.dateDiff("day", plan_date, hireDate);//核销时间和上次核销的时间的时间差
					 difp=Tools.dateDiff("day", hireDate, cur_date);
					 if(difp>=0){
						if(difC<=freeDefaInterDay){
							  if(isRent==true){
							      rentOver=rentOver.subtract(fc.getRent()).setScale(2, BigDecimal.ROUND_HALF_UP);
						      }else{
							    penalty= penalty.add(ComputerPenaltyByFomula(fc.getCorpus(),rate,new BigDecimal(difC))).setScale(2, BigDecimal.ROUND_HALF_UP);;
						       }
						}else{
					       if(isRent==true){
						      penalty= penalty.add(ComputerPenaltyByFomula(fc.getRent(),rate,new BigDecimal(difC))).setScale(2, BigDecimal.ROUND_HALF_UP);;
						      rentOver=rentOver.subtract(fc.getRent()).setScale(2, BigDecimal.ROUND_HALF_UP);
					      }else{
						    penalty= penalty.add(ComputerPenaltyByFomula(fc.getCorpus(),rate,new BigDecimal(difC))).setScale(2, BigDecimal.ROUND_HALF_UP);;
						    rentOver=rentOver.subtract(fc.getCorpus()).setScale(2, BigDecimal.ROUND_HALF_UP);
					     }
					   }
					}
				 }
			 }
			 if(rentOver.floatValue()>0){
				 difC=Tools.dateDiff("day", plan_date, cur_date);
				 if(difC>freeDefaInterDay){
				    penalty= penalty.add(ComputerPenaltyByFomula(rentOver,rate,new BigDecimal(difC))).setScale(2, BigDecimal.ROUND_HALF_UP);;
				 }
				}
			 //减罚息实收
		     penalty=penalty.subtract(rentPlan.getCurpenaltyIncome()).setScale(2, BigDecimal.ROUND_HALF_UP);
		     //减罚息实减
		     penalty=penalty.subtract(rentPlan.getCurpenaltyAdjustIncome()).setScale(2, BigDecimal.ROUND_HALF_UP);
			 Penaltys.add(penalty);
			 Penaltys.add(rentPlan.getCurpenaltyIncome().add(rentPlan.getCurpenaltyAdjustIncome()).setScale(2, BigDecimal.ROUND_HALF_UP));
		 }
		 return Penaltys;
		 
	}
	public static BigDecimal ComputerPenaltyByFomula(BigDecimal rent,BigDecimal rate,BigDecimal difdate){
		System.out.println(rent.toString()+"*"+rate.toString()+""+"*"+difdate.toString()+"/10000");
		BigDecimal penalty=BigDecimal.ZERO;
		penalty=rent.multiply(rate);
		//penalty=penalty.divide(new BigDecimal(360),6, BigDecimal.ROUND_HALF_EVEN);
		penalty=penalty.multiply(difdate);
		penalty=penalty.divide(new BigDecimal("10000"),6, BigDecimal.ROUND_HALF_EVEN);
		return penalty.setScale(2, BigDecimal.ROUND_HALF_UP);	 
	}
	public static void main(String[] args){
		log.debug(Tools.dateDiff("day", "2012-3-9", "2012-3-10"));
		BigDecimal penalty=new BigDecimal("1.00");
		BigDecimal penalty1=new BigDecimal("55.00") ;
		penalty=penalty.add(penalty1);
		log.debug(penalty.doubleValue());
		
	}
	
}
