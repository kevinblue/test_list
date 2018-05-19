<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>凭证科目配置信息</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
var statusdata = [{name : '有效',value : '0'},{name : '无效',value : '1'}];
jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				addRemoteOperUrl:getRootPath()+"/acl/addVoucherConfig.acl",
				editRemoteOperUrl:getRootPath()+"/acl/updateVoucherConfig.acl",
				removeRemoteOperUrl:getRootPath()+"/acl/removeVoucherConfig.acl",
				remoteOper:true,
				id:'table_id1',
				width:globalClientWidth,
				height:globalClientHeight,
				editFormPopupWindowHeight:400,
				title:'凭证科目配置信息',
				iconCls:'icon-node',
				multiSelect:true,
				hiddenQueryArea:true,
				queryButtonColSpan:2,
				queryButtonNewLine:false,
				showPager:true,
				xmlFileName:'/voucher/voucherass_stacts_config.xml',
				tools:['add', '-', 'edit', '-','remove'],
				columns:[    
				    {type:'indexcolumn'},
				   	{type:'checkcolumn'},  
				   	{field:'id',header:'id',visible:false},
				   	{field:'subjectsid', header:'科目编码ID',queryConfig:{},formEditorConfig:{required: true}},
				   	{field:'subjectsname', header:'科目名称',formEditorConfig:{required: true}},				
					{field:'subjects_code_long', header:'科目代码',formEditorConfig:{fieldVisible: false}},
					
					{field:'subjectscode', header:'科目段',visible:false,formEditorConfig:{fieldVisible: true,required: true,newLine:true}},
					{field:'subjects_onename', header:'科目段描述',visible:false,formEditorConfig:{fieldVisible: true,required: true}},
					
					{field:'company_code', header:'公司段',visible:false,formEditorConfig:{fieldVisible: true,required: true,newLine:true}},
					{field:'company_name', header:'公司段名称',visible:false,formEditorConfig:{fieldVisible: true,required: true}},
					
				   	{field:'sonsubject_code', header:'子科目段',visible:false,formEditorConfig:{fieldVisible: true,required: true,newLine:true}},
				   	{field:'sonsubject_name', header:'子科目段名称',visible:false,formEditorConfig:{fieldVisible: true,required: true}},
				   	{field:'respon_center', header:'责任中心段',visible:false,formEditorConfig:{fieldVisible: true,required: true,newLine:true}},
				   	{field:'respon_center_name', header:'责任中心段名称',visible:false,formEditorConfig:{fieldVisible: true,required: true}},
				   	
				   	{field:'product', header:'产品段',visible:false,formEditorConfig:{fieldVisible: true,required: true,newLine:true}},
				   	{field:'product_name', header:'产品段名称',visible:false,formEditorConfig:{fieldVisible: true,required: true}},
					{field:'attribute', header:'属性段',visible:false,formEditorConfig:{fieldVisible: true,required: true,newLine:true}},
					{field:'attribute_name', header:'属性段名称',visible:false,formEditorConfig:{fieldVisible: true,required: true}},
				
					{field:'intrabranch', header:'内部往来段',visible:false,formEditorConfig:{fieldVisible: true,required: true,newLine:true}},
					{field:'intrabranch_name', header:'内部往来段名称',visible:false,formEditorConfig:{fieldVisible: true,required: true}},
					{field:'stanby', header:'备用段',visible:false,formEditorConfig:{fieldVisible: true,required: true,newLine:true}},
					{field:'stanby_name', header:'备用段',visible:false,formEditorConfig:{fieldVisible: true,required: true}},
					
				   	{field: 'subjectsownername', header: '所属区域', formEditorConfig:{fieldVisible: false,fillFromFieldName:'subjectsowner',fillProperty:'name'}},
				   	{field:'subjectsowner',header:'所属区域',visible: false,queryConfig:{},
				   		         formEditorConfig:{
							                 type:'combobox',
							             required:true,
							             newLine:true,
							            textField:'name',
							           valueField:'value',
							         fieldVisible:true,
							               params:{dictid:'ContractBelong',xmlFileName:'/eleasing/workflow/contract/contract_approval/contract_dict_data.xml'}}},
				   	{field:'asstacttypename', header:'辅助账类型', formEditorConfig:{fieldVisible:false,fillFromFieldName:'asstacttype',fillProperty:'memo'}},
				   	{field:'asstacttype',visible:false,header:'辅助账类型',
				   		         formEditorConfig:{
				   			                 type:'combobox',
							             required:false,						         
							          multiSelect:true, 
							            textField:'memo',
							           valueField:'id',
							         fieldVisible:true,
							               params:{xmlFileName:'/voucher/voucherassstacts_info.xml'}}},
				   	{field:'statusname', header:'是否有效', formEditorConfig:{fieldVisible:false}},
				   	{field:'status',header:'是否有效',visible:false,
				   		         formEditorConfig:{
				   		        		 
		   		                         required:true,
		   		                         newLine:true,
							                 type:'combobox',//表单域类型
							           valueField:'value',
							            textField:'name',
							         fieldVisible:true,
							           allowInput:false,
							                 data:statusdata
							                 
				   	    }
				   	}
				]
			});
		});
	});
</script>
</head>
<body></body>
</html>