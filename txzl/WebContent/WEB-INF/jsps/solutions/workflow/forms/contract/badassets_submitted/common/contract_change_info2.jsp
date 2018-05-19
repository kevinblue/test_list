<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/minidict" prefix="mini"%>
    <div id="proj_base_info_form" title="不良资产报送页签">
   
	    <table class="fillTable" cellspacing="0" cellpadding="0" id="proj_base_info" >
	          <tr class="tr-project-info tr-even">
	             <td class="td-content-title" width="12%">融资金额：</td>
	             <td class="td-content" width="38%">
	             	<input name="badassets_info.financingamount" id ="financing_amount"  class="mini-textbox" label="融资金额"  type="text" 
	             			allowInput="false" value="${requestScope['badassets_info.financingamount']}">
	             </td>
	            
	             <td class="td-content-title" width="12%">融资余额：</td>
	             <td class="td-content" width="38%">
	             	<input name="badassets_info.financingbalance" id ="financing_balance"  class="mini-textbox" label="融资余额"  type="text" 
	             			allowInput="false" value="${requestScope['badassets_info.financingbalance']}">
	             </td>
	          </tr>
	          
	          <tr class="tr-project-info tr-even">
	            <td class="td-content-title" width="12%">应收款金额：</td>
	            <td class="td-content" width="38%">
	            	<input name="badassets_info.receiveamount" id ="receive_amount"  class="mini-textbox" label="应收总金额"  type="text" 
	            			allowInput="false" value="${requestScope['badassets_info.receiveamount']}">
	            </td>
	            
	            <td class="td-content-title" width="12%">已收款金额：</td>
	            <td class="td-content" width="38%">
	            	<input name="badassets_info.paymentamount" id ="payment_amount"  class="mini-textbox" label="已收总金额"  type="text"
	            	       allowInput="false" value=${requestScope['badassets_info.paymentamount']}>
	            </td>
	          </tr>
	          
	          <tr class="tr-project-info tr-odd">
	             <td class="td-content-title">资产分类等级：</td>
	             <td class="td-content" width="38%">
	             	<input name="badassets_info.classifylevel" 
	             		   class="mini-combobox" 
	             		   textField="text" 
	                  	   valueField="text"  
						   data="[{text:'次级类'},{text:'可疑类'},{text:'损失类'}]"
						   value="${requestScope['badassets_info.classifylevel']}"
						   readOnly 
						   showNullItem="true" 
						   nullItemText=""
						   >
               	  </td>
	          </tr>
	          
	          <tr class="tr-project-info tr-odd" style="display:none">
		            <td class="td-content-title" width="12%">资产处理方案：</td>
		            <td class="td-content" colspan="3">
		            	<input style="width:60%" name="badassets_info.treatmentschema" id ="treatment_schema" allowInput="false" class="mini-textbox" label="资产处理方案"  type="text" value="${requestScope['badassets_info.treatmentschema']}">
		            </td>
	          </tr>
	</table>
</div>
<script language="javascript">

</script>

