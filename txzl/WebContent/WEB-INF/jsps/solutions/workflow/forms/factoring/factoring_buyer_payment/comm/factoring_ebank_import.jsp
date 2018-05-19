<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<div id="ebank_import_form">
		<input name="fund_ebank_data.id" id="fund_ebank_data.id" class="mini-hidden" value="${requestScope['fund_ebank_data.id']}" />
			<table class="fillTable" cellspacing="0" cellpadding="0" id="ebank_import_table">
				<tr class="tr-odd">
					<td class="td-content-title">网银编号：</td>
					<td class="td-content"><input id="fund_ebank_data.ebdataid" name="fund_ebank_data.ebdataid" class="mini-textbox" readOnly type="text" value="${requestScope['fund_ebank_data.ebdataid']}"></td>
					<td class="td-content-title">付款人：</td>
					<td class="td-content"><input id="fund_ebank_data.clientname" name="fund_ebank_data.clientname" class="mini-textbox" type="text" value="${requestScope['fund_ebank_data.clientname']}" /></td>
					<td class="td-content-title">流水号：</td>
					<td class="td-content"><input id="fund_ebank_data.sn" name="fund_ebank_data.sn" class="mini-textbox" readOnly type="text" value="${requestScope['fund_ebank_data.sn']}"></td>
				</tr>
				<tr class="tr-even">
					<td class="td-content-title">本方银行：</td>
					<td class="td-content"><input id="fund_ebank_data.ownbank" name="fund_ebank_data.ownbank" class="mini-textbox" readOnly type="text" value="${requestScope['fund_ebank_data.ownbank']}"></td>
					<td class="td-content-title">本方账户：</td>
					<td class="td-content"><input id="fund_ebank_data.ownaccount" name="fund_ebank_data.ownaccount" class="mini-textbox" readOnly type="text" value="${requestScope['fund_ebank_data.ownaccount']}"></td>
					<td class="td-content-title">本方账号：</td>
					<td class="td-content"><input id="fund_ebank_data.ownaccnumber"  name="fund_ebank_data.ownaccnumber" class="mini-textbox" readOnly type="text" value="${requestScope['fund_ebank_data.ownaccnumber']}"></td>
				</tr>
				<tr class="tr-odd">
					<td class="td-content-title">对方银行：</td>
					<td class="td-content"><input id="fund_ebank_data.clientbank" name="fund_ebank_data.clientbank" class="mini-textbox" readOnly type="text" value="${requestScope['fund_ebank_data.otherbank']}"></td>
					<td class="td-content-title">对方账户：</td>
					<td class="td-content"><input id="fund_ebank_data.clientaccount" name="fund_ebank_data.clientaccount" class="mini-textbox" type="text" value="${requestScope['fund_ebank_data.clientname']}" /></td>
					<td class="td-content-title">对方账号：</td>
					<td class="td-content"><input id="fund_ebank_data.clientaccnumber" name="fund_ebank_data.clientaccnumber" class="mini-textbox" type="text" value="${requestScope['fund_ebank_data.clientaccnumber']}" /></td>
				</tr>
				<tr class="tr-even">
					<td class="td-content-title">到账金额：</td>
					<td class="td-content"><input id="fund_ebank_data.factmoney" name="fund_ebank_data.factmoney" class="mini-textbox" readOnly type="text" value="${requestScope['fund_ebank_data.factmoney']}"></td>
					<td class="td-content-title">已核销金额：</td>
					<td class="td-content" ><input id="fund_ebank_data.hadmoney" name="fund_ebank_data.hadmoney" class="mini-textbox" type="text" value="${requestScope['fund_ebank_data.hadmoney']}" /></td>
					<td class="td-content-title">可核销金额：</td>
					<td class="td-content" ><input id="fund_ebank_data.mayopemoney" name="fund_ebank_data.mayopemoney" class="mini-textbox" type="text" value="${requestScope['fund_ebank_data.mayopemoney']}" /></td>	
				</tr>
				<tr class="tr-odd">
					<td class="td-content-title">无关金额：</td>
					<td class="td-content"><input id="fund_ebank_data.nowithmoney" name="fund_ebank_data.nowithmoney" class="mini-textbox" readOnly type="text" value="${requestScope['fund_ebank_data.nowithmoney']}"></td>
					<td class="td-content-title">到账金额类型：</td>
					<td class="td-content"><input id="fund_ebank_data.moneytype" name="fund_ebank_data.moneytype" class="mini-textbox" readOnly type="text" value="${requestScope['fund_ebank_data.moneytype']}"></td>
					<td class="td-content-title">到账时间：</td>
					<td class="td-content"><input id="fund_ebank_data.factdate" name="fund_ebank_data.factdate" class="mini-textbox" readOnly type="text" value="${requestScope['fund_ebank_data.factdate']}"></td>
				</tr>
				<tr class="tr-even">
					<td class="td-content-title">备注：</td>
					<td class="td-content" colspan="2">
						<input style="width:87%;height: 80px;" id="fund_ebank_data.summary" name="fund_ebank_data.summary"  required="true" label="备注" class="mini-textarea"  type="text" value="${requestScope['fund_ebank_data.summary'] }"> 
					</td>
					<td class="td-content" colspan="3" style="text-indent: 0px;">
						<a id="auto_hire" class="mini-button" onclick="hireRent()" style="color: red;font-weight: bold;">自动核销</a>
					</td>
				</tr>
			</table>
	</div>
<script>
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;};
    if(showTools==false){
         mini.get("auto_hire").hide();
    }
});
function hireRent(){
	var paymentman = mini.getbyName("fund_ebank_data.clientname").getValue();
	var baseman = mini.getbyName("contract_info.custname").getValue();
	if(paymentman!=baseman){
			mini.confirm("付款人与供应商名称不一致，请谨慎核销","提示",function(action){
				if(action=="ok"){
					var maymoney=getfundEbankOverMoney();//获得网银余额（网银余额-已增加到每次核销的租金）
					var planTable=mini.get("rent_income_plan");
					var currTable = mini.get("rent_income_detail");
					var currRowDatas = mini.clone(currTable.getData());
					var selectedDatas = planTable.getData();
					generateRentDetail(planTable,currTable,currRowDatas,selectedDatas,maymoney);
				}else{
					return ;
				}
			})
	}else{
		var maymoney=getfundEbankOverMoney();//获得网银余额（网银余额-已增加到每次核销的租金）
		var planTable=mini.get("rent_income_plan");
		var currTable = mini.get("rent_income_detail");
		var currRowDatas = mini.clone(currTable.getData());
		var selectedDatas = planTable.getData();
		generateRentDetail(planTable,currTable,currRowDatas,selectedDatas,maymoney);
	}
	
}
</script>