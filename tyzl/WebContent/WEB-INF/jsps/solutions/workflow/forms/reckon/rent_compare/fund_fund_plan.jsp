<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "fund_fund_plan_new",
			renderTo: "id_fund_fund_plan_new",
			width: globalClientWidth - 30,
			height: 360,
			lazyLoad: false,
			isClickLoad:false,
			remoteOper : false,
			showPager: false,
			multiSelect: true,
			showToolbar: false,
			editFormPopupWindowWidth : 700,
			data: mini.decode('${empty json_fund_fund_charge_new_str ? "[]" : json_fund_fund_charge_new_str }'),
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
				miniTable.addRows(newRows);
			},
			columns: [
				{type: 'indexcolumn'},
				{type: 'checkcolumn'},
				{field: 'id', header: 'id', visible: false},
				{field: 'docid', header: 'docid', visible: false},	
				{field: 'contractid', header: '合同号',
					formEditorConfig:
					{
						readOnly:true,
						required: true,
						labelWidth:100,
						width: 200
					}
				},
				{field: 'paymentid', header: '编号',
					formEditorConfig:
					{
						readOnly:true,
						required: true,
						labelWidth:100,
						width: 200
					}
				},
				{field: 'plandate', header: '计划收付日期',type:"date",format:"yyyy-MM-dd",
					formEditorConfig:
					{
						type:"date",format:"yyyy-MM-dd",
						newLine : true,
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
						readOnly:true,
						newLine : true,
						required: true,
						labelWidth:100,
						width: 200
					}
				},
				{field: 'paytype', header: '收付方向id', visible: false},
				{field: 'paytypename', header: '收付方向',
					formEditorConfig:
					{
						readOnly:true,
						required: true,
						labelWidth:100,
						width: 200
					}
				},
				{field: 'paycust', header: '收付对象',
					visible: false,
			       formEditorConfig:{
				    	newLine:true,
		                width: 200,
		                type:'queryinput',
		             	required: true,
		            	textField: 'paycustname',
		           		valueField: 'paycust',
		           		allowInput: false,
		         		fieldVisible: true,
		             	/* onSelect:function($me, queryInput, rowData){
			                   mini.getbyName("paycustname").setValue(rowData.paycustname);
			                   mini.getbyName("paycust").setValue(rowData.paycust);
			            }, */
		               	params: {xmlFileName: '/eleasing/workflow/rent/rent_cal/fund_fund_pay_obj.xml'}
				  }
				},
				{field: 'paycustname', header: '收付对象',visible: true,
						formEditorConfig:{
			  			fieldVisible:false 
		             }
				},
				{field: 'fpnote', header: '备注',
					formEditorConfig:
					{
						type:'textarea',
						newLine:true,
						labelWidth:100,
						height:70,
						colspan:3,
						width: 478
					}
				}
			]
		});
	});
});

</script>
<div id="id_fund_fund_plan_new"></div>