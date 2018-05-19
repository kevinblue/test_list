package com.tenwa.leasing.serviceImpl.sapmaindata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.business.service.TableService;
import com.tenwa.leasing.entity.cust.CustTypeInfo;
import com.tenwa.leasing.entity.sapmaindatainfo.DesignMasterDataInfo;
import com.tenwa.leasing.entity.sapmaindatainfo.SapMainDataInfo;
import com.tenwa.leasing.service.sapmaindata.SapMainDataService;

@Service(value="sapMainDataService")
public class SapMainDataServiceImpl implements SapMainDataService{
	
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Override
	public void saveSapMainDataInfo(Map<String, String> model) throws Exception {
	        SapMainDataInfo info = new SapMainDataInfo();
		    info.setSapNumber(model.get("Sap_Main_Data.sapnumber"));
		    info.setApplicantDept(model.get("Sap_Main_Data.applicantdept"));
	        info.setApplicantReason(model.get("Sap_Main_Data.applicantreason"));
	        info.setAlreadyBmc(model.get("Sap_Main_Data.alreadybmc"));
	        info.setIsReleaseBmc(model.get("Sap_Main_Data.isreleasebmc"));
	        info.setOthers(model.get("Sap_Main_Data.others"));
	        info.setEditText(model.get("Sap_Main_Data.edittext"));
	        info.setDesignMasterData(model.get("designmasterdata"));
	        info.setNeedBmc(model.get("Sap_Main_Data.needbmc"));
	        info.setNextStep(model.get("Sap_Main_Data.nextstep"));
	        info.setProjNumber(model.get("Sap_Main_Data.projnumber"));
	        info.setEcrNumber(model.get("Sap_Main_Data.ecrnumber"));
	        info.setCreateDate(model.get("Sap_Main_Data.createdate"));
            User users = this.tableService.findEntityByID(User.class, model.get("login_userid"));
	        info.setCreator(users);
	        this.tableService.saveEntity(info);
	        //保存SAP主数据
	        Map<String,Object> whereMap=null;
	        DictionaryData dict = new DictionaryData();
	        String sapData=model.get("designmasterdata");
			if(sapData!=null&&!sapData.equals("")){
				 String sapdatas[]=sapData.split(",");
				 List<DesignMasterDataInfo> dataInfos=new ArrayList<DesignMasterDataInfo>();
				 for (String temp:sapdatas) {
					 DesignMasterDataInfo dmdi=new DesignMasterDataInfo();
					 whereMap=new HashMap<String, Object>();
					 whereMap.put("code", temp);
					 dict=this.tableService.findEntityByProperties(DictionaryData.class, whereMap).get(0);
					 dmdi.setSapId(info);
					 dmdi.setMainData(dict);
					 dataInfos.add(dmdi);
				}
				 this.tableService.saveAllEntities(dataInfos);
			}
	        
	        
          
	}

}
