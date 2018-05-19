package com.reckon.commons.helper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.reckon.commons.base.Condition;
import com.reckon.commons.base.FundPlan;
import com.reckon.commons.util.UUIDTools;
import com.tenwa.business.entity.DictionaryData;

public class FundPlanCalculator {

	/**
	 * 创建业务资金收付计划
	 * 
	 * @param cb
	 * @param conditionMap
	 * @return
	 * @throws Exception
	 */
	public static <T extends FundPlan> List<T> createBusinessFundPlan(Condition cb, Class<T> clazs) throws Exception {
		List<CashConfig> cashConfigList = getBudinessCashConfig();
		return createFundPlan(cb, cashConfigList, clazs);
	}

	/**
	 * 创建财务资金收付计划
	 * 
	 * @param cb
	 * @param conditionMap
	 * @return
	 * @throws Exception
	 */
	public static List<FundPlan> createFinacesFundPlan(Condition cb, Class<FundPlan> clazs) throws Exception {
		List<CashConfig> cashConfigList = getFinacesCashConfig();
		return createFundPlan(cb, cashConfigList, clazs);
	}

	private static <T extends FundPlan> List<T> createFundPlan(Condition condition, List<CashConfig> cashConfigList,Class<T> clazs) throws Exception {

		String custName = "客户";
		String ownName = "融资租赁有限公司";

		String businessId = condition.getEquipId();
		businessId = businessId == null ? condition.getContractId() : null;
		businessId = businessId == null ? condition.getProjId() : null;
		businessId = businessId == null ? condition.getCustId() : condition.getDocId();

		int in = 1, out = 1;
		T ffcp = null;
		List<T> ffcpList = new ArrayList<T>();
		Map<String, Object> conditionMap1 = ObjectConvertUtils.convertBeanToMap(condition);
		Map<String, Object> conditionMap2 = new HashMap<String, Object>();
		for(String key : conditionMap1.keySet()){
			conditionMap2.put(key.toLowerCase(), conditionMap1.get(key));
		}
		for (int i = 0; i < cashConfigList.size(); i++) {
			CashConfig cashConfig = cashConfigList.get(i);
			String temp = (String)conditionMap2.get(cashConfig.getFieldName());
			if (temp != null && !temp.equals("")) {
				if (Double.parseDouble(temp) > 0) {

					ffcp = clazs.newInstance();
					ffcp.setId(UUIDTools.getUUID());
					ffcp.setPlanMoney(new BigDecimal(temp));
					ffcp.setPlanDate((String)conditionMap2.get(cashConfig.getPayDate()));
					DictionaryData feeType = new DictionaryData();
					feeType.setId(cashConfig.getFeeType());
					feeType.setCode(cashConfig.getFeeType());
					feeType.setName(cashConfig.getFeeName());
					ffcp.setFeeType(feeType);
					DictionaryData payType = new DictionaryData();
					payType.setId(cashConfig.getPayType());
					payType.setCode(cashConfig.getPayType());
					payType.setName(cashConfig.getPayName());
					ffcp.setPayType(payType);

					if ("pay_type_in".equals(cashConfig.getPayType())) {
						ffcp.setPayObj(custName);
						String inId = "" + (in++);
						while (inId.length() < 3) {
							inId = "0" + inId;
						}
						ffcp.setPaymentId("I-" + businessId + "-" + inId);
					} else if ("pay_type_out".equals(cashConfig.getPayType())) {
						ffcp.setPayObj(ownName);
						String outId = "" + (out++);
						while (outId.length() < 3) {
							outId = "0" + outId;
						}
						ffcp.setPaymentId("O-" + businessId + "-" + outId);
					}
					ffcpList.add(ffcp);
				}
			}
		}
		ObjectAssociationUtil.associationConditionForFundPlan(ffcpList, condition);
		Collections.sort(ffcpList);
		return ffcpList;
	}

	private static List<CashConfig> getBudinessCashConfig() {
		String in = "pay_type_in", out = "pay_type_out", sd = "startdate", ed = "enddate";
		
		List<CashConfig> result = new ArrayList<CashConfig>();
		CashConfig c01 = new CashConfig("feetype02", "手续费", in, "收款", sd, "handlingchargemoney");
		result.add(c01);
		CashConfig c021 = new CashConfig("feetype01", "保证金", in, "收款", sd, "cautionmoney");
		result.add(c021);
		CashConfig c022 = new CashConfig("feetype01", "保证金", out, "付款", ed, "cautionmoney");
		result.add(c022);
		CashConfig c03 = new CashConfig("feetype12", "管理费", in, "收款", sd, "managementmoney");
		result.add(c03);
		CashConfig c04 = new CashConfig("feetype06", "首付款", in, "收款", sd, "firstpayment");
		result.add(c04);
		CashConfig c062 = new CashConfig("feetype08", "保险押金", out, "付款", sd, "insuremoney");
		result.add(c062);
		CashConfig c07 = new CashConfig("feetype00", "设备款", out, "付款", sd, "equipamt");
		result.add(c07);
		CashConfig c08 = new CashConfig("feetype13", "租前息", in, "收款", sd, "beforeinterest");
		result.add(c08);
		CashConfig c09 = new CashConfig("feetype11", "名义货价", in, "收款", sd, "nominalprice");
		result.add(c09);
		CashConfig c10 = new CashConfig("feetype10", "其它收入", in, "收款", sd, "otherincome");
		result.add(c10);
		CashConfig c11 = new CashConfig("feetype05", "材料押金", out, "付款", sd, "materialscashpledge");
		result.add(c11);
		CashConfig c151 = new CashConfig("feetype03", "GPS费用", in, "收款", sd, "gpsmoney");
		result.add(c151);
		CashConfig c152 = new CashConfig("feetype03", "GPS费用", out, "付款", sd, "gpsmoney");
		result.add(c152);
		CashConfig c16 = new CashConfig("feetype04", "公证费", out, "付款", sd, "notarialmoney");
		result.add(c16);
		return result;
	}

	private static List<CashConfig> getFinacesCashConfig() {
		String in = "pay_type_in", out = "pay_type_out", sd = "startdate";
		List<CashConfig> result = new ArrayList<CashConfig>();
		CashConfig c07 = new CashConfig("feetype00", "设备款", out, "付款", sd, "equipamt");
		result.add(c07);
		CashConfig c01 = new CashConfig("feetype02", "手续费", in, "收款", sd, "handlingchargemoney");
		result.add(c01);
		CashConfig c03 = new CashConfig("feetype12", "管理费", in, "收款", sd, "managementmoney");
		result.add(c03);
		CashConfig c04 = new CashConfig("feetype06", "首付款", in, "收款", sd, "firstpayment");
		result.add(c04);
		return result;
	}
	
}

class CashConfig {
	
	private String feeType;
	private String feeName;
	private String payType;
	private String payName;
	private String payDate;
	private String fieldName;
	
	public CashConfig(String feeType, String feeName, String payType, String payName, String payDate, String fieldName) {
		super();
		this.feeType = feeType;
		this.feeName = feeName;
		this.payType = payType;
		this.payName = payName;
		this.payDate = payDate;
		this.fieldName = fieldName;
	}
	
	public String getFeeType() {
		return feeType;
	}
	
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	
	public String getFeeName() {
		return feeName;
	}
	
	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}
	
	public String getPayType() {
		return payType;
	}
	
	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	public String getPayName() {
		return payName;
	}
	
	public void setPayName(String payName) {
		this.payName = payName;
	}
	
	public String getPayDate() {
		return payDate;
	}
	
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	
	public String getFieldName() {
		return fieldName;
	}
	
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
}
