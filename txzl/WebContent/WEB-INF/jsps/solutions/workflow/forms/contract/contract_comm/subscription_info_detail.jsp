<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<div id="contract_info_sign_detail" title="签约信息" showCollapseButton="true" class="mini-panel" style="width:100%">
	    <table cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;" class="fillTable">
				<tr class="tr-even">
					<td class="td-content-title" width="12%">回租发票数量：</td>
					<td class="td-content" width="38%">
						<input name="contract_info.leasinginvoicenum" label="回租发票数量" class="mini-textbox" value="${requestScope['contract_info.leasinginvoicenum'] }" allowInput="true" type="text"/>
	               </td>
					<td class="td-content-title">划拨设备数量：</td>
					<td class="td-content">
						<input name="contract_info.leasingequipnum" label="划拨设备数量" class="mini-textbox" value="${requestScope['contract_info.leasingequipnum'] }" allowInput="true" type="text"/></td>
				</tr>
				<tr id="id_public_tr"  class="tr-odd">
	               <td class="td-content-title" width="12%">中登网查询的设备数量：</td>
				   <td class="td-content" width="38%">
				   	<input name="contract_info.leasingregisternum" label="中登网查询的设备数量" class="mini-textbox" value="${requestScope['contract_info.leasingregisternum'] }" allowInput="true" type="text"/></td>
				</tr>
	          
	          <tr class="tr-contractect-info tr-odd">
	             <td class="td-content-title">发票验证查询：</td>
	             <td colspan=3 style="padding-top: 4px;padding-bottom: 4px;">
		             <input style="width:72%;height:150px" name="contract_info.leasinginvoicecheck" label="发票验证查询"  value="${requestScope['contract_info.leasinginvoicecheck'] }"  class="mini-textarea" maxLength="500" type="textarea" > 
	             </td>
	          </tr>
	</table>
</div>