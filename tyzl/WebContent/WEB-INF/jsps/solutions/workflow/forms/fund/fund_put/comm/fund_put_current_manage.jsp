<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
jQuery(function(){
	//获取父页面中保存在hidden域的值
	var showTools = true;
	if('<mini:param  name="isView"/>' == 'true'){showTools = false;};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
		id: "put_current",
		renderTo: "id_table_put_current",
		width : globalClientWidth-10,
		height : 300,
		editFormPopupWindowWidth : 600,
		lazyLoad: false,
		isClickLoad:false,
		title: "",
		remoteOper : false,
		showPager: false,
		showToolbar: showTools,
		multiSelect: true,
		tools: ['edit','-','remove','-',{
			html : '批量修改银行账号',
			plain : true,
			iconCls : 'icon-save',   
			handler : function(miniTable, buttonText) {
				mini.get("id_select_pay_account").show();
			}
		  },
		'-',{
			html : '删除银行账号',
			plain : true,
			iconCls : 'icon-save',   
			handler : function(miniTable, buttonText) {
				deleteFundPayCountInfo();
			}
		}
		],
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
// 		removeOperCallBack:function(miniTable, rowDatas){
// 	           var guarantable=mini.get("caution_money_refund_detail");
// 	           var guarantableData=guarantable.getData();
// 			   for(j=0;j<rowDatas.length;j++){
// 	               var pid=rowDatas[j].pid;              
// 	               for(var i=0;i<guarantableData.length;i++){
// 	                    if(guarantableData[i].fundfundchargeplan==pid){
// 	                    	guarantable.removeRow (guarantableData[i]);
// 	                    }
// 	               }	               
// 			   }
// 			   guarantable=mini.get("rent_income_detail");
// 	           guarantableData=guarantable.getData();
// 			   for(j=0;j<rowDatas.length;j++){
// 	               var pid=rowDatas[j].pid;              
// 	               for(var i=0;i<guarantableData.length;i++){
// 	                    if(guarantableData[i].planid==pid){
// 	                    	guarantable.removeRow (guarantableData[i]);
// 	                    }
// 	               }	               
// 			   }
// 		       return true;
// 	    },
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
				colspan:3,
				onSelect:function(){clearCustbank();},
				params: {
					xmlFileName: 'common/custInfo.xml'
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
			{field: 'factdate', header: '投放日期',formEditorConfig:{newLine:true,required: true,type:'date',colspan:3}},
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
	var put_current_value = mini.get("id_json_put_current_str").getValue();
	var put_current_data =  mini.decode(put_current_value);
	var chargeData= mini.get("put_current").isinitData==1 ? 
			mini.get("put_current").getData() : put_current_data;
	if( chargeData.length == 0)
	{
		mini.alert("本次投放明细不能为空!");
		return false;
	}
	var allput=0;
	for(var i=0;i<chargeData.length;i++){
		allput=parseFloat(allput)+parseFloat(chargeData[i].factmoney);
	}
	var putcurequipamt=mini.get("putcurequipamt").getValue();
	if(parseFloat(putcurequipamt)!=parseFloat(allput)){
		mini.alert("投放金额与投放明细总金额不相等，不能提交！！！");
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
   //var rentData=mini.get("rent_income_detail").getData();
   if(payData.length>0){
       for(var i=0;i<payData.length;i++){
            for(var j=0;j<fundData.length;j++){
                if(payData[i].fundfundchargeplan==fundData[j].fundfundchargeplan&&(parseFloat(payData[i].factmoney)!=parseFloat(fundData[j].factmoney))){
                    mini.alert("设备款("+payData[i].factmoney+")与被抵扣的"+fundData[j].feetypename+"("+fundData[j].factmoney+")不一样");
                      return false;
                 }
            }
//             for(var h=0;h<rentData.length;h++){
//             	   if(payData[i].pid==rentData[h].planid&&(parseFloat(payData[i].factmoney)!=parseFloat(rentData[h].rent))){
//                        mini.alert("设备款("+payData[i].factmoney+")与被抵扣的租金("+rentData[h].rent+")不一样");
//                          return false;
//                     }
//             }
       }
       return true;
   }else{
     return true;
   }
}
function checkFundPayAccount(){
	 var payData=mini.get("put_current").getData();
	 var message=[];
	 var payObj="";
	 var clientbankname="";
	 var paytype="";
	 if(payData.length>0){
		 for(var i=0;i<payData.length;i++){
			 pay=payData[i].factobject||"";
			 clientbankname=payData[i].clientbankname||"";
			 paytype=payData[i].settlemethodname||"";
			 if(paytype=="货扣"){
				 if(pay==""){
					 message.push("设备款["+payData[i].factmoney+"]支付对象没有填<br>");
				 }
			 }else{
			 if(pay==""||clientbankname==""){
				 message.push("设备款["+payData[i].factmoney+"]支付对象或对方银行没有填<br>");
			 }}
		 }
		 if(message.length>0){
			 mini.alert(message,"本次投放明细");
			 selectChangeTab("tabApprovalDeatils","put_current");
			 return false;
		 }
	 }
	 return true;
}
</script>
<div id="id_table_put_current"></div>
<div id="id_table_put_current"></div>
 <div id="id_select_pay_account" class="mini-window"   closed="true" modal="true" title="选择支付对象和银行账号" style="display:none;width:350px;padding-top:20px;">
       <table lass="fillTable" cellspacing="0" cellpadding="0" align="center" >
              <tr class="tr-pay_account tr-even" style="height:40px">
	             <td class="td-content-title" width="25%">支付对象：</td>
	             <td class="td-content">
                      <input id="id_select_person" name="selectperson" label="支付对象" class="mini-buttonedit mini-queryinput" allowInput="false" required="true"/>
					 <font class="required-tag">*</font>
                 </td>
	          </tr  >
	          <tr class="tr-project-info tr-even" style="height:40px">
	          <td class="td-content-title" width="25%">对方银行：</td>
	              <td class="td-content" > <input id="id_select_bank" name="selectbank"   class="mini-combobox"  label="对方银行"  required="true" allowInput="false"/>
	              <font class="required-tag">*</font></td>
	          </tr >
	          <tr class="tr-project-info tr-even" style="height:40px">
	          <td class="td-content-title" width="25%">对方账户：</td>
	             <td class="td-content" > <input id="id_select_account" name="selectaccount" class="mini-textbox"  readOnly  label="对方账户"  required="true" allowInput="false"/> <font class="required-tag">*</font></td>
	          </tr>
	          <tr  class="tr-project-info tr-even" style="height:40px">
	          <td class="td-content-title" width="25%">对方账号：</td>
	             <td class="td-content" > <input id="id_select_accnumber" name="selectaccnumber" class="mini-textbox"   readOnly  label="对方账号"  required="true" allowInput="false"/> <font class="required-tag">*</font> </td>
	          </tr >
               <tr class="content-separator">                 
				      <td colspan='2' align="center">
				           <a  style="margin-left:20px;"  class="mini-button" iconCls="icon-add" onclick='setFundPutInfo();'><span>确定</span></a>
						   <a  style="margin-left:20px;"  class="mini-button" iconCls="icon-close" onclick='mini.get("id_select_pay_account").hide();'><span>取消</span></a>
				      </td>
			   </tr>
       </table>
 </div>
 <script language="javascript">
jQuery(function(){
	if('<mini:param  name="isView"/>' == 'true' || isViewHistoryTask==true){
	}else{	
		tenwa.createQueryInput({
			id:'id_select_person',
			label:'选择付款对象',
			textField: 'name',
			valueField: 'name',
			windowWidth: 600,
			onSelect: function($me, inputObj, rowData){
			    mini.get("id_select_account").setValue("");
			    mini.get("id_select_accnumber").setValue("");
	            var clientbank= mini.get("id_select_bank")
	            fact=rowData.name;
	            if(fact==""){alert("请选择付款对象");return false;}
	            var param={};
	            param["custname"]=fact;
	            param["xmlFileName"]="common/custbankInfo.xml";
	      	    ajaxRequest({
	      	      url:getRootPath()+'/table/getTableData.action',
	      	      async:false,
	      	      params:param,
	      	      timeout:60*60*1000,
	      	      success:function(res){
	      	          var currentData = mini.decode(res.responseText);
	      	          var cdata=mini.decode(currentData.datas);
	      	          mini.get('id_select_bank').set({
						textField : "clientbank",
						valueField : "clientbank",
						data:cdata,	
						onValueChanged:function(){
							var selecteds = this.getSelecteds();
					        var selected = selecteds[0];//单选只有一个
							mini.get("id_select_account").setValue(selected.clientaccount);
							mini.get("id_select_accnumber").setValue(selected.clientaccnumber);
						}
					 });
	      	      }});    	
			},
			params: {
				xmlFileName: 'common/custInfo.xml'
			}
		});
	};
});
function setFundPutInfo(){
	 var curTable=mini.get("put_current")
	 var payData=curTable.getData();
	 var selectData=curTable.getSelecteds();
	 var payobj=mini.get("id_select_person").getValue();
	 var paybank=mini.get("id_select_bank").getValue();
	 var payaccount=mini.get("id_select_account").getValue();
	 var payaccnumber=mini.get("id_select_accnumber").getValue();
	 if(selectData.length>0){
		 for(var i=0;i<selectData.length;i++){
			 var cm={"factobject":payobj,"factobjectname":payobj,"clientbank":paybank,"clientbankname":paybank,"clientaccount":payaccount,"clientaccnumber":payaccnumber}
			 if(selectData[i].settlemethodname=="货扣"){
				 cm={"factobject":payobj,"factobjectname":payobj}
			 }
			 curTable.updateRow(selectData[i],cm);
		 }
	 }else{
	    for(var i=0;i<payData.length;i++){
	    	 var cm={"factobject":payobj,"factobjectname":payobj,"clientbank":paybank,"clientbankname":paybank,"clientaccount":payaccount,"clientaccnumber":payaccnumber}
	    	 if(payData[i].settlemethodname=="货扣"){
				 cm={"factobject":payobj,"factobjectname":payobj}
			 }
	    	 curTable.updateRow(payData[i],cm);
	    }
	 }
	 curTable.deselectAll();
	 mini.get("id_select_pay_account").hide();
}
function deleteFundPayCountInfo(){
	var curTable=mini.get("put_current")
	var payData=curTable.getData();
	var cm={"factobject":"","factobjectname":"","clientbank":"","clientbankname":"","clientaccount":"","clientaccnumber":""}
	var selectData=curTable.getSelecteds();
	if(selectData.length>0){
		 for(var i=0;i<selectData.length;i++){
			 curTable.updateRow(selectData[i],cm);
		 }
	}else{
	for(var i=0;i<payData.length;i++){
		curTable.updateRow(payData[i],cm);
    }}
	 curTable.deselectAll();
	 mini.alert("操作成功");
}
</script>		