package com.tenwa.business.callback;

import java.util.List;
import java.util.Map;

import com.tenwa.business.constant.OperTypeEnum;
import com.tenwa.business.service.BaseService;
import com.tenwa.leasing.entity.insurance.InsuranceInfo;
@SuppressWarnings({"rawtypes","unchecked"})
public class InsuranceInfoBeanCallBack implements EntityBeanCallBack {

	@Override
	public void beforePersistent(BaseService txService,List entityBeanList,List entityIdList,Map<String,String> paramMap, OperTypeEnum operType)throws Exception {
		
		switch(operType){
	          case ADD:
	          case COPY:{
	        	  break;
	          }
	          case EDIT: {
	        	  break;
	          }
	          case REMOVE: {
	        	  break;
	          }
          }
	}

	@Override
	public void afterPersistent(BaseService txService,List entityBeanList,List entityIdList, Map<String, String> paramMap, OperTypeEnum operType)
			throws Exception {
		
		List<InsuranceInfo> insuranceBeanList = (List<InsuranceInfo>)entityBeanList;
		//List<String> userIdList   = (List<String>)entityIdList;
		
		switch(operType){
	          case ADD:
	          case COPY:{
	          }
	          case EDIT: {
	        	  InsuranceInfo bean = insuranceBeanList.get(0);
	        	  String isinsure = paramMap.get("isinsure");
	        	  String ispaymentpremium = paramMap.get("ispaymentpremium");
	        	  isinsure = isinsure.equals("是") ? "0" : "1";
	        	  ispaymentpremium = ispaymentpremium.equals("是") ? "0" : "1";
	        	  bean.setIsInsure(isinsure);
	        	  bean.setIspaymentPremium(ispaymentpremium);
	        	  txService.updateEntity(bean);
	        	  break;
	          }
	          case REMOVE: {
	        	  break;
	          }
          }
	}

}
