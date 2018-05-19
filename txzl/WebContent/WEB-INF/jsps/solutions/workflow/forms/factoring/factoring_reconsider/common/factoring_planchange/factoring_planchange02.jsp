<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
	/* if (miniui_ext.submitFormValidation(["contract_change_info_form","changeInfoForm"]) == false) return; */
    miniui_ext.submitIncludeData("tabDetails");
	return true;
}
</script>
<!--多行控件  -->
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_equip_str" name="json_contract_equip_str" value='${empty json_contract_equip_str ? "[]" : json_contract_equip_str }'></input>
<div id="contract_change_form">
	<div class="fillTableContainer"> 
		<table class="fillTable" cellspacing="0" cellpadding="0">
			<tr>
				<td ><jsp:include page="../factoring_comm/contract_factoring_base_info.jsp"><jsp:param value="true" name="isView"/></jsp:include></td>
			</tr>
		</table>
	</div>
	<div id="tabDetails" class="mini-tabs" activeIndex="0" style="width: 100%;" onactivechanged="changTab">
 	 <div title="变更说明" name="contract_change_info" iconCls="icon-node">
			<jsp:include page="factoring_comm/contract_change_info.jsp">
			 <jsp:param value="true" name="isView" />
			</jsp:include>
			
		</div>  
 	<div title="(原)保理报价方案" name="business_condition" iconCls="icon-cut">
			<jsp:include page="factoring_rent/main_5_1O.jsp" >
			 <jsp:param value="true" name="isView" />
			</jsp:include>
			
		</div>  
	<div title="保理报价方案变更" name="fund_rent_plan_new" iconCls="icon-cut">
			<jsp:include page="factoring_rent/main_5_1C.jsp" >
			 <jsp:param value="true" name="isView" /></jsp:include>
		</div>   
	</div>
</div>