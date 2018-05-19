package com.tenwa.business.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.EncodeFilter;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.StringUtil;
import com.tenwa.leasing.utils.StrTools;

public class BaseController 
{
	private Log log =  LogFactory.getLog(this.getClass());
	//private final String DETAULT_ERROR_PAGE = "solutions/errorPages/error.jsp";
	protected void output(HttpServletResponse response, String returnInfo) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		out.print(StrTools.outPrintCheck(returnInfo));
		out.flush();
		out.close();
	}
	protected void ajaxReturn(HttpServletResponse response, String returnInfo) throws IOException {
		response.setContentType("text/html;charset=UTF-8"); 
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(StrTools.outPrintCheck(returnInfo));
		out.flush();
		out.close();
	}
	@SuppressWarnings("unchecked")
	protected void setAllKeyToRequestAttributeByMap(HttpServletRequest request ,Map modelData)
	{
		if(null == modelData)return;
		for(Object key : modelData.keySet())
		{
			//变异json字符串
			String value = StringUtil.nullToString(modelData.get(key));
		   if(value.matches("\\[\\{.*?\\}\\]")){
			   request.setAttribute(StringUtil.nullToString(key), StringUtil.nullToString(modelData.get(key)).replaceAll("'","&#39;"));
		   }else{
			   request.setAttribute(StringUtil.nullToString(key), StringUtil.nullToString(modelData.get(key)));
		   }
		}
	}
    @ExceptionHandler(value = { Exception.class})  
	public String handleException(Exception ex, HttpServletRequest request,HttpServletResponse response) throws Exception
    {  
      if("ClientAbortException".equals(ex.getClass().getSimpleName()))
      {   
    	  return null;
      } 
	  if(null != ex)
	  {
		  request.setAttribute("isOccuredOperationException", "true");
		  String exceptionInfo = StringUtil.traceExceptionMessage(this, ex);
		  request.setAttribute("occuredOperationExceptionInfo", exceptionInfo);
		  if(log.isErrorEnabled())
		  {
			  log.error(exceptionInfo);
		  }
		  //String requestType = request.getHeader("X-Requested-With");  
		  //if((null != requestType)&&("XMLHttpRequest".equalsIgnoreCase(requestType)))
		  if(QueryUtil.isAjaxRequest(request))
		  {
			  String returnInfo = "操作失败，请检查数据关联性！";
			  if(ex instanceof BusinessException){
				  returnInfo = ex.getMessage();
			  }
			  this.output(response, "X-Requested-With-Error "+returnInfo);
			  //String returnInfo = "X-Requested-With-Error "+exceptionInfo;
			  //this.ajaxReturn(response, returnInfo);
			  return null;
		  }
		 
		  this.output(response, exceptionInfo);
	  }	
	  request.setAttribute("ex", ex);  
	  return null;//DETAULT_ERROR_PAGE;  
	} 
}
