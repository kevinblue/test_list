<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "id_table_contract_cust",
			renderTo: "id_table_contract_cust_detail",
			width: globalClientWidth - 30,
			height: 360,
			multiSelect: false,
			showToolbar: showTools,
			//virtualScroll:true,
			showPager:true,
			lazyLoad:false,	
			//entityClassName:'com.tenwa.leasing.entity.contract.ContractTaxpeople',
		    params:{"custid":"${requestScope['contract_info.custid']}"},
		    xmlFileName:'/eleasing/workflow/contract/contract_tax_change/contract_cust.xml',
		    //data: JsonUtil.decode('${empty json_tax_change_str ? "[]" : json_tax_change_str }'),
		    columns: [
				{type:'indexcolumn'},
				{type:'checkcolumn'},
				{field:'id',header:'id',visible: false},
				{field:'contractid', header:'合同编号'},
				{field:'contractnumber', header:'业务合同编号'},
				{field:'projectname', header:'项目名称'},
				{field:'taxregtype', header:'纳税人类型'},
				{field:'taxregcode', header:'合同状态'},
				{field:'taxbank', header:'开户行'},
				{field:'taxacc', header:'开户账号'},
				{field:'invoiceadd', header:'开票地址'},
				{field:'invoicephone', header:'开票电话'}
				
				
			]
		});
	});
});
</script>
<div id="id_table_contract_cust_detail"></div>