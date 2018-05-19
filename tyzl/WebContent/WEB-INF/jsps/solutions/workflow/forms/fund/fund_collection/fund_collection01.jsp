<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<jsp:include page="../fund_comm/fund_comm_js_function.jsp"></jsp:include>
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
		miniui_ext.saveIncludeData("tabApprovalDeatils");
        //判断资金计划明细和本次收款明细总金额
        var  cfalg=checkFundFundCharge("collection_current");
        if(!cfalg){return false;}
	    return true;
	}
</script>
<!--多行控件  -->
<input  style="display:none;"	class="mini-textarea" id="id_json_ebank_info_str" name="json_ebank_info_str" value='<mini:param  name="json_ebank_info_str" defaultValue="[]"/>'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_collection_plan_str" 	name="json_collection_plan_str" value='<mini:param  name="json_collection_plan_str" defaultValue="[]"/>'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_collection_his_str" 	name="json_collection_his_str" value='<mini:param  name="json_collection_his_str" defaultValue="[]"/>'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_collection_current_str" 	name="json_collection_current_str" value='<mini:param  name="json_collection_current_str" defaultValue="[]"/>'></input>
<div class="fillTableContainer">
   <table class="fillTable" cellspacing="0" cellpadding="0">
	  <tr>
	     <td colspan=4><jsp:include page="../../contract/contract_comm/contract_base_info.jsp"> <jsp:param value="true" name="isView"/></jsp:include> </td><!-- 合同基本信息 --> 		
	   </tr>
	  <tr>
	     <td colspan=4><jsp:include page="../../fund/fund_comm/fund_ebank_info.jsp"> <jsp:param value="true" name="isView"/></jsp:include></td>  <!-- 网银信息 -->
	   </tr>
	</table>
</div>		
<div id="tabApprovalDeatils" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width: 100%" bodyStyle="padding:0;border:0;">
	<div title="投放明细" name="fund_put" iconCls="icon-expand">
	     <jsp:include page="../../fund/fund_comm/fund_put_cur_money.jsp" ></jsp:include>
	</div>
	<div title="资金计划明细" name="collection_plan" iconCls="icon-expand">
	     <jsp:include page="../../fund/fund_comm/fund_collection_plan_info.jsp" ></jsp:include>
	</div>
	<div title="资金收款历史明细" name="collection_his" iconCls="icon-expand">
	     <jsp:include page="../../fund/fund_comm/fund_collection_his_info.jsp" >
	         <jsp:param value="true" name="isView"/>
	     </jsp:include>
	</div>
	<div title="本次收款明细" name="collection_current" iconCls="icon-expand">
	     <jsp:include page="fund_collection_current_info.jsp" ></jsp:include>
	</div>
		<div title="联合承租人" name="union_cust" iconCls="icon-cut" >
		     <jsp:include page="../fund_comm/fund_union_cust.jsp" >
		      <jsp:param value="true" name="isView"/>
		     </jsp:include>
    </div> 	 
</div>
