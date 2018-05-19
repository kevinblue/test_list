<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<input style="display: none;" class="mini-textarea"
	id="id_json_csut_apply_list_str" name="json_csut_apply_list_str"
	value='${empty json_csut_apply_list_str ? "[]" : json_csut_apply_list_str }'></input>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/seajs/sea.js"></script>
<script type="text/javascript">
	jQuery(function() {
		valueContractId = mini.getbyName("contract_info.contractid").getValue();
	});
	jQuery(function() {
		seajs
				.use(
						[ "js/apcomponent/aptable/aptable.js" ],
						function(ApTable) {
							new ApTable(
									{
										id : "proj_invoice",
										renderTo : "id_table_proj_invoice_detail",
										width : globalClientWidth - 30,
										title : '进项发票信息',
										height : 360,
										lazyLoad : false,
										isClickLoad : false,
										showPager : false,
										multiSelect : true,
										hiddenQueryArea : true,
										queryButtonColSpan : 6,
										queryButtonNewLine : false,
										showToolbar : true,
										virtualScroll : true,
										params : {
											contractid : contractid
										},
										 beforeShowWindowCallBack : function(miniTable, miniForm, operFlag) {
											if ("add" == operFlag|| "edit" == operFlag) {
												mini.getbyName("contractid").setValue(ciid);
												mini.getbyName("invoicestatus").setValue(1);
											}
											return true;
										}, 
										remoteOper : true,
										entityClassName : "com.tenwa.leasing.entity.invoice.VatInvoiceInfo",
										xmlFileName : '/eleasing/jsp/invoice_manage/vat_invoice/vat_invoice_record_detailinfo.xml',
										tools : [ 'add', '-', 'edit', '-',
												'remove', '-', 'exportExcel' ],
										columns : [
												{
													type : 'indexcolumn'
												},
												{
													type : 'checkcolumn'
												},
												{
													field : 'id',
													header : 'id',
													visible : false
												},
												{
													field : 'contractid',
													header : '合同ID',
													visible : false,
													formEditorConfig : {
														fieldVisible : false
													}
												},
												{
													field : 'contractnumber',
													header : '合同编号',
													width : 120,
													visible : false,
													formEditorConfig : {
														fieldVisible : false,
														value : valueContractId
													}
												},
												{
													field : 'invoiceno',
													header : '发票号码',
													formEditorConfig : {
														width : 230,
														fieldVisible : true,
														required : true
													},
													queryConfig : {
														newLine : true
													}
												},
												{
													field : 'invoicemoney',
													header : '发票金额',
													queryConfig : {},
													formEditorConfig : {
														width : 230,
														type : 'float',
														vtype : 'float',
														required : true,
														fieldVisible : true
													}
												},
												{
													field : 'purchasenits',
													header : '购货单位',
													formEditorConfig : {
														width : 230,
														fieldVisible : true,
														newLine : true,
														required : true
													}
												},
												{
													//显示供应商名字，保存供应商ID
													field : 'suppliername',
													header : '供应商',
													visible : true,
													formEditorConfig : {
														fieldVisible : false
													}
												},
												{
													field : 'supplier',
													header : '供应商',
													visible : false,
													formEditorConfig : {
														width:230,
														type : 'queryinput',
														textField : 'suppliername',
														valueField : 'supplier',
														params : {
															xmlFileName : '/eleasing/jsp/invoice_manage/vat_invoice/vat_invoice_record_supplier.xml',
														},
														fieldVisible : true,
														allowinput:false,
														required:true
													}
												},
												{
													field : 'recorddate',
													header : '开票日期',
													width:120,
													queryConfig : {
														allowInput : false,
														type : 'date',
														format : 'yyyy-MM-dd'
													},
													formEditorConfig : {
														newLine : true,
														fieldVisible : true,
														allowInput : false,
														width : 230,
														type : 'date',
														defaultValue : mini.formatDate(new Date(),"yyyy-MM-dd"),
													}
												},
												{
													field : 'goodsname',
													header : '货物名称',
													visible : true,
													queryConfig : {
														newLine : true
													},
													formEditorConfig : {
														width : 230,
														required:true,
														fieldVisible : true
													}
												},
												{
													//显示的是款项，存的是设备的ID
													field : 'contractfundfundplanidname',
													header : '计划款明细',
													visible : true,
													width : 130,
													formEditorConfig : {
														type : 'text',
														fieldVisible : false
													}
												},
												{
													field : 'contractfundfundplanid',
													header : '计划款明细 ',
													visible : false,
													formEditorConfig : {
														type : 'queryinput',
														textField : 'contractfundfundplanidname',
														valueField : 'contractfundfundplanid',
														width : 230,
														params : {
															contractid : ciid,
															xmlFileName : '/eleasing/jsp/invoice_manage/vat_invoice/vat_invoice_record_equipsubentry.xml',
														},
														fieldVisible : true,
														allowinput:false,
														newLine : true,
														required:true
													}
												},
												{
													field : 'memo',
													header : '备注',
													width : 200,
													visible : true,
													formEditorConfig : {
														type : 'textarea',
														width : "100%",
														height : 70,
														colspan : 4,
														newLine : true,
														fieldVisible : true
													}
												},
												{
													field:'invoicestatusname',
													header:'状态',
													visible:true,
													formEditorConfig:{
														fieldVisible:false
													}
												},
												{
													field:'invoicestatus',
													header:'状态',
													textField:'name',
													valueField:'value',
													visible:false,
													data:[{name:'未提交',value:0},{name:'已提交',value:1},{name:'已确认',value:2},{name:'已退回',value:3}],
													formEditorConfig:{
														fieldVisible:false
													}
												}]
									});
						});
	});
</script>
<div id="id_table_proj_invoice_detail" style="height: 100%;"></div>
