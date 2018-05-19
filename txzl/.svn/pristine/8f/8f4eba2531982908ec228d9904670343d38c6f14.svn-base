package com.tenwa.leasing.serviceImpl.ebank;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.tenwa.business.callback.EntityBeanCallBack;
import com.tenwa.business.constant.OperTypeEnum;
import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.exception.BusinessException;
import com.tenwa.leasing.entity.base.FundEbankData;
import com.tenwa.leasing.entity.cust.CustInfo;
@SuppressWarnings({"rawtypes","unchecked"})
public class FundEbankBeanCallBack implements EntityBeanCallBack {

	@Resource(name="tableService")
	private TableService tableService;
	@Override
	public void beforePersistent(BaseService txService,List entityBeanList,List entityIdList,Map<String,String> paramMap, OperTypeEnum operType)throws Exception {
		
		List<FundEbankData> fundEbankDataBeanList = (List<FundEbankData>)entityBeanList;
		
		switch(operType){
	          case ADD:
	          case COPY:{
	        	  break;
	          }
	          case EDIT: {
	        	 /* FundEbankData fundEbankData = fundEbankDataBeanList.get(0);
	        	  CustInfo custInfo= this.tableService.findEntityByID(CustInfo.class,fundEbankData.getCustId().getId());
	        	  fundEbankData.setCustId(custInfo);*/
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
		
		List<FundEbankData> fundEbankDataBeanList = (List<FundEbankData>)entityBeanList;
		
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
