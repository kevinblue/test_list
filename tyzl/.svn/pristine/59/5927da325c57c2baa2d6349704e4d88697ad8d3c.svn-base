<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<div id="proj_rent_invoice_type1" >
	    <table cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;" class="fillTable">
	    <tbody id='rentinfo'>
				<tr class="tr-even">
					<td class="td-content-title" width="12%">纳税人类别：</td>
					<td class="td-content" width="38%">
					<input  name="rent_invoice_type.taxregtype" label="纳税人类别" class="mini-combobox" readonly textField="name"  required="true"
		                  	   valueField="value"  
							   dataField="datas"
							   allowInput="false" 
							   data-options="{_params:{pid:'tax_level_name'}}" 
							   onbeforeshowpopup="onbeforeshowpopup"
							   value="<mini:param  name="rent_invoice_type.taxregtype"/>"
							   text="<mini:param  name="rawValue_rent_invoice_type.taxregtype"/>"
							   onvaluechanged="comboboxChanged(e)"
				    />
				    <input id="rawValue_rent_invoice_type.taxregtype" name="rawValue_rent_invoice_type.taxregtype" value="<mini:param  name="rawValue_rent_invoice_type.taxregtype"/>"  class="mini-textbox" style="display:none"/>
	               </td>
	               <td class="td-content-title" width="12%">纳税人识别号：</td>
				   <td class="td-content" width="38%"><input name="rent_invoice_type.taxregcode" label="纳税人识别号" class="mini-textbox" value="<mini:param  name="rent_invoice_type.taxregcode"/>" allowInput="true" /></td>
				</tr>
				<tr class="tr-odd">
					<td class="td-content-title">开户行：</td>
					<td class="td-content"><input name="rent_invoice_type.taxbank" label="开户行" class="mini-textbox" value="<mini:param  name="rent_invoice_type.taxbank"/>" allowInput="true" /></td>
					<td class="td-content-title">开户账号：</td>
					<td class="td-content"><input name="rent_invoice_type.taxacc" label="开户账号" class="mini-textbox" value="<mini:param  name="rent_invoice_type.taxacc"/>" allowInput="true" /></td>
				</tr>
				<tr class="tr-even">
					<td class="td-content-title">开票地址：</td>
					<td class="td-content"><input name="rent_invoice_type.invoiceadd" label="开票地址" class="mini-textbox" value="<mini:param  name="rent_invoice_type.invoiceadd"/>" allowInput="true" /></td>
					<td class="td-content-title">开票电话：</td>
					<td class="td-content"><input name="rent_invoice_type.invoicephone" label="开票电话" class="mini-textbox" value="<mini:param  name="rent_invoice_type.invoicephone"/>" allowInput="true" /></td>
				</tr>
		  </tbody>
	            <tr class="tr-project-info tr-odd">
	             <td class="td-content-title">租金发票类型：</td>
	             <td class="td-content">
		             <input  name="rent_invoice_type.rentinvoicetype"  label="租金发票类型" class="mini-combobox" textField="name"  required="true"
			                  	   valueField="value"  
								   dataField="datas"
								   allowInput="false" 
								   data-options="{_params:{pid:'rent_invoice_type'}}" 
								   onbeforeshowpopup="onbeforeshowpopup"
								   value="<mini:param  name="rent_invoice_type.rentinvoicetype"/>"
								   text="<mini:param  name="rawValue_rent_invoice_type.rentinvoicetype"/>"
								   onvaluechanged="comboboxChanged(e)"
					 />
					 <input id="rawValue_rent_invoice_type.rentinvoicetype" name="rawValue_rent_invoice_type.rentinvoicetype" value="<mini:param  name="rawValue_rent_invoice_type.rentinvoicetype"/>"  class="mini-textbox" style="display:none"/>
	             </td>
	             <td class="td-content-title">发票种类：</td>
	             <td class="td-content">
		             <input  name="rent_invoice_type.invoicetype" label="发票种类" class="mini-combobox" textField="name"  required="true"
			                  	   valueField="value"  
								   dataField="datas"
								   allowInput="false" 
								   data-options="{_params:{pid:'invoicetype'}}" 
								   onbeforeshowpopup="onbeforeshowpopup"
								   value="<mini:param  name="rent_invoice_type.invoicetype"/>"
								   text="<mini:param  name="rawValue_rent_invoice_type.invoicetype"/>"
								   onvaluechanged="comboboxChanged(e)"
					 />
					 <input id="rawValue_rent_invoice_type.invoicetype" name="rawValue_rent_invoice_type.invoicetype" value="<mini:param  name="rawValue_rent_invoice_type.invoicetype"/>" class="mini-textbox" style="display:none"/>
	             </td>
	          </tr>
	          
	          <tr class="tr-project-info tr-odd">
	             <td class="td-content-title">开票说明：</td>
	             <td colspan=3 style="padding-top: 4px;padding-bottom: 4px;">
		             <input style="width:72%;height:150px" name="rent_invoice_type.invoicememo" label="开票说明"  value="<mini:param  name="rent_invoice_type.invoicememo"/>" label="开票说明" class="mini-textarea" maxLength="500" type="text" > 
	             </td>
	          </tr>
	</table>
</div>

<script language="javascript">
//控制只读页面
if(isViewHistoryTask){
 mini.parse("proj_rent_invoice_type1");
 miniui_ext.initformenabled("proj_rent_invoice_type1");
 //var projRentInvoiceTypeform = new mini.Form("proj_rent_invoice_type1");
 //projRentInvoiceTypeform.disable();
/*  var o = form.getData(); 
 var fields = form.getFields();
 for(var index =0;index<fields.length;index++){
 	 //判断是否是下拉框控件，是则同时把text属性传入后台
 	if(fields[index].uiCls == "mini-combobox" || fields[index].uiCls =="mini-datepicker"){
 		fields[index].disable();
 	}else{
		fields[index].allowInput=false;
 	}
 } */
}
//必填翻转
mini.parse("rentinfo");
var form = new mini.Form("rentinfo");
var o = form.getData(); 
var fields = form.getFields();
//控制必填
//一般纳税人必填
if("tax_level_name.1"=="<mini:param name='rent_invoice_type.taxregtype'/>"){
    for(var index =0;index<fields.length;index++){
	    fields[index].setRequired(true);
    }
}else{
	 for(var index =0;index<fields.length;index++){
		fields[index].setRequired(false);
	 }
}
</script>
