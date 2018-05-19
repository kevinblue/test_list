<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<input style="display:none;"	class="mini-textarea" id="id_json_proj_cust_list_str" name="json_proj_cust_list_str" value='${empty json_proj_cust_list_str ? "[]" : json_proj_cust_list_str }'></input>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true' || isViewHistoryTask == true){showTools = false;};
	tenwa.createTable({
		id: "proj_kehu",
		renderTo: "id_table_proj_cust",
		width: globalClientWidth - 30,
		height: 365,
		lazyLoad: false,
		title: "",
		remoteOper : false,
		showPager: false,
		showToolbar: showTools,
		tools: ['add', '-', 'edit', '-', 'remove'],
		data: JsonUtil.decode('${empty json_proj_cust_list_str ? "[]" : json_proj_cust_list_str }'),
		columns: [
			{type:'indexcolumn'},
			//{type:'checkcolumn'},
			{field:'id', header:'id', visible:false},
			{field:'contractid', header:'合同号', visible:true,
				     formEditorConfig:{
					     fieldVisible:false}},
			{field:'contractnumber', header:'业务合同编号', visible: true,
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
			{field:'projectname', header:'项目名称',visible: true,
				     formEditorConfig:{
				         fieldVisible:false,
				    fillFromFieldName:'assuror',
				         fillProperty:'custclass'}},
			{field:'equipamt', header:'融资金额',
				 	 formEditorConfig:{
				         fieldVisible:false,
				    fillFromFieldName:'assuremethod',
				         fillProperty:'name'}},
			{field:'balancerent', header:'剩余租金', visible: true,
				 	 formEditorConfig:{
				                width:"100%",
				              newLine:true,
				             required:true,
				            textField:'name',
				           valueField:'value',
				           allowInput:false}},
			{field:'leaseterm', header:'租金总期数', 
				     formEditorConfig:{
				         fieldVisible:false,
				    fillFromFieldName:'assurerelation',
				         fillProperty:'name'}},
			{field:'balancelist', header:'剩余期数 ', visible: true,
				 	 formEditorConfig:{
				              newLine:false,
				                width:"100%",
				             required:true,
				            textField:'name',
				           valueField:'value',
				           allowInput:false,
				          fieldVisible: false,
				            labelWidth:100,
				  }},
			{field:'outlist', header:'逾期期数 ', visible: true,
				 	 formEditorConfig:{
				              newLine:false,
				                width:"100%",
				             required:true,
				            textField:'name',
				           valueField:'value',
				           allowInput:false,
				          fieldVisible: false,
				            labelWidth:100,
				  }}
		]
	});
});
</script>
<div id="id_table_proj_cust" style="width:100%;height:100%;"></div>