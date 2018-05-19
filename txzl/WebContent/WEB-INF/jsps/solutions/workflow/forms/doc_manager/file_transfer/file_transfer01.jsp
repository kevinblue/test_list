<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/arrayUtils.js"></script>
<script type="text/javascript">
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
		return true;
	}
</script>
<!--多行控件  -->
<input style="display:none;"    class="mini-textarea" id="id_json_this_handover_str" name="json_this_handover_str" value='${empty json_handover_str ? "[]" : json_handover_str }'></input>
<div class="fillTableContainer">
   <table class="fillTable" cellspacing="0" cellpadding="0">
	  <tr>
	     <td colspan=4>
			 <!-- 插入项目基本信息 --> 
			 <jsp:include page="../../contract/contract_comm/contract_base_info.jsp"><jsp:param value="true" name="isView"/></jsp:include>
		 </td>
	   </tr>
	    <tr>
			<td >
				<!-- 交接人 --> 
				<jsp:include page="transfer_man.jsp"></jsp:include>
			</td>
		</tr>
	</table>
	<div id="tabApprovalDeatils" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width: 100%;" bodyStyle="padding:0;border:0;">
	<div title="待交接资料" name="this_handover" iconCls="icon-expand">
	     <jsp:include page="this_handover.jsp"></jsp:include>
	</div>
	<div title="已交接资料" name="handover_history" iconCls="icon-expand">
	     <jsp:include page="handover_history.jsp"></jsp:include>
	</div>
	<div title="保险清单" name="insurance_list" iconCls="icon-expand">
	     <jsp:include page="insurance_list.jsp"></jsp:include>
	</div>
</div>
</div>