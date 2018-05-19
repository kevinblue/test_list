<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
        <div id="div_limit_info_form" >
		<table class="fillTable" cellspacing="0" cellpadding="0" id="div_limit_info" >
	        <tr class="tr-project-info tr-odd">  
	             <td class="td-content-title" width="12%">借阅类型：</td>
	             <td class="td-content" width="88%">
		             <input name="conotract_info.borrowingtype" id="conotract_info.borrowingtype" class="mini-combobox" label="借阅类型" textField="name" valueField="value" required="true"
								   allowInput="false" 
								   data="[{value:'内部借阅',name:'内部借阅'},{value:'外部借阅',name:'外部借阅'}]"
								    onvaluechanged="comboboxChanged"
								   text="${requestScope['rawValue_conotract_info.borrowingtype']}"
								   value="${requestScope['conotract_info.borrowingtype']}"
								/>		 
					 <input id="rawValue_conotract_info.borrowingtype" name="rawValue_conotract_info.borrowingtype"  value="${requestScope['rawValue_conotract_info.borrowingtype']}" class="mini-textbox" style="display:none" />
		           </td>
		    </tr>
		</table>
		</div>
<script language="javascript">
if('${param.isView}' == 'true'||isViewHistoryTask==true){ 
    miniui_ext.disableFormFields("div_limit_info");
}
</script>



