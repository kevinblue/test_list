package com.tenwa.business.callback;

import java.util.List;
import java.util.Map;

import com.tenwa.business.constant.OperTypeEnum;
import com.tenwa.business.service.BaseService;
@SuppressWarnings("rawtypes") 
public interface EntityBeanCallBack {
	public void beforePersistent(BaseService txService,List entityBeanList,List entityIdList,Map<String,String> paramMap, OperTypeEnum operType)throws Exception ;
	public void afterPersistent(BaseService txService,List entityBeanList,List entityIdList,Map<String,String> paramMap, OperTypeEnum operType)throws Exception ;
}
