<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="operate_info_list" title="整体经营情况">
	<fieldset class="fieldset-title">
		<table cellspacing="0" cellpadding="0" style="width:100%" class="fillTable">
		<tr class="tr-odd">
				<td class="td-content-title">主要经营信息</td>
				<td class="td-content-title">评估时/前次巡视</td>
				<td class="td-content-title">本次巡视</td>
				<td class="td-content-title" >情况说明/备注</td>
			</tr>
			<tr class="tr-odd">
				<td class="td-content-title"  width="200px">年销售收入（万元）：</td>
				<td class="td-content" ><input id="contract_invest_info.fsrmoney" style="width:90px"  name="contract_invest_info.fsrmoney" class="mini-textbox" value="${requestScope['contract_invest_info.fsrmoney'] }" required="true" label="年销售收入（万元）"/></td>
				<td class="td-content" ><input id="contract_invest_info.fsrcurmoney" style="width:90px"  name="contract_invest_info.fsrcurmoney" class="mini-textbox" value="${requestScope['contract_invest_info.fsrcurmoney'] }" required="true" label="年销售收入（万元）"/></td>
				<td style="padding-top: 4px;padding-bottom: 4px;">
					<input style="width:55%;height:80px;" id="contract_invest_info.fsrmemo" 
					name="contract_invest_info.fsrmemo" class="mini-textarea" 
					value="${requestScope['contract_invest_info.fsrmemo']}"/>
				</td>
			</tr>
			<tr class="tr-even">
				<td class="td-content-title" >库存（万元）：</td>
				<td class="td-content" ><input id="contract_invest_info.kcmoney" style="width:90px"  name="contract_invest_info.kcmoney" class="mini-textbox" value="${requestScope['contract_invest_info.kcmoney'] }" required="true" label="年销售收入（万元）"/></td>
				<td class="td-content" ><input id="contract_invest_info.kccurmoney" style="width:90px"  name="contract_invest_info.kccurmoney" class="mini-textbox" value="${requestScope['contract_invest_info.kccurmoney'] }" required="true" label="年销售收入（万元）"/></td>
				<td style="padding-top: 4px;padding-bottom: 4px;">
					<input style="width:55%;height:80px;" id="contract_invest_info.kcmemo" 
					name="contract_invest_info.kcmemo" class="mini-textarea" 
					value="${requestScope['contract_invest_info.kcmemo']}"/>
				</td>
			</tr>
			<tr class="tr-odd">
				<td class="td-content-title" >应收账款（万元）：</td>
				<td class="td-content" ><input id="contract_invest_info.ysmoney" style="width:90px"  name="contract_invest_info.ysmoney" class="mini-textbox" value="${requestScope['contract_invest_info.ysmoney'] }" required="true" label="年销售收入（万元）"/></td>
				<td class="td-content" ><input id="contract_invest_info.yscurmoney" style="width:90px"  name="contract_invest_info.yscurmoney" class="mini-textbox" value="${requestScope['contract_invest_info.yscurmoney'] }" required="true" label="年销售收入（万元）"/></td>
				<td style="padding-top: 4px;padding-bottom: 4px;">
					<input style="width:55%;height:80px;" id="contract_invest_info.ysmemo" 
					name="contract_invest_info.ysmemo" class="mini-textarea" 
					value="${requestScope['contract_invest_info.ysmemo']}"/>
				</td>
			</tr>
			<tr  class="tr-even">
				<td class="td-content-title" >应付账款（万元）：</td>
				<td class="td-content" ><input id="contract_invest_info.yfmoney" style="width:90px"  name="contract_invest_info.yfmoney" class="mini-textbox" value="${requestScope['contract_invest_info.yfmoney'] }" required="true" label="年销售收入（万元）"/></td>
				<td class="td-content" ><input id="contract_invest_info.yfcurmoney" style="width:90px"  name="contract_invest_info.yfcurmoney" class="mini-textbox" value="${requestScope['contract_invest_info.yfcurmoney'] }" required="true" label="年销售收入（万元）"/></td>
				<td style="padding-top: 4px;padding-bottom: 4px;">
					<input style="width:55%;height:80px;" id="contract_invest_info.yfmemo" 
					name="contract_invest_info.yfmemo" class="mini-textarea" 
					value="${requestScope['contract_invest_info.yfmemo']}"/>
				</td>
			</tr>
			<tr class="tr-odd">
				<td class="td-content-title" >其他应收账款（万元）：</td>
				<td class="td-content" ><input id="contract_invest_info.othermoney" style="width:90px"  name="contract_invest_info.othermoney" class="mini-textbox" value="${requestScope['contract_invest_info.othermoney'] }" required="true" label="年销售收入（万元）"/></td>
				<td class="td-content" ><input id="contract_invest_info.othercurmoney" style="width:90px"  name="contract_invest_info.othercurmoney" class="mini-textbox" value="${requestScope['contract_invest_info.othercurmoney'] }" required="true" label="年销售收入（万元）"/></td>
				<td style="padding-top: 4px;padding-bottom: 4px;">
					<input style="width:55%;height:80px;" id="contract_invest_info.othermemo" 
					name="contract_invest_info.othermemo" class="mini-textarea" 
					value="${requestScope['contract_invest_info.othermemo']}"/>
				</td>
			</tr>
			<tr class="tr-even">
				<td class="td-content-title">资产负债率：</td>
				<td class="td-content"  colspan="3"><input id="contract_invest_info.debtlv" style="width:90px"   name="contract_invest_info.debtlv" class="mini-textbox" allowInput="true" value="${requestScope['contract_invest_info.debtlv'] }" required="true" label="资产负债率"/></td>
			</tr>
			<tr class="tr-odd">
				<td class="td-content-title" >流动比率：</td>
				<td class="td-content" colspan="3"><input id="contract_invest_info.cashlv" style="width:90px" name="contract_invest_info.cashlv" class="mini-textbox" allowInput="true" value="${requestScope['contract_invest_info.cashlv'] }" required="true" label="流动比率"/></td>
			</tr>
			<tr class="tr-odd">
				<td class="td-content-title" >小结分析：</td>
				<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3">
					<input style="width:80%;height:100px" id="contract_invest_info.investmemo" 
					name="contract_invest_info.investmemo" class="mini-textarea" 
					value="${requestScope['contract_invest_info.investmemo']}"/>
				</td>
			</tr>
		</table>
			<br/>
	</fieldset>
</div>
<script type="text/javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("operate_info_list");};
});
</script>
