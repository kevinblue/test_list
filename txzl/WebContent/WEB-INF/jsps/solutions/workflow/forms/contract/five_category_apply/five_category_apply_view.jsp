<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<%
	String applyid = request.getParameter("applyid");//ID
	String opertype = request.getParameter("opertype");
%>
<script type="text/javascript">
var applyid='<%=applyid%>'
	var applydate="";
</script>
<title>五级分类申请</title>
<%@include file="/base/mini.jsp"%>
<link
	href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/css/tracywindy/workflow.css"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath}/css/tracywindy/workFlowUtil.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
</head>
<body style="overflow: auto;">
	<!-- 撑满布局 -->
	<div id="id_table_application_detail">
		<div id="mini-panel" title="申请基本信息" showCollapseButton="true" style="width: 100%;">
			<table class="fillTable" cellspacing="0" cellpadding="0" style="width:100%;" id="application_base_info">
			<tr>
		        <td style="padding:5px">
		        	<a class="mini-button" iconCls="icon-close" onclick="onCancel">关闭</a>
		        </td>
		    </tr>
		</table>
			<table class="fillTable" cellspacing="0" cellpadding="0"
				style="width: 100%;">
				<tr style="background: #468CC8; height: 32px">
					<td style="padding-left: 10px" colspan='4'><font color="white">申请基本信息</font></td>
				</tr>
				<tr class="tr-odd">
					<td class="td-content-title" width="12%">申请人：</td>
					<td class="td-content" width="38%"><input width="55%"
						name="applyusername" class="mini-textbox " type='text'
						required="true" readonly="readonly"
						value="${requestScope['applyusername']}" /></td>
					<td class="td-content-title" width="12%">申请年月：</td>
					<td class="td-content" width="38%"><input width="55%"
						name="applydate" class="mini-datepicker" type="date"
						format="yyyy-MM" readonly required="true" allowinput="false"
						value="${requestScope['applydate']}" /></td>
				</tr>
				<tr class="tr-even">
					<td class="td-content-title" width="12%">申请状态：</td>
					<td class="td-content" width="38%"><input width="55%"
						name="applystatus" class="mini-textbox" required="true"
						readonly="readonly"
						value="${empty requestScope['applystatus']?'未审核': requestScope['applystatus']}" />
					</td>
					<td class="td-content-title" width="12%" >申请编号</td>
		        	<td class="td-content" width="38%">
		            <input width="55%" name="pid"  style="display:none" class="mini-textbox" required="true" readonly="readonly" value="${requestScope['pid']}"/>
		        	<input width="55%" name="applyid"  class="mini-textbox" required="true" readonly="readonly" value="${requestScope['applyid']}"/>
		        	</td>
				</tr>
				<tr class="tr-odd" style="height: 40px">
					<td class="td-content-title" width="12%" height="70px">备注：</td>
					<td class="td-content" colspan="3"><textarea
							style="width: 81%; height: 90%" name="applymemo" readonly
							class="mini-textarea" value="${requestScope['applymemo']}"></textarea>
					</td>
				</tr>
				<tr class="tr-even">
					<td class="td-content-title" width="12%">创建人：</td>
					<td class="td-content" width="38%"><input width="55%"
						name="creatornamex" class="mini-textbox" readonly
						value="${requestScope['creatornamex']}" /></td>
					<td class="td-content-title" width="12%">创建时间：</td>
					<td class="td-content" width="38%"><input width="55%"
						name="createdate" class="mini-textbox" readonly
						value="${requestScope['createdate']}" "/></td>
				</tr>
				<tr class="tr-odd">
					<td class="td-content-title" width="12%">修改人：</td>
					<td class="td-content" width="38%"><input width="55%"
						name="modificator" class="mini-textbox" readonly
						value="${requestScope['modificator']}" /></td>
					<td class="td-content-title" width="12%">修改时间：</td>
					<td class="td-content" width="38%"><input width="55%"
						name="modifydate" class="mini-textbox" readonly
						value="${requestScope['modifydate']}" /></td>
				</tr>
			</table>
		</div>
	</div>
	<!-- 页签 -->
	<div id="tabDeatils" class="mini-tabs" activeIndex="0"
		style="width: 100%;" bodyStyle="padding:0;border:0;">
		<div title="申请详细信息" name="applicationcust">
			<jsp:include page="five_category_apply_detail.jsp"><jsp:param
					value="true" name="isView" /></jsp:include>
		</div>
	</div>


	<script type="text/javascript">
	function onCancel(){
		window.opener.location.href=window.opener.location.href;
		window.close();
	}
		//初始化
		mini.parse("id_table_application_detail");
		var form = new mini.Form("id_table_application_detail");
		jQuery(function() {
			$(".mini-textbox-readOnly .mini-textbox-border").css("background",
					"#F0F0F0");
			$(".mini-buttonedit-readOnly .mini-buttonedit-border").css(
					"background", "#F0F0F0");
			$(".mini-buttonedit-readOnly .mini-buttonedit-button").css(
					"display", "none");
		});
	</script>
</body>
</html>