<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true||reportShowFlag==false){showTools = false;};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "guarantor_backinfo",
			renderTo: "id_table_guarantor_backinfo",
			width: globalClientWidth - 20,
			height: 200,
			lazyLoad: false,
			title: "保证人资力背景",
			isClickLoad:false,
			remoteOper : false,
			showPager: false,
			multiSelect: false,
			showToolbar: showTools,
			tools: ['add', '-', 'edit', '-', 'remove' ],
			data: JsonUtil.decode('${empty proj_report_guarantor_backinfo ? "[]" : proj_report_guarantor_backinfo }'),
			columns: [
				{type:'indexcolumn'},
				{type:'checkcolumn'},
				{field:'id', header:'id', visible:false},
				{field:'name', header:'姓名/公司名称',formEditorConfig:{labelWidth:145,required:true}},
				{field:'cardno', header:'身份证号/组织机构代码 ',formEditorConfig:{labelWidth:145}},
				{field:'founddate', header:'成立日期',formEditorConfig:{type:'date',vtype:'date',format:'yyyy-MM-dd',newLine :true}},
				{field:'insuremoney', header:'保证额度人民币',formEditorConfig:{}},
				{field:'addr', header:'地址',formEditorConfig:{newLine :true,colspan:3,type:'textarea',width:520}},
				{field:'marriage', header:'婚姻 ',formEditorConfig:{newLine :true}},
				{field:'connectwithresponse', header:'与负责人关系 ',formEditorConfig:{}},
				{field:'estate', header:'名下不动产',formEditorConfig:{newLine :true}},
				{field:'insuremoneyradio', header:'本次保证比率(%)',formEditorConfig:{vtype:"float"}}
			]	
		});
	});
});

</script>

<div id="id_table_guarantor_backinfo"></div>


