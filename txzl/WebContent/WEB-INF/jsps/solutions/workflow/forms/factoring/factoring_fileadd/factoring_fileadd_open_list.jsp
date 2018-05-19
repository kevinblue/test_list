<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>项目资料补充</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
	jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id:'table_id1',
				width:globalClientWidth,
				height:globalClientHeight,
				title:'保理资料补充',
				iconCls:'icon-node',
				multiSelect:false,
				queryButtonColSpan:2,
				queryButtonNewLine:false,
				showPager:true,
				/* params:{projmanager:'${sessionScope.login_userid}'}, */
				xmlFileName:'/eleasing/workflow/factoring/factoring_fileadd/factoring_fileadd_open_list.xml',
				tools:[
					{
						html:'保理资料补充',
						plain:true,
						iconCls:'icon-addfolder',
						handler:function(miniTable, buttonText) {
							var row = miniTable.getSelected();
							if(row){
								var attachmentParams = "proj_id="+row.id;
				        		startProcess("保理资料补充-1",attachmentParams); 
							}else{
								mini.alert('请你选择需要发起变更的项目！！！');
							}
						
						}
					}         
				],
				columns:[ 
				     {type:'indexcolumn'},
				   	{type:'checkcolumn'},  
				   	{field:'id',header:'id',visible:false},
					{field:'projid',header:'项目编号'},
					{field:'projectname',header:'项目名称'},
				   	{field:'custname',header:'客户名称',queryConfig:{}},
				   	{field:'orgcodecardno',header:'身份证/组织机构代码'},
				   	{field:'custkind',header:'内部行业'},
				   	{field:'projmanager',header:'项目经理',queryConfig:{newLine:false}},
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