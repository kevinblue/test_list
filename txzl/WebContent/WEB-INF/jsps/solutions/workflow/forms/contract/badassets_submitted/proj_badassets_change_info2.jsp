<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
//是否保存   
function workflowSaveCallBack()//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
{
	miniui_ext.saveData("tabDetails");
	return true;
}
//保存成功提交后，后台返回
function saveCallBack() {
	miniui_ext.saveData("tabDetails");
	return true;
}
//是否提交    
function workflowSubmitCallBack(buttonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
{    
	//mini.parse();
	
    //miniui_ext.submitData("newconditionDeatils");
    //if (miniui_ext.submitFormValidation(["contract_change_info_form"]) == false) return false;
     miniui_ext.saveData("tabDetails");
	 return true;
} 
</script>
<!--多行控件  -->
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
	    <div title="不良资产信息" name="business_condition" iconCls="icon-node" showCollapseButton="true">
			<jsp:include page="common/contract_change_info2.jsp" ></jsp:include>
		</div>
		<div title="逾期租金信息" name="overdue_rentinformation" iconCls="icon-node">
			<jsp:include page="common/overdue_rentinformation.jsp" ></jsp:include>
		</div>
	    <div title="逾期资金信息" name="overdue_fundinformation" iconCls="icon-node">
			<jsp:include page="common/overdue_fundinformation.jsp" ></jsp:include>
		</div>
		<div title="不良资产报告" name="badassets_report" iconCls="icon-node" >
			<jsp:include page="common/badassets_report.jsp" >
			<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
	</div>
</div>