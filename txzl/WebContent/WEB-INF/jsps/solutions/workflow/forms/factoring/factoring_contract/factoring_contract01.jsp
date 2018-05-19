<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	//流程保存之前回调
	function workflowSaveCallBack() {
		miniui_ext.saveIncludeData("conditionDeatils");
		return true;
	}
	//保存成功提交后，后台返回回调
	function saveCallBack() {
		return true;
	}
	//流程提交之前回调
	function workflowSubmitCallBack(buttonText) {
		if (miniui_ext.submitFormValidation(["contract_base_info_form","id_contrade_transaction","contract_supplier_message"]) == false) return false;

		return true;
	}
</script>
<!--多行控件  -->
<input style="display: none;" class="mini-textarea" id="id_json_contract_invoice_str" name="json_contract_invoice_str" value='${empty json_contract_invoice_str ? "[]" : json_contract_invoice_str }'></input>
<input style="display: none;" class="mini-textarea" id="id_json_contract_equip_str" name="json_contract_equip_str" value='${empty json_contract_equip_str ? "[]" : json_contract_equip_str }'></input>
<input style="display: none;" class="mini-textarea" id="id_json_contract_guarantee_method_str" name="json_contract_guarantee_method_str" value='${empty json_contract_guarantee_method_str ? "[]" : json_contract_guarantee_method_str }'></input>
<input style="display: none;" class="mini-textarea" id="id_json_contract_guarantee_equip_str" name="json_contract_guarantee_equip_str" value='${empty json_contract_guarantee_equip_str ? "[]" : json_contract_guarantee_equip_str }'></input>
<input style="display: none;" class="mini-textarea" id="id_json_contract_supplier_str" name="json_contract_supplier_str" value='${empty json_contract_supplier_str ? "[]" : json_contract_supplier_str }'></input>
<input style="display: none;" class="mini-textarea" id="id_json_contract_word_str" name="json_contract_word_str" value='${empty json_contract_word_str ? "[]" : json_contract_word_str }'></input>
<input style="display: none;" class="mini-textarea" id="id_json_contract_premise_str" name="json_contract_premise_str" value='${empty json_contract_premise_str ? "[]" : json_contract_premise_str }'></input>
<div class="fillTableContainer">
	<table class="fillTable" cellspacing="0" cellpadding="0">
		<tr>
			<td colspan=4>
				<!-- 插入保理业务基本信息 --> <jsp:include
					page="../factoring_comm/contract_factoring_base_info.jsp"></jsp:include>
			</td>
		</tr>
	</table>
</div>
<div id="tabApprovalDeatils" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width: 100%;" bodyStyle="border:0px;">
	<div title="发票信息" name="contract_invoice" iconCls="icon-cut" >
		 <jsp:include page="../factoring_comm/contract_invoice.jsp" ></jsp:include>
	</div>
	<div title="保理报价方案" name="business_condition" iconCls="icon-cut">
			<jsp:include page="../../reckon/rent_reckon/factoring_main_5_1.jsp" ></jsp:include>
	</div>
	<div title="供应商信息" name="supplier_message" iconCls="icon-cut" >
		 <jsp:include page="../factoring_comm/contract_supplier_message.jsp" ></jsp:include>
	</div>
	<div title="贸易基础交易情况" name="trade_based" iconCls="icon-cut" >
		 <jsp:include page="../factoring_comm/contrade_transaction.jsp" >
		 	<jsp:param value="false" name="isView"/>
		 </jsp:include>
	</div>
 	<div title="签约方" name="contract_supplier" iconCls="icon-node">
		<jsp:include page="../factoring_comm/contract_signatory.jsp"></jsp:include>
	</div> 
	 <div title="生成合同" name="contract_word" iconCls="icon-node">
		<jsp:include page="../factoring_comm/contract_word.jsp"></jsp:include>
	</div>  
	<%-- <div title="生成合同" name="contract_word" iconCls="icon-node">
			<jsp:include page="../../contract_comm/contract_word_read.jsp" >
			<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div> --%>
</div>