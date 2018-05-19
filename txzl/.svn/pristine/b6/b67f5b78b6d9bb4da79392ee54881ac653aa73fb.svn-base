
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.kernal.utils
 * 文件名：         SecurityUtil.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-9-9-上午12:07:31
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.kernal.utils;

import javax.annotation.Resource;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.tenwa.business.entity.User;
import com.tenwa.business.service.BaseService;


 /**
 * 类名称：     SecurityUtil
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-9-9 上午12:07:31
 * 修改备注：
 * @version 1.0.0
 **/

public class SecurityUtil {
	
		@Resource(name = "baseService")
		private static BaseService baseService;
	  
		private static final ThreadLocal<User> holder = new ThreadLocal<User>();   
		public static void putUser(User user)
		{   
		   holder.set(user);   
		}   
		public static User getUserId()
		{   
		   return holder.get();   
		}   
		public static void setCurrentUser(User user)
		{
			putUser(user);
		}
		public static User getCurrentUser()
		{
			return holder.get();  
		}
	
    public static User getPrincipal(){
    	//Object targetObj = (User)SecurityUtils.getSubject().getPrincipal();    
    	User loginedUser = null;
    	//app登录信息
    	if(getCurrentUser()!=null&&!"".equals(getCurrentUser())){
    		try {
    			loginedUser=getCurrentUser();
				System.out.println(loginedUser.toString());
			}  catch (Exception e) {
				e.printStackTrace();
			}
    	}else{
    		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        	if(null != auth){
            	loginedUser = (User)auth.getPrincipal();
        	}
    	}
        return loginedUser;
    }
}
