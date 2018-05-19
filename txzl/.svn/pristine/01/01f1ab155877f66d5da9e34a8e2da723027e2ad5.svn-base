<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
        <div id="div_object_info_form" >
		<table class="fillTable" cellspacing="0" cellpadding="0" id="div_object_info" >
	        <tr class="tr-project-info tr-odd">  
	             <td class="td-content-title" width="12%">借阅对象：</td>
	             <td class="td-content" width="88%">
		             <input name="conotract_info.borrowingobject" id="conotract_info.borrowingobject" class="mini-combobox" label="借阅对象" textField="name" valueField="value" required="true"
								   allowInput="false" 
								   data="[{value:'档案室',name:'档案室'},{value:'保险柜',name:'保险柜'}]"
								    onvaluechanged="comboboxChanged"
								   text="${requestScope['rawValue_conotract_info.borrowingobject']}"
								   value="${requestScope['conotract_info.borrowingobject']}"
								/>		 
					 <input id="rawValue_conotract_info.borrowingobject" name="rawValue_conotract_info.borrowingobject"  value="${requestScope['rawValue_conotract_info.borrowingobject']}" class="mini-textbox" style="display:none" />
		           </td>
		    </tr>
		</table>
		</div>
<script language="javascript">
if('${param.isView}' == 'true'||isViewHistoryTask==true){ 
    miniui_ext.disableFormFields("div_object_info");
}
</script>



