<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--  <%@ taglib uri="/minidict" prefix="mini"%>  --%> 

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<%-- <link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/tracywindy/workflow.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/tracywindy/workFlowUtil.css" rel="stylesheet" type="text/css"> --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/arrayUtils.js"></script> 

 <div  id="pledge_other_form"  title="抵质押注销说明"> 
    <div   class="mini-panel" title="抵质押注销说明" showCollapseButton="true" style="width: 100%;padding-top:5px;padding-bottom:5px;">
	<table class="fillTable" cellspacing="0" cellpadding="0" id="pledge_other" >
	          <tr class="tr-project-info tr-even">
	             <td class="td-content-title" width="5%" >
	          		    抵质押注销时间:
	             </td>	             
	             <td class="td-content" width="38%" >	            	             
	             	<input  name="pledge.pledgetime" id ="pledge.pledgetime" required="true" type="date" format="yyyy-MM-dd"
	               allowinput="false"  onvaluechanged="changeDate(e)"
	               class="mini-datepicker"   value="${requestScope['pledge.pledgetime']}">
	               </td>         
	          </tr>	         
	          <tr class="tr-project-info tr-even">
	             <td class="td-content-title" > 
	             	抵质押注销原因:
	             </td>
	             <td  colspan="3"  style="padding-top: 5px;padding-bottom: 5px;" > 
	             <input  class="mini-textarea"  name="pledge.pledgereason" id ="pledge.pledgereason"  label="抵质押注销原因:" 
	              style="width:35%;height:50px"
	             value="${requestScope['pledge.pledgereason']}" type="text"  >  </td>
	        </tr>  
	         <%--  <tr class="tr-odd">
	             <td class="td-content-title">抵质押注销原因:</td>
	             <td colspan="3" style="padding-top: 5px;padding-bottom: 5px;">
	             <input style="width:35%;height:50px" label="抵质押注销原因:" 
	             name="pledge.pledgereason"  id ="pledge.pledgereason" 
	              value="${requestScope['pledge.pledgereason']}" class="mini-textarea"  type="text" >  </td>
	          
	          	<input width="55%" name="applydate" class="mini-datepicker" type="date" format="yyyy-MM" required="true"
	          	 allowinput="false" value="${requestScope['applydate']}" onvaluechanged="changeDate(e)"/>
	           </tr>  --%>
		</table>
	</div>
</div> 


<script language="javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("pledge_other_form");};
}); 

function changeDate(e){
	pledge.pledgetime=mini.formatDate(mini.getbyName("pledge.pledgetime").getValue(),'yyyy-MM-dd');
}
</script> 
