<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true' || isViewHistoryTask == true){showTools = false;};
	tenwa.createTable({
		id: "contract_guarantee_method",
		renderTo: "id_table_guarantee_method",
		width: globalClientWidth - 30,
		height: 365,
		lazyLoad: false,
		title: "",
		remoteOper : false,
		showPager: false,
		showToolbar: showTools,
		tools: ['add', '-', 'edit', '-', 'remove'],
		data: JsonUtil.decode('${empty json_contract_guarantee_method_str ? "[]" : json_contract_guarantee_method_str }'),
		columns: [
			{type:'indexcolumn'},
			{type:'checkcolumn'},
			{field:'id', header:'id', visible:false},
			{field:'assurorname', header:'担保单位', visible:true,
				     formEditorConfig:{
					     fieldVisible:false}},
			{field:'assuror', header:'担保单位', visible:false,
					 formEditorConfig:{
					          newLine:true,
					            width:"100%",
					             type:'queryinput',
					         required:true,
					        textField:'name',
					       valueField:'value',
					       allowInput:false,
					     fieldVisible:true,
					            width:'100%',
					          colspan:3,
					           params:{cust_type:'cust_type.assuror',xmlFileName:'/eleasing/workflow/contract/contract_approval/contract_custinfo.xml'}}},
			{field:'assurorcustclass', header:'担保单位客户类型',visible:false,
				     formEditorConfig:{
				         fieldVisible:false,
				    fillFromFieldName:'assuror',
				         fillProperty:'custclass'}},
			{field:'assuremethodname', header:'担保类型',
				 	 formEditorConfig:{
				         fieldVisible:false,
				    fillFromFieldName:'assuremethod',
				         fillProperty:'name'}},
			{field:'assuremethod', header:'担保类型', visible:false,
				 	 formEditorConfig:{
				                width:"100%",
				              newLine:true,
				                 type:'combobox',
				             required:true,
				            textField:'name',
				           valueField:'value',
				           allowInput:false,
				         fieldVisible:true,
				               params:{pid:'assure_method',xmlFileName:'combos/comboDict.xml'}}},
			{field:'assurerelationname', header:'关系', 
				     formEditorConfig:{
				         fieldVisible:false,
				    fillFromFieldName:'assurerelation',
				         fillProperty:'name'}},
			{field:'assurerelation', header:'关系', visible:false,
				 	 formEditorConfig:{
				              newLine:false,
				                width:"100%",
				                 type:'combobox',
				             required:true,
				            textField:'name',
				           valueField:'value',
				           allowInput:false,
				          fieldVisible:true,
				            labelWidth:100,
				                params:{pid:'relation',xmlFileName:'combos/comboDict.xml'}}},
			{field:'cgmnote', header:'备注',formEditorConfig:{newLine:true,width:'100%',colspan:3,type:'textarea'}}
		]
	});
});
</script>
<div id="id_table_guarantee_method" style="width:100%;height:100%;"></div>