<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<input style="display:none;"	class="mini-textarea" id="id_json_invest_info_list_str" name="json_invest_info_list_str" value='${empty json_invest_info_list_str ? "[]" : json_invest_info_list_str }'></input>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true' || isViewHistoryTask == true){showTools = false;};
	var localvar = [
	                {id : '',text : ''}, 
		{id : '设备投资',text : '设备投资'}, 
		{id : '基建投资',text : '基建投资'},
		{id : '其他投资',text : '其他投资'}
	];
	tenwa.createTable({
		id: "invest_info_list",
		renderTo: "id_table_invest_info_list",
		width: globalClientWidth - 30,
		height: 200,
		lazyLoad: true,
		title: "",
		allowCellWrap: true,
		remoteOper : false,
		showPager: false,
		showToolbar: showTools,
		tools: ['add', '-', 'edit', '-', 'remove'],
		data: JsonUtil.decode('${empty json_invest_info_list_str ? "[]" : json_invest_info_list_str }'),
		columns: [
			{type:'indexcolumn',width:9},
			{type:'checkcolumn',width:9},
			{field:'id', header:'id', visible:false},
			{field:'content', header:'内容', visible: true,
				     formEditorConfig:{
				    	 labelWidth:110,
				    	 readOnly : false,
						 data : localvar,
						 type : 'combobox',
				    	 required:true,
					     fieldVisible: true}
			},
			{field:'project', header:'项目', visible: true,
				     formEditorConfig:{
				    	 labelWidth:110,
				    	 required:true,
					     fieldVisible: true}
			},
			{field:'tzje', header:'投资金额', visible: true,
				     formEditorConfig:{
				    	 newLine:true,
				    	 labelWidth:110,
				    	 required:true,
					     fieldVisible: true}
			},
			{field:'zjap', header:'资金安排', visible: true,
				     formEditorConfig:{
				    	 labelWidth:110,
				    	 required:true,
					     fieldVisible: true}
			},
			{field:'process', header:'目前进展状况',formEditorConfig:{
				newLine:true,required:true,
				width:'100%',height:'100px',
				colspan:3,type:'textarea'}
			}
		]
	});
});
</script>
<div id="id_table_invest_info_list" style="width:100%;height:100%;"></div>