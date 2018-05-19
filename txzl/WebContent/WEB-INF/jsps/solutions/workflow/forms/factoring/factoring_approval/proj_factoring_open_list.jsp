<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>保理业务立项</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id:'table_factoring_id',
				width:globalClientWidth,
				height:globalClientHeight,
				title:'保理业务立项',
				iconCls:'icon-node',
				multiSelect:false,
				hiddenQueryArea:false,//是否将查询区域折叠起来
				queryButtonColSpan:2,
				queryButtonNewLine:false,
				showPager:true,				
				xmlFileName:'/eleasing/workflow/proj/proj_factoring/proj_factoring_open_list.xml',
				tools:[
					{html:'保理立项',
					 plain:true,
					 iconCls:'icon-addfolder',
					 handler:function(miniTable, buttonText) {
							var row = miniTable.getSelected();
							if (row) {
								var attachmentParams = "cust_id="+row.custid+"&proj_id="+row.projid;
								startProcess("保理业务立项-1",attachmentParams); 
							
							} else {
								mini.alert('请你选择需要发起立项客户！！！');
							}
						}
					}         
				],
				columns:[ 
				    {type:'indexcolumn'},
				   	{type:'checkcolumn'},  
				   	{field:'projid',header:'id',visible:false},
				   	{field:'custname',header:'客户名称',allowSort:true,queryConfig:{}},
				   	{field:'provincename',header:'所在省份'},
				   	{field:'projsourcename',header:'项目来源'},
				   	{field:'linkman',header:'联系人'},
					{field:'custtypename',header:'客户类型'},
					{field:'projname',header:'项目名称'},
					{field:'leasformname',header:'项目类型'},
				   	{field:'custkindname',header:'内部行业',
				   		           queryConfig:{
                                       colspan:1,
							              type:'combobox', 
							        valueField:'value',
							         textField:'name',
							            params:{xmlFileName:'combos/comboDict.xml',pid:'cust_kind'},
							        allowInput:true, 
							      showNullItem:true}}
				]
			});
		});
	});
</script>
</head>
<body>

</body>

</html>