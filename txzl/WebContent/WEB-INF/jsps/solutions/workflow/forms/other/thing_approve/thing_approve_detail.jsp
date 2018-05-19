<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<input name="contract_change_info.id" id="contract_change_info.id" type="hidden" value="${requestScope['contract_change_info.id']}" />
<div id="contract_patrol_plan_form" title="重大事项信息">
<div class="mini-panel" title="重大事项信息" showCollapseButton="true" style="width:100%;">
	<table cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;" class="fillTable">
		<tr class="tr-odd">
				<td class="td-content-title"  width="160px">重大事项内容：</td>
	             <td class="td-content">
			             <input name="thing_disposition.thingmemo" class="mini-textbox"  value="${requestScope['thing_disposition.thingmemo'] }"  label="重大事项内容"   required="true"/>
			      </td>
				<td class="td-content-title"  width="160px">发生时间：</td>
	             <td class="td-content">
			             <input name="thing_disposition.occurdate"  class="mini-datepicker" label="发生时间" textField="name"  required="true" value="${requestScope['thing_disposition.occurdate']}" />
			      </td>
			</tr>
		
		<tr class="tr-even">
			<td class="td-content-title">跟踪措施：</td>
			<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3"><input maxLength="1000" emptyText="请输入跟踪措施" label="跟踪措施" style="width:74%;height: 80px;" id="followstep" name="thing_disposition.followstep" class="mini-textarea" allowInput="true" required="true" label="跟踪措施" value="${requestScope['thing_disposition.followstep']}"/></td>
		</tr>
		<tr class="tr-even">
			<td class="td-content-title">后续计划：</td>
			<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3"><input maxLength="1000" emptyText="请输入后续计划" label="后续计划" style="width:74%;height: 80px;" id="overplan" name="thing_disposition.overplan" class="mini-textarea" allowInput="true" required="true" label="后续计划" value="${requestScope['thing_disposition.overplan']}"/></td>
		</tr>
		<tr class="tr-odd">
				<td class="td-content-title"  width="160px">重大事项状态：</td>
	             <td class="td-content" >
				<input name="thing_disposition.thingstatus" id ="thingstatus"  class="mini-combobox" 
				   data="[{text:'跟踪'},{text:'终止'}]" required="true" label="重大事项状态" 
					type="text" value="${requestScope['thing_disposition.thingstatus']}"
					textField="text" valueField="text" >
				</td>
				
			      <td class="td-content-title"  width="160px">资产分类：</td>
			      <td class="td-content">
					<input name="thing_disposition.assettype" class="mini-combobox" textField="name" required="true"   valueField="value" dataField="datas" allowInput="false"
						data-options="{_params:{pid:'five_class'}}" onbeforeshowpopup="onbeforeshowpopup" value="${requestScope['thing_disposition.assettype'] }" 
						text="${requestScope['rawValue_thing_disposition.assettype'] }" label="资产分类" 
						onvaluechanged="comboboxChanged" />
					<input id="rawValue_thing_disposition.assettype" name="rawValue_thing_disposition.assettype" value="${requestScope['rawValue_thing_disposition.assettype']}"
						class="mini-textbox" style="display: none" />
				</td>
				
		</tr>
	</table>
</div>
</div>
<script type="text/javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("contract_patrol_plan_form");};
});
</script>
