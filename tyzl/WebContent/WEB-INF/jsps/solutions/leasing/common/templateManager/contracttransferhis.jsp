<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>项目移交历史信息</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
</head>
<%@include file="/base/mini.jsp"%>
<%
String projid=request.getParameter("id");//合同主建id
%>
<body style="overflow:hidden;"> 
   <div id="id_contract_transfer_table"></div>
   <script type="text/javascript" defer>
   jQuery(function(){
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable){ 					
			new ApTable({
				id:"contract_transfer_table",
				renderTo:"id_contract_transfer_table",
				width:globalClientWidth,
				height:globalClientHeight,
				showPager :true,
				queryButtonColSpan :8,
				queryButtonNewLine:true,
				multiSelect:false,
				isExcel:true,
				remoteOper :false,
				xmlFileName:"/eleasing/workflow/proj/proj_transfer/proj_transfer_his.xml",
				showToolbar: false,
				columns:[
					{type : 'indexcolumn'},
					{field:'projid', header:'项目编号',visible:false},
					{field:'formermanagename', header:'前项目经理'},
					{field:'currentmanagename', header:'现项目经理'},
					{field:'operator', header:'操作人'},
					{field:'operatordate', header:'操作时间',dateFormat:"yyyy-MM-dd"},
					{field:'note', header:'移交备注'}
                 ],
		        params:{
		        	projid:"<%=projid%>"
			  	}
				
			});
		});
	});
</script>
</body>
</html>