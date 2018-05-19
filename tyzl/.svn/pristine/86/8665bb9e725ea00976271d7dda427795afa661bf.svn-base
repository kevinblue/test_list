<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<jsp:include page="../../fund/fund_comm/fund_comm_js_function.jsp"></jsp:include>
<script type="text/javascript">
	isViewHistoryTask=true;
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
		//json域 保存
		miniui_ext.saveIncludeData("tabApprovalDeatils");
		
	    return true;
	}
</script>
<!--多行控件  -->
<input  style="display:none;"	class="mini-textarea" id="id_json_plancharg_collect_str" name="json_plancharg_collect_str" value='<mini:param  name="json_plancharg_collect_str" defaultValue="[]"/>'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_plancharg_pay_str" 	name="json_plancharg_pay_str" value='<mini:param  name="json_plancharg_pay_str" defaultValue="[]"/>'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_fund_plan_old_str" 	name="json_fund_plan_old_str" value='<mini:param  name="json_fund_plan_old_str" defaultValue="[]"/>'></input>
<div class="fillTableContainer">
   <table class="fillTable" cellspacing="0" cellpadding="0">
	  <tr>
	     <td colspan=4>
			 <!-- 合同基本信息 --> 
			 <jsp:include page="../../contract/contract_comm/contract_base_info.jsp"></jsp:include>
		 </td>
	   </tr>
	</table>
</div>		
<div id="tabApprovalDeatils" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width:100%;" bodyStyle="padding:0;border:0;">
	 <div title="变更说明" name="contract_fund_plan_change" iconCls="icon-cut">
	     <jsp:include page="../../contract/contract_comm/contract_change_info.jsp" ></jsp:include>
	</div>
	<div title="投放明细" name="fund_put" iconCls="icon-expand">
	     <jsp:include page="../../fund/fund_comm/fund_put_cur_money.jsp" ></jsp:include>
	</div>
	<div title="资金收款计划明细" name="plancharg_collect" iconCls="icon-cut">
	     <jsp:include page="fund_plancharg_collect_info.jsp" >
	     <jsp:param value="true" name="isView"/>
	     </jsp:include>
	</div>
	<div title="资金付款计划明细" name="plancharg_pay" iconCls="icon-cut">
	     <jsp:include page="fund_plancharg_pay_info.jsp" > <jsp:param value="true" name="isView"/></jsp:include>
	</div>
	<div title="资金收付款计划历史" name="fund_plan_old" iconCls="icon-cut">
	     <jsp:include page="../fund_comm/fund_fund_plan_info.jsp" > <jsp:param value="true" name="isView"/></jsp:include>
	</div>
		<div title="联合承租人" name="union_cust" iconCls="icon-cut" >
		     <jsp:include page="../fund_comm/fund_union_cust.jsp" >
		      <jsp:param value="true" name="isView"/>
		     </jsp:include>
    </div> 
	 
</div>
<script type="text/javascript">
 
jQuery(function(){
	//单个table 只读
	miniui_ext.initformenabled("contract_base_info_form");
	miniui_ext.initformenabled("contract_change_info_form");
});
</script>