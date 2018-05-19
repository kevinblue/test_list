package com.reckon.rentcalc.service.impl.evencorpus;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.TabCalBean;
import com.reckon.dao.impl.RentPlanContrCalDAOImpl;
import com.reckon.rentcalc.service.impl.pub.RentPlanContrCalServiceImpl;
import com.reckon.util.tools.NumTools;


/**
 * 
 * @author SCLICX
 * @version 1.0
 * @copyright (C) Tenwa 2011
 * @date Nov 29, 2011
 * @desc 均息法的租金计划变更实现类
 */
public class EvenCorpusPlanStopCalServiceImpl extends RentPlanContrCalServiceImpl {
	static Logger logger = Logger.getLogger(EvenCorpusPlanStopCalServiceImpl.class);

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public FundRentPlanBean rentPlanCalByConditionAndTab(ConditionBean cb, TabCalBean tcb, int startList, FundRentPlanBean frpb) throws Exception {
		int fromIssue = startList;// 调整开始期
		
		List<String> businessCorpusList = frpb.getCorpusBusinessList();				//[28129.91, 28343.23, 28558.17, 28774.73, 257189.02]业务本金
		List<String> businessInterestList = frpb.getInterestBusinessList();			//[2669.34, 2456.02, 2241.08, 2024.52, 31950.35]业务利息
		List<String> planDateList = frpb.getPlanDateList();					//[2013-10-05, 2013-11-05, 2013-12-05, 2014-01-05, 2014-02-05]
		
		//看看从第fromIssue期开始结算时，剩余的本金，本来应该直接拿本金余额列表中对应的值的，
		//但是第二次点结算时，这个列表变化了，无奈只要一个一个加起来了
		String corpusOverageStr = NumTools.getSumCorpusOverage(businessCorpusList, fromIssue - 1);
		
		BigDecimal corpus = new BigDecimal(corpusOverageStr);//
		BigDecimal interest = new BigDecimal(businessInterestList.get(fromIssue-1).toString());
		BigDecimal corpusOverage = new BigDecimal(0);//结算完成后的本金余额，必须为0
		BigDecimal rent = corpus.add(interest);
		
		List<String> newBussinessCorpusList = new ArrayList<String>();
		newBussinessCorpusList.add(corpus.toString());
		List<String> newBusinessInterestList = new ArrayList<String>();
		newBusinessInterestList.add(interest.toString());
		List<String> newBusinessCorpusOverageList = new ArrayList<String>();
		newBusinessCorpusOverageList.add(corpusOverage.toString());
		List<String> newRentList = new ArrayList<String>();
		newRentList.add(rent.toString());
		List<String> newPlanDateList = new ArrayList<String>();
		newPlanDateList.add(planDateList.get(fromIssue - 1));
		
		frpb.setColumn_1(newBussinessCorpusList);
		frpb.setColumn_2(newBusinessInterestList);
		frpb.setInterestBusinessList(newBusinessInterestList);
		frpb.setCorpusBusinessList(newBussinessCorpusList);
		frpb.setCorpusOverageBusinessList(newBusinessCorpusOverageList);
		frpb.setPlanDateList(newPlanDateList);
		frpb.setRentList(newRentList);
		
		RentPlanContrCalDAOImpl rpcd = new RentPlanContrCalDAOImpl();
		rpcd.deleteRentPlan(tcb.getRentPlan_tb(), tcb, startList);
		rpcd.addRentPlan(tcb.getRentPlan_tb(), frpb, tcb, startList,cb);
		return frpb;
	}
	
	
}
