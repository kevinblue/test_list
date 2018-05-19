<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>工作周报表</title>
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
    var startdate=${requestScope['startdate']};
    var registerid="${requestScope['registerid']}";
    var saleweekid="${requestScope['saleweekid']}";
	var currentClientWidth = document.documentElement.clientWidth-1;
	var showTools=true;
	if("${requestScope['opertype']}"=='view'){showTools=false};
	jQuery(function() {		
		tenwa.createTable({
					id : "work_week_report7",
					renderTo : "id_table_work_week_report7",
					width : currentClientWidth,
					height :305,
					lazyLoad : false,
					title : "三、问题及建议",
					showToolbar:showTools,
					remoteOper : false,
					tools : ['add','-','edit','-','remove'],
					queryButtonColSpan : 2,
					remoteOper:true,
					allowCellWrap:true,
					showPager : true,
					pageSize : 5,
					entityClassName:'com.tenwa.leasing.entity.cust.ProblemAdviceDetail',
					xmlFileName : '/eleasing/jsp/other/get_problem_advice_detail_list.xml',
					params:{projstatus:'问题及建议',startdate:"${requestScope['startdate']}",registerid:"${requestScope['registerid']}",saleweekid:"${requestScope['saleweekid']}"},
					columns : [							
							{type : 'checkcolumn'},		
							{field : 'id',header : 'id',visible: false,
								formEditorConfig:{
									readOnly:true,
									fieldVisible: false,
								}	
							},
							{field : 'projstatus',header : '阶段',visible: false,
								formEditorConfig:{
									newLine:true,
									value:'问题及建议',
									readOnly:true
								}	
							},							
							{field : 'problem',header : '问题',
								formEditorConfig:{
									type:'textarea',
									newLine:true,
									width:400,
									lableWidth:300
								}
							},
							{field : 'advice',header : '建议',
								formEditorConfig:{
									type:'textarea',
									newLine:true,
									width:400,
									lableWidth:300,
									
								}
							},
							{field : 'remarks',header : '备注',
								formEditorConfig:{
									type:'textarea',
									newLine:true,
									width:400,								
									lableWidth:300,
									
								}
							},
							{field : 'saleweekid',header : '周报编号',visible: false,
								formEditorConfig:{
									readOnly:true,
									fieldVisible: false,
									defaultValue:"${requestScope['workid']}"
								}	
							}
							]
				});
	});	
	
</script>

</head>
<body>
	<div id="id_table_work_week_report7"></div>
	
</body>
</html>