package com.reckon.dao.impl;



import org.apache.log4j.Logger;

import com.reckon.bean.AdjustBean;
import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.TabCalBean;
import com.reckon.renttranrate.service.impl.ContrRentPlanServiceImpl;
import com.reckon.util.NumberUtils;
import com.reckon.util.TbBeanTools;
import com.reckon.util.tools.DateTools;
import com.reckon.util.tools.NumTools;


public class AdjustDAOImpl {
	static Logger logger = Logger.getLogger(AdjustDAOImpl.class);
	/**
	 * 
	 *  (更加Adjustbean把conditionbean重置)
	 * @param cdb conditionbean 交易结构bean 
	 * @param adb Adjustbean 租金变更bean 
	 * @param frpb FundRentPlanBean 租金计划bean 
	 * @return conditionbean 重置之后的交易结构bean 
	 * @throws Exception 
	 */
	public static ConditionBean getConditionBeanByAdjustBean(ConditionBean cdb,AdjustBean adb,FundRentPlanBean frpb) throws Exception{
		//重置cdb中信息
		//重置付款类型
		//cdb.setIncome_number_year(adb.getIncomeNumberYear());
		//重构宽限期
		int grace = cdb.getGrace();
		if(grace-adb.getStartList()>=0){
			grace=grace-adb.getStartList()+1;
		}else{
			grace=0;
		}
		cdb.setGrace(grace);
		//重置付租方式
		cdb.setPeriodType(ContrRentPlanServiceImpl.getPeroidType(adb.getStartList(),cdb.getGrace(),cdb.getPeriodType()));
		//重构还款次数
		int income_number_year=Integer.parseInt(cdb.getIncomeNumberYear());
		int incomeNumber = ContrRentPlanServiceImpl.getIncomeNumByAdjust(adb.getAdjustList(), adb.getStartList(), cdb.getGrace());
		cdb.setIncomeNumber(incomeNumber);
		//重构租赁期限
		int incoms_num = grace + cdb.getIncomeNumber();
		cdb.setLeaseTerm(12 * incoms_num / income_number_year);
		//重置起租日
		String start_date="";
		if(adb.getPaydayAdjust()==null||adb.getPaydayAdjust().equals("")){
			//如果变更调整中的日期为空,那么就以开始调整期数当期为起租日
			start_date=frpb.getPlanDateList().get(adb.getStartList()-1).toString();
		}else{
			start_date=adb.getPaydayAdjust();
		}
		cdb.setStartDate(start_date);
		//cdb.setStartDate(DateTools.getDBDateStr(ContrRentPlanServiceImpl.getStartDate(adb.getStartList(), 
			//	cdb.getPeriodType(), cdb.getStartDate(), start_date, String.valueOf((12/income_number_year)))));
		//重置利率
		if( adb.getYearRate()!= null && Double.parseDouble( NumberUtils.nullToZero( String.valueOf( adb.getYearRate() ) ) ) > 0 ){
			cdb.setYearRate(String.valueOf(adb.getYearRate()));
		}
		//剩余测算总本金 为开始调整(包括当期)之后的本金总和
		 String clean_lease_money="";
		 //2011-12-1 因为均息法和等额租金的剩余本金总额字段不一样所以要判断
		 if ("even_rent".equals(cdb.getSettleMethod()) ){
			 clean_lease_money=NumTools.getSumCorpusOverage(frpb.getCorpusBusinessList(), adb.getStartList());
		 }else if ("even_interest".equals(cdb.getSettleMethod())){//均息法
			 clean_lease_money=NumTools.getSumCorpusOverage(frpb.getColumn_1(), adb.getStartList());
		 }else{//[KEY:scl-A]2012-11-22其他的方式默认为本金
			 clean_lease_money=NumTools.getSumCorpusOverage(frpb.getCorpusBusinessList(), adb.getStartList());
		 }
		//2011-07-13 修改 总本金 + 期末余值
		 clean_lease_money = NumTools.calculationStr(clean_lease_money, cdb.getEquipEndValue(), NumTools.ADD, 2);
		
		// 租金测算pmt公式后期测算代入的值,相当于期末残值,默认为0==设备残值+期末购买权【原名称：名义货价】
		//cdb.setPmtEndValue("0");
		
		//TODO: 这个字段暂时不用了
		//cdb.setPmtEndValue(cdb.getEquipEndValue());
		
		//cdb.setPmtEndValue(String.valueOf(Double.parseDouble(cdb.getEquip_end_value())+Double.parseDouble(cdb.getNominal_price())));
		// 租金测总本金合同
		cdb.setCleanLeaseMoney(clean_lease_money);
		
		/**
		 TODO:谢永伦，因为5.1暂时无财务相关操作，所以这里变更的测算本金是剩余业务本金合计
		// 租金测总本金会计,2011-07-13修改 计算错误－－》应是财务本金之和＋期末余值
		//cdb.setCleanLeaseMoney(String.valueOf(Double.parseDouble(clean_lease_money)+Double.parseDouble(cdb.getCaution_money())));
		//cdb.setCleanLeaseMoney(NumTools.calculationStr(clean_lease_money, cdb.getEquip_end_value(), NumTools.ADD, 2));
		//2012-09-22 财务本金始终取值为财务本金字段不区分均息法和等额
		//cdb.setCleanLeaseMoney(NumTools.calculationStr(NumTools.getSumCorpusOverage(frpb.getCorpus_list(), adb.getStartList()), cdb.getEquip_end_value(), NumTools.ADD, 2));
		//[KEY:scl-A]2012-11-19 会计租金计划用于测算的其实是财务本金,但是财务本金虽然取值为财务本金这一列,
		//但是实际上上面说的财务本金字段应该是会计租金计划中的本金.
		//但是会计租金计划在后来的测算中已经分开为另一个表.所以这里要从新到会计租金计划中抓取本金值.
		FundRentPlanBean finacBean = new FundRentPlanBean();
		TabCalBean tcb = TbBeanTools.getTabInfo("cont_process", adb);
		tcb.setRentPlan_tb(tcb.getRentFinaPlan_tb());//将合同租金计划表设置为会计租金计划表
		finacBean = new RentPlanContrCalDAOImpl().getRentAndDateByTcb(tcb,1);
		cdb.setCleanLeaseMoney(NumTools.calculationStr(NumTools.getSumCorpusOverage(finacBean.getCorpusBusinessList(), adb.getStartList()), cdb.getEquipEndValue(), NumTools.ADD, 2));
		*/
		logger.info("变更测算本金"+cdb.getCleanLeaseMoney());
		
		return cdb;
	}
}
