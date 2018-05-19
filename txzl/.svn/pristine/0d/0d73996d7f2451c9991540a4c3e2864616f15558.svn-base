package com.tenwa.business.serviceImpl;

import javax.annotation.Resource;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.dao.DataAccessException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tenwa.business.dao.AclDao;
import com.tenwa.business.entity.User;
@Service(value="userDetailsService")
public class UserDetailsServiceImpl   extends AuthorizingRealm  implements UserDetailsService
{
	@Resource(name="aclDao")
	private AclDao aclDao;
	
	@Resource(name="credentialsMatcher")
	public void setUpperCredentialsMatcher(CredentialsMatcher credentialsMatcher){
		this.setCredentialsMatcher(credentialsMatcher) ;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException
	{
		try {
			return this.aclDao.findUserByUserName(username);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("username {0} not found ".replace("{0}", username));
		}
	}
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token; 
		String username = upToken.getUsername();
		User user = null;
		try {
			user = (User)this.loadUserByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AccountException("username {0} not found ".replace("{0}", username));
		}
        if (null == user) { 
           throw new UnknownAccountException("No account found for user [" + username + "]"); 
        } 
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName()); 
	}
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		User user = (User) principals.fromRealm(getName()).iterator().next(); 
	     if (user != null) { 
	          SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(); 
	          info.addRole("ROLE_USER"); 
		      info.addStringPermission("ROLE_USER"); 
	          return info; 
	     } 
	     else { 
	        return null; 
	     } 
	}
}
