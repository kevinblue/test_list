<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<div id="proj_rent_invoice_type" title="租金发票类型">
	    <table cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;" class="fillTable">
	    <tbody id='rentinfo'>
				<tr class="tr-even">
					<td class="td-content-title" width="12%">纳税人类别：</td>
					<td class="td-content" width="38%">
					<input  name="rent_invoice_type.taxregtype" label="纳税人类别" class="mini-combobox"  textField="name"  required="false"
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
		             <input  name="rent_invoice_type.rentinvoicetype"  label="租金发票类型" class="mini-combobox" textField="name"  required="false"
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
		             <input  name="rent_invoice_type.invoicetype" label="发票种类" class="mini-combobox" textField="name"  required="false"
			                  	   valueField="value"  
								   dataField="datas"
								   allowInput="false" 
								   data-options="{_params:{pid:'invoicetype'}}" 
								   onbeforeshowpopup="onbeforeshowpopup"
								   value="<mini:param  name="rent_invoice_type.invoicetype"/>" 
								   text="<mini:param  name="rawValue_rent_invoice_type.invoicetype"/>" 
								   onvaluechanged="comboboxChanged(e)"
					 />
					 <input id="rawValue_rent_invoice_type.invoicetype" name="rawValue_rent_invoice_type.invoicetype" value="<mini:param  name="rawValue_rent_invoice_type.invoicetype"/>"  class="mini-textbox" style="display:none"/>
	             </td>
	          </tr>
	          
	          <tr class="tr-project-info tr-odd">
	             <td class="td-content-title">开票说明：</td>
	             <td colspan=3 style="padding-top: 4px;padding-bottom: 4px;">
		             <input style="width:72%;height:150px" name="rent_invoice_type.invoicememo" label="开票说明"  value="<mini:param  name="rent_invoice_type.invoicememo"/>"  label="开票说明" class="mini-textarea" maxLength="500" type="text" > 
	             </td>
	          </tr>
	</table>
</div>
<script language="javascript">
	 
	if('<mini:param  name="isView"/>' == 'true'||isViewHistoryTask==true){ 
         miniui_ext.disableFormFields("proj_rent_invoice_type");
    }
    //控制必填 一般纳税人必填
    if("tax_level_name.1"=="<mini:param  name="rent_invoice_type.taxregtype"/>"){
         miniui_ext.setFieldsRequired("rentinfo",false);
    }else{
    	 miniui_ext.setFieldsRequired("rentinfo",false);
    }
</script>
