<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
jQuery(function(){
	//获取父页面中保存在hidden域的值
	var showTools = true;
	if('<mini:param name="isView"/>' == 'true'){showTools = false;}
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
		id: "fund_plan_old",
		renderTo: "id_table_fund_plan_old_",
		width : '100%',
		height :'100%',
		isClickLoad:false,
		title: "资金收付情况",
		remoteOper : false,
		showPager: true,
		showToolbar: showTools,
		xmlFileName:"/eleasing/workflow/fund/fundcomm/fund_fund_plan_list.xml",
		params : {
			contractid:'<mini:param name="contractid"/>',
				orderbyclause:'order by paytype,paymentid'
		},
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'paytypename', header: '收付方向' },
			{field: 'feetypename', header: '费用类型'},
			{field: 'feetype', header: '费用类型', visible: false},
			{field: 'paymentid', header: '付款编号'},
			{field: 'paycustname', header: '支付对象'},
			{field: 'paycust', header: '支付对象',visible: false},
			{field: 'planmoney', header: '计划付款金额',summary : true,dataType : "currency"},
			{field: 'factmoney', header: '实付款金额',summary : true,dataType : "currency"},
			{field: 'overmoney', header: '计划付款余额',summary : true,dataType : "currency"},
			{field: 'plandate', header: '收款日期'},
			{field: 'fpnote', header: '备注'}
		]
	});
	});
	 
});
</script>

<div id="id_table_fund_plan_old_" style="width:100%;height:100%"></div>
