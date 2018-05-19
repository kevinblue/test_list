<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>资金预实月报</title>
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
<body>
	<div style="width: 100%; height: 100%; overflow: auto;">
		<!-- 撑满布局 -->
		<input class="mini-textbox" name="id" value="${requestScope['id'] }"
			style="display: none" /> <input class="mini-textbox" name="opertype"
			value="${requestScope['opertype'] }" style="display: none" /> <input
			class="mini-textbox" name="tuid" value="${requestScope['tuid'] }"
			style="display: none" />

		<div id="contract_approval_form">
			<div id="mini-panel" title="基本信息" showCollapseButton="true"
				style="width: 100%;">
				<table class="fillTable" cellspacing="0" cellpadding="0"
					style="width: 100%;" id="application_base_info">
					<tr>
						<td style="padding: 5px"><a class="mini-button"
							iconCls="icon-close" onclick="onCancel">关闭</a></td>
					</tr>
				</table>
				<table class="fillTable" cellspacing="0" cellpadding="0"
					style="width: 100%;">
					<tr style="background: #468CC8; height: 32px">
						<td style="padding-left: 10px" colspan='4'><font
							color="white">基本信息</font></td>
					</tr>
					<tr class="tr-odd">
						<td class="td-content-title" width="12%">流水号：</td>
						<td class="td-content" width="38%">${requestScope['sn']}</td>
						<td class="td-content-title" width="12%">填写人：</td>
						<td class="td-content" width="38%">${requestScope['creator']}</td>
					</tr>
					<tr class="tr-odd">
						<td class="td-content-title" width="12%">月份：</td>
						<td class="td-content" width="38%">${requestScope['didate']}</td>
						<td class="td-content-title" width="12%">填写状态：</td>
						<td class="td-content" width="38%">${requestScope['reportstyle']}</td>
					</tr>
					<tr class="tr-odd">
						<td class="td-content-title" width="12%">月初账户余额：</td>
						<td class="td-content" width="38%">${requestScope['accountbalance']}</td>
						<td class="td-content-title" width="12%">月初存款余额：</td>
						<td class="td-content" width="38%">${requestScope['balancedeposit']}</td>
					</tr>
					<tr class="tr-odd">
						<td class="td-content-title">备注：</td>
						<td colspan="5" class="miniext-form-fit">${requestScope['note']}
						</td>
					</tr>
					<tr class="tr-odd" style="display:none">
						<td class="td-content-title" width="12%">主表ID：</td>
						<td class="td-content" width="38%">${requestScope['id']}</td>
					</tr>
				</table>
			</div>
		</div>
		<div id="tabDetails" class="mini-tabs" activeIndex="0"
			onactivechanged="changTabaleData" style="width: 100%;"
			bodyStyle="border:0px;">
			<div class="mini-panel" title="收付款统计手动" id="lastone1" name="lastone1"
				style="width: 100%;" iconCls="icon-cut">
				<jsp:include page="fund_plan_receipt_count_ap.jsp"></jsp:include>
				<jsp:include page="fund_plan_payment_count_ap.jsp"></jsp:include>
			</div>
			<div title="生成资金计划表" 
			    name="create_fund_schedule" iconCls="icon-node" >
				<jsp:include page="create_fund_schedule.jsp" ></jsp:include>
			</div>
			<div title="生成资金计划预实表" 
			    name="create_fund_pre_schedule" iconCls="icon-node" >
				<jsp:include page="create_fund_pre_schedule.jsp" ></jsp:include>
			</div>
			<%-- <div class="mini-panel" title="收付款统计" id="lastone" name="lastone"
				style="width: 100%;" iconCls="icon-cut">
				<jsp:include page="fund_plan_receipt_count.jsp"></jsp:include>
				<jsp:include page="fund_plan_payment_count.jsp"></jsp:include>
			</div>
			<div class="mini-panel" title="资金安排" id="fundfundplan"
				name="fundfundplan" style="width: 100%;" iconCls="icon-cut">
				<jsp:include page="fund_fund_plan.jsp"></jsp:include>
			</div> --%>
		</div>
	</div>


</body>
<script type="text/javascript">
	function changTabaleData(e) {
		if (e.name == "lastone") {
			if (mini.get("receipt_count") != null) {
				mini.get("receipt_count").load();
				mini.get("payment_count").load();
			}
		} else if (e.name == "fundfundplan") {
			if (mini.get("fund_fund_plan") != null) {
				mini.get("fund_fund_plan").load();
			}
		}
	}
	
</script>

</html>