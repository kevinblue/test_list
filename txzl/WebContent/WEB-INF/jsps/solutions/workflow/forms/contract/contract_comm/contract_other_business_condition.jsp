<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="contract_other_business_condition_form">
<table cellspacing="0" cellpadding="0"  style="width:100%;border: 1px solid #99CCFF;" class="fillTable">
     <tr class="tr-contractect-info tr-odd">
	             <td class="td-content-title">备注：</td>
	             <td colspan=3 style="padding-top: 4px;padding-bottom: 4px;">
		             <input style="width:72%;height:150px" name="contract_info.custmemo" label="备注"  value="${requestScope['contract_info.custmemo'] }"  label="备注" class="mini-textarea" maxLength="500" type="text" > 
	             </td>
	          </tr>
</table>
</div>

<%-- <div title="客户描述" name="contract_cust_detail" iconCls="icon-cut" >
	<tr class="tr-odd">
		<td class="td-content-title" width="12%">其他商务条件：</td>
		<td style="padding: 5px 0px;" width="88%">
			<input name="contract_info.othercondition" id="contract_info_othercondition" class="mini-textarea" label="其它商务条件" style="width: 72%;height: 200px;" emptyText="请输入其它商务条件" value="${requestScope['contract_info.othercondition'] }" />
		</td>
	</tr>
     <jsp:include page="contract_cust_detail.jsp" ></jsp:include>
</div> --%>
<%-- <div title="代理商" name="contract_agent_detail" iconCls="icon-cut" >
     <jsp:include page="contract_agent_detail.jsp" ></jsp:include>
</div>
<div title="政府信息" name="contract_gov_detail" iconCls="icon-cut" >
     <jsp:include page="contract_gov_detail.jsp" ></jsp:include>
</div> --%>
<script>
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("contract_other_business_condition_form");};
});
</script>