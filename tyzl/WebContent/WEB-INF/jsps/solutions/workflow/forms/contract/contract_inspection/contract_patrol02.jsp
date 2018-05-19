<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
	//是否保存   
	function workflowSaveCallBack()//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
	{
		miniui_ext.saveIncludeData("tabDetails");
		return true;
	}
	//保存成功提交后，后台返回
	function saveCallBack() {
		miniui_ext.saveIncludeData("tabDetails");
		return true;
	}
	//是否提交    
	function workflowSubmitCallBack(buttonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
	{
		if (miniui_ext.submitFormValidation(["contract_patrol_plan_form"]) == false) return;
		miniui_ext.submitData("tabDetails");
		return true;
	}
</script>

<!--start 多行控件  -->
<jsp:include page="comm/contract_inspection_mutli_info.jsp" ></jsp:include>
<!--end 多行控件  -->

<div id="contract_patrol_form" title="租后巡视">
	<div class="fillTableContainer"> 
		<table class="fillTable" cellspacing="0" cellpadding="0">
			<tr>
				<td ><!-- 插入项目基本信息 --> <jsp:include page="../../contract/contract_comm/contract_base_info.jsp"><jsp:param value="true" name="isView"/></jsp:include></td>
			</tr>
		</table>
	</div>
	<div id="tabDetails" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width: 100%;">
		<div title="客户巡视计划表单" name="contract_patrol_plan_form" iconCls="icon-node">
			<jsp:include page="comm/contract_patrol_plan_form.jsp" ><jsp:param value="true" name="isView"/></jsp:include>
		</div>
		<div title="存量资产检查报告" name="stock_patrol_report" iconCls="icon-node">
			<jsp:include page="comm/stock_patrol_report.jsp" ><jsp:param value="true" name="isView"/></jsp:include>
		</div>
		<div title="担保单位信息" name="contract_guarantee_method" iconCls="icon-node">
			<jsp:include page="../../contract/contract_comm/contract_guarantee_method.jsp" ><jsp:param value="true" name="isView"/></jsp:include>
		</div>
	</div>
</div>
