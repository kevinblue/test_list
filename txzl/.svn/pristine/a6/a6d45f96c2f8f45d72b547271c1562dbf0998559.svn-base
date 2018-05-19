<%@ page language="java" import="java.net.URLDecoder" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>历史巡视记录</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<%String custid=request.getParameter("custid");%>
<script type="text/javascript">
var custid = "<%= custid %>";
var screenHeight = window.screen.height;
var fixedHeight = 300;
var topHeight = 460;

//合同信息
jQuery(function() {
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id : 'table_id1',
			width : "100%",
			height : fixedHeight,
			iconCls : 'icon-node',
			renderTo:'content_table_id1',
			remoteOper : true,
			pageSize : 10,
			showPager : true,
			lazyLoad : false,
			xmlFileName : '/eleasing/workflow/contract/contract_patrol/contract_patrol_history.xml',
			//entityClassName:'com.tenwa.leasing.entity.asset.AssetMngDetail',
			params : {custid :custid},		
			columns : [ 
			         {type:'indexcolumn'},
			         {field:'id',header:'编号',headerAlign:'center',width:100,allowSort:true,visible:false},
					 {field:'custname',header:'客户名称',headerAlign:'center',width:100},
					 {field:'xdate',header:'巡视日期',headerAlign:'center',width:100},
					 {field:'xnum',header:'巡视编号',headerAlign:'center',width:100}
					 ]
		});
	});
});

function onhireTypeComboboxChanged(e){
	document.getElementById('contactWay').value = e.sender.text;
}

</script>

</head>
<body style="overflow: auto">
<div>
	<div id='content_table_id1'></div>
</div>
</body>
</html>
