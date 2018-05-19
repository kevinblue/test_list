<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
//是否保存   
function workflowSaveCallBack()//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
{
	miniui_ext.saveIncludeData("tabDetails");
	return true;
}
//保存成功提交后，后台返回
function saveCallBack() {
	return true;
}
//是否提交    
function workflowSubmitCallBack(buttonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
{
	miniui_ext.saveIncludeData("tabDetails");
	return true;
}
</script>
<input 	style="display:none;"	id="allpenalty" name="contract_info.allpenalty" class="mini-textbox" label="业务合同编号"  value="${requestScope['contract_info.allpenalty'] }" />
<input  style="display:none;"	class="mini-textarea" id="id_json_rent_income_plan_str" name="json_rent_income_plan_str" value='${empty json_rent_income_plan_str ? "[]" : json_rent_income_plan_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_rent_income_his_str" name="json_rent_income_his_str" value='${empty json_rent_income_his_str ? "[]" : json_rent_income_his_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_rent_penalty_adjust_detail_str" name="json_rent_penalty_adjust_detail_str" value='${empty json_rent_penalty_adjust_detail_str ? "[]" : json_rent_penalty_adjust_detail_str }'></input>
<div id="rent_penalty_adjust_form">
	<div class="fillTableContainer"> 
		<table class="fillTable" cellspacing="0" cellpadding="0">
			<tr>
				<td ><jsp:include page="../../contract/contract_comm/contract_base_info.jsp"></jsp:include></td>
			</tr>
		</table>
	</div>
	<div id="tabDetails" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width: 100%">
	<div title="租金回笼计划" name="rent_income_plan" iconCls="icon-node">
			<jsp:include page="rent_penalty_adjust_plan.jsp" >
			<jsp:param value="true" name="isView"/>
			</jsp:include>
	</div>
	<div title="罚息通知书" name="penalty_notice" iconCls="icon-node">
			<jsp:include page="penalty_notice.jsp" >
			<jsp:param value="true" name="isView"/>
			</jsp:include>
	</div>
	</div>
</div>
<script>
jQuery(function(){
	$("#tabDetails").width($("#id_content_tabs").width() - 17);
	miniui_ext.initformenabled("contract_base_info_form");
});
</script>