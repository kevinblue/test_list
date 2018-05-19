<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if(isViewHistoryTask){showTools = false};
	tenwa.createTable({
		id: "caution_money_refund_plan",
		renderTo: "id_table_caution_money_refund_plan",
		width: '100%',
		height: '100%',
		title: "",
		remoteOper : false,
		showPager: false,
		multiSelect: true,
		showToolbar: false,
		data: JsonUtil.decode('${empty json_caution_money_refund_plan_str ? "[]" : json_caution_money_refund_plan_str }'),
		columns: [
			{field: 'id', header: 'id', visible: false},
			{field: 'payment_id', header: '计划编号'},
			{field: 'pay_obj', header: '收款对象'},
			{field: 'fee_type', header: '费用类型', visible: false},
			{field: 'fee_type_name', header: '费用类型'},
			{field: 'settle_method', header: '结算方式', visible: false},
			{field: 'settle_method_name', header: '结算方式', visible: false},
			{field: 'plan_date', header: '计划支付日期'},
			{field: 'plan_money', header: '计划退款本金'},
			{field: 'remain_money', header: '剩余金额'},
			{field: 'fpnote', header: '备注'}
		]
	});
});
</script>
<div id="id_table_caution_money_refund_plan" style="width: 100%;height: 100%;"></div>
