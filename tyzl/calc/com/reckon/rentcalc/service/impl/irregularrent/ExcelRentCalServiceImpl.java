package com.reckon.rentcalc.service.impl.irregularrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundPlanBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.FundRentPlanIrr;
import com.reckon.bean.SpecialRulesBean;
import com.reckon.bean.TabCalBean;
import com.reckon.commons.helper.RentCalculateHelper;
import com.reckon.dao.impl.RentPlanContrCalDAOImpl;
import com.reckon.rentcalc.service.RentCalcService;
import com.reckon.rentcalc.service.impl.pub.ConditionServiceImpl;
import com.reckon.rentcalc.service.impl.pub.RentalServiceImpl;
import com.reckon.renttranrate.service.TransRateHelper;
import com.reckon.util.DateUtils;
import com.reckon.util.TbBeanTools;

public class ExcelRentCalServiceImpl implements RentCalcService {
	
	static Logger logger = Logger.getLogger(ExcelRentCalServiceImpl.class);

	@Override
	public Hashtable<String, Object> rentCal(ConditionBean cb, String calType,
			String userName, String[] rentAdjustList, int startList,
			List<FundPlanBean> fundPlanList, List<SpecialRulesBean> srb,
			List<FundRentPlanIrr> irrPlans, List<String>... planList)
			throws Exception {
		
		logger.info("进入方法:rentCal 进行不规则的测算..");
		// 得到操作表信息
		TabCalBean tcb = TbBeanTools.getTabInfo(calType, cb);
		
		// 保存商务条件
		ConditionServiceImpl csi = new ConditionServiceImpl();
		csi.addConditionByCal(tcb, cb);
				
		// 合同租金测算
		FundRentPlanBean frpb = new FundRentPlanBean();
		frpb.setProjOrCont(cb.getProjId());
		frpb.setCreator(cb.getCreator());
		frpb.setYearRate(cb.getYearRate());
		frpb.setRentAdjustList(new ArrayList<String>());// 租金调整值
		
		//先找到总本金值
		String leaseMoney = cb.getCleanLeaseMoney();
		Collections.sort(irrPlans);
		List<String> interest = new ArrayList<String>();
		List<String> corpus = new ArrayList<String>();
		List<String> rentList = new ArrayList<String>();
		List<String> dateList = new ArrayList<String>();
		List<String> year_rate = new ArrayList<String>();
		
		for(FundRentPlanIrr irrPlan : irrPlans){
			interest.add(irrPlan.getBusinessinterest());
			corpus.add(irrPlan.getBusinesscorpus());
			rentList.add(irrPlan.getRent());
			dateList.add(irrPlan.getPlandate());
			year_rate.add(irrPlan.getYearrate()== null || irrPlan.getYearrate().trim().length() <= 0  ? cb.getYearRate() : irrPlan.getYearrate());
		}
		//业务
		frpb.setPlanDateList(dateList);
		frpb.setCorpusBusinessList(corpus);
		frpb.setInterestBusinessList(interest);
		//frpb.setColumn_1(corpus);
		//frpb.setColumn_2(interest);
		frpb.setRentList(rentList);
		frpb.setYearRateList(year_rate);
		List<String> rentListTemp = rentList;
		List<String> rentRemainList = new RentalServiceImpl().getRentRemainList(rentListTemp);
		// 加载本金余额列表
		frpb.setCorpusOverageBusinessList(TransRateHelper.getCorpusOvergeList(cb.getCleanLeaseMoney(), frpb.getCorpusBusinessList()));
		frpb.setAllRemainRentList(rentRemainList);
		RentPlanContrCalDAOImpl rpcd = new RentPlanContrCalDAOImpl();
		rpcd.deleteRentPlan(tcb.getRentPlan_tb(), tcb, startList);
		rpcd.addRentPlan(tcb.getRentPlan_tb(), frpb, tcb, startList);
		
		//String yearRate =cb.getYearRate();
		//String irr = IrrTools.getIRRByEvenInterest(cb, frpb);//获取财务利息
		//cb.setYearRate(irr);
		//财务
		//calFina(cb, tcb, startList, frpb);
		//cb.setYearRate(yearRate);
		Hashtable<String, Object> re_ht = new Hashtable<String, Object>();
		
		//重新计算，租赁期限
		int leaseTerm =  DateUtils.getBetweenMonths(dateList.get(0), dateList.get(dateList.size()-1),"yyyy-MM-dd");
		cb.setLeaseTerm(leaseTerm + 1);
		re_ht = RentCalculateHelper.createFundPlanCashIrr(cb, tcb, fundPlanList, frpb);
		re_ht.put("isSucess", "true");
		logger.info("结束不规则的测算方法:rentCal ..");
		return re_ht;
	}

	@Override
	public Hashtable<String, Object> rentCalOnHire(ConditionBean cb,
			String calType, String userName, String[] rentAdjustList,
			List<FundPlanBean> fundPlanList, List<SpecialRulesBean> srb,
			List<FundRentPlanIrr> irrPlans) throws Exception {
		//  Auto-generated method stub
		Hashtable<String, Object> re_ht = new Hashtable<String, Object>();
		re_ht.put("isSucess", "false");
		re_ht.put("message", "不规则的租金测算起租日不允许变更,如果要修改租金计划中的还款日请在租金计划修改中完成!");
		logger.debug("进行不规则时测算的方法:rentCalOnHire ..");
		return re_ht;
	}

	

}
