<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<input style="display:none;"	class="mini-textarea" id="id_json_loan_task_str" name="json_loan_task_str" value='${empty json_loan_task_str ? "[]" : json_loan_task_str }'></input>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true' || isViewHistoryTask == true){showTools = false;};
	var contentType = [{id:'银行贷款',text:'银行贷款'},{id:'融资租赁',text:'融资租赁'},{id:'其他负债',text:'其他负债'},{id:'或有负债',text:'或有负债'},{id:'负债分析',text:'负债分析'}];
	tenwa.createTable({
		id: "loan_task",
		renderTo: "id_table_loan_task",
		width: globalClientWidth - 30,
		height: 260,
		lazyLoad: false,
		title: "",
		remoteOper : false,
		allowCellWrap: true,
		showPager: false,
		showToolbar: showTools,
		tools: ['add', '-', 'edit', '-', 'remove'],
		data: JsonUtil.decode('${empty json_loan_task_str ? "[]" : json_loan_task_str }'), 
 		columns: [
			{type:'indexcolumn',width:9},
			{type:'checkcolumn',width:9},
			{field:'id', header:'id', visible:false},
			{field:'debtcontent', header:'内容', visible:true,
					formEditorConfig:{
						type:'combobox',
						data:contentType
					}
				     },
			{field:'debtbank', header:'融资渠道', visible:true,
				     formEditorConfig:{
					     fieldVisible: true
				     }},
			{field:'debtey', header:'融资余额', visible: true,
					 formEditorConfig:{
						 newLine:true,
					            width:"100%",
					            }},
			{field:'debtqx', header:'融资期限',visible: true,
				     formEditorConfig:{
				   }},
			{field:'debtuse', header:'资金用途',
				 	 formEditorConfig:{newLine:true}},
			{field:'debtmemo', header:'备注', visible: true,
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
<div id="id_table_loan_task" style="width:100%;height:100%;"></div>