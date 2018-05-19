package com.tenwa.leasing.serviceImpl.rent.rentincome;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.kernal.utils.StringUtil;
import com.tenwa.leasing.entity.base.FundEbankData;
import com.tenwa.leasing.entity.base.FundEbankProcess;
import com.tenwa.leasing.entity.fund.ContractFundRentInCome;
import com.tenwa.leasing.entity.fund.ContractFundRentPlan;
import com.tenwa.leasing.service.rent.rentincome.RentIncomeService;

/**
 * 
 * @author lichaojie
 *
 */
@Service(value = "rentIncomeService")
public class RentIncomeServiceImpl implements RentIncomeService {

	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Override
	public void saveRentIncomeInfo(Map<String, String> variablesMap) throws Exception {
		//String contract_id = variablesMap.get("contract_info.id");
		//String mayopemoney = variablesMap.get("fund_ebank_data.mayopemoney");
		
		User user = (User) SecurityUtil.getPrincipal();
		String nowdate = DateUtil.getSystemDateTime();
		
		String ebankId = variablesMap.get("fund_ebank_data.id");
		String ownaccnumber = variablesMap.get("fund_ebank_data.ownaccnumber");
		String json_rent_income_detail_str = variablesMap.get("json_rent_income_detail_str");
		
		BigDecimal total = BigDecimal.ZERO;
		
		FundEbankData ebank = this.baseService.findEntityByID(FundEbankData.class, ebankId);
		
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
			//ContractFundRentInCome newIncome = objectMapper.readValue(jsonMap.toString(), ContractFundRentInCome.class);
			plan = this.baseService.findEntityByID(ContractFundRentPlan.class, jsonMap.get("id").toString());
			income.setPlanId(plan);
			income.setContractId(plan.getContractId());
			income.setDocId(variablesMap.get("docId"));
			income.setEbankNumber(ebank);
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
			income.setHireObject(StringUtil.nullToString(jsonMap.get("hireobject")));
			income.setHireBank(StringUtil.nullToString(jsonMap.get("hirebank")));
			income.setHireNumber(StringUtil.nullToString(jsonMap.get("hirenumber")));
			income.setOwnBank(StringUtil.nullToString(jsonMap.get("ownbank")));
			income.setOwnAccount(StringUtil.nullToString(jsonMap.get("ownaccount")));
			income.setOwnNumber(ownaccnumber);
			income.setAccountingDate(StringUtil.nullToString(jsonMap.get("accountingdate")));
			//income.setInsteadFlag();
			//income.setMemo();
			income.setRollBack("0");
			income.setCreator(user);
			income.setCreateDate(nowdate);
			incomeList.add(income);
			total = total.add(new BigDecimal(StringUtil.nullToString(jsonMap.get("rent"))));
		}
		this.baseService.saveAllEntities(incomeList);//保存租金实收信息
		
		//更新网银表已核销金额和可核销金额
		ebank.setHadMoney(ebank.getHadMoney().add(total));
		ebank.setMayOpeMoney(ebank.getMayOpeMoney().subtract(total));
		ebank.setModificator(user);
		ebank.setModifyDate(nowdate);
		this.baseService.updateEntity(ebank);
		
		//标记网银过程表状态
		FundEbankProcess ebankProcess = this.baseService.findEntityByID(FundEbankProcess.class, variablesMap.get("fund_ebank_process.id"));
		ebankProcess.setWork_flag("1");
		this.baseService.updateEntity(ebankProcess);
	}
	@Override
	public void deleteCheckedRecors(Map<String, String> model) throws Exception{
		 User user = new User();
		 user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();//获取登录人的uuid
		 String uuid = String.valueOf( UUID.randomUUID() );
		 System.out.println("call proc_plhx_hire('"+uuid+"','"+user.getId()+"')");
		 this.baseService.getBussinessDao().getJdbcTemplate().execute("call proc_plhx_hire('"+uuid+"','"+user.getId()+"')");
		 this.baseService.updateFlush();
		 model.put("docId",uuid);
		 //TODO 调用批量核销
/*		 if(ResourceUtil.getConfigValue("call.voucher.interface.switch").equals("true")){
				HessianProxyFactory factory = new HessianProxyFactory();
				CreateVoucherService service  = (CreateVoucherService)factory.create(CreateVoucherService.class, ResourceUtil.getConfigValue("call.voucher.interface.url"));
				service.rentIncomeBatch(model);
		 }*/
	}
}
