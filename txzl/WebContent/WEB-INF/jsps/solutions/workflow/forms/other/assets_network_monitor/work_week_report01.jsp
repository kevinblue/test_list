<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script type="text/javascript">
</script>
<title>工作周报表</title>
<%@include file="/base/mini.jsp"%>
<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/tracywindy/workflow.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/tracywindy/workFlowUtil.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
</head>
<body style="overflow: auto;">
<!-- 撑满布局 -->
<input class="mini-textbox" name="workid" value="${requestScope['workid'] }" style="display:none"/>
<div id="id_table_application_detail">
	<div id="mini-panel" title="基本信息" showCollapseButton="true" style="width: 100%;" >
		<table class="fillTable" cellspacing="0" cellpadding="0" style="width:100%;" id="application_base_info">
			<tr>
		        <td style="padding:5px">
		        	<a class="mini-button" iconCls="icon-save" onclick="saveApplication">保存</a>
		        	<span class="separator"></span>
		        	<a class="mini-button" iconCls="icon-close" onclick="onCancel">关闭</a>
		        </td>
		    </tr>
		</table>
		<table class="fillTable" cellspacing="0" cellpadding="0" style="width:100%;" >   
		    <tr style="background:#468CC8;height:32px" ><td style="padding-left:10px" colspan='4'><font color="white">申请基本信息</font></td></tr>
		    <tr class="tr-odd">
		      	<td class="td-content-title" width="12%">填写人：</td>
		        <td class="td-content" width="38%">
 		        	 <input width="55%" name="registername" class="mini-textbox" required="true" readonly="readonly" value="${requestScope['registername']}"/>
 		        </td> 
		        <td class="td-content-title" width="12%">报表流水号：</td>
		        <td class="td-content" width="38%">
		        	<input width="55%" name="serialid" class="mini-textbox" required="true" readonly="readonly" value="${requestScope['serialid']}"/>
		        </td>
		     </tr>
		     <tr class="tr-odd">
		        <td class="td-content-title" width="12%" >本周开始时间：</td>
		        <td class="td-content" width="38%">
					<input width="55%" name="startdate" class="mini-textbox" type="date" readonly="readonly" format="yyyy-MM-dd" required="true" allowinput="false" value="${requestScope['startdate']}" />
		        </td>
		         </td>
		        <td class="td-content-title" width="12%" >本周结束时间：</td>
		        <td class="td-content" width="38%">
					<input width="55%" name="enddate" class="mini-textbox" readonly="readonly" type="date" format="yyyy-MM-dd" required="true" allowinput="false" value="${requestScope['enddate']}" />
		        </td>
		    </tr>
		</table>
	</div>
	</div>
<!-- 页签 -->	
	<div class="mini-panel" title="本周工作计划" name="last_work_week_report_detail" style="width: 100%;height:235px" iconCls="icon-cut" >
	    <jsp:include page="last_work_week_report_detail.jsp" ></jsp:include>
	</div>
	<div class="mini-panel" title="本周工作完成情况" name="work_week_report_detail" style="width: 100%;height:265px" iconCls="icon-cut" >
	    <jsp:include page="work_week_report_detail.jsp" ><jsp:param value="true" name="isView"/></jsp:include>
	</div>
	<div class="mini-panel" title="下周工作计划情况" name="next_work_week_report_detail" style="width: 100%;height:265px" iconCls="icon-cut" >
	    <jsp:include page="next_work_week_report_detail.jsp" ><jsp:param value="true" name="isView"/></jsp:include>
	</div>


<script type="text/javascript">


//初始化
mini.parse("id_table_application_detail");
var form = new mini.Form("id_table_application_detail");



function saveApplication() {
	var params={};
	var workweek=JsonUtil.encode(mini.get("datagrid1").getData());
	var workweekone=JsonUtil.encode(mini.get("datagrid2").getData());
	var workid=mini.getbyName("workid").getValue();
		params["workweek"]=workweek;
		params["workid"]=workid;
		params["workweekone"]=workweekone;
		$.ajax({
			url:getRootPath()+"/acl/updWorkWeekReportlication.acl",
			data:params,
			type:'post',
			async:false,
			success:function(e){
				var result=mini.decode(e);
				if(result.flag=='true'){
					 mini.confirm(result.msg,"确认",function(action){
						 if(action=='ok'){
							 onCancel();
						 }else{
							 onCancel();
						 }
					 });
				}else{
					mini.alert('发生异常：'+result.msg);
				}
			}
		});	
} 


</script>
</body>
</html>