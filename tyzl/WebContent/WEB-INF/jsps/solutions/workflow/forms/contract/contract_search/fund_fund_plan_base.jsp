<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="/WEB-INF/tlds/c.tld"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/tracywindy/workflow.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/tracywindy/workFlowUtil.css" rel="stylesheet" type="text/css">
<style>
.module-content-title {
	font-weight: bold;
	line-height: 40px;
	font-size: 16px;
	padding-left: 10px;
	color: #222222;
}
</style>

<%@include file="/base/mini.jsp"%>
<script type="text/javascript">
</script>
	<div id="contract_approval_form">
		<div class="mini-tabs" activeIndex="0"   style="width: 100%;height:100%;" id="tabDetailsBase"  onactivechanged="changTab">
			<div title="资金收付款情况" name="proj_equip" iconCls="icon-cut" >
				<jsp:include page="fund_fund_plan_info.jsp">
					<jsp:param value="false" name="isView"/>
				</jsp:include>
			</div>
			<div title="商务条件" name="business_condition" iconCls="icon-cut" url="${pageContext.request.contextPath}/leasing/selectContractCondition.action?contractid=<mini:param name="contractid"/>">
			</div>
			<div title="租金回笼历史" name="rent_income_his" iconCls="icon-node">
				<jsp:include page="rent_income_his.jsp" ></jsp:include>
			</div>
			<div title="合同变更历史" name="contract_change_history" iconCls="icon-cut" >
				<jsp:include page="contract_change_his.jsp">
					<jsp:param value="false" name="isView"/>
				</jsp:include>
			</div>
	   </div>
	</div>
