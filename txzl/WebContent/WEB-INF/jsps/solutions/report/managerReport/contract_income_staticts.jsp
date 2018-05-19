<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>合同回收统计表</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyJsonUtil.js"></script>
<script type="text/javascript">
//状态下拉查询
var statusdata = [ {name : '未申请',value : '未申请'},{name : '已退回',value : '已退回'},{name : '已申请',value : "已申请"},{name : '全部',value : ''}];
jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id : 'table_id1',
				width : globalClientWidth,
				height : globalClientHeight,
				title : '合同本金回收统计表',
				iconCls : 'icon-node',
				hiddenQueryArea : true,//是否将查询区域折叠起来
				queryButtonColSpan : 2,
				queryButtonNewLine:false,
				showPager:true,
				params:{
					status:"未申请','已退回"
				},
				xmlFileName : '/eleasing/jsp/report/managerrepot/contract_income_staticts.xml',
				tools : ['exportExcel'],
				exportComplexHeaders : [[ /* {
					header : '&nbsp;',
					headerAlign : 'center',
					colspan : 6,
					rowspan : 1
					//startColNum : 2
				}, */ 
				{
					header : '编号',
					headerAlign : 'center',
					colspan : 1,
					rowspan : 2
				},{
					header : '业务合同号',
					headerAlign : 'center',
					colspan : 1,
					rowspan : 2
				},
				{
					header : '起租日',
					headerAlign : 'center',
					colspan : 1,
					rowspan : 2
				},
				{
					header : '承租人',
					headerAlign : 'center',
					colspan : 1,
					rowspan : 2
				},
				,
				{
					header : '当月情况',
					headerAlign : 'center',
					colspan : 2,
					rowspan : 1,
					startColNum : 5
				},
				{
					header : '累计应收',
					headerAlign : 'center',
					colspan : 1,
					rowspan : 2
				},
				{
					header : '累计实收',
					headerAlign : 'center',
					colspan : 1,
					rowspan : 2
				},
				{
					header : '正常回收',
					headerAlign : 'center',
					colspan : 1,
					rowspan : 2
				},
				{
					header : '提前回收',
					headerAlign : 'center',
					colspan : 1,
					rowspan : 2
				},
				{
					header : '积欠回收',
					headerAlign : 'center',
					colspan : 1,
					rowspan : 2
				},
				{
					header : '积欠未收',
					headerAlign : 'center',
					colspan : 1,
					rowspan : 2
				},
				{
					header : '当期回收率',
					headerAlign : 'center',
					colspan : 1,
					rowspan : 2
				},
				{
					header : '累计回收率',
					headerAlign : 'center',
					colspan : 1,
					rowspan : 2
				}
				]],
				
				columns : [ 
				    {type : 'indexcolumn'},
					{field : 'contractnumber',header : '业务合同号',queryConfig :{}},
					//mei
					{field : 'leasecommencementdate',header : '起租日'},
					{field : 'custname',header : '承租人',queryConfig :{}},
					
				/* 	{field : 'managername',header : '客户经理',queryConfig :{}},
					{field : 'contractid',header : '合同编号',queryConfig :{}},
					{field : 'deptname',header : '部门',queryConfig :{newLine:true,colspan:3}}, */
					
					{
						header : '当月情况',
						headerAlign : 'center',
						columns : [ 
						{
							field : 'm_plancorpus',
							header : '当月应收',
							headerAlign : 'center',
							width : 100,
							allowSort : true
						}, 
						{
							//没
							field : 'm_netreceipts',
							header : '当月实收',
							headerAlign : 'center',
							width : 100,
							allowSort : true
						}
						
						]
					},
					{
						field : 'a_plancorpus',
						header : '累计应收',
						headerAlign : 'center',
						width : 100,
						allowSort : true
					}, 
					{
						field : 'a_netreceipts',
						header : '累计实收',
						headerAlign : 'center',
						width : 100,
						allowSort : true
					}, 
					/* {
						field : 'contract_plancorpus',
						header : '合同应收',
						headerAlign : 'center',
						width : 100,
						allowSort : true,
					}, */
					{
						field : 'm_incomecorpus',
						header : '正常回收',
						headerAlign : 'center',
						width : 100,
						allowSort : true,
					}, 
					{
						field : 'after_incomecorpus',
						header : '提前回收',
						headerAlign : 'center',
						width : 100,
						allowSort : true,
					},
					{
						field : 'before_incomecorpus',
						header : '积欠回收',
						headerAlign : 'center',
						width : 100,
						allowSort : true,
					},
					{
						field : 'no_incomecorpus',
						header : '积欠未收',
						headerAlign : 'center',
						width : 100,
						allowSort : true,
					},
					
					{
						field : 'm_rate',
						header : '当期回收率',
						headerAlign : 'center',
						width : 100,
						allowSort : true,
					},
					{
						//mei
						field : 't_rate',
						header : '累计回收率',
						headerAlign : 'center',
						width : 100,
						allowSort : true,
					}
				]
			});
		});
	});
</script>
</head>
<body></body>
</html>