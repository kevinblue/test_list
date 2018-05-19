<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="/WEB-INF/tlds/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>保理合同信息详细页面</title>
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
	 console.info(data);
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
	<input style="display: none;" class="mini-textarea" id="id_json_contract_invoice_str" name="json_contract_invoice_str" value='${empty json_contract_invoice_str ? "[]" : json_contract_invoice_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_equip_str" name="json_contract_equip_str" value='${empty json_contract_equip_str ? "[]" : json_contract_equip_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_guarantee_method_str" name="json_contract_guarantee_method_str" value='${empty json_contract_guarantee_method_str ? "[]" : json_contract_guarantee_method_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_guarantee_equip_str" name="json_contract_guarantee_equip_str" value='${empty json_contract_guarantee_equip_str ? "[]" : json_contract_guarantee_equip_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_supplier_str" name="json_contract_supplier_str" value='${empty json_contract_supplier_str ? "[]" : json_contract_supplier_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_premise_str" name="json_contract_premise_str" value='${empty json_contract_premise_str ? "[]" : json_contract_premise_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_trade_based_str" name="json_trade_based_str" value='${empty json_trade_based_str ? "[]" : json_trade_based_str }'></input>

	<div>
		<jsp:include page="../factoring_comm/contract_factoring_base_info.jsp"><jsp:param
				value="true" name="isView" /></jsp:include>
	</div>
	<div class="mini-tabs" activeIndex="0"
		style="width: 100%; height: 500px;">
		
		
      <div title="发票信息" name="proj_invoice" iconCls="icon-cut" >
		 <jsp:include page="../factoring_comm/contract_invoice.jsp" ></jsp:include>
	  </div>
	  <div title="保理报价方案" name="business_condition" iconCls="icon-cut">
			<jsp:include page="../../reckon/rent_reckon/factoring_main_5_1.jsp" >
			<jsp:param value="true" name="isView"/>
			</jsp:include>
	  </div>
	  <div title="供应商信息" name="supplier_message" iconCls="icon-cut" >
		 <jsp:include page="../factoring_comm/contract_supplier_message.jsp" ></jsp:include>
	  </div>
	  <div title="签约方" name="contract_supplier" iconCls="icon-node">
		<jsp:include page="../factoring_comm/contract_signatory.jsp"></jsp:include>
	  </div>
	  <div title="贸易基础交易情况" name="trade_based" iconCls="icon-cut" >
		 <jsp:include page="../../Proj/proj_common/trade_based_transactions.jsp" >
		 	<jsp:param value="true" name="isView"/>
		 </jsp:include>
	  </div>  
		

		
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