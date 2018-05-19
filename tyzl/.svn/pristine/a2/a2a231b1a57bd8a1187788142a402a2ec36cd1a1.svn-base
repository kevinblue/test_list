<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if(isViewHistoryTask){showTools = false};
	tenwa.createTable({
		id: "caution_money_payment_info",
		renderTo: "id_table_caution_money_payin",
		width: '100%',
		height: '100%',
		title: "",
		remoteOper : false,
		showPager: false,
		multiSelect: true,
		showToolbar: false,
		data: JsonUtil.decode('<mini:param  name="json_caution_money_payment_info_str" defaultValue="[]"/>'),
		columns: [
			{field: 'pay_type', header: '收付方向'},
			{field: 'payment_id', header: '收付款编号'},
			{field: 'fact_object', header: '收付款对象'},
			{field: 'fee_type', header: '费用类型'},
			{field: 'settle_method', header: '结算方式'},
			{field: 'fact_money', header: '支付金额'},
			{field: 'fact_date', header: '支付日期'},
			{field: 'ffcmemo', header: '备注'}
		]
	});
});
</script>
	<span class="module-content-title">保证金收付款情况</span>
	<div id="id_table_caution_money_payin" style="width: 100%;height: 300px;"></div>