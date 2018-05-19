<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
	<script type="text/javascript">
		jQuery(function(){
			var creditStatus = [{text:'同意上会',value:'同意上会'},{text:'不同意上会',value:'不同意上会'}];
			mini.get('leadervote').set({
				textField : "text",
				valueField : "value",
				data:creditStatus	
			});
		});
	</script>
	<div  id="id_project_leader_vote" title="上会投票信息" > 
	<div class="mini-panel" title="上会投票信息" showCollapseButton="true" style="width: 100%;">
		<table class="fillTable" cellspacing="0" cellpadding="0"  >
			<tr class="tr-project-info tr-odd">  
				<td class="td-content-title" style="width:12%;">是否继续上会：</td>
				<td class="td-content" style="width:476px;">
					<input name="proj_info.leadervote" id="leadervote" class="mini-combobox" required="true"  label="投票意见" showNullItem="false"  value="${requestScope['proj_info.leadervote']}"/>
				 </td>
				 </tr>
				 <tr class="tr-project-info tr-even">
				 <td class="td-content-title" style="width:12%">意见：</td>
				<td class="td-content" colspan="3">
					<input name="proj_info.leadervoteview" id="leadervoteview" class="mini-textarea"  required="true"  label="意见" style="width:80%;height:200px"  value="${requestScope['proj_info.leadervoteview']}"/>
				 </td>
	          </tr>
		</table>
	</div>
	</div>
<script language="javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("id_project_leader_vote");};
});
</script>

