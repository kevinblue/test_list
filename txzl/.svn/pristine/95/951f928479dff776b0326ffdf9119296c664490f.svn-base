<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
var contract_id = "${requestScope['contract_info.id']}";
var nowdate = new Date().format("yyyy-MM-dd");
jQuery(function(){
	tenwa.createTable({
		id: "overdue_rentinformation",
		renderTo: "id_table_overdue_rentinformation",
		width : globalClientWidth - 30,
		height :400,
		title: "",
		remoteOper : false,
		showPager: false,
		lazyLoad: false,
		xmlFileName:'eleasing/workflow/contract/badassets_submitted/overdue_rentinformation.xml',
		params:{contract_id:contract_id,nowdate:nowdate},
		columns: [
			{type: 'indexcolumn'},
			//{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'rentlist', header: '期次'},
			//{field: 'status', header: '回笼状态', renderer: showRentStatus},
			{field: 'plandate', header: '计划日期'},
			{field: 'rent', header: '计划租金',type:'double',summary : true,dataType : "currency"},
			{field: 'corpus', header: '计划本金',type:'double',summary : true,dataType : "currency"},
			{field: 'interest', header: '计划租息',type:'double',summary : true,dataType : "currency"},
			{field: 'penalty', header: '逾期罚息',type:'double',summary : true,dataType : "currency"},
			{field: 'currentoverage', header: '租金余额',type:'double',summary : true,dataType : "currency"},
			{field: 'curcorpusoverage', header: '本金余额',type:'double',summary : true,dataType : "currency"},
			{field: 'curinterestoverage', header: '利息余额',type:'double',summary : true,dataType : "currency"},
			{field: 'penaltyoverage', header: '罚息余额',type:'double',summary : true,dataType : "currency"}
		]
	});
});
</script>
<div id="id_table_overdue_rentinformation" style="width: 100%;height: 100%;"></div>