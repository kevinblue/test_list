<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/arrayUtils.js"></script>
<script type="text/javascript">
//是否保存
function workflowSaveCallBack() {
	miniui_ext.saveIncludeData("tabDetails");
	miniui_ext.saveIncludeData("conditionDeatils");
	return true;
}
//保存成功提交后，后台返回
function saveCallBack() {
	return true;
}
//是否提交
function workflowSubmitCallBack(buttonText) {
	//if(!checkCalIsSame()){return ;}
	if(checkEquipIsNull()==false) return;
	miniui_ext.saveIncludeData("tabDetails");
	miniui_ext.saveIncludeData("conditionDeatils");
	if (miniui_ext.submitFormValidation(["contract_base_info_form","contract_rent_invoice_type_form","contract_signatory_form"]) == false) return false;
	if(mini.getbyName("rawValue_contract_info.leasform").getValue()=="直租"){
		var data=mini.get('contract_equip').getData();
    	for(var i=0;i<data.length;i++){
    		if(data[i].vndrtype==""){
    			mini.alert("直租时，请在租赁物明细中，填写贸易类型！");
    			return false;
    		}
    		return true;
    	}
	}; 
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
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_equip_str" name="json_contract_equip_str" value='${empty json_contract_equip_str ? "[]" : json_contract_equip_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_guarantee_method_str" name="json_contract_guarantee_method_str" value='${empty json_contract_guarantee_method_str ? "[]" : json_contract_guarantee_method_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_guarantee_equip_str" name="json_contract_guarantee_equip_str" value='${empty json_contract_guarantee_equip_str ? "[]" : json_contract_guarantee_equip_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_supplier_str" name="json_contract_supplier_str" value='${empty json_contract_supplier_str ? "[]" : json_contract_supplier_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_word_str" name="json_contract_word_str" value='${empty json_contract_word_str ? "[]" : json_contract_word_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_premise_str" name="json_contract_premise_str" value='${empty json_contract_premise_str ? "[]" : json_contract_premise_str }'></input>
<div id="contract_approval_form">
	<div class="fillTableContainer">
		<tr>
	     	<td colspan=4>
				<!-- 合同基本信息 --> 
				<jsp:include page="../contract_comm/contract_base_info.jsp"></jsp:include>
			</td>
		</tr>
		<tr>
			<td >
				<!-- 流程备注 --> 
				<jsp:include page="comm/contract_approval_process_note.jsp"></jsp:include>
			</td>
		</tr>
	</div>
	<div id="tabDetails" class="mini-tabs"  activeIndex="0" onactivechanged="changTab" style="width: 100%;" bodyStyle="border:0px;">
		<div title="租赁物明细" name="contract_equip" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_equip_detail.jsp" ></jsp:include>
		</div>
		<div title="商务条件" name="business_condition" iconCls="icon-node">
			<jsp:include page="../../reckon/rent_reckon/main_5_1.jsp" ></jsp:include>
			<table style="display:none">
					<tr>
						<td>
						<input id="approval_distingguish" name="approval_distingguish" value="show_fund_cash"
							class="mini-textbox"  />	
						</td>
					</tr>
				</table>
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
			<jsp:param value="true" name="isView"/></jsp:include>
		</div>
		<div title="租金开票类型" name="rentInvoiceType" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_rent_invoice_type.jsp" ></jsp:include>
		</div>
		<div title="特别付款前提" name="contract_premise" iconCls="icon-node">
			<jsp:include page="contract_premise_info.jsp" ></jsp:include>
		</div>
		<div title="签约方" name="contract_supplier" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_signatory.jsp" ></jsp:include>
		</div>
		<div title="相关合同" name="contract_word" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_word.jsp" ></jsp:include>
		</div>
	</div>
</div>
<script type="text/javascript">

 
jQuery(function(){	
	if(isViewHistoryTask!=true){
		$('#calculateButton').hide();
		miniui_ext.initformenabled("contract_base_info_form");
		miniui_ext.initformenabled("businessconditionForm");
		mini.get("contract_number").enable();
		mini.get("onhiretype").enable();
		mini.get("contracttype").enable();
		mini.get("equipsource").enable();
		mini.get("rawValue_contract_info.equipsource").enable();
		mini.get("contract_info.lawmanage").enable();		
		mini.get("rawValue_contract_info.lawmanage").enable();		
	    miniui_ext.initformenabled("contract_signatory_form_table");
	    mini.get("contract_signatory_leaseaccname").enable();
	    mini.get("contract_signatory.leaseaccnumber").enable();
	    mini.get("contract_signatory_leaseaccbank").enable();
		miniui_ext.initformenabled("rentincomestyle");
		miniui_ext.initformenabled("calculationcondition");
		miniui_ext.initformenabled("pranayama");
		miniui_ext.initformenabled("contract_info_cust_detail");
		miniui_ext.initformenabled("contract_info_agent_detail");
		miniui_ext.initformenabled("contract_info_gov_detail");
		
	};
	
});

</script>