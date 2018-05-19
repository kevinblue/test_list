package com.tenwa.leasing.serviceImpl.fileTemplate;

import java.util.List;
import java.util.Map;

import com.tenwa.business.callback.EntityBeanCallBack;
import com.tenwa.business.constant.OperTypeEnum;
import com.tenwa.business.service.BaseService;
import com.tenwa.leasing.entity.file.BaseFileTemplate;
import com.tenwa.leasing.entity.finance.FinancialSubjects;
import com.tenwa.leasing.utils.WorkflowUtil;

public class FileTemplateCreateCallBack implements EntityBeanCallBack {

	@Override
	public void afterPersistent(BaseService txService, List entityBeanList,List entityIdList, Map<String, String> paramMap,OperTypeEnum operType) throws Exception {

	}

	@Override
	public void beforePersistent(BaseService txService, List entityBeanList,List entityIdList, Map<String, String> paramMap,OperTypeEnum operType) throws Exception {
		List<BaseFileTemplate> userBeanList = (List<BaseFileTemplate>)entityBeanList;
		switch(operType){
	          case ADD:{
	        	  BaseFileTemplate ft = userBeanList.get(0);
	        	  ft.setTemplateNo(WorkflowUtil.getFileTemplateNoInfoSerialNumber(null, txService.getBussinessDao().getHibernateTemplate(), txService.getBussinessDao().getJdbcTemplate()));
	      		  
	        	  break;
	          }
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
