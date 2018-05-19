<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('<mini:param name="isView"/>' == 'true' || isViewHistoryTask == true){showTools = false;};
	tenwa.createTable({
		id: "contract_fund_payout",
		renderTo: "id_table_contract_fund_payout",
		width: globalClientWidth - 20,
		height: 360,
		title: "",
		remoteOper : false,
		showPager: false,
		lazyLoad: true,
		multiSelect: true,
		showToolbar: showTools,
		data: JsonUtil.decode('<mini:param  name="json_contract_fund_payout_str" defaultValue="[]"/>'),
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'feetypename', header: '费用类型'},
			{field: 'settlemethodname', header: '结算方式'},
			{field: 'factdate', header: '支付时间'},
			{field: 'factmoney', header: '金额', dataType:'currency'},
			{field: 'feeadjust', header: '调整金额', dataType:'currency'},
			{field: 'accountbank', header: '银行'},
			{field: 'account', header: '银行账户'},
			{field: 'factobject', header: '收款客户'},
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
<div id="id_table_contract_fund_payout" style="width: 100%;height:100%;"></div>