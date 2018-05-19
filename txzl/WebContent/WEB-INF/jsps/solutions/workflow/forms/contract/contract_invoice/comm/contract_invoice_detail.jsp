<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
 <div  id="contract_invoice_form"  title="资金开票审批"> 
    <div   class="mini-panel" title="资金开票审批" showCollapseButton="true" style="width: 100%;">
	<table class="fillTable" cellspacing="0" cellpadding="0" id="contract_invoice" >
	          <tr class="tr-project-info tr-even">
	          	<td class="td-content-title" width="10%" >
	          		资金开票编号:
	             </td>	             
	             <td class="td-content" width="40%"  >	            	             
	             	<input  name="contract_info.invoiceid" id ="contract_info.invoiceid"  type="text" 	    
	               class="mini-textbox"  allowinput="false" value="${requestScope['contract_info.invoiceid']}">
	               </td> 
	             <td class="td-content-title" width="10%" >
	          		  申请人:
	             </td>	             
	             <td class="td-content" width="40%"  >	            	             
	             	<input  name="contract_info.realname" id ="contract_info.realname"  type="text" 	    
	               class="mini-textbox"  allowinput="false"  value="${requestScope['contract_info.realname']}">
	               </td>  
	                  
	          </tr>	  
	          <tr class="tr-project-info tr-even">
 
	            <td class="td-content-title" width="10%" >
	          		 申请时间:
	             </td>	             
	             <td class="td-content" width="40%" >	            	             
	             	<input  name="contract_info.thisdate" id ="contract_info.thisdate"  type="date" format="yyyy-MM-dd"
	               allowinput="false"   class="mini-textbox"    value="${requestScope['contract_info.thisdate']}">
	               </td>         
	          </tr>	 
	          <tr class="tr-project-info tr-even">
	             <td class="td-content-title" > 
	             	申请开票内容描述:
	             </td>
	             <td  colspan="2" width="40%" style="padding-top: 5px;padding-bottom: 5px;" > 
	             <input  width="70%" height="80px" class="mini-textarea"  name="contract_invoice.letterdescription" id ="contract_invoice.letterdescription"  label="函件内容描述:" 
	             value="${requestScope['contract_invoice.letterdescription']}"  type="text"  >  </td>
	        </tr>  
		</table>
	</div>
</div> 


<script language="javascript">

jQuery(function(){
	if('${param.isView}' == 'true'){
		miniui_ext.disableFormFields("contract_invoice_form");
	};
}); 
</script> 
