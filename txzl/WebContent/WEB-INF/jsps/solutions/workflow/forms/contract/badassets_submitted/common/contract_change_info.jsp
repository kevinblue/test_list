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
						   allowInput="false" 
						   showNullItem="true" 
						   nullItemText=""
						   >
               	  </td>
	          </tr>	          
	          <tr class="tr-project-info tr-odd">
		            <td class="td-content-title" width="12%">基本情况：</td>
		            <td class="td-content" colspan="3">
		            	<input style="width:75%;height: 80px;" name="badassets_info.basicsituation" id ="basic_situation" class="mini-textarea" label="基本情况"  type="text" value="${requestScope['badassets_info.basicsituation']}">
		            </td>
	          </tr>
	          <tr class="tr-project-info tr-odd">
		            <td class="td-content-title" width="12%">已采取的清收措施：</td>
		            <td class="td-content" colspan="3">
		            	<input style="width:75%;height: 80px;" name="badassets_info.collmeasures" id ="coll_measures" class="mini-textarea" label="已采取的清收措施"  type="text" value="${requestScope['badassets_info.collmeasures']}">
		            </td>
	          </tr>
	          <tr class="tr-project-info tr-odd">
		            <td class="td-content-title" width="12%">不良资产产生的原因：</td>
		            <td class="td-content" colspan="3">
		            	<input style="width:75%;height: 80px;" name="badassets_info.badassets" id ="badassets" class="mini-textarea" label="不良资产产生的原因"  type="text" value="${requestScope['badassets_info.badassets']}">
		            </td>
	          </tr>
	          <tr class="tr-project-info tr-odd">
		            <td class="td-content-title" width="12%">建议处置措施：</td>
		            <td class="td-content" colspan="3">
		            	<input style="width:75%;height: 80px;" name="badassets_info.treatmentschema" id ="treatment_schema" class="mini-textarea" label="建议处置措施"  type="text" value="${requestScope['badassets_info.treatmentschema']}">
		            </td>
	          </tr>
	          <tr class="tr-project-info tr-odd">
		            <td class="td-content-title" width="12%">改进建议：</td>
		            <td class="td-content" colspan="3">
		            	<input style="width:75%;height: 80px;" name="badassets_info.impsuggestion" id ="impsuggestion" class="mini-textarea" label="改进建议"  type="text" value="${requestScope['badassets_info.impsuggestion']}">
		            </td>
	          </tr>
	</table>
</div>
<script language="javascript">
jQuery(function(){
	mini.get("financing_amount").setValue(formatNumberThousand(mini.get("financing_amount").getValue()));
	mini.get("financing_balance").setValue(formatNumberThousand(mini.get("financing_balance").getValue()));
	mini.get("receive_amount").setValue(formatNumberThousand(mini.get("receive_amount").getValue()));
	mini.get("payment_amount").setValue(formatNumberThousand(mini.get("payment_amount").getValue()));
}); 
function formatNumberThousand(s) {  
	   s += "";
	   s = s.replace(/,/g,"");
	   if(isNaN(s)){
		   return s;
	   }
	   //if(s==0){s="0.00";}
	   s += "";
	   //s = s.replace(/,/g,"");
	   var re=/(\d{1,3})(?=(\d{3})+(?:$|\D))/g;
	   var n1=s.replace(re,"$1,");
	   return n1;
}
</script>

