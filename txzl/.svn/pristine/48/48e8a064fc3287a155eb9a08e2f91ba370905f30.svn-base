<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发票开票回导</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>

<script type="text/javascript">
var statusdata = [{name : '未回导',value : '未回导'},{name : '已回导',value : '已回导'},{name : '全部',value : ''}];
var hcstatusdata = [{name : '未红冲',value : '未红冲'},{name : '已红冲',value : '已红冲'},{name : '已作废',value : '已作废'},{name : '全部',value : ''}];

function importMiniTableCallBack(message,tableid){
	 var info=eval("("+message+")");
	 mini.alert(info.message);
     mini.get("id_minitableimport").hide();
     mini.unmask(document.body);
     mini.get(tableid).reload();
}

jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				//导入Excel配置
				importTargetClass:'com.tenwa.leasing.entity.invoice.RentInvoiceDownloadInfo',
				importOrExPortCallBack:'checkRentInvoiceDownloadInfoImport',
				importDataIndex:'2',
				importHeaderIndex:'1',
				
				id : 'table_id1',
				width : globalClientWidth,
				height : globalClientHeight,
				title : '租金发票开票回导',
				iconCls : 'icon-node',
				multiSelect : true,
				queryButtonColSpan : 6,
				queryButtonNewLine: true,
				showPager:true,
				params:{
					backstatus:"未回导"
				},
				isRemoteStatistic: true,
				xmlFileName : '/eleasing/jsp/invoice_manage/fund_rent_invoice/fund_rent_invoice_import.xml',
				tools : ['importExcel','-',
				         {
					html : '租金发票开票模板下载',
					plain : true,
					iconCls : 'icon-addfolder',
					handler : function(miniTable, buttonText) {
						var fileTeplate=new fileTemplateConfig({
	                    	 templateno:'F-201411005',
	                    	 tableid:miniTable.config.id,
	                         modelname:miniTable.config.title,
	                         returntype:'file',
	                         parames:{
	                         }
	                         });
	                     fileTeplate.createFile();

					}
				}
				],
				columns : [ 
				    {type : 'indexcolumn'},
				   	{type : 'checkcolumn'},  
				   	{field : 'id',header : 'id',visible : false},
				   	{field : 'cid',header : 'cid',visible : false},
					{field : 'iscancel',header : '作废标志',width : 0},
				   	{field : 'outno',header : '发票编号'},
				   	{field : 'contractid',header : '合同号',queryConfig : {}},
				   	{field : 'contractnumber',header : '业务合同号',queryConfig : {},renderer:function(e){
						return "<a href='javascript:void(0);' onclick='showContractDetail(\""+e.record.cid+"\")'>" + e.record.contractnumber + "</a>";
					}},
				   	{field : 'custname',header : '客户名称',allowSort:true,queryConfig : {}},
				   	{field : 'cdate',header : '收款日期',
				   		queryConfig : {
				   			newLine: true,
							type : 'date',
							vtype : 'date',//
							range : true,//是否是范围查询
							format : 'yyyy-MM-dd',
							showTime : true
						}
				   	},
				   	{field : 'money',header : '收款金额',dataType:"currency",align:'right',summary: true},
				   	
				   	{field : 'invoiceno',header : '发票号码',queryConfig : {}},
				   	{field : 'invoicemoney',header : '含税总金额',dataType:"currency",align:'right',summary: true,
				   		formEditorConfig:{
				              vtype:'currency'
				        }
				   	},
				   	{field : 'invoicedate',header : '开票日期'},
				   	{field : 'taxmoney',header : '合计税额',dataType:"currency",align:'right',summary: true,
				   		formEditorConfig:{
				              vtype:'currency'
				        }	
				   	},
				   	{field : 'taxrate',header : '税率'},
				   	
				   	{field : 'plantype',header : '金额类型',queryConfig : {}},
				   	{field : 'taxlevel',header : '发票类型'},
				   	{field : 'taxregcode',header : '纳税人识别号'},
				   	{field : 'taxbank',header : '开户行'},
				   	{field : 'taxacc',header : '开户账号'},
				   	{field : 'taxphone',header : '开票电话'},
				   	{field : 'taxregadd',header : '开票地址'},
				   	{field : 'backstatus',header : '回导状态',
						queryConfig : 
				   	    {
							newLine: true,
							type : 'combobox',//表单域类型
							valueField : 'value',
							textField : 'name',
							allowInput : false,
							data : statusdata
					   }	
					},
					{field : 'hcstatus',header : '红冲状态',
						queryConfig : 
				   	    {
							type : 'combobox',//表单域类型
							valueField : 'value',
							textField : 'name',
							allowInput : false,
							data : hcstatusdata
					   }	
					},
					{field: 'leaseform', header:'租赁形式',
						queryConfig:{
							type:'combobox',
							params : {
								pid:'leas_form',
								xmlFileName : 'combos/comboDict.xml'
							},
							readOnly:false,
							textField:'name',
							valueField:'name'
					}}
				]
			});
		});
	});
</script>
</head>
<body></body>
</html>
<script>
function showContractDetail(cid){
	var url = getRootPath()+"/workflow/forms/contract/contract_search/fund_fund_plan_base.bi?contractid="+cid;
	openFullScreenWindow(url);
}
</script>