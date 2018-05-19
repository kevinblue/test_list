<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
//是否保存   
function workflowSaveCallBack()//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
{
	miniui_ext.saveIncludeData("tabDetails");
	miniui_ext.saveIncludeData("conditionDeatils");
	return true;
}
function formPageReadOnlyCallBack(){
	mini.get("contract_number").setRequired();
}
//保存成功提交后，后台返回
function saveCallBack() {
	miniui_ext.saveIncludeData("tabDetails");
	return true;
}
//是否提交    
function workflowSubmitCallBack(buttonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
{
	//校验业务合同编号是否重复
	if(checkcontractnumber()){
		return false;
	}
	//if(checkContractNumberUnique()){return false;}
	//if (miniui_ext.submitFormValidation(["contract_base_info_form","contract_rent_invoice_type_form"]) == false) return false;
	//if(checkEquipIsNull()==false) return;
	miniui_ext.saveIncludeData("tabDetails");
	miniui_ext.saveIncludeData("conditionDeatils");
	return true;
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
				<jsp:include page="../contract_comm/contract_base_info.jsp">
		   		<jsp:param value="true" name="isView"/>
		   		</jsp:include>
		   </td>
	   </tr>
		   <%-- <tr>
			<td >
				<!-- 流程备注 --> 
				<jsp:include page="comm/contract_approval_process_note.jsp">
				<jsp:param value="true" name="isView"/>
				</jsp:include>
			</td>
		</tr> --%>
	</div>
	<div id="tabDetails" class="mini-tabs" activeIndex="0" style="width: 100%;" onactivechanged="changTab">
		<div title="租赁物明细" name="contract_equip_detail" iconCls="icon-node">
			<jsp:include page="../proj_contract/contract_equip_detail_tmp.jsp" >
					<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		<div title="商务条件" name="business_condition" iconCls="icon-node">
			<jsp:include page="../../reckon/rent_reckon/factoring_main_5_1.jsp" >
			       <jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		<div title="其它商务条件" name="otherBusinessCondition" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_other_business_condition.jsp" >
			    <jsp:param value="true" name="isView"/>
		    </jsp:include>
		</div>
		<div title="担保单位信息" name="contract_guarantee_method" iconCls="icon-node">
			<jsp:include page="../proj_contract/contract_guarantee_method_tmp.jsp" >
			<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		<div title="抵质押物信息" name="contract_guarantee_equip" iconCls="icon-node">
			<jsp:include page="../proj_contract/contract_guarantee_equip_tmp.jsp" >
			<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		<div title="租金开票类型" name="rentInvoiceType" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_rent_invoice_type.jsp" >
			<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		
		<div title="签约方" name="contract_supplier" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_signatory.jsp" >
			<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		<%-- <div title="生成合同" name="contract_word" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_word_read.jsp" >
			<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div> --%>
		
		<div title="相关合同" name="contract_word" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_word_and_numset_read.jsp" >
			<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
	</div>
</div>
<script type="text/javascript">
jQuery(function(){	
	miniui_ext.initformenabled("contract_signatory_form1");
	miniui_ext.initformenabled("contract_rent_invoice_type_form");
	
});
</script>
