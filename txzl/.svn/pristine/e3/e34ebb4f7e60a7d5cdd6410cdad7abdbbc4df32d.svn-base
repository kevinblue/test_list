<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<div id="id_contrade_transaction" title="贸易基础交易情况" showCollapseButton="true" style="width:100%;">
	     <table cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;" class="fillTable">
				<tr class="tr-even">
					<td class="td-content-title" width="20%">合同是否为长期（一年以上）供应合同：</td>
				    <td class="td-content" width="38%" >
				   	<input name="contrade_transaction.longcontract" label="合同是否为长期（一年以上）供应合同" class="mini-combobox miniext-form-fit" textField="text" 
	                  	   valueField="text"  
						   data="[{text:'是'},{text:'否'}]"
						   value="${requestScope['contrade_transaction.longcontract']}"
					       text="${requestScope['contrade_transaction.longcontract']}"
						   width="40%"
						   allowInput="false" 
						   showNullItem="true" nullItemText=""
						   emptyText=""
						   required="true"/>
					</td>
					<td class="td-content-title">合同付款期限：</td>
					<td class="td-content">
						<input name="contrade_transaction.contractpaymentterm" label="合同付款期限" class="mini-textbox" vType="int" value="${requestScope['contrade_transaction.contractpaymentterm'] }" allowInput="true" required="true"/></td>
				</tr>
				<tr id="id_medical_tr" class="tr-odd">
					<td class="td-content-title" width="12%" >实际基础交易与合同是否相符：</td>
				    <td class="td-content" width="38%" >
				   	<input name="contrade_transaction.actualtransaction" label="实际基础交易与合同是否相符" class="mini-combobox miniext-form-fit" textField="text" 
	                  	   valueField="text"  
						   data="[{text:'是'},{text:'否'}]"
						   value="${requestScope['contrade_transaction.actualtransaction']}"
					       text="${requestScope['contrade_transaction.actualtransaction']}"
						   width="40%"
						   allowInput="false" 
						   showNullItem="true" nullItemText=""
						   emptyText=""
						   required="true"/>
					</td>
	               <td class="td-content-title" width="12%">付款方式：</td>
				   <td  class="td-content" width="38%">
				   	<input name="contrade_transaction.paymentmode" label="付款方式" class="mini-textbox" value="${requestScope['contrade_transaction.paymentmode'] }" allowInput="true" required="true"/></td>
				</tr>
				<tr id="id_public_tr"  class="tr-even">
	               <td class="td-content-title" width="14%">合同是否含有禁止转让条款：</td>
				   <td class="td-content" width="38%">
				   	<input name="contrade_transaction.assignmentclause" label="合同是否含有禁止转让条款" class="mini-combobox "
				   		   textField="text" 
	                  	   valueField="text"  
						   data="[{text:'是'},{text:'否'}]"
						   value="${requestScope['contrade_transaction.assignmentclause']}"
					       text="${requestScope['contrade_transaction.assignmentclause']}"
						   width="40%"
						   allowInput="false" 
						   showNullItem="true" nullItemText=""
						   emptyText=""
						   required="true"/>
					</td>
	                <td class="td-content-title" width="12%">销售商品：</td>
					<td class="td-content" width="38%">
						<input name="contrade_transaction.sale" label="销售商品" class="mini-textbox" value="${requestScope['contrade_transaction.sale'] }" allowInput="true" required="true"/>
	               </td>
				</tr>
				<tr id="id_medical_tr" class="tr-odd">
	               <td class="td-content-title" width="12%">买卖双方是否关联企业：</td>
				   <td  class="td-content" width="38%">
				   	<input name="contrade_transaction.relationbusiness" label="买卖双方是否关联企业" class="mini-combobox miniext-form-fit" textField="text" 
	                  	   valueField="text"  
						   data="[{text:'是'},{text:'否'}]"
						   value="${requestScope['contrade_transaction.relationbusiness']}"
					       text="${requestScope['contrade_transaction.relationbusiness']}"
						   width="40%"
						   allowInput="false" 
						   showNullItem="true" nullItemText=""
						   emptyText=""
						   required="true"/>
					</td>
					<td class="td-content-title" width="12%">买卖双方预计年贸易额：</td>
					<td class="td-content" width="38%">
						<input name="contrade_transaction.tradevolume" label="买卖双方预计年贸易额" class="mini-textbox" vType="float" value="${requestScope['contrade_transaction.tradevolume'] }" allowInput="true" required="true"/>
	               </td>
				</tr>
				<tr id="id_public_tr"  class="tr-even">
	               <td class="td-content-title" width="12%">买方是否同意付款至保理专户：</td>
				   <td class="td-content" width="38%">
				   	<input name="contrade_transaction.factoringaccount" label="买方是否同意付款至保理专户" class="mini-combobox miniext-form-fit" textField="text" 
	                  	   valueField="text"  
						   data="[{text:'是'},{text:'否'}]"
						   value="${requestScope['contrade_transaction.factoringaccount']}"
					       text="${requestScope['contrade_transaction.factoringaccount']}"
						   width="40%"
						   allowInput="false" 
						   showNullItem="true" nullItemText=""
						   emptyText=""
						   required="true"/>
					</td>
	                <td class="td-content-title" width="12%">付款条件：</td>
					<td class="td-content" width="38%">
						<input name="contrade_transaction.paymentcondition" label="付款条件" class="mini-textbox" value="${requestScope['contrade_transaction.paymentcondition'] }" allowInput="true" required="true"/>
	               </td>
				</tr>
	          
	          <tr class="tr-project-info tr-odd">
	             <td class="td-content-title">卖方整体情况及买卖双方贸易情况：</td>
	             <td colspan=3 style="padding-top: 4px;padding-bottom: 4px;">
		             <input style="width:72%;height:150px" name="contrade_transaction.tradesituation" label="卖方整体情况及买卖双方贸易情况"  value="${requestScope['contrade_transaction.tradesituation'] }" class="mini-textarea" maxLength="500" required="true" /> 
	             </td>
	          </tr>
	          <tr class="tr-project-info tr-odd">
	             <td class="td-content-title">供应商已在金风融资情况：</td>
	             <td colspan=3 style="padding-top: 4px;padding-bottom: 4px;">
		             <input style="width:72%;height:150px" name="contrade_transaction.financingsituation" label="供应商已在金风融资情况"  value="${requestScope['contrade_transaction.financingsituation'] }" class="mini-textarea" maxLength="500" required="true" /> 
	             </td>
	          </tr>
	</table>
</div>
<script language="javascript">
if('${param.isView}' == 'true'||isViewHistoryTask==true){miniui_ext.disableFormFields("id_contrade_transaction");}
</script>