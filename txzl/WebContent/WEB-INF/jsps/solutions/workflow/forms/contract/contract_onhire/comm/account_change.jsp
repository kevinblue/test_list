<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>	
<div id="account_change_form" title="租金回笼账户变更">
	<div class="fillTableContainer">
		
		<div class="mini-panel" title="变更前账号信息"
			style="width: 50%; float: left">
			<table cellspacing="0" cellpadding="0" width="100%" class="fillTable">
			  	<tr class="tr-lessor-info tr-odd">
					<td class="td-content-title">开户账号别名：</td>
					<td class="td-content">
					<input label="开户账号别名"  readonly
					id="contract_signatory.memoname" 
					name="contract_signatory.memoname" 
					class="mini-textbox" 
					value="${requestScope['contract_signatory.memoname'] }"/></td>
				</tr>
			  	<tr class="tr-lessor-info tr-odd">
					<td class="td-content-title">开户户名：</td>
					<td class="td-content">
					<input require="true" label="开户户名" readonly 
					name="contract_signatory.leaseaccname" 
					id="contract_signatory.leaseaccname" class="mini-textbox"  
					value="${requestScope['contract_signatory.leaseaccname']}"></td>
				</tr>
				<tr class="tr-lessor-info tr-odd">
					<td class="td-content-title">开户账号：</td>
					<td class="td-content">
					<input label="开户账号"  class="mini-textbox"  readonly
					id="contract_signatory.leaseaccnumber" 
					name="contract_signatory.leaseaccnumber" 
					value="${requestScope['contract_signatory.leaseaccnumber']}"></td>
				</tr>
				<tr class="tr-lessor-info tr-odd">
					<td class="td-content-title">开户银行：</td>
					<td class="td-content">
					<input require="true" label="开户银行" readonly 
					name="contract_signatory.leaseaccbank" 
					id="contract_signatory.leaseaccbank" class="mini-textbox" 
					value="${requestScope['contract_signatory.leaseaccbank']}"></td>
				</tr>
			</table>
		</div>
		<div class="mini-panel" title="变更后账户信息"
			style="width: 50%; float: left">
			<table cellspacing="0" cellpadding="0" width="100%" class="fillTable">
				<tr class="tr-lessor-info tr-odd">
					<td class="td-content-title">开户账号别名：</td>
					<td class="td-content">
					<input id="contractsignatory.memoname" name="contractsignatory.memoname" label="开户账号别名"  
					class="mini-buttonedit mini-queryinput" 
					allowInput="false"
					value="${requestScope['contractsignatory.memo'] }" 
					text="${requestScope['rawValue_contractsignatory.memoname'] }"
					/>
					 <input id="rawValue_contractsignatory.memoname" name="rawValue_contractsignatory.memoname" 
					 value="${requestScope['rawValue_contractsignatory.memoname']}" class="mini-textbox" style="display:none"/>
	             	</td>
				</tr>
				<tr class="tr-lessor-info tr-odd">
					<td class="td-content-title">开户户名：</td>
					<td class="td-content">
					<input require="true" label="开户户名" readonly 
					name="contractsignatory.leaseaccname" 
					id="contractsignatory.leaseaccname" class="mini-textbox"  
					value="${requestScope['contractsignatory.leaseaccname']}"></td>
				</tr>
				<tr class="tr-lessor-info tr-odd">
					<td class="td-content-title">开户账号：</td>
					<td class="td-content">
					<input label="开户账号"  class="mini-textbox"  readonly
					id="contractsignatory.leaseaccnumber" 
					name="contractsignatory.leaseaccnumber" 
					value="${requestScope['contractsignatory.leaseaccnumber']}"></td>
				</tr>
				<tr class="tr-lessor-info tr-odd">
					<td class="td-content-title">开户银行：</td>
					<td class="td-content">
					<input require="true" label="开户银行" readonly 
					name="contractsignatory.leaseaccbank" 
					id="contractsignatory.leaseaccbank" class="mini-textbox" 
					value="${requestScope['contractsignatory.leaseaccbank']}"></td>
				</tr>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
if('${param.isView}' == 'true'||isViewHistoryTask==true){ 
    miniui_ext.disableFormFields("account_change_form");
}
jQuery(function(){
	tenwa.createQueryInput({ 
		id:'contractsignatory.memoname',
		label:'开户账号别名',
		windowWidth: 600,
		textField:"memoname",
      	valueField:"memo",
		onSelect: function($me, inputObj, rowData){
			mini.getbyName("contractsignatory.leaseaccbank").setValue(rowData.accbank);
			mini.getbyName("contractsignatory.leaseaccname").setValue(rowData.accname);
			mini.getbyName("contractsignatory.leaseaccnumber").setValue(rowData.accnumber);
			mini.getbyName("rawValue_contractsignatory.memoname").setValue(rowData.memoname);
		},
		params: {
			xmlFileName: '/eleasing/jsp/sysmgr/sysdatamgr/ownaccountinfo.xml'
		}
	});
})


/* function leaseaccnumbervaluechange(e){
		var selected = e.selected;
		mini.getbyName("contractsignatory.leaseaccbank").setValue(selected.accbank);
		mini.getbyName("contractsignatory.leaseaccname").setValue(selected.accname);
		mini.getbyName("contractsignatory.leaseaccnumber").setValue(selected.accnumber);
		//mini.getbyName("contract_signatory.bankno").setValue(selected.bankno);
	} */
</script>