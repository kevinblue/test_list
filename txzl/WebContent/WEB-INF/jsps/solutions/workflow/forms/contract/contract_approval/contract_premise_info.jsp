<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
var premises =null;
var vndrType = null;
var number = null;
jQuery(function (){
	var showTools = false;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;}
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "contract_premise",
			renderTo:"id_table_contract_premise_container",
			width: globalClientWidth - 30,
			height: 360,
			lazyLoad: false,
			title: "", 
			isClickLoad:false,
			remoteOper : false,
			showPager: false,
			multiSelect: false,
			showToolbar: showTools,
			tools: ['add', '-', 'edit', '-','remove'],
			afterShowWindowCallBack:function(miniTable,miniForm,operFlag){
				if("edit" == operFlag){
					var row = mini.get("contract_premise").getSelected(); 
					getMiniEditFormField("contract_premise.paymentid").setValue(row.paymentid);
					getMiniEditFormField("contract_premise.paymentid").setText(row.paymentid);
				}
			},
			//转换特殊符号函数
			validateForm:function(miniTable, miniForm, editFormItemOperFlag){
				if(editFormItemOperFlag=="add"||editFormItemOperFlag=="edit"){
					var prem = mini.getbyName("premise").getValue().replace(/\r\n/g,"<br />");
					mini.getbyName("premise").setValue(prem);
				}
				
				   
					return true;
			},
			data: JsonUtil.decode('${empty json_contract_premise_str ? "[]" : json_contract_premise_str }'),
			columns: [
						{type:'indexcolumn'},
						{type:'checkcolumn'},
						{field: 'id', header: 'id',visible:false,formEditorConfig : {fieldVisible: false}},
						{field: 'paytype', header: '支付方式',visible:false,formEditorConfig : {fieldVisible: false}},
						{field: 'paymentnumber', header: '付款编号',width:'40px',formEditorConfig:{fieldVisible: false}},
						{field: 'paymentid', header: '收付款编号',formEditorConfig : {
							newLine: true,
							type : 'combobox',
							required: true,
							textField: 'paymentnumber',
							valueField: 'paymentnumber',
							allowInput: false,
							fieldVisible: true,
							colspan: 3,
							width: '100%',
							onbeforeshowpopup: 'getPaymentidData',
							data: [],
							onvaluechanged: 'onPaymentidChanged'
						}},
						{field: 'vndrtypename', header: '贸易类型', visible: false,
			            	  formEditorConfig:{
								   		 fieldVisible: false,
								   		 fillFromFieldName : 'payment',
										 fillProperty : 'name',
										 
							}},
					    {field : 'vndrtype',header : '贸易类型',visible: false,
					   		  formEditorConfig : {
							   			 type : 'combobox',
										 textField: 'name',
										 newLine:true,
										 required: true,
										 valueField: 'value',
										 fieldVisible: true,
										 params:{pid: 'vndr_type',xmlFileName: '/combos/comboDict.xml'},
						                 onvaluechanged: 'onVndrtypeChanged'
						                 
					   		}},
					    {field: 'paycust', header: '供应商',formEditorConfig : {
								type:'text',
								height:100,
								width:'100%',
								readOnly:true 
						}}, 
						{field: 'feetype', header: '费用类型',visible:false,formEditorConfig:{
							type:'text',
							newLine:true,
							required:true,
							textField:'name',
							valueField:'value',
							fieldVisible:false,
							allowInput:false,
							params:{pid: 'FeeType', xmlFileName:'combos/comboDict.xml'}
						}},
						{field: 'feetypename', header: '费用类型',width:'40px',formEditorConfig:{
							readOnly:true,
							fieldVisible:true,
							allowInput:false,
							fillFromFieldName:'feetype',
							fillProperty:'name'
						}},
						{field: 'premisemoney', header: '金额',width:'50px',formEditorConfig : {allowInput:false,readOnly:true}},
						{field: 'premise', header: '前提条件',width:'300px',formEditorConfig : {
							type:'textarea',
							newLine:true,
							colspan:3,
							height:100,
							width:'100%'
						}},
						{field: 'premisenote', header: '备注',type:"textarea",formEditorConfig : {
							type:'textarea',
							newLine:true,
							colspan:3,
							height:100,
							width:'100%'
						}}
					]
		})
	})
});
//得到付款编号的data
function getPaymentidData(){
	var putCurrData = mini.get("contract_premise").getData();
	var data = mini.get("fund_fund_plan").getData();
	var rowDatas = [];
	
	var flag = false;
	for (var i = 0; i < data.length; i++) {	
		if (data[i].paytype == "pay_type_out") {
			flag = false;
			for(var j = 0;j < putCurrData.length; j++){
				if(data[i].paymentid == putCurrData[j].paymentnumber && data[i].feetype == putCurrData[j].feetype){
					flag = true;
				}
			}
			if(flag == false){
				//判断费用类型是否是    抵扣保证金
				if ( data[i].feetype != "feetype10") {
					continue;
				} 
				data[i].paymentnumber = "第" + data[i].paymentid + "笔付款计划，类型为"+data[i].feetypename+"金额为"
						+ data[i].planmoney + "，付款日:" + data[i].plandate;
				rowDatas.push(mini.clone(data[i]));
			}
		}
	}
	getMiniEditFormField("contract_premise.paymentid").setData(rowDatas);
}
//付款编号变更赋值
function onPaymentidChanged(e) {
 	var rowData = e.selected;
 	number = rowData.paymentid;
 	var data = mini.get("fund_fund_plan").getData();
	getMiniEditFormField("contract_premise.paymentnumber").setValue(
			rowData.paymentid);
	getMiniEditFormField("contract_premise.feetype").setValue(rowData.feetype);
	getMiniEditFormField("contract_premise.feetypename").setValue(
			rowData.feetypename);
	getMiniEditFormField("contract_premise.premisemoney").setValue(
			rowData.planmoney);
	getMiniEditFormField("contract_premise.paytype").setValue(
			rowData.settlemethodname);
	//前提条件放入数据
		$.ajax({
			url:"<%=request.getContextPath() %>/table/getTableData.action?xmlFileName=/eleasing/jsp/sysmgr/sysdatamgr/payment_rhythm.xml",
					cache:false,
					data:{
			vndrtypes:vndrType,
			paymentnum:number
				},	       
			success: function (text){
				//获得商务条件里的付款对象
				for(var i=0;i<data.length;i++){
		           	if(data[i].feetype=='feetype10' && number==data[i].paymentid){
		           	    mini.getbyName('paycust').setValue(data[i].paycustname);
		           	}
		           }  
		            var foo = mini.decode(text);
		            var ary   = foo.datas;
		           for(var k=0;k<ary.length;k++){
		        	   premises =ary[k].termspayment;
		           }
		           
		  getMiniEditFormField("contract_premise.premise").setValue(premises.replace(/<br \/>/g,"\r\n"));
				 } 	      
		       });
	 
}
//贸易类型变更赋值 
function onVndrtypeChanged(e){
	var rowData = e.selected;
	vndrType=rowData.value; 
   if(null == number){
	   return;
   }else{
	   $.ajax({
			url:"<%=request.getContextPath() %>/table/getTableData.action?xmlFileName=/eleasing/jsp/sysmgr/sysdatamgr/payment_rhythm.xml",
					cache:false,
					data:{
			vndrtypes:vndrType,
			paymentnum:number
				},	       
			success: function (text){
		            var foo = mini.decode(text);
		            var ary   = foo.datas;
		           for(var k=0;k<ary.length;k++){
		        	   premises =ary[k].termspayment;
		           }
		  getMiniEditFormField("contract_premise.premise").setValue(premises.replace(/<br \/>/g,"\r\n"));
				 } 	      
		       });
   }
}
</script>

<div id="id_table_contract_premise_container"></div>