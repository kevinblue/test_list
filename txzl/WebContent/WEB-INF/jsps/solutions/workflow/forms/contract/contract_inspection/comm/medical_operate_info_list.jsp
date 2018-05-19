<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="medical_operate_info_list" title="项目运营情况">
	<fieldset class="fieldset-title">
		<table cellspacing="0" cellpadding="0" style="width:100%" class="fillTable">
		<tr class="tr-odd">
				<td class="td-content-title" align="center">主要经营信息</td>
				<td class="td-content-title" align="center">总额</td>
				<td class="td-content-title" align="center">月均</td>
				<td class="td-content-title" align="center">情况说明/备注</td>
			</tr>
			
			<tr class="tr-odd">
				<td class="td-content-title" width="220px">项目投产后实现的销售收入（万元）：</td>
				<td class="td-content" align="center"><input id="contract_patrol_info.saleSumMoney" style="width:150px"  name="contract_patrol_info.saleSumMoney" class="mini-textbox" value="${requestScope['contract_patrol_info.saleSumMoney'] }" required="true" label="总额"/></td>
				<td class="td-content" align="center"><input id="contract_patrol_info.saleMonthAvgMoney" style="width:150px"  name="contract_patrol_info.saleMonthAvgMoney" class="mini-textbox" value="${requestScope['contract_patrol_info.saleMonthAvgMoney'] }" required="true" label="月均"/></td>
				<td style="padding-top: 4px;padding-bottom: 4px;" align="center">
					<input style="width:70%;height:60px;" id="contract_patrol_info.saleRemarks" 
					name="contract_patrol_info.saleRemarks" class="mini-textarea" 
					value="${requestScope['contract_patrol_info.saleRemarks']}"/>
				</td>
			</tr>
			
			<tr class="tr-even">
				<td class="td-content-title" width="220px">已收到现金（万元）：</td>
				<td class="td-content" align="center"><input id="contract_patrol_info.receivedSumMoney" style="width:150px"  name="contract_patrol_info.receivedSumMoney" class="mini-textbox" value="${requestScope['contract_patrol_info.receivedSumMoney'] }" required="true" label="总额"/></td>
				<td class="td-content" align="center"><input id="contract_patrol_info.receivedMonthAvgMoney" style="width:150px"  name="contract_patrol_info.receivedMonthAvgMoney" class="mini-textbox" value="${requestScope['contract_patrol_info.receivedMonthAvgMoney'] }" required="true" label="月均"/></td>
				<td style="padding-top: 4px;padding-bottom: 4px;" align="center">
					<input style="width:70%;height:60px;" id="contract_patrol_info.receivedRemarks" 
					name="contract_patrol_info.receivedRemarks" class="mini-textarea" 
					value="${requestScope['contract_patrol_info.receivedRemarks']}"/>
				</td>
			</tr>
			
			<tr class="tr-odd">
				<td class="td-content-title" width="220px">应收电费补贴（万元）：</td>
				<td class="td-content"  align="center"><input id="contract_patrol_info.subsidySumMoney" style="width:150px"  name="contract_patrol_info.subsidySumMoney" class="mini-textbox" value="${requestScope['contract_patrol_info.subsidySumMoney'] }" required="true" label="总额"/></td>
				<td class="td-content"  align="center"><input id="contract_patrol_info.subsidyMonthAvgMoney" style="width:150px"  name="contract_patrol_info.subsidyMonthAvgMoney" class="mini-textbox" value="${requestScope['contract_patrol_info.subsidyMonthAvgMoney'] }" required="true" label="月均"/></td>
				<td style="padding-top: 4px;padding-bottom: 4px;" align="center">
					<input style="width:70%;height:60px;" id="contract_patrol_info.subsidyRemarks" 
					name="contract_patrol_info.subsidyRemarks" class="mini-textarea" 
					value="${requestScope['contract_patrol_info.subsidyRemarks']}"/>
				</td>
			</tr>
			
			<tr class="tr-even">
				<td class="td-content-title" width="220px">项目预期销售（万元）：</td>
				<td class="td-content"  align="center"><input id="contract_patrol_info.expectSumMoney" style="width:150px"  name="contract_patrol_info.expectSumMoney" class="mini-textbox" value="${requestScope['contract_patrol_info.expectSumMoney'] }" required="true" label="总额"/></td>
				<td class="td-content"  align="center"><input id="contract_patrol_info.expectMonthAvgMoney" style="width:150px"  name="contract_patrol_info.expectMonthAvgMoney" class="mini-textbox" value="${requestScope['contract_patrol_info.expectMonthAvgMoney'] }" required="true" label="备注"/></td>
				<td style="padding-top: 4px;padding-bottom: 4px;" align="center">
					<input style="width:70%;height:60px;" id="contract_patrol_info.expectRemarks" 
					name="contract_patrol_info.expectRemarks" class="mini-textarea" 
					value="${requestScope['contract_patrol_info.expectRemarks']}"/>
				</td>
			</tr>
			
		</table>
			<br/>
	</fieldset>
</div>
<script type="text/javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("medical_operate_info_list");};
});
</script>