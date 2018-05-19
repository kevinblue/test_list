package com.reckon.rentcalc.service.impl.pub;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.KnowingRentBean;
import com.reckon.bean.SpecialRulesBean;
import com.reckon.bean.TabCalBean;
import com.reckon.calculation.condition.CalculationConditionImpl;
import com.reckon.calculation.utils.RentCalculateUtil;
import com.reckon.calculation.vo.CalculationCondition;
import com.reckon.dao.impl.RentPlanContrCalDAOImpl;
import com.reckon.renttranrate.service.TransRateHelper;
import com.reckon.util.MoneyUtils;
import com.tenwa.leasing.utils.LeasingException;


/*******************************************************************************
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-3-4
 * @desc  ( 合同租金测算运算点)
 */
public class RentPlanContrCalServiceImpl {
	static Logger logger = Logger.getLogger(RentPlanContrCalServiceImpl.class);

	/**
	 * 
	 *  (  合同租金测算)
	 * 
	 * @param cb
	 * @param tcb
	 * @return
	 * @throws Exception
	 */
	public FundRentPlanBean rentPlanCalByConditionAndTab(ConditionBean cb, TabCalBean tcb, int startList, FundRentPlanBean frpb) throws Exception {

		logger.info("合同租金计划生成....方法rentPlanCalByConditionAndTab");
		// 根据表信息进行对表数据的清除操作
		RentPlanContrCalDAOImpl rpcd = new RentPlanContrCalDAOImpl();
		rpcd.deleteRentPlan(tcb.getRentPlan_tb(), tcb, startList);

		// 得到正常pmt租金测算后的租金计划
		getPmtRentPlan(cb, frpb);
		// 2011-12-6 为了配合均息法下的第二次测算需要租金计划bean中的利息列表所以在这里添加进入
		frpb.setYearRateList(new ArrayList<String>());
		for (int i = 0; i < frpb.getRentList().size(); i++) {
			frpb.getYearRateList().add(cb.getYearRate());
		}
		// 调用保存的操作
		rpcd.addRentPlan(tcb.getRentPlan_tb(), frpb, tcb, startList);
		logger.info("合同租金计划生成成功....方法rentPlanCalByConditionAndTab");
		// 返回租金计划实体 bean
		return frpb;
	}

	/**
	 * 
	 * 等额租金，均息法
	 * 
	 * 正常pmt租金测算后的租金计划
	 * 
	 * @param cb
	 * @param frpb
	 * @throws Exception 
	 */
	private void getPmtRentPlan(ConditionBean cb, FundRentPlanBean frpb) throws Exception {
		logger.info("正常 租金测算开始..(getPmtRentPlan)" + "t");
		
		// 加载租金列表
		RentalServiceImpl rsi = new RentalServiceImpl();
		List<String> rentList = rsi.getRentListByCond(cb);
		//Collections.sort(rentList);
		frpb.setRentList(rentList);
		
		
		// 加载日期列表
		PlanDateServiceImpl pdsi = new PlanDateServiceImpl();
		List<String> dateList = pdsi.getPlanDateList(cb, String.valueOf(frpb.getRentList().size()));
		Collections.sort( dateList );
		frpb.setPlanDateList(dateList);
		
		
		// 加载利息列表
		InterestCalServiceImpl icsi = new InterestCalServiceImpl();
		List<String> interests = icsi.getInterestList(frpb.getRentList(), cb.getCleanLeaseMoney(), cb.getYearRate(), cb.getPeriodType(), cb.getGrace(), cb.getIncomeNumberYear(), cb.getEquipEndValue());
		//Collections.sort( interests );
		frpb.setInterestBusinessList(interests);
		
		
		// 加载本金列表
		CorpusServiceImpl csi = new CorpusServiceImpl();
		List<String> corpusList = csi.getCorpusList(frpb.getRentList(), frpb.getInterestBusinessList());
		//Collections.sort( corpusList );
		frpb.setCorpusBusinessList(corpusList);
		
		
		// 加载调整信息列表
		RentPlanServiceImpl rpsi = new RentPlanServiceImpl();
		rpsi.adjustLastRentPlan(frpb, cb.getCleanLeaseMoney(), cb.getEquipEndValue());
		
		
		// 加载本金余额列表
		List<String> corpusOverageBusinessList = TransRateHelper.getCorpusOvergeList(cb.getCleanLeaseMoney(), frpb.getCorpusBusinessList());
		//Collections.sort( corpusOverageBusinessList );
		frpb.setCorpusOverageBusinessList(corpusOverageBusinessList);
		logger.info("正常 租金测算结束..(getPmtRentPlan)");
		
		
//		//越秀：第一期与起租日期不满一期，或者超过一期都要计算利息，要么加上，要么减掉。
//		//**********************************************************************************
//		CalculationCondition condition = new CalculationConditionImpl();
//		condition.copyConditionBeanValues(cb);
//		BigDecimal diffInterest = RentCalculateUtil.getFirstInterestDiff(condition);
//		
//		BigDecimal rent = new BigDecimal(rentList.get(0).toString());
//		rent = rent.add(diffInterest);
//		rentList.set(0, rent.toString());
//		
//		BigDecimal interest = new BigDecimal(interests.get(0).toString());
//		interest = interest.add(diffInterest);
//		interests.set(0, interest.toString());
//		
//		BigDecimal corpus = new BigDecimal(corpusList.get(0).toString());
//		corpus = rent.subtract(interest);
//		corpusList.set(0, corpus.toString());
//		//**********************************************************************************

		List<String> rentRemainList = new RentalServiceImpl().getRentRemainList(rentList);
		//Collections.sort( rentRemainList );
		frpb.setAllRemainRentList(rentRemainList);
	}
	
	/**
	 * 已知租金法构建完整的租金偿还计划
	 * 仅限于已知租金法租金列生成后的租金计划
	 * @param cb 交易结构信息
	 * @param frpb 
	 * @param kniwnRentBeans 已知租金法测算传入的分段租金计划值，为空则直接传入null
	 * <pre>中文注解：
     * 第1期到第6期租金值是：1W
	 * 第7期到第10期租金值是：2W
	 * 第11期到第12期租金值是：3W
	 * 底层方法生成完整的租金列集合是：{1W,1W,1W,1W,1W,2W,2W,2W,2W,2W,3W,3W}
	 * kniwnRentBeans伪码参数格式例子如下：
	 * List<KnowingRentBean> bean_l = new ArrayList<KnowingRentBean>();
	 *	KnowingRentBean obj0 = new KnowingRentBean();
	 *	obj0.setStartlist(1);
	 *	obj0.setEndlist(6);
	 *	obj0.setRent("10000.00");
	 *	bean_l.add(obj0);
	 *	KnowingRentBean obj1 = new KnowingRentBean();
	 *	obj1.setStartlist(7);
	 *	obj1.setEndlist(10);
	 *	obj1.setRent("20000.00");
	 *	bean_l.add(obj1);
	 *  ...
	 * <p>2014-3-11</p>
	 * @author sea
	 * @throws Exception 
	 */
	public FundRentPlanBean getKnowingRentPlan(ConditionBean cb, FundRentPlanBean frpb,List<KnowingRentBean> kniwnRentBeans) throws Exception {
		logger.info("已知租金法测算开始..(getKnowingRentPlan)" + "t");
		
		// 加载租金列表
		RentalServiceImpl rsi = new RentalServiceImpl();
		List<String> rentList = new ArrayList<String>();
		if(null == frpb.getRentAdjustList()){
			rentList  = rsi.getRentValue(kniwnRentBeans);
			frpb.setRentList(rentList);
		}else{
			rentList = frpb.getRentList();
		}
		
		
		// 加载日期列表
		PlanDateServiceImpl pdsi = new PlanDateServiceImpl();
		frpb.setPlanDateList(pdsi.getPlanDateList(cb, String.valueOf(frpb.getRentList().size())));
		
		
		// 加载利息列表
		InterestCalServiceImpl icsi = new InterestCalServiceImpl();
		List<String> interests = icsi.getInterestList(frpb.getRentList(), cb.getCleanLeaseMoney(), cb.getYearRate(), cb.getPeriodType(), cb.getGrace(), cb.getIncomeNumberYear(), cb.getEquipEndValue());
		frpb.setInterestBusinessList(interests);
		
		
		// 加载本金列表
		CorpusServiceImpl csi = new CorpusServiceImpl();
		List<String> corpusList = csi.getCorpusList(frpb.getRentList(), frpb.getInterestBusinessList());
		frpb.setCorpusBusinessList(corpusList);
		
		
		// 加载调整信息列表
		RentPlanServiceImpl rpsi = new RentPlanServiceImpl();
		rpsi.adjustLastRentPlan(frpb, cb.getCleanLeaseMoney(), cb.getEquipEndValue());
		
		
		// 加载本金余额列表
		frpb.setCorpusOverageBusinessList(TransRateHelper.getCorpusOvergeList(cb.getCleanLeaseMoney(), frpb.getCorpusBusinessList()));
		logger.info("已知租金法测算结束..(getKnowingRentPlan)");
		
		
		//越秀：第一期与起租日期不满一期，或者超过一期都要计算利息，要么加上，要么减掉。
		//**********************************************************************************
//		CalculationCondition condition = new CalculationConditionImpl();
//		condition.copyConditionBeanValues(cb);
//		BigDecimal diffInterest = RentCalculateUtil.getFirstInterestDiff(condition);
//		
//		BigDecimal rent = new BigDecimal(rentList.get(0).toString());
//		rent = rent.add(diffInterest);
//		rentList.set(0, rent.toString());
//		
//		BigDecimal interest = new BigDecimal(interests.get(0).toString());
//		interest = interest.add(diffInterest);
//		interests.set(0, interest.toString());
//		
//		BigDecimal corpus = new BigDecimal(corpusList.get(0).toString());
//		corpus = rent.subtract(interest);
//		corpusList.set(0, corpus.toString());
		//**********************************************************************************
		
		List<String> rentRemainList = new RentalServiceImpl().getRentRemainList(rentList);
		frpb.setAllRemainRentList(rentRemainList);
		
		return frpb;
	}

	/**
	 * 
	 * 均息法
	 * 
	 * 合同不规则租金测算
	 * 
	 * @param cb
	 * @param tcb
	 * @return
	 * @throws Exception
	 */
	public FundRentPlanBean rentPlanCalByDevi(ConditionBean cb, TabCalBean tcb, int startList, FundRentPlanBean frpb, String[] rentAdjustList) throws Exception {
		
		// 根据表信息进行对表数据的清除操作
		RentPlanContrCalDAOImpl rpcd = new RentPlanContrCalDAOImpl();
		rpcd.deleteRentPlan(tcb.getRentPlan_tb(), tcb, startList);
		String leas_income = cb.getCleanLeaseMoney();
		
		
		// 租金类返回租金列表
		RentalServiceImpl rsi = new RentalServiceImpl();
		List<String> rentList = rsi.getDeviRentList(cb, rentAdjustList, frpb.getYearRateList());
		frpb.setRentList(rentList);

		
		// 日期类返回日期列表
		PlanDateServiceImpl pdsi = new PlanDateServiceImpl();
		frpb.setPlanDateList(pdsi.getPlanDateList(cb, String.valueOf(frpb.getRentList().size())));

		
		// 利息类返回利息列表 因为在算租金的时候已经改变了测算总本金但是在算利息的时候其实要用最开始的总本金算
		InterestCalServiceImpl icsi = new InterestCalServiceImpl();
		List<String> interests = icsi.getInterestList(frpb.getRentList(), leas_income, cb.getCleanLeaseMoney(), frpb.getYearRateList(), cb.getPeriodType(), cb.getGrace(), cb.getIncomeNumberYear(), cb.getEquipEndValue(), rentAdjustList);
		frpb.setInterestBusinessList(interests);
		
		
		// 本金类返回本金列表
		CorpusServiceImpl csi = new CorpusServiceImpl();
		List<String> corpusList = csi.getCorpusList(frpb.getRentList(), frpb.getInterestBusinessList());
		frpb.setCorpusBusinessList(corpusList);
		
		
		// 本金余额类返回本金余额列表
		frpb.setCorpusOverageBusinessList(TransRateHelper.getCorpusOvergeList(cb.getCleanLeaseMoney(), frpb.getCorpusBusinessList()));

		
		// 2011-06-20修改 本金，利息格式化，并调整最后一期的本金值
		RentPlanServiceImpl rpsi = new RentPlanServiceImpl();
		rpsi.adjustLastRentPlan(frpb, leas_income, cb.getEquipEndValue());

		
		
		//越秀：第一期与起租日期不满一期，或者超过一期都要计算利息，要么加上，要么减掉。
		//**********************************************************************************
		CalculationCondition condition = new CalculationConditionImpl();
		condition.copyConditionBeanValues(cb);
		BigDecimal diffInterest = RentCalculateUtil.getFirstInterestDiff(condition);
		
		BigDecimal rent = new BigDecimal(rentList.get(0).toString());
		rent = rent.add(diffInterest);
		rentList.set(0, rent.toString());
		
		BigDecimal interest = new BigDecimal(interests.get(0).toString());
		interest = interest.add(diffInterest);
		interests.set(0, interest.toString());
		
		BigDecimal corpus = new BigDecimal(corpusList.get(0).toString());
		corpus = rent.subtract(interest);
		corpusList.set(0, corpus.toString());
		//**********************************************************************************
		
		List<String> rentRemainList = new RentalServiceImpl().getRentRemainList(rentList);
		frpb.setAllRemainRentList(rentRemainList);
		
		// 调用保存的操作
		rpcd.addRentPlan(tcb.getRentPlan_tb(), frpb, tcb, startList);
		
		// 返回租金计划实体 bean
		return frpb;

	}
	/**
	 * 已知本金比例
	 * @param cb
	 * @param frpb
	 * @param specialRulesBean
	 * @return
	 * @throws Exception
	 */
	public FundRentPlanBean getKnowingCorpusPlanForSpecial(ConditionBean cb, FundRentPlanBean frpb,SpecialRulesBean  specialRulesBean,int... str) throws Exception {
		List<String> corpusList = new ArrayList<String>();
		List<String> yearRateList = new ArrayList<String>();
		Integer startlist = null!=str&&str.length>0?str[0]: specialRulesBean.getStartList();//开始期项
		Integer endlist = specialRulesBean.getEndList();//截止期项
		BigDecimal boforeCorpus=cb.getRemainCorpus();//剩余本金
		String corpus = specialRulesBean.getRate().toString(); //对应本金值  
		BigDecimal corpusSum=BigDecimal.ZERO;
		for (int j = startlist; j <= endlist; j++) {
			corpusList.add(corpus);
			yearRateList.add(cb.getYearRate());
			corpusSum=corpusSum.add(new BigDecimal(corpus));
		}
		frpb.getYearRateList().addAll(yearRateList);
		// 加载本金列表
		frpb.getCorpusBusinessList().addAll(corpusList);
		
		// 加载利息列表 返回剩余本金
		InterestCalServiceImpl icsi = new InterestCalServiceImpl();
		List<String> interests = icsi.getKnowingCorpusInterestList(corpusList, cb.getCleanLeaseMoney(), cb, specialRulesBean,new BigDecimal(cb.getYearRate()), cb.getPeriodType(), cb.getGrace(), cb.getIncomeNumberYear(), cb.getEquipEndValue(),startlist);
		frpb.getInterestBusinessList().addAll(interests);
		
		//加载租金列表
		List<String> rentList = new ArrayList<String>();
		for (int i=0;i<corpusList.size();i++) {
			rentList.add(new BigDecimal(corpusList.get(i)).add(new BigDecimal(interests.get(i))).toString());
		}
		frpb.getRentList().addAll(rentList);
		
		
		
		
		// 加载调整信息列表
		/*RentPlanServiceImpl rpsi = new RentPlanServiceImpl();
		rpsi.adjustLastRentPlan(frpb, cb.getCleanLeaseMoney(), cb.getEquipEndValue());*/
		
		
		// 加载本金余额列表
		frpb.getCorpusOverageBusinessList().addAll(TransRateHelper.getCorpusOvergeList(boforeCorpus.toString(), frpb.getCorpusBusinessList()));
		logger.info("已知租金法测算结束..(getKnowingRentPlan)");
		
		
		List<String> rentRemainList = new RentalServiceImpl().getRentRemainList(rentList);
		frpb.getAllRemainRentList().addAll(rentRemainList);
		cb.setRemainLeaseTerm(cb.getRemainLeaseTerm()-(endlist-startlist+1)*Integer.parseInt(specialRulesBean.getRegular_months()));
		if(cb.getRemainLeaseTerm()==0&&cb.getRemainCorpus().compareTo(BigDecimal.ZERO)>0){
			throw new LeasingException("测算失败：第"+startlist+"期到第"+endlist+"期分段规则设置不合理，本金之和需等于融资额！");
		}else if(cb.getRemainLeaseTerm()!=0&&cb.getRemainCorpus().compareTo(BigDecimal.ZERO)<0){
			throw new LeasingException("测算失败：第"+startlist+"期到第"+endlist+"期分段规则设置不合理，本金之和需等于融资额！");
		}
		return frpb;
	}
	/**
	 * 等额本息 分段测算
	 * @param cb
	 * @param frpb
	 * @param specialRulesBean
	 * @return
	 * @throws Exception
	 */
public FundRentPlanBean getEqualRentPlanForSpecial(ConditionBean cb, FundRentPlanBean frpb,SpecialRulesBean  specialRulesBean,int... str) throws Exception {
		
		Integer startlist = null!=str&&str.length>0?str[0]:specialRulesBean.getStartList();//开始期项
		Integer endlist = specialRulesBean.getEndList();//截止期项
		List<String> yearRateList=new ArrayList<String>();
		BigDecimal boforeCorpus=cb.getRemainCorpus();
		cb.setRemainCorpus(boforeCorpus);
		// 加载租金列表
		RentalServiceImpl rsi = new RentalServiceImpl();
		if(cb.getRemainCorpus().compareTo(BigDecimal.ZERO)<0){
			throw new LeasingException("融资额太小！！");
		}
		List<String> rentList = rsi.getRentListByCondForSpecial(cb,specialRulesBean,startlist);
		frpb.getRentList().addAll(rentList);
		// 加载利息列表
		InterestCalServiceImpl icsi = new InterestCalServiceImpl();
		List<String> interests = icsi.getInterestList(rentList, cb.getCleanLeaseMoney(), cb, specialRulesBean,new BigDecimal(cb.getYearRate()), cb.getPeriodType(), cb.getGrace(), cb.getIncomeNumberYear(), cb.getEquipEndValue(),startlist);
		frpb.getInterestBusinessList().addAll(interests);
		
		// 加载本金列表
		List<String> corpusList = new ArrayList<String>();
		for (int i=0;i<rentList.size();i++) {
			yearRateList.add(cb.getYearRate());
			corpusList.add(new BigDecimal(rentList.get(i)).subtract(new BigDecimal(interests.get(i))).toString());
		}
		frpb.getCorpusBusinessList().addAll(corpusList);
		
		// 加载调整信息列表
		/*RentPlanServiceImpl rpsi = new RentPlanServiceImpl();
		rpsi.adjustLastRentPlan(frpb, cb.getCleanLeaseMoney(), cb.getEquipEndValue());*/
		
		
		// 加载本金余额列表
		frpb.getCorpusOverageBusinessList().addAll(TransRateHelper.getCorpusOvergeList(boforeCorpus.toString(), corpusList));
		logger.info("已知租金法测算结束..(getKnowingRentPlan)");
		
		frpb.getYearRateList().addAll(yearRateList);
		List<String> rentRemainList = new RentalServiceImpl().getRentRemainList(rentList);
		frpb.getAllRemainRentList().addAll(rentRemainList);
		cb.setRemainLeaseTerm(cb.getRemainLeaseTerm()-(endlist-startlist+1)*Integer.parseInt(specialRulesBean.getRegular_months()));
		return frpb;
	}
}
