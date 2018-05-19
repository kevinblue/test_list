package com.tenwa.leasing.serviceImpl.rent.fundoffset;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.kernal.utils.StringUtil;
import com.tenwa.leasing.entity.fund.ContractFundFundCharge;
import com.tenwa.leasing.entity.fund.ContractFundFundPlan;
import com.tenwa.leasing.entity.fund.ContractFundRentInCome;
import com.tenwa.leasing.entity.fund.ContractFundRentPlan;
import com.tenwa.leasing.service.rent.fundoffset.FundOffsetService;

/**
 * 
 * @author lichaojie
 *2014-12-04
 */
@Service(value = "fundOffsetService")
public class FundOffsetServiceImpl implements FundOffsetService {

	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Override
	public void saveFundOffsetInfo(Map<String, String> variablesMap) throws Exception {
		//String contract_id = variablesMap.get("contract_info.id");
		
		User user = (User) SecurityUtil.getPrincipal();
		String nowdate = DateUtil.getSystemDateTime();
		
		String json_caution_money_refund_detail_str = variablesMap.get("json_caution_money_refund_detail_str");
		String json_rent_income_detail_str = variablesMap.get("json_rent_income_detail_str");
		
		BigDecimal zero = BigDecimal.ZERO;
		
		ObjectMapper objectMapper = new ObjectMapper();
		List<Map<String, Object>> jsonChargeList = objectMapper.readValue(json_caution_money_refund_detail_str, List.class);
		List<Map<String, Object>> jsonIncomeList = objectMapper.readValue(json_rent_income_detail_str, List.class);
		
		ContractFundFundPlan fundPlan = null;
		ContractFundFundCharge charge = null;
		ContractFundRentPlan rentPlan = null;
		ContractFundRentInCome income = null;
		DictionaryData dictData = null;
		Map<String, Object> jsonChargeMap = null;
		Map<String, Object> jsonIncomeMap = null;
		
		List<ContractFundFundCharge> chargeList = new ArrayList<ContractFundFundCharge>();
		List<ContractFundRentInCome> incomeList = new ArrayList<ContractFundRentInCome>();
		
		DictionaryData payTypeOut = new DictionaryData();
		payTypeOut.setId("pay_type_out");
		DictionaryData currencyType1 = new DictionaryData();
		currencyType1.setId("currency_type1");
		DictionaryData feeType2 = new DictionaryData();
		feeType2.setId("feetype2");
		DictionaryData payFund7 = new DictionaryData();
		payFund7.setId("payfund7");
		
		Map<String, String> chargeIdMap = new HashMap<String, String>();
		
		//保存资金信息
		for(int i = 0; i < jsonChargeList.size();i ++){
			charge = new ContractFundFundCharge();
			jsonChargeMap = jsonChargeList.get(i);
			fundPlan = this.baseService.findEntityByID(ContractFundFundPlan.class, jsonChargeMap.get("planid").toString());
			charge.setFundFundChargePlan(fundPlan);
			charge.setContractId(fundPlan.getContractId());
			charge.setChargeList(1);
			charge.setFeeType(feeType2);
			charge.setSettleMethod(payFund7);
			charge.setFactDate(StringUtil.nullToString(jsonChargeMap.get("factdate")));
			charge.setFactMoney(new BigDecimal(StringUtil.nullToString(jsonChargeMap.get("factmoney"),"0")));
			charge.setFeeAdjust(new BigDecimal(StringUtil.nullToString(jsonChargeMap.get("feeadjust"),"0")));
			charge.setCurrency(currencyType1);
			charge.setFactObject(StringUtil.nullToString(jsonChargeMap.get("factobject")));
			charge.setAccountBank(StringUtil.nullToString(jsonChargeMap.get("accountbank")));
			charge.setAccount(StringUtil.nullToString(jsonChargeMap.get("account")));
			charge.setAccNumber(StringUtil.nullToString(jsonChargeMap.get("accnumber")));
			charge.setClientBank(StringUtil.nullToString(jsonChargeMap.get("clientbank")));
			charge.setClientAccount(StringUtil.nullToString(jsonChargeMap.get("clientaccount")));
			charge.setClientAccnumber(StringUtil.nullToString(jsonChargeMap.get("clientaccnumber")));
			charge.setInvoiceNo("");
			charge.setAccountingDate(StringUtil.nullToString(jsonChargeMap.get("accountingdate")));
			charge.setPayType(payTypeOut);
			charge.setRoll_Back("0");
			charge.setCreator(user);
			charge.setCreateDate(nowdate);
			chargeList.add(charge);
			this.baseService.saveEntity(charge);
			chargeIdMap.put(StringUtil.nullToString(jsonChargeMap.get("planid")), charge.getId());//保存租金实收信息
			System.out.println("planid=" + StringUtil.nullToString(jsonChargeMap.get("planid")) + "资金id=" + charge.getId());
		}
		
		//保存租金核销信息
		for(int i = 0; i < jsonIncomeList.size();i ++){
			income = new ContractFundRentInCome();
			jsonIncomeMap = jsonIncomeList.get(i);
			rentPlan = this.baseService.findEntityByID(ContractFundRentPlan.class, jsonIncomeMap.get("id").toString());
			income.setPlanId(rentPlan);
			income.setContractId(rentPlan.getContractId());
			income.setDocId(variablesMap.get("docId"));
			income.setPlanList(Integer.valueOf(StringUtil.nullToString(jsonIncomeMap.get("planlist"))));
			income.setHireList(Integer.valueOf(StringUtil.nullToString(jsonIncomeMap.get("hirelist"))));
			//income.setHireType(hireType);
			dictData = new DictionaryData();
			dictData.setId(StringUtil.nullToString(jsonIncomeMap.get("balancemode")));
			income.setBalanceMode(dictData);
			income.setHireDate(StringUtil.nullToString(jsonIncomeMap.get("hiredate")));
			income.setCurrency(currencyType1);
			income.setRent(new BigDecimal(StringUtil.nullToString(jsonIncomeMap.get("rent"))));
			income.setCorpus(new BigDecimal(StringUtil.nullToString(jsonIncomeMap.get("corpus"))));
			income.setInterest(new BigDecimal(StringUtil.nullToString(jsonIncomeMap.get("interest"))));
			income.setPenalty(new BigDecimal(StringUtil.nullToString(jsonIncomeMap.get("penalty"))));
			income.setRentAdjust(new BigDecimal(StringUtil.nullToString(jsonIncomeMap.get("rentadjust"),"0")));
			income.setCorpusAdjust(new BigDecimal(StringUtil.nullToString(jsonIncomeMap.get("corpusadjust"),"0")));
			income.setInterestAdjust(new BigDecimal(StringUtil.nullToString(jsonIncomeMap.get("interestadjust"),"0")));
			income.setPenaltyAdjust(new BigDecimal(StringUtil.nullToString(jsonIncomeMap.get("penaltyadjust"),"0")));
			income.setHireObject(StringUtil.nullToString(jsonIncomeMap.get("hireobject")));
			income.setHireBank(StringUtil.nullToString(jsonIncomeMap.get("hirebank")));
			income.setHireNumber(StringUtil.nullToString(jsonIncomeMap.get("hirenumber")));
			income.setOwnBank(StringUtil.nullToString(jsonIncomeMap.get("ownbank")));
			income.setOwnAccount(StringUtil.nullToString(jsonIncomeMap.get("ownaccount")));
			income.setAccountingDate(StringUtil.nullToString(jsonIncomeMap.get("accountingdate")));
			//income.setInsteadFlag();
			//income.setMemo();
			income.setPID(chargeIdMap.get(jsonIncomeMap.get("planid")));
			income.setRollBack("0");
			income.setCreator(user);
			income.setCreateDate(nowdate);
			incomeList.add(income);
			System.out.println("rentincome=" + jsonIncomeMap.get("planid") + "||||||" + chargeIdMap.get(jsonIncomeMap.get("planid")));
		}
		this.baseService.saveAllEntities(incomeList);//保存租金实收信息
	}
}
