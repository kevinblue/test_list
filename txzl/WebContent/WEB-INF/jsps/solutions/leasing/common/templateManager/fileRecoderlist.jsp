<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件操作记录</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>

<%@include file="/base/mini.jsp"%>
<script type="text/javascript">
jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id : 'table_id1',
				width : globalClientWidth,
				height : globalClientHeight,
				title : '文件操作记录',
				iconCls : 'icon-node',
				multiSelect : false,
				hiddenQueryArea : true,//是否将查询区域折叠起来
				queryButtonColSpan : 2,
				queryButtonNewLine:false,
				showPager:true,
				xmlFileName : '/eleasing/jsp/template_word/fileRecorderList_info.xml',
				params:{id:"${param.id}"},
				columns : [ 
				    {type : 'indexcolumn'},
				   	{field : 'creator',header : '创建人'},
				   	{field : 'createdate',header : '创建时间',dateFormat : "yyyy-MM-dd HH:mm:ss"},
				   	{field : 'operatortype',header : '操作'}
				   
				]
			});
		});
	});
</script>
</head>
<body>


</body>
</html>