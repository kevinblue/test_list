<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
        if(checkFundRedOutInfo()==false) return;
		miniui_ext.saveIncludeData("tabApprovalDeatils");
	    return true;
	}
</script>
<!--多行控件  -->
<input  style="display:none;"	class="mini-textarea" id="id_json_collection_his_str" 	name="json_collection_his_str" value='${empty json_collection_his_str ? "[]" : json_collection_his_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_pay_his_str" 	name="json_pay_his_str" value='${empty json_pay_his_str ? "[]" : json_pay_his_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_put_his_str" 	name="json_put_his_str" value='${empty json_put_his_str ? "[]" : json_put_his_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_redout_current_str" 	name="json_redout_current_str" value='${empty json_redout_current_str ? "[]" : json_redout_current_str }'></input>
<div class="fillTableContainer">
   <table class="fillTable" cellspacing="0" cellpadding="0">
	  <tr>
	     <td colspan=4>
			 <!-- 插入合同基本信息 --> 
			 	<!-- 插入合同基本信息 --><jsp:include page="../factoring_comm/contract_factoring_base_info.jsp"></jsp:include>
		 </td>
	   </tr>
	   
	</table>
</div>		
<div id="tabApprovalDeatils" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width: 100%" bodyStyle="padding:0;border:0;">
	<div title="资金实收明细" name="collection_his" iconCls="icon-expand">
	     <jsp:include page="factoring_collection_his_info.jsp" ></jsp:include>
	</div>
	<div title="资金付款明细" name="pay_his" iconCls="icon-expand">
	     <jsp:include page="factoring_pay_his_info.jsp" ></jsp:include>
	</div>
	<div title="本次红冲明细" name="redout_current" iconCls="icon-expand">
	     <jsp:include page="factoring_redout_current_info.jsp" ></jsp:include>
	</div>
	 
</div>
<script type="text/javascript">

function checkFundRedOutInfo()
 {
    var redout_current =mini.get("redout_current");
 	var selectedRowData=redout_current.getData();
 	if( selectedRowData.length == 0 )
 	{
 		  mini.alert("本次红冲明细不能为空!");
 		  return false;
 	}
 }
jQuery(function(){	
	miniui_ext.disableFormFields("contract_base_info_form");
});
</script>