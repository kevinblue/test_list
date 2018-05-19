<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('<mini:param  name="isView"/>' == 'true'||isViewHistoryTask==true){showTools = false;};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "proj_guaranty_detail",
			renderTo: "id_table_proj_guaranty_detail",
			width: globalClientWidth - 10,
			height: 365,
			lazyLoad: true,
			isClickLoad:false,
			title: "",
			remoteOper : false,
			showPager: false,
			showToolbar: showTools,
			tools: ['add', '-', 'edit', '-', 'remove'],
			data: JsonUtil.decode('<mini:param  name="json_proj_guaranty_detail_str" defaultValue="[]"/>'),
			columns: [
				{type:'indexcolumn'},
				{type:'checkcolumn'},
				{field:'id',header:'id',visible:false},
				{field:'equipname',header:'抵押物名称',
					  formEditorConfig:{
						       newLine:true,
						      required:true,
						     maxLength:120,
						       colspan:3,
						    labelWidth:100,
						         width:'100%'}},
				{field:'guarantorname', header:'抵押人',
					  formEditorConfig:{
					      fieldVisible:false,
					 fillFromFieldName:'guarantor',
					      fillProperty:'name'}},
				{field:'guarantor',header:'抵押人',visible:false,
					   formEditorConfig:{
					            newLine:true,
					               type:'queryinput',
					           required:true,
					          textField:'name',
					         valueField:'value',
					       fieldVisible:true,
					            colspan:3,
					              width:'100%',
					             params:{cust_type:'cust_type.guarantor',checkcust:"and ci.creator_='"+"${sessionScope['login_userid']}"+"'",xmlFileName:'/eleasing/workflow/contract/contract_approval/contract_custinfo.xml'}}},
				{field:'equipinvoice',header:'发票号',
					   formEditorConfig:{
				           required:true,
							    newLine:true,
							  maxLength:120}},
				{field:'guarantyvalue', header:'抵质押物价值',dataType :"currency",align:'right',
					   formEditorConfig:{
					         labelWidth:100,
					           required:true,
					              vtype:'float',
					          maxLength:20}},	
				{field:'equipguaranteetypename',header:'抵质押方式', 
					   formEditorConfig:{
					       fieldVisible:false,
					  fillFromFieldName:'equipguaranteetype',
					       fillProperty:'name'}},
				{field:'equipguaranteetype', header:'抵质押方式', visible:false,
					   formEditorConfig:{
					            newLine:true,
					               type:'combobox',
					           required:true,
					          textField:'name',
					          valueField:'value',
					        fieldVisible:true,
					             colspan:3,
					               width:'100%',
					              params:{dictid:'guarantee_type',xmlFileName:'/eleasing/workflow/contract/contract_approval/contract_dict_data.xml'}}},
				{field:'presentvalue',header:'现值',dataType :"currency",align:'right',
					  	formEditorConfig:{
					             newLine:true,
					               vtype:'float',
					           maxLength:20}},
				{field:'notaryflagname',header:'是否抵质押公证',visible:false,
						formEditorConfig:{
					        fieldVisible:false,
					   fillFromFieldName:'notaryflag',
					        fillProperty:'name'}},
				{field:'notaryflag',header:'是否抵质押公证',
						formEditorConfig:{
					                type:'combobox',
					           textField:'name',
					          valueField:'value',
					        fieldVisible:true,
					                data:[{name :'是',value:'是'},{name :'否',value:'否'}]}},
				
				{field:'recordmech',header:'登记机关',
					    formEditorConfig:{
					             newLine:true,
					           maxLength:120}},
				{field:'purchaselife',header:'购买年限',
						formEditorConfig:{
					              
				               }},
				{field:'cgenote',header:'备注',
				   		formEditorConfig:{
					             newLine:true,
					                type:'textarea',
					             colspan:3,
					               width:'100%',
					              height:70,
					           maxLength:120
					}}
				]
	});});
});
</script>
<div id="id_table_proj_guaranty_detail"></div>
