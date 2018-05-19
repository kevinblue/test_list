package com.tenwa.leasing.serviceImpl.Proj.projReconsider;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tenwa.business.service.BaseService;
import com.tenwa.leasing.entity.proj.ProjCreditWorkFlowInfo;
import com.tenwa.leasing.entity.proj.ProjInfo;
import com.tenwa.leasing.service.Proj.projReconsider.ProjReconsiderService;

@Service(value = "projReconsiderService")
public class ProjReconsiderServiceImpl implements ProjReconsiderService{
	
	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Override
	public void getProjCreditInfo(
			Map<String, String> variablesMap,ProjInfo projInfo,ProjCreditWorkFlowInfo projCreditWorkFlowInfo) throws Exception {
		List<ProjCreditWorkFlowInfo> projCreditWorkFlowInfos=baseService.findResultsByHSQL("from ProjCreditWorkFlowInfo where projId=?", projInfo);
		if(null!=projCreditWorkFlowInfos&&projCreditWorkFlowInfos.size()>0){
			projCreditWorkFlowInfo = projCreditWorkFlowInfos.get(0);
		}
	}
}
