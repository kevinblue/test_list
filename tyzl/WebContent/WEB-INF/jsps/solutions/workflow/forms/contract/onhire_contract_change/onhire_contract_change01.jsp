<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
//是否保存   
function workflowSaveCallBack()//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
{
	fillHiddenVal();
	return true;
}
//保存成功提交后，后台返回
function saveCallBack() {
	return true;
}
//是否提交    
function workflowSubmitCallBack(buttonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
{
	if (miniui_ext.submitFormValidation(["contract_change_info_form","contract_rent_invoice_type_form","contract_signatory_form"]) == false) return false;
	fillHiddenVal();
	if(!submitCheck()) return false;
	return true;
}
</script>

<!--start 多行控件  -->
<jsp:include page="comm/onhire_contract_mutli_info.jsp" ></jsp:include>
<!--end 多行控件  -->
 
<div id="onhire_contract_change_form" title="合同变更">
	<div class="fillTableContainer"> 
		<table class="fillTable" cellspacing="0" cellpadding="0">
			<tr>
				<td ><jsp:include page="../contract_comm/contract_base_info.jsp"><jsp:param value="true" name="isView"/></jsp:include></td>
			</tr>
		</table>
	</div>
	<div id="tabDetails" class="mini-tabs" activeIndex="0" style="width: 100%;" onactivechanged="changTab">
		<div title="变更说明" name="contract_change_info" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_change_info.jsp" ></jsp:include>
		</div>
		<div title="承租人变更" name="contract_cust_change" iconCls="icon-node">
			<jsp:include page="comm/onhire_contract_cust_change.jsp" ></jsp:include>
		</div>
		<div title="租赁物明细" name="old_contract_equip" iconCls="icon-node">
			<jsp:include page="comm/contract_equip_detail.jsp" ></jsp:include>
		</div>
		<div title="担保单位信息" name="contract_guarantee_method,old_contract_guarantee_method" iconCls="icon-node">
			<jsp:include page="comm/contract_guarantee_method.jsp" ></jsp:include>
		</div>
		<div title="抵押物信息" name="contract_guarantee_equip,old_contract_guarantee_equip" iconCls="icon-node">
			<jsp:include page="comm/contract_guarantee_equip.jsp" ></jsp:include>
		</div>
		<div title="开票说明" name="contract_change_info" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_rent_invoice_type.jsp"></jsp:include>
		</div>
		<div title="合同各方" name="contract_supplier" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_signatory.jsp"></jsp:include>
		</div>
	</div>
</div>
<script>
//表格值填入隐藏域
function fillHiddenVal(){
	 
	var grid_new_equip_data = mini.get("contract_equip").getData();
	mini.get("id_json_contract_equip_str").setValue(mini.encode(grid_new_equip_data));
	if(mini.get("contract_guarantee_method").isinitData==1){
		var grid_new_guarantee_method_data = mini.get("contract_guarantee_method").getData();
		mini.get("id_json_contract_guarantee_method_str").setValue(mini.encode(grid_new_guarantee_method_data));
	}	 
	if(mini.get("contract_guarantee_equip").isinitData==1){
		var grid_new_guarantee_equip_data = mini.get("contract_guarantee_equip").getData();
		mini.get("id_json_contract_guarantee_equip_str").setValue(mini.encode(grid_new_guarantee_equip_data));
	}	 
	if(mini.get("contract_supplier").isinitData==1){
		var grid_new_contract_supplier_data= mini.get("contract_supplier").getData();
		mini.get("id_json_contract_supplier_str").setValue(mini.encode(grid_new_contract_supplier_data));
	}
}
function submitCheck(){
	var grid_new_equip_data = mini.get("contract_equip").getData();
	if(grid_new_equip_data.length < 0){
		alert("现租赁物明细不能为空！");
		return false;
	}else{
		return true;
	}
}
</script>