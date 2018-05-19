<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<input style="display:none;"	class="mini-textarea" id="id_json_leasing_info_list_str" name="json_leasing_info_list_str" value='${empty json_leasing_info_list_str ? "[]" : json_leasing_info_list_str }'></input>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true' || isViewHistoryTask == true){showTools = false;};
	tenwa.createTable({
		id: "power_generation_statistics_1",
		renderTo: "id_table_leasing_info_list_1",
		width: globalClientWidth - 30,
		height: 365,
		lazyLoad: false,
		title: "",
		allowCellWrap: true,
		remoteOper : true,
		showPager: true,
		
		showToolbar: showTools,
		/* tools: ['add', '-', 'edit', '-', 'remove'],  */
		params:{
			contractidselect:contractidselect,
			thisyear:thisyear,
			lastyear:lastyear
			
		},
		xmlFileName:'/eleasing/workflow/contract/contract_finish/power_generation_statistics_lastyear.xml',
		
		columns: [
			{type:'indexcolumn'},
			{field:'id', header:'id', visible:false},
		{field:'reportdate2', header:'时间', visible: true,
			     formEditorConfig:{
			    	 labelWidth:110,
			    	 required:true,
				     fieldVisible: true}
		},
		{field:'temperature2', header:'发电量（度）',dataType:"currency",summary:true,formEditorConfig:{
			newLine:true,required:true,
			labelWidth:110,
	    	 required:true,
		     fieldVisible: true}
		},
		{field:'presentvalue2', header:'发电小时数',dataType:"currency",summary:true,formEditorConfig:{
			newLine:true,required:true,
			labelWidth:110,
	    	 required:true,
		     fieldVisible: true}
		},
		{field:'billreceivable2', header:'应收电费 (元)',dataType:"currency",summary:true,formEditorConfig:{
			newLine:true,required:true,
			labelWidth:110,
	    	 required:true,
		     fieldVisible: true}
		},
		{field:'guarantyvalue2', header:'实收电费 (元)',dataType:"currency",summary:true,formEditorConfig:{
			newLine:true,required:true,
			labelWidth:110,
	    	 required:true,
		     fieldVisible: true}
		},
		{field:'powerrationing2', header:'限电比例',formEditorConfig:{
			newLine:true,required:true,
			labelWidth:110,
	    	 required:true,
		     fieldVisible: true}
		}
		]
	});
});
</script>
<div id="id_table_leasing_info_list_1" style="width:100%;height:100%;"></div>