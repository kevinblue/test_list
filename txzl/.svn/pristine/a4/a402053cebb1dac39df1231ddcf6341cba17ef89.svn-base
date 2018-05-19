<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
	var showTools = true;
	if("${param.rollback}" == 1 || "${param.rollback}" == -1){
		showTools = false;
	}
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
				remoteOper:true,
				showPager:true,
				showToolbar: showTools,
				entityClassName:'com.tenwa.leasing.entity.voucher.InterV8Vouchers',
				params:{
					evidence_number:"${param.evidence_number}",
					rollback : "${param.rollback}"
				},
				xmlFileName : '/voucher/voucher_info_list.xml',
				tools : ['edit'],
				columns : [ 
				    {type : 'indexcolumn'},
				   	{type : 'checkcolumn'},  
				   	{field : 'id',header : 'id',visible : false},
				   	/*	{ field : 'evidencetype',header : '凭证基本类别',visible : false, formEditorConfig:{fieldVisible:false}},
				   	{field : 'dept_id',header : '所属公司编号',visible : false, formEditorConfig:{fieldVisible:false}},
				   	{field : 'dept_name',header : '所属公司名称',visible : false, formEditorConfig:{fieldVisible:false}},
				   	{field : 'periodyear',header : '年份',visible : false, formEditorConfig:{fieldVisible:false}},
				   	{field : 'f59',header : '序号',visible : false, formEditorConfig:{fieldVisible:false}}, */
				   	{field : 'happen_date',header : '记账日期'},				   	
				   	{field : 'recording_voucher',header : '凭证字',visible : false, formEditorConfig:{fieldVisible:false}},
				   	{field : 'evidence_number',header : '凭证号', formEditorConfig:{fieldVisible:false}},
				   	{field : 'evidence_summary',header : '摘要', 
				   		formEditorConfig:{
				   			colspan:3,
				   			width:'100%'
				   		}
				  	 	,width:350
				   	},
				   	{field : 'subjects_code',header : '科目编码', 
				   		formEditorConfig:{
				   			newLine:true
				   		}
				   		,width:270
				   	},
				   	{field : 'debit',header : '借方金额'},
				   	
				   	{field : 'credit',header : '贷方金额'},
				   	/* {field : 'f9',header : '数量',visible : false, formEditorConfig:{fieldVisible:false}},
				   	{field : 'f10',header : '外币',visible : false, formEditorConfig:{fieldVisible:false}}, */
				   	{field : 'ebank_fact_date',header : '到账日期', formEditorConfig:{fieldVisible:false}},
				   /* 	{field : 't_depts_uuid',header : '部门编码',visible : false, formEditorConfig:{fieldVisible:false}}, */
			//	   	{field : 'f18',header : '客户编码', formEditorConfig:{fieldVisible:false}},
					{field : 'client_id',header : '客户编码', formEditorConfig:{fieldVisible:false}},
				   	{field : 'vndr_id',header : '供应商编码',visible : false, formEditorConfig:{fieldVisible:false}},
				   	{field : 'acc_set_number',header : '帐套',visible : false, formEditorConfig:{fieldVisible:false}},
				   	{field : 'acc_year',header : '会计年度',visible : false, formEditorConfig:{fieldVisible:false}},
				   	{field : 'acc_month',header : '会计月份', formEditorConfig:{fieldVisible:false}},
				   	
				   	{field : 'subjects_name',header : '科目名称', formEditorConfig:{fieldVisible:false}},
				   	/* {field : 'f63',header : '部门名称',visible : false, formEditorConfig:{fieldVisible:false}}, */
				   /* 	{field : 'f66',header : '供应商名称',visible : false, formEditorConfig:{fieldVisible:false}}, */
				   	{field : 'inter_contract_id',header : '合同号', formEditorConfig:{fieldVisible:false}},
				   /* 	{field : 'f68',header : '项目大类编码',visible : false, formEditorConfig:{fieldVisible:false}},
				   	{field : 'f69',header : '项目大类名称',visible : false, formEditorConfig:{fieldVisible:false}}, */
				   	{field : 'voucher_status',header : '凭证状态',visible : false, formEditorConfig:{fieldVisible:false}},
				   	{field : 'exp_flag',header : '导出状态',visible : false, formEditorConfig:{fieldVisible:false}},
				   	{field:'rollback',header:'凭证状态',visible:true,formEditorConfig:{fieldVisible:false}}
				]
			});
		});
	});
</script>
</head>
<body></body>
</html>