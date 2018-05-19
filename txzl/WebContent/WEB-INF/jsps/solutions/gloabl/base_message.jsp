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
				title : '风机月（年）度运行报表',
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
				xmlFileName : '/eleasing/jsp/global/annual_base.xml',
				columns : [ 
				    {type : 'indexcolumn'},
				   	{field : 'id',header : 'id',visible:false},				   	
				   	{field : 'wfid',header : '风场ID',visible:false},
				   	{field : 'projname',header : '项目名称'},
				   	{field : 'custname',header : '客户名称'},
				   	{field : 'wfwtid',header : '风场风机ID',visible:false},
				   	{field : 'reporttype',header : '月报/年报'},
				   	{field : 'reporttime',header : '时间'},
				   	{field : 'fannumber',header : '风机编号'},
				   	{field : 'fantype',header : '机型'},
				   	{field : 'windspeed',header : '平均风速(m/s)'},
				   	{field : 'power',header : '发电量（统计时段内kWh）'},
				   	{field : 'electricdate',header : '等效利用小时数(h)'},
				   	{field : 'dateavailability',header : '机组可利用率(%)'},
				   	{field : 'faultnums',header : '故障次数(次)'},
				   	{field : 'faultdate',header : '故障停机总时长(h)'},
				   	{field : 'mtbf',header : '平均无故障时间(h)'},
				  	{field : 'mttr',header : '故障排除时间(kWh)'},
				   	{field : 'maintainloss',header : '维护损失电量(kWh)'},
				   	{field : 'limitloss',header : '限功率损失电量(kWh)'},
				   	{field : 'faultloss',header : '故障损失电量(kWh)'},
				   	{field : 'totalloss',header : '损失电量合计(kWh)'},
				   	{field : 'temperature',header : '温度'},
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