<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<input style="display:none;"	class="mini-textarea" id="id_contractidselect_str" name="json_leasing_info_list_str" value='${requestScope['contract_info.id'] }'></input>

<script type="text/javascript">
var contractidselect = $("#id_contractidselect_str").val();

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
<!-- 多行控件 -->
	<input  style="display:none;"	class="mini-textarea" id="id_json_asset_classify_str" name="json_asset_classify_str" value='${empty json_asset_classify_str ? "[]" : json_asset_classify_str }'></input>
	<input  style="display:none;"	class="mini-textarea" id="id_json_contract_supplier_str" name="json_contract_supplier_str" value='${empty json_contract_supplier_str ? "[]" : json_contract_supplier_str }'></input>
	<input  style="display:none;"	class="mini-textarea" id="id_json_contract_guarantee_equip_str" name="json_contract_guarantee_equip_str" value='${empty json_contract_guarantee_equip_str ? "[]" : json_contract_guarantee_equip_str }'></input>
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
		<div title="五级分类" name="bussinessfivecategory" iconCls="icon-node">
			<jsp:include page="comm/bussiness_five_category.jsp" >
			<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		<%-- <div title="抵质押物信息" name="contract_guarantee_equip" iconCls="icon-node">
			<jsp:include page="../contract_change/contract_guarantee_equip_tmp.jsp" >
			<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div> --%>
		<div title="抵质押情况" name="mortgage_info_list" iconCls="icon-node">
			<jsp:include page="../contract_inspection/comm/mortgage_info_list2.jsp" ></jsp:include>
		</div>
	   <%--  <div title="签约方" name="contract_supplier" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_signatory.jsp" >
			<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div> --%>
		 <div title="生成资产分类认定表" name="quotation_scheme" iconCls="icon-node" >
			<jsp:include page="comm/assets_five_category_confirm.jsp" >
			<jsp:param value="true" name="isView"/>
			</jsp:include>
	    </div>  
	</div>
</div>