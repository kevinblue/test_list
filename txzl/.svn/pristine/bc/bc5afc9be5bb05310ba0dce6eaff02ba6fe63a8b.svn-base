<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var contractid = "${requestScope['contract_info.id']}";
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;}
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
		id: "handover_history",
		renderTo: "id_table_handover_history",
		width: globalClientWidth - 30,
		height: 365,
		lazyLoad: false,
		isClickLoad:false,
		title: "",
		remoteOper : false,
		showPager: true,
		showToolbar: showTools,
		params:{"contract_id":contractid},
		xmlFileName : '/eleasing/jsp/doc/handover_history.xml',
		columns: [
			{type : 'indexcolumn'}, 
			{field:'contractid', header:'contractid',visible:false,headerAlign:'center',align:'center'},
			{field:'soncontract', header:'子合同号',visible:true,headerAlign:'center',align:'center'},
			{field:'filenumber',header:'档案编号',visible:true,headerAlign:'center',align:'center'},
			{field:'filename',header:'档案名称',visible:true,headerAlign:'center',align:'center'},
			{field:'handoverman',header:'交接人',visible:true,headerAlign:'center',align:'center'},
			{field:'handoverdate',header:'交接时间',visible:true,headerAlign:'center',align:'center'},
			{field:'remark',header:'备注',visible:true,headerAlign:'center',align:'center'}
		         ]
	   });
	});
});
</script>
<div id="id_table_handover_history"></div>
