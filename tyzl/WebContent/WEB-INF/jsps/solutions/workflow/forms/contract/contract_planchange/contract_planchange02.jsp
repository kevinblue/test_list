<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
//是否保存   
function workflowSaveCallBack()//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
{
	miniui_ext.saveData("tabDetails");
	return true;
}
function formPageReadOnlyCallBack(){
	miniui_ext.initformenabled("changeInfoForm");
}
//保存成功提交后，后台返回
function saveCallBack() {
	miniui_ext.saveData("tabDetails");
	return true;
}
//是否提交    
function workflowSubmitCallBack(buttonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
{
	if (miniui_ext.submitFormValidation(["contract_change_info_form","changeInfoForm"]) == false) return;
    miniui_ext.submitIncludeData("tabDetails");
	return true;
	return true;
}
</script>
<!--多行控件  -->
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_equip_str" name="json_contract_equip_str" value='<mini:param  name="json_contract_equip_str" defaultValue="[]"/>'></input>
<div id="contract_change_form">
	<div class="fillTableContainer"> 
		<table class="fillTable" cellspacing="0" cellpadding="0">
			<tr>
				<td ><!-- 插入项目基本信息 --> <jsp:include page="../contract_comm/contract_base_info.jsp"><jsp:param value="true" name="isView"/></jsp:include></td>
			</tr>
		</table>
	</div>
	<div id="tabDetails" class="mini-tabs" activeIndex="0" style="width: 100%;" onactivechanged="changTab">
	    <div title="变更说明" name="contract_change_info" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_change_info.jsp"><jsp:param value="true" name="isView"/></jsp:include>
		</div>
		<div title="(原)商务条件" name="business_condition" iconCls="icon-cut">
			<jsp:include page="../../reckon/rent_readonly/main_5_1.jsp" ><jsp:param value="true" name="isView"/></jsp:include>
		</div>
		<%-- <div title="变更信息" name="business_condition" iconCls="icon-node">
			<jsp:include page="contract_planchange_info.jsp" ><jsp:param value="true" name="isView"/></jsp:include>
		</div> --%>
		 <div title="(现)商务条件" name="fund_rent_plan_new" iconCls="icon-cut">
			<jsp:include page="../../reckon/rent_compare/main_5_1.jsp" ><jsp:param value="true" name="isView"/></jsp:include>
		</div> 
	</div>
</div>