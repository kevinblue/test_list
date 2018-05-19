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
 	if (miniui_ext.submitFormValidation(["contract_base_info_form",
 	                                    "businessconditionForm",
	                                     "contract_change_info_form",
	                                     "contract_supplier_message",
	                                     "id_contrade_transaction"]) == false) return false; 

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
<input style="display: none;" class="mini-textarea" id="id_json_contract_invoice_str" name="json_contract_invoice_str" value='${empty json_contract_invoice_str ? "[]" : json_contract_invoice_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_equip_str" name="json_contract_equip_str" value='${empty json_contract_equip_str ? "[]" : json_contract_equip_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_guarantee_method_str" name="json_contract_guarantee_method_str" value='${empty json_contract_guarantee_method_str ? "[]" : json_contract_guarantee_method_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_guarantee_equip_str" name="json_contract_guarantee_equip_str" value='${empty json_contract_guarantee_equip_str ? "[]" : json_contract_guarantee_equip_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_supplier_str" name="json_contract_supplier_str" value='${empty json_contract_supplier_str ? "[]" : json_contract_supplier_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_word_str" name="json_contract_word_str" value='${empty json_contract_word_str ? "[]" : json_contract_word_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_premise_str" name="json_contract_premise_str" value='${empty json_contract_premise_str ? "[]" : json_contract_premise_str }'></input>
	<div class="fillTableContainer"> 
		<table class="fillTable" cellspacing="0" cellpadding="0">
			<tr>
				<td >
				 <!-- 插入保理业务基本信息 --> <jsp:include
					page="../factoring_comm/contract_factoring_base_info.jsp"></jsp:include>
			    </td>
			</tr>
		</table>
	</div>
	<div id="tabDetails" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width: 100%;" bodyStyle="border:0px;">
		<div title="变更说明" name="contractChangeInfo.jsp" iconCls="icon-node">
		<jsp:include page="../../contract/contract_comm/contract_change_info.jsp" ></jsp:include>
		</div>
	<div title="发票信息" name="proj_invoice" iconCls="icon-cut" >
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
	</div>
