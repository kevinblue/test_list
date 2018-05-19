package com.tenwa.leasing.serviceImpl.litigationConclusion;

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
import com.tenwa.leasing.entity.fivecategoryapply.FiveCategoryApply;
import com.tenwa.leasing.entity.fivecategoryapply.FiveCategoryApplyDetail;
import com.tenwa.leasing.entity.lawmng.LawApproval;
import com.tenwa.leasing.entity.litigation.LitigationDetail;
import com.tenwa.leasing.service.litigationConclusion.LitigationConclusionService;
import com.tenwa.leasing.utils.WorkflowUtil;
@Service(value = "litigationConclusionRegistrationService")
public class LitigationConclusionServiceImpl implements LitigationConclusionService{
	@Resource(name = "tableService")
	private TableService tableService;
	@Override
	public void updatelitigationConclusionRegistrationApplication(
			Map<String, String> variablesMap) throws Exception {
	}

	@Override
	//新增结论
	public void addlitigationConclusionRegistrationApplication(
			Map<String, String> variablesMap) throws Exception {
		LitigationDetail litigationDetail = new LitigationDetail();
		this.tableService.getBussinessDao().getHibernateTemplate().flush();
		String json_litigation_str = variablesMap
				.get("json_litigation_str");
		JSONArray jsonarry = new JSONArray(json_litigation_str);

		List<FiveCategoryApplyDetail> detaillist = new ArrayList<FiveCategoryApplyDetail>();
		for (int i = 0; i < jsonarry.length(); i++) {
			FiveCategoryApplyDetail ad = new FiveCategoryApplyDetail();
			this.tableService.copyAndOverrideExistedValueFromJSONObject(
					jsonarry.getJSONObject(i), ad, null, "");
			
			detaillist.add(ad);
		}
		this.tableService.saveAllEntities(detaillist);
		
		
	}

	@Override
	public void deletelitigationConclusionRegistrationApplication(
			Map<String, String> variablesMap) throws Exception {
		
	}

}
