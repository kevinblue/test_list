<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../../fund/fund_comm/fund_comm_js_function.jsp"></jsp:include>
<%@ taglib uri="/minidict" prefix="mini"%>
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
	    if (miniui_ext.submitFormValidation(["contractterminatechangeitem","contract_change_info_form"]) == false) return false;	
		return true;
	}
</script>

<!--start 多行控件  -->
<jsp:include page="comm/contract_terminate_mutli_info.jsp" ></jsp:include>
<!--end 多行控件  -->

<div id="contract_finish_form">
	<div class="fillTableContainer">
		<table class="fillTable" cellspacing="0" cellpadding="0">
			<tr>
				<td>
				<jsp:include page="../contract_comm/contract_base_info.jsp"><jsp:param value="true" name="isView" /></jsp:include></td>
			</tr>
		</table>
	</div>
	<div id="tabDetails" class="mini-tabs" activeIndex="0" style="width: 100%;"  onactivechanged="changTab"  bodyStyle="border:0px;">
		<div title="资金收付款计划" name="fund_payment_plan_info" iconCls="icon-node" >
			<jsp:include page="../contract_finish/comm/fund_payment_plan_info.jsp"><jsp:param value="true" name="isView" /></jsp:include>
		</div>
		<div title="资金收付款历史" name="fund_payment_his_info" iconCls="icon-node">
			<jsp:include page="../contract_finish/comm/fund_charge_his_info.jsp"><jsp:param value="true" name="isView" /></jsp:include>
		</div>
		<div title="租金回笼计划" name="contract_equip" iconCls="icon-node">
			<jsp:include page="../../rent/rent_comm/rent_income_plan.jsp"><jsp:param value="true" name="isView" /></jsp:include>
		</div>
		<div title="租金回笼历史" name="contract_equip" iconCls="icon-node">
			<jsp:include page="../../rent/rent_comm/rent_income_his.jsp"><jsp:param value="true" name="isView" /></jsp:include>
		</div>
    	<div title="(原)商务条件" name="business_condition" iconCls="icon-cut">
			<jsp:include page="../../reckon/rent_readonly/main_5_1.jsp" ></jsp:include>
		</div>
		<div title="提前终止变更项" name="business_condition" iconCls="icon-node">
			<jsp:include page="comm/contract_terminate_changeitem.jsp" ><jsp:param value="true" name="isView" /></jsp:include>
		</div>
		 <div title="(现)商务条件" name="fund_rent_plan_new" iconCls="icon-cut">
			<jsp:include page="../../reckon/rent_compare/main_5_1.jsp" ></jsp:include>
		</div> 
		<div title="变更说明" name="contract_equip" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_change_info.jsp"><jsp:param value="true" name="isView" /></jsp:include>
		</div>
	</div>
</div>