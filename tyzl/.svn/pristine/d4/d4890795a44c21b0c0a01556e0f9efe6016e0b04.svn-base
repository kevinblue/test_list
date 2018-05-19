<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('<mini:param name="isView"/>' == 'true'||isViewHistoryTask==true||reportShowFlag==false){showTools = false;};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "proj_bank_credit_info",
			renderTo: "id_table_proj_ebank_credit_info",
			width: globalClientWidth - 20,
			height: 200,
			lazyLoad: false,
			title: "银行资信情况",
			isClickLoad:false,
			remoteOper : false,
			showPager: false,
			multiSelect: false,
			showToolbar: showTools,
			tools: ['add', '-', 'edit', '-', 'remove'],
			data: JsonUtil.decode('<mini:param  name="proj_report_bank_credit_info_str" defaultValue="[]"/>'),
	    	columns: [
				{type:'indexcolumn'},
				{type:'checkcolumn'},
				{field:'id', header:'id', visible:false},
				{field:'debtbank', header:'贷款银行',formEditorConfig:{required:true,labelWidth:100,maxLength:120}},
				{field:'debtoveragemoney', header:'贷款余额',formEditorConfig:{required:true,labelWidth:100,maxLength:120}},
				{field:'debtoverduemoney', header:'逾期金额',formEditorConfig:{newLine:true}},
				{field:'debtacceptmoney', header:'承兑金额',formEditorConfig:{}},
				{field:'assureremainmoney', header:'担保余额',formEditorConfig:{newLine:true,colspan:3}},
				{field:'debtinfo', header:'涉诉情况',formEditorConfig:{newLine:true,colspan:3, type:'textarea', width:400,height:150}}
			]	
		});
	});
});
</script>
<div id="id_table_proj_ebank_credit_info"></div>