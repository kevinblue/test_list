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
		id: "put_his",
		renderTo: "id_table_put_his",
		width: globalClientWidth - 10,
		height: 300,
		lazyLoad: true,
		isClickLoad:false,
		multiSelect : true,
		remoteOper : false,
		showPager: false,
		showToolbar: showTools,
		tools: [],
		data: JsonUtil.decode('${empty json_put_his_str ? "[]" : json_put_his_str }'),
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'paymentid', header: '付款编号'},//计划表主键id
			{field: 'feedtype', header: '费用类型',formEditorConfig:{}, visible: false},
			{field: 'feetypename', header: '费用类型',formEditorConfig:{}},
			{field: 'devicename1', header: '设备名称',formEditorConfig:{fieldVisible: false}},
			{field: 'fundtype', header: '款项节点',formEditorConfig:{}, visible: false},
			{field: 'fundtypename', header: '款项节点',formEditorConfig:{}},
			{field: 'factobject', header: '支付对象',width:150},
			{field: 'factdate', header: '付款日期',dateFormate:"yyyy-MM-dd"},
			{field: 'factmoney', header: '付款金额',summary : true,dataType : "currency"},
			{field: 'settlemethod', header: '结算方式', visible: false,formEditorConfig:{newLine:true}},
			{field: 'settlemethodname', header: '结算方式',formEditorConfig:{readOnly : true}},
			{field: 'billnum', header: '票据编号' },
			{field: 'clientbank', header: '对方银行',width:150},
			{field: 'clientaccount', header: '对方账户',width:150},
			{field: 'clientaccnumber', header: '对方账号',width:150},
			{field: 'accountbank', header: '本方银行',width:150},     
			{field: 'account', header: '本方账户',width:150},
			{field: 'accnumber', header: '本方账号',width:150},
			{field: 'accountingdate', header: '会计处理日',dateFormate:"yyyy-MM-dd"},
			{field: 'ffcmemo', header: '备注'}
		]
	});
	});
});

 
</script>

<div id="id_table_put_his"></div>
