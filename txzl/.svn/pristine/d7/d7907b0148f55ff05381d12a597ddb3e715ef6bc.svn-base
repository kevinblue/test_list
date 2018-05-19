<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>档案交接</title>
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
				title:'档案交接',
				iconCls:'icon-node',
				multiSelect:false,
				hiddenQueryArea:false,//是否将查询区域折叠起来
				queryButtonColSpan:6,
				queryButtonNewLine:true,
				showPager:true,				
				//xmlFileName:'/eleasing/workflow/proj/proj_approval/proj_approval_open_list.xml',
				tools:[
					{html:'档案交接',
					 plain:true,
					 iconCls:'icon-addfolder',
					 handler:function(miniTable, buttonText) {
							var row = miniTable.getSelected();
							if(row){
								var attachmentParams = "contract_id="+row.id;
				        		startProcess("档案交接流程-1",attachmentParams); 
							}else{
								mini.alert('请你选择需要发起档案交接流程的清单！！！');
							}						
						}
					}         
				],
				//xmlFileName: '/eleasing/workflow/contract/contract_borrow/contract_borrow_list.xml',
				xmlFileName: '/eleasing/workflow/contract/file_transfer/file_transfer_open_list.xml',
				columns: [
					{type: 'indexcolumn'},
					{type: 'checkcolumn'},
					{field: 'id', header: 'id', visible: false},
					{field: 'contract_id', header: '合同编号'},
					{field: 'contract_number', header: '业务合同编号',queryConfig:{width:165}},
					{field: 'project_name', header: '项目名称',queryConfig:{width:165}},
					{field: 'cust_name', header: '客户名称',queryConfig:{width:165,newLine: false}},
					{field: 'card_no', header: '身份证/组织机构代码'},
					{field: 'industry_type', header: '内部行业'},
					{field: 'projmanagename', header: '项目经理'},
					{field: 'department', header: '出单部门'},
					{field: 'contractstatus', header: '项目状态'}
				]
			});
		});
	});
</script>
</head>
<body></body>
</html>