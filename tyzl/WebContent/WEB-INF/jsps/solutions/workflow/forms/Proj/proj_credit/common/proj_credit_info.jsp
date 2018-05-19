<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld' %>
<%@ taglib uri="/minidict" prefix="mini"%>
<div  id="id_proj_credit_isapproval"   title="信审信息">  
    <div class="mini-panel" title="信审信息" showCollapseButton="true" style="width: 100%;">
	    <table class="fillTable" cellspacing="0" cellpadding="0"  >
	    	 <tr class="tr-project-info tr-even">
	    	 	<td  class="td-content-title" width="12%">信审结论类型：</td>
	             <td class="td-content"   width="38%">
	             <input id="proj_info.creditisapproval" name="proj_info.creditisapproval"  label="信审结论类型" class="mini-combobox" textField="name"  required="true"
		                  	   valueField="value"  
							   dataField="datas"
							   allowInput="false" 
							   data-options="{_params:{pid:'CreditType'}}" 
							   onbeforeshowpopup="onbeforeshowpopup"
							   value="<mini:param  name="proj_info.creditisapproval"/>"
							   text="<mini:param  name="rawValue_proj_info.creditisapproval"/>" 
							   onvaluechanged="comboboxChanged"
				 />	 
				 <font class="required-tag">*</font>
				 <input id="rawValue_proj_info.creditisapproval" name="rawValue_proj_info.creditisapproval" value="<mini:param  name="rawValue_proj_info.creditisapproval"/>" class="mini-textbox" style="display:none"/>
				 </td>
	          	<td class="td-content-title"  width="12%" >信审通过时间：</td>
	             <td class="td-content"  width="38%">
	                   <input name="proj_info.approvedate" id="approvedate" allowInput="false"  class="mini-datepicker"   label="信审通过时间"   value="<mini:param  name="proj_info.approvedate"/>" />
				 </td> 
	          </tr>
			<tr class="td-content-title tr-even">
				  <td class="td-content-title" >信审结论：</td>
	             <td class="td-content" colspan="3">
	               <textarea name="proj_info.approveconclusion" id="approveconclusion"  label="信审结论" required="true" class="mini-textarea" style="width:79%;height:150px"  value="<mini:param  name="proj_info.approveconclusion"/>"/></textarea>
				 </td> 
	          </tr>
	          <permission:action code="votepermission">
	          <tr class="td-content-title tr-even">
				  <td class="td-content-title" >总裁意见：</td>
	             <td class="td-content" colspan="3">
	               <textarea name="proj_info.presidentopinions" id="presidentopinions"  label="总裁意见" required="true" class="mini-textarea" style="width:79%;height:150px"  value="<mini:param  name="proj_info.presidentopinions"/>"/></textarea>
				 </td> 
	          </tr>
	          </permission:action>
		</table>
	</div>
</div>
<script language="javascript">
jQuery(function(){
	if('<mini:param name="isView"/>' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("id_proj_credit_isapproval");};
});
</script>
