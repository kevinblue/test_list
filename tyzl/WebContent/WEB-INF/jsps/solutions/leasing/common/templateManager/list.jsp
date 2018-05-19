<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>文件管理</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<%@include file="/base/mini.jsp"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id : 'table_id1',
				width : globalClientWidth,
				height : globalClientHeight,
				title : '文件管理',
				iconCls : 'icon-node',
				multiSelect : false,
				hiddenQueryArea : true,//是否将查询区域折叠起来
				queryButtonColSpan : 2,
				queryButtonNewLine:false,
				showPager:true,
				xmlFileName : '/eleasing/jsp/template_word/filelist_info.xml',
				params:{id:"<mini:param name='id'/>"},
				columns : [ 
				    {type : 'indexcolumn'},
				   	{type : 'checkcolumn'},  
				   	{field : 'id',header : 'id',visible : false},
				   	{field : 'modelname',header : '模块名称',allowSort:true,queryConfig : {}},
				   	{field : 'filename',header : '文件名',queryConfig : {}},
				   	{field : 'creator',header : '创建人'},
				   	{field : 'createdate',header : '创建时间',dateFormat : "yyyy-MM-dd HH:mm:ss",queryConfig : {}},
				   	{field : 'createdate',header : '操作',headerAlign : 'center',
				   		renderer : function(e){
						var id = e.record.id;
						var base = "<a href='#' onclick='fileOperator.showfileRecoder(\"" + id + "\")'>记录</a>";
					    var base2 = "<a href='#' onclick='fileOperator.downFile(\"" + id + "\")'>下载</a>";
					    var base3 = "<a href='#' onclick='fileOperator.showOReditFile(\"" + id + "\",\"1\")'>编辑</a>";
					    return base2+"&nbsp;&nbsp;"+base+"&nbsp;&nbsp"+base3; 
					   }
					 }
				]
			});
		});
	});
</script>
</head>
<body>


</body>
</html>