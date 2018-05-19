package com.reckon.renttranrate.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

import com.reckon.bean.SpecialRulesBean;
import com.reckon.commons.helper.RentCalculateHelper;
import com.reckon.controller.adjustInterest.AdjustInterestController;
import com.reckon.entity.contract.reckon.cash.ContractCashDetailTemp;
import com.reckon.entity.contract.reckon.cash.ContractFinaCashDetailTemp;
import com.reckon.entity.contract.reckon.condition.ContractConditionTemp;
import com.reckon.entity.contract.reckon.fina.ContractFinaRentPlanTemp;
import com.reckon.entity.contract.reckon.fund.ContractFundRentPlanTemp;
import com.reckon.entity.contract.reckon.fund.FundAdjustInterestContractTemp;
import com.reckon.entity.contract.reckon.fund.FundFundPlanTemp;
import com.reckon.entity.interest.FundStandardInterest;
import com.reckon.renttranrate.service.AjustInterestService;
import com.reckon.renttranrate.service.TransferInterestService;
import com.reckon.renttranrate.web.TransRateDo;
import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.leasing.action.contract.approval.ContractApprovalStartAction;
import com.tenwa.leasing.entity.contract.ContractInfo;



/**
 * 调息的实现类
 * 
 * @author 孙传良
 * @date 2013-10-17下午04:23:39
 * @info
 * @Copyright Tenwa
 */
@Service(value = "transferInterestService")
public class TransferInterestServiceImpl implements TransferInterestService {

	@Resource(name = "tableService")
	private TableService tableService;

	@Resource(name = "contractApprovalStartAction")
	private ContractApprovalStartAction contractApprovalStartAction;
	
	@Resource(name="ajustInterestService")
	public AjustInterestService ajustinterestservice;
	
	private Logger logger=Logger.getLogger(AdjustInterestController.class);
	
	@Override
	public String updateConditionByTransferInterest(String adjustId, String docId, String contractId) throws Exception {
		
		FundStandardInterest fsi = (FundStandardInterest) this.tableService.findEntityByID(FundStandardInterest.class, adjustId);
		Map<String,String> queryMainObjectMap=new HashMap<String, String>();
		queryMainObjectMap.put("contract_id", contractId);
		JSONArray array1= this.tableService.getJsonArrayData("eleasing/workflow/proj/proj_approval/special_regular.xml",queryMainObjectMap);
		if(null!=array1&&array1.length()>0){
			queryMainObjectMap.put("json_special_regular_str",array1.getJSONObject(0).optString("special_regular_jsons").replaceAll("\'", "\""));
		}
		List<SpecialRulesBean> srb= RentCalculateHelper.getSpecialRulesList(queryMainObjectMap);
		ContractInfo contractInfo = this.tableService.findEntityByID(ContractInfo.class, contractId);
		Hashtable<String, String> re_ht = new Hashtable<String, String>();
		TransRateDo rtrsi = new TransRateDo();
		//调息处理入口
		re_ht = rtrsi.processTransRate(adjustId, contractInfo.getId(), contractInfo.getContractPutNumber(), null, docId,srb);
		if ("true".equals(re_ht.get("isSucess"))) {
			if ("true".equals(re_ht.get("isOver"))) {
				FundAdjustInterestContractTemp faict = new FundAdjustInterestContractTemp();
				faict.setContractId(contractInfo);
				faict.setAdjustId(fsi);
				faict.setDocId(docId);
				faict.setStartList(Integer.parseInt(re_ht.get("startList")));
				faict.setRateOriginal(new BigDecimal(re_ht.get("oldRate")));
				faict.setRateAdjust(new BigDecimal(re_ht.get("newRate")));
				faict.setOldIrr(new BigDecimal(re_ht.get("oldIrr")));
				faict.setNewIrr(new BigDecimal(re_ht.get("newIrr")));
				//faict.setOldPlanIrr(new BigDecimal(re_ht.get("oldPlanIrr")));
				//faict.setNewPlanIrr(new BigDecimal(re_ht.get("newPlanIrr")));
				faict.setAdjustDate(re_ht.get("adjustDate"));
				// 增加调息状态
				faict.setModReason("his_rate_change");
				faict.setStatus("rate_change_in");
				this.tableService.saveEntity(faict);
			}
		}
		/*流程结束后再操作his表
		//....................................................
		Map<String, String> model = new HashMap<String, String>();
		model.put("docid", docId);
		model.put("contractId", contractId);
		//执行调息算法结束时，将租金计划正式表、临时表数据移往HIS表作为调息前、后记录
		
		this.ajustinterestservice.updateCopyFundRentPlanFromTempToHisBeforeAndAfter(model);
		//....................................................
		*/
		return re_ht.get("message");
	}

	@Override
	public String removeConditionByTransferInterest(String adjustId, String docId, String contractId) throws Exception {
		ContractInfo contractInfo = this.tableService.findEntityByID(ContractInfo.class, contractId);
		this.removeConditionDataFromTemp(contractInfo.getContractId(), docId, tableService);
		Map<String, Object> propertiesMap = new HashMap<String, Object>();
		propertiesMap.put("contractId", contractInfo);
		propertiesMap.put("docId", docId);
		List<FundAdjustInterestContractTemp> faict = this.tableService.findEntityByProperties(FundAdjustInterestContractTemp.class, propertiesMap);
		if (!faict.isEmpty()) {
			this.tableService.removeAllEntites(faict);
		}
		/*这段主要是OCC将调息前后HIS数据删除掉，5.1流程中不入租金计划his表
		//.........................................................................
		if(contractInfo == null ||  Tools.isNullOrEmpty(contractInfo.getId())){
			throw new Exception("调息合同号为空，无法删除HIS表调息对应本批次的数据!");
		}else{
			//执行调息撤销时，将调息前后HIS数据删除掉
			this.ajustinterestservice.deleteHisAllSqlOfTx(docId,contractInfo.getId());
		}
		//.........................................................................
		*/
		return "撤销调息成功!";
	}
	
	/**
	 * 删除临时表里的数据
	 * @param contract_id
	 * @param doc_id
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public void removeConditionDataFromTemp(String contract_id, String doc_id,BaseService tService)throws Exception {
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
		{//5.资金计划
			//先删除
			List<FundFundPlanTemp> fundFundPlanTemps=new ArrayList<FundFundPlanTemp>();
			fundFundPlanTemps=tService.findEntityByProperties(FundFundPlanTemp.class, propertiesMap);
			if(fundFundPlanTemps!=null&&fundFundPlanTemps.size()>0){
				tService.removeAllEntites(fundFundPlanTemps);
			}
		}
	}
}
