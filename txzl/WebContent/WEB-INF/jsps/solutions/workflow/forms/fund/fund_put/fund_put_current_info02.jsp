<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	//获取父页面中保存在hidden域的值
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
		id: "put_current",
		renderTo: "id_table_put_current",
		width : globalClientWidth-10,
		height : 300,
		editFormPopupWindowWidth : 600,
		lazyLoad: true,
		isClickLoad:false,
		title: "",
		remoteOper : false,
		showPager: false,
		showToolbar: showTools,
		multiSelect: true,
		tools: ['copy','-','remove'],
		validateForm: function(miniTable, miniForm){
			var comparemoney =getMiniEditFormField("put_current.comparemoney").getValue();
			var factmoney =getMiniEditFormField("put_current.factmoney").getValue();
			comparemoney=parseFloat(comparemoney).toFixed(2);
			factmoney=parseFloat(factmoney).toFixed(2);
			if (parseFloat(comparemoney) < parseFloat(factmoney))
			{
				mini.alert("本次投放金额不能大于计划可投放金额!("+comparemoney+")");
				return false;
			}
				return true;
		},
		removeOperCallBack:function(miniTable, rowDatas){
	           var guarantable=mini.get("caution_money_refund_detail");
	           var guarantableData=guarantable.getData();
			   for(j=0;j<rowDatas.length;j++){
	               var pid=rowDatas[j].pid;              
	               for(var i=0;i<guarantableData.length;i++){
	                    if(guarantableData[i].fundfundchargeplan==pid){
	                    	guarantable.removeRow (guarantableData[i]);
	                    }
	               }	               
			   }
			   guarantable=mini.get("rent_income_detail");
	           guarantableData=guarantable.getData();
			   for(j=0;j<rowDatas.length;j++){
	               var pid=rowDatas[j].pid;              
	               for(var i=0;i<guarantableData.length;i++){
	                    if(guarantableData[i].planid==pid){
	                    	guarantable.removeRow (guarantableData[i]);
	                    }
	               }	               
			   }
		       return true;
	    },
		data: JsonUtil.decode('${empty json_put_current_str ? "[]" : json_put_current_str }'),
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'pid', header: 'pid', visible: false},
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
				onSelect:function(){clearCustbank();},
				params: {
					xmlFileName: 'common/custInfo.xml'
				}
			}},
			{field: 'customertypename', header: '客户类型',
				formEditorConfig:{
					fieldVisible: false,
					fillFromFieldName : 'customertype',
					fillProperty : 'name'
					}},
			{field: 'customertype', header: '客户类型',visible: false,formEditorConfig:{
				type : 'combobox',
				textField: 'name',
				fieldVisible: true,
				valueField:'value',
				params: {
					pid:'proj_type',xmlFileName: '/combos/comboDict.xml'
				}
			}},
			{field: 'factmoney', header: '投放金额',summary : true,dataType : "currency",formEditorConfig:{newLine:true,required: true}},
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
					
			{field: 'factdate', header: '投放日期',formEditorConfig:{newLine:true,required: true,type:'date'}},
			{field: 'accountingdate', header: '会计处理日',formEditorConfig:{type:'date'}},
			{field: 'accountbankname', header: '对方银行',visible: false,formEditorConfig:{
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
					onclick:'initCustbank',
					onbuttonclick:'initCustbank',
					onbeforeshowpopup:'initCustbank',
					lazyLoad: true,
					params : {
						xmlFileName : 'common/custbankInfo.xml',
						custname:'xxxxx'
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
				width: 400,
				height:70
			}}

		]
	});
	});
	 
});
function initCustbank(){
    var factobj=getMiniEditFormField("put_current.factobject");
    var clientbank=getMiniEditFormField("put_current.clientbank");
    fact=factobj.getText();
    if(fact==""){alert("请选择付款对象");return false;}
    var param={};
    param["custname"]=fact;
    param["xmlFileName"]="common/custbankInfo.xml",
    seajs.use([ "js/apcomponent/aputil/aputil.js" ],function(ApUtil){
    	 ApUtil.setComboxParams(clientbank,param);
     });
}
function checkFundPutInfo()
{	
	var chargeData= mini.get("put_current").getData()
	if( put_current.length == 0)
	{
		mini.alert("本次投放明细不能为空!");
		return false;
	}
}
function clearCustbank(){
	getMiniEditFormField("put_current.clientaccount").setValue("");
	getMiniEditFormField("put_current.clientaccnumber").setValue("");
	getMiniEditFormField("put_current.clientbank").setValue("");
	getMiniEditFormField("put_current.clientbank").setText("");
}
//检查抵扣的金额和被抵扣金额是不是一样
function CheckDecdueMoney(){
   var payData=mini.get("put_current").getData();
   var fundData=mini.get("caution_money_refund_detail").getData();
   var rentData=mini.get("rent_income_detail")?mini.get("rent_income_detail").getData():[];
   if(payData.length>0){
       for(var i=0;i<payData.length;i++){
            for(var j=0;j<fundData.length;j++){
                if(payData[i].pid==fundData[j].fundfundchargeplan&&(parseFloat(payData[i].factmoney)!=parseFloat(fundData[j].factmoney))){
                    mini.alert("设备款("+payData[i].factmoney+")与被抵扣的"+fundData[j].feetypename+"("+fundData[j].factmoney+")不一样");
                      return false;
                 }
            }
            for(var h=0;h<rentData.length;h++){
            	   if(payData[i].pid==rentData[h].planid&&(parseFloat(payData[i].factmoney)!=parseFloat(rentData[h].rent))){
                       mini.alert("设备款("+payData[i].factmoney+")与被抵扣的租金("+rentData[h].rent+")不一样");
                         return false;
                    }
            }
       }
       return true;
   }else{
     return true;
   }
}

</script>
<div id="id_table_put_current"></div>
