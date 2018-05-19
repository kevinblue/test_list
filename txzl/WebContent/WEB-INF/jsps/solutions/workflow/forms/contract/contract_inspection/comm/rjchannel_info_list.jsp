<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<input style="display:none;"	class="mini-textarea" id="id_json_channel_task_str" name="json_channel_task_str" value='${empty json_channel_task_str ? "[]" : json_channel_task_str }'></input>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true' || isViewHistoryTask == true){showTools = false;};
	tenwa.createTable({
		id: "channel_task",
		renderTo: "id_table_channel_task",
		width: globalClientWidth - 30,
		height: 260,
		lazyLoad: false,
		title: "",
		remoteOper : false,
		allowCellWrap: true,
		showPager: false,
		showToolbar: showTools,
		tools: ['add', '-', 'edit', '-', 'remove'],
		data: JsonUtil.decode('${empty json_channel_task_str ? "[]" : json_channel_task_str }'), 
 		columns: [
			{type:'indexcolumn',width:9},
			{type:'checkcolumn',width:9},
			{field:'id', header:'id', visible:false},
			{field:'rzcontent', header:'内容', visible:true,
			     formEditorConfig:{
			    	 readOnly:true,
			    	 value:'租赁融资',
				     fieldVisible: true}},
			{field:'rzqd', header:'融资渠道', visible:true,
				     formEditorConfig:{}},
			{field:'rzye', header:'融资余额', visible: true,
					 formEditorConfig:{newLine:true}},
			{field:'rzqx', header:'融资期限',visible: true,
				     formEditorConfig:{}},
			{field:'rzuse', header:'资金用途',
				 	 formEditorConfig:{newLine:true}},
			{field:'rzmemo', header:'备注', visible: true,
				 	 formEditorConfig:{
				 		 width:"100%",
			             newLine:true,
			             colspan:3,
			             type:'textarea'
				              }}
		]
	});
});
</script>
<div id="id_table_channel_task" style="width:100%;height:100%;"></div>