<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<%@taglib prefix="c"  uri="/WEB-INF/tlds/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>合同信息详细页面</title>
<%@include file="/base/mini.jsp"%>
<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/tracywindy/workflow.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/tracywindy/workFlowUtil.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
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
var isViewHistoryTask = true;
</script>
<style>
.td-content-title{
	font-size: 12px;
}
</style>
</head>
<body style="overflow: auto;">
<input type="hidden" id="id_json_contract_equip_str" name="json_contract_equip_str" value='<mini:param  name="json_contract_equip_str" defaultValue="[]"/>'></input>
<input type="hidden" id="id_json_rent_income_plan_str" name="json_rent_income_plan_str" value='<mini:param  name="json_rent_income_plan_str" defaultValue="[]"/>'></input>
<input type="hidden" id="id_json_rent_income_his_str" name="json_rent_income_his_str" value='<mini:param  name="json_rent_income_his_str" defaultValue="[]"/>'></input>
<input type="hidden" id="id_json_collection_plan_str" 	name="json_collection_plan_str" value='<mini:param  name="json_collection_plan_str" defaultValue="[]"/>'></input>
<input type="hidden" id="id_json_collection_his_str" 	name="json_collection_his_str" value='<mini:param  name="json_collection_his_str" defaultValue="[]"/>'></input>
<input type="hidden" id="id_json_contract_guarantee_method_str" name="json_contract_guarantee_method_str" value='<mini:param  name="json_contract_guarantee_method_str" defaultValue="[]"/>'></input>
<input type="hidden" id="id_json_contract_guarantee_equip_str" name="json_contract_guarantee_equip_str"value='<mini:param  name="json_contract_guarantee_equip_str" defaultValue="[]"/>' ></input>
	<div >
		<jsp:include page="../contract_comm/contract_base_info.jsp"><jsp:param value="true" name="isView"/></jsp:include>
	</div>
	<div class="mini-tabs" activeIndex="0" style="width: 100%;height:500px;">
		<div title="租赁物明细" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_equip_detail.jsp" ></jsp:include>
		</div>
		<div title="商务条件" iconCls="icon-cut" url="${pageContext.request.contextPath}/leasing/selectContractCondition.action?contractid=<mini:param name="contractid"/>">
		</div>
		<div title="其它商务条件" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_other_business_condition.jsp" ><jsp:param value="true" name="isView"/></jsp:include>
		</div>
		<div title="资金计划明细" iconCls="icon-expand">
			<jsp:include page="../../fund/fund_comm/fund_collection_plan_info.jsp" ></jsp:include>
		</div>
		<div title="资金收款历史明细" iconCls="icon-expand">
			<jsp:include page="../../fund/fund_comm/fund_collection_his_info.jsp" >
				<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		<div title="租金回笼计划" iconCls="icon-node">
			<jsp:include page="../../rent/rent_comm/rent_income_plan.jsp" ></jsp:include>
		</div>
		<div title="租金回笼历史" iconCls="icon-node">
			<jsp:include page="../../rent/rent_comm/rent_income_his.jsp" ></jsp:include>
		</div>
		<div title="投放明细" iconCls="icon-node">
			<jsp:include page="../../fund/fund_comm/fund_put_his_info.jsp" ></jsp:include>
		</div>
		<div title="担保单位信息" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_guarantee_method.jsp" ></jsp:include>
		</div>
		<div title="抵押物信息" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_guarantee_equip.jsp" ></jsp:include>
		</div>
		<div title="合同变更历史" iconCls="icon-cut" >
			<jsp:include page="contract_change_his.jsp"><jsp:param value="false" name="isView"/></jsp:include>
		</div>
		<div title="租金开票类型" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_rent_invoice_type.jsp" ><jsp:param value="true" name="isView"/></jsp:include>
		</div>
	     <div title="五级分类" iconCls="icon-node">
			<jsp:include page="../contract_five_category/comm/bussiness_five_category.jsp" ><jsp:param value="true" name="isView"/></jsp:include>
		</div>
	</div>
</body>
</html>
<script>
$(function(){
	mini.get("contract_number").disable();
});
//租金回笼状态
function showRentStatus(e){
    var curRowData=e.record;
	if(curRowData.currentoverage == 0){//&& curRowData.penaltyoverage==0
		return "已回笼";
	}else if(curRowData.currentoverage == curRowData.rent){
		return "未回笼";
	}else{
		return "部分回笼";
	}
}
//资金收款状态
function setfundIncomeState(row){
    var overmoney=row.overmoney;
    var planmoney=row.planmoney;
    overmoney=parseFloat(overmoney);
    overmoney=overmoney.toFixed(2);
    planmoney=parseFloat(planmoney);
    planmoney=planmoney.toFixed(2);
    var csate="";
    if(parseFloat(overmoney)<0){
   	 csate= "多收款";
    }else if(parseFloat(overmoney)==0){
   	 csate= "已收款"; 
    }else if(parseFloat(overmoney)<parseFloat(planmoney)){
   	 csate= "部份收款";
    }else{
   	 csate= "未收款";
    }
    return csate;   
}
</script>