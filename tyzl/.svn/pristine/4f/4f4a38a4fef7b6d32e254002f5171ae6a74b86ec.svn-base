<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "special_regular_new",
			renderTo: "id_special_regular_new",
			width: globalClientWidth - 30,
			height: 360,
			lazyLoad: false,
			isClickLoad:false,
			remoteOper : false,
			showPager: false,
			multiSelect: true,
			showToolbar: false,
			editFormPopupWindowWidth : 700,
			data: mini.decode('${empty json_special_regular_new_str ? "[]" : json_special_regular_new_str }'),
			addOperCallBack :function(miniTable,rowData){
				var rowDatas = miniTable.getData();
				rowDatas.sort(function(a,b){return a.startlist - b.endlist;});
				miniTable.setData(rowDatas); 
			},
			updateOperCallBack:function(miniTable){
				var rowDatas = miniTable.getData();
				rowDatas.sort(function(a,b){return a.startlist - b.endlist;});
				miniTable.setData(rowDatas); 
			},
			afterShowWindowCallBack: function(miniTable,miniForm,operFlag){
				if("add" == operFlag){
					var startrentlist = mini.getbyName("startlist");
					var endlist = mini.getbyName("endlist");
					var row = miniTable.getRow(miniTable.getData().length-1);
					if(null != row){
						endrentlist = row.endlist;
						startrentlist.setValue(parseInt(endrentlist)+1);
						endlist.setValue(parseInt(endrentlist)+2);
					}else{
						startrentlist.setValue(1);
					}
					startrentlist.setEnabled(false);
				}else{
					var regular_settlemethod = mini.getbyName('regular_settlemethod').getValue();
					var rate = mini.getbyName('rate');
					var regular_months = mini.getbyName('regular_months');
					if(regular_settlemethod == 'regular_settlemethod.knowcorpusrate'){
						rate.enable();
					}else{
						rate.disable();
					}
				}
			},
			validateForm:function (miniTable, miniForm, editFormItemOperFlag, addIndex){
				//校验融资额的计划收款日期不能大于第一期的租金支付日
				var startlist = mini.getbyName('startlist').getValue();
				var endlist = mini.getbyName('endlist').getValue();
				if(Number(endlist) < startlist){
					mini.alert('结束期次不能小于开始期次！！！');
					return false;
				}
				return true;
			},
			columns: [
				{type: 'indexcolumn'},
				{type: 'checkcolumn'},
				{field: 'id', header: 'id', visible: false},
				{field: 'docid', header: 'docid', visible: false},	
				{field: 'startlist', header: '起始期次',
					formEditorConfig:
					{
						readOnly:false,
						required: true,
						labelWidth:100,
						width: 200
					}
				},
				{field: 'endlist', header: '结束期次',
					formEditorConfig:
					{
						type:'int',
						required: true,
						labelWidth:100,
						width: 200
					}
				},
				{field:'regular_settlemethodname', header:'计算方式', 
				      formEditorConfig:{
				          fieldVisible:false,
				     fillFromFieldName:'regular_settlemethod',
				          fillProperty:'name'}},
				{field: 'regular_settlemethod', header: '计算方式',
					visible:false,
					  formEditorConfig:{
				               newLine:true,
				                  type:'combobox',
				                  required:true,
				             textField:'name',
				            valueField:'value',
				            otherAttributes:' onitemclick=checkregular_planmoneyIsReadonly ',
				          fieldVisible:true,
				    params:{pid: 'regular_settlemethod', xmlFileName:'combos/comboDict.xml'}}
				},
				{field: 'rate', header: '每期本金占总本金比例',dataType : "currency",summary : true,align:'right',
					formEditorConfig:
					{
						type:'float',
						required: true,
						labelWidth:150,
						width: 200
					}
				},{field: 'regular_months', header: '间隔月数',
					formEditorConfig:
					{
						type:'int',
						defaultValue:1,
						newLine:true,
						labelWidth:100,
						width: 200
					}
				}
			]
		});
	});
});



</script>
<div id="id_special_regular_new"></div>