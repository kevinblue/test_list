<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
/* 字段备注  ：   计划金额   = 本次收款金额 + 调整金额            */
jQuery(function(){
	//获取父页面中保存在hidden域的值
	var contractid = "${requestScope['contract_info.id']}";
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
		id: "payment_current",
		renderTo: "id_table_payment_current",
		width : globalClientWidth-30,
		height : 400,
		lazyLoad: false,
		isClickLoad:false,
		title: "",
		remoteOper : false,
		showPager: false,
		showToolbar: showTools,
		tools: ['edit','-','remove','-','copy'],
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
			
			  var purpose=getMiniEditFormField("payment_current.purpose").getValue();
			  if(getLength(purpose)>25){
				  mini.alert("支付用途不能超过25个字符或8个汉字");
				  return false;
			  } 
			  var clientaccnum=getMiniEditFormField("payment_current.clientaccnumber").getValue();
			  if(clientaccnum==""||clientaccnum==null){
				  mini.alert("付款账号不能为空！");
				  return false;
			  }
			  var bankshortname=getMiniEditFormField("payment_current.depositbanktype").getValue();
			  if(bankshortname==""||bankshortname==null){
				  mini.alert("收款方银行简称不能为空！");
				  return false;
			  }
				return true;
		},
		afterShowWindowCallBack: function(miniTable,miniForm,operFlag){
			if("edit" == operFlag){
				if('${param.isFinance}' == 'true'){
					mini.getbyName("accountingdate").setRequired(true);
					mini.getbyName("accountbank").setRequired(true);
				}
			}
		},		
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
		data: JsonUtil.decode('${empty json_payment_current_str ? "[]" : json_payment_current_str }'),
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
						valueField:'value',
						allowInput: false,
						fieldVisible: true,
						width: 215,
						onSelect:function(combo,rowData){
							var rowDatas = combo.getSelectedOptionData();
							var customer = rowDatas.customertypename;
							var customertype = rowDatas.customertype;
							var objid = rowDatas.value;
							mini.getbyName("factobjectid").setValue(objid);
							 if(customertype==""){
								mini.getbyName("customertype").setValue("1");
							}else{ 
							mini.getbyName("customertype").setText(customer);
							mini.getbyName("customertype").setValue(customertype);
							mini.getbyName("customertypename").setValue(customer);
							}
							clearCustbank();
							},
						params: {
							"contractid":contractid,
							xmlFileName: 'common/factoringcustomertype.xml'
						}
						
					}},
					{field: 'customertype', header: '客户类型',visible: false,formEditorConfig:{
						type : 'combobox',
						textField: 'name',
						fieldVisible: true,
						allowInput: false,
						valueField:'value',
						params: {
							pid:'proj_type',
							xmlFileName: '/combos/comboDict.xml'
						}
					}},
					{field: 'customertypename', header: '客户类型',visible: true,
						formEditorConfig:{
							fieldVisible: false,
							fillFromFieldName : 'customertype',
							fillProperty : 'name'
							}},
					{field: 'factobjectid', header: 'factobjectid',visible: false,formEditorConfig:{
								type:'text',
								textField: 'factobjectid',
								fieldVisible: false,
								valueField:'factobjectid'
								
							}},
					{field: 'factmoney', header: '付款金额',summary : true,dataType : "currency",
								formEditorConfig:{
									newLine:true,
									vtype:"float",
									required: true
								}},
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
					{field: 'accountbankname', header: '本方银行',visible: false,formEditorConfig:{
						fillFromFieldName : 'accountbank',
						fillProperty : 'accbank'}},
					{field: 'accountbank', header: '本方银行',
						formEditorConfig:
						{
							newLine:true,
							type : 'combobox', 
							multiSelect : false, 
							valueField : 'accbank', 
							textField : 'accbank', 
							labelWidth : 100, 
							width : 200, 
							params : {
								xmlFileName : 'eleasing/jsp/sysmgr/sysdatamgr/ownaccountinfo.xml'  
							}
						}
					},   
					{field: 'account', header: '本方账户',
						formEditorConfig:{
							fillFromFieldName : 'accountbank',
							fillProperty : 'accname',
								readonly:true
						}
					},
					{field: 'accnumber', header: '本方账号',
						formEditorConfig:{
							fillFromFieldName : 'accountbank',
							fillProperty : 'accnumber',
								readonly:true,
								newLine:true,
								colspan : 3
						}
					},
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
							onbeforeshowpopup:'initCustbank',
							params : {
								xmlFileName : 'common/custbankInfo.xml',
								custname:'XXX'
							}
						}
					},
					{field: 'clientbankno', header: '对方行号',
						formEditorConfig:{
							fillFromFieldName : 'clientbank',
							fillProperty : 'clientbankno',
								readonly:true
						}
					},
					//银行简称--
					{field: 'depositbanktype', header: '对方银行简称',width:150,visible:true,
						formEditorConfig:{
							fillFromFieldName : 'clientbank',
							fillProperty : 'depositbanktype',
							newLine:true,
				   			readonly:true
						}
					},			
					//银联号--
					{field: 'unionclientbankno', header: '银联号',width:150,visible:true,
						formEditorConfig:{
							fillFromFieldName : 'clientbank',
							fillProperty : 'unionclientbankno',
				   			readonly:true
						}
					},
					//开户行省--
					{field: 'depositprovince', header: '开户行省',width:150,visible:true,
						formEditorConfig:{
							fillFromFieldName : 'clientbank',
							fillProperty : 'depositprovince',
							newLine:true,
				   			readonly:true
						}
					},
					//开户行市--
					{field: 'depositcity', header: '开户行市',width:150,visible:true,
						formEditorConfig:{
							fillFromFieldName : 'clientbank',
							fillProperty : 'depositcity',
				   			readonly:true
						}
					},
					
					{field: 'clientaccount', header: '对方账户',
						formEditorConfig:{
							newLine:true,
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
								colspan : 3
						}
					},
					{field: 'purpose', header: '用途',width:150,visible:true,
						formEditorConfig:{
							required : true,
							newLine:true
						}
					},
					{field: 'priorityflag', header: '加急标识',width:150,visible:true,
						formEditorConfig:{	
							showNullItem:true,
							visible:true,
							type:'combobox',
							textField:'text',
							valueField:'id',
							data:[{id:'Y',text:'Y'},{id:'N',text:'N'}]
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
    if(fact==""){mini.alert("请选择付款对象");return false;}
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
	getMiniEditFormField("payment_current.clientbankno").setValue("");
}
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
function getLength(str)   
{  
    var realLength = 0;  
    for (var i = 0; i < str.length; i++)   
    {  
        charCode = str.charCodeAt(i);  
        if (charCode >= 0 && charCode <= 128)   
        realLength += 1;  
        else   
        realLength += 3;  
    }  
    return realLength;  
}
</script>

<div id="id_table_payment_current"style="width:100%;height:100%;"></div>
