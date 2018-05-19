
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.kernal.web.filter
 * 文件名：         ResourcePermissionFilter.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-6-26-下午12:09:23
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.kernal.web.filter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.WebUtils;

import com.tenwa.kernal.utils.WebUtil;


 /**
 * 类名称：     ResourcePermissionFilter
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-6-26 下午12:09:23
 * 修改备注：
 * @version 1.0.0
 **/

public class ResourcePermissionFilter extends GenericFilterBean
{
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest  request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		String requestUrl = request.getRequestURL().toString();
		String requestUri = request.getRequestURI();
		AntPathMatcher pathMatcher = new AntPathMatcher();
		Map<String, List<String>> resourceOwnedUsers = WebUtil.getResourceOwnedUsers();
		Object usernameObj = WebUtils.getSessionAttribute(request, "isHasUserLoginedUserName");
		try{
			if(null != usernameObj){
				String username = usernameObj.toString();
				for(String iterUrl : resourceOwnedUsers.keySet()){
					if(pathMatcher.match(iterUrl, requestUri)){
						if(!resourceOwnedUsers.get(iterUrl).contains(username)){
							response.sendRedirect(request.getContextPath()+"/accessDenied.jsp?username="+username+"&requestUrl="+requestUrl);
							return;
						}
						else{
							filterChain.doFilter(request, response);
						}
						break;
					}
				}
			}
			filterChain.doFilter(request, response);
		}catch(Exception e){
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}
		
	}

}
