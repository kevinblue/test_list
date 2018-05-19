<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<input name="proj_invoice_type.id" id="proj_invoice_type.id" type="hidden" value="${requestScope['proj_invoice_type.id']}" />
<input id="rawValue_proj_invoice_type.receivedinvoicetype" name="rawValue_proj_invoice_type.receivedinvoicetype" value="${requestScope['rawValue_proj_invoice_type.receivedinvoicetype']}" class="mini-textbox"  style="display:none">
<input id="rawValue_proj_invoice_type.rentinvoicetype" name="rawValue_proj_invoice_type.rentinvoicetype" value="${requestScope['rawValue_proj_invoice_type.rentinvoicetype']}" class="mini-textbox"  style="display:none">
<input id="rawValue_proj_invoice_type.invoicetype" name="rawValue_proj_invoice_type.invoicetype" value="${requestScope['rawValue_proj_invoice_type.invoicetype']}" class="mini-textbox"  style="display:none"/>
<input id="rawValue_proj_invoice_type.taxregtype" name="rawValue_proj_invoice_type.taxregtype" value="${requestScope['rawValue_proj_invoice_type.taxregtype']}" class="mini-textbox" style="display:none"/>

 <div id="proj_rent_invoice_type" title="租金开票类型">
	<table cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;" class="fillTable">
			<tr class="tr-even">
			<td class="td-content-title">收到票据类型：</td>
			<td class="td-content" colspan="3">
				<input id="proj_invoice_type.receivedinvoicetype" name="proj_invoice_type.receivedinvoicetype" class="mini-combobox" label="收到票据类型"
					width="72%"
					textField="name"
					valueField="value"
					dataField="datas"
					allowInput="false"
					data-options="{_params:{pid:'received_invoice_type'}}" 
					onbeforeshowpopup="onbeforeshowpopup"
					value="${requestScope['proj_invoice_type.receivedinvoicetype']}"
					text="${requestScope['rawValue_proj_invoice_type.receivedinvoicetype']}"
					onvaluechanged="receivedInvoie" required="true"
				>
			</td>
			</tr>
			<tr class="tr-odd">
			<td class="td-content-title">租金发票类型：</td>
			<td class="td-content">
				<input id="proj_invoice_type.rentinvoicetype" name="proj_invoice_type.rentinvoicetype" class="mini-combobox" label="租金发票类型"
					textField="name"
					valueField="value"
					dataField="datas"
					allowInput="false"
					data-options="{_params:{pid:'rent_invoice_type'}}" 
				    onbeforeshowpopup="onbeforeshowpopup"
					value="${requestScope['proj_invoice_type.rentinvoicetype']}"
					text="${requestScope['rawValue_proj_invoice_type.rentinvoicetype']}"
					onvaluechanged="comboboxChanged"  readonly
				>
			</td>
			<td class="td-content-title">发票种类：</td>
			<td class="td-content">
				<input id="proj_invoice_type.invoicetype" name="proj_invoice_type.invoicetype" class="mini-combobox" style="width: 200px;" label="发票种类"
					textField="name"
					valueField="value"
					dataField="datas"
					allowInput="false" 
					data-options="{_params:{pid:'invoicetype'}}" 
				    onbeforeshowpopup="onbeforeshowpopup"
					value="${requestScope['proj_invoice_type.invoicetype'] }"
					text="${requestScope['rawValue_proj_invoice_type.invoicetype']}"
					onvaluechanged="comboboxChanged"  readonly/>
			</td>
		</tr>
		<tbody id="invoice_tbody">
		<tr class="tr-even">
			<td class="td-content-title">纳税人类别：</td>
			<td class="td-content">
				<input id="proj_invoice_type.taxregtype" name="proj_invoice_type.taxregtype" class="mini-combobox" textField="name"  label="纳税人类别" 
		                  	   valueField="value"  
							   dataField="datas"
							   allowInput="false" 
							   data-options="{_params:{pid:'tax_level_name'}}" 
							   onbeforeshowpopup="onbeforeshowpopup"
							   value="${requestScope['proj_invoice_type.taxregtype'] }" 
							   text="${requestScope['rawValue_proj_invoice_type.taxregtype'] }" 
							   onvaluechanged="comboboxChanged" 
				 />	 
			</td>
			<td class="td-content-title">纳税人识别号：</td>
			<td class="td-content"><input id="proj_invoice_type.taxregcode" name="proj_invoice_type.taxregcode" class="mini-textbox" label="纳税人识别号" value="${requestScope['proj_invoice_type.taxregcode'] }" allowInput="true" /></td>
		</tr>
		<tr class="tr-odd">
			<td class="td-content-title" width="12%">开户行：</td>
			<td class="td-content" width="38%"><input id="proj_invoice_type.taxbank" name="proj_invoice_type.taxbank" class="mini-textbox" label="开户行" value="${requestScope['proj_invoice_type.taxbank'] }" allowInput="true" /></td>
			<td class="td-content-title" width="12%">开户账号：</td>
			<td class="td-content" width="38%"><input id="proj_invoice_type.taxacc" name="proj_invoice_type.taxacc" class="mini-textbox" label="开户账号" value="${requestScope['proj_invoice_type.taxacc'] }" allowInput="true" /></td>
		</tr>
		<tr class="tr-even">
			<td class="td-content-title">开票地址：</td>
			<td class="td-content"><input id="proj_invoice_type.invoiceadd" name="proj_invoice_type.invoiceadd" class="mini-textbox" label="开票地址" value="${requestScope['proj_invoice_type.invoiceadd'] }" allowInput="true" /></td>
			<td class="td-content-title">开票电话：</td>
			<td class="td-content"><input id="proj_invoice_type.invoicephone" name="proj_invoice_type.invoicephone" class="mini-textbox" label="开票电话" value="${requestScope['proj_invoice_type.invoicephone'] }" allowInput="true" /></td>
		</tr>
		<tr class="tr-even">
			<td class="td-content-title">本金开票税率：</td>
			<td class="td-content"><input id="proj_invoice_type.corpusinvoiceratio" name="proj_invoice_type.corpusinvoiceratio" class="mini-textbox" label="本金开票税率" value="${requestScope['proj_invoice_type.corpusinvoiceratio'] }" allowInput="true" vtype="float" />
			<font class="required-tag">%</font>
			</td>
			<td class="td-content-title">利息开票税率：</td>
			<td class="td-content"><input id="proj_invoice_type.interestinvoiceratio" name="proj_invoice_type.interestinvoiceratio" class="mini-textbox" label="利息开票税率" value="${requestScope['proj_invoice_type.interestinvoiceratio'] }" allowInput="true" vtype="float"/>
			<font class="required-tag">%</font>
			</td>
		</tr>
		<tr class="tr-even">
			<td class="td-content-title">手续费开票税率：</td>
			<td class="td-content"><input id="proj_invoice_type.handlingchargeinvoiceratio" name="proj_invoice_type.handlingchargeinvoiceratio" class="mini-textbox" label="手续费开票税率" value="${requestScope['proj_invoice_type.handlingchargeinvoiceratio'] }" allowInput="true" vtype="float"/>
			<font class="required-tag">%</font>
			</td>
			<td class="td-content-title">咨询费开票税率：</td>
			<td class="td-content"><input id="proj_invoice_type.managementinvoiceratio" name="proj_invoice_type.managementinvoiceratio" class="mini-textbox" label="自续费开票税率" value="${requestScope['proj_invoice_type.managementinvoiceratio'] }" allowInput="true" vtype="float"/>
			<font class="required-tag">%</font>
			</td>
		</tr>
</tbody>
	</table>
</div>
<script type="text/javascript">
var showTools = true;
if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;};
if(showTools==false){
	miniui_ext.disableFormFields("proj_proj_invoice_type_form");
}
//控制必填 一般纳税人必填
if("tax_level_name.1"=="${requestScope['proj_invoice_type.taxregtype'] }"){
	miniui_ext.setFieldsRequired("proj_proj_invoice_type_form",true);
}else{
	miniui_ext.setFieldsRequired("proj_proj_invoice_type_form",false);
	miniui_ext.setFieldsRequired("proj_invoice_type.receivedinvoicetype",true);
}
/* 
*  received_invoice_type.01	增值税专用发票（17%，有税务监制章）
*  received_invoice_type.02	正规收据（有财政监制章）
*  received_invoice_type.03	增值税专用发票（6%，有税务监制章）
*  received_invoice_type.04	零税率发票（有税务监制章）
*  received_invoice_type.05	白条收据
*  received_invoice_type.06	未取得合规扣额凭证
*  
*   invoicetype1	增值税专用发票
*   invoicetype2	增值税普通发票
*   invoicetype4	增专、增普混合开票
*  
*   
*  本金开收据、利息开发票   invoice_type06
*  本金开普票,利息开专票     invoice_type07
*  每期租金开票                     invoice_type03
*  
*/
var invoiceobj = {'invoicetype1':'增值税专用发票','invoicetype2':'增值税普通发票','invoicetype4':'增专、增普混合开票'};
var projinvoiceobj = {'invoice_type06':'每期开具利息发票和本金收据（不开具本金发票）','invoice_type07':'每期本金开普票,利息开专票','invoice_type03':'发票分期开具，按每期租金开票'};
function receivedInvoie(e){
	var isYBNSR = mini.get("proj_invoice_type.taxregtype").getValue()=="tax_level_name.1"; //是否一般纳税人
	var receivedinvoicetype= mini.get("proj_invoice_type.receivedinvoicetype").getValue();
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
	mini.get("proj_invoice_type.invoicetype").setValue(invoicetype);
	mini.get("proj_invoice_type.invoicetype").setText(invoiceobj[invoicetype]);
	mini.get("rawValue_proj_invoice_type.invoicetype").setValue(invoiceobj[invoicetype]);
	
	mini.get("proj_invoice_type.rentinvoicetype").setValue(rentinvoicetype);
	mini.get("proj_invoice_type.rentinvoicetype").setText(projinvoiceobj[rentinvoicetype]);
	mini.get("rawValue_proj_invoice_type.rentinvoicetype").setValue(projinvoiceobj[rentinvoicetype]);
}
<%-- function assignment(invoicetype,rentinvoicetype){
	$.ajax({
		url: "<%=request.getContextPath() %>/table/getTableData.action",
		type: "post",
		cache: false, 
		async:true,
		data :{
			xmlFileName:"/eleasing/workflow/fund/fund_put/query_proj_invoice_info.xml",
			ids: invoicetype +'\',\''+ rentinvoicetype
		},
		success: function (text) {
			console.info(text);
			var result = mini.decode(text).datas;
			console.info(result);
			for(var i=0;i<result.length;i++){
				if(result[i].pid == 'proj_invoice_type') {
					mini.get("proj_invoice_type.rentinvoicetype").setValue(result[i].id);
					mini.get("proj_invoice_type.rentinvoicetype").setText(result[i].name);
					mini.get("rawValue_proj_invoice_type.rentinvoicetype").setValue(result[i].name);
					console.info(1+result[i].id  + result[i].name);
				}
				if(result[i].pid == 'invoicetype') {
					mini.get("proj_invoice_type.invoicetype").setValue(result[i].id);
					mini.get("proj_invoice_type.invoicetype").setText(result[i].name);
					mini.get("rawValue_proj_invoice_type.invoicetype").setValue(result[i].name);
					console.info(2+result[i].id  + result[i].name);
				}
			}
		}
	});
} --%>
</script>