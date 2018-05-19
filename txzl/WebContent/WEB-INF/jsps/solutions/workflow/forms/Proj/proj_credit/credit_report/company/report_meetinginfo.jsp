<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true||reportShowFlag==false){showTools = false;};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "meeting_info",
			renderTo: "id_table_meeting_info",
			width: globalClientWidth - 20,
			height: 200,
			lazyLoad: false,
			title: "照会单内容(交易确认/品质/营运/外部信息)",
			isClickLoad:false,
			remoteOper : false,
			showPager: false,
			multiSelect: false,
			showToolbar: showTools,
			tools: ['add', '-', 'edit', '-', 'remove'],
			data: JsonUtil.decode('${empty proj_report_meeting_info ? "[]" : proj_report_meeting_info }'),
			columns: [
				{type:'indexcolumn'},
				{type:'checkcolumn'},
				{field:'id', header:'id', visible:false},
				{field:'meetingcomid', header:'照会厂商统编',formEditorConfig:{labelWidth:100}},
				{field:'meetingcomname', header:'照会厂商名称',formEditorConfig:{labelWidth:100,required:true}},
				{field:'meetingperson', header:'照会人员及职称',formEditorConfig:{newLine :true}},
				{field:'meetingdate', header:'照会日期',formEditorConfig:{type:'date'}},
				{field:'meetingtype', header:'照会类别',formEditorConfig:{colspan:3,newLine :true}},
				{field:'meetingproduct', header:'厂商产品',formEditorConfig:{newLine:true,colspan:3,type:'textarea', width:400,heigth:150}},
				{field:'custidustryid', header:'客户行业编号',formEditorConfig:{newLine :true}},
				{field:'meetingphone', header:'照会电话',formEditorConfig:{}},
				{field:'meetingacontent', header:'照会内容',formEditorConfig:{newLine :true,type:'textarea',colspan:3,width:400,heigth:150}}
			]	
		});
	});
});

</script>

<div id="id_table_meeting_info"></div>


