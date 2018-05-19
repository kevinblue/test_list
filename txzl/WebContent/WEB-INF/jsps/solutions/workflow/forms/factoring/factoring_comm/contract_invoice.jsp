<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "contract_invoice",  
			renderTo: "id_table_contract_invoice_detail",
			width: globalClientWidth - 30,
			height: 360,
			lazyLoad: false,
			title: "",
			isClickLoad:false,
			remoteOper : false,
			showPager: false,
			multiSelect: true,
			title:'',
	/* 		importOrExPortParam:{'exclid':'EXCLcheck'},//用来校验租赁物单价*数量=总价
			importTargetClass:'',//导入EXCEL对应的类
			importOrExPortCallBack:'',//导入回调方
			importHeaderIndex:'3',//标题行
			importDataIndex:'4',//数据行 */
			showToolbar: showTools,
			virtualScroll:true,
			tools: ['add', '-', 'edit', '-','remove','-','exportExcel'],
			data: JsonUtil.decode('${empty json_contract_invoice_str ? "[]" : json_contract_invoice_str }'),
			columns: [
				{type:'indexcolumn'},
				{type:'checkcolumn'},
				{field:'id',header:'id',visible: false},
				{field:'purchasername',header:'买方名称',
					  formEditorConfig:{
						      required:true,
						    labelWidth:100,
						     maxLength:120,
						       colspan:3,
						         width:'100%'}},
				{field:'invoicecode', header:'发票号',
					  formEditorConfig:{
						       newLine: true,
						    labelWidth:100,
						     maxLength:120,
						       colspan: 3,
						         width: '100%'}},
				{field:'equipprice', header:'发票金额',summary:true,
					  formEditorConfig:{
					        	  type:'text',
						         vtype:'float',
						         newLine:true}},
				{field: 'invoicedate', header:'发票日期',dateFormat:"yyyy-MM-dd",
						  formEditorConfig:{
							  otherAttributes:'onvaluechanged="judgedate"',
					                  type:'date',
					                 vtype:'date',
					                format:'yyyy-MM-dd',
					                newLine:true,
					            allowInput:false}},
			   {field: 'invoiceexpiredate', header:'发票到期日',dateFormat:"yyyy-MM-dd",
									  formEditorConfig:{
								                  type:'date',
								                 vtype:'date',
								                format:'yyyy-MM-dd',
								                newLine:true,
								            allowInput:false}},
               {field:'cenote',header:'备注',
											      formEditorConfig:{
											             maxLength:500,
											               newLine:true,
											                  type:'textarea',
											               colspan:3,
											                height:70,
											                 width:'100%'
										}}					            
			]
		});
	});
});
function judgedate(e){ 
	var start=mini.getbyName("invoicedate").getValue();
	mini.getbyName("invoiceexpiredate").setMinDate(start);  			
}
</script>
<div id="id_table_contract_invoice_detail"></div>