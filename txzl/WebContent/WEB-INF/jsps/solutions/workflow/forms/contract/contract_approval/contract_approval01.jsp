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
	//校验业务合同编号是否重复
	if(checkcontractnumber()){
		return false;
	}
	//if(!checkCalIsSame()){return ;}
	/* if(checkEquipIsNull()==false) return; */
	miniui_ext.saveIncludeData("tabDetails");
	miniui_ext.saveIncludeData("conditionDeatils");
	if (miniui_ext.submitFormValidation(["contract_base_info_form","contract_rent_invoice_type_form","contract_signatory_form"]) == false) return false;
/* 	if("" ==mini.get("contract_word").getData()){
		mini.alert("请生成合同文件再提交！");
		return false;
		} */
	/* if(mini.getbyName("rawValue_contract_info.leasform").getValue()=="直租"){
		var data=mini.get('contract_equip').getData();
    	for(var i=0;i<data.length;i++){
    		if(data[i].vndrtype==""){
    			mini.alert("直租时，请在租赁物明细中，填写贸易类型！");
    			return false;
    		}
    		return true;
    	}
	}; */
	return true;
}
/* function workflowNextRouteCallBack(buttonText,requestNextRoute){
	if(buttonText == "Submit"){
		var isUnpacking = "${param.businesstype}";
		//console.info(isUnpacking);
			//requestNextRoute.value = "否";
		
	}
}  */  
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
		<jsp:include page="../contract_comm/contract_base_info.jsp"></jsp:include>
	</div>
	<div id="tabDetails" class="mini-tabs"  activeIndex="0" onactivechanged="contractChangTab" style="width: 100%;" bodyStyle="border:0px;">
		<div title="租赁物明细" name="contract_equip" iconCls="icon-node">
			<jsp:include page="../proj_contract/contract_equip_detail_tmp.jsp" ></jsp:include>
		</div> 
		<div title="商务条件" name="business_condition" iconCls="icon-node">
			<jsp:include page="../../reckon/rent_reckon/factoring_main_5_1.jsp" ></jsp:include>
		</div>
		<div title="其它商务条件" name="otherBusinessCondition" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_other_business_condition.jsp" ></jsp:include>
		</div>
		<div title="担保单位信息" name="contract_guarantee_method" iconCls="icon-node">
			<jsp:include page="../proj_contract/contract_guarantee_method_tmp.jsp" ></jsp:include>
		</div>
		<div title="抵质押物信息" name="contract_guarantee_equip" iconCls="icon-node">
			<jsp:include page="../proj_contract/contract_guarantee_equip_tmp.jsp" ></jsp:include>
		</div>
		<div title="租金开票类型" name="rentInvoiceType" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_rent_invoice_type.jsp" ></jsp:include>
		</div>
		
		<div title="签约方" name="contract_supplier" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_signatory.jsp" ></jsp:include>
		</div>
 		<div title="相关合同" name="contract_word" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_word_and_numset.jsp" ></jsp:include>
		</div> 
		
	</div>
</div>
<script >
function contractChangTab(e){
	if(e.name== "contract_guarantee_equip"){
		mini.get("contract_guarantee_equip").load();
	}
}
</script>