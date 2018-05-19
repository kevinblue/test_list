<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
var contract_id = "${requestScope['contract_info.id']}";
jQuery(function(){
	tenwa.createTable({
		id: "overdue_fundinformation",
		renderTo: "id_table_overdue_fundinformation",
		width : globalClientWidth - 30,
		height :400,
		title: "",
		remoteOper : false,
		showPager: false,
		lazyLoad: false,
		xmlFileName:'eleasing/workflow/contract/badassets_submitted/overdue_fundinformation.xml',
		params:{contract_id:contract_id},
		columns: [
			{type:'indexcolumn'},
			//{type:'checkcolumn'},
			{field:'paymentid', header:'收款编号'},//计划表主键id
			{field:'feetypename', header:'费用类型',formEditorConfig:{}},
			{field:'plandate', header:'计划日期',type:"date",dateFormat : "yyyy-MM-dd"},
			{field:'planmoney', header:'计划金额（元）',summary : true,dataType : "currency"},
			{field:'factmoney', header:'已收金额（元）',summary : true,dataType :"currency"},
			{field:'overmoney', header:'未收金额（元）',summary : true,dataType :"currency"},
			{field:'factdate', header:'实收日期',type:"date",dateFormat : "yyyy-MM-dd"},
			{field:'penalty', header:'逾期罚息（元）',summary : true,dataType :"currency"},
			{field:'penaltyoverage', header:'罚息余额（元）',summary : true,dataType :"currency"}
		]
	});
});
</script>
<div id="id_table_overdue_fundinformation" style="width: 100%;height: 100%;"></div>