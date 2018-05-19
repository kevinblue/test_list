<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<%@include file="/base/mini.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">
	var user = [];
	jQuery(function() {
		seajs
				.use(
						[ "js/apcomponent/aptable/aptable.js" ],
						function(ApTable) {
							new ApTable(
									{
										id : 'table_factoring_controversy_invoice',
										renderTo : "id_table_factoring_controversy_invoice",
										width : globalClientWidth,
										height : globalClientHeight,
										iconCls : 'icon-node',
										pageSize : 20,
										showPager : true,
										hiddenQueryArea : false,
										multiSelect : false,
										editFormPopupWindowWidth : 800,
										editFormPopupWindowHeight : 380,
										remoteOper : true,
										pageSize : 20,
										showPager : true,
										lazyLoad : false,
										loadMode : 'ajax',
										frozenStartColumn : 0,
										frozenEndColumn : 2,
										entityClassName : 'com.tenwa.leasing.entity.factoring.FactoringControversy',
										xmlFileName:'/eleasing/workflow/factoring/factoring_controversy/factoring_controversy_other.xml',
										tools: ['add', '-', 'edit', '-','remove','-','exportExcel'],
										columns:[ 
												    {
												    	type:'indexcolumn',
												    	headerAlign : 'center'
												    },
												    {
												    	type:'checkcolumn',
												    	headerAlign : 'center'
												    },
												    {
												    	field:'id',
												    	header:'ID',
												    	headerAlign : 'center',
												    	visible : false
												    },
												    {
												    	field:'contractid',
												    	header:'合同编号',
												    	headerAlign : 'center',
												    	allowSort:true,
												    	formEditorConfig : {
												    		width:200,
															type : 'text',
															newLine : true,
															required : true,
															fieldVisible : true
														}
												    },
												    {
												    	field:'applicationnumber',
												    	header:'争议申请编号',
												    	headerAlign : 'center',
												    	allowSort:true,
												    	formEditorConfig : {
												    		width:200,
												    		labelWith:100,
															type : 'text',
															newLine : true,
															required : true,
															fieldVisible : true
														}
												    },
												    {
												    	field:'applicationdate',
												    	header:'争议申请时间',
												    	headerAlign : 'center',
												    	allowSort:true,
												    	formEditorConfig : {
												    		width:200,
												    		colspan: 2,	
															type : 'date',
															newLine : true,
															readonly:true,
															defaultValue:mini.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"),
															format:'yyyy-MM-dd HH:mm:ss',
															required : true,
															fieldVisible : true
														}
												    },
												    {
												    	field:'explain',
												    	header:'争议申请说明',
												    	headerAlign : 'center',
												    	allowSort:true,
												    	formEditorConfig : {
															type:'textarea',
															colspan: 4,	
												            width:400,
															height:60,
															newLine : true,
															required : true,
															allowInput : true,
															required : true,
															fieldVisible : true
														}
												    }
												]
									});
						});
	});
</script>
</head>
<body>
	<div id="mini_test_form">
		<div id="id_table_factoring_controversy_invoice"></div>
	</div>
</body>
</html>
