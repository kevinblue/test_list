<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="/WEB-INF/tlds/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="CustomerCodingMaintenance" text="客户编码维护"/></title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript">
jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id : 'table_id1',
				width : globalClientWidth,
				height : globalClientHeight,
				title : '<spring:message code="CustomerCodingMaintenance" text="客户编码维护"/>',
				iconCls : 'icon-node',
				editRemoteOperUrl:getRootPath()+"/acl/editVoucherCust.acl",
				multiSelect : true,
				hiddenQueryArea : false,//是否将查询区域折叠起来
				queryButtonColSpan : 2,
				queryButtonNewLine:false,
				showPager:true,
				remoteOper: true,
				loadMode:'ajax',
				xmlFileName : '/voucher/voucher_cust_list.xml',
				tools : ['edit','-','exportExcel'],
				columns : [ 
				    {type : 'indexcolumn'},
				   	{type : 'checkcolumn'},  
				   	{field : 'id',header : 'id',visible:false},
				   	{field : 'custid',header : 'custidid',visible:false},
				   	{field : 'custname',header : '<spring:message code="CustomerNameOne" text="客户名称"/>',formEditorConfig:{readOnly:true},queryConfig:{}},
				   	{field : 'custnumber',header : '<spring:message code="CustomerNo" text="客户编号"/>',formEditorConfig:{readOnly:true,fieldVisible:false},queryConfig:{}},
				   	{field : 'custtypename',header : '<spring:message code="CustomerType" text="客户类别"/>',formEditorConfig:{readOnly:true}},
				   	{field : 'financialcode',header : '<spring:message code="SubjectCode_customer" text="客户科目编码"/>',formEditorConfig:{required:true,newLine:true}},
				   	{field : 'financialcode_vendor',visible:false,header : '<spring:message code="SubjectCode_vendor" text="供应商科目编码"/>',formEditorConfig:{newLine:true}}
				]
			});
		});
	});
</script>
</head>
<body></body>
</html>