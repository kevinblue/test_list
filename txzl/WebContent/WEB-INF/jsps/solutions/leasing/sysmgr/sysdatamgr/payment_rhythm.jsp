<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>付款节奏维护</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/seajs/sea.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
var localEnabled = [{id : '1',text : '第一次支付'}, {id : '2',text :'第二次支付' }, {id : '3',text :'第三次支付' }];
jQuery(function(){
	 var showTools = true;
	if ('${param.isView}' == 'true') {
		showTools = false;
	}  
	tenwa.createTable({
		id: 'csut_apply_list',
		renderTo: 'id_table_csut_apply_list',
		width:globalClientWidth,
		height: 600,
		lazyLoad: false,
		showPage: true,
		showToolbar: showTools,
		hiddenQueryArea : true,
		pageSize: 20,
		showPager : true,//分页按钮是否显示
		remoteOper :true,
		entityClassName :"com.tenwa.leasing.entity.paymentrhythm.PaymentRhythm",
		tools: ['add', '-', 'edit', '-', 'remove'],
		xmlFileName : '/eleasing/jsp/sysmgr/sysdatamgr/payment_rhythm.xml',	
		columns: [
			{type: 'checkcolumn'},
			{field: 'id', header: '主键', visible: false},
			{field: 'vndrtypename', header: '贸易类型', visible: true,
	            	  formEditorConfig:{
						   		 fieldVisible: false,
						   		 fillFromFieldName : 'payment',
								 fillProperty : 'name'
					}},
			{field : 'vndrtype',header : '贸易类型',visible: false,queryConfig : {},
			   		  formEditorConfig : {
					   			 type : 'combobox',
								 textField: 'name',
								 newLine:true,
								 required: true,
								 valueField: 'value',
								 fieldVisible: true,
								 params:{pid: 'vndr_type',xmlFileName: '/combos/comboDict.xml'},
				                 textField: 'name'
			   		}},
			{field : 'paymentpen',header : '付款笔数',
				   		  formEditorConfig:{
					   				 newLine:true,
					   				 data:localEnabled,
					   				 type:'combobox',
					   				 required:true
				   				}},
			{field : 'termspayment',header : '支付条件',
			   		  formEditorConfig:{
				   				 newLine:true,
				   				 type:'textarea',
				   				 required:true
			   				}},
		]
	});
});
</script>
</head>
<body>
	<div id="id_table_csut_apply_list" style="height: 100%;"></div>
</body>
</html>