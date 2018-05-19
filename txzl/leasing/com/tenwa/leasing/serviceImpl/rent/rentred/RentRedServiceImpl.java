package com.tenwa.leasing.serviceImpl.rent.rentred;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.kernal.utils.StringUtil;
import com.tenwa.leasing.entity.base.FundEbankData;
import com.tenwa.leasing.entity.fund.ContractFundFundCharge;
import com.tenwa.leasing.entity.fund.ContractFundRentInCome;
import com.tenwa.leasing.service.rent.rentred.RentRedService;

/**
 * 
 * @author lichaojie
 *
 */
@Service(value = "rentRedService")
public class RentRedServiceImpl implements RentRedService {

	@Resource(name = "tableService")
	private TableService tableService;
	
	@Override
	public void saveRentRedInfo(Map<String, String> variablesMap) throws Exception {
		User user = (User) SecurityUtil.getPrincipal();
		String nowdatetime = DateUtil.getSystemDateTime();
		String nowdate = DateUtil.getSystemDate();
		
		String contract_id = variablesMap.get("contract_info.contract_id");
		String json_rent_red_detail_str = variablesMap.get("json_rent_red_detail_str");
		
		ObjectMapper objectMapper = new ObjectMapper();
		List<Map<String, Object>> jsonList = objectMapper.readValue(json_rent_red_detail_str, List.class);
		
		Map<String, Object> jsonMap = null;
		
		ContractFundRentInCome rentRedIncome = null;
		ContractFundRentInCome income = null;
		ContractFundFundCharge charge = null;
		ContractFundFundCharge rentRedCharge = new ContractFundFundCharge();
		BigDecimal incomeCautionMoney = BigDecimal.ZERO;
		FundEbankData ebank = null;
		List<ContractFundRentInCome> rentRedList = new ArrayList<ContractFundRentInCome>();
		
		DictionaryData PayFund9 = new DictionaryData();
		PayFund9.setId("payfund9");
		
		for(int i = 0;i < jsonList.size();i++){
			jsonMap = jsonList.get(i);
			rentRedIncome = new ContractFundRentInCome();
			income = this.tableService.findEntityByID(ContractFundRentInCome.class, StringUtil.nullToString(jsonMap.get("id")));
			BeanUtils.copyProperties(income, rentRedIncome);
			income.setRollBack("1");
			income.setModificator(user);
			income.setModifyDate(nowdatetime);
			rentRedIncome.setId(null);
			rentRedIncome.setRollBack("-1");
			rentRedIncome.setCreator(user);
			rentRedIncome.setCreateDate(nowdatetime);
			
			if(income.getEbankNumber() != null){
				ebank = income.getEbankNumber();
				ebank.setHadMoney(ebank.getHadMoney().subtract(income.getRent()).subtract(income.getPenalty()));
				ebank.setMayOpeMoney(ebank.getMayOpeMoney().add(income.getRent()).add(income.getPenalty()));
				ebank.setModificator(user);
				ebank.setModifyDate(nowdatetime);
				this.tableService.updateEntity(ebank);
			}else if(income.getPID() != null){
				charge = this.tableService.findEntityByID(ContractFundFundCharge.class, income.getPID());
				BeanUtils.copyProperties(charge, rentRedCharge);
			}
			
			this.tableService.updateEntity(income);
			this.tableService.saveEntity(rentRedIncome);
		}
		
		if(incomeCautionMoney.compareTo(BigDecimal.ZERO) == 1){
			rentRedCharge.setId(null);
			rentRedCharge.setFactMoney(incomeCautionMoney);
			rentRedCharge.setFactDate(nowdate);
			rentRedCharge.setEbankNumber(null);
			rentRedCharge.setSettleMethod(PayFund9);
			rentRedCharge.setCreator(user);
			rentRedCharge.setCreateDate(nowdate);
			this.tableService.saveEntity(rentRedCharge);
		}
		
	}
}
