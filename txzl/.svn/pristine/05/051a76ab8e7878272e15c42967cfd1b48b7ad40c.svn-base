<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- <<script type="text/javascript">
var localEnabled = [{id : '否',text : '否'}, {id : '是',text : '是'}];
</script> -->

<input name="contract_change_info.id" id="contract_change_info.id" type="hidden" value="${requestScope['contract_change_info.id']}" />
<div id="contract_patrol_plan_form" title="法务处理信息">
	<table cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;" class="fillTable">
		<tr class="tr-odd">
				<td class="td-content-title"  width="158px">法务编号：</td>
	             <td class="td-content">
			             <input name="law_approval.lawnum" class="mini-textbox" readOnly value="${requestScope['law_approval.lawnum'] }"  label="法务编号"   />
						 <font class="required-tag">*</font>
			      </td>
				<td class="td-content-title"  width="130px">风险敞口：</td>
	             <td class="td-content">
			             <input name="law_approval.riskexposure" class="mini-textbox" label="风险敞口" textField="name"  
						               value="${requestScope['law_approval.riskexposure'] }"  />
			      </td>
		</tr>
		<tr class="tr-odd">
				<td class="td-content-title" >应收租金：</td>
	             <td class="td-content">
			             <input name="law_approval.rent" class="mini-textbox" readOnly value="${requestScope['law_approval.rent'] }"  label="法务编号"   />
			      </td>
				<td class="td-content-title" >应收债权总计：</td>
	             <td class="td-content">
			             <input name="law_approval.totaldebt" class="mini-textbox" label="应收债权总计" textField="name"  
						               value="${requestScope['law_approval.totaldebt'] }"  />
			      </td>
		</tr>
		<tr class="tr-odd">
				<td class="td-content-title" >联系地址：</td>
	             <td class="td-content">
			             <input name="law_approval.linkad" class="mini-textbox"  value="${requestScope['law_approval.linkad'] }"  label="法务编号"   />
			      </td>
				<td class="td-content-title" >法人代表：</td>
	             <td class="td-content">
			             <input name="law_approval.legalperson" class="mini-textbox" label="法人代表" textField="name"  
						               value="${requestScope['law_approval.legalperson'] }"  />
			      </td>
		</tr>
		<tr class="tr-odd">
				<td class="td-content-title" >代表人电话：</td>
	             <td class="td-content">
			             <input name="law_approval.dbphone" class="mini-textbox"  value="${requestScope['law_approval.dbphone'] }" required="true" label="代表人电话"   />
						 <font class="required-tag">*</font>
			      </td>
				<td class="td-content-title">移交时间：</td>
	             <td class="td-content">
			             <input name="law_approval.transfer" class="mini-datepicker" allowInput="false" label="移交时间" textField="name"  
						               value="${requestScope['law_approval.transfer'] }" />
			      </td>
		</tr>
		<tr class="tr-even">
			<td class="td-content-title"  width="158px">是否上传打印文件：</td>
	             <td class="td-content" >
					<input name="law_approval.isprintfile" id ="law_approval.isprintfile"  class="mini-combobox" 
					   data="[{text:'是'},{text:'否'}]" 
						type="text" value="${requestScope['law_approval.isprintfile']}"
						textField="text" valueField="text" >
				</td>
		</tr>
		<tr class="tr-even">
			<td class="td-content-title">办理意见：</td>
			<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3"><input maxLength="1000" emptyText="请输入办理意见" label="办理意见" style="width:75%;height: 80px;" id="law_approval.staffsugs" name="law_approval.staffsugs" class="mini-textarea" allowInput="true" required="true" label="办理意见" value="${requestScope['law_approval.staffsugs']}"/></td>
		</tr>
		<tr class="tr-even">
			<td class="td-content-title">备注：</td>
			<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3"><input maxLength="1000" emptyText="请输入备注" label="备注" style="width:75%;height: 80px;" id="law_approval.lawmemo" name="law_approval.lawmemo" class="mini-textarea" allowInput="true" required="true" label="备注" value="${requestScope['law_approval.lawmemo']}"/></td>
		</tr>
	</table>
</div>
<script type="text/javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("contract_patrol_plan_form");};
});
</script>
