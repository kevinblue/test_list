<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
/* 字段备注  ：   计划金额   = 本次收款金额 + 调整金额            */
jQuery(function(){
	//获取父页面中保存在hidden域的值
	var showTools = true;
	if('<mini:param name="isView"/>' == 'true'){showTools = false;}
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
		id: "fund_charge_his_info",
		renderTo: "id_table_fund_charge_his_info",
		width: globalClientWidth - 20,
		height: 300,
		lazyLoad: true,
		title: "",
		remoteOper : false,
		showPager: false,
		multiSelect : true,
		showToolbar: showTools,
		data: JsonUtil.decode('<mini:param  name="json_fund_charge_his_info_str" defaultValue="[]"/>'),
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'paytype', header: '收付方向',visible: false},
			{field: 'paytypename', header: '收付方向'},
			{field: 'fundfundchargeplan', header: '收付款编号',visible:false}, 
			{field: 'paymentid', header: '收付款编号'}, 
			{field: 'ebanknumber', header: '网银编号', visible: false},
			{field: 'feetype', header: '费用类型', visible: false},
			{field: 'feetypename', header: '费用类型'},
			{field: 'settlemethod', header: '结算方式', visible: false},
			{field: 'settlemethodname', header: '结算方式'},
			{field: 'factmoney', header: '收付金额',type:'float',dataType: 'currency',summary:true},
			{field: 'factdate', header: '收付日期'},
			{field: 'feeadjust', header: '调整金额',dataType: 'currency'},
			{field: 'accountbank', header: '本方银行'},
			{field: 'account', header: '本方账户'},
			{field: 'accnumber', header: '本方账号'},
			{field: 'factobject', header: '付款客户'},
			{field: 'clientbank', header: '对方银行'},
			{field: 'clientaccount', header: '对方账户'},
			{field: 'clientaccnumber', header: '对方账号'},
			{field: 'accountingdate', header: '会计处理日'},
			{field: 'ffcmemo', header: '备注'}
		]
	});
	});
});
</script>
<div id="id_table_fund_charge_his_info" style="width:100%;"></div>