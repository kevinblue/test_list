<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>租金发票开票回导</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>

<script type="text/javascript">
var statusdata = [{name : '未回导',value : '未回导'},{name : '已回导',value : '已回导'},{name : '全部',value : ''}];
var hcstatusdata = [{name : '未红冲',value : '未红冲'},{name : '已红冲',value : '已红冲'},{name : '全部',value : ''}];

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
				title : '发票接口操作',
				iconCls : 'icon-node',
				multiSelect : true,
				//hiddenQueryArea : true,//是否将查询区域折叠起来
				queryButtonColSpan : 6,
				queryButtonNewLine: false,
				showPager:true,
				params:{
					backstatus:"未回导"
				},
				isRemoteStatistic: true,
				xmlFileName : '/eleasing/jsp/invoice_manage/rent_invoice/rent_invoice_import.xml',
				tools : [
				   {
					html : '发票生成',
					plain : true,
					iconCls : 'icon-addfolder',
					handler : function(miniTable, buttonText) {
						var checkedRowDatas = miniTable.getSelecteds();
						var operType = "生成发票";
						var operAction = "voidedInvoiceToSajt";
						var params = {};
						if(!window.confirm(("确定"+operType+"当前"+checkedRowDatas.length+"条记录么？"))) return;
						var url=getRootPath()+"/acl/"+operAction+".acl";
						//遮罩
						mini.mask({
							el : document.body,
							cls : 'mini-mask-loading',
							html : '数据操作中 请稍等...'
						});
						jQuery.ajax({
							url : url,
							type : 'POST',
							timeout : 30 * 1000,
							data :params,
							dataType : 'json',
							error : function(res, errInfo, e) {
								mini.unmask(document.body);
							},
							success : function(resultJson) {
								var returnState = resultJson.returnType;
								switch (returnState) {
								case "SUCCESS": {
									miniTable.reload();
									mini.unmask(document.body);
									mini.alert(resultJson.content);
									break;
								}
								case "FAILURE": {
									miniTable.reload();
									mini.unmask(document.body);
									mini.alert("生成失败！"+resultJson.content);
									break;
								}
								}
							}
						});
					 }
				    },'-',
				    {
						html : '发票作废',
						plain : true,
						iconCls : 'icon-addfolder',
						handler : function(miniTable, buttonText) {
						 }
					    },'-',
					    {
							html : '发票红冲',
							plain : true,
							iconCls : 'icon-addfolder',
							handler : function(miniTable, buttonText) {
							 }
						    },'-',
						    {
								html : '发票打印',
								plain : true,
								iconCls : 'icon-addfolder',
								handler : function(miniTable, buttonText) {
								 }
							    }
				],
				columns : [ 
				    {type : 'indexcolumn'},
				   	{type : 'checkcolumn'},  
				   	{field : 'id',header : 'id',visible : false},
				   	{field : 'outno',width : 120,header : '发票编号'},
				   	{field : 'contractid',header : '合同号',width : 120},
				   	{field : 'contractnumber',header : '业务合同号',width : 120,queryConfig : {},renderer:function(e){
						return "<a href='javascript:void(0);' onclick='showContractDetail(\""+e.record.cid+"\")'>" + e.record.contractnumber + "</a>";
					}},
				   	{field : 'custname',header : '客户名称',width : 120,allowSort:true,queryConfig : {}},
				   	{field : 'cdate',header : '收款日期',
				   		queryConfig : {
				   			newLine: false,
							type : 'date',
							vtype : 'date',//
							range : true,//是否是范围查询
							format : 'yyyy-MM-dd',
							showTime : true
						}
				   	},
				   	{field : 'money',header : '收款金额',width : 120,dataType:"currency",align:'right',summary: true},
				   	
				   	{field : 'invoiceno',header : '发票号码',width : 120,queryConfig : {newLine: true}},
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
				   	{field : 'taxregcode',width : 120,header : '纳税人识别号'},
				   	{field : 'taxbank',width : 120,header : '开户行'},
				   	{field : 'taxacc',width : 120,header : '开户账号'},
				   	{field : 'taxphone',header : '开票电话'},
				   	{field : 'taxregadd',width : 120,header : '开票地址'},
				   	{field : 'backstatus',header : '发票状态',
						queryConfig : 
				   	    {
							newLine: false,
							width:200,
							type : 'combobox',//表单域类型
							valueField : 'value',
							textField : 'name',
							allowInput : false,
							
							//defaultValue:'1',
							data : statusdata
					   }	
					},
					{field : 'hcstatus',header : '红冲状态',
						queryConfig : 
				   	    {
							newLine: true,
							type : 'combobox',//表单域类型
							valueField : 'value',
							textField : 'name',
							allowInput : false,
							//defaultValue:'1',
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
