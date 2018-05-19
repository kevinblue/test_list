<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
/* 字段备注  ：   计划金额   = 本次收款金额 + 调整金额            */
jQuery(function(){
	//获取父页面中保存在hidden域的值
	var showTools = true;
	if('${param.isView}' == 'true'){showTools = false};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
		id: "fund_payment_plan_info",
		renderTo: "id_table_fund_payment_plan_info",
		width: globalClientWidth - 30,
		height: '300',
		lazyLoad: false,
		title: "",
		remoteOper : false,
		showPager: false,
		multiSelect : true,
		showToolbar: showTools,
		data: JsonUtil.decode('${empty json_fund_payment_plan_info_str ? "[]" : json_fund_payment_plan_info_str }'),
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'paytypename', header: '收付方向'},
			{field: 'paymentid', header: '付款编号'},
			{field: 'feetype', header: '费用类型', visible: false},
			{field: 'feetypename', header: '费用类型'},
			{field: 'planmoney', header: '计划金额',dataType: 'currency',summary:true},
			{field: 'factmoney', header: '实收/付金额',dataType: 'currency',summary:true},
			{field: 'overmoney', header: '计划余额',dataType: 'currency',summary:true},
			{field: 'plandate', header: '计划日期',dateFormat : "yyyy-MM-dd"},
			{field: 'paycustname', header: '支付对象'},
			{field: 'paycust', header: '支付对象',visible:false},
			{field: 'fpnote', header: '备注'}
		]
	});
	});
});
</script>
<div id="id_table_fund_payment_plan_info" style="width:100%;height:100%;"></div>