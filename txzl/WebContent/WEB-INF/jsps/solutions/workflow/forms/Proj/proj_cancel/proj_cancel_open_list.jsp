<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>项目撤销</title>
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
				title:'项目撤销',
				iconCls:'icon-node',
				multiSelect:false,
				queryButtonColSpan:4,
				queryButtonNewLine:false,
				showPager:true,
				xmlFileName:'/eleasing/workflow/proj/proj_cancel/proj_cancel_open_list.xml',
				params:{extendsql:extendsql},
				tools:[
					 {
						html:'项目撤销',
						plain:true,
						iconCls:'icon-addfolder',
						handler:function(miniTable, buttonText) {
							var row = miniTable.getSelected();
							if(row){
								var attachmentParams = "proj_id="+row.id;
				        		startProcess("项目撤销-1",attachmentParams); 
							}else{
								mini.alert('请你选择需要发起撤销的项目！！！');
							}
						
						}
					}         
				],
				columns:[ 
				    {type:'indexcolumn'},
				   	{type:'checkcolumn'},  
				   	{field:'id',header:'id',visible:false},
					{field:'projid',header:'项目编号'},
					{field:'custname',header:'客户名称',queryConfig:{}},
					{field:'projectname',header:'项目名称',queryConfig:{}},				   	
					{field : 'cleanleasemoney',	dataType : "currency",header : '融资金额',	align : "right"	},
					{field:'custkind',header:'内部行业'},
					{field : 'leasformname',header : '项目类型',
						queryConfig : {
							newLine:false,
							type : 'combobox',
							params : {
								pid : 'leas_form',
								xmlFileName : 'combos/comboDict.xml'
							},
							readOnly : false,
							textField : 'name',
							valueField : 'value'
						}
					},
				   	{field:'orgcodecardno',header:'身份证/组织机构代码'},				   	
				   	{field:'projmanager',header:'项目经理',queryConfig:{newLine:true}},
				   	{field:'deptname',header:'出单部门'},
				   	{field:'projstatus',header:'项目状态',headerAlign:'center'}
				]
			});
		});
	});
</script>
</head>
<body></body>
</html>