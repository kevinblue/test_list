package com.reckon.commons.helper;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundPlanBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.SpecialRulesBean;
import com.reckon.bean.TabCalBean;
import com.reckon.commons.base.CashDetail;
import com.reckon.commons.base.Condition;
import com.reckon.commons.base.RentPlan;
import com.reckon.commons.util.DateTools;
import com.reckon.dao.Conn;
import com.reckon.dao.impl.IrrDetailsDAOImpl;
import com.reckon.entity.contract.reckon.cash.ContractCashDetail;
import com.reckon.entity.contract.reckon.cash.ContractCashDetailTemp;
import com.reckon.entity.contract.reckon.cash.ContractFinaCashDetailTemp;
import com.reckon.entity.contract.reckon.condition.ContractConditionTemp;
import com.reckon.entity.contract.reckon.fina.ContractFinaRentPlanTemp;
import com.reckon.entity.contract.reckon.fund.ContractFundRentPlanTemp;
import com.reckon.entity.proj.ProjFundRentPlanTemp;
import com.reckon.rentcalc.service.impl.pub.FundFundChargeServiceImpl;
import com.reckon.service.FundFundChargePlanService;
import com.reckon.util.IrrTools;
import com.reckon.util.MoneyUtils;
import com.tenwa.business.entity.User;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.cust.quot.CustCashDetail;
import com.tenwa.leasing.entity.cust.quot.CustFundRentPlan;
import com.tenwa.leasing.entity.fund.ContractFundRentPlan;
import com.tenwa.leasing.utils.LeasingException;

/**
 * @author MHY QQ:648020894
 */
public class RentCalculateHelper {
	
	private static Logger logger = Logger.getLogger(RentCalculateHelper.class);

	/**
	 * 2个字符串数组的数字按index相加
	 * 
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public static List<BigDecimal> listAddS(List<BigDecimal> arr1, List<BigDecimal> arr2) {
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
	public static List<BigDecimal> listAdd(List<BigDecimal> arr1, List<BigDecimal> arr2) {
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
	public static List<BigDecimal> listSubS(List<BigDecimal> array, List<BigDecimal> arrSub) {
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
	public static List<BigDecimal> listSub(List<BigDecimal> array, List<BigDecimal> arrSub) {
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
	public static void adjustLastCorpus(BigDecimal oldCorpuTotal, List<BigDecimal> rentList) {
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
	public static int getStartListFromRentPlan(List<? extends RentPlan> rentPlanList, String adjustStartDate) {
		try {
			Collections.sort(rentPlanList);
			for (RentPlan rp : rentPlanList) {
				long diff = DateTools.getDateDiff(rp.getPlanDate(), adjustStartDate);
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
	public static List<BigDecimal> getCorpusOvergeList(BigDecimal leaseMoney, List<BigDecimal> corpusList) {
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
	public static <T extends RentPlan> BigDecimal getPlanIrrFromRentPlan(Condition cb, List<T> rentPlan) {
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
		irr = irr.multiply(new BigDecimal(cb.getIncomeNumberYear())).multiply(new BigDecimal(100));
		return irr.setScale(Scale.RATE_SCALE - 2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 根据现金流获取IRR
	 * 
	 * @param cashFlowList
	 * @return
	 */
	public static <T extends CashDetail> BigDecimal getRealIrrFromCashDetail(Condition cb, List<T> cashDetail) {
		List<BigDecimal> cashFlow = new ArrayList<BigDecimal>();
		for (CashDetail cdb : cashDetail) {
			cashFlow.add(cdb.getNetFlow());
		}

		BigDecimal irr = IRRCalculateUtil.getIRR(cashFlow);
		irr = irr.multiply(new BigDecimal(cb.getIncomeNumberYear())).multiply(new BigDecimal(100));
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
	public static <T extends RentPlan> BigDecimal getInterestTotal(List<T> rentPlan) {
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
	public static <T extends RentPlan> void fillRentPlanAllRemain(List<T> rentPlanList) {
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
	public static <T extends RentPlan> void fillRentPlanOverage(List<T> rentPlanList) {
		for (RentPlan rpb : rentPlanList) {
			if(rpb.getOverageRent() == null){
				rpb.setOverageRent(rpb.getRent());
			}
			if(rpb.getOverageInterest() == null){
				rpb.setOverageInterest(rpb.getBusinessInterest());
			}
			if(rpb.getOverageCorpus() == null){
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
	public static <T extends RentPlan> void fillRentPlanStatus(List<T> rentPlanList) {
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
	public static <T extends RentPlan> void fillRentPlanPenalty(List<T> rentPlanList) {
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
	public static <T extends RentPlan> void fillRentPlanCondition(Condition condition, List<T> rentPlan) {
		ObjectAssociationUtil.associationConditionForRentPlan(rentPlan, condition);
	}

	/**
	 * 年利率填充
	 * 
	 * @param cb
	 * @param rentPlan
	 * @throws Exception
	 */
	public static <T extends RentPlan> void fillRentPlanRate(Condition condition, List<T> rentPlan) throws Exception {
		//CalculateCondition cc = new CalculateCondition(condition);
		for (RentPlan rpb : rentPlan) {
			if(rpb.getYearRate() == null) {
				rpb.setYearRate(condition.getYearRate());
			}
			/*
			if(rpb.getIssueRate() == null) {
				rpb.setIssueRate(cc.getIssueRate());
			}
			if(rpb.getMonthRate() == null) {
				rpb.setRateOfMonth(cc.getMonthRate());
			}
			if(rpb.getDayRate() == null) {
				rpb.setRateOfDay(cc.getDayRate());
			}
			*/
		}
	}

	/**
	 * 填充每期业务本金余额
	 * 
	 * @param condition
	 * @param rentPlan
	 */
	public static <T extends RentPlan> void fillBusinessCorpusOverage(Condition condition, List<T> rentPlan) {
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
	public static <T extends RentPlan> void fillFinanceCorpusOverage(Condition condition, List<T> rentPlan) {
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
	

	/**
	 * 填补空数据的方法，对租金计划其他业务数据进行填充
	 * 
	 * @param cb
	 * @param rentPlan
	 * @throws Exception
	 */
	public static <T extends RentPlan> void fillOtherInfoOfRentPlan(Condition condition, List<T> rentPlan) throws Exception {
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
	@SuppressWarnings({"unchecked"})
	public static Hashtable<String, Object> createFundPlanCashIrr(ConditionBean cb,TabCalBean tcb,List<FundPlanBean> fundPlanList,FundRentPlanBean planBean,Boolean ... isAdjust) throws Exception{
		//计算endDate
		String endDate = "";
		List<String> planDateList = planBean.getPlanDateList();
		endDate = planDateList.get(planDateList.size()-1);
		cb.setEndDate(endDate);
		Hashtable<String, Object> re_ht = new Hashtable<String, Object>();
		
		List rentPlanList = null;
		String process = tcb.getCalType();
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("docId", cb.getDocId());
		TableService tableService = getTableService();
		if ("proj_process".equals(process)) {
			queryParams.put("projId", cb.getProjId());
			rentPlanList = tableService.findEntityByProperties(ProjFundRentPlanTemp.class, queryParams);
		}else if ("quoted_price".equals(process)) {
			queryParams.put("custId", cb.getCustId());
			rentPlanList = tableService.findEntityByProperties(CustFundRentPlan.class, queryParams);
		}else{
			queryParams.put("contractId", cb.getProjId());
			rentPlanList = tableService.findEntityByProperties(ContractFundRentPlanTemp.class, queryParams);
		}
		Collections.sort(rentPlanList);
		re_ht.put("rentPlanList", rentPlanList);
		return re_ht;
	}
	
	private static List<FundPlanBean> splitFundFundPlan(FundRentPlanBean planBean,List<FundPlanBean> fundplans,CustInfo cust) throws Exception{
		List<String> planDates = planBean.getPlanDateList();
		List<String> rentPlans = planBean.getRentList();
		List<FundPlanBean> newFundPlans = new ArrayList<FundPlanBean>();
		outer:
		for(int j = 0 ; j < fundplans.size() ; j++){
			FundPlanBean fundplan = fundplans.get(j);
			if(fundplan.getFeeType().equals("feetype17") && fundplan.getPayType().equals("pay_type_out")){
				fundplan.setPayObj(cust.getCustName());
				fundplan.setPayCust(cust.getId());
				fundplan.setPayCustName(cust.getCustName());
				newFundPlans.add(fundplan);
				continue;
			}else
			if(fundplan.getFeeType().equals("feetype16") && fundplan.getPayType().equals("pay_type_out")){
				//fundplans.remove(fundplan);
				BigDecimal planMoney =  new BigDecimal(fundplan.getPlanMoney()) ;
				int paymentId = 0;
				for(int i = planDates.size()-1 ; i >= 0 ; i-- ){
					paymentId ++;
					BigDecimal Rent  = new BigDecimal(rentPlans.get(i));
					if(planMoney.compareTo(BigDecimal.ZERO) <= 0 ){
						continue outer;
					}else{
						if(planMoney.compareTo(Rent) >= 0 ){
							planMoney = planMoney.subtract(Rent);
							FundPlanBean newFundPlan  = ObjectConvertUtils.convertMapToBean(FundPlanBean.class, ObjectConvertUtils.convertBeanToMap(fundplan)) ;
							newFundPlan.setPlanMoney(Rent.toString());
							newFundPlan.setPlanDate(planDates.get(i));
							newFundPlan.setPaymentId(paymentId+"");
							newFundPlan.setPayObj(cust.getCustName());
							newFundPlan.setPayCust(cust.getId());
							newFundPlan.setPayCustName(cust.getCustName());
							newFundPlans.add(newFundPlan);
						}else{
							FundPlanBean newFundPlan  = ObjectConvertUtils.convertMapToBean(FundPlanBean.class, ObjectConvertUtils.convertBeanToMap(fundplan)) ;
							newFundPlan.setPlanMoney(planMoney.toString());
							newFundPlan.setPlanDate(planDates.get(i));
							newFundPlan.setPaymentId(paymentId+"");
							newFundPlan.setPayObj(cust.getCustName());
							newFundPlan.setPayCust(cust.getId());
							newFundPlan.setPayCustName(cust.getCustName());
							newFundPlans.add(newFundPlan);
							continue outer;
						}
					}
				}
			}else{
				newFundPlans.add(fundplan);
			}
		}
		return newFundPlans;
	}
	/**
	 * 重写拆分 方法，用于租金变更，保证金抵扣的拆分要考虑到租金计划的还款状态
	 * @param planBean
	 * @param fundplans
	 * @param custName
	 * @return
	 * @throws Exception
	 */
	private static List<FundPlanBean> splitFundFundPlanForAdjust(FundRentPlanBean planBean,List<FundPlanBean> fundplans,CustInfo cust,ConditionBean cb) throws Exception{
		String contractId = cb.getContractId();
		List<String> planDates = planBean.getPlanDateList();
		List<String> rentPlans = planBean.getRentList();
		Conn conn = new Conn();
		String contractIdSql = "select id from  contract_info ci where ci.contract_id = ? ";
		System.out.println(contractIdSql);
		List paramValue=new ArrayList();
		List paramType=new ArrayList();
		paramValue.add(contractId);
		paramType.add(Types.VARCHAR);
		List<Map<String,String>> ids = conn.executeQuery(contractIdSql,paramValue,paramType, "租金变更查询");
		if(null != ids && ids.size() >0){
			contractId = ids.get(0).get("id");
		}
		String sqlIsAdjust = " select rplan.rent_list, "
				+ " case " 
				+ " when income.incomerent is null or income.incomerent = 0 then 'PLANMANYSTATUSNO' "
				+ " else 'OTHERS' END rentstatus"
				+" from ( "
				+" SELECT rp.contract_id,rp.rent_list,rp.rent planrent FROM  CONTRACT_FUND_RENT_PLAN rp "
				+" where rp.contract_id = ?)rplan "
				+" left join "
				+" (select sum(i.rent)incomerent,i.plan_list,i.contract_id from  Contract_Fund_Rent_Income i "
				+" where i.contract_id = ? "
				+ "	group by i.contract_id,i.plan_list "
				+ "	)income "
				+ "	on rplan.contract_id = income.contract_id and income.plan_list = rplan.rent_list "
				+ "	ORDER BY rplan.rent_list ASC  ";
		paramValue=new ArrayList();
	    paramType=new ArrayList();
		paramValue.add(contractId);
		paramType.add(Types.VARCHAR);
		paramValue.add(contractId);
		paramType.add(Types.VARCHAR);
		List<Map<String,String>> planInfos = conn.executeQuery(sqlIsAdjust,paramValue,paramType, "租金变更查询");
		FundPlanBean fundplanRemain = null;
		BigDecimal planMoney =  new BigDecimal(cb.getCautionDeductionMoney()) ;
		if(planMoney.compareTo(BigDecimal.ZERO) <= 0){
			return fundplans;
		}else{
			//outer:
			String currentTime = DateUtil.getSystemDateTime();
			User currentUser =  SecurityUtil.getPrincipal();
			for(int j = 0 ; j < fundplans.size() ; j++){
				FundPlanBean fundplan = fundplans.get(j);
				if(fundplan.getFeeType().equals("feetype16") && fundplan.getPayType().equals("pay_type_out")){
					fundplans.remove(fundplan);
					contractId = fundplan.getContractId();
				}
				if(fundplan.getFeeType().equals("feetype17") && fundplan.getPayType().equals("pay_type_out")){
					fundplanRemain= fundplan;
					fundplans.remove(fundplan);
				}
			}
			int paymentId = 0;
			for(int i = planDates.size()-1 ; i >= 0 ; i-- ){
				paymentId ++;
				BigDecimal Rent  = new BigDecimal(rentPlans.get(i));
				if(planMoney.compareTo(BigDecimal.ZERO) <= 0 ){
					break;
				}else{
					FundPlanBean newFundPlan  =  new FundPlanBean();
					if(i+1 >  Integer.parseInt(planInfos.get(planInfos.size()-1).get("rent_list")) ){
						newFundPlan.setPaymentId(paymentId+"");
						newFundPlan.setPayObj(cust.getCustName());
						newFundPlan.setPayCust(cust.getId());
						newFundPlan.setPayCustName(cust.getCustName());
						newFundPlan.setFeeType("feetype16");
						newFundPlan.setFeeTypeName("保证金抵扣");
						newFundPlan.setContractId(contractId);
						newFundPlan.setPayType("pay_type_out");
						newFundPlan.setPayTypeName("付款");
						newFundPlan.setSettleMethod("payfund6");
						newFundPlan.setSettleMethodName("电汇");
						newFundPlan.setCreator(currentUser.getId());
						newFundPlan.setCreateDate(currentTime);
						if(planMoney.compareTo(Rent) >= 0 ){
							planMoney = planMoney.subtract(Rent);
							newFundPlan.setPlanMoney(Rent.toString());
							newFundPlan.setPlanDate(planDates.get(i));
							fundplans.add(newFundPlan);
						}else{
							newFundPlan.setPlanMoney(planMoney.toString());
							newFundPlan.setPlanDate(planDates.get(i));
							fundplans.add(newFundPlan);
							break;
						}
					}else{
						if(planInfos.get(i).get("rentstatus").equals("PLANMANYSTATUSNO")){
							newFundPlan.setPaymentId(paymentId+"");
							newFundPlan.setPayObj(cust.getCustName());
							newFundPlan.setPayCust(cust.getId());
							newFundPlan.setPayCustName(cust.getCustName());
							newFundPlan.setFeeType("feetype16");
							newFundPlan.setFeeTypeName("保证金抵扣");
							newFundPlan.setContractId(contractId);
							newFundPlan.setPayType("pay_type_out");
							newFundPlan.setPayTypeName("付款");
							newFundPlan.setSettleMethod("payfund6");
							newFundPlan.setSettleMethodName("电汇");
							newFundPlan.setCreator(currentUser.getId());
							newFundPlan.setCreateDate(currentTime);
							if(planMoney.compareTo(Rent) >= 0 ){
								planMoney = planMoney.subtract(Rent);
								newFundPlan.setPlanMoney(Rent.toString());
								newFundPlan.setPlanDate(planDates.get(i));
								fundplans.add(newFundPlan);
							}else{
								newFundPlan.setPlanMoney(planMoney.toString());
								newFundPlan.setPlanDate(planDates.get(i));
								fundplans.add(newFundPlan);
								break;
							}
						}else{
							if(fundplanRemain != null){
								fundplanRemain.setPlanMoney((new BigDecimal(fundplanRemain.getPlanMoney()).add(planMoney)).setScale(BigDecimal.ROUND_HALF_UP,Scale.TWO).toString());
							}else{
								fundplanRemain = new FundPlanBean();
								newFundPlan.setPaymentId("1");
								newFundPlan.setPayObj(cust.getCustName());
								newFundPlan.setPayCust(cust.getId());
								newFundPlan.setPayCustName(cust.getCustName());
								newFundPlan.setFeeType("feetype17");
								newFundPlan.setFeeTypeName("保证金退还");
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
			if(fundplanRemain != null){
				fundplans.add(fundplanRemain);
			}
		}
		return fundplans;
	}
	
	/**
	 * 把合同正式表中的数据写入到临时表
	 * @param contractId
	 * @param docId
	 */
	public static void saveConditionDataToTemp(ContractInfo contractInfo,String doc_id)throws Exception{
		String contract_id=contractInfo.getContractId();
		TableService tService = getTableService();
		removeConditionDataFromTemp( contract_id, doc_id,tService);
		{//1.商务条件
			ContractConditionTemp contractCondition=new ContractConditionTemp();
			Map<String, Object> objectParams = ObjectConvertUtils.convertBeanToMap(contractInfo.getContractCondition());
			contractCondition= ObjectConvertUtils.convertMapToBean(ContractConditionTemp.class, objectParams);
			contractCondition.setContractId(contract_id);
			contractCondition.setDocId(doc_id);
			contractCondition.setId(null);
			//写入
			tService.saveEntity(contractCondition);
		}
		{//2.租金计划
			List<ContractFundRentPlanTemp> contractFundRentPlanTemps=new ArrayList<ContractFundRentPlanTemp>();
			//复制数据
			for(ContractFundRentPlan temp:contractInfo.getContractFundRentPlans()){
				ContractFundRentPlanTemp contractFundRentPlanTemp=new ContractFundRentPlanTemp();
				Map<String, Object> objectParams = ObjectConvertUtils.convertBeanToMap(temp);
				contractFundRentPlanTemp= ObjectConvertUtils.convertMapToBean(ContractFundRentPlanTemp.class, objectParams);
				contractFundRentPlanTemp.setContractId(contract_id);
				contractFundRentPlanTemp.setDocId(doc_id);
				contractFundRentPlanTemps.add(contractFundRentPlanTemp);
			}
			//写入
			tService.saveAllEntities(contractFundRentPlanTemps);
		}
		{//4.现金流
			//先删除
			List<ContractCashDetailTemp> contractCashDetailTemps=new ArrayList<ContractCashDetailTemp>();
			//复制数据
			for(ContractCashDetail temp:contractInfo.getContractCashDetails()){
				ContractCashDetailTemp contractCashDetailTemp=new ContractCashDetailTemp();
				contractCashDetailTemp=ObjectConvertUtils.convertBeanToBean(temp, contractCashDetailTemp);
				contractCashDetailTemp.setContractId(contract_id);
				contractCashDetailTemp.setDocId(doc_id);
				contractCashDetailTemps.add(contractCashDetailTemp);
			}
			//写入
			tService.saveAllEntities(contractCashDetailTemps);
		}
	}
	
	/**
	 * 删除临时表里的数据
	 * @param contract_id
	 * @param doc_id
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public static void removeConditionDataFromTemp(String contract_id, String doc_id,TableService tService)throws Exception {
		Map<String, Object>	propertiesMap=new HashMap<String, Object>();
		propertiesMap.put("contractId", contract_id);
		propertiesMap.put("docId", doc_id);
		{//1.商务条件
			//先删除
			List<ContractConditionTemp> contractConditionTemps=new ArrayList<ContractConditionTemp>();
			contractConditionTemps=tService.findEntityByProperties(ContractConditionTemp.class, propertiesMap);
			if(contractConditionTemps!=null&&contractConditionTemps.size()>0){
				tService.removeAllEntites(contractConditionTemps);
			}
		}
		{//2.租金计划
			//先删除
			List<ContractFundRentPlanTemp> contractFundRentPlanTemps=new ArrayList<ContractFundRentPlanTemp>();
			contractFundRentPlanTemps=tService.findEntityByProperties(ContractFundRentPlanTemp.class, propertiesMap);
			if(contractFundRentPlanTemps!=null&&contractFundRentPlanTemps.size()>0){
				tService.removeAllEntites(contractFundRentPlanTemps);
			}
		}
		{//3.会计租金计划
			//先删除
			List<ContractFinaRentPlanTemp> contractFinaRentPlanTemps=new ArrayList<ContractFinaRentPlanTemp>();
			contractFinaRentPlanTemps=tService.findEntityByProperties(ContractFinaRentPlanTemp.class, propertiesMap);
			if(contractFinaRentPlanTemps!=null&&contractFinaRentPlanTemps.size()>0){
				tService.removeAllEntites(contractFinaRentPlanTemps);
			}
		}
		{//4.现金流
			//先删除
			List<ContractCashDetailTemp> contractCashDetailTemps=new ArrayList<ContractCashDetailTemp>();
			contractCashDetailTemps=tService.findEntityByProperties(ContractCashDetailTemp.class, propertiesMap);
			if(contractCashDetailTemps!=null&&contractCashDetailTemps.size()>0){
				tService.removeAllEntites(contractCashDetailTemps);
			}
		}
		{//5.会计现金流
			//先删除
			List<ContractFinaCashDetailTemp> contractFinaCashDetailTemps=new ArrayList<ContractFinaCashDetailTemp>();
			contractFinaCashDetailTemps=tService.findEntityByProperties(ContractFinaCashDetailTemp.class, propertiesMap);
			if(contractFinaCashDetailTemps!=null&&contractFinaCashDetailTemps.size()>0){
				tService.removeAllEntites(contractFinaCashDetailTemps);
			}
		}
	}
	
	private static TableService getTableService(){
		return (TableService)WebUtil.getApplicationContext().getBean("tableService");
	}
	public static List<SpecialRulesBean> getSpecialRulesList(Map<String, String> modelData) throws JSONException{
		List<SpecialRulesBean> specialRulesBeans = new ArrayList<SpecialRulesBean>();
		if(modelData.containsKey("json_special_regular_str")&&modelData.get("json_special_regular_str").length()>0){
			String jsonStr = modelData.get("json_special_regular_str");
			JSONArray jsonArray = new JSONArray(jsonStr);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				String startlist = jsonObject.optString("startlist");
				String endlist = jsonObject.optString("endlist");
				String regular_settlemethod= jsonObject.optString("regular_settlemethod");
				BigDecimal rate=new BigDecimal(MoneyUtils.getZeroStr(jsonObject.optString("rate").replaceAll(",", "")));
				String regular_months = jsonObject.optString("regular_months");
				SpecialRulesBean obj = new SpecialRulesBean();
				obj.setStartList(Integer.parseInt(startlist));
				obj.setEndList(Integer.parseInt(endlist));
				obj.setRegular_settlemethod(regular_settlemethod);
				obj.setRate(rate);
				obj.setRegular_months(regular_months);
				specialRulesBeans.add(obj);
			}
			return specialRulesBeans;
		}else{
			return null;
		}
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
			addMonth=Integer.parseInt(srb.get(srb.size()-1).getRegular_months());
		}
		endDate = planDateList.get(planDateList.size()-1);
		cb.setEndDate(endDate);
		Hashtable<String, Object> re_ht = new Hashtable<String, Object>();
		
		
		//获取生成的租金计划和现金流，并计算irr
		List  rentPlanList = null;
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
		}else if ("quoted_price".equals(process)) {
			queryParams.put("custId", cb.getCustId());
			rentPlanList = tableService.findEntityByProperties(CustFundRentPlan.class, queryParams);
		}else{
			queryParams.put("contractId", cb.getProjId());
			rentPlanList = tableService.findEntityByProperties(ContractFundRentPlanTemp.class, queryParams);
		}
		Collections.sort(rentPlanList);
		re_ht.put("rentPlanList", rentPlanList);
		return re_ht;
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
			throw new LeasingException("分段规则设置不合理！");
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
	public static void adjustLastListInterest(FundRentPlanBean frpb,ConditionBean cb) throws Exception{
		List<String> rent=frpb.getRentList();
		List<String> corpus=frpb.getCorpusBusinessList();
		List<String> interest=frpb.getInterestBusinessList();
		BigDecimal totalCorpus=BigDecimal.ZERO;
		for(int i=0;i<corpus.size()-1;i++){
			totalCorpus=totalCorpus.add(new BigDecimal(corpus.get(i)));
		}
		corpus.set(corpus.size()-1, new BigDecimal(cb.getCleanLeaseMoney()).subtract(new BigDecimal(cb.getEquipEndValue())).subtract(totalCorpus).toString());
		if(new BigDecimal(rent.get(rent.size()-1)).subtract(new BigDecimal(corpus.get(corpus.size()-1))).compareTo(BigDecimal.ZERO)<0){
			throw new LeasingException("分段规则设置不合理！");
		}
		interest.set(interest.size()-1, new BigDecimal(rent.get(rent.size()-1)).subtract(new BigDecimal(corpus.get(corpus.size()-1))).toString());
		frpb.setCorpusBusinessList(corpus);
		frpb.setInterestBusinessList(interest);
		frpb.setCorpusFinacList(corpus);
		frpb.setInterestFinacList(interest);
	}
}
