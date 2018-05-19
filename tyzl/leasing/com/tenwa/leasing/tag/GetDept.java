package com.tenwa.leasing.tag;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.tenwa.business.entity.Department;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.kernal.utils.EncodeFilter;
import com.tenwa.leasing.utils.StrTools;

@FieldName(name = "获取参数据配置")
public class GetDept extends TagSupport {
    private String name;
    private String scope="request";
    private String valuetype="name";
	@Override
	public int doEndTag() throws JspException {
		 return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		 try {
		     JspWriter out = this.pageContext.getOut();	
		     String value="";
		     if(this.scope.equals("request")){
		    	 ServletRequest request=this.pageContext.getRequest();
		    	 if(null!=this.name&&this.name.length()>0){
		    	    if(null!=request.getAttribute(this.getName())){
		    			value=request.getAttribute(this.getName()).toString();
		    		 }  		 
		    		 if(null!=value &&value.length()==0){
		    			 if(null!=request.getParameter(this.getName())){
		    				 value=request.getParameter(this.getName());
		    			 }
		    		 }
		    	 }
		     }
		     if(null!=value &&value.length()==0){
		    	 Department dept=(Department)this.pageContext.getSession().getAttribute("loginUserDeptObj");
		    	if(this.valuetype.equals("name")){
		    		value=dept.getName();
		    	}else{
		    		value=dept.getId();
		    	}
		     }
		     out.print(EncodeFilter.encode(value));
		    } catch(Exception e) {
			 e.printStackTrace();
	       throw new JspException(e.getMessage());
	     }
	     return SKIP_BODY;
	}

	@Override
	public void release() {
		super.release();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getValuetype() {
		return valuetype;
	}

	public void setValuetype(String valuetype) {
		this.valuetype = valuetype;
	}
	
}
