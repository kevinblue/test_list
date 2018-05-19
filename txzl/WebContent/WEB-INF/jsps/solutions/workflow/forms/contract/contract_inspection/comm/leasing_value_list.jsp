<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="leasing_value_list" title="租赁物情况">
	<fieldset class="fieldset-title">
		<table cellspacing="0" cellpadding="0" style="width:100%" class="fillTable">
				
		 	 <tr class="tr-odd">
				<td class="td-content-title" align="center">租赁物原值（万元）</td>
				<td class="td-content-title" align="center">租赁物最新评估价值</td>
				<td class="td-content-title" align="center">评估方法</td>
				<td class="td-content-title" align="center">现价与应收租赁款余值的比例</td>
			</tr>
			<tr class="tr-even">
				<td class="td-content" colspan="1"><input id="contract_invest_info.yxmoney" style="width:100%" name="contract_invest_info.yxmoney" class="mini-textbox" value="${requestScope['contract_invest_info.yxmoney'] }" label="年销售收入（万元）"/></td>
				<td class="td-content" colspan="1"><input id="contract_invest_info.ycurmoney" style="width:100%" name="contract_invest_info.ycurmoney" class="mini-textbox" value="${requestScope['contract_invest_info.ycurmoney'] }" label="年销售收入（万元）"/></td>
				<td class="td-content" colspan="1"><input id="contract_invest_info.yxmoney" style="width:100%" name="contract_invest_info.yxmoney" class="mini-textbox" value="${requestScope['contract_invest_info.yxmoney'] }" label="年销售收入（万元）"/></td>
				<td class="td-content" colspan="1"><input id="contract_invest_info.ycurmoney" style="width:94%" name="contract_invest_info.ycurmoney" class="mini-textbox" value="${requestScope['contract_invest_info.ycurmoney'] }" label="年销售收入（万元）"/></td>
			</tr> 
			
		</table>
			<br/>
	</fieldset>
</div>
<script type="text/javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("leasing_value_list");};
});
</script>