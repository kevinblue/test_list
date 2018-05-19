package com.tenwa.leasing.serviceImpl.filing;

import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tenwa.business.service.TableService;
import com.tenwa.leasing.entity.filingmng.LawFilingNotice;
import com.tenwa.leasing.entity.lawmng.LawApproval;
import com.tenwa.leasing.service.filing.LawFilingNoticeService;

@Service(value="lawFilingNoticeService")
public class LawFilingNoticeServiceImpl implements LawFilingNoticeService {
	
	@Resource(name = "tableService")
	private TableService tableService;

	@Resource(name="lawFilingNoticeService")
	private LawFilingNoticeService lawFilingNoticeService;
	
	@Override
	public void saveFilingInfo(Map<String, String> variablesMap)
			throws Exception {
		LawApproval lawapprovalInfo = this.tableService
				.findEntityByID(LawApproval.class, variablesMap.get("lawid"));
		String jsonFilingcostString = variablesMap
				.get("json_filing_cost_detail_str");
		this.tableService
		.updateOneToManyCollectionsNoRemoved(lawapprovalInfo, "filingInfos", LawFilingNotice.class, 
				"lawapprovalId", jsonFilingcostString,null);

	}

	@Override
	public void updateFilingInfo(Map<String, String> variablesMap)
			throws Exception {
		String jsonFilingString= variablesMap.get("json_filing_cost_his_detail_str");
		JSONArray ary = new JSONArray(jsonFilingString);
		for(int i=0;i<ary.length();i++){
			JSONObject obj = ary.getJSONObject(i); 
			String id = obj.optString("id");
			LawFilingNotice lawfilingInfo=this.tableService.findEntityByID(LawFilingNotice.class, id);
			lawfilingInfo.setDrawalDate(variablesMap.get("drawalDate"));
			lawfilingInfo.setDrawalreason(variablesMap.get("drawalreason"));
			this.tableService.updateEntity(lawfilingInfo);
		}
	}

}
