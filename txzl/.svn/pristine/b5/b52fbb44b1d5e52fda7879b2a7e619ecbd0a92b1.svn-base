<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<input style="display:none;"	class="mini-textarea" id="id_json_electric_info_list_str" name="json_electric_info_list_str" value='${empty json_electric_info_list_str ? "[]" : json_electric_info_list_str }'></input>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true' || isViewHistoryTask == true){showTools = false;};
	tenwa.createTable({
		id: "electric_info_list",
		renderTo: "id_table_electric_info_list",
		width: globalClientWidth - 30,
		height: 365,
		lazyLoad: false,
		title: "",
		allowCellWrap: true,
		remoteOper : false,
		showPager: false,
		showToolbar: showTools,
		/* tools: ['add', '-', 'edit', '-', 'remove'], */
		params:{
			thissend1:thissend1,
			quarter:quarter,
			contractidselect:contractidselect
		},
		xmlFileName:'/eleasing/workflow/contract/contract_finish/power_generation_statistics_year.xml',
		columns: [
            {type:'indexcolumn'},
			{field:'id', header:'id', visible:false},
			{field:'reportdate', header:'时间', visible: true
			},
			{field:'temperature', header:'发电量（万度）',dataType:"currency",summary:true,formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: true}
			},
			{field:'electricdate', header:'折合标准发电小时数',dataType:"currency",summary:true,formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: true}
			},
			{field:'billreceivable', header:'应收电费 (万元)',dataType:"currency",summary:true,formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: true}
			},
			{field:'electric_money_real', header:'实收电费 (万元)',dataType:"currency",summary:true,formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: true}
			},
			{field:'powerrationing', header:'限电比例',formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: true}
			}
			
		]
	});
});
</script>
<div id="id_table_electric_info_list" style="width:100%;height:100%;"></div>