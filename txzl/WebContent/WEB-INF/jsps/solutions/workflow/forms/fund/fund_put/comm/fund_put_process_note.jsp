<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
		<table class="fillTable" cellspacing="0" cellpadding="0" id="div_limit_info" >
			<tr class="tr-project-info tr-even">
				 <td class="td-content-title" width="12%">流程备注：</td>
	             <td class="td-content" width="88%">
	             	<input id="progressnote" 
					 name="progressnote" 
					 value="${requestScope['progressnote']}" 
					 class="mini-textbox" style="width:30%" maxlength="20"/>
		          </td>
	        </tr>
	        <tr class="tr-project-info tr-odd">  
	             <td class="td-content-title">是否抵扣：</td>
	             <td class="td-content">
		             <input name="proj_info.fund_delivery" id="proj_info.fund_delivery" class="mini-combobox" label="内部行业"
		             				textField="name" 
		             				required="true"
		             				valueField="value" 
								   allowInput="false" 
								   data="[{value:'是',name:'是'},{value:'否',name:'否'}]"
								   text="${empty requestScope['rawValue_proj_info.fund_delivery'] ? '否' : requestScope['rawValue_proj_info.fund_delivery']}"
								   value="${empty requestScope['proj_info.fund_delivery'] ? '否' : requestScope['proj_info.fund_delivery']}"
								   		   onvaluechanged="comboboxChanged" 
								/>		 
					 <input id="rawValue_proj_info.fund_delivery" name="rawValue_proj_info.fund_delivery"  value="${requestScope['rawValue_proj_info.fund_delivery']}" class="mini-textbox" style="display:none;"/>
		           </td>
		    </tr>
		</table>
<script language="javascript">
if('${param.isView}' == 'true'||isViewHistoryTask==true){ 
    miniui_ext.disableFormFields("div_limit_info");
}
</script>



