<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>进项发票登记</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>

<script type="text/javascript">
//状态下拉查询
var statusdata = [{name : '未提交',value : '0'},{name : '已提交',value : "1"},{name : '已确认',value : "2"},{name : '已退回',value : "3"},{name : '全部',value : ""}];
var toolsFlag = '0';
jQuery(function(){
	tenwa.createTable({
		id: 'vat_invoice_info',
		renderTo: 'id_table_container_vat_invoice_info',
		width: '100%',
		height: '100%',
		lazyLoad: false,
		entityClassName : 'com.tenwa.leasing.entity.invoice.VatInvoiceInfo',
		title: "进项发票登记",
		editFormPopupWindowWidth:700,
		editFormPopupWindowHeight:250,
		multiSelect : true,
		remoteOper: true,
		showPage: true,
		hiddenQueryArea : false,
		pageSize: 20,
		queryButtonColSpan: 2,
		params:{invoicestatusname:"0','3"},
		xmlFileName: '/eleasing/jsp/invoice_manage/vat_invoice/vat_invoice_record.xml',
		confirmRemoveCallBack: function(miniTable){
			var checkedRowDatas = miniTable.getSelecteds();
			for(var i = 0;i<checkedRowDatas.length;i++){
				var contractnum = checkedRowDatas[i].contractnum;
				var sumamt = checkedRowDatas[i].sumamt;
				var invoiceStatus = checkedRowDatas[i].invoicestatusname;
				if(invoiceStatus != '未提交' && invoiceStatus!='已退回'){
					mini.alert("请选择未提交或已退回的数据");
					return false;
				}
				if(parseInt(contractnum) > 0 || parseFloat(sumamt) > 0){
					mini.alert("发票已关联到相关合同，删除之前请点击修改，删除发票下关联合同");
					return false;
				}
			}
			return true;
		},
		showPager : true,//分页按钮是否显示
		tools: [{
			html : '新增',
			plain : true,
			iconCls : 'icon-add',
			handler : function(miniTable, buttonText) {
				var buttonFlag = 'add';
				var url = getRootPath()+"/leasing/invoice_manage/vat_invoice/vat_invoice_record_detail.bi?invoiceId=0&buttonFlag="+buttonFlag;
				var sheight = window.screen.availHeight - 30;
				var swidth = window.screen.availWidth - 10;
				var winoption = "left=0px,top=0px,height=" + sheight + "px,width=" + swidth + "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
				window.open(url, '_blank', winoption);
			}
			}, '-', 
			{
				html : '修改',
				plain : true,
				iconCls : 'icon-edit',
				handler : function(miniTable, buttonText) {
					var checkedRowDatas = miniTable.getSelecteds();
					if(checkedRowDatas.length>1 ){
						mini.alert("只能选择单条数据进行修改操作！");
						return false;
					}
					var invoiceStatus = checkedRowDatas[0].invoicestatusname;
					if(invoiceStatus != '未提交' && invoiceStatus!='已退回'){
						mini.alert("请选择未提交或已退回的数据");
						return false;
					};
					var buttonFlag = 'edit';
					var row = miniTable.getSelected();
					var invoiceId = row.id;
					var url = getRootPath()+"/leasing/invoice_manage/vat_invoice/vat_invoice_record_detail.bi?invoiceId="+invoiceId+"&buttonFlag="+buttonFlag;
					var sheight = window.screen.availHeight - 30;
					var swidth = window.screen.availWidth - 10;
					var winoption = "left=0px,top=0px,height=" + sheight + "px,width=" + swidth + "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
					window.open(url, '_blank', winoption);
				}
			}
			, '-','remove', '-',{
			html : '提交',
			plain : true,
			iconCls : 'icon-addfolder',
			handler : function(miniTable, buttonText) {
				var checkedRowDatas = miniTable.getSelecteds();
				var operType = "提交";
				var operTitle = "发票清单";
				var operAction = "submitVatInvoice";
				if(0 == checkedRowDatas.length){
					mini.alert("请选择要"+operType+"的记录！！");
					return;
				}
			    var params = {};
			    var tempIdArr = [];
			    for(var i = 0;i<checkedRowDatas.length;i++){
					var checkedRowdata = checkedRowDatas[i];
					var invoiceStatus = checkedRowdata.invoicestatusname;
					if(invoiceStatus != '未提交' && invoiceStatus!='已退回'){
						mini.alert("请选择未提交或已退回的数据");
						return false;
					};
					if(checkedRowdata.contractinfo == ''||checkedRowdata.contractinfo==null){
						mini.alert("发票号"+checkedRowdata.invoiceno+"未关联合同,请检查数据后在操作");
						return false;
					}
					var id = checkedRowdata.id;
					tempIdArr.push(id);
				}
				params["ids"] = tempIdArr.join(",");
				mini.confirm("确定"+operType+"当前条记录么？","提交",
					function(action){
						if("ok" == action){
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
								data : params,
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
										mini.alert(operType+operTitle + " 成功！");
										break;
									}
									case "FAILURE": {
										mini.unmask(document.body);
										mini.alert(operType+operTitle + " 失败！");
										break;
									}
									}
								}
							});
							
						}
					});
			}
			}],
		columns: [
			{type: 'checkcolumn'},
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
			}
		]
	});
});
function refresh(){
	mini.get('vat_invoice_info').reload();
}
</script>
</head>	
<body>
<div id="id_table_container_vat_invoice_info" style="height: 100%;"></div>
</body>
</html>

