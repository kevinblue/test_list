<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>税率信息维护</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">
jQuery(function(){
	tenwa.createTable({
		id: "rate_info",
		renderTo: "id_table_container_rateinfo",
		width: '100%',
		height: '100%',
		lazyLoad: false,
		entityClassName : 'com.tenwa.leasing.entity.base.RateInfo',
		title: "税率信息",
		remoteOper : true,
		showPager: true,
		pageSize: 20,
		editFormPopupWindowWidth:500,
		tools: ['add','-','edit','-','remove'],
		xmlFileName: '/eleasing/jsp/sysmgr/sysdatamgr/rate_info.xml',
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field:'leasformname', header:'租赁形式', 
			      formEditorConfig:{
			          fieldVisible:false,
			     fillFromFieldName:'leasform'}},
			{field:'leasform', header:'租赁形式', visible:false,
				  formEditorConfig:{
			                  type:'combobox',
			              required:false,
			             textField:'name',
			            valueField:'value',
			          fieldVisible:true,
              	 params:{pid: 'leas_form', xmlFileName:'combos/comboDict.xml'}}
			 },
/* 			{field:'leasetypename', header:'租赁类型', 
			      formEditorConfig:{
			          fieldVisible:false,
			     fillFromFieldName:'leasetype'}},
			{field:'leasetype', header:'租赁类型', visible:false,
				  formEditorConfig:{
			                  type:'combobox',
			              required:false,
			             textField:'name',
			            valueField:'value',
			          fieldVisible:true,
              	 params:{pid: 'lease_type', xmlFileName:'combos/comboDict.xml'}}
			 }, */
			{field:'receivedinvoicetypename', header:'收到票据类型',visible:false,
			      formEditorConfig:{
			          fieldVisible:false,
			     fillFromFieldName:'receivedinvoicetype'}},
			{field:'receivedinvoicetype', header:'收到票据类型', visible:false,
				  formEditorConfig:{
			               newLine:false,
			                  type:'combobox',
			              required:false,
			             textField:'name',
			            valueField:'value',
			          fieldVisible:false,
              	 params:{pid: 'received_invoice_type', xmlFileName:'combos/comboDict.xml'}}
			 },
			{field:'taxregtypename', header:'纳税人类别', 
			      formEditorConfig:{
			          fieldVisible:false,
			     fillFromFieldName:'taxregtype'}},
			{field:'taxregtype', header:'纳税人类别', visible:false,
				  formEditorConfig:{
			                  type:'combobox',
			              required:false,
			             textField:'name',
			            valueField:'value',
			          fieldVisible:true,
              	 params:{pid: 'tax_level_name', xmlFileName:'combos/comboDict.xml'}}
			 },
			 {field:'rentratename', header:'租息税率', 
			      formEditorConfig:{
			          fieldVisible:false,
			     fillFromFieldName:'rentrate'}},
			{field:'rentrate', header:'租息税率', visible:false,
				  formEditorConfig:{
			               newLine:true,
			                  type:'combobox',
			              required:false,
			             textField:'name',
			            valueField:'value',
			          fieldVisible:true,
             	 params:{pid: 'invoice_rate', xmlFileName:'combos/comboDict.xml'}}
			 },
			{field:'rentinvoicetypename', header:'租息开票类型', 
			      formEditorConfig:{
			          fieldVisible:false,
			     fillFromFieldName:'rateinvoicetype'}},
			{field:'rentinvoicetype', header:'租息开票类型', visible:false,
				  formEditorConfig:{
			                  type:'combobox',
			              required:false,
			             textField:'name',
			            valueField:'value',
			          fieldVisible:true,
            	 params:{pid: 'invoicetype', xmlFileName:'combos/comboDict.xml'}}
			 },
			 {field:'corpusratename', header:'本金税率', 
			      formEditorConfig:{
			          fieldVisible:false,
			     fillFromFieldName:'corpusrate'}},
			{field:'corpusrate', header:'本金税率', visible:false,
				  formEditorConfig:{
			                  type:'combobox',
			                  newLine:true,
			              required:false,
			             textField:'name',
			            valueField:'value',
			          fieldVisible:true,
           	 params:{pid: 'invoice_rate', xmlFileName:'combos/comboDict.xml'}}
			 },
			{field:'corpusinvoicetypename', header:'本金开票类型', 
			      formEditorConfig:{
			          fieldVisible:false,
			     fillFromFieldName:'corpusinvoicetype'}},
			{field:'corpusinvoicetype', header:'本金开票类型', visible:false,
				  formEditorConfig:{
			                  type:'combobox',
			              required:false,
			             textField:'name',
			            valueField:'value',
			          fieldVisible:true,
          	 params:{pid: 'invoicetype', xmlFileName:'combos/comboDict.xml'}}
			 },
			 {field:'serviceratename', header:'咨询服务费税率', 
			      formEditorConfig:{
			          fieldVisible:false,
			     fillFromFieldName:'servicerate'}},
			{field:'servicerate', header:'咨询服务费税率', visible:false,
				  formEditorConfig:{
			                  type:'combobox',
			                  newLine:true,
			              required:false,
			             textField:'name',
			            valueField:'value',
			          fieldVisible:true,
          	 params:{pid: 'invoice_rate', xmlFileName:'combos/comboDict.xml'}}
			 },
			{field:'serviceinvoicetypename', header:'咨询服务费开票类型', 
			      formEditorConfig:{
			          fieldVisible:false,
			     fillFromFieldName:'serviceinvoicetype'}},
			{field:'serviceinvoicetype', header:'咨询服务费开票类型', visible:false,
				  formEditorConfig:{
			                  type:'combobox',
			              required:false,
			             textField:'name',
			            valueField:'value',
			          fieldVisible:true,
        	 params:{pid: 'invoicetype', xmlFileName:'combos/comboDict.xml'}}
			 },
			 {field:'handratename', header:'手续费税率', 
			      formEditorConfig:{
			          fieldVisible:false,
			     fillFromFieldName:'handrate'}},
			{field:'handrate', header:'手续费税率', visible:false,
				  formEditorConfig:{
			                  type:'combobox',
			                  newLine:true,
			             textField:'name',
			            valueField:'value',
			          fieldVisible:true,
         	 params:{pid: 'invoice_rate', xmlFileName:'combos/comboDict.xml'}}
			 },
			{field:'handinvoicetypename', header:'手续费开票类型', 
			      formEditorConfig:{
			          fieldVisible:false,
			     fillFromFieldName:'handinvoicetype'}},
			{field:'handinvoicetype', header:'手续费开票类型', visible:false,
				  formEditorConfig:{
			                  type:'combobox',
			              required:false,
			             textField:'name',
			            valueField:'value',
			          fieldVisible:true,
      	 	params:{pid: 'invoicetype', xmlFileName:'combos/comboDict.xml'}}
			 },
			 {field:'nominalratename', header:'留购价税率', 
			      formEditorConfig:{
			          fieldVisible:false,
			     fillFromFieldName:'nominalrate'}},
			{field:'nominalrate', header:'留购价税率', visible:false,
				  formEditorConfig:{
			                  type:'combobox',
			                  newLine:true,
			              required:false,
			             textField:'name',
			            valueField:'value',
			          fieldVisible:true,
        	 params:{pid: 'invoice_rate', xmlFileName:'combos/comboDict.xml'}}
			 },
			{field:'nominalinvoicetypename', header:'留购价开票类型', 
			      formEditorConfig:{
			          fieldVisible:false,
			     fillFromFieldName:'nominalinvoicetype'}},
			{field:'nominalinvoicetype', header:'留购价开票类型', visible:false,
				  formEditorConfig:{
			                  type:'combobox',
			              required:false,
			             textField:'name',
			            valueField:'value',
			          fieldVisible:true,
    		 params:{pid: 'invoicetype', xmlFileName:'combos/comboDict.xml'}}
			 },
			/*  {field:'returnamtratename', header:'厂商返利税率', 
			      formEditorConfig:{
			          fieldVisible:false,
			     fillFromFieldName:'returnamtrate'}},
			{field:'returnamtrate', header:'厂商返利税率', visible:false,
				  formEditorConfig:{
			                  type:'combobox',
			                  newLine:true,
			              required:false,
			             textField:'name',
			            valueField:'value',
			          fieldVisible:true,
        	 params:{pid: 'invoice_rate', xmlFileName:'combos/comboDict.xml'}}
			 },
			{field:'returnamtinvoicetypename', header:'厂商返利税率开票类型', 
			      formEditorConfig:{
			          fieldVisible:false,
			     fillFromFieldName:'returnamtinvoicetype'}},
			{field:'returnamtinvoicetype', header:'厂商返利税率开票类型', visible:false,
				  formEditorConfig:{
			                  type:'combobox',
			              required:false,
			             textField:'name',
			            valueField:'value',
			          fieldVisible:true,
    		 params:{pid: 'invoicetype', xmlFileName:'combos/comboDict.xml'}}
			 },
			 {field:'insurancelessorratename', header:'保险费税率', 
			      formEditorConfig:{
			          fieldVisible:false,
			     fillFromFieldName:'insurancelessorrate'}},
			{field:'insurancelessorrate', header:'保险费税率', visible:false,
				  formEditorConfig:{
			                  type:'combobox',
			                  newLine:true,
			              required:false,
			             textField:'name',
			            valueField:'value',
			          fieldVisible:true,
        	 params:{pid: 'invoice_rate', xmlFileName:'combos/comboDict.xml'}}
			 },
			{field:'insurancelessorinvoicetypename', header:'保险费开票类型', 
			      formEditorConfig:{
			          fieldVisible:false,
			     fillFromFieldName:'insurancelessorinvoicetype'}},
			{field:'insurancelessorinvoicetype', header:'保险费开票类型', visible:false,
				  formEditorConfig:{
			                  type:'combobox',
			              required:false,
			             textField:'name',
			            valueField:'value',
			          fieldVisible:true,
    		 params:{pid: 'invoicetype', xmlFileName:'combos/comboDict.xml'}}
			 }, */
			 {field:'firstpaymentratename', header:'首付款税率', 
			      formEditorConfig:{
			          fieldVisible:false,
			     fillFromFieldName:'firstpaymentrate'}},
			{field:'firstpaymentrate', header:'首付款税率', visible:false,
				  formEditorConfig:{
			                  type:'combobox',
			                  newLine:true,
			              required:false,
			             textField:'name',
			            valueField:'value',
			          fieldVisible:true,
        	 params:{pid: 'invoice_rate', xmlFileName:'combos/comboDict.xml'}}
			 },
			{field:'firstpaymentinvoicetypename', header:'首付款开票类型', 
			      formEditorConfig:{
			          fieldVisible:false,
			     fillFromFieldName:'firstpaymentinvoicetype'}},
			{field:'firstpaymentinvoicetype', header:'首付款开票类型', visible:false,
				  formEditorConfig:{
			                  type:'combobox',
			              required:false,
			             textField:'name',
			            valueField:'value',
			          fieldVisible:true,
    		 params:{pid: 'invoicetype', xmlFileName:'combos/comboDict.xml'}}
			 }
		]
	});
});
</script>
</head>
<body>
<div id="id_table_container_rateinfo" style="height: 100%;"></div>
</body>
</html>