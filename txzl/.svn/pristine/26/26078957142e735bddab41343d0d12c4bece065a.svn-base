
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.kernal.tags
 * 文件名：         PermissionTag.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-5-8-下午06:53:18
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.kernal.tags;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.springframework.web.util.ExpressionEvaluationUtils;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.kernal.utils.WebUtil;


 /**
 * 类名称：     PermissionTag
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-5-8 下午06:53:18
 * 修改备注：
 * @version 1.0.0
 **/

public class ResourcePermissionTag extends  BodyTagSupport  {
	

	private static final long serialVersionUID = 1L;
	private  boolean isPermissionPassedFlag = false;
	private  String isContainPermission    = "true";
	private String code;
	public ResourcePermissionTag(){
		code        = "";
	}
	
	@Override
	public int doStartTag() throws JspException {
		try {
			isPermissionPassedFlag = isPermissionPassed();
		} catch (JspException e) {
			e.printStackTrace();
		}
		final boolean isSkipContent = ("false".equalsIgnoreCase(isContainPermission.trim())) ? isPermissionPassedFlag : !isPermissionPassedFlag;
		if(isSkipContent){
			String currentUrl = ((HttpServletRequest)this.pageContext.getRequest()).getRequestURL().toString();
			try {
				JspWriter out = this.pageContext.getOut();
				out.clear();
				out.print("<center>您没有访问页面 <font color='red'>"+currentUrl+"</font>的权限</center>");
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return BodyTag.SKIP_BODY;
		}
		return  BodyTag.EVAL_BODY_INCLUDE;
	}
	@Override
	public int doEndTag() throws JspException {
		if(!this.isPermissionPassedFlag){
			return BodyTag.SKIP_PAGE;
		}
		return BodyTag.EVAL_PAGE;
	}
	
	@Override
	public void setBodyContent(BodyContent b) {
		this.bodyContent =b;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setIsContainPermission(String isContainPermission) {
		this.isContainPermission = isContainPermission;
	}
	public String getIsContainPermission() {
		return isContainPermission;
	}
	
    private boolean isPermissionPassed() throws JspException {
    	boolean isPassed = false;
		String codeValue = ExpressionEvaluationUtils.evaluateString("code", code, pageContext);
    	User user    =  (User)SecurityUtil.getPrincipal();
		String username = user.getUsername();
		List<Map<String,String>> userOwnedResources = WebUtil.getUserOwnedResources().get(username);
		if(null != userOwnedResources){
			for(Map<String,String> dataMap : userOwnedResources){
				if(codeValue.equals(dataMap.get("code"))){
					isPassed = true;
					break;
				}
			}
		}
		return isPassed;
    }
}
