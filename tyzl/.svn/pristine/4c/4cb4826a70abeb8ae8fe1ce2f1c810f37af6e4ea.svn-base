package com.tenwa.leasing.serviceImpl.fund.fundcollection;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.base.FundEbankData;
import com.tenwa.leasing.entity.base.FundEbankProcess;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.fund.ContractFundFundCharge;
import com.tenwa.leasing.entity.fund.ContractFundFundPlan;
import com.tenwa.leasing.service.fund.fundcollection.FundCollectionService;

@Service(value="fundCollectionService")
public class FundCollectionServiceImpl implements FundCollectionService{

	@Resource(name="tableService")
	private TableService tableService;
	
	/*获取本次收款明细前台json ,持久化实收表和网银信息表*/
	@Override
	public void getCollectJsonObjStr(JSONObject JObj,ContractInfo contractInfo,FundEbankData fundEbankData)
	{
		  try{
			  ContractFundFundCharge chagObj = new ContractFundFundCharge();
			  chagObj.setContractId(contractInfo);
			  chagObj.setFeeAdjust(new BigDecimal(JObj.has("clientaccount")?JObj.getString("feeadjust"):"0"));//金额调整
			  chagObj.setPayType(this.tableService.findEntityByID(DictionaryData.class,"pay_type_in"));
			  chagObj.setCreator(SecurityUtil.getPrincipal());
			  chagObj.setCreateDate(DateUtil.getSystemDateTime());
			  chagObj.setFeeType(this.tableService.findEntityByID(DictionaryData.class,JObj.optString("feetype")));
			  chagObj.setSettleMethod(this.tableService.findEntityByID(DictionaryData.class, JObj.optString("settlemethod")));
			  chagObj.setEbankNumber(fundEbankData);
			  
			  chagObj.setFactMoney( new BigDecimal(JObj.has("ownbank")?JObj.getString("factmoney"):"0") );
			  chagObj.setFactDate(JObj.has("factdate")?JObj.getString("factdate"):"");
			  chagObj.setClientAccnumber(JObj.has("clientaccnumber")?JObj.getString("clientaccnumber"):"");
			  chagObj.setClientAccount(JObj.has("clientaccount")?JObj.getString("clientaccount"):"");
			  chagObj.setClientBank(JObj.has("clientbank")?JObj.getString("clientbank"):"");
			  chagObj.setFactObject(JObj.has("clientname")?JObj.getString("clientname"):"");
			  chagObj.setAccNumber(JObj.has("ownaccnumber")?JObj.getString("ownaccnumber"):"");
			  chagObj.setAccount(JObj.has("ownaccount")?JObj.getString("ownaccount"):"");
			  chagObj.setAccountBank(JObj.has("ownbank")?JObj.getString("ownbank"):"");
			  chagObj.setAccountingDate(JObj.has("accountingdate")?JObj.getString("accountingdate"):"");
			  chagObj.setFfcmemo(JObj.has("ffcmemo")?JObj.getString("ffcmemo"):"");
			  chagObj.setCurrency(this.tableService.findEntityByID(DictionaryData.class,"currency_type1"));
			  chagObj.setRoll_Back("0");//RollBack 【-1为 红冲  ,0或者 null 表非红冲】
			  
			  ContractFundFundPlan contractFundFundPlan = this.tableService.findEntityByID(ContractFundFundPlan.class,JObj.optString("paymentid"));
			  if( null != contractFundFundPlan)
			  {
				  chagObj.setFundFundChargePlan(contractFundFundPlan);
			  } 
			  System.out.println(JObj.getString("factdate")+":"+"收款资金金额："+JObj.getString("factmoney"));
			  
			  this.tableService.saveEntity(chagObj);//保存实收表
		  }
		  catch (Exception e){
			  e.printStackTrace();
			  e.getMessage();
		  }
	  }

	@Override
	public String addEbankInfoImpl(Map<String, String> mapProperties) {
		try
		{
			String contractid = mapProperties.get("contractid");
			String flowUnid = mapProperties.get("flowUnid");
			ContractInfo contractInfo= this.tableService.findEntityByID(ContractInfo.class,contractid);
			String ebid = mapProperties.get("ebid");
			BigDecimal curtotalmoney = new BigDecimal(mapProperties.get("curtotalmoney"));
			FundEbankData fundEbankData= this.tableService.findEntityByID(FundEbankData.class,ebid);
			
			//判断本次核销金额  和网银可用金额    compareTo  比较 -1表示小于，0是等于，1是大于。
			if( curtotalmoney.compareTo(fundEbankData.getMayOpeMoney()) ==1 ){
				return "目前网银可用金额 :"+fundEbankData.getMayOpeMoney()+"元  不足以核销本次收款金额 :"+curtotalmoney+"元";
			}
			else 
			{
				  //init FundEbankProcess
				  /*流程提交   时网银中间表里面插入一条记录 */
				  FundEbankProcess pro = new FundEbankProcess();
				  pro.setContractId(contractInfo.getId());
				  pro.setEbdataId(fundEbankData);
				  pro.setStartDate(DateUtil.getSystemDateDetailTime());
				  pro.setKeyOne(contractInfo.getContractNumber()+";"+contractInfo.getProjectName()+";"
						  +"到账时间"+fundEbankData.getFactDate());
				  //注明具体的流程实例id  用到了 这笔网银
				  pro.setFlowUnid(flowUnid);
				  pro.setProcessName("收款流程");
				  //插入网银过程表记录【1为已使用，-1为红冲,初始化过程表workflag 为3】
				  pro.setWork_flag("3");
				  pro.setProcessAmount( curtotalmoney );//插入可用金额
				  //logger.info("当前操作的网银信息: 时间"+fundEbankData.getFactDate() + "可用金额 : "+fundEbankData.getMayOpeMoney());
				  this.tableService.saveEntity(pro);//保存网银过程表	
				
				  //更新 网银的 新可核销金额   =原可核销金额 -本次已核销的金额
				  fundEbankData.setMayOpeMoney(fundEbankData.getMayOpeMoney().subtract(curtotalmoney));
				  //更新 网银  新已核销金额 = 原已核销金额 + 本次已核销金额
				  fundEbankData.setHadMoney(fundEbankData.getHadMoney().add(curtotalmoney));
				  this.tableService.updateEntity(fundEbankData);
				  return "no";
			}
			
		}catch (Exception e){
			return "失败"+e.getMessage()+e.getStackTrace();
		}
	}
	
	
}
