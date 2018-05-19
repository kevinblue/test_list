<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	//是否保存   
	function workflowSaveCallBack()//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
	{
		miniui_ext.saveIncludeData("tabApprovalDeatils");
		return true;
	}
	//保存成功提交后，后台返回
	function saveCallBack() {
		return true;
	}
	//是否提交    
	function workflowSubmitCallBack(buttonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
	{
		if (miniui_ext.submitFormValidation(["drawal_cost_detail"]) == false) return false;
		
		miniui_ext.saveIncludeData("tabApprovalDeatils");
		return true;
	}
	jQuery(function(){
		var lawnum='${param.lawnum}';
		$("#lawnum").val(lawnum);
	});
</script>
<!--多行控件 -->
<input  style="display:none;"   class="mini-textarea" id="lawid" name="lawid" value='${requestScope["lawid"]}'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_filing_cost_his_detail_str" name="json_filing_cost_his_detail_str" value='${empty json_filing_cost_his_detail_str ? "[]" : json_filing_cost_his_detail_str }'></input>
<div id="contract_finish_form">
	<div class="fillTableContainer">
		<table class="fillTable" cellspacing="0" cellpadding="0">
			<tr>
				<td>
				 <jsp:include page="../../contract/contract_comm/contract_base_info.jsp"><jsp:param value="true" name="isView" /></jsp:include></td>
			</tr>
		</table>
	</div>
	<div id="tabApprovalDeatils" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width: 100%;">
		<div title="撤诉情况" name="drawal_cost_detail" iconCls="icon-node">
		 	 <jsp:include page="drawal_cost_detail.jsp"></jsp:include>  
		</div> 
	 	<div title="法务处理详情" name="approval_detail" iconCls="icon-node">
			<jsp:include page="/WEB-INF/jsps/solutions/workflow/forms/law_mng/law_approval/law_approval_detail.jsp"></jsp:include>
		</div>  
	 	<div title="立案记录" name="filing_cost_his_detail" iconCls="icon-node">
			 <jsp:include page="drawal_cost_his_detail.jsp"></jsp:include>   
		</div> 
		 
		  
	</div>
</div>
