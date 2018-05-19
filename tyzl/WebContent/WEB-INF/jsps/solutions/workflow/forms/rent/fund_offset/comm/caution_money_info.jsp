<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<input name="contract_invoice_type.id" id="contract_invoice_type.id" type="hidden" value="<mini:param  name="contract_invoice_type.id" />" />
<div id="contract_rent_invoice_type_form">
	<span class="module-content-title">保证金信息</span>
	<table class="fillTable" cellspacing="0" cellpadding="0" style="border: 1px solid #99CCFF;">
		<tr class="tr-odd">
			<td class="td-content-title" width="15%">总保证金：</td>
			<td class="td-content" width="35%">
				<input id="contract_fund.caution_money" name="contract_fund.caution_money" class="mini-textbox" style="width: 165px;" allowInput="false" value="<mini:param  name="contract_fund.caution_money" />"/>
			</td>
			<td class="td-content-title" width="15%">已还保证金：</td>
			<td class="td-content" width="35%">
				<input id="contract_fund.caution_money_out" name="contract_fund.caution_money_out" class="mini-textbox" style="width: 165px;" allowInput="false" value="<mini:param  name="contract_fund.caution_money_out" />"/>
			</td>
		</tr>
		<tr class="tr-even">
			<td class="td-content-title">剩余保证金：</td>
			<td class="td-content">
				<input id="contract_fund.caution_money_remain" name="contract_fund.caution_money_remain" class="mini-textbox" style="width: 165px;" allowInput="false" value="<mini:param  name="contract_fund.caution_money_remain" />"/>
			</td>
			<td class="td-content-title">可抵扣保证金：</td>
			<td class="td-content">
				<input id="contract_fund.caution_money_available" name="contract_fund.caution_money_available" class="mini-textbox" style="width: 165px;" allowInput="true" required="true" value="<mini:param  name="contract_fund.caution_money_available" />"/>
			</td>
		</tr>
		<tr class="tr-odd">
			<td class="td-content-title"></td>
			<td class="td-content">
			</td>
			<td class="td-content-title"></td>
			<td class="td-content" style="text-indent: 0px;height: 30px;padding-left: 10px;" colspan="3">
				<a id="auto_hire" class="mini-button" iconCls="icon-ok" onclick="verificationRent()" style="color: red;font-weight: bold;">自动核销</a>
			</td>
		</tr>
	</table>
</div>
<script type="text/javascript">
function verificationRent(){
	var cautionMoneyAvailable = decimal(mini.get("contract_fund.caution_money_available").getValue(),2);
	
	var refundPlanTableDatas = mini.get("caution_money_refund_plan").getData();
	var refundDetailTable = mini.get("caution_money_refund_detail");
	var refundDetailTableDatas = [];
	
	var factdate = (new Date()).format('yyyy-MM-dd');
	
	var rowObj = {};
	var factmoney = 0;
	
	for(var i = 0;i < refundPlanTableDatas.length;i ++){
		if(cautionMoneyAvailable <= 0){
			break;
		}
		var remain_money = refundPlanTableDatas[i].remain_money;
		if(remain_money > 0){
			if(cautionMoneyAvailable >= remain_money){
				factmoney = remain_money;
				cautionMoneyAvailable = cautionMoneyAvailable - remain_money;
			}else{
				factmoney = cautionMoneyAvailable;
				cautionMoneyAvailable = 0;
			}
			
			rowObj.planid = refundPlanTableDatas[i].id;
			rowObj.paymentid = refundPlanTableDatas[i].payment_id;
			rowObj.feetype = refundPlanTableDatas[i].fee_type;
			rowObj.feetypeText = refundPlanTableDatas[i].fee_type_name;
			rowObj.settlemethod = "payfund7";
			rowObj.settlemethodText = "保证金抵扣";
			rowObj.factdate = factdate;
			rowObj.factmoney = factmoney;
			rowObj.feeadjust = 0;
			refundDetailTableDatas.push(mini.clone(rowObj));
		}
		refundDetailTable.setData(refundDetailTableDatas);
	}
	if(refundDetailTableDatas.length > 0){
		createIncomeDetail(refundDetailTableDatas,factdate);
	}
}

function createIncomeDetail(refundDetailTableDatas,factdate){
	var planTableDatas = mini.clone(mini.get("rent_income_plan").getData());
	var currTable = mini.get("rent_income_detail");
	var currTableDatas = [];
	var rowObj = {};
	
	for(var i = 0;i < refundDetailTableDatas.length;i ++){
		var remainmoney = refundDetailTableDatas[i].factmoney;
		
		var hireInterest = 0;
		var hireCorpus = 0;
		var hirePenalty = 0;
		var hireRent = 0;
		
		for(var ii = 0;ii < planTableDatas.length;ii ++){
			if(remainmoney <= 0){
				break;
			}
			var curinterestoverage = decimal(planTableDatas[ii].curinterestoverage,2);
			var curcorpusoverage = decimal(planTableDatas[ii].curcorpusoverage,2);
			var penaltyoverage = decimal(typeof(planTableDatas[ii].penaltyoverage) == "undefined" ? 0 : planTableDatas[i].penaltyoverage,2);
			var currentoverage = decimal(planTableDatas[ii].currentoverage,2);
			
			if(curinterestoverage > 0 || curcorpusoverage > 0 || penaltyoverage > 0){
				if(remainmoney >= curinterestoverage){
					hireInterest = curinterestoverage;
					remainmoney = decimal(remainmoney - curinterestoverage, 2);
					planTableDatas[ii].curinterestoverage = 0;
				}else{
					hireInterest = remainmoney;
					planTableDatas[ii].curinterestoverage = curinterestoverage - remainmoney;
					remainmoney = 0;
				}
				if(remainmoney >= curcorpusoverage){
					hireCorpus = curcorpusoverage;
					remainmoney = decimal(remainmoney - curcorpusoverage, 2);
					planTableDatas[ii].curcorpusoverage = 0;
				}else{
					hireCorpus = remainmoney;
					planTableDatas[ii].curcorpusoverage = curcorpusoverage - remainmoney;
					remainmoney = 0;
				}
				if(remainmoney >= penaltyoverage){
					hirePenalty = penaltyoverage;
					remainmoney = decimal(remainmoney - penaltyoverage, 2);
					planTableDatas[ii].penaltyoverage = 0;
				}else{
					hirePenalty = remainmoney;
					planTableDatas[ii].penaltyoverage = penaltyoverage - remainmoney;
					remainmoney = 0;
				}
				
				hireRent = decimal(hireCorpus + hireInterest,2);
				rowObj.id = planTableDatas[ii].id;
				rowObj.planlist = planTableDatas[ii].rentlist;
				rowObj.hirelist = getHireListNew(planTableDatas[ii].rentlist, currTableDatas);
				rowObj.balancemode = "payfund6";
				rowObj.balancemodeText = "电汇";
				rowObj.hiredate = factdate;
				rowObj.rent = hireRent;
				rowObj.corpus = hireCorpus;
				rowObj.interest = hireInterest;
				rowObj.penalty = hirePenalty;
				rowObj.accountingdate = factdate;
				rowObj.paymentid = refundDetailTableDatas[i].paymentid;
				rowObj.planid = refundDetailTableDatas[i].planid;
				currTableDatas.push(mini.clone(rowObj));
			}
		}
	}
	currTableDatas.sort(function(a,b){return a.planlist - b.planlist;});
	currTable.setData(currTableDatas);
	mini.alert("自动核销成功！");
}

//获取核销次数
function getHireListNew(rentlist, currTableDatas){
	var hireList = 0;
	var hisTableData = mini.get("rent_income_his").getData();
	var hisLen = hisTableData.length;
	for(var i = 0;i < hisLen; i ++){
		if(hisTableData[i].plan_list == rentlist){
			if(hireList < hisTableData[i].hire_list){
				hireList = hisTableData[i].hire_list;
			}
		}
	}
	
	var currLen = currTableDatas.length;
	for(var i = 0;i < currLen; i ++){
		if(currTableDatas[i].planlist == rentlist){
			hireList = hireList + 1;
		}
	}
	return parseInt(hireList) + 1;
}
</script>