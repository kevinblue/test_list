<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="ebank_import_form" style= "clear:both;">
     <div class="mini-panel" title="变更前信息" showCollapseButton="true" style="width: 50%; float:left">
	    <table class="fillTable" cellspacing="0" cellpadding="0" id="ebank_info" >
	     	 <tr class="tr-client-info tr-even">
	     		<td class="td-content-title" width="12%">承租人：</td>
				<td class="td-content" width="38%"><input require="true" label="承租人" name="contract_signatory.client" class="mini-textbox" readOnly value="${requestScope['contract_signatory.client']}"></td>
			</tr>
	     	<tr class="tr-client-info tr-even">
	     		<td class="td-content-title">开户账号：</td>    
	     		<td class="td-content"><input id="contract_signatory_clientaccnumber" label="开户帐号" name="contract_signatory_clientaccnumber"  readOnly class="mini-textbox" value="${requestScope['contract_signatory.clientaccnumber']}"></td>
	     	</tr>
	     	<tr class="tr-client-info tr-even">
	     	<td class="td-content-title">开户银行：</td>
				<td class="td-content"><input require="true" label="开户银行" id="contract_signatory_clientaccbank" name="contract_signatory.clientaccbank" readOnly class="mini-textbox" value="${requestScope['contract_signatory.clientaccbank']}"></td>
	     	</tr>
	     	<tr class="tr-client-info tr-even">
	     	<td class="td-content-title">开户户名：</td>
				<td class="td-content"><input require="true" label="开户户名" id="contract_signatory_clientaccname" name="contract_signatory.clientaccname" readOnly class="mini-textbox" value="${requestScope['contract_signatory.clientaccname']}"></td>
	     	</tr>
	     	   
	</table>
	</div>
	<div id="changeDeatils" class="mini-panel" title="变更后信息" showCollapseButton="true" style="width: 50%; float:right">
	<table class="fillTable" cellspacing="0" cellpadding="0" id="ebank_info" >
	   		<tr class="tr-client-info tr-even">
	     		<td class="td-content-title" width="12%">承租人：</td>
				<td class="td-content" width="38%"><input require="true" label="承租人" name="contract_signatory.client1" class="mini-textbox"  value="${requestScope['contract_signatory.client1']}"></td>
			</tr>
	     	<tr class="tr-client-info tr-even">
	     		<td class="td-content-title">开户账号：</td>    
	     		<td class="td-content"><input id="contract_signatory_clientaccnumber1" label="开户帐号" name="contract_signatory.clientaccnumber1"   class="mini-textbox" value="${requestScope['contract_signatory.clientaccnumber1']}"></td>
	     	</tr>
	     	<tr class="tr-client-info tr-even">
	     	<td class="td-content-title">开户银行：</td>
				<td class="td-content"><input require="true" label="开户银行" id="contract_signatory_clientaccbank1" name="contract_signatory.clientaccbank1"  class="mini-textbox" value="${requestScope['contract_signatory.clientaccbank1']}"></td>
	     	</tr>
	     	<tr class="tr-client-info tr-even">
	     	<td class="td-content-title">开户户名：</td>
				<td class="td-content"><input require="true" label="开户户名" id="contract_signatory_clientaccname1" name="contract_signatory.clientaccname1"  class="mini-textbox" value="${requestScope['contract_signatory.clientaccname1']}"></td>
	     	</tr>
	</table>
	</div>
</div>
<script type="text/javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("changeDeatils");};
});
</script>