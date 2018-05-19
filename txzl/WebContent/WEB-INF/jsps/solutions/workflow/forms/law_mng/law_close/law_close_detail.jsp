<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<input name="lawnum" id="lawnum" type="hidden"  value="${requestScope['lawnum'] }"/>
<div id="contract_lawclose_info_form" title="结案信息详情">
	<table cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;" class="fillTable">
			<tr class="tr-odd">
				<td class="td-content-title" width="160px">法务处理类型：</td>
	              <td class="td-content" colspan="3">
			             <input name="law_approval.jalawtype" class="mini-combobox" label="法务处理类型" textField="name" 
				                  	   valueField="value"  
									   dataField="datas"
									   allowInput="false" 
									   data-options="{_params:{pid:'jieanlx'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
						               value="${requestScope['law_approval.jalawtype'] }" 
									   text="${requestScope['rawValue_law_approval.jalawtype'] }" 
									   onvaluechanged="comboboxChanged" />
						 <font class="required-tag">*</font>
						 <input id="rawValue_law_approval.jalawtype" name="rawValue_law_approval.jalawtype" value="${requestScope['rawValue_law_approval.jalawtype']}" class="mini-textbox" style="display:none"/>
			      </td>
			</tr>
		<tr class="tr-even">
			<td class="td-content-title">结案信息：</td>
			<td class="td-content">
				<input  id="contract_change_info.beforecontent" label="结案信息" maxLength="50" name="law_approval.closeinfo" class="mini-textbox"   value="${requestScope['law_approval.closeinfo']}"/>
			</td>
			<td class="td-content-title">回收金额：</td>
			<td class="td-content">
				<input  id="contract_change_info.beforecontent" label="回收金额" vtype="float" name="law_approval.closemoney" class="mini-textbox"   value="${requestScope['law_approval.closemoney']}"/>
			</td>
		</tr>
		<tr class="tr-even">
			<td class="td-content-title">费用总计：</td>
			<td class="td-content">
				<input  id="contract_change_info.beforecontent" label="费用总计" vtype="float" name="law_approval.lawmoney" class="mini-textbox" value="${requestScope['law_approval.lawmoney']}"/>
			</td>
			<td class="td-content-title">净回收额：</td>
			<td class="td-content">
				<input  id="contract_change_info.beforecontent" label="净回收额" vtype="float" name="law_approval.netrecmoney" class="mini-textbox" value="${requestScope['law_approval.netrecmoney']}"/>
			</td>
		</tr>
		<tr class="tr-even">
			<td class="td-content-title">法务处理说明：</td>
			<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3"><input maxLength="1000" emptyText="请输入法务处理说明" label="法务处理说明" style="width:74%;height: 80px;" id="contract_change_info.beforecontent" name="law_approval.closememo" class="mini-textarea" allowInput="true" required="true"  value="${requestScope['law_approval.closememo']}"/></td>
		</tr>
		</table>
</div>
<script type="text/javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("contract_lawclose_info_form");};
});
</script>
