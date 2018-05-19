<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<input style="display:none;"	class="mini-textarea" id="id_json_channel_type_task_str" name="json_channel_type_task_str" value='${empty json_channel_type_task_str ? "[]" : json_channel_type_task_str }'></input>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true' || isViewHistoryTask == true){showTools = false;};
	tenwa.createTable({
		id: "channel_type_task",
		renderTo: "id_table_channel_type_task",
		width: globalClientWidth - 30,
		height: 260,
		lazyLoad: false,
		title: "",
		remoteOper : false,
		allowCellWrap: true,
		showPager: false,
		showToolbar: showTools,
		tools: ['add', '-', 'edit', '-', 'remove'],
		data: JsonUtil.decode('${empty json_channel_type_task_str ? "[]" : json_channel_type_task_str }'), 
 		columns: [
			{type:'indexcolumn',width:9},
			{type:'checkcolumn',width:9},
			{field:'id', header:'id', visible:false},
			{field:'channelcontnet', header:'内容', visible:true,
			     formEditorConfig:{
			    	 readOnly:true,
			    	 value:'其他主要负债',
				     fieldVisible: true}},
			{field:'channeltype', header:'负债类型', visible:true,
				     formEditorConfig:{}},
			{field:'channelye', header:'负债余额', visible: true,
					 formEditorConfig:{newLine:true}},
			{field:'channelmemo', header:'情况说明/备注',visible: true,
				     formEditorConfig:{
				    	 newLine:true,
				    	 colspan:3,
				    	 type:'textarea',
				    	 width:'100%'}}
		]
	});
});
</script>
<div id="id_table_channel_type_task" style="width:100%;height:100%;"></div>