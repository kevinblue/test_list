<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<jsp:include page="../fund_comm/fund_comm_js_function.jsp"></jsp:include>
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
		
		//基本表单校验
		var form = new mini.Form("#id_workflowFormContainer");
        form.validate();
        if (form.isValid() == false){ return false;} 
        //判断资金计划明细和本次收款明细总金额
        var  cfalg=checkFundFundCharge("collection_current");
        if(!cfalg){return false;}
	    return true;
	}
</script>
<!--多行控件  -->
<input  style="display:none;"	class="mini-textarea" id="id_json_ebank_info_str" name="json_ebank_info_str" value='${empty json_ebank_info_str ? "[]" : json_ebank_info_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_collection_plan_str" 	name="json_collection_plan_str" value='${empty json_collection_plan_str ? "[]" : json_collection_plan_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_collection_his_str" 	name="json_collection_his_str" value='${empty json_collection_his_str ? "[]" : json_collection_his_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_collection_current_str" 	name="json_collection_current_str" value='${empty json_collection_current_str ? "[]" : json_collection_current_str }'></input>
<div class="fillTableContainer">
   <table class="fillTable" cellspacing="0" cellpadding="0">
	  <tr>
	     <td colspan=4>
			 <!-- 合同基本信息 --> 
			 <jsp:include page="../../contract/contract_comm/contract_base_info.jsp"></jsp:include>
		 </td>
	   </tr>
	  <tr>
	     <td colspan=4>
			 <!-- 网银信息 --> 
			 <jsp:include page="../../fund/fund_comm/fund_ebank_info.jsp"></jsp:include>
		 </td>
	   </tr>
	</table>
</div>		
<div id="tabApprovalDeatils" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width: 100%" bodyStyle="padding:0;border:0;">
	<div title="资金计划明细" name="collection_plan" iconCls="icon-expand">
	     <jsp:include page="../../fund/fund_comm/fund_collection_plan_info.jsp" >
	     <jsp:param value="true" name="isView"/>
	     </jsp:include>
	</div>
	<div title="资金收款历史明细" name="collection_his" iconCls="icon-expand">
	     <jsp:include page="../../fund/fund_comm/fund_collection_his_info.jsp" >
	     <jsp:param value="true" name="isView"/>
	     </jsp:include>
	</div>
	<div title="本次收款明细" name="collection_current" iconCls="icon-expand">
	     <jsp:include page="fund_collection_current_info.jsp" >
	     <jsp:param value="true" name="isView"/>
	     </jsp:include>
	</div>
	 
</div>
<script type="text/javascript">
 
jQuery(function(){
	//单个table 只读
	miniui_ext.initformenabled("contract_base_info_form");
	//mini.get("contract_number").enable();//单个字段不只读
	miniui_ext.initformenabled("ebank_import_form");
});
</script>