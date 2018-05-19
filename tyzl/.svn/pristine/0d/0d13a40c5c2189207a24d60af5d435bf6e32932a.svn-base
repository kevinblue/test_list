package com.tenwa.leasing.tag;

import java.lang.reflect.Method;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.beans.BeanUtils;

import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.kernal.utils.EncodeFilter;
import com.tenwa.kernal.utils.StringUtil;
import com.tenwa.leasing.utils.StrTools;

@FieldName(name = "获取参数据配置")
public class GetParameter extends TagSupport {
    private String name;
    private String defaultValue;
    private String scope="request";
    private String valuetype="attribute";
	@Override
	public int doEndTag() throws JspException {
		 return EVAL_PAGE;
	}
	@Override
	public int doStartTag() throws JspException {
		 try {
		     JspWriter out = this.pageContext.getOut();	
		     String value="";
		     ServletRequest request=this.pageContext.getRequest();
		     HttpSession session=this.pageContext.getSession();
		     if(null!=this.name&&this.name.length()>0){
			     if(null!=this.pageContext.getAttribute(this.name)){
			    	 value=this.pageContext.getAttribute(this.name).toString();
			     }else if(null!=request.getAttribute(this.getName())){
			    			value=request.getAttribute(this.getName()).toString();
			     }else if(null!=request.getParameter(this.getName())){
			    	  value=request.getParameter(this.getName()).toString();
			     }else if(null!=session.getAttribute(this.getName())){
			    	 value=session.getAttribute(this.getName()).toString();
			     }else{
			    	 //获得对像属性
			    	 if(this.name.indexOf(".")>0){
			    		 String temp[]=this.name.split("\\.");
			    		 if(temp.length>=2){
				    		 String objectname=temp[0];
				    		 String property=temp[1];
				    		 String nextProperty="";
				    		 if(temp.length==3){
				    			 nextProperty=temp[2];
				    		 }
				    		 Object tobject=null;
				    		 if(null!=this.pageContext.getAttribute(objectname)){
				    			 tobject=this.pageContext.getAttribute(this.name);
						     }else if(null!=request.getAttribute(objectname)){
						    	 tobject=request.getAttribute(objectname);
						     }else if(null!=request.getParameter(this.getName())){
						    	 tobject=request.getParameter(objectname);
						     }else if(null!=session.getAttribute(objectname)){
						    	 tobject=session.getAttribute(objectname);
						     }				    		 
				    		 if(tobject!=null){
				    			 Method method=null;
				    			 method = BeanUtils.getPropertyDescriptor(tobject.getClass(), property).getReadMethod();
				    			 if(temp.length==2){
					    			 if(method!=null){
					    				 value=StringUtil.nullToString(method.invoke(tobject),"");
					    			 }
				    			 }else{
				    				 if(method!=null){
				    					 Object simpobject=null;
				    					 simpobject=method.invoke(tobject);
				    					 if(simpobject!=null){
				    						 method = BeanUtils.getPropertyDescriptor(simpobject.getClass(), nextProperty).getReadMethod();
							    			 if(method!=null){
							    				 value=StringUtil.nullToString(method.invoke(simpobject),"");
							    			 }
				    					 }
					    			 }
				    			 }
				    		 }
			    		 }
			    	 }
			     } 
			     
		     }
		     if(null!=value &&value.length()==0){
		    	 if(null!=this.defaultValue&&this.defaultValue.length()>0){
		    		 value=this.defaultValue;
		    	 }
		     }
		     if(value.indexOf("http://")>=0){
		    	 out.print(value);
		     }else{
		        out.print(EncodeFilter.encode(value));
		     }
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

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
}
