<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/arrayUtils.js"></script>
<script type="text/javascript">
//是否保存
function workflowSaveCallBack() {
	miniui_ext.saveIncludeData("tabDetails");
	return true;
}
//保存成功提交后，后台返回
function saveCallBack() {
	return true;
}
//是否提交
function workflowSubmitCallBack(buttonText) {
	miniui_ext.saveIncludeData("tabDetails");
	return true;
}
</script>
<!--多行控件  -->
<input style="display:none;"	class="mini-textarea" id="id_json_contract_rentinvoice_list_str" name="json_contract_rentinvoice_list_str" value='${empty json_contract_rentinvoice_list_str ? "[]" : json_contract_rentinvoice_list_str }'></input>
<div id="contract_invoice_list">
	 <div class="fillTableContainer"> 
		<jsp:include page="comm/contract_rentinvoice_detail.jsp">
		<jsp:param value="true" name="isView"/>
		</jsp:include>
	  </div>
	<div id="tabDetails" class="mini-tabs"  activeIndex="0" style="width: 100%;" bodyStyle="border:0px;">
		<div title="租金开票信息" name="contract_rentinvoice_list" iconCls="icon-node">
			<jsp:include page="comm/contract_rentinvoice_listtwo.jsp" >
			<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div> 
	</div>
</div>