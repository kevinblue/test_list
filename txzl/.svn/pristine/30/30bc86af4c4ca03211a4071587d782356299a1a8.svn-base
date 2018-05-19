<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script type="text/javascript">
</script>
<title>周报汇总</title>
<%@include file="/base/mini.jsp"%>
<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/tracywindy/workflow.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/tracywindy/workFlowUtil.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
</head>
<body>
<div style="width:100%;height:100%;overflow: auto;">
<!-- 撑满布局 -->
<input class="mini-textbox" name="workid" value="${requestScope['workid'] }" style="display:none"/>
<input class="mini-textbox" name="opertype" value="${requestScope['opertype'] }" style="display:none"/>
<input class="mini-textbox" name="registerid" value="${requestScope['registerid'] }" style="display:none"/>
<div id="id_table_application_detail">
	<div id="mini-panel" title="基本信息" showCollapseButton="true" style="width: 100%;" >
	<table class="fillTable" cellspacing="0" cellpadding="0" style="width:100%;" id="application_base_info">
			<tr>
		        <td style="padding:5px">        	
		        	<a class="mini-button" iconCls="icon-close" onclick="onCancel">关闭</a>
		        </td>
		    </tr>
		</table>
		<table class="fillTable" cellspacing="0" cellpadding="0" style="width:100%;" >   
		    <tr style="background:#468CC8;height:32px" ><td style="padding-left:10px" colspan='4'><font color="white">基本信息</font></td></tr>
		    <tr class="tr-odd">
		        <td class="td-content-title" width="12%">编号：</td>
		        <td class="td-content" width="38%">${requestScope['serialid']}</td>
		      	<td class="td-content-title" width="12%">填写人：</td>
		        <td class="td-content" width="38%">${requestScope['registername']}</td> 
		     </tr>
		     <tr class="tr-odd">
		        <td class="td-content-title" width="12%" >业务部：</td>
		        <td class="td-content" width="38%">${requestScope['deptname']}</td>
		        <td class="td-content-title" width="12%" >业务子部：</td>
		        <td class="td-content" width="38%">${requestScope['childdeptname']}</td>
		    </tr>
		     <tr class="tr-odd">
		        <td class="td-content-title" width="12%" >填写周期：</td>
		        <td class="td-content" width="38%">${requestScope['startdate']}至${requestScope['enddate']}</td>
		         </td>
		        <td class="td-content-title" width="12%" >填写状态：</td>
		        <td class="td-content" width="38%">${requestScope['weekstyle']}</td>
		    </tr>
		</table>
	</div>
	</div> 
<!-- 页签 -->	
	 <div class="mini-panel" title="一、项目管理" id="lastone" name="lastone" style="width: 100%;" iconCls="icon-cut" >	     
	     <jsp:include page="admin_first_sale_week_detail.jsp" ></jsp:include>
	     <jsp:include page="admin_second_sale_week_detail.jsp" ></jsp:include>
	     <jsp:include page="admin_third_sale_week_detail.jsp" ></jsp:include> 
	     <jsp:include page="admin_fourth_sale_week_detail.jsp" ></jsp:include>
	     <jsp:include page="admin_five_sale_week_detail.jsp" ></jsp:include>
	     <jsp:include page="admin_six_sale_week_detail.jsp" ></jsp:include>  
	     <jsp:include page="admin_channel_administration_detail.jsp" ></jsp:include>	   
	     <jsp:include page="admin_problem_advice_detail.jsp" ></jsp:include>
	     <jsp:include page="admin_department_build_detail.jsp" ></jsp:include> 
	 </div>
</div>
</body>
</html>