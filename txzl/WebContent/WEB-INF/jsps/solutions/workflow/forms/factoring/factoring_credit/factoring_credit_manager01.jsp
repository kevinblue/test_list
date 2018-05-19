<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/arrayUtils.js"></script>
<script type="text/javascript">
    //流程保存之前回调
	function workflowSaveCallBack()
	{
		miniui_ext.saveIncludeData("tabApprovalDeatils");
		return true;
	}
	//保存成功提交后，后台返回回调
	function saveCallBack() {
		return true;
	}
	//流程提交之前回调
	function workflowSubmitCallBack(buttonText)
	{	
		if (miniui_ext.submitFormValidation(["proj_factoring_base_info_form","id_trade_based_transactions","proj_supplier_message","businessconditionForm",]) == false) return false;
        miniui_ext.saveIncludeData("tabApprovalDeatils");
        //保存投票信息
        var cflag=miniui_ext.submitFormValidation(["id_project_vote"]);
		if (cflag== false) return false;	 
        //获取审批结论下拉框是否填写信息，如果填写，则进行保存。
        if(mini.get('id_selectAdvise_combo').getValue()!=""){
	        var flag = saveVote();
	    	return flag;
        }
       return true;
	}
</script>
<!--多行控件  -->
<input 	style="display: none;" class="mini-textarea" id="id_json_proj_invoice_str" name="json_proj_invoice_str" value='${empty json_proj_invoice_str ? "[]" : json_proj_invoice_str }'></input>
<div class="fillTableContainer">
   <table class="fillTable" cellspacing="0" cellpadding="0">
	  <tr>
	     <td colspan=4>
			 <!-- 插入保理业务基本信息 --> 
			 <jsp:include page="../factoring_comm/factoring_base_info.jsp"></jsp:include>  
		 </td>
	   </tr>
	   <tr>
			<!-- 插入项目信审信息 -->
			<td colspan=4> <jsp:include page="../../Proj/proj_credit/common/proj_credit_committee2.jsp"></jsp:include></td>
		</tr>
	</table>
</div>		
<div id="tabApprovalDeatils" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width: 100%;" bodyStyle="border:0px;">
	<%-- <div title="投票信息汇总" name="proj_vote" iconCls="icon-cut" >
		<table class="fillTable" cellspacing="0" cellpadding="0">
			 <tr>
				<td colspan=4> <jsp:include page="../../Proj/proj_credit/common/proj_limit_choose.jsp"><jsp:param value="true" name="isView"/></jsp:include></td>
			</tr>
			<tr>
				<td colspan=4> <jsp:include page="../../Proj/proj_credit/common/proj_choose_fkjb.jsp"><jsp:param value="true" name="isView"/></jsp:include></td>
			</tr>
			<tr>
				<!-- 插入项目信审信息 -->
				<td colspan=4> <jsp:include page="../../Proj/proj_credit/common/proj_credit_committee2.jsp"></jsp:include></td>
			</tr>
			<tr>
				<!-- 插入项目信审信息 -->
				<td colspan=4> <jsp:include page="../../Proj/proj_credit/common/proj_credit_committee_final2.jsp"></jsp:include></td>
			</tr>
		</table>
	</div> --%>
	<div title="发票信息" name="proj_invoice" iconCls="icon-cut" >
		 <jsp:include page="../factoring_comm/factoring_invoice.jsp" ></jsp:include>
	</div>
	<div title="保理报价方案" name="business_condition" iconCls="icon-cut">
			<jsp:include page="../../reckon/rent_reckon/main_5_1.jsp" ></jsp:include>
	</div>
	<div title="供应商信息" name="supplier_message" iconCls="icon-cut" >
		 <jsp:include page="../../Proj/proj_common/proj_supplier_message.jsp" ></jsp:include>
	</div>
	<div title="贸易基础交易情况" name="trade_based" iconCls="icon-cut" >
		 <jsp:include page="../../Proj/proj_common/trade_based_transactions.jsp" >
		 	<jsp:param value="false" name="isView"/>
		 </jsp:include>
	</div>
	<%-- <div title="信审信息" name="credit_message" iconCls="icon-cut" >
		<jsp:include page="common/credit_result.jsp"></jsp:include>
	</div> --%>
	<%-- <div title="上传风险评估报告" name="credit_report_list" iconCls="icon-cut"  style="display:none;">
		<jsp:include page="common/risk_assessment_report.jsp"></jsp:include>
	</div> --%>
 
</div>