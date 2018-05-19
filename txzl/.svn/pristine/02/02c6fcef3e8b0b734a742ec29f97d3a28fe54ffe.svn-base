<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../../fund/fund_comm/fund_comm_js_function.jsp"></jsp:include>
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
		if (miniui_ext.submitFormValidation(["contract_finish_info","contract_property_rights_transfer","contract_summary"]) == false) return;
		miniui_ext.submitData("tabDetails");
		return true;
	}
</script>
<!--多行控件  -->
<input  style="display:none;"	class="mini-textarea" id="id_json_fund_payment_plan_info_str" name="json_fund_payment_plan_info_str" value='${empty json_fund_payment_plan_info_str ? "[]" : json_fund_payment_plan_info_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_fund_charge_his_info_str" name="json_fund_charge_his_info_str" value='${empty json_fund_charge_his_info_str ? "[]" : json_fund_charge_his_info_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_rent_income_plan_str" name="json_rent_income_plan_str" value='${empty json_rent_income_plan_str ? "[]" : json_rent_income_plan_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_rent_income_his_str" name="json_rent_income_his_str" value='${empty json_rent_income_his_str ? "[]" : json_rent_income_his_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_property_rights_transfer_str" name="json_contract_property_rights_transfer_str" value='${empty json_contract_property_rights_transfer_str ? "[]" : json_contract_property_rights_transfer_str }'></input>
<div id="contract_finish_form">
	<div class="fillTableContainer">
		<table class="fillTable" cellspacing="0" cellpadding="0">
			<tr>
				<td>
				 <jsp:include page="../contract_comm/contract_base_info.jsp"><jsp:param value="true" name="isView" /></jsp:include></td>
			</tr>
		</table>
	</div>
	<div id="tabDetails" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width: 100%;">
		<div title="资金收付款计划" name="fund_payment_plan_info" iconCls="icon-node">
			<jsp:include page="comm/fund_payment_plan_info.jsp"><jsp:param value="true" name="isView" /></jsp:include>
		</div>
		<div title="资金收付款历史" name="fund_charge_his_info" iconCls="icon-node">
			<jsp:include page="comm/fund_charge_his_info.jsp"><jsp:param value="true" name="isView" /></jsp:include>
		</div>
		<div title="租金回笼计划" name="rent_income_plan" iconCls="icon-node">	
			<jsp:include page="../../rent/rent_comm/rent_income_plan.jsp"><jsp:param value="true" name="isView" /></jsp:include>
		</div>
		<div title="租金回笼历史" name="rent_income_his" iconCls="icon-node">
			<jsp:include page="../../rent/rent_comm/rent_income_his.jsp"><jsp:param value="true" name="isView" /></jsp:include>
		</div>
		<div title="合同结束信息" name="contract_finish_info" iconCls="icon-node">
			<jsp:include page="comm/contract_finish_info.jsp"></jsp:include>
		</div>
		<div title="产权转移单" name="contract_property_rights_transfer" iconCls="icon-node">
			<jsp:include page="comm/contract_property_rights_transfer.jsp"></jsp:include>
		</div>
		<div title="合同总结" name="contract_summary" iconCls="icon-node">
			<jsp:include page="comm/contract_summary.jsp"></jsp:include>
		</div>
	</div>
</div>
