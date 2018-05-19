package com.tenwa.leasing.action.contract.assetcategory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.service.BaseService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.leasing.entity.contract.ContractFiveCategory;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.service.contract.contractCancel.ContractCancelService;

@WorkflowAction(name = "assetCategoryEndAction", description = "流程结束动作", workflowName = "资产分类流程")
@Component(value = "assetCategoryEndAction")
public class AssetCategoryEndAction implements JbpmBaseAction {

	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "contractCancelService")
	private ContractCancelService contractCancelService;
	
	@Override
	public void back(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		
		
		String asset_category_str=variablesMap.get("json_asset_category_str");
		Map<String, Object> propertiesMap = new HashMap<String, Object>();
		JSONArray jsonarray=new JSONArray(asset_category_str);
		for(int i=0;i<jsonarray.length();i++){
			JSONObject jsonobject=jsonarray.getJSONObject(i);
			propertiesMap.put("contractId.id", jsonobject.getString("contractid"));
			List<ContractFiveCategory> fiveCategoryList = this.baseService
					.findEntityByProperties(ContractFiveCategory.class,propertiesMap);
			DictionaryData dictfivetype = this.baseService
					.findEntityByID(DictionaryData.class, jsonobject.getString("classificationresult"));
			if(fiveCategoryList.size()>0){
				ContractFiveCategory category = fiveCategoryList.get(0);
				category.setContractfive_assets(dictfivetype);
				category.setLatecause(jsonobject.getString("latecause"));
				category.setImportantmatters(jsonobject.getString("importantmatters"));
				category.setAdjustmentreason(jsonobject.getString("adjustmentreason"));
				category.setCustvisit(jsonobject.getString("custvisit"));
				category.setAccidentlitigationsituation(jsonobject.getString("accidentlitigationsituation"));
				this.baseService.updateEntity(category);
			}else{
				ContractFiveCategory contractfivecategory = new ContractFiveCategory();
				ContractInfo contractInfo = this.baseService
						.findEntityByID(ContractInfo.class, jsonobject.getString("contractid"));
				contractfivecategory.setContractId(contractInfo);
				contractfivecategory.setContractfive_assets(dictfivetype);
				contractfivecategory.setLatecause(jsonobject.getString("latecause"));
				contractfivecategory.setImportantmatters(jsonobject.getString("importantmatters"));
				contractfivecategory.setAdjustmentreason(jsonobject.getString("adjustmentreason"));
				contractfivecategory.setCustvisit(jsonobject.getString("custvisit"));
				contractfivecategory.setAccidentlitigationsituation(jsonobject.getString("accidentlitigationsituation"));
				this.baseService.saveEntity(contractfivecategory);
			}
		}
		/*String asset_category_str = request.getParameter("json_asset_category_str");

		JSONArray jsonArray = new JSONArray(asset_category_str);
		Map<String, Object> propertiesMap = new HashMap<String, Object>();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			
			propertiesMap.put("contractId.id", jsonObject.getString("contractid"));
			List<ContractFiveCategory> fiveCategoryList = this.baseService
					.findEntityByProperties(ContractFiveCategory.class,propertiesMap);
			
			if (fiveCategoryList.size() > 0) {
				
				ContractFiveCategory category = fiveCategoryList.get(0);
				DictionaryData dictFivetype = this.baseService
						.findEntityByID(DictionaryData.class, jsonObject.getString("classificationresult"));
				
				category.setContractfive_business(dictFivetype);
				this.baseService.updateEntity(category);
				
			} else {
				ContractFiveCategory contractFiveCategory = new ContractFiveCategory();
				ContractInfo contractInfo = this.baseService
						.findEntityByID(ContractInfo.class, jsonObject.getString("contractid"));
				
				contractFiveCategory.setContractId(contractInfo);
				contractFiveCategory.setContractfivedate_business(DateUtil.getSystemDate());
				DictionaryData dictFivetype = this.baseService
						.findEntityByID(DictionaryData.class, jsonObject.getString("classificationresult"));
				
				contractFiveCategory.setContractfive_business(dictFivetype);
				contractFiveCategory.setCreateDate(DateUtil.getSystemDateTime());
				this.baseService.saveEntity(contractFiveCategory);
			}
			
		}*/
		
		
	
	}

	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

}
