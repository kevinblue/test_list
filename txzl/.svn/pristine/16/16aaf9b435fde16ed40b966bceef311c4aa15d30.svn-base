  <%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id:'due_diligence_report2',
				renderTo: "table_due_diligence_report2",
				width:globalClientWidth,
				height:globalClientHeight,
				title:'尽调报告管理',
				iconCls:'icon-node',
				multiSelect:false,
				hiddenQueryArea:false,//是否将查询区域折叠起来
				queryButtonColSpan:2,
				queryButtonNewLine:false,
				showPager:true,		
				remoteOper : true,
				entityClassName : "com.tenwa.leasing.entity.proj.DueDiligenceReport",
				xmlFileName:'/eleasing/workflow/proj/proj_common/due_diligence_report.xml',
				tools:[
				       'add', '-', 'edit', '-', 'remove'   
				],
				params:{
					
				},
				columns:[ 
				    {type:'indexcolumn'},
				   	{type:'checkcolumn'},  
				   	{field:'id',header:'id',visible:false},
				   	{field:'tabkey',header:'页签关键字',
				   		formEditorConfig:{
				   			labelWidth:100,
				   			maxLength:120,
						       colspan:3,
						         width:'100%',
						       newLine: true}},
				   	{field:'filename',header:'文件类型名称',
				   		formEditorConfig:{
				   			labelWidth:100,
				   			maxLength:120,
						       colspan:3,
						         width:'100%',
						       newLine: true}},
				   	{field:'dockey',header:'流程关键字',
				   		formEditorConfig:{
				   			labelWidth:100,
				   			maxLength:120,
						       colspan:3,
						         width:'100%',
						       newLine: true}},
				   
					{field:'creator',header:'创建人',
						formEditorConfig:{
					          fieldVisible: false}},
					{field:'createdate',header:'创建时间',dateFormat:"yyyy-MM-dd",
						formEditorConfig:{
							 newLine: true,
							 fieldVisible: false
						   }},
					{field:'modificator',header:'修改人',
							   formEditorConfig:{
							          fieldVisible: false},},
					{field:'modifydate',header:'修改时间',dateFormat:"yyyy-MM-dd",
						formEditorConfig:{
					          fieldVisible: false}},
				   
				]
			});
		});
	});
</script>
<div id="table_due_diligence_report2"></div>