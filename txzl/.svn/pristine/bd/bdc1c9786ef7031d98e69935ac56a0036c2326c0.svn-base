<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "fund_fund_plan",
			renderTo: "id_fund_fund_plan",
			width: '100%',
			height: 360,
			lazyLoad: true,
			isClickLoad:false,
			remoteOper : false,
			showPager: false,
			multiSelect: true,
			data: mini.decode('${empty json_fund_fund_charge_str ? "[]" : json_fund_fund_charge_str }'),
			completeCallBack: function(miniTable){
				miniTable.setData(mini.decode(json_fund_fund_charge_str));
			},
			columns: [
				{type: 'indexcolumn'},
				{type: 'checkcolumn'},
				{field: 'id', header: 'id', visible: false},
				{field: 'docid', header: 'docid', visible: false},	
				{field: 'contractid', header: '合同号',
					formEditorConfig:
					{
						required: true,
						labelWidth:100,
						width: 200
					}
				},
				{field: 'paymentid', header: '编号',
					formEditorConfig:
					{
						required: true,
						labelWidth:100,
						width: 200
					}
				},
				{field: 'plandate', header: '计划收款日期',
					formEditorConfig:
					{
						required: true,
						labelWidth:100,
						width: 200
					}
				},
				{field: 'planmoney', header: '金额',dataType : "currency",summary : true,align:'right',
					formEditorConfig:
					{
						required: true,
						labelWidth:100,
						width: 200
					}
				},
				{field: 'feetypename', header: '款项名称',
					formEditorConfig:
					{
						required: true,
						labelWidth:100,
						width: 200
					}
				},
				{field: 'paytype', header: '收付方向id', visible: false},
				{field: 'paytypename', header: '收付方向',
					formEditorConfig:
					{
						required: true,
						labelWidth:100,
						width: 200
					}
				},
				{field: 'paycust', header: '收付对象id', visible: false},
				{field: 'paycustname', header: '收付对象',
					formEditorConfig:
					{
						required: true,
						labelWidth:100,
						width: 200
					}
				},
				{field: 'fpnote', header: '备注',
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
<div id="id_fund_fund_plan"></div>