<%@page import="java.net.URLEncoder"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>应收账款信息维护</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id:'table_factoring_id',
				width:globalClientWidth,
				height:globalClientHeight,
				title:'应收账款信息维护',
				iconCls:'icon-node',
				multiSelect:false,
				hiddenQueryArea:false,//是否将查询区域折叠起来
				queryButtonColSpan:2,
				queryButtonNewLine:false,
				showPager:true,				
				xmlFileName:'/eleasing/workflow/proj/proj_account_rece/proj_factoring_account_receivable.xml',
				tools:[
					{html:'应收账款信息维护',			 	
					 plain:true,
					 iconCls:'icon-addfolder',
					 handler:function(miniTable, buttonText) {
						 var row = miniTable.getSelected();
						    if (row)
						      {
						     	var id = row.id;
						    	showDetail(id); 						    	
						    	}
						    else   
						      {
						    	mini.alert("请选择要操作的项");
						    	return false;
						    	}
						}
					}         
				],
				columns:[ 
				    {type:'indexcolumn'},
				   	{type:'checkcolumn'},
				   	{field:'contract_id',header:'合同编号',visible:true},
				   	{field:'contract_number',header:'业务合同编号',queryConfig:{}},
				   	{field:'cust_name',header:'客户名称',queryConfig:{}},
				   	{field:'project_name',header:'项目名称',queryConfig:{newLine:false}},
					{field:'price',header:'应收账款总额'},
					{field:'proj_manage',header:'项目经理',queryConfig:{newLine:true}}
				]
			});
		});
	});
	
	
 	function showDetail(id) {
	    var url = getRootPath()
			+ "/acl/showAccountReceivable.acl?contractid="
			+ id + "&opertype=view";
		var sheight = window.screen.availHeight - 30;
		var swidth = window.screen.availWidth - 10;
		var winosption = "left=0px,top=0px,height="
			+ sheight
			+ "px,width="
			+ swidth
			+ "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
		window.open(url, '_blank', winosption);
	} 



</script>
</head>
<body>

</body>

</html>