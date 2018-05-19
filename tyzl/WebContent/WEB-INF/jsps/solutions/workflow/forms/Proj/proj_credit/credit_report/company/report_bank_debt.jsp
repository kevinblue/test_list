<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('<mini:param  name="isView"/>' == 'true'||isViewHistoryTask==true||reportShowFlag==false){showTools = false;};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "report_bank_debt",
			renderTo: "id_table_report_bank_debt",
			width: globalClientWidth - 20,
			height: 200,
			lazyLoad: false,
			title: "金融机构往来—银行借款",
			isClickLoad:false,
			remoteOper : false,
			showPager: false,
			multiSelect: false,
			showToolbar: showTools,
			tools: ['add', '-', 'edit', '-', 'remove'],
			data: JsonUtil.decode('<mini:param  name="proj_report_bank_debt" defaultValue="[]"/>'),
			columns: [
				{type: 'indexcolumn'},
				{type:'checkcolumn'},
				{field:'id', header:'id', visible:false},
				{field:'method', header:'期间',formEditorConfig:{required:true}},
				{field:'realdebt', header:'实际借款',formEditorConfig:{}},
				{field:'remaindebt', header:'余额',formEditorConfig:{newLine :true}},
				{field:'returntype', header:'还款方式',formEditorConfig:{}},
				{field:'guarantee', header:'担保及保证',formEditorConfig:{type:'textarea',height:70,width:477,newLine :true,colspan:3}}
			]	
		});
	});
});

</script>

<div id="id_table_report_bank_debt"></div>


