<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="/WEB-INF/tlds/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>合同信息详细页面</title>
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
	/* var json_fund_rent_plan_str = "";
	 var json_fund_cash_flow_str ="";
	 var json_fund_fund_charge_str="";
	 $(function(){
	 $.ajax({
	 url :getRootPath()+"/leasing/selectContractCondition.action?contractid=${param.contractid}",
	 type:"post",
	 async :false,
	 success :function(data) {
	 var returnInfo = mini.decode(data);
	 var form = new mini.Form('#businessconditionFormReadonly');
	 form.setData(returnInfo);
	 miniui_ext.initcomboboxloaddata("businessconditionFormReadonly",returnInfo);
	 json_fund_rent_plan_str = returnInfo.json_fund_rent_plan_str;
	 json_fund_cash_flow_str = returnInfo.json_fund_cash_flow_str;
	 json_fund_fund_charge_str = returnInfo.json_fund_fund_charge_str
	 }
	 });
	 }); */
	var isViewHistoryTask = true;
</script>
<style>
.td-content-title {
	font-size: 12px;
}
</style>
</head>
<body style="overflow: auto;">
	<input type="hidden" id="id_json_contract_equip_str"
		name="json_contract_equip_str"
		value='${empty json_contract_equip_str ? "[]" : json_contract_equip_str }'></input>
	<input type="hidden" id="id_json_rent_income_plan_str"
		name="json_rent_income_plan_str"
		value='${empty json_rent_income_plan_str ? "[]" : json_rent_income_plan_str }'></input>
	<input type="hidden" id="id_json_rent_income_his_str"
		name="json_rent_income_his_str"
		value='${empty json_rent_income_his_str ? "[]" : json_rent_income_his_str }'></input>
	<input type="hidden" id="id_json_collection_plan_str"
		name="json_collection_plan_str"
		value='${empty json_collection_plan_str ? "[]" : json_collection_plan_str }'></input>
	<input type="hidden" id="id_json_collection_his_str"
		name="json_collection_his_str"
		value='${empty json_collection_his_str ? "[]" : json_collection_his_str }'></input>
	<input type="hidden" id="id_json_contract_guarantee_method_str"
		name="json_contract_guarantee_method_str"
		value='${empty json_contract_guarantee_method_str ? "[]" : json_contract_guarantee_method_str }'></input>
	<input type="hidden" id="id_json_contract_guarantee_equip_str"
		name="json_contract_guarantee_equip_str"
		value='${empty json_contract_guarantee_equip_str ? "[]" : json_contract_guarantee_equip_str }'></input>
	<div>
		<jsp:include page="../contract_comm/contract_base_info.jsp"><jsp:param
				value="true" name="isView" /></jsp:include>
	</div>
	<div class="mini-tabs" activeIndex="0"
		style="width: 100%; height: 500px;">
		<div title="租赁物明细" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_equip_detail.jsp"></jsp:include>
		</div>
		<div title="商务条件" iconCls="icon-cut"
			url="${pageContext.request.contextPath}/leasing/selectContractCondition.action?contractid=${param.contractid}">
		</div>
		<div title="其它商务条件" iconCls="icon-node">
			<jsp:include
				page="../contract_comm/contract_other_business_condition.jsp"><jsp:param
					value="true" name="isView" /></jsp:include>
		</div>
		 <div title="项目收付款情况" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_payment.jsp"></jsp:include>
		</div>
		<%-- <div title="项目收付款情况" iconCls="icon-expand">

			<div title="资金计划明细" iconCls="icon-expand">
				<jsp:include
					page="../../fund/fund_comm/fund_collection_plan_info.jsp"></jsp:include>
			</div>
			<div title="资金收款历史明细" iconCls="icon-expand">
				<jsp:include
					page="../../fund/fund_comm/fund_collection_his_info.jsp">
					<jsp:param value="true" name="isView" />
				</jsp:include>
			</div>
			<div title="租金回笼计划" iconCls="icon-node">
				<jsp:include page="../../rent/rent_comm/rent_income_plan_query.jsp"></jsp:include>
			</div>
			<div title="租金回笼历史" iconCls="icon-node">
				<jsp:include page="../../rent/rent_comm/rent_income_his_query.jsp"></jsp:include>
			</div>
			<div title="投放明细" iconCls="icon-node">
				<jsp:include page="../../fund/fund_comm/fund_put_his_info_query.jsp"></jsp:include>
			</div>
		</div> --%>
       <div title="担保抵押情况" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_guaranteed_mortgage.jsp"></jsp:include>
		</div>
		<%-- <div title="担保抵押情况" iconCls="icon-expand">
			<div title="担保单位信息" iconCls="icon-node">
				<jsp:include
					page="../contract_comm/contract_guarantee_method_query.jsp"></jsp:include>
			</div>
			<div title="抵质押物信息" iconCls="icon-node">
				<jsp:include
					page="../contract_comm/contract_guarantee_equip_query.jsp"></jsp:include>
			</div>
		</div> --%>
		<div title="合同变更历史" iconCls="icon-cut">
			<jsp:include page="contract_change_his.jsp"><jsp:param
					value="false" name="isView" /></jsp:include>
		</div>
		<div title="租金开票类型" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_rent_invoice_type.jsp"><jsp:param
					value="true" name="isView" /></jsp:include>
		</div>
		<div title="签约方" name="contract_supplier" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_signatory.jsp" ><jsp:param 
			value="true" name="isView"/></jsp:include>
		</div>
		

		<%-- <div title="项目收付款情况" iconCls="icon-cut"
			url="${pageContext.request.contextPath}/leasing/selectPayment.action?contractid=${param.contractid}">
		</div> --%>
	</div>
</body>
</html>
<script>
	$(function() {
		mini.get("contract_number").disable();
	});
	//租金回笼状态
	function showRentStatus(e) {
		var curRowData = e.record;
		if (curRowData.currentoverage == 0) {//&& curRowData.penaltyoverage==0
			return "已回笼";
		} else if (curRowData.currentoverage == curRowData.rent) {
			return "未回笼";
		} else {
			return "部分回笼";
		}
	}
	//资金收款状态
	function setfundIncomeState(row) {
		var overmoney = row.overmoney;
		var planmoney = row.planmoney;
		overmoney = parseFloat(overmoney);
		overmoney = overmoney.toFixed(2);
		planmoney = parseFloat(planmoney);
		planmoney = planmoney.toFixed(2);
		var csate = "";
		if (parseFloat(overmoney) < 0) {
			csate = "多核销";
		} else if (parseFloat(overmoney) == 0) {
			csate = "已核销";
		} else if (parseFloat(overmoney) < parseFloat(planmoney)) {
			csate = "部份核销";
		} else {
			csate = "未核销";
		}
		return csate;
	}
</script>