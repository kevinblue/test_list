<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="contract_finish_info" title="合同结束信息">
	<table class="fillTable" cellspacing="0" cellpadding="0" style="border: 1px solid #99CCFF;">
		<tr class="tr-odd">
			<td class="td-content-title" width="11%">结束日期：</td>
			<td class="td-content" width="38%">
				<input id="contract_end_info.actualenddate" name="contract_end_info.actualenddate" label="结束日期" value="${requestScope['contract_end_info.actualenddate']}" class="mini-datepicker"  required="true" allowInput="false" />
			</td>
			<td class="td-content-title" width="12%">结束类型：</td>
			<td class="td-content-content" width="38%">
				<input id="contract_end_info.endtype" name="contract_end_info.endtype"  label="结束类型" class="mini-combobox" required="true"
				               valueField="value"
				               textField="name"  
							   dataField="datas"
							   allowInput="false" 
							   data-options="{_params:{pid:'finish_type'}}" 
							   onbeforeshowpopup="onbeforeshowpopup"
							   value="${requestScope['contract_end_info.endtype']}"
					           text="${requestScope['rawValue_contract_end_info.endtype']}" 
							   onvaluechanged="comboboxChanged" />
				<input id="rawValue_contract_end_info.endtype" name="rawValue_contract_end_info.endtype" value="${requestScope['rawValue_contract_end_info.endtype']}" class="mini-hidden" style="width: 165px;" style="display:none" />
			</td>
		</tr>
		<tr class="tr-even">
			<td class="td-content-title" width="12%">备注：</td>
			<td class="td-content" colspan="3">
				<input style="width:74%;height: 100px;" id="contract_end_info.endmemo" label="备注" name="contract_end_info.endmemo"  value="${requestScope['contract_end_info.endmemo'] }"  required="true" label="备注" class="mini-textarea" emptyText="请输入备注。。。" /> 
			</td>
		</tr>
	</table>
</div>
<script type="text/javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("contract_finish_info");};
});
	function submitCheck(){
		var fppiTable = mini.get("fund_payment_plan_info");
		var ripTable = mini.get("rent_income_plan");
		var datas01 = fppiTable.getData();
		var datas02 = ripTable.getData();
		var summary = 0.0;
		//资金首付款计划未付金额
		for(var i = 0;i<datas01.length;i++){
			summary += Number(datas01[i].overmoney);
		}
		//租金回笼计划租金余额
		for(var i = 0;i<datas02.length;i++){
			summary += Number(datas02[i].currentoverage);
		}
		//租金回笼计划罚息余额
		for(var i = 0;i<datas02.length;i++){
			summary += Number(datas02[i].penaltyoverage);
		}
		if(summary>1){
			mini.alert("剩余来往金额大于1元");
			return false;
		}
		return true;
	}
</script>