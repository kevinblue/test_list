<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户应收账龄表</title>
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
				title : '客户应收账龄表',
				iconCls : 'icon-node',
				multiSelect : true,
				hiddenQueryArea : true,//是否将查询区域折叠起来
				queryButtonColSpan : 2,
				queryButtonNewLine:false,
				showPager:true,
				params:{
					status:"未申请','已退回"
				},
				xmlFileName : '/eleasing/jsp/report/custreceivereport/cust_bill_plan.xml',		
				tools : ['exportExcel'],
				exportComplexHeaders : [[ {
					header : '编号',
					headerAlign : 'center',
					rowspan : 2
				},
				{
					header : '客户编号',
					headerAlign : 'center',
					rowspan : 2
				}, 
				{
					header : '客户名称',
					headerAlign : 'center',
					rowspan : 2
				}, 
				 {
					header : '30天内应收',
					headerAlign : 'center',
					colspan : 5,
					rowspan : 1
				}, {
					header : '30-90天内应收',
					headerAlign : 'center',
					colspan : 5,
					rowspan : 1
				}, {
					header : '90-180天内应收',
					headerAlign : 'center',
					colspan : 5,
					rowspan : 1
				}, {
					header : '180-360天内应收',
					headerAlign : 'center',
					colspan : 5,
					rowspan : 1
				}, {
					header : '360天以上应收',
					headerAlign : 'center',
					colspan : 5,
					rowspan : 1
				}, {
					header : '合计',
					headerAlign : 'center',
					colspan : 5,
					rowspan : 1
				}
				]],
				
				columns : [ 
				    {type : 'indexcolumn'},
					{field : 'custnumber',header : '客户编号',queryConfig :{}},
					{field : 'custname',header : '客户名称',queryConfig :{}},
					{
						header : '30天内应收',
						headerAlign : 'center',
						columns : [ 
						       	{
									field : 'charge30',
									header : '手续费',
									headerAlign : 'center',
									width : 100,
									allowSort : true,
									vtype : 'email',//验证值的类型
									align:'right',
									summary:true,
									dataType:"currency"
								}, 
								{
									field : 'guarantee30',
									header : '保证金',
									headerAlign : 'center',
									width : 100,
									allowSort : true,
									vtype : 'email',//验证值的类型
									align:'right',
									summary:true,
									dataType:"currency"
								}, 
						{
							field : 'rent30',
							header : '租金',
							headerAlign : 'center',
							width : 100,
							allowSort : true,
							vtype : 'email',//验证值的类型
							align:'right',
							summary:true,
							dataType:"currency"
						}, 
						{
							field : 'corpus30',
							header : '本金',
							headerAlign : 'center',
							width : 100,
							allowSort : true,
							align:'right',
							summary:true,
							dataType:"currency"
						},
						{
							field : 'interest30',
							header : '利息',
							headerAlign : 'center',
							width : 100,
							allowSort : true,
							align:'right',
							summary:true,
							dataType:"currency"
						}
						]
					},
					{
						header : '30-90天内应收',
						headerAlign : 'center',
						columns : [ 
						          	{
										field : 'charge90',
										header : '手续费',
										headerAlign : 'center',
										width : 100,
										allowSort : true,
										vtype : 'email',//验证值的类型
										align:'right',
										summary:true,
										dataType:"currency"
									}, 
									{
										field : 'guarantee90',
										header : '保证金',
										headerAlign : 'center',
										width : 100,
										allowSort : true,
										vtype : 'email',//验证值的类型
										align:'right',
										summary:true,
										dataType:"currency"
									}, 
						{
							field : 'rent90',
							header : '租金',
							headerAlign : 'center',
							width : 100,
							allowSort : true,
							vtype : 'email',//验证值的类型
							align:'right',
							summary:true,
							dataType:"currency"
						}, 
						{
							field : 'corpus90',
							header : '本金',
							headerAlign : 'center',
							width : 100,
							allowSort : true,
							align:'right',
							summary:true,
							dataType:"currency"
						},
						{
							field : 'interest90',
							header : '利息',
							headerAlign : 'center',
							width : 100,
							allowSort : true,
							align:'right',
							summary:true,
							dataType:"currency"
						}
						]
					},
					{
						header : '90-180天内应收',
						headerAlign : 'center',
						columns : [ 
						          	{
										field : 'charge180',
										header : '手续费',
										headerAlign : 'center',
										width : 100,
										allowSort : true,
										vtype : 'email',//验证值的类型
										align:'right',
										summary:true,
										dataType:"currency"
									}, 
									{
										field : 'guarantee180',
										header : '保证金',
										headerAlign : 'center',
										width : 100,
										allowSort : true,
										vtype : 'email',//验证值的类型
										align:'right',
										summary:true,
										dataType:"currency"
									}, 
						{
							field : 'rent180',
							header : '租金',
							headerAlign : 'center',
							width : 100,
							allowSort : true,
							vtype : 'email',//验证值的类型
							align:'right',
							summary:true,
							dataType:"currency"
						}, 
						{
							field : 'corpus180',
							header : '本金',
							headerAlign : 'center',
							width : 100,
							allowSort : true,
							align:'right',
							summary:true,
							dataType:"currency"
						},
						{
							field : 'interest180',
							header : '利息',
							headerAlign : 'center',
							width : 100,
							allowSort : true,
							align:'right',
							summary:true,
							dataType:"currency"
						}
						]
					},
					{
						header : '180-360天内应收',
						headerAlign : 'center',
						columns : [ 
						          	{
										field : 'charge360',
										header : '手续费',
										headerAlign : 'center',
										width : 100,
										allowSort : true,
										vtype : 'email',//验证值的类型
										align:'right',
										summary:true,
										dataType:"currency"
									}, 
									{
										field : 'guarantee360',
										header : '保证金',
										headerAlign : 'center',
										width : 100,
										allowSort : true,
										vtype : 'email',//验证值的类型
										align:'right',
										summary:true,
										dataType:"currency"
									}, 
						{
							field : 'rent360',
							header : '租金',
							headerAlign : 'center',
							width : 100,
							allowSort : true,
							vtype : 'email',//验证值的类型
							align:'right',
							summary:true,
							dataType:"currency"
						}, 
						{
							field : 'corpus360',
							header : '本金',
							headerAlign : 'center',
							width : 100,
							allowSort : true,
							align:'right',
							summary:true,
							dataType:"currency"
						},
						{
							field : 'interest360',
							header : '利息',
							headerAlign : 'center',
							width : 100,
							allowSort : true,
							align:'right',
							summary:true,
							dataType:"currency"
						}
						]
					},
					{
						header : '360天以上应收',
						headerAlign : 'center',
					columns : [ 
							{
								field : 'thancharge360',
								header : '手续费',
								headerAlign : 'center',
								width : 100,
								allowSort : true,
								vtype : 'email',//验证值的类型
								align:'right',
								summary:true,
								dataType:"currency"
							}, 
							{
								field : 'thanguarantee360',
								header : '保证金',
								headerAlign : 'center',
								width : 100,
								allowSort : true,
								vtype : 'email',//验证值的类型
								align:'right',
								summary:true,
								dataType:"currency"
							}, 
						{
							field : 'thanrent360',
							header : '租金',
							headerAlign : 'center',
							width : 100,
							allowSort : true,
							vtype : 'email',//验证值的类型
							align:'right',
							summary:true,
							dataType:"currency"
						}, 
						{
							field : 'thancorpus360',
							header : '本金',
							headerAlign : 'center',
							width : 100,
							allowSort : true,
							align:'right',
							summary:true,
							dataType:"currency"
						},
						{
							field : 'thaninterest360',
							header : '利息',
							headerAlign : 'center',
							width : 100,
							allowSort : true,
							align:'right',
							summary:true,
							dataType:"currency"
						}
						]
					},
					{
						header : '合计',
						headerAlign : 'center',
						columns : [ 
				       	{
							field : 'allcharge',
							header : '手续费',
							headerAlign : 'center',
							width : 100,
							allowSort : true,
							vtype : 'email',//验证值的类型
							align:'right',
							summary:true,
							dataType:"currency"
						}, 
						{
							field : 'allguarantee',
							header : '保证金',
							headerAlign : 'center',
							width : 100,
							allowSort : true,
							vtype : 'email',//验证值的类型
							align:'right',
							summary:true,
							dataType:"currency"
						},
						{
							field : 'allrent',
							header : '租金',
							headerAlign : 'center',
							width : 100,
							allowSort : true,
							vtype : 'email',//验证值的类型
							align:'right',
							summary:true,
							dataType:"currency"
						}, 
						{
							field : 'allcorpus',
							header : '本金',
							headerAlign : 'center',
							width : 100,
							allowSort : true,
							align:'right',
							summary:true,
							dataType:"currency"
						},
						{
							field : 'allinterest',
							header : '利息',
							headerAlign : 'center',
							width : 100,
							allowSort : true,
							align:'right',
							summary:true,
							dataType:"currency"
						}
						]
					}
				]
			});
		});
	});
</script>
</head>
<body></body>
</html>