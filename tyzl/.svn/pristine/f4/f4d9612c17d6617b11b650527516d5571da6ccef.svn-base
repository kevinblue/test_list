package com.tenwa.leasing.serviceImpl.Proj.projApproval;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.reckon.service.RentConditionDataOperator;
import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.utils.AppStaticUtil;
import com.tenwa.leasing.entity.proj.ProjInfo;
import com.tenwa.leasing.service.Proj.proComm.ProCommService;
import com.tenwa.leasing.service.Proj.projApproval.ProjApprovalService;

/**   
*    
* 项目名称：tls5.1   
* 类名称：ProjApprovalServiceImpl   
* 类描述：   项目立项保存数据
* 创建人：rovine   
* 创建时间：2014年11月19日 下午6:24:31   
* @version        
*/
@Service(value = "projApprovalService")
public class ProjApprovalServiceImpl implements ProjApprovalService {	
	@Resource(name = "proCommService")
	private ProCommService proCommService;
	@Override
	public void saveProjectInfo(Map<String, String> variablesMap) throws Exception {
		ProjInfo projinfo=proCommService.saveProjInfo(variablesMap, AppStaticUtil.PROJ_APPROVAL);
		proCommService.saveProjEquipment(projinfo, variablesMap);
		proCommService.saveProjRentCalculation(projinfo, variablesMap);
		proCommService.saveProjGuaranteeMethod(projinfo, variablesMap);
		proCommService.saveProjGuaranteeEquipment(projinfo, variablesMap);
		proCommService.savProjInvoice(projinfo, variablesMap);
	}
}
