<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true' || isViewHistoryTask ==true){showTools = false};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "fund_fund_plan",
			renderTo: "id_fund_fund_plan",
			width: globalClientWidth - 30,
			height: 360,
			lazyLoad: false,
			isClickLoad:false,
			remoteOper : false,
			showPager: false,
			multiSelect: true,
			showToolbar: showTools,
			editFormPopupWindowWidth : 700,
			tools : [ 'remove'],
			data: mini.decode('${empty json_fund_fund_charge_str ? "[]" : json_fund_fund_charge_str }'),
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
			removeOperCallBack:function(miniTable){
				$('#id_json_fund_fund_charge_str').val(mini.encode(miniTable.getData()));
			},
			
			copyOperCallBack:function(miniTable){
				$('#id_json_fund_fund_charge_str').val(mini.encode(miniTable.getData()));
			},
			updateOperCallBack:function(miniTable){
				$('#id_json_fund_fund_charge_str').val(mini.encode(miniTable.getData()));
			},
			columns: [
				{type: 'indexcolumn'},
				{type: 'checkcolumn'},
				{field: 'id', header: 'id', visible: false},
				{field: 'docid', header: 'docid', visible: false},	
				{field: 'contractid', header: '客户编号',
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
				{field: 'payconditionname', header: '支付条件',visible: true,formEditorConfig:{
					readOnly:true,
					fieldVisible:false,
					allowInput:false,
					fillFromFieldName:'paycondition',
					fillProperty:'name'
				}},
				{field: 'paycondition', header: '支付条件id',visible: false,
					formEditorConfig:
					{
						type:'combobox',
						newLine:true,
						textField:'name',
						valueField:'value',
						allowInput:false,
						fieldVisible:true,
						params:{pid: 'pay_condition', xmlFileName:'combos/comboDict.xml'}
					}
				},
				{field: 'whetherdeduct', header: '是否抵扣',
					formEditorConfig:
					{
						type:'combobox',
						labelWidth:100,
						width: 200,
						textField:'name',
						valueField:'value',
						data:[{name:'是',value:'是'},{name:'否',value:'否'}]
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

function unique(arr){
	var ret = [];
	var have = {};
	for(var i = 0 ; i< arr.length; i++){
		var item = arr[i];
		var key = typeof(item)+item;
		if(have[key] != 1){
			ret.push(item);
			have[key] = 1;
		}
	}
	return ret;
}
</script>
<div id="id_fund_fund_plan"></div>