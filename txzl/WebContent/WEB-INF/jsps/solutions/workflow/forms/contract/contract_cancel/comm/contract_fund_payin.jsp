<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true' || isViewHistoryTask == true){showTools = false;};
	tenwa.createTable({
		id: "contract_fund_payin",
		renderTo: "id_table_contract_fund_payin",
		width: globalClientWidth - 20,
		height: 360,
		title: "",
		lazyLoad: true,
		remoteOper : false,
		showPager: false,
		multiSelect: true,
		showToolbar: showTools,
		data: JsonUtil.decode('${empty json_contract_fund_payin_str ? "[]" : json_contract_fund_payin_str }'),
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'ebanknumber', header: '网银编号'},
			{field: 'feetypename', header: '费用类型'},
			{field: 'settlemethodname', header: '结算方式'},
			{field: 'factdate', header: '到账时间'},
			{field: 'factmoney', header: '金额', dataType:'currency'},
			{field: 'feeadjust', header: '调整金额', dataType:'currency'},
			{field: 'accountbank', header: '银行'},
			{field: 'account', header: '银行账户'},
			{field: 'accnumber', header: '银行账号'},
			{field: 'factobject', header: '付款客户'},
			{field: 'clientbank', header: '客户银行'},
			{field: 'clientaccount', header: '客户账户'},
			{field: 'clientaccnumber', header: '客户账号'},
			{field: 'accountingdate', header: '会计处理日'},
			{field: 'invoiceno', header: '单据号'},
			{field: 'ffcmemo', header: '备注'},
			{field: 'creator', header: '输入人'}
		]
	});
});
</script>
<div id="id_table_contract_fund_payin" style="width: 100%;height:100%;"></div>