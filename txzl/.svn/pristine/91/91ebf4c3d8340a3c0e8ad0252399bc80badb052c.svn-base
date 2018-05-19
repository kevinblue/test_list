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
	var userEnable2=[{id:'人员招聘',text:'人员招聘'},{id:'人员培训',text:'人员培训'},{id:'部门制度',text:'部门制度'}];
	var showTools=true;
	if("${requestScope['opertype']}"=='view'){showTools=false};
	jQuery(function() {		
		tenwa.createTable({
					id : "work_week_report8",
					renderTo : "id_table_work_week_report8",
					width : currentClientWidth,
					height :'100%',
					lazyLoad : false,
					title : "四、部门建设（部门领导）",
					showToolbar:showTools,
					remoteOper : false,
					tools : ['add','-','edit','-','remove'],
					queryButtonColSpan : 2,
					remoteOper:true,
					/* showPager : true, */
					allowCellWrap:true,
					/* pageSize : 5, */
					entityClassName:'com.tenwa.leasing.entity.cust.DepartmentBuildDetail',
					xmlFileName : '/eleasing/jsp/other/get_sum_department_build_detail_list.xml',
					params:{projstatus:'部门建设',startdate:"${requestScope['startdate']}",registerid:"${requestScope['registerid']}",saleweekid:"${requestScope['saleweekid']}"},
					columns : [
								
								 {type : 'indexcolumn'},
							/* {type : 'checkcolumn'},	 */		
								{field : 'id',header : 'id',visible: false,
									formEditorConfig:{
										readOnly:true,
										fieldVisible: false,
									}	
								},
								{field : 'projstatus',header : '阶段',visible: false,align:'center',
									formEditorConfig:{
										newLine:true,
										value:'部门建设',
										readOnly:true
									}	
								},
								{field : 'content',header : '内容',align:'center',width:'80',
									formEditorConfig:{
										type: 'combobox',
										data: userEnable2,
										required:true,
									}
								},
								{field : 'area',header : '区域',align:'center',
									formEditorConfig:{
										type:'text',										
									}
								},
								{field : 'contents',header : '内容详情',
									formEditorConfig:{
										type:'textarea',
										newLine:true,
										colspan:4,
										width:500,
										
									}
								},
								{field : 'saleweekid',header : '周报编号',visible: false,
									formEditorConfig:{
										readOnly:true,
										fieldVisible: false,
										defaultValue:"${requestScope['workid']}"
									}	
								}
								],
								onupdate:function(){
									var data = mini.get("work_week_report8").getData();
									var len = data.length;
									$("#id_table_work_week_report8").height(130+len*20);
									mini.get("id_panelContainer_work_week_report8").doLayout();
								}
				});
	});	
	
</script>

</head>
<body>
	<div id="id_table_work_week_report8">
	   
	</div>
		
</body>
</html>