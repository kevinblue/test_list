
 /**
 * 项目名称：    系统名称
 * 包名：              com.business.serviceImpl.cust
 * 文件名：         CustServiceImpl.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-5-18-下午12:30:07
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.leasing.serviceImpl.sysmgr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tenwa.business.entity.ExclusionInfo;
import com.tenwa.business.entity.User;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.service.sysmgr.WorkFlowProcessRejectService;





/**
 * @author Administrator
 *
 */
@Service(value="workFlowProcessRejectService")
public class WorkFlowProcessRejectServiceImpl implements
		WorkFlowProcessRejectService {
	@Resource(name = "tableService")
	private TableService tableService;

	@Override
	public void addOrRemoveReject(Map<String, String> model) throws Exception {
		Map<String, Object> propertiesMap = new HashMap<String, Object>();
		propertiesMap.put("workFlowNameA", model.get("workFlowNameA"));
		propertiesMap.put("workFlowNameB", model.get("workFlowNameB"));
		List<ExclusionInfo> proList = this.tableService.findEntityByProperties(
				ExclusionInfo.class, propertiesMap);
		if (proList.size() > 0 && null != proList) {
			if (proList.size() > 0) {
				ExclusionInfo exclusionInfo = proList.get(0);
				this.tableService.removeEntity(exclusionInfo);
			}
		} else {
			ExclusionInfo exclusionInfo = new ExclusionInfo();
			exclusionInfo.setWorkFlowNameA(model.get("workFlowNameA"));
			exclusionInfo.setWorkFlowNameB(model.get("workFlowNameB"));
			exclusionInfo.setWorkFlowDisplayName(model.get("workFlowNameC"));
			String  currentTime = DateUtil.getSystemDateTime();
			User currentUser = SecurityUtil.getPrincipal();
			exclusionInfo.setCreateDate(currentTime);
			exclusionInfo.setCreator(currentUser);
			this.tableService.saveEntity(exclusionInfo);
			//流程互斥插入(workFlowNameA,workFlowNameB)时判断反向数据(workFlowNameB,workFlowNameA)是否存在 如果不存在插入
			propertiesMap.put("workFlowNameA", model.get("workFlowNameB"));
			propertiesMap.put("workFlowNameB", model.get("workFlowNameA"));
			List<ExclusionInfo> reverseList = this.tableService.findEntityByProperties(
					ExclusionInfo.class, propertiesMap);
			if(reverseList.isEmpty()){
				ExclusionInfo exclusionInfo1 = new ExclusionInfo();
				exclusionInfo1.setWorkFlowNameA(model.get("workFlowNameB"));
				exclusionInfo1.setWorkFlowNameB(model.get("workFlowNameA"));
				exclusionInfo1.setWorkFlowDisplayName(model.get("workFlowNameC"));
				exclusionInfo1.setCreateDate(currentTime);
				exclusionInfo1.setCreator(currentUser);
				this.tableService.saveEntity(exclusionInfo1);
			}
		}

	}

}
