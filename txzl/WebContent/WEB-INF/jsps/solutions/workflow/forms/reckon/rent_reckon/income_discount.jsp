<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true' || isViewHistoryTask ==true){showTools = false};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "income_discount",
			renderTo: "id_income_discount",
			width: globalClientWidth - 30,
			height: 360,
			lazyLoad: false,
			isClickLoad:false,
			remoteOper : false,
			showPager: false,
			multiSelect: true,
			showToolbar: showTools,
			editFormPopupWindowWidth : 700,
			data: mini.decode('${empty json_income_discount_str ? "[]" : json_income_discount_str }'),
			tools :['edit','-','remove','-','exportExcel'],
			confirmCopyCallBack:function(miniTable,rows){
				var names = [];
				for(var i = 0 ; i<rows.length;i++){
					names.push(rows[i].feetypename);
				}
				names = unique(names);
				var newRows = [];
				var datas = miniTable.getData();
				for(var i = 0 ; i< names.length;i++){
					var temp = 0;
					var feetypename = names[i];
					for(var j = 0 ; j< datas.length;j++){
						var data = datas[j];
						if(data.feetypename == feetypename){
							if(Number(data.paymentid) > temp){
								temp = Number(data.paymentid);
							}
						}
					}
					for(var k = 0 ;k<rows.length;k++){
						var row = mini.clone(rows[k]);
						if(row.feetypename == feetypename){
							row.paymentid = ++temp;
							newRows.push(row);
						}
					} 
				}
			   miniTable.addRows(newRows, 0); 
			   miniTable.saveDataToInput();
			},
			removeOperCallBack:function(miniTable){
				$('#json_income_discount_str').val(mini.encode(miniTable.getData()));
			},
			
			copyOperCallBack:function(miniTable){
				$('#json_income_discount_str').val(mini.encode(miniTable.getData()));
			},
			updateOperCallBack:function(miniTable){
				$('#json_income_discount_str').val(mini.encode(miniTable.getData()));
			},
			columns: [
				{type: 'indexcolumn'},
				{type: 'checkcolumn'},
				{field: 'id', header: 'id', visible: false},
				{field: 'docid', header: 'docid', visible: false},	
				{field: 'contractid', header: '合同号',visible: false,
					formEditorConfig:
					{
						readOnly:true,
						required: true,
						labelWidth:100,
						width: 200
					}
				},
				{field: 'cashdetail', header: '现金流',
					formEditorConfig:
					{
						required: true,
						labelWidth:100,
						newLine:true,
						vtype:'float',
						width: 200
					}
				},
				{field: 'financeincomewithtax', header: '融资收入(含税)',
					formEditorConfig:
					{
						required: true,
						labelWidth:100,
						vtype:'float',
						width: 200
					}
				},
				
				{field: 'investmentdecrease', header: '租赁投资净额减少额',
					formEditorConfig:
					{
						required: true,
						newLine:true,
						labelWidth:100,
						vtype:'float',
						width: 200
					}
				},
				{field: 'overinvestmentdecrease', header: '租赁投资净额余额',
					formEditorConfig:
					{
						required: true,
						labelWidth:100,
						vtype:'float',
						width: 200
					}
				},
				{field: 'overdueaccounts', header: '长期应收款-原值',
					formEditorConfig:
					{
						required: true,
						newLine:true,
						labelWidth:100,
						vtype:'float',
						width: 200
					}
				},
				{field: 'unconfirmedprofit', header: '未确认融资收益',
					formEditorConfig:
					{
						required: true,
						labelWidth:100,
						vtype:'float',
						width: 200
					}
				},
				{field: 'acountdate', header: '记账月份',
					formEditorConfig:
					{
						required: true,
						readOnly:true,
						newLine:true,
						labelWidth:100,
						width: 200
					}
				},
				{field: 'financeincomewithouttax', header: '融资收入(不含税)',
					formEditorConfig:
					{
						required: true,
						labelWidth:100,
						vtype:'float',
						width: 200
					}
				}
			]
		});
	});
});
</script>
<div id="id_income_discount"></div>