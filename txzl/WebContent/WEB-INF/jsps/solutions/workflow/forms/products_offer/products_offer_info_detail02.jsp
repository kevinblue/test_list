<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<input name="contract_invoice_type.id" id="contract_invoice_type.id"
	type="hidden" value="${requestScope['contract_invoice_type.id']}" />
<div id="contract_rent_invoice_type_form" title="租金开票类型">
	<table cellspacing="0" cellpadding="0"
		style="width: 100%; border: 1px solid #99CCFF;" class="fillTable">

		<tr class="tr-even">
			<td class="td-content-title">Applied by</td>
			<td class="td-content" colspan="3"><input
				id="contract_invoice_type.receivedinvoicetype"
				name="contract_invoice_type.receivedinvoicetype"
				class="mini-textbox" label="收到票据类型"  textField="name"
				valueField="value" dataField="datas" allowInput="true"
				data-options="{_params:{pid:'received_invoice_type'}}"
				onbeforeshowpopup="onbeforeshowpopup"
				value="${requestScope['contract_invoice_type.receivedinvoicetype']}"
				text="${requestScope['rawValue_contract_invoice_type.receivedinvoicetype']}"
				onvaluechanged="receivedInvoie" required="true"> <input
				id="rawValue_contract_invoice_type.receivedinvoicetype"
				name="rawValue_contract_invoice_type.receivedinvoicetype"
				value="${requestScope['rawValue_contract_invoice_type.receivedinvoicetype']}"
				class="mini-textbox" style="display: none" /></td>
		</tr>
		<tr class="tr-odd">
			<td class="td-content-title">Confirmation Logistics Dept:</td>
			<td id="rentinvoicetype" class="td-content"><input
				id="contract_invoice_type.rentinvoicetype"
				name="contract_invoice_type.rentinvoicetype" class="mini-combobox"
				label="租金发票类型" textField="name" valueField="value" dataField="datas"
				allowInput="false"
				data-options="{_params:{pid:'rent_invoice_type'}}"
				onbeforeshowpopup="onbeforeshowpopup"
				value="${requestScope['contract_invoice_type.rentinvoicetype']}"
				text="${requestScope['rawValue_contract_invoice_type.rentinvoicetype']}"
				onvaluechanged="comboboxChanged" readonly> <input
				id="rawValue_contract_invoice_type.rentinvoicetype"
				name="rawValue_contract_invoice_type.rentinvoicetype"
				value="${requestScope['rawValue_contract_invoice_type.rentinvoicetype']}"
				class="mini-textbox" style="display: none" /></td>
			<td class="td-content-title">Confirmation Financial Dept:</td>
			<td id="invoicetype" class="td-content"><input
				id="contract_invoice_type.invoicetype"
				name="contract_invoice_type.invoicetype" class="mini-combobox"
				style="width: 200px;" label="发票种类" textField="name"
				valueField="value" dataField="datas" allowInput="false"
				data-options="{_params:{pid:'invoicetype'}}"
				onbeforeshowpopup="onbeforeshowpopup"
				value="${requestScope['contract_invoice_type.invoicetype'] }"
				text="${requestScope['rawValue_contract_invoice_type.invoicetype']}"
				onvaluechanged="comboboxChanged" readonly> <input
				id="rawValue_contract_invoice_type.invoicetype"
				name="rawValue_contract_invoice_type.invoicetype"
				value="${requestScope['rawValue_contract_invoice_type.invoicetype']}"
				class="mini-textbox" style="display: none" /></td>
		</tr>


	</table>
</div>
<script type="text/javascript">
	jQuery(function() {
		var showTools = true;
		if ('${param.isView}' == 'true' || isViewHistoryTask == true) {
			showTools = false;
		}
		;
		if (showTools == false) {
			miniui_ext.disableFormFields("contract_rent_invoice_type_form");
		}
		if (mini.get("taxregtype").getValue() == "tax_level_name.1") {
			mini.get("taxregcode").setRequired(true);
		} else {
			mini.get("taxregcode").setRequired(false);
		}
		miniui_ext.disableFormFields("rentinvoicetype");
		miniui_ext.disableFormFields("invoicetype");
		taxregtypeonvaluechanged();
	});

	function taxregtypeonvaluechanged() {
		var sender = mini.get("taxregtype").getValue();
		if (sender == "tax_level_name.1") {
			mini.get("taxregcode").setRequired(true);
			mini.getbyName("rawValue_contract_invoice_type.taxregtype")
					.setValue("一般纳税人");
		} else {
			mini.get("taxregcode").setRequired(false);
			mini.getbyName("rawValue_contract_invoice_type.taxregtype")
					.setValue("非一般纳税人");
		}
	}

	var invoiceobj = {
		'invoicetype1' : '增值税专用发票',
		'invoicetype2' : '增值税普通发票',
		'invoicetype4' : '增专、增普混合开票'
	};
	var rentinvoiceobj = {
		'invoice_type06' : '每期开具利息发票和本金收据（不开具本金发票）',
		'invoice_type07' : '每期本金开普票,利息开专票',
		'invoice_type03' : '发票分期开具，按每期租金开票'
	};
	function receivedInvoie(e) {
		var isYBNSR = mini.get("taxregtype").getValue() == "tax_level_name.1"; //是否一般纳税人
		var receivedinvoicetype = mini.get(
				"contract_invoice_type.receivedinvoicetype").getValue();
		if (isYBNSR) {
			//一般纳税人
			switch (receivedinvoicetype) {
			case 'received_invoice_type.01':
				//增值税专用发票  每期租金开票
				assignment("invoicetype1", "invoice_type03");
				break;
			case 'received_invoice_type.02':
				//增专增普混合开票  本金开普票,利息开专票
				assignment("invoicetype4", "invoice_type07");
				break;
			case 'received_invoice_type.03':
				//增专增普混合开票	本金开普票,利息开专票
				assignment("invoicetype4", "invoice_type07");
				break;
			case 'received_invoice_type.04':
				//增专增普混合开票	本金开普票,利息开专票
				assignment("invoicetype4", "invoice_type07");
				break;
			case 'received_invoice_type.05':
				//增值税专用发票	本金开收据、利息开发票
				assignment("invoicetype1", "invoice_type06");
				break;
			case 'received_invoice_type.06':
				//增值税专用发票	本金开收据、利息开发票
				assignment("invoicetype1", "invoice_type06");
				break;
			}
		} else {
			switch (receivedinvoicetype) {
			//非一般纳税人
			case 'received_invoice_type.01':
				//增值税普通发票  每期租金开票
				assignment("invoicetype2", "invoice_type03");
				break;
			case 'received_invoice_type.02':
				//增值税普通发票	每期租金开票
				assignment("invoicetype2", "invoice_type03");
				break;
			case 'received_invoice_type.03':
				//增值税普通发票	每期租金开票
				assignment("invoicetype2", "invoice_type03");
				break;
			case 'received_invoice_type.04':
				//增值税普通发票	每期租金开票
				assignment("invoicetype2", "invoice_type03");
				break;
			case 'received_invoice_type.05':
				//增值税专用发票	本金开收据、利息开发票
				assignment("invoicetype2", "invoice_type06");
				break;
			case 'received_invoice_type.06':
				//增值税专用发票	本金开收据、利息开发票
				assignment("invoicetype2", "invoice_type06");
				break;
			}
		}
		if (typeof (e) != 'undefined') {
			comboboxChanged(e);
		}

	}
	function assignment(invoicetype, rentinvoicetype) {
		mini.get("contract_invoice_type.invoicetype").setValue(invoicetype);
		mini.get("contract_invoice_type.invoicetype").setText(
				invoiceobj[invoicetype]);
		mini.get("rawValue_contract_invoice_type.invoicetype").setValue(
				invoiceobj[invoicetype]);

		mini.get("contract_invoice_type.rentinvoicetype").setValue(
				rentinvoicetype);
		mini.get("contract_invoice_type.rentinvoicetype").setText(
				rentinvoiceobj[rentinvoicetype]);
		mini.get("rawValue_contract_invoice_type.rentinvoicetype").setValue(
				rentinvoiceobj[rentinvoicetype]);
	}
</script>