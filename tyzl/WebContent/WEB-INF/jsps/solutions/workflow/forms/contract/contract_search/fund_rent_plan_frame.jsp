<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('<mini:param name="isView"/>' == 'true'){showTools = false};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "fund_rent_plan_frame_",
			renderTo: "id_fund_rent_plan_frame",
			width: '100%',
			height: 400,
			lazyLoad: true,
			isClickLoad:false,
			remoteOper : false,
			showPager: false,
			multiSelect: true,
			showToolbar: showTools,
			data: JsonUtil.decode('<mini:param  name="json_fund_rent_plan_str" defaultValue="[]"/>'),
			completeCallBack: function(miniTable){
				miniTable.setData(mini.decode(json_fund_rent_plan_str));
			},
			columns: [
				{type: 'indexcolumn'},
				{type: 'checkcolumn'},
				{field: 'id', header: 'id', visible: false},
				{field: 'rentlist', header: '期项',
					formEditorConfig:
					{
						required: true,
						labelWidth:100,
						
						width: 200
					}
				},
				{field: 'plandate', header: '计划日期',
					formEditorConfig:
					{
						required: true,
						labelWidth:100,
						
						width: 200
					}
				},
				{field: 'rent', header: '租金',dataType : "currency",summary : true,align:'right',
					formEditorConfig:
					{
						required: true,
						labelWidth:100,
						
						width: 200
					}
				},
				{field: 'corpus', header: '财务本金',dataType : "currency",summary : true,align:'right',
					formEditorConfig:
					{
						required: true,
						labelWidth:100,
						
						width: 200
					}
				},
				{field: 'interest', header: '财务利息',dataType : "currency",summary : true,align:'right',
					formEditorConfig:
					{
						required: true,
						labelWidth:100,
						
						width: 200
					}
				},
				{field: 'corpusbusiness', header: '业务本金',dataType : "currency",summary : true,align:'right',
					formEditorConfig:
					{
						required: true,
						labelWidth:100,
						
						width: 200
					}
				},
				{field: 'interestbusiness', header: '业务利息',dataType : "currency",summary : true,align:'right',
					formEditorConfig:
					{
						required: true,
						labelWidth:100,
						
						width: 200
					}
				},
				{field: 'rentadjust', header: '租金调整',
					formEditorConfig:
					{
						required: true,
						labelWidth:100,
						
						width: 200
					}
				},
				{field: 'planstatusname', header: '核销状态',
					formEditorConfig:
					{
						required: true,
						labelWidth:100,
						width: 200
					}
				}
			]
		});
	});
});
</script>
<div id="id_fund_rent_plan_frame"></div>