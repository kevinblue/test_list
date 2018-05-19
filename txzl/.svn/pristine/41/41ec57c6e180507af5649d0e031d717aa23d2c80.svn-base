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
function workflowSubmitCallBack(buttonText)
{
    if(checkEquipIsNull()==false) return;
    miniui_ext.saveIncludeData("tabDetails");
    if (miniui_ext.submitFormValidation(["contract_rent_invoice_type_form"]) == false) return false;
	return true;
}
</script>
<!--多行控件  -->
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_equip_str" name="json_contract_equip_str" value='${empty json_contract_equip_str ? "[]" : json_contract_equip_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_fund_plan_old_str" 	name="json_fund_plan_old_str" value='${empty json_fund_plan_old_str ? "[]" : json_fund_plan_old_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_onhire_report_str" 	name="json_onhire_report_str" value='${empty json_onhire_report_str ? "[]" : json_onhire_report_str }'></input>
<div class="contract_onhire_form">
	<jsp:include page="../contract_comm/contract_base_info.jsp"><jsp:param value="true" name="isView"/></jsp:include>
	<div id="tabDetails" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width: 100%;" bodyStyle="border:0px;">
		<div title="起租通知书" name="onhire_report" iconCls="icon-node">
			<jsp:include page="comm/contract_onhire_notice.jsp" >
			<jsp:param value="true" name="isView"/></jsp:include>
		</div>
		<div title="租赁物明细" name="contract_equip" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_equip_detail.jsp" >
			     <jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		<div title="商务条件" name="business_condition" iconCls="icon-node">
			<jsp:include page="../../reckon/rent_onhire/main_5_1.jsp" >
			    <jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		<div title="租金开票类型" name="rentInvoiceType" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_rent_invoice_type.jsp" >
			    <jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		<div title="资金收付款情况" name="fund_plan_old" iconCls="icon-node">
			<jsp:include page="../../fund/fund_comm/fund_fund_plan_info.jsp" ></jsp:include>
		</div>
		<%-- <div title="租金回笼账户变更" name="account_change" iconCls="icon-node" >
			<jsp:include page="comm/account_change.jsp" >
			<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div> --%>
	</div>
</div>
