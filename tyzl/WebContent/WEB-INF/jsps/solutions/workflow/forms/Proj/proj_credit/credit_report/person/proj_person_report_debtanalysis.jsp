<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('<mini:param  name="isView"/>' == 'true'||isViewHistoryTask==true||reportShowFlag==false){showTools = false;};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "proj_person_debtanalysis",
			renderTo: "id_table_proj_person_debtanalysis",
			width: globalClientWidth - 20,
			height: 360,
			lazyLoad: false,
			title: "债务调查",
			isClickLoad:false,
			remoteOper : false,
			showPager: false,
			multiSelect: false,
			showToolbar: showTools,
			tools: ['add', '-', 'edit', '-', 'remove'],
			data: JsonUtil.decode('<mini:param  name="proj_report_person_debtanalysis_str" defaultValue="[]"/>'),
			columns: [
				{type:'indexcolumn'},
				{type:'checkcolumn'},
				{field:'id', header:'id',visible: false},
				{field:'type',header:'类型',formEditorConfig:{required: true,labelWidth:100,maxLength:120}},
				{field:'debtmoney',header:'贷款金额',formEditorConfig:{required: true,labelWidth:100,maxLength:120,vtype:'double'}},
				{field:'debtdate',header:'贷款日期',formEditorConfig:{type: 'date',required: true,newLine: true,format: 'yyyy-MM-dd'}},
				{field:'debtrange',header:'贷款期限',formEditorConfig:{required: true,vtype:'int'}},
				{field:'debtdayout',header:'每日支出',formEditorConfig:{newLine: true,vtype:'double',colspan:3}},
				{field:'remark',header:'备注',formEditorConfig:{newLine:true,colspan:3,height:70,width:516,type:'textarea'}}
			]	
		});
	});
});
</script>

<div id="id_table_proj_person_debtanalysis"></div>
