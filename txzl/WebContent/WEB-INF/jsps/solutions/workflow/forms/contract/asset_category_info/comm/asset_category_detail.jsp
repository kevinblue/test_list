<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if ('${param.isView}' == 'true') {
		showTools = false;
	}
	tenwa.createTable({
		id: 'asset_category_list',
		renderTo: 'id_table_asset_category_list',
		width: globalClientWidth,
		height: 400,
		showPager: true,
		lazyLoad : false,
		remoteOper : false,
		queryButtonColSpan : 2,
		editFormPopupWindowHeight:500,
		showToolbar: showTools,
		hiddenQueryArea : true,
		pageSize: 20,
		xmlFileName:"eleasing/jsp/asset_category/asset_category_list.xml",
		params:{applyidx:"${requestScope['fiveids']}"},
		columns: [
			//{type: 'checkcolumn'},
			{field: 'id',visible: false,formEditorConfig: {fieldVisible:false}},
			{field: 'contractid',visible: false,formEditorConfig: {fieldVisible:false}},
			{field: 'custid',visible: false,formEditorConfig: {fieldVisible:false}},
		 	{field: 'contractno', header: '合同编号',headerAlign:'center', visible: true,
			  			  queryConfig:{  
                           labelWidth:100,
	   			            width:200},
				     formEditorConfig:{
				    	newLine:true,
		                width: 200,
		                 type:'queryinput',
		             required: true,
		            textField: 'contractid',
		           valueField: 'contractno',
		           allowInput: false,
		         fieldVisible: true,
		             onSelect:function($me, queryInput, rowData){
		            		
	                    },
	                    params : {
							xmlFileName : '/eleasing/jsp/other/docfive_category_contractId.xml',
							userid:'${sessionScope.loginUser.id}'
						}
					 }},
			
			{field: 'contractnumber', header: '业务合同编号',headerAlign:'center',visible: true,
				formEditorConfig:{fieldVisible: true,readOnly:true}},
			{field: 'cardno',header : '身份证/组织机构代码',visible:true,formEditorConfig: {readOnly:true,newLine:true}},
			{field : 'projectname',header : '项目名称',formEditorConfig:{readOnly:true}}, 
			{field : 'projdept',header : '部门名称',formEditorConfig:{newLine:true,readOnly:true}},
			{field : 'insideindustryname',header : '内部行业',formEditorConfig:{readOnly:true}}, 
			{field : 'custname',header : '客户名称',formEditorConfig:{newLine:true,readOnly:true}},
			{field : 'leaseform',header : '租赁形式',formEditorConfig:{readOnly:true}},
			{field : 'provincename',header : '省份',formEditorConfig:{newLine:true,readOnly:true}},
			{field : 'projmanage',header : '项目经理',formEditorConfig:{readOnly:true}},
			{field : 'startdate',header : '起租日',formEditorConfig:{newLine:true,readOnly:true}},
			{field : 'incomenumber',header : '还租频次',formEditorConfig:{readOnly:true}},
			{field : 'currentoverage',header : '未偿租金金额',formEditorConfig:{newLine:true,readOnly:true}},
			{field : 'curinterestoverage',header : '未偿本金金额',formEditorConfig:{readOnly:true}},
			{field : 'firstrent',header : '每期租金金额',formEditorConfig:{newLine:true,readOnly:true}},
			{field : 'cautionmoney',header : '保证金金额',formEditorConfig:{readOnly:true}},
			{field : 'out_list',header : '逾期次数',formEditorConfig:{newLine:true,readOnly:true}},
			{field : 'out_date',header : '逾期天数',formEditorConfig:{readOnly:true}},
			{field : 'latecause',header : '逾期原因',visible:true,
				formEditorConfig:{
					newLine:true,
					type:'textarea',
					colspan:3,
					width:'100%',
					fieldVisible: true
				}},
			{field : 'importantmatters',header : '重大事项',visible:true,
				formEditorConfig:{
					type:'textarea',
					newLine:true,
					colspan:3,
					width:'100%',
					fieldVisible: true
				}},
			{field : 'custvisit',header : '客户巡视',visible:true,
					formEditorConfig:{
						type:'textarea',
						newLine:true,
						colspan:3,
						width:'100%',
						fieldVisible: true
					}},
			{field : 'accidentlitigationsituation',header : '出险及涉诉情况',formEditorConfig:{newLine:true}},
			{field : 'lastclassificationresult',header : '前次资产分类结果',visible:true,
				formEditorConfig:{
					readOnly:true
				}},
			{field: 'classificationresultname', header: '本次分类结果', visible: true,
	            	  formEditorConfig:{
				   		fieldVisible: false,
				   		fillFromFieldName : 'classificationresult',
						fillProperty : 'name'
					}},
			{field : 'classificationresult',header : '本次分类结果',visible: false,queryConfig : {},
			   		formEditorConfig : {
			   			type : 'combobox',
						textField: 'name',
						newLine:true,
						required: true,
						valueField: 'value',
						fieldVisible: true,
						params:{pid: 'five_class',xmlFileName: '/combos/comboDict.xml'},
		                 textField: 'name',
		                valueField: 'value'
			   		}},
			   		
			{field : 'adjustmentreason',header : '调整原因',visible:true,
					formEditorConfig:{
						type:'textarea',
						colspan:3,
						width:'100%',
						fieldVisible: true,
						newLine:true
					}}
		]
	});
});
</script>
<div id="id_table_asset_category_list" style="height: 100%;"></div>
