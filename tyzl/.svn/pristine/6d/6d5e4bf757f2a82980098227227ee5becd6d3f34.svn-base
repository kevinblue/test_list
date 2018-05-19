<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
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
<input  style="display:none;"	class="mini-textarea" id="id_json_collection_his_str" 	name="json_collection_his_str" value='<mini:param  name="json_collection_his_str" defaultValue="[]"/>'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_pay_his_str" 	name="json_pay_his_str" value='<mini:param  name="json_pay_his_str" defaultValue="[]"/>'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_put_his_str" 	name="json_put_his_str" value='<mini:param  name="json_put_his_str" defaultValue="[]"/>'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_redout_current_str" 	name="json_redout_current_str" value='<mini:param  name="json_redout_current_str" defaultValue="[]"/>'></input>
<div class="fillTableContainer">
   <table class="fillTable" cellspacing="0" cellpadding="0">
	  <tr>
	     <td colspan=4>
			 <!-- 插入合同基本信息 --> 
			 <jsp:include page="../../contract/contract_comm/contract_base_info.jsp"><jsp:param value="true" name="isView"/></jsp:include>
		 </td>
	   </tr>
	   
	</table>
</div>		
<div id="tabApprovalDeatils" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width: 100%" bodyStyle="padding:0;border:0;">
	<div title="投放明细" name="fund_put" iconCls="icon-expand">
	     <jsp:include page="../../fund/fund_comm/fund_put_cur_money.jsp" ></jsp:include>
	</div>
	<div title="资金实收明细" name="collection_his" iconCls="icon-expand">
	     <jsp:include page="../../fund/fund_comm/fund_collection_his_info.jsp" ></jsp:include>
	</div>
	<div title="资金付款明细" name="pay_his" iconCls="icon-expand">
	     <jsp:include page="../../fund/fund_comm/fund_pay_his_info.jsp" ></jsp:include>
	</div>
	<div title="本次红冲明细" name="redout_current" iconCls="icon-expand">
	     <jsp:include page="fund_redout_current_info.jsp" ></jsp:include>
	</div>
	<div title="联合承租人" name="union_cust" iconCls="icon-cut" >
		     <jsp:include page="../fund_comm/fund_union_cust.jsp" >
		      <jsp:param value="true" name="isView"/>
		     </jsp:include>
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
</script>