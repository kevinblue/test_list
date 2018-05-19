package com.tenwa.leasing.serviceImpl.Proj.projChange;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.reckon.service.RentConditionDataOperator;
import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.utils.AppStaticUtil;
import com.tenwa.leasing.entity.proj.ProjInfo;
import com.tenwa.leasing.service.Proj.proComm.ProCommService;
import com.tenwa.leasing.service.Proj.projChange.ProjChangeService;

/**   
*    
* 项目名称：tls5.1   
* 类名称：ProjChangeServiceImpl   
* 类描述：   项目变更保存数据
* 创建人：rovine   
* 创建时间：2014年11月19日 下午6:23:29   
* @version        
*/
@Service(value = "projChangeService")
public class ProjChangeServiceImpl implements ProjChangeService {

	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "proCommService")
	private ProCommService proCommService;
	
	@Override
	public void saveProjectInfo(Map<String, String> variablesMap) throws Exception {
		
		variablesMap.remove("proj_info.projcondition");
		ProjInfo projinfo=proCommService.saveProjInfo(variablesMap, AppStaticUtil.PROJ_APPROVAL);
		proCommService.saveProjEquipment(projinfo, variablesMap);
		proCommService.saveProjRentCalculation(projinfo, variablesMap);
		proCommService.saveProjGuaranteeMethod(projinfo, variablesMap);
		proCommService.saveProjGuaranteeEquipment(projinfo, variablesMap);
		proCommService.savProjInvoice(projinfo, variablesMap);			
	}
}
