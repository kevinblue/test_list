<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
var premises =null;
var number= null;
var leaseform = "${requestScope['proj_info.leasform'] }";
jQuery(function (){
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;}
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "proj_premise",
			renderTo:"id_table_proj_premise_container",
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
			//转换特殊符号函数
			validateForm:function(miniTable, miniForm, editFormItemOperFlag){
				alert(number);
				if(editFormItemOperFlag=="add"||editFormItemOperFlag=="edit"){
					var prem = mini.getbyName("premise").getValue().replace(/\r\n/g,"<br />");
					mini.getbyName("premise").setValue(prem);
				}
					return true;
			},
			afterShowWindowCallBack:function(miniTable,miniForm,operFlag){
				if("edit" == operFlag){
					var row = mini.get("proj_premise").getSelected(); 
					getMiniEditFormField("proj_premise.paymentid").setValue(row.paymentid);
					getMiniEditFormField("proj_premise.paymentid").setText(row.paymentid);
				}
			},
			
			data: JsonUtil.decode('${empty json_proj_premise_str ? "[]" : json_proj_premise_str }'),
			columns: [
						{type:'indexcolumn'},
						{type:'checkcolumn'},
						{field: 'id', header: 'id',visible:false,formEditorConfig : {fieldVisible: false}},
						{field: 'paytype', header: '支付方式',visible:false,formEditorConfig : {fieldVisible: false}},
						{field: 'paymentnumber', header: '付款编号',formEditorConfig:{fieldVisible: false}},
						{field: 'paymentid', header: '收付款编号',formEditorConfig : {
							newLine: true,
							type : 'combobox',
							required: true,
							textField: 'paymentnumber',
							valueField: 'paymentnumber',
							allowInput: false,
							fieldVisible: true,
							//lazyLoad: true,
							colspan: 3,
							width: '100%',
							onbeforeshowpopup: 'getPaymentidData',
							data: [],
							 onvaluechanged: 'onPaymentidChanged'
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
						{field: 'feetypename', header: '费用类型',formEditorConfig:{
							readOnly:true,
							fieldVisible:true,
							allowInput:false,
							fillFromFieldName:'feetype',
							fillProperty:'name'
						}},
						{field: 'premisemoney', header: '金额',formEditorConfig : {allowInput:false,readOnly:true}},
						{field: 'premise', header: '前提条件',type:"textarea",formEditorConfig : {
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
function getPaymentidData() {
	var putCurrData = mini.get("proj_premise").getData();
	
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
			
			if(flag == false)
			{
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
	 getMiniEditFormField("proj_premise.paymentid").setData(rowDatas); 
}
//付款编号变更赋值
function onPaymentidChanged(e){
 	var rowData = e.selected;
 	number =rowData.paymentid;
	 getMiniEditFormField("proj_premise.paymentnumber").setValue(
			rowData.paymentid); 
	getMiniEditFormField("proj_premise.feetype").setValue(rowData.feetype);
	getMiniEditFormField("proj_premise.feetypename").setValue(
			rowData.feetypename);
	getMiniEditFormField("proj_premise.premisemoney").setValue(
			rowData.planmoney);
	 getMiniEditFormField("proj_premise.paytype").setValue(
			rowData.settlemethodname); 
     //ajax查询数据库信息
	 $.ajax({
			url:"<%=request.getContextPath() %>/table/getTableData.action?xmlFileName=/eleasing/jsp/sysmgr/sysdatamgr/payment_rhythm.xml",
					cache:false,
					data:{
			leaseforms:leaseform,
			paymentnum:number
				},	       
			success: function (text){
		            var foo = mini.decode(text);
		            var ary   = foo.datas;
		           for(var k=0;k<ary.length;k++){
		        	   premises =ary[k].termspayment;
		           }
		  getMiniEditFormField("proj_premise.premise").setValue(premises.replace(/<br \/>/g,"\r\n"));
				 } 	      
		       });
}

</script>
<div id="id_table_proj_premise_container"></div>