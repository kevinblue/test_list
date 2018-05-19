package com.reckon.commons.helper;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundPlanBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.SpecialRuleBean;
import com.reckon.bean.TabCalBean;
import com.reckon.calculation.condition.CalculationConditionImpl;
import com.reckon.calculation.utils.RentCalculateUtil;
import com.reckon.calculation.vo.CalculationCondition;
import com.reckon.calculation.vo.RentPlanInfo;
import com.reckon.commons.base.CashDetail;
import com.reckon.commons.base.Condition;
import com.reckon.commons.base.RentPlan;
import com.reckon.commons.util.DateTools;
import com.reckon.dao.Conn;
import com.reckon.dao.impl.ConditionDAOImpl;
import com.reckon.dao.impl.FundFundChargeDAOImpl;
import com.reckon.dao.impl.IrrDetailsDAOImpl;
import com.reckon.dao.impl.RentPlanContrCalDAOImpl;
import com.reckon.entity.contract.reckon.cash.ContractCashDetail;
import com.reckon.entity.contract.reckon.cash.ContractCashDetailTemp;
import com.reckon.entity.contract.reckon.cash.ContractFinaCashDetailTemp;
import com.reckon.entity.contract.reckon.condition.ContractConditionTemp;
import com.reckon.entity.contract.reckon.condition.FundPutPlan;
import com.reckon.entity.contract.reckon.condition.SpecialRulesBean;
import com.reckon.entity.contract.reckon.fina.ContractFinaRentPlanTemp;
import com.reckon.entity.contract.reckon.fund.ContractFundRentPlanTemp;
import com.reckon.entity.contract.reckon.fund.ContractSpecialRulesBean;
import com.reckon.entity.proj.ProjFundRentPlanTemp;
import com.reckon.rentcalc.service.impl.pub.FundFundChargeServiceImpl;
import com.reckon.rentcalc.service.impl.pub.PlanDateServiceImpl;
import com.reckon.service.FundFundChargePlanService;
import com.reckon.service.impl.BeforeInterestDaoImpl;
import com.reckon.util.DateUtils;
import com.reckon.util.IrrTools;
import com.tenwa.business.entity.User;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.kernal.utils.StringUtil;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.leasing.entity.beforeinterest.BeforeInterestCalProcess;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.cust.quot.CustCashDetail;
import com.tenwa.leasing.entity.cust.quot.CustFundRentPlan;
import com.tenwa.leasing.entity.fund.ContractFundRentPlan;
import com.tenwa.leasing.entity.proj.ProjInfo;
import com.tenwa.leasing.utils.LeasingException;

/**
 * @author MHY QQ:648020894
 */
public class RentCalculateHelper {
	
	@Resource(name = "tableService")
	private static TableService tableService;
	private static Logger logger = Logger.getLogger(RentCalculateHelper.class);

	/**
	 * 2个字符串数组的数字按index相加
	 * 
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public static List<BigDecimal> listAddS(List<BigDecimal> arr1,
			List<BigDecimal> arr2) {
		List<BigDecimal> result = new ArrayList<BigDecimal>();
		if (arr1 != null) {
			result.addAll(arr1);
			if (arr2 != null) {
				for (int i = 0; i < arr2.size(); i++) {
					if (i < result.size()) {
						result.set(i, result.get(i).add(arr2.get(i)));
					} else {
						result.add(arr2.get(i));
					}
				}
			}
		} else if (arr2 != null) {
			result.addAll(arr2);
		}
		return result;
	}

	/**
	 * 2个数组的数字按index相加
	 * 
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public static List<BigDecimal> listAdd(List<BigDecimal> arr1,
			List<BigDecimal> arr2) {
		List<BigDecimal> result = new ArrayList<BigDecimal>();
		if (arr1 != null) {
			result.addAll(arr1);
			if (arr2 != null) {
				for (int i = 0; i < arr2.size(); i++) {
					if (i < result.size()) {
						result.set(i, result.get(i).add(arr2.get(i)));
					} else {
						result.add(arr2.get(i));
					}
				}
			}
		} else if (arr2 != null) {
			result.addAll(arr2);
		}
		return result;
	}

	/**
	 * 2个字符串数组的数字按index相减
	 * 
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public static List<BigDecimal> listSubS(List<BigDecimal> array,
			List<BigDecimal> arrSub) {
		List<BigDecimal> result = new ArrayList<BigDecimal>();
		if (array != null) {
			result.addAll(array);
			if (arrSub != null) {
				for (int i = 0; i < arrSub.size(); i++) {
					if (i < result.size()) {
						result.set(i, result.get(i).subtract(arrSub.get(i)));
					} else {
						result.add(BigDecimal.ZERO.subtract(arrSub.get(i)));
					}
				}
			}
		}
		return result;
	}

	/**
	 * 2个数组的数字按index相减
	 * 
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public static List<BigDecimal> listSub(List<BigDecimal> array,
			List<BigDecimal> arrSub) {
		List<BigDecimal> result = new ArrayList<BigDecimal>();
		if (array != null) {
			result.addAll(array);
			if (arrSub != null) {
				for (int i = 0; i < arrSub.size(); i++) {
					if (i < result.size()) {
						result.set(i, result.get(i).subtract(arrSub.get(i)));
					} else {
						result.add(BigDecimal.ZERO.subtract(arrSub.get(i)));
					}
				}
			}
		}
		return result;
	}

	/**
	 * 根据总本金和本金列表，修正最后一期的本金
	 * 
	 * @param oldCorpuTotal
	 * @param newCorpusList
	 */
	public static void adjustLastCorpus(BigDecimal oldCorpuTotal,
			List<BigDecimal> rentList) {
		BigDecimal oldCorpus = oldCorpuTotal;
		for (BigDecimal rent : rentList) {
			oldCorpus = oldCorpus.subtract(rent);
		}
		BigDecimal lastCorpus = rentList.get(rentList.size() - 1);
		lastCorpus = lastCorpus.add(oldCorpus);
		rentList.set(rentList.size() - 1, lastCorpus);
	}

	/**
	 * 根据日期找到日期所在期次
	 * 
	 * @param rentPlanList
	 * @param adjustStartDate
	 * @return
	 */
	public static int getStartListFromRentPlan(
			List<? extends RentPlan> rentPlanList, String adjustStartDate) {
		try {
			Collections.sort(rentPlanList);
			for (RentPlan rp : rentPlanList) {
				long diff = DateTools.getDateDiff(rp.getPlanDate(),
						adjustStartDate);
				if (diff <= 0) {
					return rp.getRentList().intValue();
				}
			}
		} catch (Exception e) {
			logger.error("日期转化错误", e);
		}
		return 0;
	}

	/**
	 * 根据总本金和本金列表获取每期的剩余本金
	 * 
	 * @param leaseMoney
	 *            总的测算本金
	 * @param corpusList
	 *            每一期的本金
	 * @return
	 */
	public static List<BigDecimal> getCorpusOvergeList(BigDecimal leaseMoney,
			List<BigDecimal> corpusList) {
		BigDecimal total = leaseMoney;// 累积每一期的本金
		List<BigDecimal> corpusOvergeList = new ArrayList<BigDecimal>();
		for (int i = 0; i < corpusList.size(); i++) {
			corpusOvergeList.add(total);
			total = total.subtract(corpusList.get(i));
		}
		return corpusOvergeList;
	}

	/**
	 * 根据商务条件和租金计划获取IRR
	 * 
	 * @param cb
	 * @param oldRentPlanContext
	 * @return
	 */
	public static <T extends RentPlan> BigDecimal getPlanIrrFromRentPlan(
			Condition cb, List<T> rentPlan) {
		List<BigDecimal> rentList = new ArrayList<BigDecimal>();
		for (int i = 0; i < rentPlan.size(); i++) {
			rentList.add(rentPlan.get(i).getRent());
		}
		if (cb.getPeriodType().toString().contains("0")) {
			BigDecimal rent0 = rentList.get(0).subtract(cb.getLeaseMoney());
			rentList.set(0, rent0);
		} else if (cb.getPeriodType().toString().contains("1")) {
			rentList.add(0, BigDecimal.ZERO.subtract(cb.getLeaseMoney()));
		}
		BigDecimal irr = IRRCalculateUtil.getIRR(rentList);
		irr = irr.multiply(new BigDecimal(cb.getIncomeNumberYear())).multiply(
				new BigDecimal(100));
		return irr.setScale(Scale.RATE_SCALE - 2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 根据现金流获取IRR
	 * 
	 * @param cashFlowList
	 * @return
	 */
	public static <T extends CashDetail> BigDecimal getRealIrrFromCashDetail(
			Condition cb, List<T> cashDetail) {
		List<BigDecimal> cashFlow = new ArrayList<BigDecimal>();
		for (CashDetail cdb : cashDetail) {
			cashFlow.add(cdb.getNetFlow());
		}

		BigDecimal irr = IRRCalculateUtil.getIRR(cashFlow);
		irr = irr.multiply(new BigDecimal(cb.getIncomeNumberYear())).multiply(
				new BigDecimal(100));
		return irr.setScale(Scale.RATE_SCALE - 2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 计算总的要还的租金
	 * 
	 * @param rentPlanList
	 *            完整的租金计划
	 * @return
	 */
	public static <T extends RentPlan> BigDecimal getRentTotal(List<T> rentPlan) {
		BigDecimal result = BigDecimal.ZERO;
		if (rentPlan != null) {
			for (RentPlan rpb : rentPlan) {
				result = result.add(rpb.getRent());
			}
		}
		return result;
	}

	/**
	 * 计算总的利息
	 * 
	 * @param rentPlanList
	 *            完整的租金计划
	 * @return
	 */
	public static <T extends RentPlan> BigDecimal getInterestTotal(
			List<T> rentPlan) {
		BigDecimal result = BigDecimal.ZERO;
		if (rentPlan != null) {
			for (RentPlan rpb : rentPlan) {
				result = result.add(rpb.getBusinessInterest());
			}
		}
		return result;
	}

	/**
	 * 填充租金计划中当期以后所有剩余未还的租金
	 * 
	 * @param rentPlanList
	 *            完整的租金计划
	 */
	public static <T extends RentPlan> void fillRentPlanAllRemain(
			List<T> rentPlanList) {
		Collections.sort(rentPlanList);
		BigDecimal rent = BigDecimal.ZERO;
		BigDecimal interest = BigDecimal.ZERO;
		BigDecimal corpus = BigDecimal.ZERO;
		if (rentPlanList != null) {
			RentPlan rb = null;
			for (int i = rentPlanList.size() - 1; i >= 0; i--) {
				rb = rentPlanList.get(i);

				rb.setAllRemainRent(rent);
				rent = rent.add(rb.getRent());

				rb.setAllRemainInterest(interest);
				interest = interest.add(rb.getBusinessInterest());

				rb.setAllRemainCorpus(corpus);
				corpus = corpus.add(rb.getBusinessCorpus());
			}
		}
	}

	/**
	 * 填充租金计划中业务剩余未还的租金
	 * 
	 * @param rentPlanList
	 *            完整的租金计划
	 */
	public static <T extends RentPlan> void fillRentPlanOverage(
			List<T> rentPlanList) {
		for (RentPlan rpb : rentPlanList) {
			if (rpb.getOverageRent() == null) {
				rpb.setOverageRent(rpb.getRent());
			}
			if (rpb.getOverageInterest() == null) {
				rpb.setOverageInterest(rpb.getBusinessInterest());
			}
			if (rpb.getOverageCorpus() == null) {
				rpb.setOverageCorpus(rpb.getBusinessCorpus());
			}
		}
	}

	/**
	 * 填充租金计划状态
	 * 
	 * @param rentPlanList
	 *            完整的租金计划
	 */
	public static <T extends RentPlan> void fillRentPlanStatus(
			List<T> rentPlanList) {
		for (RentPlan rpb : rentPlanList) {
			if (rpb.getStatus() == null) {
				rpb.setStatus("未核销");
			}
		}
	}

	/**
	 * 填充租金计划逾期利息
	 * 
	 * @param rentPlanList
	 *            完整的租金计划
	 */
	public static <T extends RentPlan> void fillRentPlanPenalty(
			List<T> rentPlanList) {
		for (RentPlan rpb : rentPlanList) {
			if (rpb.getPenalty() == null) {
				rpb.setPenalty(BigDecimal.ZERO);
			}
			if (rpb.getPenaltyOverage() == null) {
				rpb.setPenaltyOverage(BigDecimal.ZERO);
			}
		}
	}

	/**
	 * 年利率填充
	 * 
	 * @param condition
	 * @param rentPlan
	 * @throws Exception
	 */
	public static <T extends RentPlan> void fillRentPlanCondition(
			Condition condition, List<T> rentPlan) {
		ObjectAssociationUtil.associationConditionForRentPlan(rentPlan,
				condition);
	}

	/**
	 * 年利率填充
	 * 
	 * @param cb
	 * @param rentPlan
	 * @throws Exception
	 */
	public static <T extends RentPlan> void fillRentPlanRate(
			Condition condition, List<T> rentPlan) throws Exception {
		// CalculateCondition cc = new CalculateCondition(condition);
		for (RentPlan rpb : rentPlan) {
			if (rpb.getYearRate() == null) {
				rpb.setYearRate(condition.getYearRate());
			}
			/*
			 * if(rpb.getIssueRate() == null) {
			 * rpb.setIssueRate(cc.getIssueRate()); } if(rpb.getMonthRate() ==
			 * null) { rpb.setRateOfMonth(cc.getMonthRate()); }
			 * if(rpb.getDayRate() == null) { rpb.setRateOfDay(cc.getDayRate());
			 * }
			 */
		}
	}

	/**
	 * 填充每期业务本金余额
	 * 
	 * @param condition
	 * @param rentPlan
	 */
	public static <T extends RentPlan> void fillBusinessCorpusOverage(
			Condition condition, List<T> rentPlan) {
		BigDecimal leaseMoney = condition.getLeaseMoney();
		if (rentPlan != null) {
			RentPlan rb = null;
			for (int i = 0; i < rentPlan.size(); i++) {
				rb = rentPlan.get(i);
				rb.setBusinessCorpusOverage(leaseMoney);
				leaseMoney = leaseMoney.subtract(rb.getBusinessCorpus());
			}
		}
	}

	/**
	 * 填充每期财务本金余额
	 * 
	 * @param condition
	 * @param rentPlan
	 */
	public static <T extends RentPlan> void fillFinanceCorpusOverage(
			Condition condition, List<T> rentPlan) {
		BigDecimal leaseMoney = condition.getLeaseMoney();
		if (rentPlan != null) {
			RentPlan rb = null;
			for (int i = 0; i < rentPlan.size(); i++) {
				rb = rentPlan.get(i);
				rb.setFinanceCorpusOverage(leaseMoney);
				leaseMoney = leaseMoney.subtract(rb.getFinanceCorpus());
			}
		}
	}

	private static void createFacCautionFundFundPlan() {

	}

	/**
	 * 填补空数据的方法，对租金计划其他业务数据进行填充
	 * 
	 * @param cb
	 * @param rentPlan
	 * @throws Exception
	 */
	public static <T extends RentPlan> void fillOtherInfoOfRentPlan(
			Condition condition, List<T> rentPlan) throws Exception {
		Collections.sort(rentPlan);

		// 填充关联信息
		fillRentPlanCondition(condition, rentPlan);

		// 填充年，月，日，期利率
		fillRentPlanRate(condition, rentPlan);

		// 填充残租金，残利息，残本金
		fillRentPlanAllRemain(rentPlan);

		// 填充剩余租金，利息，本金
		fillRentPlanOverage(rentPlan);

		// 填充状态，逾期利息，逾期利息余额
		fillRentPlanStatus(rentPlan);
		fillRentPlanPenalty(rentPlan);
	}

	public static void createCautionFac(FundRentPlanBean planBean,List<FundPlanBean> fundPlanList,CustInfo cust,ConditionBean cb) throws Exception{
		List<String> cautionList = planBean.getCautionmoneyRemainList();
		List<String> planDateList = planBean.getPlanDateList();
		User currentUser = SecurityUtil.getPrincipal();
		String currentTime = DateUtil.getSystemDateTime();
		int i = 1;
		//合同号
		String contractId = (cb.getContractId() == null || cb.getContractId().equals("null")) ? "" :  cb.getContractId();
		//项目号
		String projId = (cb.getProjId() == null || cb.getProjId().equals("null")) ? "" :  cb.getProjId() ;
		String businessId ="";//保证金退还的合同号问题 
		TableService tableService = getTableService();
		if(contractId != null && !contractId.isEmpty()){
			businessId = contractId;
			ContractInfo ci = tableService.findEntityByID(ContractInfo.class, contractId);
			if(ci != null){
				businessId = ci.getContractId();
			}
		} else if(projId != null && !projId.isEmpty()){
			businessId = projId;
			ProjInfo pi = tableService.findEntityByID(ProjInfo.class, projId);
			if(pi != null){
				businessId = pi.getProjId();
			}
		}
		for(int j = 0 ;j < cautionList.size() ; j++){
			String caution = cautionList.get(j);
			if(new BigDecimal(caution).compareTo(BigDecimal.ZERO) > 0){
				FundPlanBean newFundPlan = new FundPlanBean();
				newFundPlan.setPaymentId(i+"");
				newFundPlan.setPayObj(cust.getCustName());
				newFundPlan.setPayCust(cust.getId());
				newFundPlan.setPayCustName(cust.getCustName());
				newFundPlan.setFeeType("fac_feetype11");
				newFundPlan.setFeeTypeName("保证金退还");
				newFundPlan.setContractId(businessId);
				newFundPlan.setPayType("pay_type_out");
				newFundPlan.setPayTypeName("付款");
				newFundPlan.setSettleMethod("payfund6");
				newFundPlan.setSettleMethodName("电汇");
				newFundPlan.setCreator(currentUser.getId());
				newFundPlan.setCreateDate(currentTime);
				newFundPlan.setPlanMoney(caution);
				newFundPlan.setPlanDate(planDateList.get(j));
				fundPlanList.add(newFundPlan);
				i++;
			}
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	public static Hashtable<String, Object> createFundPlanCashIrr(
			ConditionBean cb, TabCalBean tcb, List<FundPlanBean> fundPlanList,
			FundRentPlanBean planBean, Boolean... isAdjust) throws Exception {
		String process = tcb.getCalType();
		// 计算endDate
		String endDate = "";
		List<String> planDateList = planBean.getPlanDateList();
		/*
		 * String periodType = cb.getPeriodType(); //计算租金支付类型 String
		 * incomeNumberYear = cb.getIncomeNumberYear(); Integer addMonth =
		 * 12/Integer.parseInt(incomeNumberYear);//默认为1
		 * if(periodType.equalsIgnoreCase("period_type_1") ||
		 * periodType.equalsIgnoreCase("1")){//代表期初 endDate =
		 * DateUtil.addDate(DateUtil
		 * .getTimeByFormat(planDateList.get(planDateList.size()-1),
		 * "yyyy-MM-dd") , DateUtil.TIME_MONTH, addMonth); }else{//代表期末
		 */
		endDate = planDateList.get(planDateList.size() - 1);
		// }
		cb.setEndDate(endDate);
		Hashtable<String, Object> re_ht = new Hashtable<String, Object>();
		FundFundChargePlanService chargeService = (FundFundChargePlanService) WebUtil
				.getApplicationContext().getBean("fundFundChargePlanService");
		// if(null == fundPlanList || fundPlanList.size()<=0
		// ){//合同起租，租前息会发生改变，所以租金计划重新生成
		Map<String, Object> result = chargeService.createFundPlan(cb, planBean);
		fundPlanList = (List<FundPlanBean>) result.get("fundchargeplan");
		// 根据资金计划，和租金计划，进行资金计划，保证金的抵扣
		CustInfo cust = (CustInfo) result.get("cust");
		/*
		 * if(isAdjust != null && isAdjust.length>0 && isAdjust[0]) { //如果是租金变更，
		 * rentReduceListContainer.clear(); fundPlanList
		 * =splitFundFundPlanForAdjust(planBean, fundPlanList, cust, cb,0,null);
		 * fundPlanList =splitFundFundPlanForAdjust(planBean, fundPlanList,
		 * cust, cb,1,rentReduceListContainer); }else{
		 */
		//国药特殊需求 保证金 和 供应商保证金不分摊抵扣
//		if(!cb.getIsFactory()){
//			rentReduceListContainer.clear();
//			fundPlanList = splitFundFundPlan(planBean, fundPlanList, cust,
//					"feetype17", "feetype16", null);
//			
//			fundPlanList = splitFundFundPlan(planBean, fundPlanList, cust,
//					"feetype19", "feetype18", rentReduceListContainer);
//		}
		// }
		/*
		 * }else{ //第二次测算如果期末余值有值，重新生成期末余值和租前息要重新生成资金计划 Map<String, Object>
		 * result=
		 * chargeService.createFundChargePlan(cb,"feetype9","feetype12");
		 * List<FundPlanBean> newFundPlanList = new ArrayList<FundPlanBean>();
		 * for(FundPlanBean fundPlan : fundPlanList){
		 * if(fundPlan.getFeeType().equalsIgnoreCase("feetype9") ||
		 * fundPlan.getFeeType().equalsIgnoreCase("feetype12")){
		 * newFundPlanList.add(fundPlan); } }
		 * fundPlanList.removeAll(newFundPlanList);
		 * fundPlanList.addAll((List<FundPlanBean
		 * >)result.get("fundchargeplan")); }
		 */
		// 保理测算生成 保证金退还的资金计划
		if(cb.getIsFactory()){
			createCautionFac(planBean, fundPlanList,cust,cb);
		}
		// 向临时表当中插入数据
		FundFundChargeServiceImpl ffc = new FundFundChargeServiceImpl();
		ffc.addFundFundTemp(fundPlanList, cb, tcb);
		// 修改现金流的生成规则，按照租金计划和资金计划生成
		IrrDetailsDAOImpl idd = new IrrDetailsDAOImpl();
		idd.deleteCashDetails(tcb, tcb.getContractCashTb());// 合同现金流明细表
		idd.addCashDetails(tcb, cb);

		// 获取生成的租金计划和现金流，并计算irr
		String hsql = "";
		List rentPlanList = null;
		List cashDetailList = null;

		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("docId", cb.getDocId());
		TableService tableService = getTableService();
		if ("proj_process".equals(process)) {
			queryParams.put("projId", cb.getProjId());
			rentPlanList = tableService.findEntityByProperties(
					ProjFundRentPlanTemp.class, queryParams);
			hsql = " FROM   com.reckon.entity.proj.ProjCashDetailTemp where projId = '"
					+ cb.getProjId()
					+ "' AND docId = '"
					+ cb.getDocId()
					+ "' order by onhireId ";
			cashDetailList = tableService.findResultsByHSQL(hsql);
		} else if ("quoted_price".equals(process)) {
			re_ht.put("docid", cb.getDocId());
			queryParams.put("custId", cb.getCustId());
			rentPlanList = tableService.findEntityByProperties(
					CustFundRentPlan.class, queryParams);
			cashDetailList = tableService.findEntityByProperties(
					CustCashDetail.class, queryParams);
		} else {
			queryParams.put("contractId", cb.getProjId());
			rentPlanList = tableService.findEntityByProperties(
					ContractFundRentPlanTemp.class, queryParams);
			hsql = " FROM  ContractCashDetailTemp where contractId = '"
					+ cb.getProjId() + "' AND docId = '" + cb.getDocId()
					+ "' order by onhireId ";
			cashDetailList = tableService.findResultsByHSQL(hsql);
			// 如果类型是租金计算器
			if (process.equals("rent_calculator")) {
				// 将生成的临时表数据删除掉
				// 删除商务报价
				ConditionDAOImpl cd = new ConditionDAOImpl();
				cd.deleteCondition(tcb, cb);
				// 删除租金计划
				RentPlanContrCalDAOImpl rpcd = new RentPlanContrCalDAOImpl();
				rpcd.deleteRentPlan(tcb.getRentPlan_tb(), tcb, 1);
				// 删除资金计划
				FundFundChargeDAOImpl ffd = new FundFundChargeDAOImpl();
				ffd.deleteFundFundCharge(cb, tcb);
				idd.deleteCashDetails(tcb, tcb.getContractCashTb());// 删除现金流
			}
		}
		List<FundPlanBean> fundList=new ArrayList<FundPlanBean>();
		for(FundPlanBean fb:fundPlanList){//天信特殊需求，租前息和资金计划分开存放
			if(!"feetype10".equals(fb.getFeeType())&&!"feetype9".equals(fb.getFeeType())){
				fundList.add(fb);
			}
		}
		Collections.sort(rentPlanList);
		Collections.sort(cashDetailList);
		Collections.sort(fundPlanList);
		
		// 根据现金流计算Irr
		String xirr = IrrTools.getIrr(cashDetailList, process,true);
		String irr = IrrTools.getIrr(cashDetailList, process);
		cb.setIrr(irr);
		cb.setXirr(xirr);
		re_ht.put("enddate", cb.getEndDate());
		re_ht.put("irr", irr);
		re_ht.put("xirr", xirr);
		re_ht.put("fundchargeplan", fundList);
		re_ht.put("rentPlanList", rentPlanList);
		re_ht.put("cashDetailList", cashDetailList);
		return re_ht;
	}
	@SuppressWarnings({ "unchecked" })
	public static Hashtable<String, Object> createFactoringFundPlanCashIrr(
			ConditionBean cb, TabCalBean tcb, List<FundPlanBean> fundPlanList,
			FundRentPlanBean planBean, Boolean... isAdjust) throws Exception {
		String process = tcb.getCalType();
		// 计算endDate
		String endDate = "";
		List<String> planDateList = planBean.getPlanDateList();
		endDate = planDateList.get(planDateList.size() - 1);
		// }
		cb.setEndDate(endDate);
		Hashtable<String, Object> re_ht = new Hashtable<String, Object>();
		FundFundChargePlanService chargeService = (FundFundChargePlanService) WebUtil
				.getApplicationContext().getBean("fundFundChargePlanService");
		// if(null == fundPlanList || fundPlanList.size()<=0
		// ){//合同起租，租前息会发生改变，所以租金计划重新生成
		Map<String, Object> result = chargeService.createFundPlanFatoring(cb, "");
		fundPlanList = (List<FundPlanBean>) result.get("fundchargeplan");
		// 根据资金计划，和租金计划，进行资金计划，保证金的抵扣
		CustInfo cust = (CustInfo) result.get("cust");
		//国药特殊需求 保证金 和 供应商保证金不分摊抵扣
		if(!cb.getIsFactory()){
			rentReduceListContainer.clear();
			fundPlanList = splitFundFundPlan(planBean, fundPlanList, cust,
					"feetype17", "feetype16", null);
			
			fundPlanList = splitFundFundPlan(planBean, fundPlanList, cust,
					"feetype19", "feetype18", rentReduceListContainer);
		}
		// 保理测算生成 保证金退还的资金计划
		if(cb.getIsFactory()){
			createCautionFac(planBean, fundPlanList,cust,cb);
		}
		// 向临时表当中插入数据
		FundFundChargeServiceImpl ffc = new FundFundChargeServiceImpl();
		ffc.addFundFundTemp(fundPlanList, cb, tcb);
		// 修改现金流的生成规则，按照租金计划和资金计划生成
		IrrDetailsDAOImpl idd = new IrrDetailsDAOImpl();
		idd.deleteCashDetails(tcb, tcb.getContractCashTb());// 合同现金流明细表
		idd.addFctiorngCashDetails(tcb, cb);

		// 获取生成的租金计划和现金流，并计算irr
		String hsql = "";
		List rentPlanList = null;
		List cashDetailList = null;

		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("docId", cb.getDocId());
		TableService tableService = getTableService();
		if ("proj_process".equals(process)) {
			queryParams.put("projId", cb.getProjId());
			rentPlanList = tableService.findEntityByProperties(
					ProjFundRentPlanTemp.class, queryParams);
			hsql = " FROM   com.reckon.entity.proj.ProjCashDetailTemp where projId = '"
					+ cb.getProjId()
					+ "' AND docId = '"
					+ cb.getDocId()
					+ "' order by onhireId ";
			cashDetailList = tableService.findResultsByHSQL(hsql);
		} else if ("quoted_price".equals(process)) {
			re_ht.put("docid", cb.getDocId());
			queryParams.put("custId", cb.getCustId());
			rentPlanList = tableService.findEntityByProperties(
					CustFundRentPlan.class, queryParams);
			cashDetailList = tableService.findEntityByProperties(
					CustCashDetail.class, queryParams);
		} else {
			queryParams.put("contractId", cb.getProjId());
			rentPlanList = tableService.findEntityByProperties(
					ContractFundRentPlanTemp.class, queryParams);
			hsql = " FROM  ContractCashDetailTemp where contractId = '"
					+ cb.getProjId() + "' AND docId = '" + cb.getDocId()
					+ "' order by onhireId ";
			cashDetailList = tableService.findResultsByHSQL(hsql);
			// 如果类型是租金计算器
			if (process.equals("rent_calculator")) {
				// 将生成的临时表数据删除掉
				// 删除商务报价
				ConditionDAOImpl cd = new ConditionDAOImpl();
				cd.deleteCondition(tcb, cb);
				// 删除租金计划
				RentPlanContrCalDAOImpl rpcd = new RentPlanContrCalDAOImpl();
				rpcd.deleteRentPlan(tcb.getRentPlan_tb(), tcb, 1);
				// 删除资金计划
				FundFundChargeDAOImpl ffd = new FundFundChargeDAOImpl();
				ffd.deleteFundFundCharge(cb, tcb);
				idd.deleteCashDetails(tcb, tcb.getContractCashTb());// 删除现金流
			}
		}
		Collections.sort(rentPlanList);
		Collections.sort(cashDetailList);
		Collections.sort(fundPlanList);
	/*	String irr = IrrTools.getIrr(cashDetailList, process,cb);
		// 根据现金流计算Irr
		String xirr = IrrTools.getIrr(cashDetailList, process,cb.getIsFactory());*/
		
		String xirr = IrrTools.getIrr(cashDetailList, process,true);
		String irr = IrrTools.getIrr(cashDetailList, process);
		cb.setIrr(irr);
		cb.setXirr(xirr);
		re_ht.put("irr", irr);
		re_ht.put("xirr", xirr);
		re_ht.put("fundchargeplan", fundPlanList);
		re_ht.put("rentPlanList", rentPlanList);
		re_ht.put("cashDetailList", cashDetailList);
		return re_ht;
	}
	
	

	private static List<BigDecimal> rentReduceListContainer = new ArrayList<BigDecimal>();

	private static List<FundPlanBean> splitFundFundPlan(
			FundRentPlanBean planBean, List<FundPlanBean> fundplans,
			CustInfo cust, String remainType, String reduceType,
			List<BigDecimal> rentReduceList) throws Exception {
		List<String> planDates = planBean.getPlanDateList();
		List<String> rentPlans = planBean.getRentList();
		List<FundPlanBean> newFundPlans = new ArrayList<FundPlanBean>();
		boolean isCache = false;
		if (rentReduceList == null) {
			isCache = true;
		}
		outer: for (int j = 0; j < fundplans.size(); j++) {
			FundPlanBean fundplan = fundplans.get(j);
			if (fundplan.getFeeType().equals(remainType)
					&& fundplan.getPayType().equals("pay_type_out")) {
				fundplan.setPayObj(cust == null ? "租金计算器模拟客户" : cust
						.getCustName());
				fundplan.setPayCust(cust == null ? null : cust.getId());
				fundplan.setPayCustName(cust == null ? "租金计算器模拟客户" : cust
						.getCustName());
				newFundPlans.add(fundplan);
				continue;
			} else if (fundplan.getFeeType().equals(reduceType)
					&& fundplan.getPayType().equals("pay_type_out")) {
				// fundplans.remove(fundplan);
				BigDecimal planMoney = new BigDecimal(fundplan.getPlanMoney());
				int paymentId = 0;
				for (int i = planDates.size() - 1; i >= 0; i--) {
					paymentId++;
					BigDecimal Rent = new BigDecimal(rentPlans.get(i));
					if (rentReduceList != null
							&& rentReduceList.size() > planDates.size() - 1 - i) {
						Rent = Rent.subtract(rentReduceList.get(planDates
								.size() - 1 - i));
					}
					if (Rent.compareTo(BigDecimal.ZERO) <= 0) {
						continue;
					}
					if (planMoney.compareTo(BigDecimal.ZERO) <= 0) {
						continue outer;
					} else {
						if (planMoney.compareTo(Rent) >= 0) {

							planMoney = planMoney.subtract(Rent);
							FundPlanBean newFundPlan = ObjectConvertUtils
									.convertMapToBean(FundPlanBean.class,
											ObjectConvertUtils
													.convertBeanToMap(fundplan));
							newFundPlan.setPlanMoney(Rent.toString());
							if (isCache) {
								rentReduceListContainer.add(Rent);
							}
							newFundPlan.setPlanDate(planDates.get(i));
							newFundPlan.setPaymentId(paymentId + "");
							newFundPlan.setPayObj(cust == null ? "租金计算器模拟客户"
									: cust.getCustName());
							newFundPlan.setPayCust(cust == null ? null : cust
									.getId());
							newFundPlan
									.setPayCustName(cust == null ? "租金计算器模拟客户"
											: cust.getCustName());
							newFundPlans.add(newFundPlan);
						} else {
							FundPlanBean newFundPlan = ObjectConvertUtils
									.convertMapToBean(FundPlanBean.class,
											ObjectConvertUtils
													.convertBeanToMap(fundplan));
							newFundPlan.setPlanMoney(planMoney.toString());
							if (isCache) {
								rentReduceListContainer.add(planMoney);
							}
							newFundPlan.setPlanDate(planDates.get(i));
							newFundPlan.setPaymentId(paymentId + "");
							newFundPlan.setPayObj(cust == null ? "租金计算器模拟客户"
									: cust.getCustName());
							newFundPlan.setPayCust(cust == null ? null : cust
									.getId());
							newFundPlan
									.setPayCustName(cust == null ? "租金计算器模拟客户"
											: cust.getCustName());
							newFundPlans.add(newFundPlan);
							continue outer;
						}
					}
				}
			} else {
				newFundPlans.add(fundplan);
			}
		}
		return newFundPlans;
	}

	/**
	 * 重写拆分 方法，用于租金变更，保证金抵扣的拆分要考虑到租金计划的还款状态
	 * 
	 * @param planBean
	 * @param fundplans
	 * @param custName
	 * @return
	 * @throws Exception
	 */
	private static String[] remainArray = new String[] { "feetype17",
			"feetype19" };
	private static String[] remainNameArray = new String[] { "保证金退还", "厂商保证金退还" };
	private static String[] reduceArray = new String[] { "feetype16",
			"feetype18" };
	private static String[] reduceNameArray = new String[] { "保证金抵扣", "厂商保证金抵扣" };

	private static List<FundPlanBean> splitFundFundPlanForAdjust(
			FundRentPlanBean planBean, List<FundPlanBean> fundplans,
			CustInfo cust, ConditionBean cb, int flag,
			List<BigDecimal> rentReduceList) throws Exception {
		boolean isCache = false;
		if (rentReduceList == null) {
			isCache = true;
		}
		String contractId = cb.getContractId();
		List<String> planDates = planBean.getPlanDateList();
		List<String> rentPlans = planBean.getRentList();
		Conn conn = new Conn();
		String contractIdSql = "select id from  contract_info ci where ci.contract_id = '"
				+ contractId + "'";
		System.out.println(contractIdSql);
		List<Map<String, String>> ids = conn.executeQuery(contractIdSql,
				"租金变更查询");
		if (null != ids && ids.size() > 0) {
			contractId = ids.get(0).get("id");
		}
		String sqlIsAdjust = " select rplan.rent_list, "
				+ " case "
				+ " when income.incomerent is null or income.incomerent = 0 then 'PLANMANYSTATUSNO' "
				+ " else 'OTHERS' END rentstatus"
				+ " from ( "
				+ " SELECT rp.contract_id,rp.rent_list,rp.rent planrent FROM  CONTRACT_FUND_RENT_PLAN rp "
				+ " where rp.contract_id =  '"
				+ contractId
				+ "')rplan "
				+ " left join "
				+ " (select sum(i.rent)incomerent,i.plan_list,i.contract_id from  Contract_Fund_Rent_Income i "
				+ " where i.contract_id = '"
				+ contractId
				+ "' "
				+ "	group by i.contract_id,i.plan_list "
				+ "	)income "
				+ "	on rplan.contract_id = income.contract_id and income.plan_list = rplan.rent_list "
				+ "	ORDER BY rplan.rent_list ASC  ";
		List<Map<String, String>> planInfos = conn.executeQuery(sqlIsAdjust,
				"租金变更查询");
		FundPlanBean fundplanRemain = null;
		BigDecimal planMoney = BigDecimal.ZERO;
		if (flag == 0) {
			planMoney = new BigDecimal(cb.getCautionDeductionMoney());
		} else {
			planMoney = new BigDecimal(cb.getFacCautionDeductionmoney());
		}
		if (planMoney.compareTo(BigDecimal.ZERO) <= 0) {
			return fundplans;
		} else {
			// outer:
			String currentTime = DateUtil.getSystemDateTime();
			User currentUser = SecurityUtil.getPrincipal();
			for (int j = 0; j < fundplans.size(); j++) {
				FundPlanBean fundplan = fundplans.get(j);
				if (fundplan.getFeeType().equals(reduceArray[flag])
						&& fundplan.getPayType().equals("pay_type_out")) {
					fundplans.remove(fundplan);
					contractId = fundplan.getContractId();
				}
				if (fundplan.getFeeType().equals(remainArray[flag])
						&& fundplan.getPayType().equals("pay_type_out")) {
					fundplanRemain = fundplan;
					fundplans.remove(fundplan);
				}
			}
			int paymentId = 0;
			for (int i = planDates.size() - 1; i >= 0; i--) {
				paymentId++;
				BigDecimal Rent = new BigDecimal(rentPlans.get(i));
				if (rentReduceList != null
						&& rentReduceList.size() > planDates.size() - 1 - i) {
					Rent = Rent.subtract(rentReduceList.get(planDates.size()
							- 1 - i));
				}
				if (Rent.compareTo(BigDecimal.ZERO) <= 0) {
					continue;
				}
				if (planMoney.compareTo(BigDecimal.ZERO) <= 0) {
					break;
				} else {
					FundPlanBean newFundPlan = new FundPlanBean();
					if (i + 1 > Integer.parseInt(planInfos.get(
							planInfos.size() - 1).get("rent_list"))) {
						newFundPlan.setPaymentId(paymentId + "");
						newFundPlan.setPayObj(cust.getCustName());
						newFundPlan.setPayCust(cust.getId());
						newFundPlan.setPayCustName(cust.getCustName());
						newFundPlan.setFeeType(reduceArray[flag]);
						newFundPlan.setFeeTypeName(reduceNameArray[flag]);
						newFundPlan.setContractId(contractId);
						newFundPlan.setPayType("pay_type_out");
						newFundPlan.setPayTypeName("付款");
						newFundPlan.setSettleMethod("payfund6");
						newFundPlan.setSettleMethodName("电汇");
						newFundPlan.setCreator(currentUser.getId());
						newFundPlan.setCreateDate(currentTime);
						if (planMoney.compareTo(Rent) >= 0) {
							planMoney = planMoney.subtract(Rent);
							newFundPlan.setPlanMoney(Rent.toString());
							if (isCache) {
								rentReduceListContainer.add(Rent);
							}
							newFundPlan.setPlanDate(planDates.get(i));
							fundplans.add(newFundPlan);
						} else {
							newFundPlan.setPlanMoney(planMoney.toString());
							if (isCache) {
								rentReduceListContainer.add(planMoney);
							}
							newFundPlan.setPlanDate(planDates.get(i));
							fundplans.add(newFundPlan);
							break;
						}
					} else {
						if (planInfos.get(i).get("rentstatus")
								.equals("PLANMANYSTATUSNO")) {
							newFundPlan.setPaymentId(paymentId + "");
							newFundPlan.setPayObj(cust.getCustName());
							newFundPlan.setPayCust(cust.getId());
							newFundPlan.setPayCustName(cust.getCustName());
							newFundPlan.setFeeType(reduceArray[flag]);
							newFundPlan.setFeeTypeName(reduceNameArray[flag]);
							newFundPlan.setContractId(contractId);
							newFundPlan.setPayType("pay_type_out");
							newFundPlan.setPayTypeName("付款");
							newFundPlan.setSettleMethod("payfund6");
							newFundPlan.setSettleMethodName("电汇");
							newFundPlan.setCreator(currentUser.getId());
							newFundPlan.setCreateDate(currentTime);
							if (planMoney.compareTo(Rent) >= 0) {
								planMoney = planMoney.subtract(Rent);
								if (isCache) {
									rentReduceListContainer.add(Rent);
								}
								newFundPlan.setPlanMoney(Rent.toString());
								newFundPlan.setPlanDate(planDates.get(i));
								fundplans.add(newFundPlan);
							} else {
								newFundPlan.setPlanMoney(planMoney.toString());
								if (isCache) {
									rentReduceListContainer.add(planMoney);
								}
								newFundPlan.setPlanDate(planDates.get(i));
								fundplans.add(newFundPlan);
								break;
							}
						} else {
							if (fundplanRemain != null) {
								fundplanRemain.setPlanMoney((new BigDecimal(
										fundplanRemain.getPlanMoney())
										.add(planMoney)).setScale(
										BigDecimal.ROUND_HALF_UP, Scale.TWO)
										.toString());
							} else {
								fundplanRemain = new FundPlanBean();
								newFundPlan.setPaymentId("1");
								newFundPlan.setPayObj(cust.getCustName());
								newFundPlan.setPayCust(cust.getId());
								newFundPlan.setPayCustName(cust.getCustName());
								newFundPlan.setFeeType(remainArray[flag]);
								newFundPlan
										.setFeeTypeName(remainNameArray[flag]);
								newFundPlan.setContractId(contractId);
								newFundPlan.setPayType("pay_type_out");
								newFundPlan.setPayTypeName("付款");
								newFundPlan.setSettleMethod("payfund6");
								newFundPlan.setSettleMethodName("电汇");
								newFundPlan.setCreator(currentUser.getId());
								newFundPlan.setCreateDate(currentTime);
								newFundPlan.setPlanMoney(planMoney.toString());
								newFundPlan.setPlanDate(planDates.get(i));
							}
							break;
						}
					}

				}
			}
			if (fundplanRemain != null) {
				fundplans.add(fundplanRemain);
			}
		}
		return fundplans;
	}

	/**
	 * 把合同正式表中的数据写入到临时表
	 * 
	 * @param contractId
	 * @param docId
	 */
	public static void saveConditionDataToTemp(ContractInfo contractInfo,
			String doc_id) throws Exception {
		String contract_id = contractInfo.getContractId();
		TableService tService = getTableService();
		removeConditionDataFromTemp(contract_id, doc_id, tService);
		{// 1.商务条件
			ContractConditionTemp contractCondition = new ContractConditionTemp();
			Map<String, Object> objectParams = ObjectConvertUtils
					.convertBeanToMap(contractInfo.getContractCondition());
			contractCondition = ObjectConvertUtils.convertMapToBean(
					ContractConditionTemp.class, objectParams);
			contractCondition.setContractId(contract_id);
			contractCondition.setDocId(doc_id);
			contractCondition.setId(null);
			// 写入
			tService.saveEntity(contractCondition);
		}
		{// 2.租金计划
			List<ContractFundRentPlanTemp> contractFundRentPlanTemps = new ArrayList<ContractFundRentPlanTemp>();
			// 复制数据
			for (ContractFundRentPlan temp : contractInfo
					.getContractFundRentPlans()) {
				ContractFundRentPlanTemp contractFundRentPlanTemp = new ContractFundRentPlanTemp();
				Map<String, Object> objectParams = ObjectConvertUtils
						.convertBeanToMap(temp);
				contractFundRentPlanTemp = ObjectConvertUtils.convertMapToBean(
						ContractFundRentPlanTemp.class, objectParams);
				contractFundRentPlanTemp.setContractId(contract_id);
				contractFundRentPlanTemp.setDocId(doc_id);
				contractFundRentPlanTemps.add(contractFundRentPlanTemp);
			}
			// 写入
			tService.saveAllEntities(contractFundRentPlanTemps);
		}
		{// 4.现金流
			// 先删除
			List<ContractCashDetailTemp> contractCashDetailTemps = new ArrayList<ContractCashDetailTemp>();
			// 复制数据
			for (ContractCashDetail temp : contractInfo
					.getContractCashDetails()) {
				ContractCashDetailTemp contractCashDetailTemp = new ContractCashDetailTemp();
				contractCashDetailTemp = ObjectConvertUtils.convertBeanToBean(
						temp, contractCashDetailTemp);
				contractCashDetailTemp.setContractId(contract_id);
				contractCashDetailTemp.setDocId(doc_id);
				contractCashDetailTemps.add(contractCashDetailTemp);
			}
			// 写入
			tService.saveAllEntities(contractCashDetailTemps);
		}
	}

	/**
	 * 删除临时表里的数据
	 * 
	 * @param contract_id
	 * @param doc_id
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public static void removeConditionDataFromTemp(String contract_id,
			String doc_id, TableService tService) throws Exception {
		Map<String, Object> propertiesMap = new HashMap<String, Object>();
		propertiesMap.put("contractId", contract_id);
		propertiesMap.put("docId", doc_id);
		{// 1.商务条件
			// 先删除
			List<ContractConditionTemp> contractConditionTemps = new ArrayList<ContractConditionTemp>();
			contractConditionTemps = tService.findEntityByProperties(
					ContractConditionTemp.class, propertiesMap);
			if (contractConditionTemps != null
					&& contractConditionTemps.size() > 0) {
				tService.removeAllEntites(contractConditionTemps);
			}
		}
		{// 2.租金计划
			// 先删除
			List<ContractFundRentPlanTemp> contractFundRentPlanTemps = new ArrayList<ContractFundRentPlanTemp>();
			contractFundRentPlanTemps = tService.findEntityByProperties(
					ContractFundRentPlanTemp.class, propertiesMap);
			if (contractFundRentPlanTemps != null
					&& contractFundRentPlanTemps.size() > 0) {
				tService.removeAllEntites(contractFundRentPlanTemps);
			}
		}
		{// 3.会计租金计划
			// 先删除
			List<ContractFinaRentPlanTemp> contractFinaRentPlanTemps = new ArrayList<ContractFinaRentPlanTemp>();
			contractFinaRentPlanTemps = tService.findEntityByProperties(
					ContractFinaRentPlanTemp.class, propertiesMap);
			if (contractFinaRentPlanTemps != null
					&& contractFinaRentPlanTemps.size() > 0) {
				tService.removeAllEntites(contractFinaRentPlanTemps);
			}
		}
		{// 4.现金流
			// 先删除
			List<ContractCashDetailTemp> contractCashDetailTemps = new ArrayList<ContractCashDetailTemp>();
			contractCashDetailTemps = tService.findEntityByProperties(
					ContractCashDetailTemp.class, propertiesMap);
			if (contractCashDetailTemps != null
					&& contractCashDetailTemps.size() > 0) {
				tService.removeAllEntites(contractCashDetailTemps);
			}
		}
		{// 5.会计现金流
			// 先删除
			List<ContractFinaCashDetailTemp> contractFinaCashDetailTemps = new ArrayList<ContractFinaCashDetailTemp>();
			contractFinaCashDetailTemps = tService.findEntityByProperties(
					ContractFinaCashDetailTemp.class, propertiesMap);
			if (contractFinaCashDetailTemps != null
					&& contractFinaCashDetailTemps.size() > 0) {
				tService.removeAllEntites(contractFinaCashDetailTemps);
			}
		}
	}

	private static TableService getTableService() {
		return (TableService) WebUtil.getApplicationContext().getBean(
				"tableService");
	}
	
	/**
	 * 等额本金调法  利息不变  调整租金
	 * @param frpb
	 * @param cb
	 * @throws Exception
	 */
	public static void adjustLastListCorpus(FundRentPlanBean frpb,ConditionBean cb) throws Exception{
		List<String> rent=frpb.getRentList();
		List<String> corpus=frpb.getCorpusBusinessList();
		List<String> interest=frpb.getInterestBusinessList();
		BigDecimal totalCorpus=BigDecimal.ZERO;
		for(int i=0;i<corpus.size()-1;i++){
			totalCorpus=totalCorpus.add(new BigDecimal(corpus.get(i)));
		}
		corpus.set(corpus.size()-1, new BigDecimal(cb.getCleanLeaseMoney()).subtract(new BigDecimal(cb.getEquipEndValue())).subtract(totalCorpus).toString());
		if(new BigDecimal(rent.get(rent.size()-1)).subtract(new BigDecimal(corpus.get(corpus.size()-1))).compareTo(BigDecimal.ZERO)<0){
			throw new LeasingException("特殊规则设置不合理！");
		}
		rent.set(rent.size()-1, new BigDecimal(corpus.get(corpus.size()-1)).add(new BigDecimal(interest.get(interest.size()-1))).toString());
		frpb.setCorpusBusinessList(corpus);
		frpb.setInterestBusinessList(interest);
		frpb.setCorpusFinacList(corpus);
		frpb.setInterestFinacList(interest);
	}
	/**
	 * 等额租金调法  租金不变  调整利息
	 * @param frpb
	 * @param cb
	 * @throws Exception
	 */
	public static void adjustLastListInterest(FundRentPlanBean frpb,ConditionBean cb) throws LeasingException{
		List<String> rent=frpb.getRentList();
		List<String> corpus=frpb.getCorpusBusinessList();
		List<String> interest=frpb.getInterestBusinessList();
		BigDecimal totalCorpus=BigDecimal.ZERO;
		for(int i=0;i<corpus.size()-1;i++){
			totalCorpus=totalCorpus.add(new BigDecimal(corpus.get(i)));
		}
		corpus.set(corpus.size()-1, new BigDecimal(cb.getCleanLeaseMoney()).subtract(new BigDecimal(cb.getEquipEndValue())).subtract(totalCorpus).toString());
		if(new BigDecimal(rent.get(rent.size()-1)).subtract(new BigDecimal(corpus.get(corpus.size()-1))).compareTo(BigDecimal.ZERO)<0){
			throw new LeasingException("特殊规则设置不合理！最后一期租金小于本金，利息出现负值！");
		}else if(new BigDecimal(corpus.get(corpus.size()-1)).compareTo(BigDecimal.ZERO)<0){
			throw new LeasingException("特殊规则设置不合理！最后一期租金过大，本金出现负值！");
		}
		interest.set(interest.size()-1, new BigDecimal(rent.get(rent.size()-1)).subtract(new BigDecimal(corpus.get(corpus.size()-1))).toString());
		frpb.setCorpusBusinessList(corpus);
		frpb.setInterestBusinessList(interest);
		frpb.setCorpusFinacList(corpus);
		frpb.setInterestFinacList(interest);
	}
	public static Hashtable<String, Object> createFundPlanCashIrrForSpecial(ConditionBean cb,TabCalBean tcb,List<FundPlanBean> fundPlanList,FundRentPlanBean planBean,List<SpecialRulesBean> srb,Boolean ... isAdjust) throws Exception{
		//计算endDate
		String endDate = "";
		List<String> planDateList = planBean.getPlanDateList();
		String periodType = cb.getPeriodType();
		//计算租金支付类型
		String incomeNumberYear = cb.getIncomeNumberYear();
		Integer addMonth=0;
		if(!cb.getSettleMethod().equals("special_regular")){
			addMonth = 12/Integer.parseInt(incomeNumberYear);//默认为1
		}else{
			addMonth=srb.get(srb.size()-1).getRegularMonths();
		}
		if(periodType.equalsIgnoreCase("period_type_1") || periodType.equalsIgnoreCase("1")){//代表期初
			endDate = DateUtil.addDate(DateUtil.getTimeByFormat(planDateList.get(planDateList.size()-1), "yyyy-MM-dd") , DateUtil.TIME_MONTH, addMonth);
		}else{//代表期末
			endDate = planDateList.get(planDateList.size()-1);
		}
		cb.setEndDate(endDate);
		Hashtable<String, Object> re_ht = new Hashtable<String, Object>();
		String custName = "";
		//if(null ==  fundPlanList  || fundPlanList.size()<=0){
		FundFundChargePlanService chargeService = (FundFundChargePlanService)WebUtil.getApplicationContext().getBean("fundFundChargePlanService");
		Map<String, Object> result= chargeService.createFundPlan(cb,planBean);
		fundPlanList = (List<FundPlanBean>)result.get("fundchargeplan");
		//根据资金计划，和租金计划，进行资金计划，保证金的抵扣
		custName = (String)result.get("custName");
		
		//向临时表当中插入数据
		FundFundChargeServiceImpl  ffc = new FundFundChargeServiceImpl();
		ffc.addFundFundTemp(fundPlanList, cb,tcb);
		//修改现金流的生成规则，按照租金计划和资金计划生成
		IrrDetailsDAOImpl idd = new IrrDetailsDAOImpl();
		idd.deleteCashDetails(tcb, tcb.getContractCashTb());// 合同现金流明细表
		idd.addCashDetails(tcb, cb);
		
		//获取生成的租金计划和现金流，并计算irr
		String hsql = "";
		List  rentPlanList = null;
		List cashDetailList = null;
		String process = tcb.getCalType();
		if(null==process){//客户报价器测算时初始化
			process="proj_process";
			tcb.setCalType(process);
		}
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("docId", cb.getDocId());
		TableService tableService = getTableService();
		if ("proj_process".equals(process)) {
			queryParams.put("projId", cb.getProjId());
			rentPlanList = tableService.findEntityByProperties(ProjFundRentPlanTemp.class, queryParams);
			hsql = " FROM   com.reckon.entity.proj.ProjCashDetailTemp where projId = '"+cb.getProjId()+"' AND docId = '"+cb.getDocId()+"' order by onhireId ";
			cashDetailList = tableService.findResultsByHSQL(hsql);
		}else if ("quoted_price".equals(process)) {
			queryParams.put("custId", cb.getCustId());
			rentPlanList = tableService.findEntityByProperties(CustFundRentPlan.class, queryParams);
			cashDetailList = tableService.findEntityByProperties(CustCashDetail.class, queryParams);
		}else{
			queryParams.put("contractId", cb.getProjId());
			rentPlanList = tableService.findEntityByProperties(ContractFundRentPlanTemp.class, queryParams);
			hsql = " FROM  ContractCashDetailTemp where contractId = '"+cb.getProjId()+"' AND docId = '"+cb.getDocId()+"' order by onhireId ";
			cashDetailList = tableService.findResultsByHSQL(hsql);
		}
		Collections.sort(rentPlanList);
		Collections.sort(cashDetailList);
		Collections.sort(fundPlanList);
		String irr = IrrTools.getIrr(cashDetailList, process);
		String xirr = IrrTools.getIrr(cashDetailList, process,true);
		cb.setIrr(irr);
		re_ht.put("irr", irr);
		re_ht.put("xirr", xirr);
		
		List<FundPlanBean> fpbs=new ArrayList<FundPlanBean>();
		List<FundPlanBean> fundputplan=new ArrayList<FundPlanBean>();
		List<FundPlanBean> graceplan=new ArrayList<FundPlanBean>();
		for(FundPlanBean fb:fundPlanList){
			if(fb.getFeeType().equals("feetype9")){
				graceplan.add(fb);
			}else if(fb.getFeeType().equals("feetype10")){
				fundputplan.add(fb);
			}else{
				fpbs.add(fb);
			}
		}
		
		re_ht.put("fundchargeplan", fpbs);//过滤掉设备款
		re_ht.put("fundputplan", fundputplan);//投放计划
		re_ht.put("graceplan", graceplan);//租前息收款计划
		re_ht.put("rentPlanList", rentPlanList);
		re_ht.put("cashDetailList", cashDetailList);
		return re_ht;
	}
	public static void addBeforeInterest(FundRentPlanBean frpb,ConditionBean cb,List<FundPutPlan> fpps,List<String> plandateList) throws Exception{
		List<String> interestList=new ArrayList<String>();
		List<String> yearrateList=new ArrayList<String>();
		List<String> corpusOverageList=new ArrayList<String>();
		List<String> nousecolum=new ArrayList<String>();
		RentCalculateHelper.addRentBeforeInterest2(fpps, cb,interestList,plandateList);
		for(int i=1;i<=interestList.size();i++){
			yearrateList.add(cb.getGracerate());
			corpusOverageList.add(cb.getCleanLeaseMoney());
			nousecolum.add("0");
		}
		List<String> temp= new ArrayList<String>();
		temp.addAll(interestList);
		temp.addAll(frpb.getInterestBusinessList());
		frpb.setInterestBusinessList(temp);
		frpb.setInterestFinacList(temp);
		
		List<String> temp1= new ArrayList<String>();
		temp1.addAll(interestList);
		temp1.addAll(frpb.getRentList());
		frpb.setRentList(temp1);
		
		List<String> temp2= new ArrayList<String>();
		temp2.addAll(plandateList);
		temp2.addAll(frpb.getPlanDateList());
		frpb.setPlanDateList(temp2);
		
		List<String> temp3= new ArrayList<String>();
		temp3.addAll(nousecolum);
		temp3.addAll(frpb.getAllRemainRentList());
		frpb.setAllRemainRentList(temp3);
		
		List<String> temp4= new ArrayList<String>();
		temp4.addAll(nousecolum);
		temp4.addAll(frpb.getCorpusBusinessList());
		frpb.setCorpusBusinessList(temp4);
		frpb.setCorpusFinacList(temp4);
		
		List<String> temp5= new ArrayList<String>();
		temp5.addAll(corpusOverageList);
		temp5.addAll(frpb.getCorpusOverageBusinessList());
		frpb.setCorpusOverageBusinessList(temp5);
		frpb.setCorpusOverageFinacList(temp5);
		
		List<String> temp6= new ArrayList<String>();
		temp6.addAll(yearrateList);
		temp6.addAll(frpb.getYearRateList());
		frpb.setYearRateList(temp6);
	}
	public static void addRentBeforeInterest2(List<FundPutPlan> rbicblist,ConditionBean cb,List<String> interestList,List<String> plandateList)throws Exception{
		BigDecimal dayrate= new BigDecimal(cb.getGracerate()).multiply(new BigDecimal(cb.getRateType())).divide(new BigDecimal(36000),20,BigDecimal.ROUND_HALF_EVEN).divide(new BigDecimal(360),20,BigDecimal.ROUND_HALF_EVEN);
		BigDecimal tmpdayrate=dayrate;
		List<BigDecimal> temp=new ArrayList<BigDecimal>();
		if(null!=rbicblist&&rbicblist.size()>0){
			Collections.sort(rbicblist);
			for(int j=0;j<plandateList.size();j++){
				String incomeday=plandateList.get(j);
				BigDecimal putMoney=BigDecimal.ZERO;
				BigDecimal interests=BigDecimal.ZERO;
				for(int i=0;i<rbicblist.size();i++){
					putMoney=putMoney.add(rbicblist.get(i).getChangefactmoney()!=null?rbicblist.get(i).getChangefactmoney():rbicblist.get(i).getPlanMoney());
					tmpdayrate=dayrate;
					String funputday=rbicblist.get(i).getChangefactdate()==null?rbicblist.get(i).getPlanDate():rbicblist.get(i).getChangefactdate();
					long days=DateUtils.betweenDays(funputday, incomeday);
					if(days>0){
						if(i==0){
							interests=interests.add(putMoney.multiply(tmpdayrate).multiply(new BigDecimal(days)));
						}else{
							interests=interests.add((rbicblist.get(i).getChangefactmoney()!=null?rbicblist.get(i).getChangefactmoney():rbicblist.get(i).getPlanMoney()).multiply(tmpdayrate).multiply(new BigDecimal(days)));
						}
					}
				}
				temp.add(interests.setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP));
			}
			for(int i=0;i<temp.size();i++){
				if(i==0){
					interestList.add(temp.get(i).toString());
				}else{
					interestList.add(temp.get(i).subtract(temp.get(i-1)).toString());
				}
			}
		}
	}
	public static void calHandMoney(ConditionBean condition,FundRentPlanBean frpb,List<FundPlanBean> ffcpList){
		 Map<String, String> hangMoneyList = new TreeMap<String, String>(
	                new Comparator<String>() {
	                    public int compare(String obj1, String obj2) {
	                    	int dateDiff = DateUtils.getCompareDate(obj1,obj2);
	                        // 升序排序
	                        return dateDiff;
	                    }
	                });
		List<String> planDateList=frpb.getPlanDateList();
		List<String> remainCorpusList=frpb.getCorpusOverageBusinessList();//剩余本金
		String handhz=condition.getHandhz();//手续费收取间隔
		String handmoney=condition.getHandmoney();//手续费计算基数
		String handRatio=condition.getHandratio();//手续费比例
		BigDecimal perHandMoney=BigDecimal.ZERO;
		//hand_hz.01	随还租频率收取
		//hand_hz.02	不规则收取
		
		//hand_ratio.01	融资总额
		//hand_ratio.02	上期剩余本金
		//hand_ratio.03	当期剩余本金
		
		//period_type_1	期初
		//period_type_0	期末
		if("hand_hz.01".equals(handhz)){
			if("hand_ratio.01".equals(handmoney)){
				perHandMoney=new BigDecimal(condition.getCleanLeaseMoney()).multiply(new BigDecimal(handRatio)).divide(new BigDecimal("100"),Scale.TWO,BigDecimal.ROUND_HALF_UP);
				for(int i=0;i<planDateList.size();i++){
					if(i==0&&"period_type_0".equals(condition.getHandtype())){
						hangMoneyList.put(planDateList.get(i), "0");
						continue;
					}
					hangMoneyList.put(planDateList.get(i),perHandMoney.toString());
				}
			}else if("hand_ratio.02".equals(handmoney)){
				for(int i=0;i<remainCorpusList.size();i++){
					if(i==0&&"period_type_0".equals(condition.getHandtype())){
						hangMoneyList.put(planDateList.get(i), "0");
						continue;
					}
					hangMoneyList.put(planDateList.get(i),i==0?condition.getCleanLeaseMoney():remainCorpusList.get(i-1));
				}
			}else{
				for(int i=0;i<remainCorpusList.size();i++){
					if(i==0&&"period_type_0".equals(condition.getHandtype())){
						hangMoneyList.put(planDateList.get(i),"0");
						continue;
					}
					hangMoneyList.put(planDateList.get(i),i==remainCorpusList.size()-1?"0":remainCorpusList.get(i));
				}
			}
		}else{
			perHandMoney=new BigDecimal(condition.getCleanLeaseMoney()).multiply(new BigDecimal(handRatio)).divide(new BigDecimal("100"),Scale.TWO,BigDecimal.ROUND_HALF_UP);
			for(int i=0;i<planDateList.size();i++){
				if(i==0&&"period_type_0".equals(condition.getHandtype())){
					hangMoneyList.put(planDateList.get(i),"0");
					continue;
				}
				hangMoneyList.put(planDateList.get(i),perHandMoney.toString());
				break;
			}
		}
		int i=0;
		for(Entry<String,String> key:hangMoneyList.entrySet()){
			if(new BigDecimal(key.getValue()).compareTo(BigDecimal.ZERO)==0){
				continue;
			}
			FundPlanBean ffcp = new FundPlanBean();
			ffcp.setContractId(null==condition.getContractId()?condition.getProjId():condition.getContractId());
			ffcp.setDocId(condition.getDocId());
			ffcp.setPlanDate(key.getKey());
			ffcp.setFieldName("handlingchargemoney");
			ffcp.setPlanMoney(key.getValue());
			ffcp.setFeeTypeName("手续费");
			ffcp.setFeeType("feetype1");//款项名称
			ffcp.setPayTypeName("收款");
			ffcp.setPayType("pay_type_in");
			ffcp.setPaymentId(String.valueOf(++i));
			ffcp.setSettleMethod("payfund6");
			ffcp.setSettleMethodName("电汇");
			ffcp.setPayObj(condition.getCustname());//客户名称
			ffcp.setPayCustName(condition.getCustname());//客户名称
			ffcpList.add(ffcp);
			
		}
	}
	public static void graceOperate(ConditionBean cb,List<FundPlanBean> ffcpList) throws Exception{
		
		PlanDateServiceImpl pdsi = new PlanDateServiceImpl();
		List<String> plandateList=pdsi.getBeforeInterestDateList(cb);
		List<String> interestList=new ArrayList<String>();
		RentCalculateHelper.addRentBeforeInterest2(cb.getFpps(), cb, interestList, plandateList);
		
		
		for(int i=0;i<interestList.size();i++){
			if(new BigDecimal(interestList.get(i)).compareTo(BigDecimal.ZERO)==0){
				continue;
			}
			FundPlanBean ffcp = new FundPlanBean();
			ffcp.setContractId(null==cb.getContractId()?cb.getProjId():cb.getContractId());
			ffcp.setDocId(cb.getDocId());
			ffcp.setPlanDate(plandateList.get(i));
			ffcp.setFieldName("handlingchargemoney");
			ffcp.setPlanMoney(interestList.get(i));
			ffcp.setFeeTypeName("租前息");
			ffcp.setFeeType("feetype9");//款项名称
			ffcp.setPayTypeName("收款");
			ffcp.setPayType("pay_type_in");
			ffcp.setPaymentId(String.valueOf(i+1));
			ffcp.setSettleMethod("payfund6");
			ffcp.setSettleMethodName("电汇");
			ffcp.setPayObj(cb.getCustname());//客户名称
			ffcp.setPayCustName(cb.getCustname());//客户名称
			ffcp.setPayCust(cb.getCustId());
			ffcpList.add(ffcp);
			
		}
		cb.setGrace(interestList.size());
		Collections.sort(ffcpList);
	}
	/**
	 * @author ykl
	 * @param condition
	 * @param frpb
	 * @param ffcpList
	 * 新版手续费测算方式
	 */
	public static void calHandMoneyNew(ConditionBean condition,FundRentPlanBean frpb,List<FundPlanBean> ffcpList){
		 Map<String, String> hangMoneyList = new TreeMap<String, String>(
	                new Comparator<String>() {
	                    public int compare(String obj1, String obj2) {
	                    	int dateDiff = DateUtils.getCompareDate(obj1,obj2);
	                        // 升序排序
	                        return dateDiff;
	                    }
	                });	
//		List<String> remainCorpusList=frpb.getCorpusOverageBusinessList();//剩余本金
		CalculationCondition conditions = new CalculationConditionImpl();		
		String handmoney=condition.getHandmoney();//手续费计算基数（type）
		String handratio=condition.getHandratio();//手续费收取比例（%）
		String equipamt=condition.getEquipAmt();//设备款，即融资总额
		List<String> remainCorpusList=frpb.getCorpusOverageBusinessList();//剩余本金
		try{
			conditions.copyConditionBeanValues(condition);
			String handhz=condition.getHandhz();//手续费收取间隔
	 		String handtype=condition.getHandtype();//手续续费收取方式
	 		List<String> rentplanDateList=frpb.getPlanDateList();//租金计划日期
	 		String lastDate=rentplanDateList.get(rentplanDateList.size()-1);//租金计划日期（手续费收取日期不能小于最后一次租金收取日期）
	 		List<Date> plandate=null;
	 		if(null!=handhz){
	 		 plandate=getPlanDateList(conditions,handhz,handtype,lastDate);
	 		}
//			BigDecimal perHandMoney=BigDecimal.ZERO;
//			BigDecimal handMoneyBase=BigDecimal.ZERO;//手续费计算基数（金额）
			List<String>handMoneyBaseList=new ArrayList<String>();//手续费本金列表
			List<String>handMoneyBaseListNew=new ArrayList<String>();//
			List<String> plandatelist=new ArrayList<String>();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//将plandate列表格式化成String
			if(null!=plandate){
				for(Date list:plandate){
					String a=sdf.format(list);
					plandatelist.add(a);
				}
			}
			//hand_ratio.01:融资总额
			//hand_ratio.02:上期剩余本金
			//hand_ratio.03:当期剩余本金
			for(String date:plandatelist){
				//手续费按融资总额收取
				if("hand_ratio.01".equals(handmoney)){
					handMoneyBaseList.add(equipamt);
					continue;
				}
				//如果不是，则先按本期剩余本金计算
				//获得当前手续费date的在租金中的期次，以获取当期/上期剩余本金
				int term=0;//默认第一期
				//遍历rentplanDateList
				for(int i=0;i<rentplanDateList.size()-1;i++){
					Date handMoneyDate=new Date(date.replaceAll("-", "/"));
					Date rentDate1=new Date(rentplanDateList.get(i).replaceAll("-", "/"));
					Date rentDate2=new Date(rentplanDateList.get(i+1).replaceAll("-", "/"));
					int compare1=handMoneyDate.compareTo(rentDate1);
					int compare2=handMoneyDate.compareTo(rentDate2);
					//如果相等或者处于两期租金之间，返回
					if(compare1==0||(compare1>0&&compare2<0)){
						term=i;
						break;
					}else if(compare2==0){
						term=i+1;
						break;
					}else if(compare1<0&&compare2<0){//该手续费期次比租金日期列表都要早，则取第一期剩余本金
						term=0;
						break;
					}
				}
				if("hand_ratio.02".equals(handmoney)){
					if(term==0) term=1;
					handMoneyBaseList.add(remainCorpusList.get(term-1));
				}else{
					handMoneyBaseList.add(remainCorpusList.get(term));
				}
			}
			//将handMoneyBaseList数据转移到handMoneyBaseListNew（为了适应上期剩余本金）
			//如果是当期剩余本金，直接复制，如果是上期剩余本金，则改变
			if("hand_ratio.01".equals(handmoney)){
				handMoneyBaseListNew.addAll(handMoneyBaseList);
			}
			if("hand_ratio.02".equals(handmoney)){//如果是上期剩余本金，则将list向前移一位
//				for(int i=0;i<handMoneyBaseList.size();i++){
//				if(i==0){
//					handMoneyBaseListNew.add(handMoneyBaseList.get(0));
//					continue;
//					}
//				handMoneyBaseListNew.add(handMoneyBaseList.get(i-1));
//				}
				handMoneyBaseListNew.addAll(handMoneyBaseList);
			}
			if("hand_ratio.03".equals(handmoney)){//当期剩余本金
				handMoneyBaseListNew.addAll(handMoneyBaseList);
			}
			//算出手续费
			List<String> endmoney=new ArrayList<String>();
			for(String basemoney:handMoneyBaseListNew){
				BigDecimal base=new BigDecimal(basemoney);
				BigDecimal ratio=new BigDecimal(handratio);
				BigDecimal end=base.multiply(ratio).divide(new BigDecimal(100),2, BigDecimal.ROUND_HALF_EVEN);
				//防止出现剩余租金为负导致手续费计算为负值的错误
				if(end.compareTo(BigDecimal.ZERO)<0){
					end=BigDecimal.ZERO;
				}
				endmoney.add(end.toString());
			}
			for(int i=0;i<handMoneyBaseList.size();i++){
				
				FundPlanBean ffcp = new FundPlanBean();
				String contractId=getContractId(condition);
				ffcp.setContractId(contractId);
				ffcp.setDocId(condition.getDocId());
				ffcp.setPlanDate(plandatelist.get(i));
				ffcp.setFieldName("handlingchargemoney");
				ffcp.setPlanMoney(endmoney.get(i));
				ffcp.setFeeTypeName("手续费");
				ffcp.setFeeType("feetype1");//款项名称
				ffcp.setPayTypeName("收款");
				ffcp.setPayType("pay_type_in");
				ffcp.setPaymentId(String.valueOf(i+1));//需要修改
				ffcp.setSettleMethod("payfund6");
				ffcp.setSettleMethodName("电汇");
				ffcp.setPayObj(condition.getCustname());//客户名称
				ffcp.setPayCust(condition.getCustId());
				ffcp.setPayCustName(condition.getCustname());//客户名称
				//如果手续费收取为期初，最后一期手续费不计入(并且不是第一期)
				if("period_type_1".equals(handtype)&&i==handMoneyBaseList.size()-1&&i>0){
					break;
				}
				ffcpList.add(ffcp);				
			}		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	private static String getContractId(ConditionBean condition) throws Exception, Exception {
		String contractId=(condition.getContractId()==null||condition.getContractId()=="")?"":condition.getContractId();
		String projId = (condition.getProjId() == null || condition.getProjId().equals("null")) ? "" :  condition.getProjId() ;
		String custId =  condition.getCustId() == null || condition.getCustId().equals("null") ? "" : condition.getCustId();
		boolean isCustCondition=false;
		String businessId=null;
		CustInfo cust = null;
		TableService tableService = getTableService();
		if(contractId!=null&&!contractId.isEmpty()){
			businessId = contractId;
			ContractInfo ci = tableService.findEntityByID(ContractInfo.class, contractId);
			if(ci != null){
				cust = ci.getCustId(); 
				businessId = ci.getContractId();
			}
		} else if(projId != null && !projId.isEmpty()){
			businessId = projId;
			ProjInfo pi = tableService.findEntityByID(ProjInfo.class, projId);
			if(pi != null){
				cust = pi.getCustInfo(); 
				businessId = pi.getProjId();
			}
		}else{
			isCustCondition = true;
		}
		if(cust == null ){
			cust = tableService.findEntityByID(CustInfo.class, custId);
		}
		if(isCustCondition){
			businessId = cust.getCustName();
		}
		return businessId;
	}

	public static List<Date> getPlanDateList(CalculationCondition condition,String handhz,String handtype,String date) throws Exception {
		List<Date> planDateList = new ArrayList<Date>();
		Date leaseamtdate=condition.getLeaseAmtDate();//付款日
		Date firstPlanDate = condition.getFirstPlanDate();// 计划第1期日期
		Date lastDate=new Date(date.replaceAll("-", "/"));//租金计划中的最后一期
		int num=0;
		if(handhz!=null&&!"".equals(handhz)){
			String s=handhz.substring(handhz.indexOf(".")+1);
			num=Integer.parseInt(s);
		}
		if(num==2){//不规则收取
			if("period_type_1".equals(handtype)){
				planDateList.add(leaseamtdate);
			}else{
				planDateList.add(firstPlanDate);
			}
			
			return planDateList;
		}
		if(num==4){//双月付
			num=2;
		}
		Calendar calendar = Calendar.getInstance();// 推算日期的日历
		
		//设置第一期手续费时间
		String type=null;
		if("period_type_1".equals(handtype)){
			//手续费期初收取，设置第一期手续费收款时间
			planDateList.add(leaseamtdate);//第一次手续费收款日
			type="before";
		}else{	
			type="after";
			//手续费期末收取，设置第一期手续费收款时间
			calendar.setTime(leaseamtdate);
			calendar.add(Calendar.MONTH, num);
			Date tempDate = calendar.getTime();
			while(getDiffMonth(leaseamtdate, tempDate) > num){
				calendar.set(Calendar.DATE, 1);//将日期设为本月第一天
				calendar.add(Calendar.DATE, -1);//-1后获取上个月最后一天
				tempDate = calendar.getTime();
			}
			planDateList.add(tempDate);
		}
		Date lastListDate=planDateList.get(0);//用来获取新的date的基准日期
		Date compareDate=planDateList.get(0);//用来和最后一期租金支付日比较的日期
		//按手续费收取方式添加手续费日期列表，但是不能超过最后一期租金支付日
		
		for(int i=1;compareDate.compareTo(lastDate)<=0;i++){
			calendar.setTime(leaseamtdate);//使用起租日设置各期日期
			Date tempDate;
			if("before".equals(type)){//	如果是期初
				calendar.add(Calendar.MONTH, num*i);
				tempDate = calendar.getTime();
				while(getDiffMonth(leaseamtdate, tempDate) > num*i){
					calendar.set(Calendar.DATE, 1);//将日期设为本月第一天
					calendar.add(Calendar.DATE, -1);//-1后获取上个月最后一天
					tempDate = calendar.getTime();
				}
			}else{
				calendar.add(Calendar.MONTH, num*(i+1));
				tempDate= calendar.getTime();
				while(getDiffMonth(leaseamtdate, tempDate) > num*(i+1)){
					calendar.set(Calendar.DATE, 1);//将日期设为本月第一天
					calendar.add(Calendar.DATE, -1);//-1后获取上个月最后一天
					tempDate = calendar.getTime();
				}
			}		
			compareDate=tempDate;
			calendar.setTime(compareDate);
			calendar.add(Calendar.MONTH, num);
			Date tempDate1 = calendar.getTime();
			while(getDiffMonth(lastListDate, tempDate1) > num*(i+1)){
				calendar.set(Calendar.DATE, 1);//将日期设为本月第一天
				calendar.add(Calendar.DATE, -1);//-1后获取上个月最后一天
				tempDate1 = calendar.getTime();
			}
			compareDate=tempDate1;
			planDateList.add(tempDate);
		}
		return planDateList;
	}
	public static int getDiffMonth(Date beginDate, Date endDate) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(beginDate);
		int beginYear = calendar.get(Calendar.YEAR);
		int beginMonth = calendar.get(Calendar.MONTH);
		calendar.setTime(endDate);
		int endYear = calendar.get(Calendar.YEAR);
		int endMonth = calendar.get(Calendar.MONTH);
		int difMonth = (endYear-beginYear)*12+(endMonth-beginMonth);
		return difMonth;
	}

	public static List<SpecialRuleBean> getSpecialRulesList(List<ContractSpecialRulesBean> srbList) {
		List<SpecialRuleBean> srb = new ArrayList<SpecialRuleBean>();
		for(ContractSpecialRulesBean sb:srbList){
			SpecialRuleBean s = new SpecialRuleBean();
			s.setStartList(sb.getStartList());
			s.setEndList(sb.getEndList());
//			s.setIsinterest(sb.get);
			s.setRegular_months(sb.getRegularMonths()==null?"":sb.getRegularMonths().toString());
			s.setRegular_settlemethod(sb.getRegularSettlemethod()==null?"":sb.getRegularSettlemethod().getId());
			s.setRegular_planmoney(sb.getRegularPlanmoney());
			srb.add(s);
		}
		return srb;
	}
}
