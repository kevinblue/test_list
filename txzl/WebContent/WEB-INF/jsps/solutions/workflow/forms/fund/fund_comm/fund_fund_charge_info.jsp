<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">

jQuery(function(){
	//获取父页面中保存在hidden域的值
	var showTools = true;
	if('${param.isView}' == 'true')
	{
		showTools = false;
	}
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
		id: "fund_charge_old",
		renderTo: "id_table_collection_his",
		width : globalClientWidth-30,
		height : 400,
		lazyLoad: true,
		isClickLoad:false,
		multiSelect : true,
		title: "",
		remoteOper : false,
		showPager: false,
		showToolbar: showTools,
		data: JsonUtil.decode('${empty json_fund_charge_old_str ? "[]" : json_fund_charge_old_str }'),
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'paytypename', header: '收付方向'},
			{field: 'fundfundchargeplan', header: '收款编号',visible: false},//计划表主键id
			{field:'roll_back',header:'是否红冲',visible: false},		
			{field: 'ebanknumber', header: '网银编号'},
			{field: 'feetype', header: '费用类型',formEditorConfig:{}, visible: false},
			{field: 'paymentid', header: '收款编号'},//计划表主键id
			{field: 'feetypename', header: '费用类型',formEditorConfig:{}},
			{field: 'settlemethod', header: '结算方式', visible: false,formEditorConfig:{newLine:true}},
			{field: 'settlemethodname', header: '结算方式',formEditorConfig:{readOnly : true}},
			{field: 'factmoney', header: '实收金额',summary : true,dataType : "currency"},
			{field: 'factdate', header: '实收日期'},
			{field: 'feeadjust', header: '调整金额',summary : true,dataType : "currency"},
			{field: 'accountbank', header: '本方银行',width:150},     
			{field: 'account', header: '本方账户',width:150},
			{field: 'accnumber', header: '本方账号',width:150},
			{field: 'factobject', header: '付款对象',width:150},
			{field: 'clientbank', header: '对方银行',width:150},
			{field: 'clientaccount', header: '对方账户',width:150},
			{field: 'clientaccnumber', header: '对方账号',width:150},
			{field: 'accountingdate', header: '会计处理日'},
			{field: 'ffcmemo', header: '备注'}
		]
	});
	});
});
</script>

<div id="id_table_collection_his" style="width:100%;height:100%;"></div>
