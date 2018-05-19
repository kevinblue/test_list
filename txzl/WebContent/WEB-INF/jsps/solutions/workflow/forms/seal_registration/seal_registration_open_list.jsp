<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>印章使用登记</title>
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
				title:'印章使用登记',
				iconCls:'icon-node',
				multiSelect:false,
				hiddenQueryArea:false,//是否将查询区域折叠起来
				queryButtonColSpan:2,
				queryButtonNewLine:false,
				showPager:true,				
				//xmlFileName:'/eleasing/workflow/proj/proj_approval/proj_approval_open_list.xml',
				tools:[
					{html:'印章使用流程',
					 plain:true,
					 iconCls:'icon-addfolder',
					 handler:function(miniTable, buttonText) {
				        		startProcess("公章使用登记-1",''); 
						}
					}         
				],
				xmlFileName: '/eleasing/workflow/contract/seal_registration/seal_registration_list.xml',
				columns: [
					{type: 'indexcolumn'},
					{field: 'id', header: 'id', visible: false},
					{field: 'project_name', header: '项目名称',queryConfig:{width:165}},
					{field: 'seal_file', header: '盖章文件名称',queryConfig:{width:165}},
					{field: 'flow_number', header: '流程编号/名称'},
					{field: 'submit_department', header: '报送部门',queryConfig:{width:165}},
					{field: 'document_number', header: '文件份数'},
					{field: 'proj_manage', header: '申请人',queryConfig:{width:165,newLine: true}},
					{field: 'proj_dept', header: '申请部门'},
					{field: 'seal_type', header: '用章种类',queryConfig:{width:165}},
					{field: 'registration_date', header: '登记日期'},
					{field: 'remarks', header: '备注'},
					{
						field : '',
						header : '操作',
						formEditorConfig : {
							fieldVisible : false
						},
						renderer : function(e) {
							var id = e.record.flow_number;
							return "<a href='javascript:void(0);' onclick='showanduploadfile(\""
									+ id
									+ "\",\"one\")'>查看附件 </a>";
						}
					}
				]
			});
		});
	});

function showanduploadfile(id, type) {
	var urlFlag = getRootPath()
			+ "/workflow/forms/seal_registration/seal_registrationl_file_list.bi?id="
			+ id + "&type=" + type;
	mini.open({
		url : urlFlag,
		title : "公章相关附件",
		width : 800,
		height : 455,
		onload : function() {
		},
		ondestroy : function(action) {
			if ("savesuccess" == action) {
				window.location.reload();
			}
		}
	});

}
</script>
</head>
<body></body>
</html>