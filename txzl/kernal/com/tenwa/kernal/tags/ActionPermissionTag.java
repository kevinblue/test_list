
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.kernal.tags
 * 文件名：         PermissionTag.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-5-8-下午06:53:18
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.kernal.tags;

import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
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

public class ActionPermissionTag extends  BodyTagSupport  {
	
	private static final long serialVersionUID = 1L;
	private  boolean isPermissionPassedFlag = false;
	private  String isContainPermission    = "true";
	private String code;
	public ActionPermissionTag(){
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
			return BodyTag.SKIP_BODY;
		}
		
		return  BodyTag.EVAL_BODY_INCLUDE;
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
		List<Map<String,String>> userOwnedActions = WebUtil.getUserOwnedActions().get(username);
		if(null != userOwnedActions){
			for(Map<String,String> dataMap : userOwnedActions){
				if(codeValue.equals(dataMap.get("code"))){
					isPassed = true;
					break;
				}
			}
		}
		return isPassed;
    }
}
