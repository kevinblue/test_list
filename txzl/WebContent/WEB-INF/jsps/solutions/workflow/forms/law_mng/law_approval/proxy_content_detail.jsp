<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="proxy_content_form" title="代理人信息">
	<table cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;" class="fillTable">
		<tr class="tr-odd">
				<td class="td-content-title"  width="158px">代理情况：</td>
	             <td class="td-content">
			             <input name="law_approval.proxytype" class="mini-combobox" label="代理情况" textField="name"  
				                  	   valueField="value"  
									   dataField="datas"
									   allowInput="false" 
									   data-options="{_params:{pid:'proxy_content'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
									   multiSelect="true"
						               value="${requestScope['law_approval.proxytype'] }" 
									   text="${requestScope['law_approval.proxytypename'] }" 
									   onvaluechanged="comboboxChanged" />
						 <input id="law_approval.proxytypename" name="law_approval.proxytypename" value="${requestScope['law_approval.proxytypename'] }" class="mini-textbox" style="display:none"/>
			      </td>
			<td class="td-content-title" >我方承办人姓名：</td>
			<td class="td-content"> 
			<input name="law_approval.contractorname" class="mini-textbox" label="我方承办人姓名" textField="name"  
						               value="${requestScope['law_approval.contractorname'] }"  /></td>
		</tr>
		<tr class="tr-even">
				<td class="td-content-title"  width="130px">职务：</td>
	             <td class="td-content">
			             <input name="law_approval.lawposition" class="mini-textbox" label="职务" textField="name"  
						               value="${requestScope['law_approval.lawposition'] }"  />
			      </td>
			<td class="td-content-title" >职位：</td>
			<td class="td-content"> 
			<input name="law_approval.lawpost" class="mini-textbox" label="职位" textField="name"  
						               value="${requestScope['law_approval.lawpost'] }"  /></td>
		</tr>
		<tr class="tr-odd">
				<td class="td-content-title" >公司电话：</td>
	             <td class="td-content">
			             <input name="law_approval.compphone" class="mini-textbox"  value="${requestScope['law_approval.compphone'] }"  label="公司电话"   />
			      </td>
				<td class="td-content-title" >联系方式：</td>
	             <td class="td-content">
			             <input name="law_approval.linktype" class="mini-textbox" label="联系方式" textField="name"  
						               value="${requestScope['law_approval.linktype'] }"  />
			      </td>
		</tr>
		<tr class="tr-even">
			<td class="td-content-title">备注：</td>
			<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3"><input maxLength="1000" emptyText="请输入备注" label="备注" style="width:75%;height: 80px;" id="proxymemo" name="law_approval.proxymemo" class="mini-textarea" allowInput="true"  label="备注" value="${requestScope['law_approval.proxymemo']}"/></td>
		</tr>
	</table>
</div>
<script type="text/javascript">
function comboboxChanged(){
	var proxytype=mini.getbyName("law_approval.proxytype").getText();
	mini.getbyName("law_approval.proxytypename").setValue(proxytype);
}
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("proxy_content_form");};
});
</script>
