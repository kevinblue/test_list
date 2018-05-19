<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>项目变更</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
var extendsql="";
<permission:action code="permission_manager">
	extendsql = "and leader.id_ = '${sessionScope.login_userid}' "
</permission:action>
<permission:action code="permission_fzj">
	extendsql = "and leader.fzj = '${sessionScope.login_userid}' "
</permission:action>
<permission:action code="permission_fdqzj">
	extendsql = "and leader.fdqzj = '${sessionScope.login_userid}' "
</permission:action>
<permission:action code="permission_qyzj">
	extendsql = "and leader.qyzj = '${sessionScope.login_userid}' "
</permission:action>
<permission:action code="permission_zj">
	extendsql = "and leader.zj = '${sessionScope.login_userid}' "
</permission:action>
<permission:action code="permission_all">
	extendsql = " or 1=1 "
</permission:action>
	jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id:'table_id1',
				width:globalClientWidth,
				height:globalClientHeight,
				editFormPopupWindowWidth : 700,
				editFormPopupWindowHeight:250,
				title:'项目状态维护',
				iconCls:'icon-node',
				multiSelect:false,
				queryButtonColSpan:4,
				queryButtonNewLine:false,
				loadMode:'ajax',
				remoteOper:true,
				showPager:true,
				entityClassName : 'com.tenwa.leasing.entity.proj.ProjInfo',
				xmlFileName:'/eleasing/workflow/proj/proj_update/proj_update_open_list.xml',
				params:{extendsql:extendsql},
				operValidate:function(miniTable,rowData,oper){
					if('edit' == oper){
						if(rowData && rowData.projstatus>=11){
							mini.alert("请选择未完成项目信审的项目进行操作! ");
							return false;
						}
						return true;
					}
				},
				tools:[
					<permission:action code="cust_saveorupdate_permission">'edit','-',</permission:action >
					'exportExcel'],
				columns:[ 
				    {type:'indexcolumn'},
				   	{type:'checkcolumn'},  
				   	{field:'id',header:'id',visible:false,
                        formEditorConfig:{
			                  readOnly:true,
			              fieldVisible:false }},
					{field:'projid',header:'项目编号',queryConfig:{},formEditorConfig:{
					        readOnly:true}},
					{field:'projmanager',header:'项目经理',queryConfig:{},formEditorConfig:{
					        readOnly:true,
							    fieldVisible:false }},
					{field:'cust_name',header:'客户名称',width:200,queryConfig:{},formEditorConfig:{
					        readOnly:true }},
					{field:'province',header:'省',formEditorConfig:{
				        readOnly:true,
					    fieldVisible:false }},
					{field:'city',header:'市',formEditorConfig:{
				        readOnly:true,
					    fieldVisible:false }},
					{field:'projdate',header:'项目启动日期',formEditorConfig:{
				        readOnly:true,
					    fieldVisible:false 
					}},
					{field:'projstatusname',header:'项目进展',formEditorConfig:{
						newLine:true,
						fieldVisible:false,
				         fillFromFieldName:'proj_status',
			              fillProperty:'name'
					}},
					{field:'projstatus',header:'项目进展',visible:false,formEditorConfig:{
						fieldVisible:true,
						allowInput:false,
						type:'combobox',
						params: {xmlFileName: '/common/proj_status_update.xml'},
						textField:'name',
						valueField:'value'
					},queryConfig:{
						fieldVisible:true,
						newLine:true,
						type:'combobox',
						params: {xmlFileName: '/common/proj_status_update.xml'},
						textField:'name',
						valueField:'value'
					}},
					{field:'expectputdate',header:'预计投放日期',formEditorConfig:{
			             type:'date',
				           format:'yyyy-MM-dd '
					}},
					{field:'expectapprovedate',header:'预计出具日期',formEditorConfig:{
						newLine:true,
			             type:'date',
				           format:'yyyy-MM-dd '
					}},
					{field:'expectsigndate',header:'预计签约日期',formEditorConfig:{
			             type:'date',
				           format:'yyyy-MM-dd '
					}},
					{field:'competitors',header:'主要竞争对手',formEditorConfig:{
				        readOnly:true,
					    fieldVisible:false }},
					{field:'custincome',header:'收入(万元)',formEditorConfig:{
				        readOnly:true,
					    fieldVisible:false }},
					{field:'debtratio',header:'资产负债率',formEditorConfig:{
				        readOnly:true,
					    fieldVisible:false }},
					{field:'custlevel',header:'等级',formEditorConfig:{
				        readOnly:true,
					    fieldVisible:false }},
					{field:'custbednum',header:'实际开放床位',formEditorConfig:{
				        readOnly:true,
					    fieldVisible:false }},
					{field:'custprofit',header:'利润(万元)',formEditorConfig:{
				        readOnly:true,
					    fieldVisible:false }},
					{field:'custdebt',header:'刚性负债(万元)',formEditorConfig:{
				        readOnly:true,
					    fieldVisible:false }},
					{field:'leaseform',header:'融资类型',formEditorConfig:{
				        readOnly:true,
					    fieldVisible:false }},
					{field:'leaseterm',header:'融资期限',formEditorConfig:{
				        readOnly:true,
					    fieldVisible:false }},
					{field:'settlemethod',header:'还款方式',formEditorConfig:{
				        readOnly:true,
					    fieldVisible:false }},
					{field:'cleanleasemoney',header:'融资金额',formEditorConfig:{
				        readOnly:true,
					    fieldVisible:false }},
					{field:'irr',header:'IRR',formEditorConfig:{
				        readOnly:true,
					    fieldVisible:false }},
					{field:'funduse',header:'资金用途',formEditorConfig:{
				        readOnly:true,
					    fieldVisible:false }},
					{field:'agentsource',header:'项目来源',formEditorConfig:{
				        readOnly:true,
					    fieldVisible:false }},
					{field:'agentcompany',header:'公司',formEditorConfig:{
				        readOnly:true,
					    fieldVisible:false }},
					{field:'agentname',header:'姓名',formEditorConfig:{
				        readOnly:true,
					    fieldVisible:false }},
					{field:'agentjob',header:'职位',formEditorConfig:{
				        readOnly:true,
					    fieldVisible:false }},
					{field:'agentphone',header:'手机',formEditorConfig:{
				        readOnly:true,
					    fieldVisible:false }},
					{field:'custmemo',header:'备注',formEditorConfig:{
				        readOnly:true,
					    fieldVisible:false }}

				]
			});
		});
	});
</script>
</head>
<body></body>
</html>