<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
var overdueProjectData;
//出险项目情况表指示
jQuery(function() {
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id:'table_id4',
			width:globalClientWidth,
			height:screenHeight - topHeight,
			iconCls:'icon-node',
			hiddenQueryArea:true,
			frozenStartColumn:1,
			frozenEndColumn:3,
			renderTo:'content_table_id4',
			editFormPopupWindowWidth:700,
			remoteOper:true,
			entityClassName:'com.tenwa.leasing.entity.fund.overdue.OverdueProject',
			pageSize:10,
			showPager:true,
			lazyLoad:false,
			afterShowWindowCallBack: function(miniTable,miniForm,operFlag){
				if("add" == operFlag){
					if(overdueProjectData){
						miniForm.setData(overdueProjectData);
					}else{
						$.ajax({
					        url: urlPrefix+"/eleasing/jsp/fund/fund_overdue/get_contract_list_byid.xml",
					        type: "post",
					        cache: false, 
					        async:false,
					        data :{"contractid":contractid},
					        success: function (text) {		
					            var result = mini.decode(text);
					            var data = result.datas[0];
					            var formData = miniForm.getData();
					            $.extend(formData,mini.decode(data));
					            miniForm.setData(formData);
					            overdueProjectData = formData;
					        }
					    });
					}
				}
			},
			xmlFileName:'/eleasing/jsp/fund/fund_overdue/overdue_project.xml',
			params:{contractid :contractid},
			tools:[ 'add', '-', 'edit', '-', 'remove'],
			columns:[ 
				{type:'indexcolumn'},
			    {type:'checkcolumn'} , 
			    {field:'id',header:'记录编号',headerAlign:'center',width:100,allowSort:true,visible:false,
				           formEditorConfig:{
					               readOnly:true,
					           fieldVisible:false}},
			    {field:'contractid',header:'合同主键ID',headerAlign:'center',width:100,allowSort:true,visible:false,
				           formEditorConfig:{
					               readOnly:true,
					           fieldVisible:false}},
			    {field:'projectname',header:'项目',headerAlign:'center',width:100,allowSort:true,
				           formEditorConfig:{
					               readOnly:true,
					                   type:'text',
					           fieldVisible:true,
					                  width:200,
					             labelWidth:100}},
				{field:'contractid1',header:'合同号',headerAlign:'center',width:100,allowSort:true,
				           formEditorConfig:{
					               readOnly:true,
					                   type:'text',
					           fieldVisible:true,
				 	                  width:200,
					             labelWidth:100}},
			    {field:'contractnumber',header:'业务合同号',headerAlign:'center',width:100,allowSort:true,
				           formEditorConfig:{
					                newLine:true,
					               readOnly:true,
					                   type:'text',
					           fieldVisible:true,
					                  width:200,
					             labelWidth:100}},
				{field:'custname',header:'承租人',headerAlign:'center',width:100,allowSort:true,
				           formEditorConfig:{
					               readOnly:true,
					                   type:'text',
					           fieldVisible:true,
					                  width:200,
                                 labelWidth:100}},
                 {field:'cleanleasemoney',header:'起租金额',headerAlign:'center',width:100,allowSort:true,
				           formEditorConfig:{
					                newLine:true,
					               readOnly:true,
					                   type:'text',
					           fieldVisible:true,
					                  width:200,
					             labelWidth:100}},
				 {field:'corpusoverage',header:'本金余额',headerAlign:'center',width:100,allowSort:true,
				           formEditorConfig:{
					                   type:'text',
					           fieldVisible:true,
				 	                  width:200,
					             labelWidth:100}},
			    {field: 'dangerdate',header:'出险时间',	headerAlign:'center', width:100,allowSort:true,dateFormat:"yyyy-MM-dd",
				           formEditorConfig:{	
					                newLine:true,
					                   type:'date',  
					             labelWidth:100,
					                  width:200,								
					             allowInput:"false"}},	
			    {field:'department',header:'出单部门',headerAlign:'center',width:100,allowSort:true,
				           formEditorConfig:{
					               readOnly:true,
					                   type:'text',
					           fieldVisible:true,
					                  width:200,
					             labelWidth:100}},
				 {field:'realname',header:'租后经办',headerAlign:'center',width:100,allowSort:true,
				           formEditorConfig:{
					                newLine:true,
					               readOnly:true,
					                   type:'text',
					           fieldVisible:true,
					                  width:200,
					             labelWidth:100}},
			     {field: 'rawValue_processingstate', header: '处理状态', 
		                   formEditorConfig:{
					           fieldVisible:false,
					      fillFromFieldName:'processingstate',
					           fillProperty:'name'}},
			     {field:'processingstate',header:'处理状态',visible:false,headerAlign:'center',width:100,allowSort:true,
                           formEditorConfig:{
					                  width:200,
					           fieldVisible:true,
					           showNullItem:"true", 
					           nullItemText:"",
					              emptyText:"",
					                   type:'combobox',
					                 params: {pid: 'handle_status',xmlFileName: '/combos/comboDict.xml'},
					              textField: 'name',
					             valueField: 'value'}
			}]
		});
	});
});
</script>
<div id='content_table_id4'></div>