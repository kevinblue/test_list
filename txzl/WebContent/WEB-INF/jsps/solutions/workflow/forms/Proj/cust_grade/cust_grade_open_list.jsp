<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>客户信用评级审核流程</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
	jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id:'table_id1',
				renderTo: "id_table_id1",
				width:globalClientWidth,
				height:globalClientHeight,
				title:'客户信用评级审核流程发起',
				iconCls:'icon-node',
				multiSelect:false,
				queryButtonColSpan:6,
				width:"100%",
				heigth:"100%",
				showPager:true,
				xmlFileName:'/eleasing/workflow/proj/cust_grade/cust_grade_info_list.xml',
				tools:[
					{
						html:'客户信用评级审核',
						plain:true,
						iconCls:'icon-addfolder',
						handler:function(miniTable, buttonText) {
							var row = miniTable.getSelected();
							if(row){
								var attachmentParams = "custid="+row.custid;
				        		startProcess("客户信用评级审核-1",attachmentParams); 
							}else{
								mini.alert('请你选择需要发起客户信用评级审核流程的项目！！！');
							}

						}
					}         
				],
				columns:[ 
							{type:'indexcolumn'},
							{type:'checkcolumn'}, 
							{field:'custid',header:'custid',visible:false},
							{field:'custnumber',header:'客户编号',queryConfig:{}}, 
							{field:'custname',header:'客户名称',queryConfig:{}},
							/* {field:'custscalename',header:'适用评级模型',queryConfig:{}}, */
							{field:'orgcodecardno',header:'身份证号/组织机构代码'},
							{field:'custcategory',header:'客户类别'},
							{field:'custclass',header:'客户性质'},
							{field:'grade_result',header:'客户信用等级',queryConfig:{newLine:true}}
							]
			})
		});
	});

</script>
</head>
<body>
	<div id="id_table_id1" style="height: 100%"></div>
</body>
</html>
