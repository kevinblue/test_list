<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>

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
		
	 	//alert(mini.get("present_cancled").getData()); 
	 	var presentData=mini.get("present_cancled").getData();
	 	if(presentData==""){
	 		mini.alert("请选择抵质押物！"); 
	 		return false;
	 	}
    	if (miniui_ext.submitFormValidation(["contract_base_info_form"]) == false) return false;
    	if (miniui_ext.submitFormValidation(["pledge_other_form"]) == false) return false;
        miniui_ext.saveIncludeData("tabApprovalDeatils");
		return true;
	}
</script>
<input  style="display:none;"	class="mini-textarea" id="id_json_not_cancled_str" 	name="json_not_cancled_str" value='${empty json_not_cancled_str ? "[]" : json_not_cancled_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_pledge_contract_str" 	name="json_pledge_contract_str" value='${empty json_pledge_contract_str ? "[]" : json_pledge_contract_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_present_cancled_str" 	name="json_present_cancled_str" value='${empty json_present_cancled_str ? "[]" : json_present_cancled_str }'></input>
<div class="fillTableContainer">  
   <table class="fillTable" cellspacing="0" cellpadding="0">
	  <tr>
	     <td colspan=4>
			 <!-- 合同基本信息 --> 
			 <jsp:include page="../../contract/contract_comm/contract_base_info.jsp">
			 <jsp:param value="true" name="isView"/></jsp:include>
		 </td>
	   </tr>

	</table>
</div>	

<div class="fillTableContainer">  
			 <!-- 抵质押注销说明--> 
			 <jsp:include page="pledge_other.jsp">
			 <jsp:param value="false" name="isView"/>
			 </jsp:include>
</div>	

<div id="tabApprovalDeatils" class="mini-tabs"   onactivechanged="changTab"  activeIndex="0" style="width: 100%" bodyStyle="padding:0;border:0;">
	<div title="未注销" name="not_cancled" iconCls="icon-expand">
	     <jsp:include page="not_cancled.jsp" >
	     <jsp:param value="false" name="isView"/>
	     </jsp:include>
	</div>
	<div title="已注销" name="pledge_contract" iconCls="icon-expand">
	     <jsp:include page="cancled.jsp" >
	     <jsp:param value="true" name="isView"/>
	     </jsp:include>
	</div>
	<div title="本次注销" name="present_cancled" iconCls="icon-expand">
	     <jsp:include page="present_cancled.jsp" >
	     <jsp:param value="false" name="isView"/>
	     </jsp:include>
	</div>	 
	
</div>




