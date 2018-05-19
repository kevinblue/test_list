<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>

 <script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/arrayUtils.js"></script>
<script type="text/javascript">
isViewHistoryTask=true;
var isFirstPage=true;
    //流程保存之前回调
	function workflowSaveCallBack()
	{
		miniui_ext.saveIncludeData("tabApprovalDeatils");
		return true;

	}
	//保存成功提交后，后台返回回调
	function saveCallBack() {
		return true;
	}
	//流程提交之前回调
	function workflowSubmitCallBack(buttonText)
	{	
		miniui_ext.saveIncludeData("tabApprovalDeatils");
    	if (miniui_ext.submitFormValidation(["letter_approval_form"]) == false) return false;
		return true;
	}
</script>
<input style="display:none;"	class="mini-textarea" id="id_json_letter_approval_file_str" 	name="json_letter_approval_file_str" value='${empty json_letter_approval_file_str ? "[]" : json_letter_approval_file_str }'></input>
<div id="fillTableContainer"   >  
   <table class="fillTable" cellspacing="0" cellpadding="0">
	  <tr>
	     <td colspan=4>
			 <!-- 函件审批型信息 --> 
			 <jsp:include page="letter_approval_detail.jsp">
			 <jsp:param value="true" name="isView"/>
			 </jsp:include>
		 </td>
	   </tr>

	</table>
</div>	
	
<div id="tabApprovalDeatils" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width: 100%;" bodyStyle="border:0px;">

	<div title="生成公函文件" name="letter_approval_file" iconCls="icon-node" >
			<jsp:include page="letter_approval_file.jsp" >
			<jsp:param value="true" name="isView"/>
			</jsp:include>
	</div> 
</div>



