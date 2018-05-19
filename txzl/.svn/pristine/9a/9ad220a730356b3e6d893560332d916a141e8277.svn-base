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
	var userEnable=[{id:'本周',text:'本周'},{id:'下周',text:'下周'}];
	var showTools=true;
	if("${requestScope['opertype']}"=='view'){showTools=false};
	jQuery(function() {		
		tenwa.createTable({
					id : "work_week_report6",
					renderTo : "id_table_work_week_report6",
					width : currentClientWidth,
					height :'100%',
					lazyLoad : false,
					title : "二、渠道管理",
					showToolbar:showTools,
					remoteOper : false,
					tools : ['add','-','edit','-','remove'],
					queryButtonColSpan : 2,
					remoteOper:true,
					allowCellWrap:true,
					/* showPager : true,
					pageSize : 5, */
					entityClassName:'com.tenwa.leasing.entity.cust.ChannelAdministrationDetail',
					xmlFileName : '/eleasing/jsp/other/get_admin_channel_administration_detail_list.xml',
					params:{projstatus:'渠道',startdate:"${requestScope['startdate']}",registerid:"${requestScope['registerid']}",saleweekid:"${requestScope['saleweekid']}"},
					columns : [
                            {type : 'indexcolumn'},
							/* {type : 'checkcolumn'},	 */	
							{field : 'id',header : 'id',visible: false,
								formEditorConfig:{
									readOnly:true,
									fieldVisible: false,
								}	
							},
							{field : 'projstatus',header : '阶段',visible: false,
								formEditorConfig:{
									newLine:true,
									value:'渠道',
									readOnly:true
								}	
							},
							{field : 'area',header : '区域',
								formEditorConfig:{
									type:'text'
								}
							},
							
							{field : 'company',header : '公司',
								formEditorConfig:{
									type:'text'
								}
							},
							{field : 'name',header : '姓名',
								formEditorConfig:{
									type:'text',
									newLine:true,
								}
							},
							{field : 'contactinformation',header : '联系方式',
								formEditorConfig:{
									type:'text'
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
								var data = mini.get("work_week_report6").getData();
								var len = data.length;
								$("#id_table_work_week_report6").height(150+len*20);									
								mini.get("id_panelContainer_work_week_report6").doLayout();
							}
				});
	});	
	
</script>

</head>
<body>
	<div id="id_table_work_week_report6"></div>
	
</body>
</html>