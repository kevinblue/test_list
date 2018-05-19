<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
	String str=request.getParameter ("contractid");
%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true'){showTools = false};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
		id: "rent_income_his",
		renderTo: "id_table_rent_income_his_",
		width : '100%',
		height : '100%',
		title: "保理债权回笼历史",
		remoteOper : false,
		showPager: false,
		multiSelect: false,
		showToolbar: showTools,
		xmlFileName:"eleasing/workflow/rent/rent_income/rent_income_his.xml",
		params : {
			contract_id:'<%=str%>',
			orderbyclause:'order by planlist'
		},
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'planlist', header: '计划期项'},
			{field: 'ebdataid', header: '网银编号'},
			{field: 'hirelist', header: '回笼期项'},
			{field: 'balancemodename', header: '结算方式'},
			{field: 'hiredate', header: '回笼日期'},
			{field: 'rent', header: '回笼租金',summary : true,dataType : "currency"},
			{field: 'corpus', header: '回笼本金',summary : true,dataType : "currency"},
			{field: 'interest', header: '回笼租息',summary : true,dataType : "currency"},
			{field: 'penalty', header: '回笼罚息',summary : true,dataType : "currency"},
			{field: 'rentadjust', header: '租金调整',summary : true,dataType : "currency"},
			{field: 'corpusadjust', header: '本金调整',summary : true,visible:false,dataType : "currency"},
			{field: 'interestadjust', header: '租息调整',summary : true,dataType : "currency"},
			{field: 'penaltyadjust', header: '罚息调整',summary : true,dataType : "currency"},
			{field: 'invoiceno', header: '单据号'},
			{field: 'accountingdate', header: '会计处理日'},
			{field: 'ownbank', header: '本方银行'},
			{field: 'ownaccount', header: '本方银行账户'},
			{field: 'ownnumber', header: '本方银行账号'},
			{field: 'hireobject', header: '付款人'},
			{field: 'hirebank', header: '对方银行'},
			{field: 'hireaccout', header: '对方银行账户'},
			{field: 'hirenumbr', header: '对方银行账号'},
			{field: 'memo', header: '备注'}		
		]
	});
	});
});
</script>
<div id="id_table_rent_income_his_" style="width: 100%;height: 100%;"></div>