<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>租金发票红冲</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
function importMiniTableCallBack(message,tableid){
	 var info=eval("("+message+")");
	 alert(info.message);
     mini.get("id_minitableimport").hide();
     mini.get(tableid).reload();
}

jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				addRemoteOperUrl:getRootPath()+"/acl/addTaxRentHcInfo.acl",
				remoteOper:true,
				id : 'table_id1',
				width : globalClientWidth,
				height : globalClientHeight,
				title : '租金发票红冲',
				iconCls : 'icon-node',
				multiSelect : true,
				//hiddenQueryArea : true,//是否将查询区域折叠起来
				queryButtonColSpan : 4,
				queryButtonNewLine:false,
				showPager:true,
				isRemoteStatistic: true,
				xmlFileName : '/eleasing/jsp/invoice_manage/rent_invoice/invoiceHC/rent_invoice_hc.xml',
				tools : ['add'],
				columns : [ 
				    {type : 'indexcolumn'},
				   	{type : 'checkcolumn'},  
				   	{field : 'id',header : 'id',visible : false},
				   	{field : 'outno',header : '编号',width : 120,formEditorConfig : {fieldVisible:false}},
				   	{field : 'contractid',header : '合同号',width : 120,formEditorConfig : {fieldVisible:false}},
				   	{field : 'contractnumber',header : '业务合同号',width : 120,queryConfig : {},formEditorConfig : {fieldVisible:false},renderer:function(e){
						return "<a href='javascript:void(0);' onclick='showContractDetail(\""+e.record.cid+"\")'>" + e.record.contractnumber + "</a>";
					}},
				   	{field : 'custname',header : '客户名称',width : 120,allowSort:true,queryConfig : {},formEditorConfig : {fieldVisible:false}},
				   	{field : 'cdate',header : '收款日期',
				   		queryConfig : {
							newLine : false,
							type : 'date',
							vtype : 'date',//
							range : true,//是否是范围查询
							format : 'yyyy-MM-dd',
							showTime : true
						}
				   	    ,formEditorConfig : {fieldVisible:false}
				   	},
				   	{field : 'money',header : '收款金额',width : 120,dataType:"currency",align:'right',summary: true,formEditorConfig : {fieldVisible:false}},
				   	
				   	{field : 'invoiceno',header : '发票号',width : 120,
				   		formEditorConfig : {
							type : 'combobox',//表单域类型
							required : true,//是否必填
							multiSelect : false,//combo是否可以多选
							valueField : 'id',//table显示值
							textField : 'invoiceno',//combox显示值
							labelWidth : 100,//该域标签宽度
							//width : 125,//该域总宽度
							params : {
								xmlFileName : 'eleasing/jsp/invoice_manage/rent_invoice/invoiceHC/rent_invoice_no_hc.xml'
							}
						}
				   	},
				   	{field : 'oldinvoiceno',header : '原始发票号',width : 120,formEditorConfig : {fieldVisible:false}},
				   	{field : 'invoicemoney',header : '开票金额',width : 120,dataType:"currency",align:'right',summary: true,
				   		formEditorConfig : {
				   			fillFromFieldName : 'invoiceno',
							fillProperty : 'invoicemoney',
				   			readonly:true,
				   			newLine:true
				   		}
				   	},
				   	{field : 'invoicedate',header : '开票时间',formEditorConfig : {fieldVisible:false}},
				   	{field : 'taxmoney',header : '销项税金',summary: true,formEditorConfig : {fieldVisible:false}},
				   	{field : 'taxrate',header : '税率',formEditorConfig : {fieldVisible:false}},
				   	
				   	{field : 'feetype',header : '费用类型',
				   		formEditorConfig : {
				   			fillFromFieldName : 'invoiceno',
							fillProperty : 'feetype',
				   			readonly:true,
				   			newLine:true
				   		}
				   	},
				   	{field : 'hcnumber',header : '红冲通知单号',visible : false,
				   		formEditorConfig : 
				   		{
				   			newLine:true,
				   			required : true,//是否必填
				   			fieldVisible:true
				   		}
				   	},
				   	{field : 'hcinvoicedate',header : '红冲时间',visible : false,
					   	formEditorConfig : {
							newLine : true,//新起一行来画该表单域，用于修改页面的表单域换行
							fieldVisible:true,
							type : 'date',
							required : true,//是否必填
							width : 200,
							labelWidth : 100,
							defaultValue : mini.formatDate(new Date(), "yyyy-MM-dd"),
						}
				   	},
				   	{field : 'taxlevel',header : '发票类型',formEditorConfig : {fieldVisible:false}},
				   	{field : 'taxregcode',header : '纳税人识别号',width : 120,formEditorConfig : {fieldVisible:false}},
				   	{field : 'taxbank',header : '开户行',width : 120,formEditorConfig : {fieldVisible:false}},
				   	{field : 'taxacc',header : '开户账号',width : 120,formEditorConfig : {fieldVisible:false}},
				   	{field : 'taxphone',header : '开票电话',formEditorConfig : {fieldVisible:false}},
				   	{field : 'taxregadd',header : '开票地址',width : 120,formEditorConfig : {fieldVisible:false}},
				   	{field : 'hcstatus',header : '状态',formEditorConfig : {fieldVisible:false}},
				   	{field: 'leaseform', header:'租赁形式'
				   		,formEditorConfig : {fieldVisible:false},
						queryConfig:{
							newLine : true,
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
