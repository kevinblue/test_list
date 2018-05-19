<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>诉讼结论登记</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
var currentClientWidth = document.documentElement.clientWidth;
var currentClientHeight = document.documentElement.clientHeight;
jQuery(function(){
	tenwa.createTable({
		id: "table_id1",
		renderTo: "id_table_container_onhire_contract_change",
		width: currentClientWidth,
		height: currentClientHeight,
		lazyLoad: false,
		title: "诉讼结论登记",
		remoteOper : false,
		showPager: true,
		pageSize: 20,
		tools: [
		{html:'诉讼结论登记',
			 plain:true,
			 iconCls:'icon-addfolder',
			 handler:function(miniTable, buttonText) {
					var rows = miniTable.getSelecteds();
					if (rows.length == 0) {
						mini.alert("请选择要登记的条目！");
						return false;
					}
				 var buttonFlag = 'litigation';
					var url = getRootPath()
							+ "/acl/showLitigationConclusionRegistrationApplication.acl?opertype=litigation&lawnum="+rows[0].lawnum
					var sheight = window.screen.availHeight - 30;
					var swidth = window.screen.availWidth - 10;
					var winoption = "left=0px,top=0px,height="
							+ sheight
							+ "px,width="
							+ swidth
							+ "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
					window.open(url, '_blank', winoption);					
				}
			} 
		],
		queryButtonColSpan: 4,
		xmlFileName: '/eleasing/jsp/law_mng/litigation_conclusion_registration.xml',
		params:{contractstatus:"31"},
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'lawnum', header: '法务编号',queryConfig:{}},
			{field: 'contract_id', header: '合同编号',queryConfig:{}},
			{field: 'contract_number', header: '业务合同编号',queryConfig:{}},
			{field: 'project_name', header: '项目名称',queryConfig:{newLine: true}},
			{field: 'cust_name', header: '客户名称',queryConfig:{colspan:3}},
			{field: 'card_no', header: '身份证/组织机构代码',queryConfig:{newLine: true}},
			{field: 'industry_type', header: '内部行业'},
			{field: 'projmanage', header: '项目经理'},
			{field: 'department', header: '出单部门'},
			{field: 'contractstatus', header: '合同状态'}
		]
	});
});
</script>
</head>
<body>
<div id="id_table_container_onhire_contract_change"></div>
</body>
</html>