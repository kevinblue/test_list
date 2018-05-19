<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>调息流程</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
jQuery(function() {
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
		id:'table1',
		renderTo:"id_table_render_table1",
		width:globalClientWidth,
		height:globalClientHeight,
		title:'调息撤销流程', 
		queryButtonColSpan:2,
		showPager:true, 
		lazyLoad:false, 
		xmlFileName:'/eleasing/jsp/base_rate/base_rate_manager_info.xml', 
		tools:[  {
			html:'调息撤销流程',
			plain:true,
			iconCls:'icon-addfolder',
			handler:function(miniTable, buttonText) {
				var row = miniTable.getSelected();
				if(row){
					var attachmentParams = "id="+row.id;
	        		startProcess("调息撤销流程-1",attachmentParams); 
				}else{
					mini.alert('请你选择需要发起调息撤销流程的开始执行时间！！！');
				}
			
			}
		}       ],
		columns:[ 
		    {type:'indexcolumn'}, 
		    {type:'checkcolumn'},
		    {field:'id', header:'记录编号', visible:false} ,
		    {field:'startdate',header:'利率开始执行 时间',dateFormat:"yyyy-MM-dd"},
		    {header:'调息是否完成',field:'isclose'},
		    {header:'利息调整幅度_六月', field:'ratehalf'},			    
		    {header:'利息央行基准_六月', field:'baseratehalf'}, 
		    {header:'利息调整幅度_1年', field:'rateone'}, 
		    {header:'利息央行基准_1年',field:'baserateone'}, 
		    {header:'利息调整幅度_3年', field:'ratethree'}, 
		    {header:'利息央行基准_3年', field:'baseratethree'}, 
		    {header:'利息调整幅度_5年',field:'ratefive'}, 
		    {header:'利息央行基准_5年', field:'baseratefive'}, 
		    {header:'利息调整幅度_5年以上', field:'rateabovefive'}, 
		    {header:'利息央行基准_5年以上', field:'baserateabovefive'}
		]
	});
});
});
</script>
</head>
<body>
	<div id="mini_test_form">
	        <div id="id_table_render_table1"></div>
	</div>
</body>
</html>