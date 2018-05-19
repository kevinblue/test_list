package com.tenwa.leasing.serviceImpl.asset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.springframework.stereotype.Service;

import com.tenwa.business.entity.User;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.asset.AssetMngApply;
import com.tenwa.leasing.entity.asset.AssetMngDetail;
import com.tenwa.leasing.service.asset.AssetsMngService;
import com.tenwa.leasing.utils.WorkflowUtil;

@Service(value = "assetsMngService")
public class AssetsMngServiceImpl implements AssetsMngService {
	@Resource(name = "tableService")
	private TableService tableService;


	@Override
	public void updateAssetMng(Map<String, String> variablesMap)throws Exception {
		
		String applyid = variablesMap.get("applyid");
		AssetMngApply anma = this.tableService.findEntityByID(AssetMngApply.class, applyid);
		this.tableService
		.copyAndOverrideExistedValueFromStringMap(variablesMap,anma, null, "");
		anma.setModificator(SecurityUtil.getPrincipal());
		anma.setModifyDate(DateUtil.getSystemDateTime());
		this.tableService.updateEntity(anma);
		
	}
	@Override
	public void addloadAssetMng(Map<String, String> variablesMap) throws Exception {
		
		User user = SecurityUtil.getPrincipal();
		String yearMonStr = DateUtil.getSystemDateTime().substring(0, 7) ,
			   applynumber ="", apid ="",apply_memo="";
		AssetMngApply assetMngApply = null;
		
		try{
			//防止刷新或者 重复点击新增  根据 状态、时间 、录入人查
			String sql = "SELECT * FROM ASSETMNG_APPLY  "
					+ "WHERE APPLY_DATE = '"+yearMonStr
					+ "' AND APPLY_STATUS='未审核' AND APPLY_USER= '"+user.getId()
					+ "' ";
			List<Map<String, Object>> applyList = this.tableService
					.queryListBySql(sql);
			
			if (applyList.size() > 0) {
				for (Map<String, Object> map : applyList) {
					for (String key : map.keySet()) 
					{
						
						if(key.equals("ID"))
							apid = map.get(key).toString();
						if(key.equals("APPLY_NUMBER"))
							applynumber = map.get(key).toString();
						if(key.equals("APPLY_MEMO"))
							apply_memo = map.get(key) != null ? map.get(key)
									.toString() : "";
					}
				}
				variablesMap.put("applymemo", apply_memo);
			} else {
				assetMngApply = new AssetMngApply();
				applynumber = WorkflowUtil.getAssetApplySerialNumber( variablesMap,
							this.tableService.getBussinessDao().getHibernateTemplate(), 
							this.tableService.getBussinessDao().getJdbcTemplate() );
				assetMngApply.setApplydate(yearMonStr);
				assetMngApply.setApplynumber(applynumber);
				assetMngApply.setApplystatus("未审核");
				assetMngApply.setApplyuser(user);
				assetMngApply.setCreateDate(DateUtil.getSystemDateTime());
				assetMngApply.setCreator(user);
				
				this.tableService.saveEntity(assetMngApply);
				apid = assetMngApply.getId();
			}
			variablesMap.put("applynumber", applynumber);
			variablesMap.put("assetMngApply", apid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void addAssetMng(Map<String, String> variablesMap) throws Exception {
		AssetMngApply assetMngApply = new AssetMngApply();
		String applynumber=WorkflowUtil.getAssetApplySerialNumber(variablesMap,this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
		
		this.tableService.copyAndOverrideExistedValueFromStringMap(variablesMap,
				assetMngApply, null, "");
		assetMngApply.setApplystatus("未审核");
		assetMngApply.setApplyuser(SecurityUtil.getPrincipal());
		assetMngApply.setApplynumber(applynumber);
		this.tableService.saveEntity(assetMngApply);
		this.tableService.getBussinessDao().getHibernateTemplate().flush();
		String json_csut_apply_list_str = variablesMap
				.get("json_csut_apply_list_str");
		JSONArray jsonarry = new JSONArray(json_csut_apply_list_str);

		List<AssetMngDetail> detaillist = new ArrayList<AssetMngDetail>();
		for (int i = 0; i < jsonarry.length(); i++) {
			AssetMngDetail ad = new AssetMngDetail();
			this.tableService.
			copyAndOverrideExistedValueFromJSONObject(jsonarry.getJSONObject(i), ad, null, "");
			ad.setApplyid(assetMngApply);
			detaillist.add(ad);
		}
		this.tableService.saveAllEntities(detaillist);
	}

	@Override
	public void deleteMng(Map<String, String> variablesMap) throws Exception {
		String applyid = variablesMap.get("applyid");

		AssetMngApply anma = this.tableService.findEntityByID(
				AssetMngApply.class, applyid);

		List<AssetMngDetail> detaillist = new ArrayList<AssetMngDetail>();
		Map<String, Object> propertiesMap = new HashMap<String, Object>();
		propertiesMap.put("applyid", anma);
		detaillist = this.tableService.findEntityByProperties(
				AssetMngDetail.class, propertiesMap);
		this.tableService.removeAllEntites(detaillist);
		this.tableService.removeEntity(anma);
	}
}
