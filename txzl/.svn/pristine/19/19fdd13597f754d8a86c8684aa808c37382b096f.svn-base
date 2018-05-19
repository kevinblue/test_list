package com.tenwa.leasing.serviceImpl.Proj.projCancel;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.utils.AppStaticUtil;
import com.tenwa.leasing.entity.proj.ProjInfo;
import com.tenwa.leasing.service.Proj.proComm.ProCommService;
import com.tenwa.leasing.service.Proj.projCancel.ProjCancelService;

/**   
*    
* 项目名称：tls5.1   
* 类名称：ProjCancelServiceImpl   
* 类描述：项目撤销结束保存数据入库
* 创建人：rovine   
* 创建时间：2014年11月19日 下午6:11:21   
* @version        
*/
@Service(value = "projCancelService")
public class ProjCancelServiceImpl implements ProjCancelService {

	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "proCommService")
	private ProCommService proCommService;
	
	@Override
	public void saveProjectInfo(Map<String, String> variablesMap) throws Exception {
		proCommService.saveProjInfo(variablesMap, AppStaticUtil.PROJ_CANCEL);
	}

	@Override
	public void saveProjectInfoStop(Map<String, String> variablesMap)
			throws Exception {

	proCommService.saveProjInfoStop(variablesMap, AppStaticUtil.PROJ_CANCEL);
		
	}
}
