<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
//是否保存   
function workflowSaveCallBack()//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
{
	miniui_ext.saveIncludeData("tabDetails");
	miniui_ext.saveIncludeData("conditionDeatils");
	return true;
}
//保存成功提交后，后台返回
function saveCallBack() {
	return true;
}
//是否提交    
function workflowSubmitCallBack(buttonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
{
	/* if(checkEquipIsNull()==false) return; */
	miniui_ext.saveIncludeData("tabDetails");
	miniui_ext.saveIncludeData("conditionDeatils");
	if (miniui_ext.submitFormValidation(["contract_base_info_form","contract_rent_invoice_type_form","contract_signatory_form"]) == false) return false;

 	return true;
}
function workflowNextRouteCallBack(buttonText,requestNextRoute){
	if(buttonText == "Submit"){
		var isUnpacking = "${param.businesstype}";
        if(isUnpacking == "business_type.yes"){
        	requestNextRoute.value = "是";
        }else{
        	requestNextRoute.value = "否";
        }		
	}
} 
</script>
<!--多行控件  -->
<input 	style="display:none;"   class="mini-textarea" id="id_json_contract_change_numset_str" name="json_contract_change_numset_str" value='${empty json_contract_change_numset_str ? "[]" : json_contract_change_numset_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_equip_str" name="json_contract_equip_str" value='${empty json_contract_equip_str ? "[]" : json_contract_equip_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_guarantee_method_str" name="json_contract_guarantee_method_str" value='${empty json_contract_guarantee_method_str ? "[]" : json_contract_guarantee_method_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_guarantee_equip_str" name="json_contract_guarantee_equip_str" value='${empty json_contract_guarantee_equip_str ? "[]" : json_contract_guarantee_equip_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_supplier_str" name="json_contract_supplier_str" value='${empty json_contract_supplier_str ? "[]" : json_contract_supplier_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_word_str" name="json_contract_word_str" value='${empty json_contract_word_str ? "[]" : json_contract_word_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_premise_str" name="json_contract_premise_str" value='${empty json_contract_premise_str ? "[]" : json_contract_premise_str }'></input>
<div id="contract_change_form">
	<div class="fillTableContainer"> 
		<table class="fillTable" cellspacing="0" cellpadding="0">
			<tr>
				<td >
				  <jsp:include page="../contract_comm/contract_base_info.jsp">
				  	<jsp:param value="true" name="isView"/>
				  </jsp:include>
			    </td>
			</tr>
		</table>
	</div>
	<div id="tabDetails" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width: 100%;" bodyStyle="border:0px;">
		<div title="租赁物明细" name="contract_equip" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_equip_detail.jsp">
				<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		<div title="商务条件" name="business_condition" iconCls="icon-node">
			<jsp:include page="../../reckon/rent_reckon/main_5_1.jsp" >
				<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		<div title="其它商务条件" name="otherBusinessCondition" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_other_business_condition.jsp" >
				<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		<div title="担保单位信息" name="contract_guarantee_method" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_guarantee_method.jsp" >
				<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		<div title="抵质押物信息" name="contract_guarantee_equip" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_guarantee_equip.jsp" >
				<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		<div title="租金开票类型" name="rentInvoiceType" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_rent_invoice_type.jsp" >
				<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		<%-- <div title="特别付款前提" name="contract_premise" iconCls="icon-node">
			<jsp:include page="../contract_approval/contract_premise_info.jsp" >
				<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div> --%>
		<div title="签约方" name="contract_supplier" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_signatory.jsp" >
				<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		<div title="相关合同" name="contract_change_numset" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_word_ancillary_numset.jsp" ></jsp:include>
		</div>
	</div>
</div>
<script type="text/javascript">
jQuery(function(){	
	miniui_ext.initformenabled("contract_signatory_form1");
	miniui_ext.initformenabled("contract_rent_invoice_type_form");
});
</script>