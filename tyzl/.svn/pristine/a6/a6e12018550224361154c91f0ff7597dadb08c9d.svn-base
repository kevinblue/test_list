package com.tenwa.leasing.serviceImpl.rent.penaltyadjust;



import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.tenwa.leasing.entity.base.FundEbankData;
import com.tenwa.leasing.entity.fund.ContractFundRentInCome;
import com.tenwa.leasing.entity.fund.ContractFundRentPlan;
import com.tenwa.leasing.service.rent.penaltyadjust.PenaltyAdjustService;

/**
 * 
 * @author tangpengfei
 *
 */
@Service(value = "penaltyAdjustService")
public class PenaltyAdjustServiceImpl implements PenaltyAdjustService {

	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Override
	public void savePenaltyAdjust(Map<String, String> variablesMap) throws Exception {
		User user = (User) SecurityUtil.getPrincipal();
		String nowdate = DateUtil.getSystemDateTime();
		
		String json_rent_income_detail_str = variablesMap.get("json_rent_penalty_adjust_detail_str");
		
		BigDecimal total = BigDecimal.ZERO;
		
		
		//DictionaryData hireType = this.baseService.findEntityByID(DictionaryData.class, "");
		
		ObjectMapper objectMapper = new ObjectMapper();
		List<Map<String, Object>> jsonList = objectMapper.readValue(json_rent_income_detail_str, List.class);
		
		ContractFundRentPlan plan = null;
		ContractFundRentInCome income = null;
		
		List<ContractFundRentInCome> incomeList = new ArrayList<ContractFundRentInCome>();
		
		Map<String, Object> jsonMap = null;
		
		DictionaryData dictData = null;
		
		for(int i = 0; i < jsonList.size();i ++){
			income = new ContractFundRentInCome();
			jsonMap = jsonList.get(i);
			plan = this.baseService.findEntityByID(ContractFundRentPlan.class, jsonMap.get("id").toString());
			income.setPlanId(plan);
			income.setContractId(plan.getContractId());
			income.setDocId(variablesMap.get("docId"));
			income.setPlanList(Integer.valueOf(StringUtil.nullToString(jsonMap.get("planlist"))));
			income.setHireList(Integer.valueOf(StringUtil.nullToString(jsonMap.get("hirelist"))));
			//income.setHireType(hireType);
			dictData = new DictionaryData();
			dictData.setId(StringUtil.nullToString(jsonMap.get("balancemode")));
			income.setBalanceMode(dictData);
			income.setHireDate(StringUtil.nullToString(jsonMap.get("hiredate")));
			dictData = new DictionaryData();
			dictData.setId("currency_type1");
			income.setCurrency(dictData);
			income.setRent(new BigDecimal(StringUtil.nullToString(jsonMap.get("rent"),"0")));
			income.setCorpus(new BigDecimal(StringUtil.nullToString(jsonMap.get("corpus"),"0")));
			income.setInterest(new BigDecimal(StringUtil.nullToString(jsonMap.get("interest"),"0")));
			income.setPenalty(new BigDecimal(StringUtil.nullToString(jsonMap.get("penalty"),"0")));
			income.setRentAdjust(new BigDecimal(StringUtil.nullToString(jsonMap.get("rentadjust"),"0")));
			income.setCorpusAdjust(new BigDecimal(StringUtil.nullToString(jsonMap.get("corpusadjust"),"0")));
			income.setInterestAdjust(new BigDecimal(StringUtil.nullToString(jsonMap.get("interestadjust"),"0")));
			income.setPenaltyAdjust(new BigDecimal(StringUtil.nullToString(jsonMap.get("penaltyadjust"),"0")));
			income.setAccountingDate(StringUtil.nullToString(jsonMap.get("accountingdate")));
			//income.setInsteadFlag();
			income.setMemo(variablesMap.get("penalyadjustmemo"));
			income.setRollBack("0");
			income.setCreator(user);
			income.setCreateDate(nowdate);
			incomeList.add(income);
		}
		this.baseService.saveAllEntities(incomeList);//保存罚息减免信息
	}
}
