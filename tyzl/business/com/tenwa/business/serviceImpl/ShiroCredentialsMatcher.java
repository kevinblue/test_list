package com.tenwa.business.serviceImpl;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.utils.MD5Util;


 /**
 * 项目名称：    系统名称
 * 包名：              
 * 文件名：         ShiroCredentialsMatcher.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-9-7-下午09:53:13
 * Copyright：2013XX公司-版权所有
 **/

/**
 * 类名称：     ShiroCredentialsMatcher
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-9-7 下午09:53:13
 * 修改备注：
 * @version 1.0.0
 **/

@Component(value="credentialsMatcher")
public class ShiroCredentialsMatcher implements CredentialsMatcher {

	@Override
	public boolean doCredentialsMatch(AuthenticationToken authenticationtoken,
			AuthenticationInfo authenticationinfo) {
	    String tokenPassword = String.valueOf(((UsernamePasswordToken)authenticationtoken).getPassword()) ;
	    String userSaltPassword = String.valueOf(authenticationinfo.getCredentials());
	    User u = (User)authenticationinfo.getPrincipals().getPrimaryPrincipal();
	    String userId = u.getId();
	    String requestSaltPassword = MD5Util.getMD5EncodedPasswordWithSalt(tokenPassword, userId);
		return userSaltPassword.equals(requestSaltPassword);
	}

}
