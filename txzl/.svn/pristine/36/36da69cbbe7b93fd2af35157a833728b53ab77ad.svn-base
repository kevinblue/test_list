<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
//是否保存   
function workflowSaveCallBack()//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
{
	miniui_ext.saveData("newconditionDeatils");
	return true;
}
//保存成功提交后，后台返回
function saveCallBack() {
	return true;
}
//是否提交    
function workflowSubmitCallBack(buttonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
{    
	 mini.parse();
     
     miniui_ext.submitData("newconditionDeatils");
     if (miniui_ext.submitFormValidation(["contract_change_info_form"]) == false) return false;
     
	 return true;
} 
</script>
<!--多行控件  -->
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_word_str" name="json_contract_word_str" value='${empty json_contract_word_str ? "[]" : json_contract_word_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_rent_income_his_str" name="json_rent_income_his_str" value='${empty json_rent_income_his_str ? "[]" : json_rent_income_his_str }'></input>
<div id="contract_change_form">
	<div class="fillTableContainer"> 
		<table class="fillTable" cellspacing="0" cellpadding="0">
			<tr>
				<td ><jsp:include page="../contract_comm/contract_base_info.jsp"><jsp:param value="true" name="isView"/></jsp:include></td>
			</tr>
		</table>
	</div>
	<div id="tabDetails" class="mini-tabs" activeIndex="0" style="width: 100%" onactivechanged="changTab" >
	    <div title="变更信息" name="business_condition" iconCls="icon-node" showCollapseButton="true">
			<jsp:include page="../contract_comm/contract_change_info.jsp" ></jsp:include>
		</div>
		 <div title="租金计划修改" name="fund_rent_plan_new" iconCls="icon-cut" showCollapseButton="true">
			<jsp:include page="../../reckon/rent_modify/main_5_1.jsp" ></jsp:include>
		</div> 
		<div title="(原)商务条件" name="business_condition" iconCls="icon-cut" showCollapseButton="true">
			<jsp:include page="../../reckon/rent_readonly/main_5_1.jsp" ><jsp:param value="true" name="isView"/></jsp:include>
		</div>
		<div title="租金回笼历史" name="rent_income_his" iconCls="icon-cut" showCollapseButton="true">
			<jsp:include page="../../rent/rent_comm/rent_income_his.jsp" ></jsp:include>
		</div>
		 <div title="合同变更协议" name="contract_planchange_tab" iconCls="icon-cut">
			<jsp:include page="../contract_comm/contract_change_word.jsp" ></jsp:include>
		</div> 
	</div>
</div>
