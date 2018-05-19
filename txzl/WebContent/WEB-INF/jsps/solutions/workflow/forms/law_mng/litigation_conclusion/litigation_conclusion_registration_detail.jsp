<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
var localEnabled = [{id : '否',text : '否'}, {id : '是',text : '是'}];
jQuery(function(){

	 var showTools = true;
	if ('${param.isView}' == 'true') {
		showTools = false;
	}  
	tenwa.createTable({
		id: 'csut_apply_list',
		renderTo: 'id_table_csut_apply_list',
		width:globalClientWidth,
		height: 600,
		lazyLoad: false,
		showPage: true,
		showToolbar: showTools,
		hiddenQueryArea : true,
		pageSize: 20,
		showPager : true,//分页按钮是否显示
		remoteOper :true,
		entityClassName :"com.tenwa.leasing.entity.litigation.LitigationDetail",
		tools: ['add', '-', 'edit', '-', 'remove'],
		xmlFileName : '/eleasing/jsp/law_mng/litigation_conclusion.xml',
		
	 	afterShowWindowCallBack:function(miniTable,miniForm, operType){
			  var lawApprovalid ='${param["lawApprovalid"]}'||'${requestScope["lawApproval"]}';
		   	  mini.getbyName("lawApproval").setValue(lawApprovalid);
		}, 
		params:{
			lawApproval : "${requestScope['lawApproval']}"
		},
		columns: [
			{type: 'checkcolumn'},
			{field: 'id', header: '主键', visible: false},
			{field: 'conclusiontypename', header: '结论类型', visible: true,
	            	  formEditorConfig:{
						   		 fieldVisible: false,
						   		 fillFromFieldName : 'conclusiontype',
								 fillProperty : 'name'
					}},
			{field : 'conclusiontype',header : '结论类型',visible: false,queryConfig : {},
			   		  formEditorConfig : {
					   			 type : 'combobox',
								 textField: 'name',
								 newLine:true,
								 required: true,
								 valueField: 'value',
								 fieldVisible: true,
								 params:{pid: 'conclusion',xmlFileName: '/combos/comboDict.xml'},
				                 textField: 'name',
				                 valueField: 'value'
			   		}},
			{field : 'decisiondate',header : '判决/调解日期', dateFormat:'yyyy-MM-dd',
			          formEditorConfig:{
				                 newline:true,
			   		             type:'date',
			   		             required:true,
			   		             width:200,
			   		             labelWidth:100
			              }
			   		},
			 {field : 'appeal',header : '是否上诉',
			   		  formEditorConfig:{
				   				 newLine:true,
				   				 data:localEnabled,
				   				 type:'combobox',
				   				 required:true
			   				}},
			 {field: 'lawApproval', header: 'lawApproval', visible: false,
						formEditorConfig:{fieldVisible: false}
							}
		]
	});
});
</script>
<div id="id_table_csut_apply_list" style="height: 100%;"></div>
