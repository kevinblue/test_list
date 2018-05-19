<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>发票认证结果导入</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>

<script type="text/javascript">
//状态下拉查询
var statusdata = [{name : '未提交',value : '0'},{name : '已提交',value : "1"},{name : '已确认',value : "2"},{name : '已退回',value : "3"},{name : '已认证',value : "4"},{name : '全部',value : ""}];
var toolsFlag = '0';
jQuery(function(){
	tenwa.createTable({
		//发票认证结果excel导入配置
		importTargetClass:'com.tenwa.leasing.entity.invoice.VatInvoiceInfo',
		importOrExPortCallBack:'checkVatInvoiceDownloadInfoImport',
		importDataIndex:'2',
		importHeaderIndex:'1',
		
		id: 'vat_authentication_imp',
		renderTo: 'id_table_container_vat_invoice_import',
		width: '100%',
		height: '100%',
		lazyLoad: false,
		entityClassName : 'com.tenwa.leasing.entity.invoice.TaxVatUploadInfo',
		title: "发票认证结果导入",
		editFormPopupWindowWidth:700,
		editFormPopupWindowHeight:250,
		remoteOper: true,
		showPage: true,
		hiddenQueryArea : false,
		pageSize: 20,
		queryButtonColSpan: 2,
		showPager : true,//分页按钮是否显示
		tools:['importExcel','-',
		{
			html : '导出进项开票数据',
			plain : true,
			iconCls : 'icon-addfolder',
			handler : function(miniTable, buttonText) {
				var fileTeplate=new fileTemplateConfig({
                	 templateno:'F-201412006',
                	 tableid:miniTable.config.id,
                	 modelname:miniTable.config.title,
                     returntype:'file',
                     parames:{
                     }
                     });
                 fileTeplate.createFile();
			}
		}],
		params:{invoicestatusname:"2"},
		xmlFileName: '/eleasing/jsp/invoice_manage/vat_invoice/vat_invoice_import.xml',
		showPager : true,//分页按钮是否显示
		columns: [
			{type : 'checkcolumn'},  
			{field: 'id', header: 'id',visible:false,formEditorConfig : {fieldVisible: false}},
 			{field: 'invoiceno', header: '发票号',queryConfig : {},formEditorConfig : {required: true,width:180}},
			{field: 'invoicemoney', header: '发票金额',align: 'right',dataType:'currency',queryConfig : {},
				formEditorConfig : {
				required: true,
				width:180,
				vtype: 'float'
			}},
			{field: 'sumamt',header: '已登记合同金额',align: 'right',dataType:'currency',formEditorConfig : {fieldVisible :false}},
			{field: 'contractinfo',header: '发票信息',formEditorConfig : {fieldVisible :false}},
			{field: 'contractno',header: '合同号',visible :false,queryConfig:{fieldVisible:true}},
			{field: 'purchasenits', header: '购货单位',queryConfig:{newLine:true},formEditorConfig : {newLine: true}},
			
			{field: 'suppliername', header: '供货商',queryConfig:{},formEditorConfig : {fieldVisible: false}},
			{field: 'supplier', header: '供货商 ',visible:false,
				formEditorConfig : {
				//type:'combobox'
				//newLine: true,
				width: 200,
				type : 'ComboBox',
				required: true,
				textField: 'name',
				valueField: 'value',
				fieldVisible: true,
				width: 150,
				colspan:3,
				params: {
					cust_type: 'cust_type.vndr',
					xmlFileName: '/eleasing/workflow/contract/contract_approval/contract_custinfo.xml'
				}
			}},
			{field: 'goodsname', header: '货物名称', queryConfig : {},formEditorConfig : {newLine:true,width:200}},
			{field: 'recorddate', header: '登记日期',type:"date",format:"yyyy-MM-dd",queryConfig : {
				newLine:true,
				type: 'date',
				vtype: 'date',
				range : true,
				format: 'yyyy-MM-dd'
			},formEditorConfig : {
				width:180,
				type: 'date',
				vtype: 'date',
				format: 'yyyy-MM-dd'
			}},

			{field: 'invoicestatusname', header: '发票状态',formEditorConfig : {fieldVisible :false,newLine : true},
				queryConfig : {
					colspan : 1,
					type : 'combobox',//表单域类型
					valueField : 'value',
					textField : 'name',
					allowInput : false,
					showNullItem : false,
					//defaultValue:'0',
					data : statusdata
				}
			},
			{field: 'creatorname', header: '登记人',formEditorConfig:{fieldVisible :false}},
			{field: 'memo', header: '备注',visible: false,
				formEditorConfig : {
					type : 'textarea',
					colspan : 3,
					width : 300,
					newLine : true,
					fieldVisible :true
				}
			},
			{field: 'taxrate',header:"税率"},
			{field: 'taxmoney',header:"税额",dataType:'currency',align: 'right'},
			{field: 'certificationresults',header:"认证结果"},
			{field: 'certificationdate',header:"认证时间"},
			{field: 'invoicedate',header:"开票时间"}
		]
	});
});
function importMiniTableCallBack(message,tableid){
	 var info=eval("("+message+")");
	 alert(info.message);
	 var tabledate=info.tabledate;
	 if(""!=tabledate){
	    var grid=mini.get(tableid);
       grid.reload();
	 }
    mini.get("id_minitableimport").hide();
    mini.unmask(document.body);
}
</script>
</head>	
<body>
<div id="id_table_container_vat_invoice_import" style="height: 100%;"></div>
</body>
</html>
