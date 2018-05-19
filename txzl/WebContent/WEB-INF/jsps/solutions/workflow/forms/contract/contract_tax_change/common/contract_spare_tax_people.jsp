<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "id_table_contract_spare_tax_people",
			renderTo: "id_table_contract_spare_tax_people_detail",
			width: globalClientWidth - 30,
			height: 360,
			remoteOper : true,
			multiSelect: false,
			showToolbar: showTools,
			virtualScroll:true,
			showPager:true,
			lazyLoad:false,	
			entityClassName:'com.tenwa.leasing.entity.contract.ContractTaxpeople',
		/* 	params:{"id":''}, */
		   // xmlFileName:'/eleasing/workflow/contract/contract_tax_change/contract_spare_tax_change_list.xml',
		    data: JsonUtil.decode('${empty json_tax_change_str ? "[]" : json_tax_change_str }'),
		    columns: [
				{type:'indexcolumn'},
				{type:'checkcolumn'},
				{field:'id',header:'id',visible: false},
				{field:'createdate', header:'变更日期',
					  formEditorConfig:{
						       newLine: true,
						    labelWidth:100,
						     maxLength:120,
						       colspan: 3,
						         width: '100%'}},
				{field:'taxname',header:'纳税人类别',
									  formEditorConfig:{
										      required:true,
										    labelWidth:100,
										     maxLength:120,
										       colspan:3,
										         width:'100%',
								}}, 						         
				{field:'taxregcode',header:'纳税人识别号',
					  formEditorConfig:{
						      required:true,
						    labelWidth:100,
						     maxLength:120,
						       colspan:3,
						         width:'100%',
				}},
				{field:'sparetaxbank', header:'开户行',
					  formEditorConfig:{
						       newLine: true,
						    labelWidth:100,
						     maxLength:120,
						       colspan: 3,
						         width: '100%'}},
				{field:'sparetaxacc', header:'开户账号',
					  formEditorConfig:{
							  newLine: true,
					              type:'text',
					           onkeyup:'adjustTotalPrice'}},
				{field:'spareinvoiceadd', header:'开票地址',
					  formEditorConfig:{
						  newLine:true,
					              type:'text',
					           onkeyup:'adjustTotalPrice'}},
				{field:'spareinvoicephone', header:'开票电话', 
					  formEditorConfig:{
					        	  type:'text',
				}},
			]
		});
	});
});
</script>
<div id="id_table_contract_spare_tax_people_detail"></div>