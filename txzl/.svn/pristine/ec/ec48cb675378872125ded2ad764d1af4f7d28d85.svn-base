<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>合同税差发票维护</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>

<script type="text/javascript">
//状态下拉查询
jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id : 'table_id1',
				width : globalClientWidth,
				height : globalClientHeight,
				title : '合同税差发票维护',
				iconCls : 'icon-node',
				multiSelect : false,
				//hiddenQueryArea : false,//是否将查询区域折叠起来
				queryButtonColSpan : 6,
				queryButtonNewLine:true,
				remoteOper:true,
				showPager:true,
				params:{},
				isRemoteStatistic: true,
				entityClassName : 'com.tenwa.leasing.entity.contract.ContractInvoiceType',
				xmlFileName : '/eleasing/jsp/invoice_manage/rent_invoice/contract_tax_diff_type.xml',
				editRemoteOperUrl: getRootPath()+"/acl/updateContractTaxDiff.acl",
				tools : ['edit'],
				columns : [ 
				    {type : 'indexcolumn'},
				   	{type : 'checkcolumn'},  
				   	{field : 'id',header : 'id',visible : false},
				   	{field : 'contractid',header : '合同号',queryConfig : {},formEditorConfig : {fieldVisible:false}},
				   	{field : 'contractnumber',header : '业务合同号',queryConfig : {},formEditorConfig : {fieldVisible:false},renderer:function(e){
						return "<a href='javascript:void(0);' onclick='showContractDetail(\""+e.record.cid+"\")'>" + e.record.contractnumber + "</a>";
					}},
				   	{field : 'projectname',header : '项目名称',queryConfig : {},formEditorConfig : {fieldVisible:false}},
				   	{field : 'custname',header : '客户名称',allowSort:true,queryConfig : {newLine:true},formEditorConfig : {fieldVisible:false}},
				   	{field : 'taxbank',header : '开户行',formEditorConfig : {fieldVisible:false}},
				   	{field : 'taxacc',header : '开户账号',formEditorConfig : {fieldVisible:false}},
				   	{field : 'taxdifftype',header : '合同税差发票开具方式',
					   	queryConfig : {
					   	type: 'combobox',
						valueField : 'text',
						textField : 'text',
						colspan : 1,
						data : [{text:'否'},{text:'是'}]
					   	},
					   	formEditorConfig : {
					   	labelWidth:180,
					   	type: 'combobox',
						valueField : 'text',
						textField : 'text',
						data : [{text:'否'},{text:'是'}] 	
					}},
				   	{field: 'leaseform', header:'租赁形式',
						queryConfig:{
							type:'combobox',
							params : {
								pid:'leas_form',
								xmlFileName : 'combos/comboDict.xml'
							},
							readOnly:false,
							textField:'name',
							valueField:'name'

					}}
				]
			});
		});
	});
jQuery(function(){
});
</script>
</head>
<body></body>
</html>
