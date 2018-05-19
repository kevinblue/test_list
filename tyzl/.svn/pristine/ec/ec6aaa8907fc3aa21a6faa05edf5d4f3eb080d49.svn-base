<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>项目立项</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id:'table_id1',
				width:globalClientWidth,
				height:globalClientHeight,
				title:'项目立项',
				iconCls:'icon-node',
				multiSelect:false,
				hiddenQueryArea:false,//是否将查询区域折叠起来
				queryButtonColSpan:2,
				queryButtonNewLine:false,
				showPager:true,				
				xmlFileName:'/eleasing/workflow/proj/proj_approval/proj_approval_open_list.xml',
				tools:[
					{html:'项目立项',
					 plain:true,
					 iconCls:'icon-addfolder',
					 handler:function(miniTable, buttonText) {
							var row = miniTable.getSelected();
							if(row){
								var attachmentParams = "cust_id="+row.custid;
				        		startProcess("项目立项-1",attachmentParams); 
							}else{
								mini.alert('请你选择需要发起立项客户！！！');
							}						
						}
					}         
				],
				columns:[ 
				    {type:'indexcolumn'},
				   	{type:'checkcolumn'},  
				   	{field:'id',header:'id',visible:false},
				   	{field:'custname',header:'客户名称',allowSort:true,queryConfig:{}},
				   	{field:'custclass',header:'客户性质'},
				   	{field:'custkind',header:'内部行业',
				   		           queryConfig:{
                                       colspan:1,
							              type:'combobox', 
							        valueField:'value',
							         textField:'name',
							            params:{xmlFileName:'combos/comboDict.xml',pid:'cust_kind'},
							        allowInput:true, 
							      showNullItem:true}},
				   	{field:'orgcodecardno',header:'身份证号/组织机构代码',queryConfig:{newLine:true}},
				   	{field:'creator',header:'登记人'},
				   	{field:'createdate',header:'登记时间',headerAlign:'center',renderer:"miniextonDateRenderer"}
				]
			});
		});
	});
</script>
</head>
<body></body>
</html>