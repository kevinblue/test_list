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
				 <jsp:include page="../../contract/contract_comm/law_contract_base_info.jsp"><jsp:param value="true" name="isView" /></jsp:include></td>
			</tr>
		</table>
	</div>
	<div id="tabDetails" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width: 100%;">
		
		<div title="法务处理详情" name="approval_detail" iconCls="icon-node">
			<jsp:include page="law_approval_detail.jsp">
			<jsp:param value="true" name="isView" /></jsp:include>
		</div>
		
		<div title="保全信息详情" name="bq_content" iconCls="icon-node">
			<jsp:include page="bq_content_detail.jsp">
			<jsp:param value="true" name="isView" />
			</jsp:include>
		</div>
		
		<div title="代理人详情" name="bq_content" iconCls="icon-node">
			<jsp:include page="proxy_content_detail.jsp">
			<jsp:param value="true" name="isView" /></jsp:include>
		</div>
		 
		<div title="租赁物明细" name="contract_equip" iconCls="icon-node">
			<jsp:include page="../../contract/contract_comm/contract_equip_detail.jsp" >
			<jsp:param value="true" name="isView" /></jsp:include>
		</div>
	</div>
</div>
