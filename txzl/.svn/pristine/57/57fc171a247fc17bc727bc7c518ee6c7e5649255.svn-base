package com.tenwa.leasing.serviceImpl.fivecategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.fivecategoryapply.FiveCategoryApply;
import com.tenwa.leasing.entity.fivecategoryapply.FiveCategoryApplyDetail;
import com.tenwa.leasing.service.fivecategory.FiveCategoryService;
import com.tenwa.leasing.utils.WorkflowUtil;

@Service(value = "fiveCategoryService")
public class FiveCategoryServiceImpl implements FiveCategoryService {
	@Resource(name = "tableService")
	private TableService tableService;

	@Override
	public void updateFiveCategoryApplication(
			Map<String, String> variablesMap) throws Exception {
		String applyid = variablesMap.get("applyid");
		FiveCategoryApply anma = this.tableService.findEntityByID(
				FiveCategoryApply.class, applyid);
		this.tableService.copyAndOverrideExistedValueFromStringMap(variablesMap,
				anma, null, "");
		//判断申请编号是不是为空值，如果为空则给申请编号赋值
/*		if(anma.getApplynumber()==null){
			anma.setApplynumber(WorkflowUtil.getFiveCategoryApplySerialNumber(variablesMap,this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate())
			);}*/
		anma.setModificator(SecurityUtil.getPrincipal());
		anma.setModifyDate(DateUtil.getSystemDateTime());
		this.tableService.updateEntity(anma);
		
	//

		/*Map<String, Object> propertiesMap = new HashMap<String, Object>();
		propertiesMap.put("applyid", anma);
		List<FiveCategoryApplyDetail> detaillist = new ArrayList<FiveCategoryApplyDetail>();// 原申请详细列表
		detaillist = this.tableService.findEntityByProperties(
				FiveCategoryApplyDetail.class, propertiesMap);
		Map<String, FiveCategoryApplyDetail> deleteMap = new HashMap<String, FiveCategoryApplyDetail>(); // 要删除的明细
		for (int j = 0; j < detaillist.size(); j++) {
			deleteMap.put(detaillist.get(j).getId(), detaillist.get(j));
		}
		List<FiveCategoryApplyDetail> detaillistnew = new ArrayList<FiveCategoryApplyDetail>();// 新增详细列表
*/
		/*// 新增或修改明细
		String json_csut_apply_list_str = variablesMap
				.get("json_csut_apply_list_str");
		JSONArray json_arr = new JSONArray(json_csut_apply_list_str);
		FiveCategoryApplyDetail anmad_temp = new FiveCategoryApplyDetail();
		for (int i = 0; i < json_arr.length(); i++) {
			JSONObject obj = json_arr.getJSONObject(i);
			if(!obj.has("id"))break;
			if ("".equals(obj.getString("id"))){
				this.tableService
						.copyAndOverrideExistedValueFromJSONObject(obj,
								anmad_temp, null, ""); 	
				anmad_temp.setApplyid(anma);
			} else {
				anmad_temp = this.tableService.findEntityByID(FiveCategoryApplyDetail.class, obj.getString("id"));
				this.tableService
				.copyAndOverrideExistedValueFromJSONObject(obj,
						anmad_temp, null, "");
				if (deleteMap.containsKey(anmad_temp.getId())) {
					deleteMap.remove(anmad_temp.getId());
				}
			}
			detaillistnew.add(anmad_temp);
		}
		this.tableService.saveOrUpdateAllEntities(detaillistnew);
		// 删除
		for (String key : deleteMap.keySet()) {
			this.tableService.removeEntity(deleteMap.get(key));
		}*/
	}
	
	@Override
	public void addFiveCategoryApplication(FiveCategoryApply fcga)
			throws Exception {
		Map<String, String> propertiesMap = new HashMap<String, String>();
		String applynumber=WorkflowUtil.getFiveCategoryApplySerialNumber(propertiesMap,
				this.tableService.getBussinessDao().getHibernateTemplate(), 
				this.tableService.getBussinessDao().getJdbcTemplate());
		
		fcga.setApplyuser(SecurityUtil.getPrincipal());
		fcga.setApplystatus("未审核");
		fcga.setApplynumber(applynumber);
		fcga.setApplydate(DateUtil.getSystemDateTime().substring(0, 7));
		fcga.setCreateDate(DateUtil.getSystemDateTime().substring(0, 19));
		
		this.tableService.saveEntity(fcga);
		
	}

	@Override
	public void addFiveCategoryApplication(Map<String, String> variablesMap)
			throws Exception {
		
		FiveCategoryApply fiveCategoryApply =this.tableService.findEntityByID(FiveCategoryApply.class, variablesMap.get("applyid"));
		String applynumber=WorkflowUtil.getFiveCategoryApplySerialNumber(variablesMap,this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
		

		this.tableService.copyAndOverrideExistedValueFromStringMap(variablesMap,
				fiveCategoryApply, null, "");
		fiveCategoryApply.setApplystatus("未审核");
		fiveCategoryApply.setApplyuser(SecurityUtil.getPrincipal());
		fiveCategoryApply.setApplynumber(applynumber);
		
		
		this.tableService.updateEntity(fiveCategoryApply);
		
	}

	@Override
	public void deleteFiveCategoryApplication(
			Map<String, String> variablesMap) throws Exception {
		String applyid = variablesMap.get("applyid");

		FiveCategoryApply anma = this.tableService.findEntityByID(
				FiveCategoryApply.class, applyid);

		List<FiveCategoryApplyDetail> detaillist = new ArrayList<FiveCategoryApplyDetail>();
		Map<String, Object> propertiesMap = new HashMap<String, Object>();
		propertiesMap.put("applyid", anma);
		detaillist = this.tableService.findEntityByProperties(
				FiveCategoryApplyDetail.class, propertiesMap);
		this.tableService.removeAllEntites(detaillist);
		this.tableService.removeEntity(anma);
	}

	@Override
	public void updateFiveCategory(Map<String, String> variablesMap)
			throws Exception {
		String id=variablesMap.get("id");
		List<FiveCategoryApplyDetail> fivelist=new ArrayList<FiveCategoryApplyDetail>();
		FiveCategoryApplyDetail fcad=new FiveCategoryApplyDetail();
		if(id==null){
			ContractInfo ci=this.tableService.findEntityByID(ContractInfo.class, variablesMap.get("contractid"));
			FiveCategoryApply fca=this.tableService.findEntityByID(FiveCategoryApply.class, variablesMap.get("applyid"));
			fcad.setContractId(ci);
			fcad.setApplyid(fca);
			fcad.setCustname(variablesMap.get("custname"));
			fcad.setCode(variablesMap.get("codeno"));
			fcad.setLatecause(variablesMap.get("latecause"));
			fcad.setImportantmatters(variablesMap.get("importantmatters"));
			fcad.setCustvisit(variablesMap.get("custvisit"));
			fcad.setAccidentlitigationsituation(variablesMap.get("accidentlitigationsituation"));
			fcad.setClassificationresult(variablesMap.get("classificationresult"));
			fcad.setAdjustmentreason(variablesMap.get("adjustmentreason"));
			fivelist.add(fcad);
		}else{
			fcad=this.tableService.findEntityByID(FiveCategoryApplyDetail.class, id);
			fcad.setLatecause(variablesMap.get("latecause"));
			fcad.setImportantmatters(variablesMap.get("importantmatters"));
			fcad.setCustvisit(variablesMap.get("custvisit"));
			fcad.setAccidentlitigationsituation(variablesMap.get("accidentlitigationsituation"));
			fcad.setClassificationresult(variablesMap.get("classificationresult"));
			fcad.setAdjustmentreason(variablesMap.get("adjustmentreason"));
			fivelist.add(fcad);
		}
		this.tableService.saveOrUpdateAllEntities(fivelist);
		
	}


}
