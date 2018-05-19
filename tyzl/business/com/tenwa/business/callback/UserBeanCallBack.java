package com.tenwa.business.callback;

import java.util.List;
import java.util.Map;

import com.tenwa.business.constant.OperTypeEnum;
import com.tenwa.business.entity.User;
import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.utils.MD5Util;
@SuppressWarnings({"rawtypes","unchecked"})
public class UserBeanCallBack implements EntityBeanCallBack {

	@Override
	public void beforePersistent(BaseService txService,List entityBeanList,List entityIdList,Map<String,String> paramMap, OperTypeEnum operType)throws Exception {
		
		List<User> userBeanList = (List<User>)entityBeanList;
		//List<String> userIdList   = (List<String>)entityIdList;
		
		switch(operType){
	          case ADD:
	          case COPY:{
	        	  User userBean = userBeanList.get(0);
	        	  userBean.setPassword("TEMP_PASSWORD");
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
		
		List<User> userBeanList = (List<User>)entityBeanList;
		//List<String> userIdList   = (List<String>)entityIdList;
		
		switch(operType){
	          case ADD:
	          case COPY:{
	        	  User userBean = userBeanList.get(0);
	        	  String newPassword = MD5Util.getMD5EncodedPasswordWithSalt("111111", userBean.getId());
	        	  userBean.setPassword(newPassword);
	        	  txService.updateEntity(userBean);
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
