package com.reckon.dao.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.reckon.bean.CashDetailsBean;
import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundPlanBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.dao.Conn;
import com.reckon.util.DateUtils;
import com.reckon.util.tools.DateTools;
import com.tenwa.kernal.utils.ResourceUtil;
import com.tenwa.kernal.utils.UUIDUtil;

/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-2-17
 * @desc ( 公用交易结构dao处理类)
 */

public class FundPlanDAOImpl {
	
	private static Logger logger = Logger.getLogger(FundPlanDAOImpl.class);
	
	//TODO:这几个字段的顺序存在错误
	private static String fundPlanColumns = " CONTRACT_ID,CREATE_DATE,CREATOR_,DOC_ID,FEE_TYPE,FPNOTE,ID,MODIFICATOR_,MODIFY_DATE,PAYMENT_ID,PAY_OBJ,PAY_TYPE,PLAN_DATE,PLAN_MONEY,WORK_FLAG ";
	
	private static String cashDetailColumns = " CONTRACT_ID,CREATE_DATE,CREATOR_,DOC_ID,FUND_IN,FUND_IN_DETAILS,FUND_OUT,FUND_OUT_DETAILS,ID,MODIFICATOR_,MODIFY_DATE,NET_FLOW,ONHIRE_ID,PLAN_DATE,QUOT_ID ";
	
	private static String conditionColumns = " ID,EQUIP_AMT,FIRST_PAYMENT,FIRST_PAYMENT_RATIO,EQUIP_END_VALUE,CLEAN_LEASE_MONEY,FIRST_PAYMENT_TOTAL,CLEAN_CREDIT_MONEY,CLEAN_CREDIT_RATIO,RENT_OR_RATE_VALUE,INCOME_NUMBER,GRACE,LEASE_TERM,RATE_FLOAT_AMT,BASE_RATE,YEAR_RATE,START_DATE,LEASE_AMT_DATE,FIRST_PLAN_DATE,SECOND_PLAN_DATE,HANDLING_CHARGE_MONEY,HANDLING_CHARGE_MONEY_RATIO,MANAGEMENT_MONEY,MANAGEMENT_MONEY_RATIO,CAUTION_MONEY,CAUTION_DEDUCTION_RATIO,CAUTION_DEDUCTION_MONEY,CAUTION_MONEY_REMAIN,INSURE_MONEY,INSURANCE_LESSEE,INSURANCE_LESSOR,NOMINAL_PRICE,BEFORE_INTEREST,OTHER_INCOME,OTHER_EXPENDITURE,RETURN_AMT,PENA_RATE,FREE_DEFA_INTER_DAY,IRR,PLAN_IRR,GROSS_PROFIT,CREATE_DATE,MODIFY_DATE,CREDIT_MONTHS,DC_NUM,CONTRACT_ID,SETTLE_METHOD,RENT_OR_RATE,PERIOD_TYPE,INCOME_NUMBER_YEAR,RATE_FLOAT_TYPE,INSURE_MONEY_TYPE,ADJUST_STYLE,CREATOR_,MODIFICATOR_,DOC_ID,END_DATE ";
	
	private static String rentPlanColumns = " CONTRACT_ID,CORPUS,CORPUS_BUSINESS,CORPUS_OVERAGE,CREATE_DATE,CREATOR_,DOC_ID,ID,INTEREST,INTEREST_BUSINESS,INTEREST_OVERAGE,MODIFICATOR_,MODIFY_DATE,ONHIRE_ID,PENALTY,PENALTY_OVERAGE,PLAN_DATE,QUOT_ID,RENT,ALL_REMAIN_RENT,RENT_ADJUST,RENT_LIST,RENT_OVERAGE,STATUS_,YEAR_RATE ";
	
	private Conn conn = null;
	
	/**
	 * 查询合同租金计划
	 * 
	 * @param contractId
	 * @return
	 */
	public ConditionBean findContractCondition(String contractId){
		Conn conn = new Conn();
		ConditionBean cb = null;
		String sql = "SELECT" + conditionColumns + "FROM CONTRACT_CONDITION WHERE CONTRACT_ID=?";
		try {
			List paramValue=new ArrayList();
		    List paramType=new ArrayList();
		    paramValue.add(contractId);
		    paramType.add(Types.VARCHAR);
			List<Map<String, String>> rs_list = conn.executeQuery(sql,paramValue,paramType, "查询交易结构表信息..");
			if (rs_list.size() > 0) {
				Map<String, String> rs = rs_list.get(0);
				cb = new ConditionBean();
				cb.setAdjustStyle(rs.get("adjust_style"));
				cb.setBeforeInterest(rs.get("before_interest"));
				cb.setCautionDeductionMoney(rs.get("caution_deduction_money"));
				cb.setCautionDeductionRatio(rs.get("caution_deduction_ratio"));
				cb.setCautionMoney(rs.get("caution_money"));
				cb.setCautionMoneyRemain(rs.get("caution_money_remain"));
				cb.setCautionMoneyRatio(rs.get("caution_money_ratio"));
				cb.setCleanLeaseMoney(rs.get("clean_lease_money"));
				cb.setProjId(rs.get("contract_id"));
				cb.setCreateDate(DateTools.getDBDateStr(rs.get("create_date")));
				cb.setCreator(rs.get("creator_"));
				cb.setDocId(rs.get("doc_id"));
				cb.setEquipAmt(rs.get("equip_amt"));
				cb.setEquipEndValue(rs.get("equip_end_value"));
				cb.setFirstPayment(rs.get("first_payment"));
				cb.setFirstPaymentRatio(rs.get("first_payment_ratio"));
				cb.setFirstPaymentTotal(rs.get("first_payment_total"));
				cb.setFirstPlanDate(rs.get("first_plan_date"));
				cb.setFreeDefaInterDay(rs.get("free_defa_inter_day"));
				cb.setHandlingChargeMoney(rs.get("handling_charge_money"));
				cb.setHandlingChargeRatio(rs.get("handling_charge_money_ratio"));
				cb.setId(rs.get("id"));
				cb.setIncomeNumber(Integer.parseInt(rs.get("income_number")));
				cb.setIncomeNumberYear(rs.get("income_number_year"));
				cb.setInsureMoney(rs.get("insure_money"));
				cb.setInsureMoneyType(rs.get("insure_money_type"));
				cb.setIrr(rs.get("irr"));
				cb.setLeaseAmtDate(rs.get("lease_amt_date"));
				cb.setCleanLeaseMoney(rs.get("clean_lease_money"));
				cb.setLeaseTerm(Integer.parseInt(rs.get("lease_term")));
				cb.setManagementMoney(rs.get("management_money"));
				cb.setManagementMoneyRatio(rs.get("management_money_ratio"));
				cb.setModificator(rs.get("modificator"));
				cb.setModifyDate(DateTools.getDBDateStr(rs.get("modify_date")));
				cb.setNominalPrice(rs.get("nominal_price"));
				cb.setOtherExpenditure(rs.get("other_expenditure"));
				cb.setOtherIncome(rs.get("other_income"));
				cb.setPenaRate(rs.get("pena_rate"));
				cb.setPeriodType(rs.get("period_type"));
				cb.setPlanIrr(rs.get("plan_irr"));
				cb.setRateFloatAmt(rs.get("rate_float_amt"));
				cb.setRateFloatType(rs.get("rate_float_type"));
				cb.setReturnAmt(rs.get("return_amt"));
				cb.setSettleMethod(rs.get("settle_method"));
				cb.setStartDate(DateTools.getDBDateStr(rs.get("start_date")));
				cb.setYearRate(rs.get("year_rate"));
				cb.setRentOrRate(rs.get("rent_or_rate"));
				cb.setCreditMonths(rs.get("credit_months"));
				cb.setEndDate(rs.get("end_date"));
				
			}
		} catch (Exception e) {
			logger.error("查询出现错误 ：" + e.getMessage(), e);
		}
		return cb;
	}
	
	/**
	 * 
	 *  (  根据表信息，从数据库中读取租金计划信息，合同的)
	 * 
	 * @param tcb
	 * @return
	 * @throws Exception
	 */
	public FundRentPlanBean findContractFundRentPlanList(String contractId) throws Exception {
		Conn conn = new Conn();
		String sql = "SELECT " + rentPlanColumns + " FROM CONTRACT_FUND_RENT_PLAN WHERE CONTRACT_ID=? ORDER BY PLAN_DATE ASC";
		List paramValue=new ArrayList();
	    List paramType=new ArrayList();
	    paramValue.add(contractId);
	    paramType.add(Types.VARCHAR);
		List<Map<String, String>> rs_list = conn.executeQuery(sql,paramValue,paramType, "查询租金计划sql：");
		FundRentPlanBean frpb = new FundRentPlanBean();
		for (Map<String, String> rs : rs_list) {
			frpb.getPlanDateList().add(DateUtils.date2Str( rs.get("plan_date") ));
			frpb.getYearRateList().add(rs.get("year_rate"));
			frpb.getRentAdjustList().add(rs.get("rent_adjust"));
			
			frpb.getRentList().add(rs.get("rent"));
			frpb.getAllRemainRentList().add(rs.get("all_remain_rent"));
			
			frpb.getCorpusBusinessList().add(rs.get("corpus_business"));
			frpb.getInterestBusinessList().add(rs.get("interest_business"));
			frpb.getCorpusOverageBusinessList().add(rs.get("corpus_overage"));
			
			frpb.getCorpusFinacList().add(rs.get("corpus"));
			frpb.getInterestFinacList().add(rs.get("interest"));
			frpb.getCorpusOverageFinacList().add(rs.get("corpus_overage"));
			
			frpb.getColumn_1().add(rs.get("corpus_business"));
			frpb.getColumn_2().add(rs.get("interest_business"));
		}
		return frpb;
	}
	
	
	/**
	 * 新增合同商务条件到临时表
	 * @param tcb
	 * @param cb
	 * @return
	 * @throws Exception
	 */
	public boolean addContractConditionTemp(ConditionBean cb) throws Exception {
		try {
			conn = (conn == null ? new Conn() : conn);
			
			StringBuffer sqlBuf = new StringBuffer("");
			String sql = "";
			sqlBuf.append(" INSERT INTO CONTRACT_CONDITION_TEMP(" + conditionColumns + ") VALUES(")
			.append("'").append(UUIDUtil.getUUID())
			.append("','").append(cb.getEquipAmt())
			.append("','").append(cb.getFirstPayment())
			.append("','").append(cb.getFirstPaymentRatio())
			.append("','").append(cb.getEquipEndValue())
			.append("','").append(cb.getCleanLeaseMoney())
			.append("','").append(cb.getFirstPaymentTotal())
			.append("','").append(cb.getCleanCreditMoney())
			.append("','").append(cb.getCleanCreditRatio())
			.append("','").append(cb.getRentOrRateValue())
			.append("','").append(cb.getIncomeNumber())
			.append("','").append(cb.getGrace())
			.append("','").append(cb.getLeaseTerm())
			.append("','").append(cb.getRateFloatAmt())
			.append("','").append(cb.getBaseRate())
			.append("','").append(cb.getYearRate())
			.append("','").append(cb.getStartDate())
			.append("','").append(cb.getLeaseAmtDate())
			.append("','").append(cb.getFirstPlanDate())
			.append("','").append(cb.getSecondPlanDate())
			.append("','").append(cb.getHandlingChargeMoney())
			.append("','").append(cb.getHandlingChargeRatio())
			.append("','").append(cb.getManagementMoney())
			.append("','").append(cb.getManagementMoneyRatio())
			.append("','").append(cb.getCautionMoney())
			.append("','").append(cb.getCautionDeductionRatio())
			.append("','").append(cb.getCautionDeductionMoney())
			.append("','").append(cb.getCautionMoneyRemain())
			.append("','").append(cb.getInsureMoney())
			.append("','").append(cb.getInsuranceLessee())
			.append("','").append(cb.getInsuranceLessor())
			.append("','").append(cb.getNominalPrice())
			.append("','").append(cb.getBeforeInterest())
			.append("','").append(cb.getOtherIncome())
			.append("','").append(cb.getOtherExpenditure())
			.append("','").append(cb.getReturnAmt())
			.append("','").append(cb.getPenaRate())
			.append("','").append(cb.getFreeDefaInterDay())
			.append("','").append(cb.getIrr())
			.append("','").append(cb.getPlanIrr())
			.append("','").append(cb.getGrossProfit())
			.append("','").append(cb.getCreateDate())
			.append("','").append(cb.getModifyDate())
			.append("','").append(cb.getCreditMonths())
			.append("','").append(cb.getDcNum())
			.append("','").append(cb.getContractId())
			.append("','").append(cb.getSettleMethod())
			.append("','").append(cb.getRentOrRate())
			.append("','").append(cb.getPeriodType())
			.append("','").append(cb.getIncomeNumberYear())
			.append("','").append(cb.getRateFloatType())
			.append("','").append(cb.getInsureMoneyType())
			.append("','").append(cb.getAdjustStyle())
			.append("','").append(cb.getCreator())
			.append("','").append(cb.getModificator())
			.append("','").append(cb.getDocId())
			.append("','").append(cb.getEndDate())
			.append("')");
			sql = sqlBuf.toString();
			sql = sql.replace("'null'", "null");
			conn.executeUpdate(sql,new ArrayList(),new ArrayList(), "新增临时商务条件...");
		} catch (Exception e) {
			logger.error("新增临时商务条件 ：" + e.getMessage(), e);
		}
		return true;
	}
	
	/**
	 * 查询资金收付计划
	 * @param contractId
	 * @return
	 */
	public List<FundPlanBean> findFundPlanList(String contractId){
		List<FundPlanBean> fundPlanList = new ArrayList<FundPlanBean>();
		try {
			conn = (conn == null ? new Conn() : conn);
			Map<String, String> findDictsOfFeeType = findDictsOfFeeType();
			String sql = "SELECT" + fundPlanColumns + "FROM contract_fund_fund_plan WHERE CONTRACT_ID=?";
			List paramValue=new ArrayList();
		    List paramType=new ArrayList();
		    paramValue.add(contractId);
		    paramType.add(Types.VARCHAR);
			List<Map<String, String>> resultList = conn.executeQuery(sql,paramValue,paramType, "查询资金收付计划...");
			for(Map<String, String> pt : resultList){
				FundPlanBean fundPlan = new FundPlanBean();
				fundPlan.setId(pt.get("id"));
				fundPlan.setContractId(pt.get("contract_id"));
				fundPlan.setDocId(pt.get("oc_id"));
				fundPlan.setFeeType(pt.get("fee_type"));
				fundPlan.setFeeTypeName(findDictsOfFeeType.get(pt.get("fee_type")));
				fundPlan.setPaymentId(pt.get("payment_id"));
				fundPlan.setPayObj(pt.get("pay_obj"));
				fundPlan.setPayType(pt.get("pay_type"));
				fundPlan.setPayTypeName(findDictsOfFeeType.get(pt.get("pay_type")));
				fundPlan.setPlanDate(pt.get("plan_date"));
				fundPlan.setPlanMoney(pt.get("plan_money"));
				fundPlanList.add(fundPlan);
			}
		} catch (Exception e) {
			logger.error("查询出现错误 ：" + e.getMessage(), e);
		}
		return fundPlanList;
	}
	
	
	/**
	 * 资金收付计划中的的与Dicts对应的值
	 * @param conn
	 * @return
	 */
	public Map<String, String> findDictsOfFeeType(){
		Map<String, String> fundPlanList = new HashMap<String, String>();
		try {
			conn = (conn == null ? new Conn() : conn);
			String sql = "SELECT code_,name_ FROM t_dicts_datas WHERE pid_=? or pid_=?";
			List paramValue=new ArrayList();
		    List paramType=new ArrayList();
		    paramValue.add("pay_type");
		    paramType.add(Types.VARCHAR);
		    paramValue.add("FeeType");
		    paramType.add(Types.VARCHAR);
			List<Map<String, String>> resultList = conn.executeQuery(sql,paramValue,paramType, "查询费用类型信息...");
			for(Map<String, String> pt : resultList){
				fundPlanList.put(pt.get("code_"),pt.get("name_"));
			}
		} catch (Exception e) {
			logger.error("查询出现错误 ：" + e.getMessage(), e);
		}
		return fundPlanList;
	}
	
	
	/**
	 * 新增现金流明细数据到TEMP
	 * @param cashFlowList
	 * @return
	 */
	public Map<String, String> saveContractCashDetialTemp(List<CashDetailsBean> cashFlowList){
		Map<String, String> fundPlanList = new HashMap<String, String>();
		try {
			conn = (conn == null ? new Conn() : conn);
			for (CashDetailsBean cdb : cashFlowList) {
				String sql = "insert into contract_cash_detail_temp(" + cashDetailColumns + ") values(";
				sql += "'" + cdb.getContractId();
				sql += "','" + DateTools.getSystemDateTime();
				sql += "','" + cdb.getCreator();
				sql += "','" + cdb.getDocId();
				sql += "','" + cdb.getFundIn();
				sql += "','" + cdb.getFundInDetails();
				sql += "','" + cdb.getFundOut();
				sql += "','" + cdb.getFundOutDetails();
				sql += "','" + UUIDUtil.getUUID();
				sql += "','" + cdb.getModificator();
				sql += "','" + DateTools.getSystemDateTime();
				sql += "','" + cdb.getNetFlow();
				sql += "','"; //ONHIRE_ID TODO
				sql += "','" + cdb.getPlanDate();
				sql += "','" + cdb.getQuotId();
				sql += "')";
				sql = sql.replace("'null'", "null");
				conn.executeUpdate(sql,new ArrayList(),new ArrayList(), "新增现金流信息...");
			}
		} catch (Exception e) {
			logger.error("查询出现错误 ：" + e.getMessage(), e);
		}
		return fundPlanList;
	}


	/**
	 * 删除临时商务条件
	 * @param docId
	 * @param contractId 
	 */
	public void cleanContractConditionTemp(String docId, String contractId) {
		try {
			conn = (conn == null ? new Conn() : conn);
			String sql = "delete from contract_condition_temp where doc_id=? and contract_id=?";
			List paramValue=new ArrayList();
		    List paramType=new ArrayList();
		    paramValue.add(docId);
		    paramType.add(Types.VARCHAR);
		    paramValue.add(contractId);
		    paramType.add(Types.VARCHAR);
			conn.executeUpdate(sql,paramValue,paramType, "删除临时商务条件...");
		} catch (Exception e) {
			logger.error("删除临时商务条件 ：" + e.getMessage(), e);
		}
	}
	
	
	/**
	 * 删除临时租金计划
	 * @param docId
	 * @param contractId 
	 */
	public void cleanContractRentPlanTemp(String docId, String contractId) {
		try {
			conn = (conn == null ? new Conn() : conn);
			String sql = "delete from contract_fund_rent_plan_temp where doc_id=? and contract_id=?";
			List paramValue=new ArrayList();
		    List paramType=new ArrayList();
		    paramValue.add(docId);
		    paramType.add(Types.VARCHAR);
		    paramValue.add(contractId);
		    paramType.add(Types.VARCHAR);
			conn.executeUpdate(sql,paramValue,paramType, "删除临时租金计划...");
		} catch (Exception e) {
			logger.error("删除临时租金计划：" + e.getMessage(), e);
		}
	}
	
	
	/**
	 * 删除临时现金流
	 * @param docId
	 * @param contractId 
	 */
	public void cleanContractCashDetailTemp(String docId, String contractId) {
		try {
			conn = (conn == null ? new Conn() : conn);
			String sql = "delete from contract_cash_detail_temp where doc_id='" + docId + "' and contract_id='" + contractId + "'";
			List paramValue=new ArrayList();
		    List paramType=new ArrayList();
		    paramValue.add(docId);
		    paramType.add(Types.VARCHAR);
		    paramValue.add(contractId);
		    paramType.add(Types.VARCHAR);
			conn.executeUpdate(sql,paramValue,paramType, "删除临时现金流...");
		} catch (Exception e) {
			logger.error("删除临时现金流：" + e.getMessage(), e);
		}
	}
	
	
	
	/**
	 * 
	 *  (  根据表信息，合同租金计划信息 添加合同租金计划信息)
	 * 
	 * @param fpb
	 * @param tcb
	 * @return
	 * @throws Exception
	 */
	public boolean saveRentPlanTempList(FundRentPlanBean fpb) throws Exception {
		try {
			conn = (conn == null ? new Conn() : conn);
			List<String> rentList = fpb.getRentList();// 期项列表
			List<String> rentRemainList = fpb.getAllRemainRentList();// 租金列表
			List<String> planDateList = fpb.getPlanDateList();// 日期列表
			
			List<String> corpusBusinessList = fpb.getCorpusBusinessList();// 本金列表
			List<String> interestBusinessList = fpb.getInterestBusinessList();// 利息列表
			List<String> corpusOverageBusinessList = fpb.getCorpusOverageBusinessList();// 本金余额列表
			List<String> corpusFinacList = fpb.getCorpusFinacList(); // 财务本金列表
			List<String> interestFinacList = fpb.getInterestFinacList(); // 财务利息列表
			List<String> yearRateList = fpb.getYearRateList();//年利率列表
			List<String> rentAdjustList = fpb.getRentAdjustList();// 租金调整列
			//List<String> corpusOverageFinacList = fpb.getCorpusOverageFinacList(); // 财务本金余额列表
			for (int i = 0; i < rentList.size(); i++) {
				StringBuffer sql = new StringBuffer();
				sql.append(" INSERT INTO CONTRACT_FUND_RENT_PLAN_TEMP(" + rentPlanColumns + ") VALUES (" )
				
				.append("'" + fpb.getContractId()+"',")
				.append( (corpusFinacList.get(i).equals("") ? "null" :corpusFinacList.get(i))+",")
				.append((corpusBusinessList.get(i).equals("") ? "null" :corpusBusinessList.get(i))+",")
				.append( (corpusOverageBusinessList.get(i).equals("") ? "null" :corpusOverageBusinessList.get(i))+",")
				.append("'" + fpb.getCreateDate()+"',")
				.append("'" + fpb.getCreator()+"',")
				.append("'" + fpb.getDocId()+"',")
				.append("'" + UUIDUtil.getUUID()+"',")
				.append( (interestFinacList.get(i).equals("") ? "null" :interestFinacList.get(i))+",")
				.append( (interestBusinessList.get(i).equals("") ? "null" :interestBusinessList.get(i))+",")
				.append( (interestBusinessList.get(i).equals("") ? "null" :interestBusinessList.get(i))+",")
				.append("'" + fpb.getModificator()+"',")
				.append("'" + fpb.getModifyDate()+"',")
				.append("'" + fpb.getOnHireId()+"',")
				.append( 0+",")
				.append( 0+",")
				.append("'" + planDateList.get(i).toString()+"',")
				.append("'" + fpb.getQuotId()+"',")
				.append( (rentList.get(i).equals("") ? "null" :rentList.get(i))+",")
				.append( (rentRemainList.get(i).equals("") ? "null" :rentRemainList.get(i))  +",")
				.append(  (rentAdjustList.get(i).equals("") ? "null" :rentAdjustList.get(i))+",")
				//.append(i + 1+",")
				.append(i + 1+",")//这里是租金期项，不应该把租金值插入进去，调息后对比是期项左连接的
				.append( (rentRemainList.get(i).equals("") ? "null" :rentRemainList.get(i))+",")
				.append("'PLANMANYSTATUSNO',")//未收款
				.append(yearRateList.get(i).equals("") ? "null" :yearRateList.get(i)+"")
				.append(")");
				
				String sql1 = sql.toString().replace("'null'", "null");
				conn.executeUpdate(sql1,new ArrayList(),new ArrayList(), "添加临时租金计划...");
			}
		} catch (Exception e) {
			logger.error("添加临时租金计划：" + e.getMessage(), e);
		}
		return true;
	}

	/**
	 *  查询当前合同的上一次调息记录
	 * 
	 * @param contract_id
	 * @param rent_list_start
	 * @return
	 * @throws Exception
	 */
	public String findLastTranRateYearRate(String contractId, int startList) throws Exception {
		try {
			conn = (conn == null ? new Conn() : conn);
			String sql = "SELECT RATE_ADJUST FROM FUND_ADJUST_INTEREST_CONTRACT WHERE CONTRACT_ID=(SELECT ID FROM CONTRACT_INFO WHERE CONTRACT_ID=?) AND START_LIST=? ORDER BY ADJUST_DATE DESC";
			logger.info("查询当前合同在当前期数是否存在多次调息的记录：" + sql);
			List paramValue=new ArrayList();
		    List paramType=new ArrayList();
		    paramValue.add(contractId);
		    paramType.add(Types.VARCHAR);
		    paramValue.add(startList);
		    paramType.add(Types.INTEGER);
			List<Map<String, String>> resultList = conn.executeQuery(sql,paramValue,paramType, "查询历史调息记录...");
			if(resultList != null && resultList.size() > 0){
				return resultList.get(0).get("rate_adjust");
			}
		} catch (Exception e) {
			logger.error("查询历史调息记录时错误：" + e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 查询当前合同某期之后回笼的租金
	 * @param contractId
	 * @param startList
	 * @return
	 * @throws Exception
	 */
	public String findRentIncomeByRentList(String contractId, int startList) throws Exception{
		String rent = "0";
		try {
			conn = (conn == null ? new Conn() : conn);
			String sql = "select nvl(sum(rent),0) rent from contract_fund_rent_income where contract_id=? and plan_list>=?" ;
			List paramValue=new ArrayList();
		    List paramType=new ArrayList();
		    paramValue.add(contractId);
		    paramType.add(Types.VARCHAR);
		    paramValue.add(startList);
		    paramType.add(Types.INTEGER);
			List<Map<String, String>> resultList = conn.executeQuery(sql,paramValue,paramValue, "查询历史调息记录...");
			if(resultList != null && resultList.size() > 0){
				rent = resultList.get(0).get("rent");
			}
		} catch (Exception e) {
			logger.error("查询当前合同某期之后回笼的租金时错误：" + e.getMessage(), e);
		}
		return rent;
	}
	
	/**
	 *  根据央行调息的id，查询他的利率信息
	 * 
	 * @param adjustId
	 * @return
	 * @throws Exception
	 */
	public Hashtable<String, String> findPBOCBAseRateInfoById(String id) throws Exception {
		// 数据库操作对象
		Conn conn = new Conn();
		String sql = "select start_date_ as start_date ," +
				"nvl(rate_half,1) as rate_half," +
				"nvl(rate_one,1) as rate_one," +
				"nvl(rate_three,1) as rate_three," +
				"nvl(rate_five,1) as rate_five," +
				"nvl(rate_abovefive,1) as rate_abovefive," +
				"nvl(base_rate_half,1) as base_rate_half," +
				"nvl(base_rate_one,1) as base_rate_one," +
				"nvl(base_rate_three,1) as base_rate_three," +
				"nvl(base_rate_five,1) as base_rate_five," +
				"nvl(base_rate_abovefive,1) as base_rate_abovefive " +
				"from fund_standard_interest where id=?";
		Hashtable<String, String> hmp = new Hashtable<String, String>();
		List paramValue=new ArrayList();
	    List paramType=new ArrayList();
	    paramValue.add(id);
	    paramType.add(Types.VARCHAR);
		List<Map<String, String>> rsList = conn.executeQuery(sql,paramValue,paramType);
		if (rsList.size() > 0) {
			Map<String, String> data = rsList.get(0);
			for(String key : data.keySet()){
				hmp.put(key, data.get(key));
			}
		}
		return hmp;
	}
	
	/**
	 * 
	 *  根据央行调息的id，查询他的利率信息
	 * 
	 * @param adjustId
	 * @return
	 * @throws Exception
	 */
	public Hashtable<String, String> getStandInfoById(String adjustId) throws Exception {
		// 数据库操作对象
		Conn conn = new Conn();
		String sql = "select start_date_ as start_date ," +
				"nvl(rate_half,1) as rate_half," +
				"nvl(rate_one,1) as rate_one," +
				"nvl(rate_three,1) as rate_three," +
				"nvl(rate_five,1) as rate_five," +
				"nvl(rate_abovefive,1) as rate_abovefive," +
				"nvl(base_rate_half,1) as base_rate_half," +
				"nvl(base_rate_one,1) as base_rate_one," +
				"nvl(base_rate_three,1) as base_rate_three," +
				"nvl(base_rate_five,1) as base_rate_five," +
				"nvl(base_rate_abovefive,1) as base_rate_abovefive " +
				"from fund_standard_interest where id=?";
		if(ResourceUtil.getDBType().indexOf("SQLSERVER")>-1){
			sql = sql.replaceAll("nvl", "ISNULL");
		}else if(ResourceUtil.getDBType().indexOf("MYSQL")>-1){
			sql = sql.replaceAll("nvl", "IFNULL");
		}
	    List paramValue=new ArrayList();
	    List paramType=new ArrayList();
	    paramValue.add(adjustId);
	    paramType.add(Types.VARCHAR);
		Hashtable<String, String> hmp = new Hashtable<String, String>();
		List<Map<String, String>> rsList = conn.executeQuery(sql,paramValue,paramType);
		if (rsList.size() > 0) {
			Map<String, String> data = rsList.get(0);
			for(String key : data.keySet()){
				hmp.put(key, data.get(key));
			}
		}
		return hmp;
	}
	
	
	/**
	 * 
	 *  根据央行调息的id，查询他的利率信息
	 * 
	 * @param adjustId
	 * @return
	 * @throws Exception
	 */
	public Hashtable<String, String> findLastStandInfo() throws Exception {
		// 数据库操作对象
		Conn conn = new Conn();
		String sql = "select start_date_ as start_date ," +
				"nvl(rate_half,1) as rate_half," +
				"nvl(rate_one,1) as rate_one," +
				"nvl(rate_three,1) as rate_three," +
				"nvl(rate_five,1) as rate_five," +
				"nvl(rate_abovefive,1) as rate_abovefive," +
				"nvl(base_rate_half,1) as base_rate_half," +
				"nvl(base_rate_one,1) as base_rate_one," +
				"nvl(base_rate_three,1) as base_rate_three," +
				"nvl(base_rate_five,1) as base_rate_five," +
				"nvl(base_rate_abovefive,1) as base_rate_abovefive " +
				"from fund_standard_interest order by start_date_ desc";
		Hashtable<String, String> hmp = new Hashtable<String, String>();
		List<Map<String, String>> rsList = conn.executeQuery(sql,new ArrayList(),new ArrayList());
		if (rsList.size() > 0) {
			Map<String, String> data = rsList.get(0);
			for(String key : data.keySet()){
				hmp.put(key, data.get(key));
			}
		}
		return hmp;
	}
}
