<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<jsp:include page="../../fund/fund_comm/fund_comm_js_function.jsp"></jsp:include>
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
function workflowSubmitCallBack(buttonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
{
	var form = new mini.Form("#rent_income_form");
	form.validate();
	if (form.isValid() == false) return;
	 var chargeTable=mini.get("rent_income_detail");
	 var chargeData=chargeTable.getData();
	 if(chargeData.length>0){
     var rent=0;
     var penalty=0;
	     for(var i=0;i<chargeData.length;i++){
		  rent=chargeData[i].rent||0;
		  penalty=chargeData[i].penalty||0;
		 if(rent>0 || penalty>0){
		 }else{mini.alert("本次租金回笼明细中第"+chargeData[i].planlist+"期回笼金额为零");return false;}
	     }
	 }else{
		  mini.alert("本次租金回笼明细为空");
		 return false;
	 }
	miniui_ext.saveIncludeData("tabDetails");
	var cflag=checkFundRentIncome("rent_income_detail");//合数据库进行比较
	if(!cflag){return false;}
	var isseparaterentlist = $("#isseparaterentlist").val();
	if(isseparaterentlist=="false"){
	cflag=doCheckRentListContinue("rent_income_plan","rent_income_detail");//检查是否隔期核销
	if(!cflag){return false;}
	}
	return true;
}
</script>
<!--多行控件  -->
<input  style="display:none;"	class="mini-textarea" id="id_json_rent_income_plan_str" name="json_rent_income_plan_str" value='<mini:param  name="json_rent_income_plan_str" defaultValue="[]"/>'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_rent_income_his_str" name="json_rent_income_his_str" value='<mini:param  name="json_rent_income_his_str" defaultValue="[]"/>'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_rent_income_detail_str" name="json_rent_income_detail_str" value='<mini:param  name="json_rent_income_detail_str" defaultValue="[]"/>'></input>
<div id="rent_income_form">
	<div class="fillTableContainer"> 
		<table class="fillTable" cellspacing="0" cellpadding="0">
			<tr>
				<td ><!-- 插入合同基本信息 --> <jsp:include page="../../contract/contract_comm/contract_base_info.jsp"></jsp:include></td>
			</tr>
		</table>
	</div>
	<div id="tabDetails" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width: 100%" bodyStyle="padding:0;border:0;">
			<div title="投放明细" name="fund_put" iconCls="icon-expand">
	     <jsp:include page="../../fund/fund_comm/fund_put_cur_money.jsp" ></jsp:include>
	</div>
		<div title="网银导入" name="ebank_import" iconCls="icon-node">
			<jsp:include page="ebank_import.jsp" ></jsp:include>
		</div>
		<div title="租金回笼计划" name="rent_income_plan" iconCls="icon-node">
			<jsp:include page="../rent_comm/rent_income_plan.jsp" ></jsp:include>
		</div>
		<div title="租金回笼历史" name="rent_income_his" iconCls="icon-node">
			<jsp:include page="../rent_comm/rent_income_his.jsp" ></jsp:include>
		</div>
		<div title="本次租金回笼明细" name="rent_income_detail" iconCls="icon-node">
			<jsp:include page="../rent_comm/rent_income_detail.jsp" ></jsp:include>
		</div>
		
	<div title="联合承租人" name="union_cust" iconCls="icon-cut" >
		     <jsp:include page="../rent_comm/rent_union_cust.jsp" >
		      <jsp:param value="true" name="isView"/>
		     </jsp:include>
    </div> 
	
	</div>
</div>
<script>
jQuery(function(){
	miniui_ext.initformenabled("ebank_import_form");
	miniui_ext.initformenabled("contract_base_info_form");
});
</script>