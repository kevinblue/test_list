<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/arrayUtils.js"></script>
<script type="text/javascript">
isViewHistoryTask=false;
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
		
    	if (miniui_ext.submitFormValidation(["conference_decision_form"]) == false) return false;
    	
    	miniui_ext.saveIncludeData("tabApprovalDeatils");
    	return true;
	}
</script>
<input style="display:none;"	class="mini-textarea" id="id_json_letter_approval_file_str" 	name="json_letter_approval_file_str" value='${empty json_letter_approval_file_str ? "[]" : json_letter_approval_file_str }'></input>
<div id="fillTableContainer"   >  
   <table class="fillTable" cellspacing="0" cellpadding="0">
	  <tr>
		 <td> 
			<jsp:include page="../proj_common/proj_base_info.jsp">
			<jsp:param value="true" name="isView"/>
			</jsp:include>
		</td>
	   </tr>

	</table>
</div>	
<div id="tabApprovalDeatils" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width: 100%;" bodyStyle="border:0px;">
	<div title="议案基本信息" name="conference_decision_info" iconCls="icon-node" >
			<jsp:include page="conference_decision_detail.jsp" ></jsp:include>
	</div> 
	<div title="审批文件" name="lconference_decision_file" iconCls="icon-node" >
			<jsp:include page="conference_decision_file.jsp" >
			<jsp:param value="false" name="isView"/>
			</jsp:include>
	</div> 
</div>