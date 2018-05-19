package com.tenwa.leasing.serviceImpl.Proj.projAddFile;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.AppStaticUtil;
import com.tenwa.leasing.entity.proj.ProjInfo;
import com.tenwa.leasing.service.Proj.proComm.ProCommService;
import com.tenwa.leasing.service.Proj.projAddFile.ProjAddFileService;


@Service(value = "projAddFileService")
public class ProjAddFileServiceImpl implements ProjAddFileService {

	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "proCommService")
	private ProCommService proCommService;
	
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Override
	public void saveProjectInfo(Map<String, String> variablesMap) throws Exception {
		
		variablesMap.remove("proj_info.projcondition");
        String fileaddname= variablesMap.get("proj_info.fileaddname");
        String fileaddexplain= variablesMap.get("proj_info.fileaddexplain");
	    String id=variablesMap.get("proj_info.id");
		ProjInfo projInfo =  this.tableService.findEntityByID(ProjInfo.class, id);		
		projInfo.setFileAddName(fileaddname);		
		projInfo.setFileAddExplain(fileaddexplain);
		this.tableService.saveOrUpdateEntity(projInfo);
		
	}
}
