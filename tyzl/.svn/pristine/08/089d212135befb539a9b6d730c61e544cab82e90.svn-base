package com.reckon.controller.fundOverduerent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tenwa.business.controller.BaseController;
import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.entity.cust.CustInfoContact;
import com.tenwa.leasing.entity.dun.DunningRecord;

@Controller(value = "DunningRecordController")
@RequestMapping(value = "/**/acl")
public class DunningRecordController extends BaseController {
	private Logger logger=Logger.getLogger(FundOverduerentController.class);
	@Resource(name = "baseService")
	private BaseService baseService;
	
	
	@RequestMapping(value = "/addDunningRecord.acl")
	public String addDunningRecord(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		 String jsonArrayString=model.get("add_json");
		 //List<CustInfoContact> info = (List<CustInfoContact>)  this.baseService.getEntitiesByJSONArrayString(CustInfoContact.class, jsonArrayString, null,true);
		 List<CustInfoContact> info = null;

		 this.baseService.saveOrUpdateAllEntities(info);
		 
		 if(logger.isInfoEnabled()){
			 logger.info("修改成功!");
		 }
		return null;
	}
	@RequestMapping(value = "/removeDunningRecord.acl")
	public String removeDunningRecord1(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		CustInfoContact info = this.baseService.findEntityByID(CustInfoContact.class,model.get("id"));
		 
		 
		 this.baseService.removeEntity(info);
		 if(logger.isInfoEnabled()){
			 logger.info("删除成功!");
		 }
		return null;
	}
}
