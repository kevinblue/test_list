package com.tenwa.leasing.tag;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.fusioncharts.exporter.ErrorHandler;
import com.tenwa.kernal.annotation.FieldName;

@FieldName(name = "获取参数据配置")
public class GetErrorHandler extends TagSupport {
  
	@Override
	public int doEndTag() throws JspException {
		 return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		 try {
		     JspWriter out = this.pageContext.getOut();	
		     ServletRequest request=  this.pageContext.getRequest();
		     String exportTargetWindow = request.getParameter("exportTargetWindow");
		     String isHTML = request.getParameter("isHTML");
		     if(isHTML==null){
		     	isHTML = "true";
		     }
		     String otherMessages = request.getParameter("otherMessages");

		     if(otherMessages==null){
		     	otherMessages="";
		     }
		     String errorMessage = request.getParameter("errorMessage");
		     if(errorMessage==null){
		     	errorMessage="";
		     }
		     this.pageContext.getResponse().setContentType("text/html");
		     if(exportTargetWindow.equalsIgnoreCase("_self")){
		    	// this.pageContext.getResponse()..addHeader("Content-Disposition", "attachment;");
		     }
		     else {
		    	 //this.pageContext.getResponse().addHeader("Content-Disposition", "inline;");
		     }
		     out.print(ErrorHandler.buildResponse(request.getParameter("errorMessage"),new Boolean(isHTML).booleanValue()) );
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


	
}
