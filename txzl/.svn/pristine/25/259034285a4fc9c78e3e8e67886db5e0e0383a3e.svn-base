<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<input  style="display:none;"	class="mini-textarea" id="id_json_csut_apply_list_str" name="json_csut_apply_list_str" value='${empty json_csut_apply_list_str ? "[]" : json_csut_apply_list_str }'></input>
<script type="text/javascript">
jQuery(function(){
	//alert(contractid);
	var showTools = true;
	//if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;}; 
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "proj_invoice",  
			renderTo: "id_table_proj_invoice_detail",
			width: globalClientWidth - 30,
			height: 360,
			lazyLoad: false,
			title: "",
			isClickLoad:false,
			remoteOper : true,
			entityClassName : "com.tenwa.leasing.entity.contract.ContractEquip",
			showPager: false,
			multiSelect: true,
			title:'',
			showToolbar: showTools,
			virtualScroll:true,
		 	params:{id:contractid},
			tools: ['edit','-','exportExcel'],/* 'add', '-',  '-','remove', */
			xmlFileName:'/eleasing/workflow/proj/proj_account_rece/proj_transfer_account_invoice.xml',
			columns: [   
				{type:'indexcolumn'},
				{type:'checkcolumn'},
				{field:'id',header:'id',visible: false},
				
				
				{
					field:'contractid',
					header:'合同id',
					visible: false,
					formEditorConfig:{
						fieldVisible:false,
						value:contractid
					         }
				},
				{field:'purchasername',header:'买方名称',
					  formEditorConfig:{
						      required:true,
						    labelWidth:100,
						     maxLength:120,
						       readOnly : true,
						         width:'100%'}},
				{field:'invoicecode', header:'发票号',
					  formEditorConfig:{
						  readOnly : true,
						       newLine: false,
						    labelWidth:100,
						     maxLength:120,
						       colspan: 3,
						         width: '100%'}},
				{field:'equipprice', header:'发票金额',align:'left',summary:true,
					  formEditorConfig:{
						  readOnly : true,
					        	  type:'text',
						         vtype:'float',
						         newLine:true}},
				{field: 'invoicedate', header:'发票日期',dateFormat:"yyyy-MM-dd",
						  formEditorConfig:{
							  readOnly : true,
					                  type:'date',
					                 vtype:'date',
					                format:'yyyy-MM-dd',
					            allowInput:false}},
			   {field: 'invoiceexpiredate', header:'发票到期日',dateFormat:"yyyy-MM-dd",
									  formEditorConfig:{
										  readOnly : true,
								                  type:'date',
								                 vtype:'date',
								                format:'yyyy-MM-dd',
								                newLine:true,
								            allowInput:false}},
	            {field: 'transferdate', header:'转让日期',dateFormat:"yyyy-MM-dd",
					  formEditorConfig:{
				                  type:'date',
				                 vtype:'date',
				                 required:true,
				                format:'yyyy-MM-dd',
				            allowInput:false}},
			  {field:'equipstatusname',header:'转让状态',visible: true, formEditorConfig : {fieldVisible : false}}	,			            
		 	  {
				   field: 'equipstatus', 
				   header:'转让状态',
				   visible: false,
		            formEditorConfig : {
		            	newLine: true,
						type : 'combobox',
						textField : 'name',
						required : true,
						valueField : 'value',
						fieldVisible : true,
				        data : [{value:'1',name:'未转让'},{value:'2',name:'已转让'}]
					}	            
								            
			 	} 
			]
		});
	});
});
</script>
<div id="id_table_proj_invoice_detail" style="height: 100%;"></div>
