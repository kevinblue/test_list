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
				title : '合同回收统计表',
				iconCls : 'icon-node',
				multiSelect : true,
				hiddenQueryArea : true,//是否将查询区域折叠起来
				queryButtonColSpan : 2,
				queryButtonNewLine:false,
				showPager:true,
				params:{
					status:"未申请','已退回"
				},
				xmlFileName : '/eleasing/jsp/report/managerrepot/contract_income_staticts.xml',
				tools : ['exportExcel'],
				exportComplexHeaders : [[ {
					header : '&nbsp;',
					headerAlign : 'center',
					colspan : 6,
					rowspan : 1
					//startColNum : 2
				}, {
					header : '当月情况',
					headerAlign : 'center',
					colspan : 5,
					rowspan : 1
				}
				]],
				
				columns : [ 
				    {type : 'indexcolumn'},
					{type : 'checkcolumn'},
					{field : 'managername',header : '客户经理',queryConfig :{}},
					{field : 'contractputnumber',header : '投放编号',queryConfig :{}},
					/* {field : 'contractid',header : '合同编号',queryConfig :{}},
					{field : 'contractnumber',header : '业务合同号',queryConfig :{}},*/
					{field : 'deptname',header : '部门',queryConfig :{newLine:true,colspan:3}},
					{field : 'custname',header : '承租人'},
					{
						header : '当月情况',
						headerAlign : 'center',
						columns : [ 
						{
							field : 'm_plancorpus',
							header : '当期应收',
							headerAlign : 'center',
							width : 100,
							allowSort : true
						}, 
						{
							field : 'contract_plancorpus',
							header : '合同应收',
							headerAlign : 'center',
							width : 100,
							allowSort : true,
						},
						{
							field : 'm_incomecorpus',
							header : '正常回收',
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
							field : 'after_incomecorpus',
							header : '提前回收',
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