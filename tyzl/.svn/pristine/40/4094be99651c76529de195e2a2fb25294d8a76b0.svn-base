package com.reckon.rentcalc.service.impl.eveninterest;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.bean.AdjustBean;
import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.TabCalBean;
import com.reckon.calculation.condition.CalculationConditionImpl;
import com.reckon.calculation.utils.RentCalculateUtil;
import com.reckon.calculation.vo.CalculationCondition;
import com.reckon.commons.helper.Scale;
import com.reckon.dao.impl.RentPlanContrCalDAOImpl;
import com.reckon.rentcalc.service.impl.evenrent.PlanDateServiceImpl;
import com.reckon.rentcalc.service.impl.pub.CorpusServiceImpl;
import com.reckon.rentcalc.service.impl.pub.InterestCalServiceImpl;
import com.reckon.rentcalc.service.impl.pub.RentPlanContrCalServiceImpl;
import com.reckon.rentcalc.service.impl.pub.RentPlanServiceImpl;
import com.reckon.rentcalc.service.impl.pub.RentalServiceImpl;
import com.reckon.renttranrate.service.TransRateHelper;
import com.reckon.util.IrrTools;
import com.reckon.util.RateTools;
import com.reckon.util.RentTools;
import com.reckon.util.tools.NumTools;

import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * 
 * @author SCLICX
 * @version 1.0
 * @copyright (C) Tenwa 2011
 * @date Nov 29, 2011
 * @desc 均息法的租金计划变更实现类
 */
public class EvenInterestPlanContrCalServiceImpl extends RentPlanContrCalServiceImpl {
	static Logger logger = Logger.getLogger(EvenInterestPlanContrCalServiceImpl.class);
	
	private static SimpleDateFormat timeFomart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public FundRentPlanBean rentPlanCalByConditionAndTab(ConditionBean cb, TabCalBean tcb, int startList, FundRentPlanBean frpb) throws Exception {
		
		// 调用均息法下的租金测算算出业务的租金计划，这个方法会把cb的年利率改成财务IRR，后边昨晚测算改回来
		rentPlanEvenInterestCal(cb, frpb);
		// 按照正常的等额租金进行均息法下的第二次租金测算.
		// 2012-09-22做第二次测算时的是要把财务本金赋值给测算本金
		//if (!"reckon".equals(cb.getReckonType())) {
			cb.setCleanLeaseMoney(cb.getCleanLeaseMoney());
		//}
		
		// 因为均息法的时候会改变cb中的year_rate所以要把该值保存下来，
		// 下边要改成财务IRR调等额租金测算，计算财务本金和利息
		String year_rate = cb.getYearRate();
		frpb = calFina(cb, tcb, startList, frpb);//做财务计划测算
		cb.setYearRate(year_rate);//把年利率置回去
		
		return frpb;
	}
	
	/**
	 * 
	 *  (  合同租金测算)
	 * 
	 */
	public FundRentPlanBean calFina(ConditionBean cb, TabCalBean tcb, int startList, FundRentPlanBean frpb) throws Exception {
		
		// 加载利息列表
		InterestCalServiceImpl icsi = new InterestCalServiceImpl();
		List<String> interests = icsi.getInterestList(frpb.getRentList(), cb.getCleanLeaseMoney(), cb.getYearRate(), cb.getPeriodType(), cb.getGrace(), cb.getIncomeNumberYear(), cb.getEquipEndValue());
		frpb.setInterestBusinessList(interests);
		
		// 加载日期列表 (我不理解为什么之前均息法这里不算日期，因此这里把等额租金的计算日期规则直接copy了过来)
		PlanDateServiceImpl pdsi = new PlanDateServiceImpl();
		List<String> dateList = pdsi.getPlanDateList(cb, String.valueOf(frpb.getInterestBusinessList().size()));
		Collections.sort( dateList );
		frpb.setPlanDateList(dateList);
		
		// 加载本金列表
		CorpusServiceImpl csi = new CorpusServiceImpl();
		List<String> corpusList = csi.getCorpusList(frpb.getRentList(), frpb.getInterestBusinessList());
		frpb.setCorpusBusinessList(corpusList);
		
		// 加载调整信息列表
		RentPlanServiceImpl rpsi = new RentPlanServiceImpl();
		rpsi.adjustLastRentPlan(frpb, cb.getCleanLeaseMoney(), cb.getEquipEndValue());
		
		// 加载本金余额列表
		frpb.setCorpusOverageBusinessList(TransRateHelper.getCorpusOvergeList(cb.getCleanLeaseMoney(), frpb.getCorpusBusinessList()));
		
		RentPlanContrCalDAOImpl rpcd = new RentPlanContrCalDAOImpl();
		rpcd.deleteRentPlan(tcb.getRentPlan_tb(), tcb, startList);
		rpcd.addRentPlan(tcb.getRentPlan_tb(), frpb, tcb, startList);
		return frpb;
	}
	
	
	/**
	 * scl  (租金计划变更的不规则租金调整)
	 * 
	 * @param cb
	 *            --
	 * @param tcb
	 *            表信息bean
	 * @param startList
	 *            开始调整期项
	 * @param rentScale
	 *            精确小数位数
	 * @param adb
	 *            AdjustBean 调整bean
	 * @param rentAdjustList
	 *            不规则租金计划调整值数组对象
	 * @return FundRentPlanBean 租金计划
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public FundRentPlanBean rentPlanCalByDevi(ConditionBean cb, TabCalBean tcb, String rentScale, AdjustBean adb, String[] rentAdjustList) throws Exception {
		
		// 查询获取租金计划
		FundRentPlanBean frpb = new RentPlanContrCalDAOImpl().getRentAndDateByTcb(tcb, adb.getStartList());
		// 设置修改信息
		frpb.setModificator(adb.getCreator());
		frpb.setModifyDate(timeFomart.format(new Date()));
		// 将调整列设置到FundRentPlanBean中
		frpb.setRentAdjustList(Arrays.asList(rentAdjustList));
		// 根据表信息进行对表数据的清除操作
		RentPlanContrCalDAOImpl rpcd = new RentPlanContrCalDAOImpl();
		rpcd.deleteRentPlan(tcb.getRentPlan_tb(), tcb, adb.getStartList());

		
		
		
		// 总本金
		BigDecimal bd = new BigDecimal("0");
		for (int i = 0; i < frpb.getCorpusBusinessList().size(); i++) {
			bd = bd.add(new BigDecimal(frpb.getCorpusBusinessList().get(i).toString()));
		}
		// 处理每期年利率
		List<String> year_rate_list = new ArrayList<String>();
		for (int i = 0; i < frpb.getYearRateList().size(); i++) {
			year_rate_list.add(RateTools.getPreRate(frpb.getYearRateList().get(i).toString(), cb.getIncomeNumberYear()));
		}
		cb.setCleanLeaseMoney(bd.toString());
		
		// 2011-07-13修改
		// cb.setPmtEndValue("0");
		cb.setEquipEndValue(cb.getEquipEndValue());
		// 租金类返回租金列表
		RentalServiceImpl rsi = new RentalServiceImpl();
		List<String> rentList = rsi.getDeviRentList(cb, year_rate_list, rentAdjustList);
		frpb.setRentList(rentList);
		
		// 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
		String periodType = "0";// 期初期末设置 1为期初 0为期末
		// 只有当要调整的是第一期,且商务条件设置为期初时不规则租金调整的利息算法才需要把他设置为期初,否则都要规整为期末
		if (adb.getStartList() == 1 && cb.getPeriodType().equals("1")) {
			periodType = "1";
		}
		
		
		
		
		// 利息类返回租金列表
		//***************************************************
		InterestCalServiceImpl icsi = new InterestCalServiceImpl();
		List<String> interests = icsi.getInterestList(frpb.getRentList(), year_rate_list, cb.getCleanLeaseMoney(), RentTools.getInterestAccuracy() + "", periodType);
		frpb.setInterestBusinessList(interests);
		//***************************************************
		
		
		// 本金类返回租金列表
		CorpusServiceImpl csi = new CorpusServiceImpl();
		List<String> corpusList = csi.getCorpusList(frpb.getRentList(), frpb.getInterestBusinessList());
		frpb.setCorpusBusinessList(corpusList);
		
		
		
		// 本金余额类返回租金列表
		frpb.setCorpusOverageBusinessList(TransRateHelper.getCorpusOvergeList(cb.getCleanLeaseMoney(), frpb.getCorpusBusinessList()));
		
		
		// 2011-06-20修改 本金，利息格式化，并调整最后一期的本金值
		RentPlanServiceImpl rpsi = new RentPlanServiceImpl();
		rpsi.adjustLastRentPlan(frpb, cb.getCleanLeaseMoney(), cb.getEquipEndValue());
		
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
		
		List<String> rentRemainList = rsi.getRentRemainList(rentList);
		frpb.setAllRemainRentList(rentRemainList);
		
		// 调用保存的操作
		rpcd.addRentPlan(tcb.getRentPlan_tb(), frpb, tcb, adb.getStartList());
		
		// 返回租金计划实体 bean
		return frpb;
	}

	@Override
	public FundRentPlanBean rentPlanCalByDevi(ConditionBean cb, TabCalBean tcb, int startList, FundRentPlanBean frpb, String[] rentAdjustList) throws Exception {
		// 因为均息法的时候会改变cb中的year_rate所以要把该值保存下来
		String year_rate = cb.getYearRate();
		// 在这之前处理点均息法的下本息调整 然后引用正常的租金调整
		rentPlanEvenInterestCal(cb, frpb, rentAdjustList);
		// 2012-09-22做第二次测算时的是要把财务本金赋值给测算本金
		//if (!cb.getReckonType().equals("reckon")) {
			cb.setCleanLeaseMoney(cb.getCleanLeaseMoney());
		//}
		// 第二次测算则把利率改回去
		cb.setYearRate(year_rate);
		// 如果有宽限期,就要做宽限期下的租金调整
		int grace = cb.getGrace();
		int income_number = cb.getIncomeNumber();
		if (grace > 0) {// 如果有宽限期 就再做一次宽限期里的租金调整
			// Object obj=new Object();
			// 构建租金调整变量
			String temp[] = new String[grace + income_number];
			for (int i = 0; i < temp.length; i++) {
				if (i < grace) {
					temp[i] = frpb.getColumn_2().get(i).toString();
				} else {
					temp[i] = rentAdjustList[i];
				}
			}
			rentAdjustList = temp;
			// 合同租金测算
			// EvenInterestPlanContrCalServiceImpl rpcs = new
			// EvenInterestPlanContrCalServiceImpl();
			// 不规则修改处//////////////////////////
			// 第二次测算则把利率改回去
			// cb.setYear_rate(year_rate);
			// frpb = super.rentPlanCalByDevi(cb, tcb, startList, rentScale,
			// frpb, obj);
		}
		return super.rentPlanCalByDevi(cb, tcb, startList, frpb, rentAdjustList);
	}
	
	

	/**
	 * 
	 *  (  均息法下的 不规则 租金测算,计算出租本息)
	 * 
	 * @param cb
	 *            交易结构bean
	 * @param frpb
	 *            租金计划bean
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public void rentPlanEvenInterestCal(ConditionBean cb, FundRentPlanBean frpb, String[] rentAdjustList) throws Exception {
		// 先按照正常的算出租金计划
		rentPlanEvenInterestCal(cb, frpb);
		// 得到租金调整集合
		List<String> l_rent_adjust = Arrays.asList(rentAdjustList);
		// 2011-11-14 宽限期内租金调整时 调整值就是利息值
		for (int i = 0; i < cb.getGrace(); i++) {
			if (!l_rent_adjust.get(i).equals("")) {
				frpb.getColumn_2().set(i, l_rent_adjust.get(i).toString());
			}
		}
		// 得到现有租金列表
		// 这里不能用bean中的租金列表,因为在rentPlanEvenInterestCal中算过现金流这个值已经被处理过
		List<String> rent_list = new ArrayList<String>();
		// 得到现有本金列表
		List<String> corpus_list = frpb.getColumn_1();
		// 得到现有利息列表
		List<String> interest_list = frpb.getColumn_2();
		String corpus_adjust_total = "0";// 被调整的总本金
		// 根据调整租金计算本息.
		for (int i = 0; i < corpus_list.size(); i++) {
			String rent_temp = l_rent_adjust.get(i).toString();
			if (!rent_temp.equals("")) {
				String corpus_temp = NumTools.formatNumberDoubleScale(Double.parseDouble(rent_temp) - Double.parseDouble(interest_list.get(i).toString()) + "", RentTools.getInterestAccuracy());
				corpus_list.set(i, corpus_temp);
				corpus_adjust_total = NumTools.formatNumberDoubleScale(Double.parseDouble(corpus_adjust_total) + Double.parseDouble(corpus_temp) + "", RentTools.getAccuracy());
			}
		}
		// 用剩余本金做本金均摊
		String corpus_no_adjust = "0";
		String income_number = cb.getIncomeNumber() - RentTools.getAdjustIds(l_rent_adjust, cb.getGrace()).length + "";
		corpus_no_adjust = (new BigDecimal(cb.getCleanLeaseMoney()).subtract(new BigDecimal(cb.getEquipEndValue())).subtract(new BigDecimal(corpus_adjust_total))).divide(new BigDecimal(income_number), RentTools.getAccuracy(), BigDecimal.ROUND_HALF_UP).toString();
		String[] adjus_index = RentTools.getNoAdjustIds(l_rent_adjust, cb.getGrace());
		// 设置未调整的本金
		for (int i = 0; i < adjus_index.length; i++) {
			corpus_list.set(Integer.parseInt(adjus_index[i]), corpus_no_adjust);
		}
		// 调整本息 并返回租金列表
		rent_list = new RentalServiceImpl().getRentListByCond(cb.getCleanLeaseMoney(), cb.getEquipEndValue(), corpus_list, interest_list);
		frpb.setColumn_1(corpus_list);
		frpb.setColumn_2(interest_list);
		frpb.setRentList(rent_list);// 此处设置这个值在后续会被覆盖掉 但是他的值其实是一样的
		// 用均息法下的现金流算的一个irr,把其当做后续测算的年利率
		String irr = IrrTools.getIRRByEvenInterest(cb, frpb);
		cb.setYearRate(irr);
		// 覆盖利率计划用于后续的测算 因为不规则计算需要利率集合
		List<String> year_rate = new ArrayList<String>();
		for (int i = 0; i < frpb.getCorpusBusinessList().size(); i++) {
			year_rate.add(irr);
		}
		frpb.setYearRateList(year_rate);
		logger.info("均息法租金调整之后用于测算的IRR是:" + cb.getYearRate());
	}
	
	
	/**
	 * 
	 *  (  均息法下的租金测算,计算出租本息)
	 * 
	 * @param cb
	 *            交易结构bean
	 * @param frpb
	 *            租金计划bean
	 * @throws Exception 
	 */
public void rentPlanEvenInterestCal(ConditionBean cb, FundRentPlanBean frpb) throws Exception {
		
		//先找到总本金值
		String leaseMoney = cb.getCleanLeaseMoney();
		
		List<String> interest = new InterestCalServiceImpl().getInterestList(leaseMoney, cb.getLeaseTerm(), cb.getIncomeNumber() + cb.getGrace(), cb.getEquipEndValue(), cb.getYearRate());
		List<String> corpus = new CorpusServiceImpl().getCorpusList(cb.getCleanLeaseMoney(), cb.getIncomeNumber(), cb.getEquipEndValue(), cb.getGrace());
		List<String> rentList = new RentalServiceImpl().getRentListByCond(cb.getCleanLeaseMoney(), cb.getEquipEndValue(), corpus, interest);
		List<String> dateList = new PlanDateServiceImpl().getPlanDateList(cb, String.valueOf(frpb.getRentList().size()));
		
		frpb.setPlanDateList(dateList);
		frpb.setColumn_1(corpus);
		frpb.setColumn_2(interest);
		frpb.setRentList(rentList);
		
		List<String> rentListTemp = rentList;
		List<String> rentRemainList = new RentalServiceImpl().getRentRemainList(rentListTemp);
		frpb.setAllRemainRentList(rentRemainList);
		
		String irr = IrrTools.getIRRByEvenInterest(cb, frpb);//获取均息法下的利率
		List<String> year_rate = new ArrayList<String>();
		for (int i = 0; i < frpb.getCorpusBusinessList().size(); i++) {
			year_rate.add(cb.getYearRate());
		}
		cb.setYearRate(irr);
		frpb.setYearRateList(year_rate);
		logger.info("均息法之后用于测算的IRR是:" + cb.getYearRate());
	}
}
