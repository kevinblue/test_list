<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	//获取父页面中保存在hidden域的值
	var showTools = true;
	if('${param.isView}' == 'true' || isViewHistoryTask ==true){showTools = false};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
		id: "plancharg_collect",
		renderTo: "id_table_plancharg_collect",
		width : '100%',
		height :'100%',
		editFormPopupWindowWidth : 400,
		lazyLoad: true,
		isClickLoad:false,
		title: "",
		remoteOper : false,
		showPager: false,
		showToolbar: showTools,
		tools: ['add','-','edit','-','remove'],
		data: JsonUtil.decode('${empty json_plancharg_collect_str ? "[]" : json_plancharg_collect_str }'),
		operValidate:function(miniTable, rowDatas, operFlag){
              if(operFlag=="edit" || operFlag=="remove"){
            	  if(!rowDatas || rowDatas.length==0){
            		  mini.alert("请选择要"+(operFlag == "edit"?"修改":"删除")+"的数据!");
            		  return false;
            	  }
                  var  factmoney=0.00;
                  if(operFlag=="edit"){factmoney=rowDatas.factmoney;}else{factmoney=rowDatas[0].factmoney;}
                  if(parseFloat(factmoney)>0){
                        mini.alert("已经收款过的资金计划不能修改或删除");
                        return false;
                      }
               }
			   return true;
	   },
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'paytype', header: '收付方向', visible: false, formEditorConfig:{defaultValue:'pay_type_in',fieldVisible: false}},
			{field: 'feetypename', header: '费用类型',visible: true, formEditorConfig:{			
				fieldVisible: false,
				fillFromFieldName : 'feetype',
				fillProperty : 'name'
			}},
			{field: 'feetype', header: '费用类型', visible: false,formEditorConfig:{
				type : 'combobox',
				labelWidth:100,
				required: true,
				textField: 'name',
				valueField: 'value',
				fieldVisible: true,
				params: {
				    pid: 'FeeType',
					xmlFileName: 'combos/comboDict.xml'
				}
			}},
			{field: 'paymentid', header: '收款编号',formEditorConfig:{labelWidth:100,required: true}},
			{field: 'paycustname', header: '支付对象',formEditorConfig:{fieldVisible: false}},
			{field: 'paycust', header: '支付对象',visible: false,formEditorConfig:{
				newLine: true,
				type : 'queryinput',
				required: false,
				textField: 'name',
				valueField: 'name',
				allowInput: false,
				fieldVisible: true,
				width: 300,
				colspan:3,
				params: {
					xmlFileName: 'common/custInfo.xml'
				}
			}},
			{field: 'planmoney', header: '计划收款金额',summary : true,dataType : "currency",formEditorConfig:{newLine:true,required: true,onkeyup: 'setFundCollectOver(e)'}},
			{field: 'factmoney', header: '实收款金额',summary : true,dataType : "currency",formEditorConfig:{defaultValue:'0.00',readOnly:true}},
			{field: 'overmoney', header: '计划收款余额',summary : true,dataType : "currency",formEditorConfig:{defaultValue:'0.00',newLine:true,readOnly:true}},
			{field: 'plandate', header: '收款日期',formEditorConfig:{
				required: true,
				type:'date'
			}},
			{field: 'planmoneystatus', header: '付款状态',formEditorConfig:{fieldVisible: false},renderer : function(e){
				  return setfundIncomeState(e.record);
	     	}},
			{field: 'fpnote', header: '备注',formEditorConfig:{newLine:true,colspan:3, type: 'textarea',
				width: 300,
				height:70}}
		]
	});
	});	 
}); 
function setFundCollectOver(e){
   var planmoney= e.sender.getInputText();
   var overmoney=getMiniEditFormField("plancharg_collect.overmoney");
   overmoney.setValue(planmoney);
}
</script>

<div id="id_table_plancharg_collect" style="width:100%;height:400px"></div>
