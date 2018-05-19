<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
 <%@include file="/base/mini.jsp"%>
<title>抵质押注销详情</title> 
 <link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css"/>
<%-- <link href="${pageContext.request.contextPath}/css/tracywindy/workflow.css" rel="stylesheet" type="text/css"> --%>
<link href="${pageContext.request.contextPath}/css/tracywindy/workFlowUtil.css" rel="stylesheet" type="text/css"> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/arrayUtils.js"></script> 
<%
String contractid=request.getParameter("contract_id");//ID

%>
<script type="text/javascript">
var contractid='<%=contractid%>'
/* alert("${requestScope['contract_info.pledgecontract']}") */
var applydate="";

</script>
<input style="display:none;" class="mini-textarea" id="id_json_pledge_contract_str" 	name="json_pledge_contract_str" value='${empty json_pledge_contract_str ? "[]" : json_pledge_contract_str }'></input>
<input style="display:none;" class="mini-textarea" id="id_json_not_cancled_str" 	name="json_not_cancled_str" value='${empty json_not_cancled_str ? "[]" : json_not_cancled_str }'></input>
<input style="display:none;" class="mini-textarea" id="id_json_present_cancled_str" 	name="json_present_cancled_str" value='${empty json_present_cancled_str ? "[]" : json_present_cancled_str }'></input>
<div class="fillTableContainer">  
	<table class="fillTable" cellspacing="0" cellpadding="0" style="width:100%;" id="application_base_info">
			<tr>
		        <td style="padding:5px">		        
		        	<a class="mini-button" iconCls="icon-close" onclick="closeWin">关闭</a>
		        </td>
		    </tr>
		</table>
   <table class="fillTable" cellspacing="0" cellpadding="0">
	  <tr>
	     <td colspan=4>
			 <!-- 合同基本信息 --> 
			 <jsp:include page="../../contract/contract_comm/contract_base_info.jsp"><jsp:param value="true" name="isView"/></jsp:include>
		 </td>
	   </tr>
	</table>
</div>	
	
<div id="tabApprovalDeatils" class="mini-tabs"   onactivechanged="changTab"  activeIndex="0" style="width: 100%" bodyStyle="padding:0;border:0;">
		<div title="未注销" name="not_cancled" iconCls="icon-expand">
	     <jsp:include page="not_cancled.jsp" >
	     <jsp:param value="true" name="isView"/>
	     </jsp:include>
	</div>	
	
	<div title="已注销" name="pledge_contract" iconCls="icon-expand">
	     <jsp:include page="cancled.jsp" >
	     <jsp:param value="true" name="isView"/>
	     </jsp:include>
	</div>

<%-- 	<div title="本次注销" name="present_cancled" iconCls="icon-expand">
	     <jsp:include page="present_cancled.jsp" >
	     <jsp:param value="true" name="isView"/>
	     </jsp:include>
	</div>	  --%>

</div>
<script type="text/javascript">
function closeWin(){
	if ('${param.opertype}'=="view") {
		onCancel();
	} else {
		parentCloseSX();
		onCancel();
	}
}

function parentCloseSX(){
	if('${param.opertype}'!="view"){
	 	window.opener.refresh();
	}
}
</script>