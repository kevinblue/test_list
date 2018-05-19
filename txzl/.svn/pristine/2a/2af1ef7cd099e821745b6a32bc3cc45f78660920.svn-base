<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	//是否保存   
	function workflowSaveCallBack()//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
	{
		return true;
	}
	//保存成功提交后，后台返回
	function saveCallBack() {
		return true;
	}
	//是否提交    
	function workflowSubmitCallBack(buttonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
	{
		if (miniui_ext.submitFormValidation(["contract_patrol_plan_form"]) == false) return false;
		return true;
	}
</script>
<!--多行控件  -->
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_guarantee_method_str" name="json_contract_guarantee_method_str" value='${empty json_contract_guarantee_method_str ? "[]" : json_contract_guarantee_method_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_guarantee_equip_str" name="json_contract_guarantee_equip_str" value='${empty json_contract_guarantee_equip_str ? "[]" : json_contract_guarantee_equip_str }'></input>
<div id="contract_finish_form">
	<div class="fillTableContainer">
		<table class="fillTable" cellspacing="0" cellpadding="0">
			<tr>
				<td>
				 <%-- <jsp:include page="comm/thing_approve_info.jsp"><jsp:param value="true" name="isView" /></jsp:include></td> --%>
				 <jsp:include page="../../contract/contract_comm/contract_base_info.jsp"> <jsp:param value="true" name="isView"/></jsp:include> </td> 
			</tr>
		</table>
	</div>
	<div class="fillTableContainer">
	<div title="基本信息" name="thing_approve_detail" iconCls="icon-node">
			<jsp:include page="thing_approve_addcolumn.jsp"></jsp:include>
		</div>
	</div>
	<div id="tabDetails" class="mini-tabs"  activeIndex="0" onactivechanged="changTab" style="width: 100%;" bodyStyle="border:0px;">
		<div title="重大事项信息" name="thing_approve_detail" iconCls="icon-node">
			<jsp:include page="thing_approve_detail.jsp">
			<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		<div title="担保单位信息" name="contract_guarantee_method" iconCls="icon-node">
			<jsp:include page="../../contract/contract_comm/contract_guarantee_method.jsp" >
			<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		<div title="抵质押物信息" name="contract_guarantee_equip" iconCls="icon-node">
			<jsp:include page="../../contract/contract_comm/contract_guarantee_equip.jsp" >
			<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
	</div>
	 
</div>
