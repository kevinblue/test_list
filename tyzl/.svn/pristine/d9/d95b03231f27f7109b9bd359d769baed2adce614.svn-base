<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>凭证明细信息</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript">
jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id : 'table_id1',
				width : globalClientWidth,
				height : globalClientHeight,
				title : '凭证明细信息',
				iconCls : 'icon-node',
				multiSelect : true,
				hiddenQueryArea : true,//是否将查询区域折叠起来
				queryButtonColSpan : 2,
				queryButtonNewLine:false,
				showPager:true,
				params:{
					vouchernumber:"<mini:param  name='vouchernumber'/>"
				},
				xmlFileName : '/voucher/voucher_info_list.xml',
				tools : [],
				columns : [ 
				    {type : 'indexcolumn'},
				   	{type : 'checkcolumn'},  
				   	{field : 'id',header : 'id',visible : false},
				   	{field : 'evidencetype',header : '凭证基本类别'},
				   	{field : 'dept_id',header : '所属公司编号'},
				   	{field : 'dept_name',header : '所属公司名称'},
				   	{field : 'periodyear',header : '年份'},
				   	{field : 'f1',header : '记账日期'},
				   	
				   	{field : 'f2',header : '凭证字'},
				   	{field : 'vouchernumber',header : '凭证号'},
				   	{field : 'f5',header : '摘要'},
				   	{field : 'subjects_code',header : '科目编码'},
				   	{field : 'f7',header : '借方金额'},
				   	
				   	{field : 'f8',header : '贷方金额'},
				   	{field : 'f9',header : '数量'},
				   	{field : 'f10',header : '外币'},
				   	{field : 'f15',header : '到账日期'},
				   	{field : 't_depts_uuid',header : '部门编码'},
				   	
				   	{field : 'f18',header : '客户编码'},
				   	{field : 'f19',header : '供应商编码'},
				   	{field : 'f49',header : '帐套'},
				   	{field : 'f51',header : '会计年度'},
				   	{field : 'f52',header : '会计月份'},
				   	
				   	{field : 'f59',header : '序号'},
				   	{field : 'subjects_name',header : '科目名称'},
				   	{field : 'f63',header : '部门名称'},
				   	{field : 'f66',header : '供应商名称'},
				   	{field : 'contract_number',header : '合同号'},
				   	
				   	{field : 'f68',header : '项目大类编码'},
				   	{field : 'f69',header : '项目大类名称'},
				   	{field : 'status_',header : '凭证状态'},
				   	{field : 'v8flag',header : '导出状态'}
				]
			});
		});
	});
</script>
</head>
<body></body>
</html>