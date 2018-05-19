<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<div  id="id_proj_credit_isapproval"   title="信审信息">  
    <div class="mini-panel" title="信审信息" showCollapseButton="true" style="width: 100%;">
	    <table class="fillTable" cellspacing="0" cellpadding="0"  >
	    	<%--  <tr class="tr-project-info tr-even">
	    	 	<td  class="td-content-title" width="12%">信审结论类型：</td>
	             <td class="td-content"   width="38%">
	             <input id="proj_info.creditisapproval" name="proj_info.creditisapproval"  label="信审结论类型" class="mini-combobox" textField="name" readonly="true"  
		                  	   valueField="value"  
							   dataField="datas"
							   allowInput="false" 
							   data-options="{_params:{pid:'CreditType'}}" 
							   onbeforeshowpopup="onbeforeshowpopup"
							   value="${requestScope['proj_info.creditisapproval'] }" 
							   text="${requestScope['rawValue_proj_info.creditisapproval'] }" 
							   onvaluechanged="comboboxChanged"
				 />	 
				 <input id="rawValue_proj_info.creditisapproval" name="rawValue_proj_info.creditisapproval" value="${requestScope['rawValue_proj_info.creditisapproval']}" class="mini-textbox" style="display:none"/>
				 </td>
	          	<td class="td-content-title"  width="12%" >信审通过时间：</td>
	             <td class="td-content"  width="38%">
	                   <input name="proj_info.approvedate" id="approvedate" allowInput="false"  class="mini-datepicker"   label="信审通过时间"   value="${requestScope['proj_info.approvedate']}"/>
				 </td>
	          </tr> --%>
	          <tr class="tr-project-info tr-odd"> 
	    	 	<td  class="td-content-title" width="5%">是否上会：</td>
	             <td class="td-content"   width="38%">
	             <input id="proj_info.isveto" name="proj_info.isveto"  label="是否使用一票否决" class="mini-combobox" textField="text"  
		                  	   valueField="value"  
							   allowInput="false" 
							   data="[{text:'是',value:'是'},{text:'否',value:'否'}]"
							   value="${requestScope['proj_info.isveto'] }" 
							   text="${requestScope['rawValue_proj_info.isveto'] }"
							   showNullItem="true" 
							   required="true"
							   onvaluechanged="comboboxChanged"
				 />	 
				 <input id="rawValue_proj_info.isveto" name="rawValue_proj_info.isveto" value="${requestScope['rawValue_proj_info.isveto']}" class="mini-textbox" style="display:none"/>
				 </td>
<%-- 				 <td class="td-content-title">风控经办：</td>
				  <td class="td-content" colspan="3">
	             	 <input name="proj_info.creditmanager" id="creditmanager" style="display:none" class="mini-combobox" textField="name"   label="风控经办"
		                   	   valueField="id"  
							   dataField="datas"
							   allowInput="false" 
							   data-options="{_xmlFileName:'/eleasing/workflow/proj/proj_credit/proj_credit_manager.xml'}" 
							   onbeforeshowpopup="miniextonbeforeshowpopup"
							   value="${requestScope['proj_info.creditmanager']}" 
							   text="${requestScope['rawValue_proj_info.creditmanager']}" 
							   onvaluechanged="comboboxChanged"
				 />	
				 <input id="rawValue_proj_info.creditmanager" name="rawValue_proj_info.creditmanager" value="${requestScope['rawValue_proj_info.creditmanager']}" class="mini-textbox" style="display:none"/>
				 </td> --%>
				 </tr>
			<tr class="td-content-title tr-even">
				  <td class="td-content-title" >意见：</td>
	             <td class="td-content" colspan="3">
	               <textarea name="proj_info.approveconclusion" id="approveconclusion"  label="信审结论"  class="mini-textarea" style="width:79%;height:150px"  value="${requestScope['proj_info.approveconclusion']}"/></textarea>
				 </td> 
	          </tr>
		</table>
	</div>
</div>
<script language="javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("id_proj_credit_isapproval");};
});
</script>
