<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
    <div id="contract_supplier_message" title="供应商信息">
    <div class="mini-panel" title="供应商信息" showCollapseButton="true" style="width: 100%;">
	    <table cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;" class="fillTable">
				<tr class="tr-even">
					<td class="td-content-title" width="12%">成立时间：</td>
					<td class="td-content" width="38%">
						<input name="contract_info.establishdate" label="成立时间" class="mini-textbox"  value="${requestScope['contract_info.establishdate'] }" allowInput="true" readonly/>
	               </td>
					<td class="td-content-title">企业性质：</td>
					<td class="td-content">
						<input name="contract_info.ownershipname" label="企业性质" class="mini-textbox"  value="${requestScope['contract_info.ownershipname'] }" allowInput="true"  readonly/></td>
				</tr>
				<tr id="id_medical_tr" class="tr-odd">
					<td class="td-content-title" width="12%">企业规模：</td>
					<td class="td-content" width="38%">
						<input name="contract_info.custscalename" label="企业规模" class="mini-textbox" value="${requestScope['contract_info.custscalename'] }" allowInput="true" readonly />
	               </td>
	               <td class="td-content-title" width="12%">金风评级：</td>
				   <td  class="td-content" width="38%">
				   	<input name="contract_info.graderesult" label="金凤评级" class="mini-textbox" value="${requestScope['contract_info.graderesult'] }" allowInput="true"  readonly/></td>
				</tr>
				<tr id="id_public_tr"  class="tr-even">
	               <td class="td-content-title" width="12%">与金风年订单量：</td>
				   <td class="td-content" width="38%">
				   	<input name="contract_info.indentamount" label="与金凤年订单量" class="mini-textbox" vType="float" required="true"  value="${requestScope['contract_info.indentamount'] }" allowInput="true" /></td>
	               <td class="td-content-title" width="12%" >应收账款余额：</td>
				   <td class="td-content" width="38%" >
				   	<input name="contract_info.creditbalance" label="应收账款余额" class="mini-textbox" vType="float" required="true"  value="${requestScope['contract_info.creditbalance'] }" allowInput="true" /></td>
				</tr>
	          
	          <tr class="tr-project-info tr-odd">
	             <td class="td-content-title">主营业务：</td>
	             <td colspan=3 style="padding-top: 4px;padding-bottom: 4px;">
		             <input style="width:72%;height:150px" name="contract_info.operatemaster" label="主营业务"  value="${requestScope['contract_info.operatemaster'] }"  readonly  class="mini-textarea" maxLength="500"  > 
	             </td>
	          </tr>
	</table>
	</div>
</div>
<script language="javascript">
if('${param.isView}' == 'true'||isViewHistoryTask==true){miniui_ext.disableFormFields("contract_supplier_message");}
</script>