<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var contractid = "${requestScope['contract_id']}";
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;}
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
		id: "history_toleaseback",
		renderTo: "id_history_toleaseback",
		width: globalClientWidth - 10,
		height: 365,
		lazyLoad: false,
		isClickLoad:false,
		title: "",
		remoteOper : false,
		showPager: false,
		showToolbar: showTools,
		params:{"contractid":contractid},
		xmlFileName : '/eleasing/jsp/fund/fund_account_change/history_toleaseback.xml',
		columns: [
			{type : 'indexcolumn'}, 
			{field:'contractid', header:'contractid',visible:false,headerAlign:'center',align:'center'},
			{field:'lessee', header:'承租人',visible:true,headerAlign:'center',align:'center'},
			{field:'anumber',header:'开户帐号',visible:true,headerAlign:'center',align:'center'},
			{field:'abank',header:'开户银行',visible:true,headerAlign:'center',align:'center'},
			{field:'aname',header:'开户户名',visible:true,headerAlign:'center',align:'center'}
		         ]
	   });
	});
});
</script>
<div id="id_history_toleaseback"></div>
