<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>SAP主数据流程</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
var currentClientWidth = document.documentElement.clientWidth;
var currentClientHeight = document.documentElement.clientHeight;
jQuery(function(){
	tenwa.createTable({
		id: "sap_main_data_info",
		renderTo: "id_table_sap_main_data",
		width: currentClientWidth,
		height: currentClientHeight,
		lazyLoad: false,
		title: "SAP主数据流程",
		remoteOper : false,
		showPager: true,
		pageSize: 20,
		tools: [{
			html: 'SAP主数据流程',
			plain: true,
			iconCls: 'icon-addfolder',
			handler: function(miniTable, buttonText){
					var attachmentParams = "";
					startProcess("SAP主数据流程-1",attachmentParams); 
			}
		}],
		queryButtonColSpan: 2,
		xmlFileName: '/eleasing/workflow/contract/sap_main_data_info/sap_main_data_info.xml',
		columns: [
			{field: 'id', header: 'id', visible: false},
			{field: 'sapnumber', header: '流程编号',queryConfig:{width:165}},
			{field: 'applicantdept', header: '申请人部门',
				queryConfig:{
				width:165,
				type:'combobox',
               	 valueField:'text',
		         textField:'text',
		        data:[{id:'LBS产品线',text:'LBS产品线'},{id:'A产品线',text:'A产品线'},{id:'B产品线',text:'B产品线'}]
				}
			},
			{field: 'applicantreason', header: '申请原因',
				queryConfig:{
					width:165,
					type:'combobox',
	               	 valueField:'text',
			         textField:'text',
			        data:[{id:'新增',text:'新增'},{id:'修改',text:'修改'}]
					}
			},
			{field: 'ecrnumber', header: 'ERC号'},
			{field: 'isreleasebmc', header: '是否申请Release BMC'},
			{field: 'projnumber', header: '项目号'},
			{field: 'others', header: '其它'},
			{field: 'edittext', header: '修改内容'},
			{field: 'designmasterdata', header: '设计主数据'},
			{field: 'needbmc', header: '是否需要Release BMC'},
			{field: 'nextstep', header: '下一步处理'},
			{field: 'alreadybmc', header: '是否已经Release BMC'},
			{field: 'createdate', header: '申请时间',queryConfig:{width:165,newLine: true}},
			{field: 'creator', header: '申请人',queryConfig:{width:165}}
		]
	});
});
</script>
</head>
<body>
<div id="id_table_sap_main_data"></div>
</body>
</html>