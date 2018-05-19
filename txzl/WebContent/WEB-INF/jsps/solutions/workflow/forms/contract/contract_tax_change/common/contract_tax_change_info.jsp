<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>	
<div id="contract_tax_change_form" title="合同基本信息">
	<div class="fillTableContainer">
		<div class="mini-panel" title="合同基本信息" showCollapseButton="true"
			style="width: 100%;">
			<table class="fillTable" cellspacing="0" cellpadding="0"
				style="width: 100%">
				<tr class="tr-even">
	           <td class="td-content-title" width="12%">项目编号：</td>
				<td class="td-content" width="38%">
				<input name="contract_info.proj_id" id="project_id" 
				class="mini-textbox" label="项目编号" readOnly type="text" value="${requestScope['contract_info.proj_id']}">
				<a href="javascript:void(0);"><img alt="项目信息" src="${pageContext.request.contextPath}/menuIcons/search.png"
						onclick="openprojinfo('${requestScope['contract_info.proj_id']}')" style="vertical-align: middle;" /></a>&nbsp;
				</td>
			    <td class="td-content-title" width="12%">合同编号：</td>
				<td class="td-content" width="38%">
				<input id="contract_id" name="contract_info.contractid" class="mini-textbox" label="合同编号" value="${requestScope['contract_info.contractid'] }" allowInput="false" />&nbsp;
				<a href="javascript:void(0);"><img alt="合同信息" src="${pageContext.request.contextPath}/menuIcons/search.png"
						onclick="contractsearch('${requestScope['contract_info.id']}')" style="vertical-align: middle;" /></a>&nbsp;
				</td>
				</tr>
				
				<tr class="tr-odd">
					<td class="td-content-title" width="12%">业务合同编号：</td>
					<td class="td-content" width="38%">
		<input id="contract_number"  required="true" name="contract_info.contractnumber" class="mini-textbox" readOnly
		 label="业务合同编号"  value="${requestScope['contract_info.contractnumber'] }" /></td>
							
		 <td class="td-content-title" width="12%">客户名称：</td>
				<td class="td-content" width="38%">
					<input  id="cust_name" name="contract_info.custname" class="mini-textbox" readOnly require="true" label="承租单位"  
						value="${requestScope['contract_info.custname'] }">
					&nbsp;
					<a href="javascript:void(0);"><img alt="客户信息" src="${pageContext.request.contextPath}/menuIcons/search.png"
						onclick="opencustdetail('${requestScope['contract_info.custid'] }','${requestScope['contract_info.custclass'] }')" style="vertical-align: middle;" /></a>&nbsp;
				</td>		
				</tr>
			</table>
		</div>
		<div class="mini-panel" title="变更前开票信息"
			style="width: 50%; float: left">
			<table cellspacing="0" cellpadding="0" width="100%" class="fillTable">
			  	<tr class="tr-lessor-info tr-odd">
					<td class="td-content-title">纳税人类别：</td>
					<td class="td-content">
						<input
						id="contractInvoiceType_info.proj_taxregtype"
						name="contractInvoiceType_info.proj_taxregtype" readonly class="mini-combobox"
						textField="name" label="纳税人类别" valueField="value"
						dataField="datas" allowInput="false"
						data-options="{_params:{pid:'tax_level_name'}}"
						onbeforeshowpopup="onbeforeshowpopup"
						value="${requestScope['contractInvoiceType_info.taxRegType'] }"
						text="${requestScope['rawValue_contractInvoiceType_info.taxRegType'] }"
						onvaluechanged="taxregtypeonvaluechanged" /> </td>
				</tr>
				
				<tr class="tr-lessor-info tr-odd">
					<td class="td-content-title">纳税人识别号：</td>
					<td class="td-content"><input label="纳税人识别号"
						id="contractInvoiceType_info.taxregcode"
						name="contractInvoiceType_info.taxregcode"  readonly class="mini-textbox"
						value="${requestScope['contractInvoiceType_info.taxregcode']}" /></td>
				</tr>
				
				<tr class="tr-lessor-info tr-even">
					<td class="td-content-title">开户银行：</td>
					<td class="td-content"><input require="true"
						id="contractInvoiceType_info.sparetaxBank"
						name="contractInvoiceType_info.sparetaxBank" readonly label="开户银行"
						class="mini-textbox"
						value="${requestScope['contractInvoiceType_info.sparetaxBank']}"></td>
				</tr>

				<tr class="tr-lessor-info tr-odd">
					<td class="td-content-title">开户账号：</td>
					<td class="td-content"><input require="true"
						id="contractInvoiceType_info.sparetaxacc" readonly name="contractInvoiceType_info.sparetaxacc"
						label="开户账号" class="mini-textbox"
						value="${requestScope['contractInvoiceType_info.sparetaxacc']}"></td>
					</td>
				</tr>
				
				<tr class="tr-lessor-info tr-odd">
					<td class="td-content-title" width="12%">开票地址：</td>
					<td class="td-content" width="38%"><input require="true"
						id="contractInvoiceType_info.spareinvoiceAdd"
						name="contractInvoiceType_info.spareinvoiceAdd"  readonly label="开票地址"
						class="mini-textbox"
						value="${requestScope['contractInvoiceType_info.spareinvoiceAdd']}"></td>
				</tr>
			    <tr class="tr-lessor-info tr-odd">
					<td class="td-content-title" width="12%">开票电话：</td>
					<td class="td-content" width="38%"><input require="true"
						id="contractInvoiceType_info.spareinvoicePhone"
						name="contractInvoiceType_info.spareinvoicePhone"  readonly label="开票电话"
						class="mini-textbox"
						value="${requestScope['contractInvoiceType_info.spareinvoicePhone']}"></td>
				</tr>
			</table>
		</div>
		<div class="mini-panel" title="变更后开票信息"
			style="width: 50%; float: left">
			<table cellspacing="0" cellpadding="0" width="100%" class="fillTable">
				<tr class="tr-lessor-info tr-odd">
					<td class="td-content-title">纳税人类别：</td>
					<td class="td-content">
						<input
						id="taxregtype"
						name="contractinvoicetype.taxregtype" class="mini-combobox" required="required"
						textField="name" label="纳税人类别" valueField="value"
						dataField="datas" allowInput="false"
						data-options="{_params:{pid:'tax_level_name'}}"
						onbeforeshowpopup="onbeforeshowpopup"
						value="${requestScope['contractinvoicetype.taxregtype'] }"
						text="${requestScope['rawValue_contract_invoice_type.taxregtype'] }"
						onvaluechanged="taxregtypeonvaluechanged" /> 
					 <input id="rawValue_contract_invoice_type.taxregtype" name="rawValue_contract_invoice_type.taxregtype" 
					 value="${requestScope['rawValue_contract_invoice_type.taxregtype']}" class="mini-textbox" style="display:none" /></td>
				</tr>
				
				<tr class="tr-lessor-info tr-odd">
					<td class="td-content-title">纳税人识别号：</td>
					<td class="td-content"><input label="纳税人识别号"
						id="taxregcode"
						name="contractinvoicetype.taxregcode" class="mini-textbox"
						value="${requestScope['contractinvoicetype.taxregcode']}" />		
						</td>
				<!-- </tr>
				<tr class="tr-lessor-info tr-odd"> -->		
	
				</tr>
				<tr class="tr-lessor-info tr-odd">
								<td class="td-content-title" >是否统一社会信用编号：</td>
					<td class="td-content" width:60px>
					<input  class="mini-combobox" label="是否统一社会信用编号" 
					name="insuretrustcode" 
					id ="insuretrustcode"  
					required="true"
					textField="text" valueField="text"
					data="[{text:'是'},{text:'否'}]"
					type="text" value="${requestScope['insuretrustcode']}"></td>
					</tr>
				<tr class="tr-lessor-info tr-even">
					<td class="td-content-title">开户银行：</td>
					<td class="td-content"><input require="true"
						id="contractinvoicetype.taxbank"
						name="contractinvoicetype.taxbank" label="开户银行"
						class="mini-textbox"
						value="${requestScope['contractinvoicetype.taxbank']}"></td>
				</tr>

				<tr class="tr-lessor-info tr-odd">
					<td class="td-content-title">开户账号：</td>
					<td class="td-content">
					<input require="true"
						id="contractinvoicetype.taxacc" name="contractinvoicetype.taxacc"
						label="开户账号" class="mini-textbox"
						value="${requestScope['contractinvoicetype.taxacc']}"></td>
					</td>
				</tr>
				
				<tr class="tr-lessor-info tr-odd">
					<td class="td-content-title" width="12%">开票地址：</td>
					<td class="td-content" width="38%"><input require="true"
						id="contractinvoicetype.invoiceadd"
						name="contractinvoicetype.invoiceadd" label="开票地址"
						class="mini-textbox"
						value="${requestScope['contractinvoicetype.invoiceadd']}"></td>
				</tr>
				
				<tr class="tr-lessor-info tr-even">
					<td class="td-content-title" width="12%">开票电话：</td>
					<td class="td-content" width="38%"><input require="true"
						id="contractinvoicetype.invoicePhone"
						name="contractinvoicetype.invoicePhone"   label="开票电话"
						class="mini-textbox"
						value="${requestScope['contractinvoicetype.invoicePhone']}"></td>
				</tr>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){
		miniui_ext.disableFormFields("contract_tax_change_form");
	};

});

//加载合同信息
function contractsearch(cid)
{
	var url = getRootPath()+"/acl/queryContractInfoDetail.acl?contractid="+cid;
	openFullScreenWindow(url);
}
//加载客户信息
function opencustdetail(id,custclass){
 	var params = "id="+id+"&isView=true";
 	var url="";
	if(custclass=="CUST_INFO_COMPANY"){
		url = getRootPath()+"/leasing/cust_info/cust_company/cust_company_detail.bi?"+params;
	}else{
		url = getRootPath()+"/leasing/cust_info/cust_person/cust_person_detail.bi?"+params;
	}
	var sheight = window.screen.availHeight - 30;
	var swidth = window.screen.availWidth - 10;
	var winoption = "left=0px,top=0px,height=" + sheight + "px,width=" + swidth + "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
	window.open(url, '_blank', winoption);
}
//加载项目信息
function openprojinfo(cid)
{
    var URL = "${pageContext.request.contextPath}/jbpm/getProjSummaryHistoryInfos.action?keyOne="+cid;
    openFullScreenWindow(URL);
}
//校验纳税识别号
function taxregtypeonvaluechanged(){
	var sender = mini.get("taxregtype").getValue();
	if(sender=="tax_level_name.1"){
		mini.get("taxregcode").setRequired(true);
		mini.getbyName("rawValue_contract_invoice_type.taxregtype").setValue("一般纳税人");
	}else{
		mini.get("taxregcode").setRequired(false);
		mini.getbyName("rawValue_contract_invoice_type.taxregtype").setValue("非一般纳税人");
	}
}
</script>