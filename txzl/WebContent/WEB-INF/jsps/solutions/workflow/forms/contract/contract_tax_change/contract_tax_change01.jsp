<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
	if (miniui_ext.submitFormValidation(["contract_tax_change_form"]) == false) return false;

	return true;
}
</script>
<input  style="display:none;"	class="mini-textarea" id="json_tax_change_str" name="json_tax_change_str" value='${empty json_tax_change_str ? "[]" : json_tax_change_str }'></input>
<input style="display:none;"  class="mini-textbox" id="contractinvoicetype.id" name="contractinvoicetype.id" value="${requestScope['contractinvoicetype.id']}"/>
<input style="display:none;"  name="contract_info.id" id="contract_info.id" class="mini-textbox" value="${requestScope['contract_info.id']}"/>
<input style="display:none;"  name="custid" id="custid" class="mini-textbox" value="${requestScope['contract_info.custid']}"/>
<div id="contract_tax_change_form1">
	<div class="fillTableContainer"> 
		<table class="fillTable" cellspacing="0" cellpadding="0">
			<tr>
				<td >
				  <jsp:include page="common/contract_tax_change_info.jsp"></jsp:include>
			    </td>
			</tr>
		</table>
	</div>
	
	<div id="tabDetails" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width: 100%;" bodyStyle="border:0px;">
		<div title="项目信息" name="contractTaxpeople" iconCls="icon-node">
			<jsp:include page="common/contract_cust.jsp" ></jsp:include>
		</div>
	</div>
</div>


