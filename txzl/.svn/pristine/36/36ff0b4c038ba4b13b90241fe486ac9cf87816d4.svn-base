<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true' || isViewHistoryTask == true){showTools = false};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "fund_cash_plan_frame",
			renderTo: "id_fund_cash_plan_frame",
			width: globalClientWidth - 30,
			height: 360,
			lazyLoad: false,
			isClickLoad:false,
			remoteOper : false,
			showToolbar: showTools,
			showPager: false,
			multiSelect: true,
			data: mini.decode('${empty json_fund_cash_flow_str ? "[]" : json_fund_cash_flow_str	 }'),
			columns: [
				{type: 'indexcolumn'},
				{type: 'checkcolumn'},
				{field: 'id', header: 'id', visible: false},
				{field: 'plandate', header: '计划日期'},
				{field: 'fundin', header: '流入量',dataType : "currency",summary : true,align:'right',},
				{field: 'fundindetails', header: '流入量清单',width:350},
				{field: 'fundout', header: '流出量',dataType : "currency",summary : true,align:'right'},
				{field: 'fundoutdetails', header: '流出量清单',width:350},
				{field: 'netflow', header: '净流量',dataType : "currency",summary : true,align:'right'}
			]
		});
	});
});
</script>
<div id="id_fund_cash_plan_frame"></div>