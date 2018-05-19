<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
var instrids="${requestScope['contract_info.instrids']}";
jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id : 'contract_invoice_confirm',
				renderTo: "id_contract_invoice_confirm",
				width: globalClientWidth - 10,
				height: 360,
				lazyLoad: false,
				isClickLoad:false,
				remoteOper : true,
				showToolbar: true,
				showPager: true,
				multiSelect: true,
				entityClassName : "com.tenwa.leasing.entity.invoice.FundInvoiceInfo",
				params:{
					status:"已申请",
					instrids:instrids
				},
				tools: ['remove','_','exportExcel'],
				xmlFileName : '/eleasing/jsp/invoice_manage/rent_invoice/invoice_confirm.xml',
				addOperCallBack : function (miniTable,rowData){
					$('#id_json_contract_invoice_confirm_str').val(mini.encode(miniTable.getData()));
				},
				removeOperCallBack:function(miniTable){
					$('#id_json_contract_invoice_confirm_str').val(mini.encode(miniTable.getData()));
				},
				copyOperCallBack:function(miniTable){
					$('#id_json_contract_invoice_confirm_str').val(mini.encode(miniTable.getData()));
				},
				updateOperCallBack:function(miniTable){
					$('#id_json_contract_invoice_confirm_str').val(mini.encode(miniTable.getData()));
				},
				columns : [ 
				    {type : 'indexcolumn'},
				   	{type : 'checkcolumn'},  
				   	{field : 'billtype',header : 'billtype',visible : false},
				   	{field : 'id',header : 'id',visible : false},
				   	{field : 'contractid',header : '合同号'},
				   	{field : 'contractnumber',header : '业务合同号',renderer:function(e){
						return "<a href='javascript:void(0);' onclick='showContractDetail(\""+e.record.cid+"\")'>" + e.record.contractnumber + "</a>";
					}},
				   	{field : 'custname',header : '客户名称',allowSort:true},
				   	{field : 'plantype',header : '金额类型'},
				   	{field : 'rentlist',header : '期次'},
				   	{field : 'plandate',header : '计划日期'},
				   	{field : 'planmoney',header : '计划金额',dataType:"currency",align:'right',summary: true},
				   	{field : 'incomedate',header : '实收日期'},
				   	{field : 'haveexpmoney',header : '已导出金额',dataType:"currency",summary: true},
				   	{field : 'currentmoney',header : '本次导出金额',dataType:"currency",summary: true},
				   	{field : 'invoicetype',header : '发票类型'},
				   	{field : 'taxrate',header : '税率',visible : false},
				   	{field : 'taxratename',header : '税率'},
				   	{field : 'taxregcode',header : '纳税人识别号'},
				   	{field : 'taxbank',header : '开户行及银行账号',width:250},
				   	{field : 'regaddress',header : '开票地址及电话',width:250}
				]
			});
		});
	});
</script>
</head>
<body>
<div id="id_contract_invoice_confirm"></div>
</body>
