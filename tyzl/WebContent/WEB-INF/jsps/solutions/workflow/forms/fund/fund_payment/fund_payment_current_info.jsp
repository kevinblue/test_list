<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
/* 字段备注  ：   计划金额   = 本次收款金额 + 调整金额            */
jQuery(function(){
	//获取父页面中保存在hidden域的值
	var showTools = true;
	if('<mini:param  name="isView"/>' == 'true'){showTools = false;}
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
		id: "payment_current",
		renderTo: "id_table_payment_current",
		width : globalClientWidth-30,
		height : 400,
		lazyLoad: true,
		isClickLoad:false,
		title: "",
		remoteOper : false,
		showPager: false,
		showToolbar: showTools,
		tools: ['edit','-','remove'],
		validateForm: function(miniTable, miniForm){
			var comparemoney =getMiniEditFormField("payment_current.comparemoney").getValue();
			var factmoney =getMiniEditFormField("payment_current.factmoney").getValue();
			comparemoney=parseFloat(comparemoney).toFixed(2);
			factmoney=parseFloat(factmoney).toFixed(2);
			if (parseFloat(comparemoney) < parseFloat(factmoney))
			{
				mini.alert("本次付款金额不能大于计划付款金额!");
				return false;
			}
				return true;
		},
		afterShowWindowCallBack: function(miniTable,miniForm,operFlag){
			if("edit" == operFlag){
				if('<mini:param  name="isFinance" />' == 'true'){
					mini.getbyName("accountingdate").setRequired(true);
					mini.getbyName("accountbank").setRequired(true);
				}
			}
		},		
		data: JsonUtil.decode('<mini:param  name="json_payment_current_str" defaultValue="[]"/>'),
		columns: [
					{type: 'indexcolumn'},
					{type: 'checkcolumn'},
					{field: 'id', header: 'id', visible: false},
					{field:'fundfundchargeplan',header:'付款计划',visible: false},
					{field: 'paymentid', header: '付款编号',formEditorConfig:{readOnly : true}}, 
					{field: 'comparemoney', header: '比较金额', visible: false,formEditorConfig:{readOnly : true}}, 
					{field: 'feetype', header: '费用类型',formEditorConfig:{}, visible: false},
					{field: 'feetypename', header: '费用类型',formEditorConfig:{readOnly : true}},
					{field: 'paytype', header: '支付类型',formEditorConfig:{newLine:true}, visible: false},
					{field: 'paytypename', header: '支付类型', visible: false,formEditorConfig:{newLine:true,readOnly:true}},
					{field: 'factobjectname', header: '支付对象',formEditorConfig:{fieldVisible: false}},
					{field: 'factobject', header: '支付对象',visible: false,formEditorConfig:{
						newLine: true,
						type : 'queryinput',
						required: true,
						textField: 'name',
						valueField: 'name',
						allowInput: false,
						fieldVisible: true,
						width: 300,
						colspan:3,
						onSelect:function(){clearCustbank();},
						params: {
							xmlFileName: 'common/custInfo.xml'
						}
					}},
					{field: 'factmoney', header: '付款金额',summary : true,dataType : "currency",formEditorConfig:{newLine:true,required: true}},
					{field: 'settlemethod', header: '结算方式', visible: false,formEditorConfig:{
						type : 'combobox',
						required: true,
						textField: 'name',
						valueField: 'value',
						allowInput: true,
						fieldVisible: true,
						colspan : 3,	
						params: {
								pid: 'PayFund',
								xmlFileName: 'combos/comboDict.xml'
							
						}}},
					{field: 'settlemethodname', header: '结算方式',formEditorConfig:{fieldVisible: false,
						  fillFromFieldName : 'settlemethod',
						fillProperty : 'name'}},
							
					{field: 'factdate', header: '付款日期',formEditorConfig:{newLine:true,required: true,type:'date'}},
					{field: 'accountingdate', header: '会计处理日',formEditorConfig:{type:'date'}},
					{field: 'clientbankname', header: '对方银行',visible: false,formEditorConfig:{
					fillFromFieldName : 'clientbank',
					fillProperty : 'clientbank'}},
					{field: 'clientbank', header: '对方银行',
						formEditorConfig:
						{
							newLine:true,
							type : 'combobox', 
							required : true, 
							multiSelect : false, 
							valueField : 'clientbank', 
							textField : 'clientbank', 
							labelWidth : 100, 
							width : 200, 
							lazyLoad: true,
							onbuttonclick:'initCustbank',
							params : {
								xmlFileName : 'common/custbankInfo.xml',
								custname:'XXX'
							}
						}
					},   
					{field: 'clientaccount', header: '对方账户',
						formEditorConfig:{
							fillFromFieldName : 'clientbank',
							fillProperty : 'clientaccount',
								readonly:true
						}
					},
					{field: 'clientaccnumber', header: '对方账号',
						formEditorConfig:{
							fillFromFieldName : 'clientbank',
							fillProperty : 'clientaccnumber',
								readonly:true,
								newLine:true,
								colspan : 3
						}
					},
					{field: 'ffcmemo', header: '备注',formEditorConfig:{
						type: 'textarea',
						newLine:true,
						colspan : 3,
						width: 300,
						height:70
					}}
	
		]
	});
	});
	
 
});
function initCustbank(){
    var factobj=getMiniEditFormField("payment_current.factobject");
    var clientbank=getMiniEditFormField("payment_current.clientbank");
    fact=factobj.getText();
    if(fact==""){alert("请选择输付款对象");return false;}
    var param={};
    param["custname"]=fact;
    seajs.use([ "js/apcomponent/aputil/aputil.js" ],function(ApUtil){
    	 ApUtil.setComboxParams(clientbank,param);
     });
}
function clearCustbank(){
	getMiniEditFormField("payment_current.clientaccount").setValue("");
	getMiniEditFormField("payment_current.clientaccnumber").setValue("");
	getMiniEditFormField("payment_current.clientbank").setValue("");
	getMiniEditFormField("payment_current.clientbank").setText("");
}
</script>

<div id="id_table_payment_current"style="width:100%;height:100%;"></div>
