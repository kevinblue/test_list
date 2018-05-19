<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<input name="contract_condition.id" id="contract_condition.id" type="hidden" value="${requestScope['contract_condition.id']}" />
<div id="commerce_clause_change" title="商务条款变更">

	<div class="fillTableContainer">
           <div class="mini-panel" title="变更前商务条款信息"  style="width:50%;float:left">
			  <table cellspacing="0" cellpadding="0" width="100%" class="fillTable" >
					 <tr class="tr-project-info tr-odd">
			              <td class="td-content-title" >调息生效节点：</td>
			              <td class="td-content">
			              	  <input id="beforeadjuststyle" name="beforeadjuststyle" class="mini-combobox" textField="name" 
				                  	   valueField="value"  readonly
									   dataField="datas"  
									   allowInput="false" 
									   data-options="{_params:{pid:'adjust_style'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
									   onvaluechanged="comboboxChanged"
									   showNullItem="true" 
								   	   nullItemText=""
								   	   value="${empty requestScope['beforeadjuststyle'] ? 'no_adjust' : requestScope['beforeadjuststyle'] }" 
								   	   text="${empty requestScope['beforeadjuststylename'] ? '固定利率' : requestScope['beforeadjuststylename'] }"
							  />	
						  </td>
			         </tr>
			         <tr class="tr-project-info tr-odd">
			              <td class="td-content-title">罚息利率（万分比）：</td>
			              <td class="td-content">
			              		<input id="beforepenarate" name="beforepenarate" class="mini-combobox" textField="name" 
				                  	   valueField="value"  readonly
									   dataField="datas"  
									   allowInput="false" 
									   data-options="{_params:{pid:'pena_rate'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
									   onvaluechanged="comboboxChanged"
									   showNullItem="true" 
								   	   nullItemText=""
								   	   value="${empty requestScope['beforepenarate'] ? '5' : requestScope['beforepenarate'] }" 
								   	   text="${empty requestScope['beforepenarate'] ? '5' : requestScope['beforepenarate'] }" 
							  />	
							</td>
			          </tr>
			         <tr class="tr-project-info tr-odd">
			         	  <td class="td-content-title">保证金：</td>   
			              <td class="td-content"><input name="beforecautionmoney" vtype="double" label="保证金" id ="beforecautionmoney"  class="mini-textbox"  value="${empty requestScope['cautionmoney'] ? 0 : requestScope['beforecautionmoney'] }" readonly></td>
			         </tr>
			         <tr class="tr-project-info tr-odd">
			         	<td class="td-content-title">保证金抵扣金额：</td> 
			            <td class="td-content"><input name="beforecautiondeductionmoney" label="保证金抵扣金额" vtype="double"  id ="beforecautiondeductionmoney"  class="mini-textbox" value="${empty requestScope['cautiondeductionmoney'] ? 0 : requestScope['beforecautiondeductionmoney'] }" readonly></td>
			          <tr class="tr-project-info tr-odd">
			             <td class="td-content-title">保证金退还金额：</td>
			             <td class="td-content"><input name="beforecautionmoneyremain" label="保证金退还金额" vtype="double"  id ="beforecautionmoneyremain"  class="mini-textbox" value="${empty requestScope['cautionmoneyremain'] ? 0 : requestScope['beforecautionmoneyremain'] }" readonly></td>
			          </tr> 
				</table>
          </div>
          <div class="mini-panel" title="变更后商务条款信息"  style="width:50%;float:left">
			  <table cellspacing="0" cellpadding="0" width="100%" class="fillTable" >
					  <tr class="tr-project-info tr-odd">
			              <td class="td-content-title" >调息生效节点：</td>
			              <td class="td-content">
			              	  <input id="adjuststyle" name="adjuststyle" class="mini-combobox" textField="name" 
				                  	   valueField="value"  
									   dataField="datas"  
									   allowInput="false" 
									   data-options="{_params:{pid:'adjust_style'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
									   onvaluechanged="comboboxChanged"
									   showNullItem="true" 
								   	   nullItemText=""
								   	   value="${empty requestScope['adjuststyle'] ? 'no_adjust' : requestScope['adjuststyle'] }" 
								   	   text="${empty requestScope['rawValue_adjuststyle'] ? '固定利率' : requestScope['rawValue_adjuststyle'] }"
							  />	
							  <input id="rawValue_adjuststyle" style="display:none" name="rawValue_adjuststyle" value="${requestScope['rawValue_adjuststyle']}" class="mini-textbox" />
			              </td>
			         </tr>
			         <tr class="tr-project-info tr-odd">
			              <td class="td-content-title">罚息利率（万分比）：</td>
			              <td class="td-content">
			              		<input id="penarate" name="penarate" class="mini-combobox" textField="name" 
				                  	   valueField="value"  
									   dataField="datas"  
									   allowInput="false" 
									   data-options="{_params:{pid:'pena_rate'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
									   onvaluechanged="comboboxChanged"
									   showNullItem="true" 
								   	   nullItemText=""
								   	   value="${empty requestScope['penarate'] ? '5' : requestScope['penarate'] }" 
								   	   text="${empty requestScope['rawValue_penarate'] ? '5' : requestScope['rawValue_penarate'] }" 
							  />	
							  <input id="rawValue_penarate" style="display:none" name="rawValue_penarate" value="${requestScope['rawValue_penarate']}" class="mini-textbox" />
			              </td>
			          </tr>
			         <tr class="tr-project-info tr-odd">
			         	  <td class="td-content-title">保证金：</td>   
			              <td class="td-content"><input name="cautionmoney" vtype="double" label="保证金" id ="cautionmoney"  class="mini-textbox"  value="${empty requestScope['cautionmoney'] ? 0 : requestScope['cautionmoney'] }" readonly></td>
			         </tr>
			         <tr class="tr-project-info tr-odd">
			         	<td class="td-content-title">保证金抵扣金额：</td> 
			            <td class="td-content"><input name="cautiondeductionmoney" label="保证金抵扣金额" vtype="double"  id ="cautiondeductionmoney" onkeyup="cautionmoneyremainvaluechange" onvaluechanged="cautionmoneyremainvaluechange"  class="mini-textbox" value="${empty requestScope['cautiondeductionmoney'] ? 0 : requestScope['cautiondeductionmoney'] }"></td>
			          <tr class="tr-project-info tr-odd">
			             <td class="td-content-title">保证金退还金额：</td>
			             <td class="td-content"><input name="cautionmoneyremain" label="保证金退还金额" vtype="double"  id ="cautionmoneyremain"  class="mini-textbox" value="${empty requestScope['cautionmoneyremain'] ? 0 : requestScope['cautionmoneyremain'] }" readonly></td>
			          </tr> 
				</table>
          </div>
	           
	</div>
</div>
<script type="text/javascript">
function $minigetinputtext(id){
	var value = mini.get(id).getInputText();
	try {
    	value = (value+'').replace(/,/g,'');
    	value = value == '' ? '0' : value;
    	value = parseFloat(value);
    } catch(e) {
    	value = 0;
    }
    return value;
}
function $mini(id){
	return mini.get(id);
}
//计算保证金退还金额
function cautionmoneyremainvaluechange(e){
	var cautiondeductionratio = "cautiondeductionratio";
	var cautionmoney = "cautionmoney";
	var cautiondeductionmoney = "cautiondeductionmoney";
	var cautionmoneyremain = "cautionmoneyremain";
	var fac = "";
	if(e.sender.id != 'cautiondeductionmoney'){
		cautiondeductionratio = "faccautiondeductionratio";
		cautionmoney = "faccautionmoney";
		cautiondeductionmoney = "faccautiondeductionmoney";
		cautionmoneyremain = "faccautionmoneyremain";
		fac = "厂商";
	}
	
	var cautionmoneyM = $minigetinputtext(cautionmoney);//保证金
	var cautiondeductionmoneyM = $minigetinputtext(cautiondeductionmoney);//保证金抵扣金额
	var cautionmoneyremainField = mini.get(cautionmoneyremain);//保证金退还金额
// 	if("" == cautionmoney || "" == cautiondeductionmoney){
// 		cautionmoneyremain.setValue("");
// 		return;
// 	}
	if(!isNumber(cautiondeductionmoneyM)){
		mini.get(cautiondeductionmoney).setValue(cautionmoneyM);
		cautionmoneyremainField.setValue('0');
		return;
	}else{
		if(Number(cautiondeductionmoneyM) < 0 ){
			//mini.alert(fac+'保证金退还金额需大于0！');
			mini.get(cautiondeductionmoney).setValue(cautionmoneyM);
			cautionmoneyremainField.setValue('0');
			return;
		}
		if(Number(cautiondeductionmoneyM) > Number(cautionmoneyM) ){
			//mini.alert(fac+'保证金退还金额需小于等于'+fac+'保证金金额');
			mini.get(cautiondeductionmoney).setValue(cautionmoneyM);
			setformatvalue($mini(cautiondeductionmoney));
			cautionmoneyremainField.setValue('0');
			return;
		}
	}
	var cautionmoneyremainvalue = decimal(cautionmoneyM - cautiondeductionmoneyM,2);
	cautionmoneyremainField.setValue(cautionmoneyremainvalue);
	setformatvalue($mini(cautionmoney));
	setformatvalue($mini(cautiondeductionmoney));
	setformatvalue($mini(cautionmoneyremain));
}
/* jQuery(function(){	
	 var showTools = true;
	 if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;};
	 if(showTools==false){
	 	miniui_ext.disableFormFields("contract_rent_invoice_type_form");
	 } 
	 if(mini.get("taxregtype").getValue()=="tax_level_name.1"){
		 mini.get("taxregcode").setRequired(true);
	 }else{
		 mini.get("taxregcode").setRequired(false);
	 }
	 miniui_ext.disableFormFields("rentinvoicetype");
	 miniui_ext.disableFormFields("invoicetype");
	 taxregtypeonvaluechanged();
});
 */

</script>