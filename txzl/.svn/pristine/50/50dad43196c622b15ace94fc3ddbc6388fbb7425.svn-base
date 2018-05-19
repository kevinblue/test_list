<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
	miniui_ext.saveData("tabDetails");
	return true;
}
//是否提交    
function workflowSubmitCallBack(buttonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
{   miniui_ext.saveIncludeData("tabDetails");
	var flag=tenwa.validateForm("contract_base_info_form");
	if(flag==false){return false;}
	var rentmoney=getRentincome();
	if(parseFloat(rentmoney)<=0){mini.alert("本次租金回笼明细为空，请选择租金回笼计划中的租金生成回笼明细");return false;}
	var fundmoney=getCautionMoney();
	if(parseFloat(rentmoney)!=parseFloat(fundmoney)){mini.alert("本次租金回笼明细和本次抵扣的保证金明细的金额不一样，请检查");return false;}
	var cflag=checkFundRentIncome("rent_income_detail");//校验租金
	if(!cflag){return false;}
	var  cfalg=checkFundFundCharge("caution_money_refund_detail");//检验资金
    if(!cfalg){return false;}
    cflag=true;
    var rentfalg= $("#isseparaterentlist").val();
    if(rentfalg=="false"){ 
	    cflag=doCheckRentListContinue("rent_income_plan","rent_income_detail");//检查是否隔期核销
	    if(!cflag){return false;}
    }
	return true;
}
</script>
<!--多行控件  -->
<input  style="display:none;"	class="mini-textarea" id="id_json_rent_income_plan_str" name="json_rent_income_plan_str" value='${empty json_rent_income_plan_str ? "[]" : json_rent_income_plan_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_rent_income_his_str" name="json_rent_income_his_str" value='${empty json_rent_income_his_str ? "[]" : json_rent_income_his_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_fund_plan_old_str" name="json_fund_plan_old_str" value='${empty json_fund_plan_old_str ? "[]" : json_fund_plan_old_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_fund_charge_old_str" name="json_fund_charge_old_str" value='${empty json_fund_charge_old_str ? "[]" : json_fund_charge_old_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_rent_income_detail_str" name="json_rent_income_detail_str" value='${empty json_rent_income_detail_str ? "[]" : json_rent_income_detail_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_caution_money_refund_detail_str" name="json_caution_money_refund_detail_str" value='${empty json_caution_money_refund_detail_str ? "[]" : json_caution_money_refund_detail_str }'></input>

<div id="contract_change_form">
	<div class="fillTableContainer"> 
		<table class="fillTable" cellspacing="0" cellpadding="0">
			<tr>
				<td ><!-- 插入项目基本信息 --> <jsp:include page="../../contract/contract_comm/contract_base_info.jsp"></jsp:include></td>
			</tr>
		</table>
	</div>
	<div id="tabDetails" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width: 100%;">
	    <div title="租金回笼计划" name="rent_income_plan" iconCls="icon-node">
			<jsp:include page="comm/rent_income_plan.jsp" >
			  <jsp:param value="true" name="isView"/>
			</jsp:include>
	    </div>
		<div title="租金回笼历史" name="rent_income_his" iconCls="icon-node">
			<jsp:include page="../rent_comm/rent_income_his.jsp" ></jsp:include>
		</div>
		<div title="保证金收付计划" name="fund_plan_old" iconCls="icon-node">
			<jsp:include page="../../fund/fund_comm/fund_fund_plan_info.jsp" ></jsp:include>
		</div>
		<div title="保证金收付明细" name="fund_charge_old" iconCls="icon-node">
			<jsp:include page="../../fund/fund_comm/fund_fund_charge_info.jsp" ></jsp:include>
		</div>
		<div title="本次租金回笼明细" name="rent_income_detail" iconCls="icon-node">
			<jsp:include page="comm/rent_income_detail_fund.jsp" >  <jsp:param value="true" name="isView"/></jsp:include>
		</div>
		<div title="本次抵扣保证金明细" name="caution_money_refund_detail" iconCls="icon-node">
			<jsp:include page="comm/caution_money_refund_detail.jsp" >  <jsp:param value="true" name="isView"/></jsp:include>
		</div>
	</div>
</div>
<script>
	jQuery(function() {
		var form = new mini.Form("contract_base_info_form");
		form.setEnabled(false);
	});
</script>