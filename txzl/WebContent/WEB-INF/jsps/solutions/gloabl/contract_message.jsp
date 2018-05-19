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
				id : 'gloabl',
				renderTo : "table_paymentlog_table1",
				width : globalClientWidth,
				height : globalClientHeight,
				title : '风场和风机采购合同对应表',
				iconCls : 'icon-node',
				multiSelect : false,
				hiddenQueryArea : false,
				queryButtonColSpan : 2,
				queryButtonNewLine:true,
				showPager:true,
				remoteOper: true,
				lazyLoad:false,
				loadMode:'ajax',
				params:{},			
				xmlFileName : '/eleasing/jsp/global/annual_contract.xml',
				columns : [ 
				    {type : 'indexcolumn'},
				   	{field : 'id',header : 'id',visible:false},
				   	{field : 'wfid',header : '风场ID',visible:false},
				   	{field : 'scadawfid',header : 'SCADAWF_ID'},
				   	{field : 'projname',header : '项目名称'},
				   	{field : 'custname',header : '客户名称'},
				   	{field : 'oraproid',header : '风机采购合同号'},
				   	{field : 'wfx',header : '风电场经度'},
				   	{field : 'wfy',header : '风电场纬度'},
				   	{field : 'wfname',header : '风电场名称'}
				]
			});
		});
	});
</script>
</head>
<body>
	<div id="mini_test_form">
	        <div id="table_paymentlog_table1"></div>
	</div>

</body>
</html>