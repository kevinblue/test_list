<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true'){showTools = false};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "fund_rent_plan_frame",
			renderTo: "id_fund_rent_plan_frame",
			width: globalClientWidth - 30,
			height: 400,
			lazyLoad: false,
			isClickLoad:false,
			remoteOper : false,
			showPager: false,
			multiSelect: true,
			showToolbar: showTools,
			/* tools: [
			{
				html : '租金测算',
				plain : true,
				iconCls : 'icon-undo',
				handler : function(miniTable, buttonText) {
					
				}
			},
			{
				html : '租金调整',
				plain : true,
				iconCls : 'icon-redo',
				handler : function(miniTable, buttonText) {
					
				}
			},'-','add', '-', 'edit', '-','remove'], */
			data: mini.decode('${empty json_fund_rent_plan_str ? "[]" : json_fund_rent_plan_str	 }'),
			
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
				{field: 'rent', header: '租金',dataType : "currency",summary : true,
					formEditorConfig:
					{
						required: true,
						labelWidth:100,
						
						width: 200
					}
				},
				{field: 'corpus', header: '财务本金',dataType : "currency",summary : true,
					formEditorConfig:
					{
						required: true,
						labelWidth:100,
						
						width: 200
					}
				},
				{field: 'interest', header: '财务利息',dataType : "currency",summary : true,
					formEditorConfig:
					{
						required: true,
						labelWidth:100,
						
						width: 200
					}
				},
				{field: 'corpusbusiness', header: '业务本金',dataType : "currency",summary : true,
					formEditorConfig:
					{
						required: true,
						labelWidth:100,
						
						width: 200
					}
				},
				{field: 'interestbusiness', header: '业务利息',dataType : "currency",summary : true,
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
				}
			]
		});
	});
});
</script>
<div id="id_fund_rent_plan_frame"></div>