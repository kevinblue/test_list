<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true' || isViewHistoryTask ==true){showTools = false};

	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "special_regular_modify",
			renderTo: "id_special_regular_modify",
			width: globalClientWidth - 30,
			height: 220,
			lazyLoad: false,
			isClickLoad:false,
			remoteOper : false,
			showPager: false,
			multiSelect: true,
			showToolbar: showTools,
			editFormPopupWindowWidth : 700,
			data: mini.decode('${empty json_special_regular_str ? "[]" : json_special_regular_str }'),
			tools : [ 'add','-','edit','-','remove','-','copy'],
			addOperCallBack :function(miniTable,rowData){
				var rowDatas = miniTable.getData();
				rowDatas.sort(function(a,b){return a.startlist - b.endlist;});
				miniTable.setData(rowDatas); 
				changeGrace();//调整宽限期的方法
				
			},
			updateOperCallBack:function(miniTable){
				var rowDatas = miniTable.getData();
				rowDatas.sort(function(a,b){return a.startlist - b.endlist;});
				miniTable.setData(rowDatas); 
				changeGrace();//调整宽限期的方法
			},
			removeOperCallBack:function(miniTable){
				changeGrace();//调整宽限期的方法
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
					var ratefloattype=getMiniEditFormField("special_regular_modify.ratefloattype");//利率计算方式
					var baserate=getMiniEditFormField("special_regular_modify.baserate");//基准利率
					var ratefloatamt=getMiniEditFormField("special_regular_modify.ratefloatamt");//利率调整值
					var yearrate=getMiniEditFormField("special_regular_modify.yearrate");//租赁年利率
					var regular_settlemethod = getMiniEditFormField('special_regular_modify.regularsettlemethod');//计算方式
					var regular_settlemethod_value=regular_settlemethod.getValue();
					var regular_planmoney = getMiniEditFormField('special_regular_modify.regularplanmoney');//金额
					var regular_incomenumberyear = getMiniEditFormField('special_regular_modify.regularincomenumberyear');//支付频率
					var regular_months = getMiniEditFormField('special_regular_modify.regularmonths');//间隔月数
					
					if(ratefloattype.getValue()=="fixed"){
						baserate.disable();	//基准利率设为只读			
						ratefloatamt.disable();	//利率调整值设为只读
						yearrate.enable();//租赁年利率设为可编辑
					}else{
						baserate.enable();	//基准利率设为编辑			
						ratefloatamt.enable();	//利率调整值设为编辑
						yearrate.disable();//租赁年利率设为可只读
					}
					if(regular_settlemethod_value == 'special_regular_modify.knowingrent' ||regular_settlemethod_value == 'special_regular.knowingcorpus'|| regular_settlemethod_value == 'special_regular.nointerest'){
						regular_planmoney.enable();
					}else{
						regular_planmoney.disable();
					}
					if(regular_incomenumberyear.getValue() == 'sp_numberyear.income_n'){
						regular_months.enable();
					}else{
						regular_months.disable();
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
				{field:'regularsettlemethodname', header:'计算方式', 
				      formEditorConfig:{
				          fieldVisible:false,
				     fillFromFieldName:'regularsettlemethod',
				          fillProperty:'name',
			           entityClassName:'com.tenwa.business.entity.DictionaryData',	
				 entityHeaderFieldName:'name'}},
				{field: 'regularsettlemethod', header: '计算方式',
					visible:false,
					  formEditorConfig:{
				               newLine:true,
				                  type:'combobox',
				                  required:true,
				             textField:'name',
				            valueField:'value',
				            otherAttributes:' onitemclick=checkregular_planmoneyIsReadonly ',
				          fieldVisible:true,
				    params:{pid: 'special_regular', xmlFileName:'combos/comboDict.xml'}}
				},
				{field: 'regularplanmoney', header: '金额',dataType : "currency",summary : true,align:'right',
					formEditorConfig:
					{
						type:'float',
						required: true,
						labelWidth:100,
						width: 200
					}
				},{field:'regularincomenumberyearname', header:'支付频率', visible:true,
				      formEditorConfig:{
				          fieldVisible:false,
				     fillFromFieldName:'regularincomenumberyear',
				          fillProperty:'name',
			           entityClassName:'com.tenwa.business.entity.DictionaryData',	
				 entityHeaderFieldName:'name'}},
				{field: 'regularincomenumberyear', header: '支付频率', visible:false,
					  formEditorConfig:{
				               newLine:true,
				                  type:'combobox',
				              required:true,
				             textField:'name',
				            valueField:'value',
				            otherAttributes:' onitemclick=checkregular_monthsIsReadonly ',
				          fieldVisible:true,
				    params:{pid: 'sp_numberyear', xmlFileName:'combos/comboDict.xml'}}
				},{field: 'regularmonths', header: '间隔月数',
					formEditorConfig:
					{
						type:'int',
						labelWidth:100,
						width: 200
					}
				},
				{field:'ratefloattypename', header:'利率计算方式', 
				      formEditorConfig:{
				          fieldVisible:false,
				     fillFromFieldName:'ratefloattype',
				          fillProperty:'name',
			           entityClassName:'com.tenwa.business.entity.DictionaryData',	
				 entityHeaderFieldName:'name'}},
				{field: 'ratefloattype', header: '利率计算方式',
					visible:false,
					  formEditorConfig:{
				               newLine:true,
				                  type:'combobox',
				                  required:true,
				             textField:'name',
				            valueField:'value',
				            otherAttributes:'onitemclick=checkyearrateIsReadonly',
				          fieldVisible:true,
				    params:{pid: 'rate_float_type', xmlFileName:'combos/comboDict.xml'}}
				},
				{field: 'baserate', header: '基准利率（%）',align:'right',
					formEditorConfig:
					{
						type:'float',
						otherAttributes:'onvaluechanged=calyearrate',
						labelWidth:100,
						width: 200
					}
				},
				{field: 'ratefloatamt', header: '利率调整值',align:'right',
					formEditorConfig:
					{
						type:'float',
						newLine:true,
						otherAttributes:'onvaluechanged=calyearrate',
						labelWidth:100,
						width: 200
					}
				},
				{field: 'yearrate', header: '租赁年利率（%）',
					formEditorConfig:
					{
						type:'float',
						labelWidth:120,
						required:true,
						width: 200
					}
				}
			]
		});
	});
});
function checkregular_planmoneyIsReadonly(e){
	var regular_settlemethod =  e.sender.value;
	var miniObj = mini.getbyName('regularplanmoney');
	if(regular_settlemethod == 'special_regular.knowingrent' || regular_settlemethod == 'special_regular.knowingcorpus'){
		miniObj.enable();
		miniObj.setValue(0);
	}else{
		miniObj.setValue(0);
		miniObj.disable();
	}
}
function changeGrace(){
	var data=mini.get("special_regular_modify").getData();
	var totalTerms=0;//租前息总期次
	for(var i=0;i<data.length;i++){
		//如果是租前息，则改变租前期数据
		if("special_regular_modify.beforeinterest"==data[i].regularsettlemethod){
			var regularmonths=Number(data[i].regularmonths);//间隔月数
			var terms=(Number(data[i].endlist)-Number(data[i].startlist)+1)*regularmonths;
			totalTerms+=terms;
		}
	}
	mini.get("grace").setValue(totalTerms);
}
function checkyearrateIsReadonly(e){
	
	var ratefloat =  e.sender.value;
	var baserateObj = getMiniEditFormField('special_regular_modify.baserate');//基准利率
	var ratefloatamtObj = getMiniEditFormField('special_regular_modify.ratefloatamt');//利率调整值
	var yearrateObj = getMiniEditFormField('special_regular_modify.yearrate');//年利率 
	if(ratefloat == 'fixed'){
		baserateObj.disable();
		baserateObj.setValue(0);
		ratefloatamtObj.disable();
		ratefloatamtObj.setValue(0);
		yearrateObj.enable();
		yearrateObj.setValue(0);
	}else{
		baserateObj.enable();
		ratefloatamtObj.enable();
		yearrateObj.disable();//年利率只读
		var a=baserateObj.getValue();
		var b=ratefloatamtObj.getValue();
		yearrateObj.setValue(parseFloat(a)+parseFloat(b));//年利率初始化为：基准利率+利率调整值
		baserateObj.setRequired(true);
		ratefloatamtObj.setRequired(true);
		if(ratefloat=='add'){
			yearrateObj.setValue(decimal((parseFloat(ratefloatamtObj.getValue()) + parseFloat(baserateObj.getValue())),6));
		}else{
			yearrateObj.setValue(decimal((parseFloat(ratefloatamtObj.getValue())+1) * parseFloat(baserateObj.getValue()),6));
		}
	}
}

function checkregular_monthsIsReadonly(e){
	var regular_incomenumberyear =  e.sender.value;
	var miniObj = mini.getbyName('regularmonths');
	var regular_months = parseInt(regular_incomenumberyear.replace("sp_numberyear.income_",""));
	if(regular_incomenumberyear == 'sp_numberyear.income_n'){
		miniObj.enable();
		miniObj.setValue(1);
	}else{
		miniObj.setValue(regular_months);
		miniObj.disable();
	}
}
function calyearrate(){
	var baserateObj = getMiniEditFormField('special_regular_modify.baserate').getValue();
	var ratefloatamtObj = getMiniEditFormField('special_regular_modify.ratefloatamt').getValue();
	var yearrateObj = getMiniEditFormField('special_regular_modify.yearrate');
	var ratefloattypeObj = getMiniEditFormField('special_regular_modify.ratefloattype').getValue();
	if(ratefloattypeObj=='add'){
		yearrateObj.setValue(decimal((parseFloat(ratefloatamtObj) + parseFloat(baserateObj)),6));
	}else{
		yearrateObj.setValue(decimal((parseFloat(ratefloatamtObj)+1) * parseFloat(baserateObj),6));
	}
}


</script>
<div id="id_special_regular_modify"></div>