package com.reckon.dao.impl;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.TabCalBean;
import com.reckon.dao.Conn;
import com.reckon.util.RentTools;
import com.reckon.util.TbBeanTools;
import com.reckon.util.tools.DateTools;
import com.reckon.util.tools.NumTools;
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

public class ConditionDAOImpl {
	
	private static Logger logger = Logger.getLogger(ConditionDAOImpl.class);
	
	/**
	 * 
	 * ( 根据测算的表信息对相应的交易结构表进行删除操作)
	 * 
	 * @param tcb
	 * @return
	 * @throws Exception
	 */
	public boolean deleteCondition(TabCalBean tcb, ConditionBean cb) throws Exception {

		Conn conn = new Conn();
		//徐云龙修改
		String sql = " delete " + tcb.getCondition_tb() + " where " + tcb.getContOrProjCName() + "='" + tcb.getContOrProjCValue() + "' and doc_id='" + tcb.getDocId() + "'";
		// 报价编号
//		String sql = " delete " + tcb.getCondition_tb() + " where  doc_id=?";
		//徐云龙修改
		/*		if (!"".equals(cb.get)) {
			sql += " and onhire_id='" + cb.getOnhireId() + "'";
		}*/
		List paramValue=new ArrayList();
		List paramType=new ArrayList();
		paramValue.add(tcb.getDocId());
		paramType.add(Types.VARCHAR);
        System.out.println(sql);
		conn.executeUpdate(sql,new ArrayList(),new ArrayList(), "交易结构删除语句...");
		// logger.debug("删除交易结构成功...");
		// 如果是多次起租,那么合同表也要做同样的操作
		/*
		 * if(tcb.getCalType().equals("onHire_more_process")){ TabCalBean
		 * tcb_cont=new TabCalBean();
		 * tcb_cont=TbBeanTools.getTabInfo("cont_process", cb);
		 * deleteCondition(tcb_cont,cb); return true; }
		 */
		return true;
	}

	/**
	 * 
	 * ( 根据表，交易结构信息添加交易结构信息到数据库中) 
	 * 所以保存的时候要调用另一个方法获得原始的年利率值
	 * 
	 * @param cb
	 * @param tcb
	 * @return  
	 */
	public boolean addCondition(TabCalBean tcb, ConditionBean cb) throws Exception {
		Conn conn = new Conn();
		StringBuffer sb = new StringBuffer();
		sb.append(" INSERT INTO " + tcb.getCondition_tb()).
		append("(ID,doc_id").
		append("," + tcb.getContOrProjCName() +",").
		append("equip_amt,first_payment,first_payment_ratio,").
		append("equip_end_value,clean_lease_money,first_payment_total,").
		append("clean_credit_money,clean_credit_ratio,rent_or_rate_value,").
		append("income_number,grace,lease_term,").
		append("rate_float_amt,base_rate,year_rate,start_date,").
		append("lease_amt_date,first_plan_date,second_plan_date,").
		append("handling_charge_money,handling_charge_money_ratio,management_money,").
		append("management_money_ratio,caution_money,").
		append("caution_deduction_ratio,caution_deduction_money,").
		append("caution_money_remain,insure_money,insurance_lessee,").
		append("insurance_lessor,nominal_price,before_interest,").
		append("other_income, other_expenditure, return_amt,pena_rate,").
		append("free_defa_inter_day,irr,plan_irr,gross_profit, credit_months,").
		append("dc_num,settle_method,rent_or_rate, period_type,").//第二个proj_id,
		append("income_number_year,rate_float_type,insure_money_type, adjust_style").
		append(") VALUES(").//sys_guid()  '" + UUIDUtil.getUUID() + "'
		append("'"+UUIDUtil.getUUID()+"','" + tcb.getDocId()).
		append("','" + tcb.getContOrProjCValue()).
		append("','" + cb.getEquipAmt() + "','" + cb.getFirstPayment() + "','" + cb.getFirstPaymentRatio()).
		append("','" + cb.getEquipEndValue() + "','" + cb.getCleanLeaseMoney() + "','" + cb.getFirstPaymentTotal()).
		append("','" + cb.getCleanCreditMoney() + "','" + cb.getCleanCreditRatio() + "','" + cb.getRentOrRateValue()).
		append("','" + cb.getIncomeNumber() + "','" + cb.getGrace() + "','" + cb.getLeaseTerm()).
		append("','" + cb.getRateFloatAmt() + "','" + cb.getBaseRate() + "','" + cb.getYearRate() + "','" + DateTools.getNullDate(cb.getStartDate())).
		append("','" + cb.getLeaseAmtDate() + "','" + cb.getFirstPlanDate() + "','" + cb.getSecondPlanDate()).
		append("','" + cb.getHandlingChargeMoney() + "','" + cb.getHandlingChargeRatio() + "','" + cb.getManagementMoney()).
		append("','" + cb.getManagementMoneyRatio() + "','" + cb.getCautionMoney()).
		append("','" + cb.getCautionDeductionRatio() + "','" + cb.getCautionDeductionMoney()).
		append("','" + cb.getCautionMoneyRemain() + "','" + cb.getInsureMoney() + "','" + cb.getInsuranceLessee()).
		append("','" + cb.getInsuranceLessor() + "','" + cb.getNominalPrice() + "','" + cb.getBeforeInterest()).
		append("','" + cb.getOtherIncome() + "','" + cb.getOtherExpenditure() + "','" + cb.getReturnAmt() + "','" + cb.getPenaRate()).
		append("','" + cb.getFreeDefaInterDay() + "','" + cb.getIrr() + "','" + cb.getPlanIrr() + "','" + cb.getGrossProfit() + "','" + cb.getCreditMonths()).
		append("','" + cb.getDcNum() + "','" + cb.getSettleMethod() + "','" + cb.getRentOrRate() + "','" + cb.getPeriodType()).
		append("','" + cb.getIncomeNumberYear() + "','" + cb.getRateFloatType() + "','" + cb.getInsureMoneyType() + "','" + cb.getAdjustStyle()).
		append("')");
		//sql = sql.replace("'null'", "null");
		String replaceStr = sb.toString().replace("'null'", "null");
		sb = sb.replace(0, sb.length(), replaceStr);
		conn.executeUpdate(sb.toString(),new ArrayList(),new ArrayList(), "流程类型:" + tcb.getCalType() + "交易结构新增语句...");
		if ("onHire_more_process".equals(tcb.getCalType())) {
			TabCalBean tcb_cont = new TabCalBean();
			tcb_cont = TbBeanTools.getTabInfo("cont_process", cb);
			addConditionOHire(tcb_cont, cb);
			return true;
		}
		return true;
	}

	/**
	 * 
	 * 多次起租中交易结构表要合并 就是拿本次起租的交易结构和之前的合并.如果之前没有就直接用本次的.
	 * 
	 * @param tcb
	 *            操作表
	 * @return 返回布尔型表示是否操作成功
	 * @throws Exception
	 */
	public boolean addConditionOHire(TabCalBean tcb, ConditionBean onHCb) throws Exception {
		ConditionBean onHireCb = new ConditionBean();// 为了防止引用传递不能对传入变量做任何赋值操作
		// 从正式表中获取之前的交易结构信息 如果没有就表示是第一次起租.直接插入
		TabCalBean tempTcb = null;
		String wheresql = "";
		ConditionBean cb = new ConditionBean();
		// 判断要不要合并 第一次 发起多次起租流程的时候不需要合并 判断标准为 多次起租交易结构正式表中有没有这个合同的数据
		tempTcb = TbBeanTools.getTabInfo("onHire_more_process");// cont_process
		tempTcb.setTableToFormal();// 转为正式表
		wheresql = tempTcb.getCondition_tb() + " where  " + tempTcb.getContOrProjCName() + "=?";
		Map resultMap=new HashMap();
		resultMap.put("where", wheresql);
		resultMap.clear();
		List paramValue=new ArrayList();
		List paramType=new ArrayList();
		paramType.add(Types.VARCHAR);
		paramValue.add(tcb.getContOrProjCValue());
		resultMap.put("where", wheresql);
		resultMap.put("value", paramValue);
		resultMap.put("type", paramValue);
		cb = getConditionBeanByContract(resultMap, tempTcb);
		if (cb == null || cb.getEquipAmt().equals("")) {// 从多次起租正式交易结构表中没有查到数据
			logger.info("第一次起租,交易结构不需要合并!");
			onHireCb = onHCb;
		} else {// 不是第一次起租 需要合并
			logger.info("开始合并交易结构:");
			onHireCb.setAdjustStyle(onHCb.getAdjustStyle());
			onHireCb.setCreateDate(onHCb.getCreateDate());
			onHireCb.setCreator(onHCb.getCreator());
			onHireCb.setDocId(onHCb.getDocId());
			onHireCb.setGrace(onHCb.getGrace());
			onHireCb.setFreeDefaInterDay(onHCb.getFreeDefaInterDay());
			onHireCb.setId(onHCb.getId());
			onHireCb.setIncomeNumberYear(onHCb.getIncomeNumberYear());
			onHireCb.setInsureMoneyType(onHCb.getInsureMoneyType());
			onHireCb.setDocId(onHCb.getDocId());
			onHireCb.setPenaRate(onHCb.getPenaRate());
			onHireCb.setPeriodType(onHCb.getPeriodType());
			onHireCb.setRateFloatAmt(onHCb.getRateFloatAmt());
			onHireCb.setRateFloatType(onHCb.getRateFloatType());
			onHireCb.setSettleMethod(onHCb.getSettleMethod());
			onHireCb.setStartDate(onHCb.getStartDate());
			onHireCb.setYearRate(onHCb.getYearRate());
			onHireCb.setFirstPlanDate(onHCb.getFirstPlanDate());
			
			tempTcb = TbBeanTools.getTabInfo("cont_process");
			tempTcb.setTableToFormal();// 转为正式表
			wheresql = tempTcb.getCondition_tb() + " where  " + tempTcb.getContOrProjCName() + "=?";
			resultMap.clear();
			paramValue=new ArrayList();
			paramType=new ArrayList();
			paramType.add(Types.VARCHAR);
			paramValue.add(tcb.getContOrProjCValue());
			resultMap.put("where", wheresql);
			resultMap.put("value", paramValue);
			resultMap.put("type", paramValue);
			cb = getConditionBeanByContract(resultMap, tempTcb);
			if (cb != null && !cb.getEquipAmt().equals("")) {// 开始合并两个交易结构
				// 相加的一段可以考虑用SQL语句实现.特殊的才用代码做的.
				// 考虑到SQL数据转换不方便暂时先用EXECL拉出下面所有相加的代码
				int accuracy = RentTools.getAccuracy();// 默认精确到两位
				char type = NumTools.ADD;// 默认为加法
				onHireCb.setEquipAmt(NumTools.calculationStr(cb.getEquipAmt(), onHCb.getEquipAmt(), type, accuracy));
				onHireCb.setFirstPayment(NumTools.calculationStr(cb.getFirstPayment(), onHCb.getFirstPayment(), type, accuracy));
				onHireCb.setCleanLeaseMoney(NumTools.calculationStr(cb.getCleanLeaseMoney(), onHCb.getCleanLeaseMoney(), type, accuracy));
				onHireCb.setCleanLeaseMoney(NumTools.calculationStr(cb.getCleanLeaseMoney(), onHCb.getCleanLeaseMoney(), type, accuracy));
				onHireCb.setNominalPrice(NumTools.calculationStr(cb.getNominalPrice(), onHCb.getNominalPrice(), type, accuracy));
				onHireCb.setCautionMoney(NumTools.calculationStr(cb.getCautionMoney(), onHCb.getCautionMoney(), type, accuracy));
				onHireCb.setCautionDeductionMoney(NumTools.calculationStr(cb.getCautionDeductionMoney(), onHCb.getCautionDeductionMoney(), type, accuracy));
				onHireCb.setHandlingChargeMoney(NumTools.calculationStr(cb.getHandlingChargeMoney(), onHCb.getHandlingChargeMoney(), type, accuracy));
				onHireCb.setInsureMoney(NumTools.calculationStr(cb.getInsureMoney(), onHCb.getInsureMoney(), type, accuracy));
				onHireCb.setManagementMoney(NumTools.calculationStr(cb.getManagementMoney(), onHCb.getManagementMoney(), type, accuracy));
				onHireCb.setReturnAmt(NumTools.calculationStr(cb.getReturnAmt(), onHCb.getReturnAmt(), type, accuracy));
				onHireCb.setFirstPaymentTotal(NumTools.calculationStr(cb.getFirstPaymentTotal(), onHCb.getFirstPaymentTotal(), type, accuracy));
				onHireCb.setBeforeInterest(NumTools.calculationStr(cb.getBeforeInterest(), onHCb.getBeforeInterest(), type, accuracy));
				onHireCb.setOtherIncome(NumTools.calculationStr(cb.getOtherIncome(), onHCb.getOtherIncome(), type, accuracy));
				onHireCb.setOtherExpenditure(NumTools.calculationStr(cb.getOtherExpenditure(), onHCb.getOtherExpenditure(), type, accuracy));
				onHireCb.setEquipEndValue(NumTools.calculationStr(cb.getEquipEndValue(), onHCb.getEquipEndValue(), type, accuracy));
				// 特殊
				onHireCb.setModifyDate(onHCb.getCreateDate());
				onHireCb.setModificator(onHCb.getCreator());
				onHireCb.setCreator(cb.getCreator());
				onHireCb.setCreateDate(cb.getCreateDate());
				onHireCb.setLeaseAmtDate(cb.getLeaseAmtDate());
				// 计算比例
				accuracy = 6;
				onHireCb.setFirstPaymentRatio(NumTools.calculationStr(NumTools.calculationStr(onHireCb.getFirstPayment(), onHireCb.getEquipAmt(), NumTools.DIVIDE, 10), "100", NumTools.MULTIPLY, accuracy));
				onHireCb.setCautionMoneyRatio(NumTools.calculationStr(NumTools.calculationStr(onHireCb.getCautionMoney(), onHireCb.getEquipAmt(), NumTools.DIVIDE, 10), "100", NumTools.MULTIPLY, accuracy));
				onHireCb.setCautionDeductionRatio(NumTools.calculationStr(NumTools.calculationStr(onHireCb.getCautionDeductionMoney(), onHireCb.getEquipAmt(), NumTools.DIVIDE, 10), "100", NumTools.MULTIPLY, accuracy));
				onHireCb.setHandlingChargeRatio(NumTools.calculationStr(NumTools.calculationStr(onHireCb.getHandlingChargeMoney(), onHireCb.getEquipAmt(), NumTools.DIVIDE, 10), "100", NumTools.MULTIPLY, accuracy));
				onHireCb.setManagementMoneyRatio(NumTools.calculationStr(NumTools.calculationStr(onHireCb.getManagementMoney(), onHireCb.getEquipAmt(), NumTools.DIVIDE, 10), "100", NumTools.MULTIPLY, accuracy));
			}
		}
		deleteCondition(tcb, onHireCb);
		return addCondition(tcb, onHireCb);
	}

	/**
	 * 
	 * ( 根据表信息，财务irr更新合同irr)
	 * 
	 * @param tcb
	 * @param contIrr
	 * @return
	 */
	public boolean updateConditionContIrr(TabCalBean tcb, String contIrr) throws Exception {

		Conn conn = new Conn();
		String sql = "update " + tcb.getCondition_tb() + " set irr=" + NumTools.formatNumberDoubleScale(contIrr, 8) + "*100  where " + tcb.getContOrProjCName() + "=? and doc_id=? ";
		List paramValue=new ArrayList();
		List paramType=new ArrayList();
		paramValue.add( tcb.getContOrProjCValue());
		paramType.add(Types.VARCHAR);
		paramValue.add(tcb.getDocId());
		paramType.add(Types.VARCHAR);
		// 报价编号
		if (!"".equals(tcb.getOnHire_id())) {
			paramValue.add(tcb.getOnHire_id());
			paramType.add(Types.VARCHAR);
			sql += " and onhire_id=?";
		}
		conn.executeUpdate(sql, paramValue,paramType,"流程类型:" + tcb.getCalType() + "更新交易结构:");
		// 如果是多次起租,那么合同表也要做同样的操作
		if ("onHire_more_process".equals(tcb.getCalType())) {
			TabCalBean tcb_cont = new TabCalBean();
			tcb_cont = TbBeanTools.getTabInfo("cont_process");
			tcb_cont.setContOrProjCValue(tcb.getContOrProjCValue());
			tcb_cont.setDocId(tcb.getDocId());
			updateConditionContIrr(tcb_cont, contIrr);
			return true;
		}
		return true;
	}

	/**
	 * 
	 * ( 根据表信息，更新会计irr)
	 * 
	 * @param tcb
	 * @param finaIrr
	 * @return
	 */

	public boolean updateConditionFinaIrr(TabCalBean tcb, String finaIrr) throws Exception {

		Conn conn = new Conn();
		String sql = "update " + tcb.getCondition_tb() + " set plan_irr=" + NumTools.formatNumberDoubleScale(finaIrr, 8) + "*100  where " + tcb.getContOrProjCName() + "=? and doc_id=?";
		List paramValue=new ArrayList();
		List paramType=new ArrayList();
		paramValue.add( tcb.getContOrProjCValue());
		paramType.add(Types.VARCHAR);
		paramValue.add(tcb.getDocId());
		paramType.add(Types.VARCHAR);
		// 报价编号
		if (!"".equals(tcb.getOnHire_id())) {
			sql += " and onhire_id=?";
			paramValue.add(tcb.getOnHire_id());
			paramType.add(Types.VARCHAR);
		}
		conn.executeUpdate(sql,paramValue,paramType, "流程类型:" + tcb.getCalType() + "更新交易结构:");
		// 如果是多次起租,那么合同表也要做同样的操作
		if ("onHire_more_process".equals(tcb.getCalType())) {
			TabCalBean tcb_cont = new TabCalBean();
			tcb_cont = TbBeanTools.getTabInfo("cont_process");
			tcb_cont.setContOrProjCValue(tcb.getContOrProjCValue());
			tcb_cont.setDocId(tcb.getDocId());
			updateConditionFinaIrr(tcb_cont, finaIrr);
			return true;
		}
		return true;
	}

	/**
	 * scl 租金计划变更中使用到 (根据ConditionBean和TabCalBean 更新
	 * income_number_year,income_number,lease_term)
	 * 
	 * @param tcb
	 * @param cdb
	 * @return
	 * @throws Exception
	 */
	public boolean updateConditionIncomeNum(TabCalBean tcb, ConditionBean cb) throws Exception {
		Conn conn = new Conn();
		String sql = "update " + tcb.getCondition_tb() + " set income_number=?, lease_term=? ,year_rate=? where " + tcb.getContOrProjCName() + "=? and doc_id=? ";
		List paramValue=new ArrayList();
		List paramType=new ArrayList();
		paramValue.add(cb.getIncomeNumber());
		paramType.add(Types.VARCHAR);
		paramValue.add(cb.getLeaseTerm());
		paramType.add(Types.VARCHAR);
		paramValue.add(cb.getYearRate());
		paramType.add(Types.VARCHAR);
		paramValue.add(tcb.getContOrProjCValue());
		paramType.add(Types.VARCHAR);
		paramValue.add( tcb.getDocId());
		paramType.add(Types.VARCHAR);
		conn.executeUpdate(sql,paramValue,paramType, "流程类型:" + tcb.getCalType() + "更新交易结构:");
		// 如果是多次起租,那么合同表也要做同样的操作
		if (tcb.getCalType().equals("onHire_more_process")) {
			TabCalBean tcb_cont = new TabCalBean();
			tcb_cont = TbBeanTools.getTabInfo("cont_process", cb);
			updateConditionIncomeNum(tcb_cont, cb);
			return true;
		}
		return true;
	}

	/**
	 * 
	 * ( 根据查取交易结构信息)
	 * 
	 * @param contract_id
	 * @return
	 * @throws Exception
	 */
	public ConditionBean getConditionBeanByContract(Map sqlmap, TabCalBean tcb) throws Exception {

		// 数据库操作对象
		Conn conn = new Conn();
		ConditionBean cb = new ConditionBean();
		String sql = "SELECT ID";
		sql += ",DOC_ID";
		sql += "," + tcb.getContOrProjCName();
		sql += ",EQUIP_AMT";
		sql += ",LEASE_AMT_DATE";
		sql += ",FIRST_PAYMENT_RATIO";
		sql += ",NVL(FIRST_PAYMENT,0) as FIRST_PAYMENT";// 首付款
		sql += ",CLEAN_LEASE_MONEY";
		sql += ",YEAR_RATE";
		sql += ",PERIOD_TYPE";
		sql += ",INCOME_NUMBER_YEAR";
		sql += ",INCOME_NUMBER";
		sql += ",NVL(LEASE_TERM,0) as LEASE_TERM";//// 租赁期限（月）
		sql += ",SETTLE_METHOD";
		sql += ",RENT_OR_RATE";
		sql += ",RENT_OR_RATE_VALUE";
		sql += ",START_DATE";
		sql += ",NOMINAL_PRICE";
		sql += ",PENA_RATE";
		sql += ",RATE_FLOAT_TYPE";
		sql += ",RATE_FLOAT_AMT";
		//sql += ",IF_RETRY";
		sql += ",ADJUST_STYLE";
		sql += ",CAUTION_DEDUCTION_RATIO";
		sql += ",NVL(CAUTION_MONEY,0) as CAUTION_MONEY"; // 租赁保证金
		sql += ",CAUTION_MONEY_REMAIN";
		//sql += ",ADVANCE_PURCHASE_PRICE";
		//sql += ",PAYMENT_CHANNEL";
		sql += ",CAUTION_DEDUCTION_RATIO";
		sql += ",CAUTION_DEDUCTION_MONEY";
		sql += ",HANDLING_CHARGE_MONEY_RATIO";
		sql += ",NVL(HANDLING_CHARGE_MONEY,0) as HANDLING_CHARGE_MONEY";// 手续费
		sql += ",NVL(INSURE_MONEY,0) as INSURE_MONEY";//保险费
		sql += ",INSURE_MONEY_TYPE";
		sql += ",MANAGEMENT_MONEY_RATIO";
		sql += ",MANAGEMENT_MONEY";
		sql += ",IRR";
		sql += ",PLAN_IRR";
		sql += ",NVL(RETURN_AMT,0) as RETURN_AMT";// 厂商返利
		sql += ",FIRST_PAYMENT_TOTAL";
		//sql += ",INCOME_DAY";
		sql += ",NVL(BEFORE_INTEREST,0) as BEFORE_INTEREST";// 租前息
		//sql += ",BEFORE_INTEREST_DAY_RATE";
		//sql += ",RATE_ADJUSTMENT_MODULUS";
		sql += ",GRACE";
		sql += ",NVL(OTHER_INCOME,0) as OTHER_INCOME";// 其它收入
		sql += ",NVL(OTHER_EXPENDITURE,0) as OTHER_EXPENDITURE";//其它支出 
		//sql += ",CUST_CAUTION_MONEY_RATIO";
		//sql += ",CUST_CAUTION_MONEY";
		//sql += ",SUPPLIER_CAUTION_MONEY_RATIO";
		//sql += ",SUPPLIER_CAUTION_MONEY";
		sql += ",FIRST_PLAN_DATE";
		sql += ",SECOND_PLAN_DATE";
		//sql += ",EXPECT_RENT_RATIO";
		//sql += ",EXPECT_RENT";
		//sql += ",EXPECT_RENT_DEDUCTION_RATIO";
		//sql += ",EXPECT_RENT_DEDUCTION";
		sql += ",FREE_DEFA_INTER_DAY";
		sql += ",EQUIP_END_VALUE";
		//sql += ",ONHIRE_ID";
		//sql += ",END_DATE";
		//sql += ",ACTUAL_START_DATE";
		sql += ",GROSS_PROFIT";
		//sea  未带出字段新增
		//sql += ",NVL(PURCHASE_PRICE,0) as  PURCHASE_PRICE ";//购买价格
		sql+=",nvl(rent_or_rate_value,0) rent_or_rate_value";
		sql +=  " FROM "+sqlmap.get("where").toString();
		if(ResourceUtil.getDBType().indexOf("SQLSERVER")>-1){
			sql = sql.replaceAll("NVL", "ISNULL");
		}else if(ResourceUtil.getDBType().indexOf("MYSQL")>-1){
			sql = sql.replaceAll("NVL", "IFNULL");
		}
		logger.info(sql);
		List<Map<String, String>> rs_list = conn.executeQuery(sql,(List)sqlmap.get("value"),(List)sqlmap.get("type"), "查询交易结构表信息..");
		if (rs_list.size() > 0) {
			Map<String, String> rs = rs_list.get(0);
			cb.setId(rs.get("id"));
			cb.setDocId(rs.get("doc_id"));
			cb.setProjId(rs.get(tcb.getContOrProjCName()));
			cb.setEquipAmt(rs.get("equip_amt"));
			cb.setLeaseAmtDate(rs.get("lease_amt_date"));
			cb.setFirstPaymentRatio(rs.get("first_payment_ratio"));
			cb.setFirstPayment(rs.get("first_payment"));//首付款
			cb.setCleanLeaseMoney(rs.get("clean_lease_money"));
			cb.setYearRate(rs.get("year_rate"));
			cb.setPeriodType(rs.get("period_type"));
			cb.setIncomeNumberYear(rs.get("income_number_year"));
			cb.setIncomeNumber(Integer.parseInt(rs.get("income_number")));
			cb.setLeaseTerm(Integer.parseInt(rs.get("lease_term")));// 租赁期限（月）
			cb.setSettleMethod(rs.get("settle_method"));
			cb.setStartDate(DateTools.getDBDateStr(rs.get("start_date")));
			cb.setNominalPrice(rs.get("nominal_price"));
			cb.setPenaRate(rs.get("pena_rate"));
			cb.setRateFloatType(rs.get("rate_float_type"));
			cb.setRateFloatAmt(rs.get("rate_float_amt"));
			cb.setAdjustStyle(rs.get("adjust_style"));
			cb.setCautionMoneyRatio(rs.get("CAUTION_DEDUCTION_RATIO"));
			cb.setCautionMoney(rs.get("caution_money"));// 租赁保证金
			cb.setCautionMoneyRemain(rs.get("caution_money_remain"));
			cb.setCautionDeductionRatio(rs.get("caution_deduction_ratio"));
			cb.setCautionDeductionMoney(rs.get("caution_deduction_money"));
			cb.setHandlingChargeRatio(rs.get("handling_charge_money_ratio"));
			cb.setHandlingChargeMoney(rs.get("handling_charge_money"));// 手续费
			cb.setInsureMoney(rs.get("insure_money"));//保险费
			cb.setInsureMoneyType(rs.get("insure_money_type"));
			cb.setManagementMoneyRatio(rs.get("management_money_ratio"));
			cb.setManagementMoney(rs.get("management_money"));
			cb.setIrr(rs.get("irr"));
			cb.setPlanIrr(rs.get("plan_irr"));
			cb.setReturnAmt(rs.get("return_amt"));// 厂商返利
			cb.setFirstPaymentTotal(rs.get("first_payment_total"));
			cb.setBeforeInterest(rs.get("before_interest"));// 租前息
			cb.setOtherIncome(rs.get("other_income"));// 其它收入
			cb.setOtherExpenditure(rs.get("other_expenditure"));//其他支出
			cb.setFirstPlanDate(rs.get("first_plan_date"));
			cb.setFreeDefaInterDay(rs.get("free_defa_inter_day"));
			cb.setEquipEndValue(rs.get("equip_end_value"));
			cb.setRentOrRate(rs.get("rent_or_rate"));
			System.out.println(rs.get("rent_or_rate_value"));
			cb.setRentOrRateValue(new BigDecimal(rs.get("rent_or_rate_value")) ); 
		} else {
			cb = null;
		}
		return cb;
	}

	/**
	 * 
	 * ( 根据查取交易结构信息)
	 * 
	 * @param contract_id
	 * @return
	 * @throws Exception
	 */
	public ConditionBean getConditionBeanByProj(Map sqlmap) throws Exception {

		// 数据库操作对象
		Conn conn = new Conn();
		ConditionBean cb = new ConditionBean();
		String sql = "SELECT ID";
		sql += ",EQUIP_AMT";
		sql += ",LEASE_AMT_DATE";
		sql += ",FIRST_PAYMENT_RATIO";
		sql += ",FIRST_PAYMENT";
		sql += ",CLEAN_LEASE_MONEY";
		sql += ",YEAR_RATE";
		sql += ",PERIOD_TYPE";
		sql += ",INCOME_NUMBER_YEAR";
		sql += ",INCOME_NUMBER";
		sql += ",LEASE_TERM";
		sql += ",SETTLE_METHOD";
		sql += ",START_DATE";
		sql += ",NOMINAL_PRICE";
		sql += ",PENA_RATE";
		sql += ",RATE_FLOAT_TYPE";
		sql += ",RATE_FLOAT_AMT";
		//sql += ",IF_RETRY";
		sql += ",ADJUST_STYLE";
		sql += ",CAUTION_DEDUCTION_RATIO";
		sql += ",CAUTION_MONEY";
		sql += ",CAUTION_MONEY_REMAIN";
		//sql += ",ADVANCE_PURCHASE_PRICE";
		//sql += ",PAYMENT_CHANNEL";
		sql += ",CAUTION_DEDUCTION_RATIO";
		sql += ",CAUTION_DEDUCTION_MONEY";
		sql += ",HANDLING_CHARGE_MONEY_RATIO";
		sql += ",HANDLING_CHARGE_MONEY";
		sql += ",INSURE_MONEY";
		sql += ",INSURE_MONEY_TYPE";
		sql += ",MANAGEMENT_MONEY_RATIO";
		sql += ",MANAGEMENT_MONEY";
		sql += ",IRR";
		sql += ",PLAN_IRR";
		sql += ",RETURN_AMT";
		sql += ",FIRST_PAYMENT_TOTAL";
		//sql += ",INCOME_DAY";
		sql += ",BEFORE_INTEREST";
		//sql += ",BEFORE_INTEREST_DAY_RATE";
		//sql += ",RATE_ADJUSTMENT_MODULUS";
		sql += ",GRACE";
		sql += ",OTHER_INCOME";
		sql += ",OTHER_EXPENDITURE";
		//sql += ",CUST_CAUTION_MONEY_RATIO";
		//sql += ",CUST_CAUTION_MONEY";
		//sql += ",SUPPLIER_CAUTION_MONEY_RATIO";
		//sql += ",SUPPLIER_CAUTION_MONEY";
		sql += ",FIRST_PLAN_DATE";
		sql += ",SECOND_PLAN_DATE";
		//sql += ",EXPECT_RENT_RATIO";
		//sql += ",EXPECT_RENT";
		//sql += ",EXPECT_RENT_DEDUCTION_RATIO";
		//sql += ",EXPECT_RENT_DEDUCTION";
		sql += ",FREE_DEFA_INTER_DAY";
		sql += ",EQUIP_END_VALUE";
		//sql += ",ACTUAL_START_DATE";
		sql += ",GROSS_PROFIT";
		
		sql += ",INSURE_MONEY_TYPE";//保险费测算方式
		sql += ",CREDIT_MONTHS";//实际授信月数
		sql += ",NVL(DC_NUM,0) as DC_NUM ";//保证金抵扣期数
		
		sql += " FROM " + sqlmap.get("where");

		if(ResourceUtil.getDBType().indexOf("SQLSERVER")>-1){
			sql = sql.replaceAll("NVL", "ISNULL");
		}else if(ResourceUtil.getDBType().indexOf("MYSQL")>-1){
			sql = sql.replaceAll("NVL", "IFNULL");
		}
		
		List<Map<String, String>> rs_list = conn.executeQuery(sql,(List)sqlmap.get("value"),(List)sqlmap.get("type"), "查询调息时交易结构表信息..");
		for (Map<String, String> rs : rs_list) {
			cb.setId(rs.get("id"));
			cb.setDocId(rs.get("doc_id"));
			cb.setProjId(rs.get("proj_id"));
			cb.setEquipAmt(rs.get("equip_amt"));
			cb.setLeaseAmtDate(rs.get("lease_amt_date"));
			cb.setFirstPaymentRatio(rs.get("first_payment_ratio"));
			cb.setFirstPayment(rs.get("first_payment"));
			cb.setCleanLeaseMoney(rs.get("clean_lease_money"));
			cb.setYearRate(rs.get("year_rate"));
			cb.setPeriodType(rs.get("period_type"));
			cb.setIncomeNumberYear(rs.get("income_number_year"));
			cb.setIncomeNumber(Integer.parseInt(rs.get("income_number")));
			cb.setLeaseTerm(Integer.parseInt(rs.get("lease_term")));
			cb.setSettleMethod(rs.get("settle_method"));
			cb.setStartDate(DateTools.getDBDateStr(rs.get("start_date")));
			cb.setNominalPrice(rs.get("nominal_price"));
			cb.setPenaRate(rs.get("pena_rate"));
			cb.setRateFloatType(rs.get("rate_float_type"));
			cb.setRateFloatAmt(rs.get("rate_float_amt"));
			cb.setAdjustStyle(rs.get("adjust_style"));
			cb.setCautionMoneyRatio(rs.get("CAUTION_DEDUCTION_RATIO"));
			cb.setCautionMoney(rs.get("caution_money"));
			cb.setCautionMoneyRemain(rs.get("caution_money_remain"));
			cb.setCautionDeductionRatio(rs.get("caution_deduction_ratio"));
			cb.setCautionDeductionMoney(rs.get("caution_deduction_money"));
			cb.setHandlingChargeRatio(rs.get("handling_charge_money_ratio"));
			cb.setHandlingChargeMoney(rs.get("handling_charge_money"));
			cb.setInsureMoney(rs.get("insure_money"));
			cb.setInsureMoneyType(rs.get("insure_money_type"));
			cb.setManagementMoneyRatio(rs.get("management_money_ratio"));
			cb.setManagementMoney(rs.get("management_money"));
			cb.setIrr(rs.get("irr"));
			cb.setPlanIrr(rs.get("plan_irr"));
			cb.setReturnAmt(rs.get("return_amt"));
			cb.setFirstPaymentTotal(rs.get("first_payment_total"));
			cb.setBeforeInterest(rs.get("before_interest"));
			cb.setOtherIncome(rs.get("other_income"));
			cb.setOtherExpenditure(rs.get("other_expenditure"));
			cb.setFirstPlanDate(rs.get("first_plan_date"));
			cb.setFreeDefaInterDay(rs.get("free_defa_inter_day"));
			cb.setEquipEndValue(rs.get("equip_end_value"));
			cb.setCreditMonths( rs.get("credit_months") );//实际授信月数
			cb.setDcNum( Integer.parseInt( rs.get("dcnum") ) );//保证金抵扣期数
			
		}
		return cb;
	}
}
