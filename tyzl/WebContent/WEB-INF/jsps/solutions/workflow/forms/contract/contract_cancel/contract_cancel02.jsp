<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
	//是否保存   
	function workflowSaveCallBack()//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
	{
		miniui_ext.saveIncludeData("tabDetails");
		return true;
	}
	//保存成功提交后，后台返回
	function saveCallBack() {
		 
		return true;
	}
	//是否提交    
	function workflowSubmitCallBack(buttonText) {
		miniui_ext.saveIncludeData("tabDetails");
		 if (miniui_ext.submitFormValidation(["contract_cancel_reason_form"]) == false) return false;
		return true;
	}
</script>

<!--start 多行控件  -->
<jsp:include page="comm/contract_cancel_mutli_info.jsp" ></jsp:include>
<!--end 多行控件  -->

<div class="fillTableContainer">
	<jsp:include page="../contract_comm/contract_base_info.jsp"><jsp:param value="true" name="isView"/></jsp:include>
	<div id="tabDetails" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width: 100%;" bodyStyle="border:0px;">
		<div title="撤销明细" name="contract_cancel_reason" iconCls="icon-node">
			<jsp:include page="comm/contract_cancel_reason.jsp" >
			     <jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		<!-- 
		<div title="租赁物明细" name="contract_equip_detail" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_equip_detail.jsp" >
				<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		<div title="资金收款历史" name="contract_fund_payin" iconCls="icon-node">
			<jsp:include page="comm/contract_fund_payin.jsp" />
		</div>
		<div title="资金付款历史" name="contract_fund_payout" iconCls="icon-node">
			<jsp:include page="comm/contract_fund_payout.jsp" />
		</div> -->
	</div>
</div>