<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<jsp:include page="../../fund/fund_comm/fund_comm_js_function.jsp"></jsp:include>
<script type="text/javascript">
	var isFirstPage=true;
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
	    return true;
	}
</script>
<!--多行控件  -->
<input  style="display:none;"	class="mini-textarea" id="id_json_payment_plan_str" 	name="json_payment_plan_str" value='${empty json_payment_plan_str ? "[]" : json_payment_plan_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_pay_his_str" 	name="json_pay_his_str" value='${empty json_pay_his_str ? "[]" : json_pay_his_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_payment_current_str" 	name="json_payment_current_str" value='${empty json_payment_current_str ? "[]" : json_payment_current_str }'></input>
<div class="fillTableContainer">
   <table class="fillTable" cellspacing="0" cellpadding="0">
	  <tr>
	     <td colspan=4>
			 <!-- 合同基本信息 --> 
			 <jsp:include page="../factoring_comm/contract_factoring_base_info.jsp"><jsp:param value="true" name="isView"/></jsp:include>
		 </td>
	   </tr>
	</table>
</div>		
<div id="tabApprovalDeatils" class="mini-tabs" activeIndex="0"  onactivechanged="changTab"  style="width: 100%" bodyStyle="padding:0;border:0;">
		<div title="本次付款明细" name="payment_current" iconCls="icon-expand">
	     <jsp:include page="../../fund/fund_payment/fund_payment_current_info.jsp" >
		     <jsp:param value="true" name="isView"/>
	     </jsp:include>
	</div>
	<div title="资金付款计划明细" name="payment_plan" iconCls="icon-expand">
	     <jsp:include page="../../fund/fund_comm/fund_payment_plan_info.jsp" >
	     <jsp:param value="true" name="isView"/>
	     </jsp:include>
	</div>
	<div title="资金付款历史明细" name="pay_his" iconCls="icon-expand">
	     <jsp:include page="../../fund/fund_comm/fund_pay_his_info.jsp" >
	     <jsp:param value="true" name="isView"/>
	     </jsp:include>
	</div>

	<div title="保理放款审批表" name="quotation_scheme" iconCls="icon-node" >
			<jsp:include page="factoring_payment_approval_list.jsp" >
			<jsp:param value="true" name="isView"/>
			</jsp:include>
	</div> 	 
</div>
<script>
	function checkCurrentPayment(){
		var payment_current_grid = mini.get("payment_current");//本次付款表格对象
		var gridData =  payment_current_grid.getData();
		var flag = true;
		$.each(gridData,function(index,value){
			if(null ==value.accountingdate || null ==value.accountbank){
				flag = false;
				return false;
			}
		});
		return flag;
	}
</script>