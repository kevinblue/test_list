package com.tenwa.leasing.serviceImpl.assetsnetmonitor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.tenwa.leasing.entity.assetnetworkmonitor.AssetNetMonitorApply;
import com.tenwa.leasing.entity.assetnetworkmonitor.AssetNetMonitorApplyDetail;
import com.tenwa.leasing.service.assetsnetmonitor.AssetsNetMonitorService;
import com.tenwa.leasing.utils.WorkflowUtil;

@Service(value = "assetsNetMonitorService")
public class AssetsNetMonitorServiceImpl implements AssetsNetMonitorService {
	@Resource(name = "tableService")
	private TableService tableService;

	@Override
	public void updateAssetsNetMonitorApplication(
			Map<String, String> variablesMap) throws Exception {
		String applyid = variablesMap.get("applyid");
		AssetNetMonitorApply anma = this.tableService.findEntityByID(
				AssetNetMonitorApply.class, applyid);
		this.tableService.copyAndOverrideExistedValueFromStringMap(variablesMap,
				anma, null, "");
		//判断当申请编码为空时 修改保存自动生成申请编码
		if(anma.getApplynumber()==null){	
			String applynumber=WorkflowUtil.getAssetApplySerialNumber(variablesMap,this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
			anma.setApplynumber(applynumber);
		}
		anma.setModificator(SecurityUtil.getPrincipal());
		anma.setModifyDate(DateUtil.getSystemDateTime());
		this.tableService.updateEntity(anma);

	/*	Map<String, Object> propertiesMap = new HashMap<String, Object>();
		propertiesMap.put("applyid", anma);
		List<AssetNetMonitorApplyDetail> detaillist = new ArrayList<AssetNetMonitorApplyDetail>();// 原申请详细列表
		detaillist = this.tableService.findEntityByProperties(
				AssetNetMonitorApplyDetail.class, propertiesMap);
		Map<String, AssetNetMonitorApplyDetail> deleteMap = new HashMap<String, AssetNetMonitorApplyDetail>(); // 要删除的明细
		for (int j = 0; j < detaillist.size(); j++) {
			deleteMap.put(detaillist.get(j).getId(), detaillist.get(j));
		}
		List<AssetNetMonitorApplyDetail> detaillistnew = new ArrayList<AssetNetMonitorApplyDetail>();// 新增详细列表

		// 新增或修改明细
		String json_csut_apply_list_str = variablesMap
				.get("json_csut_apply_list_str");
		JSONArray json_arr = new JSONArray(json_csut_apply_list_str);
		AssetNetMonitorApplyDetail anmad_temp = new AssetNetMonitorApplyDetail();
		for (int i = 0; i < json_arr.length(); i++) {
			JSONObject obj = json_arr.getJSONObject(i);
			if ("".equals(obj.getString("id"))) {
				this.tableService
						.copyAndOverrideExistedValueFromJSONObject(obj,
								anmad_temp, null, "");
				anmad_temp.setApplyid(anma);
			} else {
				this.tableService
				.copyAndOverrideExistedValueFromJSONObject(obj,
						anmad_temp, null, "");
				if (deleteMap.containsKey(anmad_temp.getId())) {
					deleteMap.remove(anmad_temp.getId());
				}
			}
			detaillistnew.add(anmad_temp);
		}
		this.tableService.saveOrUpdateAllEntities(detaillistnew);*/
		// 删除
		/*for (String key : deleteMap.keySet()) {
			this.tableService.removeEntity(deleteMap.get(key));
		}*/
	}


	@Override
	public void addAssetsNetMonitor(AssetNetMonitorApply anma)
			throws Exception {		
		Map<String, String> variablesMap=new HashMap<String, String>();
		String applynumber=WorkflowUtil.getAssetApplySerialNumber(variablesMap,this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
		anma.setApplystatus("未审核");
		anma.setApplynumber(applynumber);
		anma.setApplydate(DateUtil.getSystemDateTime().substring(0,7));
		anma.setCreateDate(DateUtil.getSystemDateTime().substring(0,19));
		this.tableService.saveEntity(anma);
	}
	
	@Override
	public void addAssetsNetMonitorApplication(Map<String, String> variablesMap)
			throws Exception {
		AssetNetMonitorApply assetNetMonitorApply = this.tableService.findEntityByID(AssetNetMonitorApply.class, variablesMap.get("applyid"));
		
	//	String applynumber=WorkflowUtil.getAssetApplySerialNumber(variablesMap,this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
		
		/*this.tableService.copyAndOverrideExistedValueFromStringMap(variablesMap,
				assetNetMonitorApply, null, "");*/
		assetNetMonitorApply.setApplymemo(variablesMap.get("applymemo"));
		this.tableService.saveOrUpdateEntity(assetNetMonitorApply);
	//	this.tableService.saveEntity(assetNetMonitorApply);
	/*	this.tableService.getBussinessDao().getHibernateTemplate().flush();
		String json_csut_apply_list_str = variablesMap
				.get("json_csut_apply_list_str");
		JSONArray jsonarry = new JSONArray(json_csut_apply_list_str);

		List<AssetNetMonitorApplyDetail> detaillist = new ArrayList<AssetNetMonitorApplyDetail>();
		for (int i = 0; i < jsonarry.length(); i++) {
			AssetNetMonitorApplyDetail ad = new AssetNetMonitorApplyDetail();
			this.tableService.
			copyAndOverrideExistedValueFromJSONObject(jsonarry.getJSONObject(i), ad, null, "");
			ad.setApplyid(assetNetMonitorApply);
			if (ad.getBaidu() == null 
				&& ad.getExecutionnet() == null
				&& ad.getJudgmentnet() == null) {
				continue;
			}
			detaillist.add(ad);
		}
		this.tableService.saveAllEntities(detaillist);*/
		
	}

	@Override
	public void deleteAssetsNetMonitorApplication(
			Map<String, String> variablesMap) throws Exception {
		String applyid = variablesMap.get("applyid");

		AssetNetMonitorApply anma = this.tableService.findEntityByID(
				AssetNetMonitorApply.class, applyid);

		List<AssetNetMonitorApplyDetail> detaillist = new ArrayList<AssetNetMonitorApplyDetail>();
		Map<String, Object> propertiesMap = new HashMap<String, Object>();
		propertiesMap.put("applyid", anma);
		detaillist = this.tableService.findEntityByProperties(
				AssetNetMonitorApplyDetail.class, propertiesMap);
		this.tableService.removeAllEntites(detaillist);
		this.tableService.removeEntity(anma);
	}


	
	
}
