<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
   <div id="proj_change_form" title="撤销说明">
	    <table class="fillTable" cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;">
	          <tr class="tr-project-info tr-even">
	             <td class="td-content-title" width='12%'>撤销日期：</td>
	             <td class="td-content" width='38%'> <input name="proj_info.repealdate"  label="撤销日期" value="${requestScope['proj_info.repealdate'] }" class="mini-textbox"  allowInput="false"/></td>
	             <td class="td-content-title" width='12%'>撤销类型：</td>
	             <td class="td-content" width='38%'>
		             <input  name="proj_info.repealreasontype" label="撤销类型" class="mini-combobox" textField="name"  required="true"
						   valueField="value"  
						   dataField="datas"
						   allowInput="false" 
						   data-options="{_params:{pid:'repeal_type'}}" 
						   onbeforeshowpopup="onbeforeshowpopup"
						   value="${requestScope['proj_info.repealreasontype'] }" 
						   text="${requestScope['rawValue_proj_info.repealreasontype']}" 
						   onvaluechanged="comboboxChanged(e)" />
					 <input id="rawValue_proj_info.repealreasontype" name="rawValue_proj_info.repealreasontype" value="${requestScope['rawValue_proj_info.repealreasontype']}"  class="mini-textbox" style="display:none"/>
	             </td>
	          </tr>          
	          <tr class="tr-project-info tr-odd">
	             <td class="td-content-title">撤销说明：</td>
	             <td colspan="3" style="padding-top: 5px;padding-bottom: 5px;"><input style="width:72%;height:150px" label="撤销说明" name="proj_info.repealreason" value="${requestScope['proj_info.repealreason'] }" required="true" label="变更原因" class="mini-textarea"  type="text" >  </td>
	          </tr>
	            
	</table>
</div>
<script language="javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("proj_change_form");};
});
</script>


