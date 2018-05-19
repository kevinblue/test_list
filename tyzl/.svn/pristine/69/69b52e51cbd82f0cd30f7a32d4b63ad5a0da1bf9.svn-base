package com.tenwa.business.callback;

import java.util.List;
import java.util.Map;

import com.tenwa.business.constant.OperTypeEnum;
import com.tenwa.business.entity.I18nDictionary;
import com.tenwa.business.service.BaseService;
@SuppressWarnings({"rawtypes","unchecked"})
public class I18NCallBack implements EntityBeanCallBack {

	@Override
	public void beforePersistent(BaseService txService,List entityBeanList,List entityIdList,Map<String,String> paramMap, OperTypeEnum operType)throws Exception {
		
		List<I18nDictionary> userBeanList = (List<I18nDictionary>)entityBeanList;
		//List<String> userIdList   = (List<String>)entityIdList;
		//com.tenwa.leasing.entity.insurance.InsuranceClaim cannot be cast to com.tenwa.business.entity.Use
		switch(operType){
	          case ADD:
	        	  I18nDictionary ins = userBeanList.get(0);
	        	  ins.setEnabled(true);
	        	  break;
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
		
		
		//List<String> userIdList   = (List<String>)entityIdList;
		
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

}
