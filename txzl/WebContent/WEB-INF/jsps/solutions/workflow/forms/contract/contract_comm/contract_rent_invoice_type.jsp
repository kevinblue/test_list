<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<input name="contract_invoice_type.id" id="contract_invoice_type.id" type="hidden" value="${requestScope['contract_invoice_type.id']}" />
<div id="contract_rent_invoice_type_form" title="租金开票类型">
	<table cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;" class="fillTable">
		
		<%-- <tr class="tr-even">
			<td class="td-content-title">收到票据类型：</td>
			<td class="td-content" colspan="3">
				<input id="contract_invoice_type.receivedinvoicetype" name="contract_invoice_type.receivedinvoicetype" class="mini-combobox" label="收到票据类型"
					width="72%"
					textField="name"
					valueField="value"
					dataField="datas"
					allowInput="false"
					data-options="{_params:{pid:'received_invoice_type'}}" 
					onbeforeshowpopup="onbeforeshowpopup"
					value="${requestScope['contract_invoice_type.receivedinvoicetype']}"
					text="${requestScope['rawValue_contract_invoice_type.receivedinvoicetype']}"
					onvaluechanged="receivedInvoie" required="true"
				>
				<input id="rawValue_contract_invoice_type.receivedinvoicetype" name="rawValue_contract_invoice_type.receivedinvoicetype" value="${requestScope['rawValue_contract_invoice_type.receivedinvoicetype']}"
						class="mini-textbox" style="display: none" />
			</td>
			</tr> --%>
			<tr class="tr-odd">
			<td class="td-content-title">租金发票类型：</td>
			<td id="rentinvoicetype" class="td-content">
				<input id="contract_invoice_type.rentinvoicetype" name="contract_invoice_type.rentinvoicetype" class="mini-combobox" label="租金发票类型" style="width: 290px;" 
					textField="name"
					valueField="value"
					dataField="datas"
					allowInput="false"
					data-options="{_params:{pid:'rent_invoice_type'}}" 
				    onbeforeshowpopup="onbeforeshowpopup"
					value="${requestScope['contract_invoice_type.rentinvoicetype']}"
					text="${requestScope['rawValue_contract_invoice_type.rentinvoicetype']}"
					onvaluechanged="comboboxChanged"/>
					 <input id="rawValue_contract_invoice_type.rentinvoicetype" name="rawValue_contract_invoice_type.rentinvoicetype" value="${requestScope['rawValue_contract_invoice_type.rentinvoicetype']}"
						class="mini-textbox" style="display: none" /> 
			</td>
			<td class="td-content-title">发票种类：</td>
			<td id="invoicetype" class="td-content">
				<input id="contract_invoice_type.invoicetype" name="contract_invoice_type.invoicetype" class="mini-combobox" style="width: 200px;" label="发票种类"
					textField="name"
					valueField="value"
					dataField="datas"
					allowInput="false" 
					data-options="{_params:{pid:'invoicetype'}}" 
				    onbeforeshowpopup="onbeforeshowpopup"
					value="${requestScope['contract_invoice_type.invoicetype'] }"
					text="${requestScope['rawValue_contract_invoice_type.invoicetype']}"
					onvaluechanged="comboboxChanged"/>
				    <input id="rawValue_contract_invoice_type.invoicetype" name="rawValue_contract_invoice_type.invoicetype" value="${requestScope['rawValue_contract_invoice_type.invoicetype']}"
						class="mini-textbox" style="display: none" /> 
			</td>
		</tr>
		
		<tr class="tr-even">
				<td class="td-content-title">纳税人类别：</td>
			<td class="td-content">
				<input id="taxregtype" name="contract_invoice_type.taxregtype" class="mini-combobox" textField="name"  label="纳税人类别" 
		                  	   valueField="value"  
							   dataField="datas"
							   allowInput="false" 
							   data-options="{_params:{pid:'tax_level_name'}}" 
							   onbeforeshowpopup="onbeforeshowpopup"
							   value="${requestScope['contract_invoice_type.taxregtype'] }" 
							   text="${requestScope['rawValue_contract_invoice_type.taxregtype'] }" 
							   onvaluechanged="taxregtypeonvaluechanged" 
				 />	 
				  <input id="rawValue_contract_invoice_type.taxregtype" name="rawValue_contract_invoice_type.taxregtype" value="${requestScope['rawValue_contract_invoice_type.taxregtype']}" class="mini-textbox" style="display:none" /> 
             </td>
			<td class="td-content-title">纳税人识别号：</td>
			<td class="td-content"><input id="taxregcode" name="contract_invoice_type.taxregcode" class="mini-textbox" label="纳税人识别号" value="${requestScope['contract_invoice_type.taxregcode'] }" allowInput="true" /></td>
		</tr>
		<tr class="tr-odd">
			<td class="td-content-title" width="12%">开户行：</td>
			<td class="td-content" width="38%"><input id="contract_invoice_type.taxbank" name="contract_invoice_type.taxbank" class="mini-textbox" label="开户行" value="${requestScope['contract_invoice_type.taxbank'] }" allowInput="true" /></td>
			<td class="td-content-title" width="12%">开户账号：</td>
			<td class="td-content" width="38%"><input id="contract_invoice_type.taxacc" name="contract_invoice_type.taxacc" class="mini-textbox" label="开户账号" value="${requestScope['contract_invoice_type.taxacc'] }" allowInput="true" /></td>
		</tr>
		<tr class="tr-even">
			<td class="td-content-title">开票地址：</td>
			<td class="td-content"><input id="contract_invoice_type.invoiceadd" name="contract_invoice_type.invoiceadd" class="mini-textbox" label="开票地址" value="${requestScope['contract_invoice_type.invoiceadd'] }" allowInput="true" /></td>
			<td class="td-content-title">开票电话：</td>
			<td class="td-content"><input id="contract_invoice_type.invoicephone" name="contract_invoice_type.invoicephone" class="mini-textbox" label="开票电话" value="${requestScope['contract_invoice_type.invoicephone'] }" allowInput="true" /></td>
		</tr>
	<tr class="tr-even" style="display:none;" >
			<td class="td-content-title">本金开票税率：</td>
			<td class="td-content"><input id="contract_invoice_type.corpusinvoiceratio" name="contract_invoice_type.corpusinvoiceratio" class="mini-textbox" label="本金开票税率" value="${requestScope['contract_invoice_type.corpusinvoiceratio'] }" allowInput="true" vtype="float" />
			<font class="required-tag">%</font>
			</td>
			<td class="td-content-title">利息开票税率：</td>
			<td class="td-content"><input id="contract_invoice_type.interestinvoiceratio" name="contract_invoice_type.interestinvoiceratio" class="mini-textbox" label="利息开票税率" value="${requestScope['contract_invoice_type.interestinvoiceratio'] }" allowInput="true" vtype="float" />
			<font class="required-tag">%</font>
			</td>
		</tr>
		<tr class="tr-even" style="display:none;" >
			<td class="td-content-title">手续费开票税率：</td>
			<td class="td-content"><input id="contract_invoice_type.handlingchargeinvoiceratio" name="contract_invoice_type.handlingchargeinvoiceratio" class="mini-textbox" label="手续费开票税率" value="${requestScope['contract_invoice_type.handlingchargeinvoiceratio'] }" allowInput="true" vtype="float" />
			<font class="required-tag">%</font>
			</td>
			<td class="td-content-title">咨询费开票税率：</td>
			<td class="td-content"><input id="contract_invoice_type.managementinvoiceratio" name="contract_invoice_type.managementinvoiceratio" class="mini-textbox" label="自续费开票税率" value="${requestScope['contract_invoice_type.managementinvoiceratio'] }" allowInput="true" vtype="float" />
			<font class="required-tag">%</font>
			</td>
		</tr> 
	</table>
</div>
<script type="text/javascript">
jQuery(function(){	
	 var showTools = true;
	 if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;};
	 if(showTools==false){
	 	miniui_ext.disableFormFields("contract_rent_invoice_type_form");
	 	miniui_ext.disableFormFields("rentinvoicetype");
		 miniui_ext.disableFormFields("invoicetype");
	 } 
	 if(mini.get("taxregtype").getValue()=="tax_level_name.1"){
		 mini.get("taxregcode").setRequired(true);
	 }else{
		 mini.get("taxregcode").setRequired(false);
	 }
	 
	 taxregtypeonvaluechanged();
});

function taxregtypeonvaluechanged(){
	var sender = mini.get("taxregtype").getValue();
	if(sender=="tax_level_name.1"){
		mini.get("taxregcode").setRequired(true);
		mini.get("contract_invoice_type.taxbank").setRequired(true);
		mini.get("contract_invoice_type.taxacc").setRequired(true);
		mini.get("contract_invoice_type.invoiceadd").setRequired(true);
		mini.get("contract_invoice_type.invoicephone").setRequired(true);
		mini.get("contract_invoice_type.corpusinvoiceratio").setRequired(true);
		mini.get("contract_invoice_type.interestinvoiceratio").setRequired(true);
		mini.get("contract_invoice_type.handlingchargeinvoiceratio").setRequired(true);
		mini.get("contract_invoice_type.managementinvoiceratio").setRequired(true);
		mini.getbyName("rawValue_contract_invoice_type.taxregtype").setValue("一般纳税人");
	}else{
		mini.get("taxregcode").setRequired(false);
		mini.get("contract_invoice_type.taxbank").setRequired(false);
		mini.get("contract_invoice_type.taxacc").setRequired(false);
		mini.get("contract_invoice_type.invoiceadd").setRequired(false);
		mini.get("contract_invoice_type.invoicephone").setRequired(false);
		mini.get("contract_invoice_type.corpusinvoiceratio").setRequired(false);
		mini.get("contract_invoice_type.interestinvoiceratio").setRequired(false);
		mini.get("contract_invoice_type.handlingchargeinvoiceratio").setRequired(false);
		mini.get("contract_invoice_type.managementinvoiceratio").setRequired(false);
		mini.getbyName("rawValue_contract_invoice_type.taxregtype").setValue("一般纳税人");
		mini.get("taxregcode").setRequired(false);
		mini.getbyName("rawValue_contract_invoice_type.taxregtype").setValue("非一般纳税人");
	}
}

var invoiceobj = {'invoicetype1':'增值税专用发票','invoicetype2':'增值税普通发票','invoicetype4':'增专、增普混合开票'};
var rentinvoiceobj = {'invoice_type06':'每期开具利息发票和本金收据（不开具本金发票）','invoice_type07':'每期本金开普票,利息开专票','invoice_type03':'发票分期开具，按每期租金开票'};
function receivedInvoie(e){
	var isYBNSR = mini.get("taxregtype").getValue()=="tax_level_name.1"; //是否一般纳税人
	//var receivedinvoicetype= mini.get("contract_invoice_type.receivedinvoicetype").getValue();
	if(isYBNSR){
		//一般纳税人
		switch(receivedinvoicetype){
		case 'received_invoice_type.01':
			//增值税专用发票  每期租金开票
			assignment("invoicetype1","invoice_type03");
			break;
		case 'received_invoice_type.02':
			//增专增普混合开票  本金开普票,利息开专票
			assignment("invoicetype4","invoice_type07");
			break;
		case 'received_invoice_type.03':
			//增专增普混合开票	本金开普票,利息开专票
			assignment("invoicetype4","invoice_type07");
			break;
		case 'received_invoice_type.04':
			//增专增普混合开票	本金开普票,利息开专票
			assignment("invoicetype4","invoice_type07");
			break;
		case 'received_invoice_type.05':
			//增值税专用发票	本金开收据、利息开发票
			assignment("invoicetype1","invoice_type06");
			break;
		case 'received_invoice_type.06':
			//增值税专用发票	本金开收据、利息开发票
			assignment("invoicetype1","invoice_type06");
			break;
		}
	}else{
		switch(receivedinvoicetype){
		//非一般纳税人
		case 'received_invoice_type.01':
			//增值税普通发票  每期租金开票
			assignment("invoicetype2","invoice_type03");
			break;
		case 'received_invoice_type.02':
			//增值税普通发票	每期租金开票
			assignment("invoicetype2","invoice_type03");
			break;
		case 'received_invoice_type.03':
			//增值税普通发票	每期租金开票
			assignment("invoicetype2","invoice_type03");
			break;
		case 'received_invoice_type.04':
			//增值税普通发票	每期租金开票
			assignment("invoicetype2","invoice_type03");
			break;
		case 'received_invoice_type.05':
			//增值税专用发票	本金开收据、利息开发票
			assignment("invoicetype2","invoice_type06");
			break;
		case 'received_invoice_type.06':
			//增值税专用发票	本金开收据、利息开发票
			assignment("invoicetype2","invoice_type06");
			break;
		}
	}
	if(typeof(e)!='undefined'){
		comboboxChanged(e);
	}

}
function assignment(invoicetype,rentinvoicetype){
	mini.get("contract_invoice_type.invoicetype").setValue(invoicetype);
	mini.get("contract_invoice_type.invoicetype").setText(invoiceobj[invoicetype]);
	mini.get("rawValue_contract_invoice_type.invoicetype").setValue(invoiceobj[invoicetype]);
	
	mini.get("contract_invoice_type.rentinvoicetype").setValue(rentinvoicetype);
	mini.get("contract_invoice_type.rentinvoicetype").setText(rentinvoiceobj[rentinvoicetype]);
	mini.get("rawValue_contract_invoice_type.rentinvoicetype").setValue(rentinvoiceobj[rentinvoicetype]);
}
</script>