package com.reckon.dao.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.reckon.bean.AdjustBean;
import com.reckon.bean.ConditionBean;
import com.reckon.bean.TabCalBean;
import com.reckon.dao.Conn;
import com.reckon.entity.contract.reckon.condition.ContractConditionHis;
import com.reckon.entity.contract.reckon.condition.FundPutPlan;
import com.reckon.util.RentTools;
import com.reckon.util.TbBeanTools;
import com.reckon.util.copyObjectToHisTools;
import com.reckon.util.tools.DateTools;
import com.reckon.util.tools.NumTools;
import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.kernal.utils.FileUtil;
import com.tenwa.kernal.utils.ResourceUtil;
import com.tenwa.kernal.utils.UUIDUtil;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.leasing.utils.WorkflowUtil;

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
		//String sql = " delete " + tcb.getCondition_tb() + " where " + tcb.getContOrProjCName() + "='" + tcb.getContOrProjCValue() + "' and doc_id='" + tcb.getDocId() + "'";
		// 报价编号
		String sql = " delete " + tcb.getCondition_tb() + " where  doc_id='" + tcb.getDocId() + "'";
		//徐云龙修改
		//		if (!"".equals(cb.getOnhireId())) {
//			sql += " and onhire_id='" + cb.getOnhireId() + "'";
//		}
        System.out.println(sql);
		conn.executeUpdate(sql, "流程类型:", tcb.getCalType(), "交易结构删除语句...");
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
	 * @author liaobo
	 * @param cb
	 * @param tcb
	 * @return  
	 */
	public boolean addCondition(TabCalBean tcb, ConditionBean cb) throws Exception {
		BaseService baseService = (BaseService)WebUtil.getApplicationContext().getBean("baseService");
		
		Set<Class<?>> classes = FileUtil.getClasses("com.reckon.entity");
		Class<?> cla=null;
		ot:for(Class<?>  clazz :classes)
		{
			Annotation[] getAnnotations=clazz.getAnnotations();
			for(int i=0;i<getAnnotations.length;i++){
				for (Method method : getAnnotations[i].annotationType().getDeclaredMethods()){
					if(getAnnotations[i].annotationType().equals(javax.persistence.Table.class)){
						Object invoke = method.invoke(getAnnotations[i]);
						if(String.valueOf(invoke).equalsIgnoreCase(tcb.getCondition_tb())){
							cla=clazz;
							break ot;
						}
					}
					
				}
			}
		}
		Object obj= cla.newInstance();
		obj=copyObjectToHisTools.CopyObjectAndAddOtherProperty(baseService,cb, cla, null);
		baseService.saveEntity(obj);
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
		wheresql = tempTcb.getCondition_tb() + " where  " + tempTcb.getContOrProjCName() + "='" + tcb.getContOrProjCValue() + "'";
		cb = getConditionBeanByContract(wheresql, tempTcb);
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
			wheresql = tempTcb.getCondition_tb() + " where  " + tempTcb.getContOrProjCName() + "='" + tcb.getContOrProjCValue() + "'";
			cb = getConditionBeanByContract(wheresql, tempTcb);
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
				onHireCb.sethandlingChargeMoneyRatio(NumTools.calculationStr(NumTools.calculationStr(onHireCb.getHandlingChargeMoney(), onHireCb.getEquipAmt(), NumTools.DIVIDE, 10), "100", NumTools.MULTIPLY, accuracy));
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
		String sql = "update " + tcb.getCondition_tb() + " set irr=" + NumTools.formatNumberDoubleScale(contIrr, 8) + "*100  where " + tcb.getContOrProjCName() + "='" + tcb.getContOrProjCValue() + "' and doc_id='" + tcb.getDocId() + "'";
		// 报价编号
		if (!"".equals(tcb.getOnHire_id())) {
			sql += " and onhire_id='" + tcb.getOnHire_id() + "'";
		}
		conn.executeUpdate(sql, "流程类型:" + tcb.getCalType() + "更新交易结构:");
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
		String sql = "update " + tcb.getCondition_tb() + " set plan_irr=" + NumTools.formatNumberDoubleScale(finaIrr, 8) + "*100  where " + tcb.getContOrProjCName() + "='" + tcb.getContOrProjCValue() + "' and doc_id='" + tcb.getDocId() + "'";
		// 报价编号
		if (!"".equals(tcb.getOnHire_id())) {
			sql += " and onhire_id='" + tcb.getOnHire_id() + "'";
		}
		conn.executeUpdate(sql, "流程类型:" + tcb.getCalType() + "更新交易结构:");
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
		String sql = "update " + tcb.getCondition_tb() + " set income_number='" + cb.getIncomeNumber() + "', lease_term='" + cb.getLeaseTerm() + "',year_rate='" + cb.getYearRate() + "' where " + tcb.getContOrProjCName() + "='" + tcb.getContOrProjCValue() + "' and doc_id='" + tcb.getDocId() + "'";
		conn.executeUpdate(sql, "流程类型:" + tcb.getCalType() + "更新交易结构:");
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
	public ConditionBean getConditionBeanByContract(String wheresql, TabCalBean tcb) throws Exception {

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
		sql += ",OTHER_LEASE_MONEY";
		sql += ",YEAR_RATE";
		sql += ",PERIOD_TYPE";
		sql += ",RATE_ADJUST_TYPE";
		sql += ",EVEN_CORPUS_RATE_TYPE";
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
		sql += ",RETURN_POINT_INCOME";
		sql += ",INTEREST_SUPPORT";
		sql += ",FAC_CAUTION_DEDUCTION_RATIO";
		sql += ",NVL(FAC_CAUTION_MONEY,0) as FAC_CAUTION_MONEY"; // 租赁保证金
		sql += ",FAC_CAUTION_DEDUCTION_MONEY";
		sql += ",FAC_CAUTION_MONEY_REMAIN";
		sql += ",HANDLING_CHARGE_MONEY_RATIO";// 保理手续费比例
		sql += ",NVL(HANDLING_CHARGE_MONEY,0) as HANDLING_CHARGE_MONEY";// 保理手续费
		sql += ",NVL(HAND_MONEY,0) as HAND_MONEY";//租赁手续费
		sql += ",NVL(HAND_RATIO,0) as HAND_RATIO";//租赁手续费比例
		sql += ",HAND_HZ";//租赁手续费还款间隔
		sql += ",HAND_TYPE";//租赁手续费还款类型
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
		//保理测算
		sql += ",EQUIPAMT_RATIO";
		sql += ",FACTORING_PAYOUT_RATIO";
		sql += ",COUNSELING_MONEY";
		sql += ",COUNSELING_RATIO";
		sql += ",FACTORING_INCOME";
		sql += ",FACTORING_FEE_RATIO";
		sql += ",INSUREMONEY_RATIO";
		sql += ",other_fee_rec";
		sql += ",other_fee_rec_ratio";
		sql += ",Factoring_guarantee_fee";
		sql += ",Factoring_guarantee_fee_ratio";
		sql += ",other_fee";
		sql += ",other_fee_ratio";
		sql += ",Factoring_register_fee";
		sql += ",Factoring_register_fee_ratio";
		sql += ",SALES_VOLUME";
		sql += ",SALES_VOLUME_RADIO";
		sql += ",actual_fund";
		sql += ",actual_fund_radio";
		sql += ",FACTORING_FLAT_RATE_MONTH";
		sql += ",FACTORING_YEAR_RATE_MONTH";
		sql += ",FACTORING_FUND_COST_MONTH";
		sql += ",FACTORING_IRR_MONTH";
		sql += ",FACTORING_SPREAD_MONTH";
		sql += ",FACTORING_FLAT_RATE_YEAR";
		sql += ",FACTORING_YEAR_RATE_YEAR";
		sql += ",FACTORING_FUND_COST_YEAR";
		sql += ",FACTORING_IRR_YEAR";
		sql += ",FACTORING_SPREAD_YEAR";
		sql += ",GP";
		sql += ",LEASE_TERM_DAY";
		sql += ",RATE_FLOAT";
		sql += ",GROSS_PROFIT";
		sql += ",nvl(GRACE_RATE,0) as GRACE_RATE";
		sql += ",nvl(GRACE_ADJUST,0) as GRACE_ADJUST";
		sql += ",nvl(GRACE,0) as GRACE";
		sql += ",CONTRACT_ID";
		//sea  未带出字段新增
		//sql += ",NVL(PURCHASE_PRICE,0) as  PURCHASE_PRICE ";//购买价格
		sql +=  " FROM "+wheresql;
		if(ResourceUtil.getDBType().indexOf("SQLSERVER")>-1){
			sql = sql.replaceAll("NVL", "ISNULL");
		}else if(ResourceUtil.getDBType().indexOf("MYSQL")>-1){
			sql = sql.replaceAll("NVL", "IFNULL");
		}
		logger.info(sql);
		List<Map<String, String>> rs_list = conn.executeQuery(sql, "查询交易结构表信息..");
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
			cb.setOtherLeaseMoney(rs.get("other_lease_money"));
			cb.setYearRate(rs.get("year_rate"));
			cb.setEvenCorpusRateType(rs.get("even_corpus_rate_type"));
			cb.setPeriodType(rs.get("period_type"));
			cb.setRateAdjustType(rs.get("rate_adjust_type"));
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
			cb.setFacCautionDeductionRatio(rs.get("FAC_CAUTION_DEDUCTION_RATIO"));
			cb.setFacCautionMoney(rs.get("fac_caution_money"));
			cb.setFacCautionmoneyRemain(rs.get("fac_caution_money_remain"));
			cb.setFacCautionDeductionmoney(rs.get("fac_caution_deduction_money"));
			cb.setCautionMoneyRatio(rs.get("CAUTION_DEDUCTION_RATIO"));
			cb.setCautionMoney(rs.get("caution_money"));// 租赁保证金
			cb.setCautionMoneyRemain(rs.get("caution_money_remain"));
			cb.setCautionDeductionRatio(rs.get("caution_deduction_ratio"));
			cb.setCautionDeductionMoney(rs.get("caution_deduction_money"));
//			cb.setCautionDeductionMoney(rs.get("return_point_income"));谁写的，醉了
//			cb.setCautionDeductionMoney(rs.get("interest_support"));
			cb.setReturnPointIncome(rs.get("return_point_income"));
			cb.setInterestSupport(rs.get("interest_support"));
			cb.sethandlingChargeMoneyRatio(rs.get("handling_charge_money_ratio"));
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
			cb.setRentOrRateValue(new BigDecimal(getstrorzero(rs,"rent_or_rate_value") ));
			
			cb.setEquipamtRatio(new BigDecimal(getstrorzero(rs,"equipamt_ratio")));
			cb.setFactoringPayoutRatio(new BigDecimal(getstrorzero(rs,"factoring_payout_ratio")));
			cb.setCounselingMoney(new BigDecimal(getstrorzero(rs,"counseling_money")));
			cb.setCounselingRatio(new BigDecimal(getstrorzero(rs,"counseling_ratio")));
			cb.setFactoringIncome(new BigDecimal(getstrorzero(rs,"factoring_income")));
			cb.setFactoringFeeRatio(new BigDecimal(getstrorzero(rs,"factoring_fee_ratio")));
			cb.setInsuremoneyRatio(new BigDecimal(getstrorzero(rs,"insuremoney_ratio")));
			cb.setOtherFeeRec(new BigDecimal(getstrorzero(rs,"other_fee_rec")));
			cb.setOtherFeeRecRatio(new BigDecimal(getstrorzero(rs,"other_fee_rec_ratio")));
			cb.setFactoringGuaranteeFee(new BigDecimal(getstrorzero(rs,"factoring_guarantee_fee")));
			cb.setFactoringGuaranteefeeRatio(new BigDecimal(getstrorzero(rs,"factoring_guarantee_fee_ratio")));
			cb.setOtherFee(new BigDecimal(getstrorzero(rs,"other_fee")));
			cb.setOtherFeeRatio(new BigDecimal(getstrorzero(rs,"other_fee_ratio")));
			cb.setFactoringRegisterfee(new BigDecimal(getstrorzero(rs,"factoring_register_fee")));
			cb.setFactoringRegisterfeeRatio(new BigDecimal(getstrorzero(rs,"factoring_register_fee_ratio")));
			cb.setSalesVolume(new BigDecimal(getstrorzero(rs,"sales_volume")));
			cb.setSalesVolumeRatio(new BigDecimal(getstrorzero(rs,"sales_volume_radio")));
			cb.setActualFund(new BigDecimal(getstrorzero(rs,"actual_fund")));
			cb.setActualFundRadio(new BigDecimal(getstrorzero(rs,"actual_fund_radio")));
			cb.setFactoringFlatRateMonth(new BigDecimal(getstrorzero(rs,"factoring_flat_rate_month")));
			cb.setFactoringYearRateMonth(new BigDecimal(getstrorzero(rs,"factoring_year_rate_month")));
			cb.setFactoringFundCostMonth(new BigDecimal(getstrorzero(rs,"factoring_fund_cost_month")));
			cb.setFactoringirrmonth(new BigDecimal(getstrorzero(rs,"factoring_irr_month")));
			cb.setFactoringSpreadMonth(new BigDecimal(getstrorzero(rs,"factoring_spread_month")));
			cb.setFactoringFlatRateYear(new BigDecimal(getstrorzero(rs,"factoring_flat_rate_year")));
			cb.setFactoringYearRateYear(new BigDecimal(getstrorzero(rs,"factoring_year_rate_year")));
			cb.setFactoringFundCostYear(new BigDecimal(getstrorzero(rs,"factoring_fund_cost_year")));
			cb.setFactoringIrrYear(new BigDecimal(getstrorzero(rs,"factoring_irr_year")));
			cb.setFactoringSpreadYear(new BigDecimal(getstrorzero(rs,"factoring_spread_year")));
			cb.setLeaseTermDay(Integer.parseInt(getstrorzero(rs,"lease_term_day")) );
			cb.setRateFloat(new BigDecimal(getstrorzero(rs,"rate_float")));
			cb.setGp(new BigDecimal(getstrorzero(rs,"factoring_register_fee_ratio")));
			cb.setGracerate(rs.get("grace_rate"));
			cb.setGraceadjust(rs.get("grace_adjust"));
			cb.setHandmoney(rs.get("hand_money"));
			cb.setHandratio(rs.get("hand_ratio"));
			cb.setHandhz(rs.get("hand_hz"));
			cb.setHandtype(rs.get("hand_type"));
//			cb.setGrace(Integer.parseInt(getstrorzero(rs,"grace")));//这里不能设置，设置的话租金计划变更就会报错
			cb.setContractId(rs.get("contract_id"));
		} else {
			cb = null;
		}
		return cb;
	}
	/**
	 * 从资金计划正式表获取投放数据
	 * @param tcb
	 * @return
	 * @throws Exception
	 */
	public List<FundPutPlan> getConditionPutPlanByContract(AdjustBean adb) throws Exception {

		// 数据库操作对象
		Conn conn = new Conn();
		ConditionBean cb = new ConditionBean();
		String sql = "SELECT ID";
		sql += ",PAYMENT_ID";
		sql += ",DOC_ID";
		sql += ",PLAN_DATE";
		sql += ",PLAN_MONEY";
		sql += ",FPNOTE";
		sql += ",CONTRACT_ID";
		sql += ",DEVICE_NAME";
		sql += ",FUND_TYPE";
		sql += ",PAY_CONDITION";
		sql += ",PAY_TYPE";
		sql += " FROM CONTRACT_FUND_FUND_PLAN WHERE contract_id='"+adb.getContractInfoId()+"' and fee_type='feetype10'";
		List<Map<String, String>> rs_list = conn.executeQuery(sql, "查询投放..");
		List<FundPutPlan> fpp =new ArrayList<FundPutPlan>();
		if (rs_list.size() > 0) {
			for(int i=0 ; i<rs_list.size();i++){
				Map<String, String> rs = rs_list.get(i);
				String planmoney = (rs.get("PLAN_MONEY")==null||rs.get("PLAN_MONEY")=="")?"0":rs.get("PLAN_MONEY");
				FundPutPlan fp = new FundPutPlan();
				fp.setId(rs.get("id"));
//				fp.setPaymentId(rs.get(""));
//				fp.setDocId(rs.get());
				fp.setPlanDate(rs.get(""));
				fp.setPlanMoney(new BigDecimal(planmoney));
				fp.setFpnote(rs.get(""));
//				fp.setContractId(rs.get(""));
//				fp.setDevicename(rs.get(""));
//				fp.setFundType(rs.get(""));
				fpp.add(fp);
			}
		}
		return fpp;
	}
	private String getstrorzero(Map<String, String> rs,String str){
		
		return (rs.get(str) == null ||rs.get(str)== "")?"0":rs.get(str);
	}
	/**
	 * 
	 * ( 根据查取交易结构信息)
	 * 
	 * @param contract_id
	 * @return
	 * @throws Exception
	 */
	public ConditionBean getConditionBeanByProj(String wheresql) throws Exception {

		// 数据库操作对象
		Conn conn = new Conn();
		ConditionBean cb = new ConditionBean();
		String sql = "SELECT ID";
		sql += ",EQUIP_AMT";
		sql += ",LEASE_AMT_DATE";
		sql += ",FIRST_PAYMENT_RATIO";
		sql += ",FIRST_PAYMENT";
		sql += ",CLEAN_LEASE_MONEY";
		sql += ",OTHER_LEASE_MONEY";
		sql += ",YEAR_RATE";
		sql += ",PERIOD_TYPE";
		sql += ",RATE_ADJUST_TYPE";
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
		sql += ",RETURN_POINT_INCOME";
		sql += ",INTEREST_SUPPORT";
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
		//保理测算
		sql += ",EQUIPAMT_RATIO";
		sql += ",FACTORING_PAYOUT_RATIO";
		sql += ",COUNSELING_MONEY";
		sql += ",COUNSELING_RATIO";
		sql += ",FACTORING_INCOME";
		sql += ",FACTORING_FEE_RATIO";
		sql += ",INSUREMONEY_RATIO";
		sql += ",other_fee_rec";
		sql += ",other_fee_rec_ratio";
		sql += ",Factoring_guarantee_fee";
		sql += ",Factoring_guarantee_fee_ratio";
		sql += ",other_fee";
		sql += ",other_fee_ratio";
		sql += ",Factoring_register_fee";
		sql += ",Factoring_register_fee_ratio";
		sql += ",SALES_VOLUME";
		sql += ",SALES_VOLUME_RADIO";
		sql += ",actual_fund";
		sql += ",actual_fund_radio";
		sql += ",FACTORING_FLAT_RATE_MONTH";
		sql += ",FACTORING_YEAR_RATE_MONTH";
		sql += ",FACTORING_FUND_COST_MONTH";
		sql += ",FACTORING_IRR_MONTH";
		sql += ",FACTORING_SPREAD_MONTH";
		sql += ",FACTORING_FLAT_RATE_YEAR";
		sql += ",FACTORING_YEAR_RATE_YEAR";
		sql += ",FACTORING_FUND_COST_YEAR";
		sql += ",FACTORING_IRR_YEAR";
		sql += ",FACTORING_SPREAD_YEAR";
		sql += ",GP";
		sql += ",RATE_FLOAT";
		sql += ",LEASE_TERM_DAY";
		sql += ",INSURE_MONEY_TYPE";//保险费测算方式
		sql += ",CREDIT_MONTHS";//实际授信月数
		sql += ",NVL(DC_NUM,0) as DC_NUM ";//保证金抵扣期数
		
		sql += " FROM " + wheresql;

		if(ResourceUtil.getDBType().indexOf("SQLSERVER")>-1){
			sql = sql.replaceAll("NVL", "ISNULL");
		}else if(ResourceUtil.getDBType().indexOf("MYSQL")>-1){
			sql = sql.replaceAll("NVL", "IFNULL");
		}
		
		List<Map<String, String>> rs_list = conn.executeQuery(sql, "查询调息时交易结构表信息..");
		for (Map<String, String> rs : rs_list) {
			cb.setId(rs.get("id"));
			cb.setDocId(rs.get("doc_id"));
			cb.setProjId(rs.get("proj_id"));
			cb.setEquipAmt(rs.get("equip_amt"));
			cb.setLeaseAmtDate(rs.get("lease_amt_date"));
			cb.setFirstPaymentRatio(rs.get("first_payment_ratio"));
			cb.setFirstPayment(rs.get("first_payment"));
			cb.setCleanLeaseMoney(rs.get("clean_lease_money"));
			cb.setOtherLeaseMoney(rs.get("other_lease_money"));
			cb.setYearRate(rs.get("year_rate"));
			cb.setPeriodType(rs.get("period_type"));
			cb.setRateAdjustType(rs.get("rate_adjust_type"));
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
			cb.setCautionDeductionMoney(rs.get("return_point_income"));
			cb.setCautionDeductionMoney(rs.get("interest_support"));
			cb.sethandlingChargeMoneyRatio(rs.get("handling_charge_money_ratio"));
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
			
			cb.setEquipamtRatio(new BigDecimal(rs.get("equipamt_ratio")));
			cb.setFactoringPayoutRatio(new BigDecimal(rs.get("factoring_payout_ratio")));
			cb.setCounselingMoney(new BigDecimal(rs.get("counseling_money")));
			cb.setCounselingRatio(new BigDecimal(rs.get("counseling_ratio")));
			cb.setFactoringIncome(new BigDecimal(rs.get("factoring_income")));
			cb.setFactoringFeeRatio(new BigDecimal(rs.get("factoring_fee_ratio")));
			cb.setInsuremoneyRatio(new BigDecimal(rs.get("insuremoney_ratio")));
			cb.setOtherFeeRec(new BigDecimal(rs.get("other_fee_rec")));
			cb.setOtherFeeRecRatio(new BigDecimal(rs.get("other_fee_rec_ratio")));
			cb.setFactoringGuaranteeFee(new BigDecimal(rs.get("factoring_guarantee_fee")));
			cb.setFactoringGuaranteefeeRatio(new BigDecimal(rs.get("factoring_guarantee_fee_ratio")));
			cb.setOtherFee(new BigDecimal(rs.get("other_fee")));
			cb.setOtherFeeRatio(new BigDecimal(rs.get("other_fee_ratio")));
			cb.setFactoringRegisterfee(new BigDecimal(rs.get("factoring_register_fee")));
			cb.setFactoringRegisterfeeRatio(new BigDecimal(rs.get("factoring_register_fee_ratio")));
			cb.setSalesVolume(new BigDecimal(rs.get("sales_volume")));
			cb.setSalesVolumeRatio(new BigDecimal(rs.get("sales_volume_radio")));
			cb.setActualFund(new BigDecimal(rs.get("actual_fund")));
			cb.setActualFundRadio(new BigDecimal(rs.get("actual_fund_radio")));
			cb.setFactoringFlatRateMonth(new BigDecimal(rs.get("factoring_flat_rate_month")));
			cb.setFactoringYearRateMonth(new BigDecimal(rs.get("factoring_year_rate_month")));
			cb.setFactoringFundCostMonth(new BigDecimal(rs.get("factoring_fund_cost_month")));
			cb.setFactoringirrmonth(new BigDecimal(rs.get("factoring_irr_month")));
			cb.setFactoringSpreadMonth(new BigDecimal(rs.get("factoring_spread_month")));
			cb.setFactoringFlatRateYear(new BigDecimal(rs.get("factoring_flat_rate_year")));
			cb.setFactoringYearRateYear(new BigDecimal(rs.get("factoring_year_rate_year")));
			cb.setFactoringFundCostYear(new BigDecimal(rs.get("factoring_fund_cost_year")));
			cb.setFactoringIrrYear(new BigDecimal(rs.get("factoring_irr_year")));
			cb.setFactoringSpreadYear(new BigDecimal(rs.get("factoring_spread_year")));
			cb.setGp(new BigDecimal(rs.get("factoring_register_fee_ratio")));
			cb.setRateFloat(new BigDecimal(rs.get("rate_float")));
			cb.setLeaseTermDay(Integer.parseInt(rs.get("lease_term_day")) );
		}
		return cb;
	}
}
