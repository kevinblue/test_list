<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<div id="id_proj_choose_projresponsible" title="信审方向负责人" >
		<table class="fillTable" cellspacing="0" cellpadding="0" id="div_responsible" >
			<tr class="tr-project-info tr-even">
			   <td class="td-content-title" width="12%">信审方向负责人：</td>
				<td class="td-content" width="88%">
					<input id="proj_info.projresponsible" name="proj_info.projresponsible" label="信审方向负责人" class="mini-buttonedit mini-queryinput" allowInput="false"
						required="true"
						text="${requestScope['rawValue_proj_info.projresponsible'] }"
						value="${requestScope['proj_info.projresponsible'] }"
						/>
					<input id="rawValue_proj_info.projresponsible" name="rawValue_proj_info.projresponsible"
						value="${requestScope['rawValue_proj_info.projresponsible']}"
						class="mini-textbox" style="display: none" />
				</td>
		</tr>
	</table>
</div>
<script language="javascript">
jQuery(function(){
	var strs = [{key:"proj_info.projresponsible",value:"信审方向负责人"}];
	for(var i = 0 ;i<strs.length;i++){
		tenwa.createQueryInput({ 
			id:strs[i].key,
			label:strs[i].value,
			textField:"name",
	      	valueField:"id",
			windowWidth: 600,
			onSelect: function($me, inputObj, rowData){
				mini.get("rawValue_"+inputObj.id).setValue(rowData.name);
			},
			params: {
				xmlFileName: '/eleasing/workflow/proj/proj_common/choose_proj_responsible.xml'
			}
		});
	}
});
if('${param.isView}' == 'true'||isViewHistoryTask==true){ 
    miniui_ext.disableFormFields("div_responsible");
}
</script>

