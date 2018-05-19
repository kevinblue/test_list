<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>利息支付统计表</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyJsonUtil.js"></script>
<script type="text/javascript">
//状态下拉查询
jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id : 'table_id1',
				width : globalClientWidth,
				height : globalClientHeight,
				title : '利息支付统计表',
				iconCls : 'icon-node',
				multiSelect : true,
				hiddenQueryArea : true,//是否将查询区域折叠起来
				queryButtonColSpan : 6,
				queryButtonNewLine:true,
				showPager:true,
				params:{					
				},
				xmlFileName : '/eleasing/jsp/report/basereport/interest_payment.xml',
				tools : ['exportExcel'],
				/* exportComplexHeaders : [[ {
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
				]], */
				
				columns : [ 
				    {type : 'indexcolumn'},
					{type : 'checkcolumn'},
					{                         
						field : 'id',
						header : '编号',
						headerAlign : 'center',
						width : 100,
						allowSort : true,
						visible : false,
						formEditorConfig : {
							readOnly : true,
							fieldVisible : false
						}
					},
					{
						field : 'contractno',
						header : '合同号',
						headerAlign : 'center',
						width : 100,
						allowSort : true,
						visible : false,
                        queryConfig :{
                        	
                        	fieldVisible : true
                        }
						
					},
					{field : 'dateofinterest',header : '借款日', queryConfig:{
			              newLine:false,
			                width:200,
			                 type:'date'
			           }},
					{field : 'ceasedate',header : '结息日', queryConfig:{
			              newLine:false,
			                width:200,
			                 type:'date'
			           }},
					{field : 'balance',header : '本金（万元）'},
				 	{field : 'interestrate',header : '利率'},
					{field : 'interest',header : '利息费用(元)'}
					
				]
			});
		});
	});
</script>
</head>
<body></body>
</html>