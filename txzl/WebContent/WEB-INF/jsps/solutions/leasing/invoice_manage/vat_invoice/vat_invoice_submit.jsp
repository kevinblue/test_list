<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript">
//状态下拉查询
var toolsFlag = '1';
jQuery(function(){
	tenwa.createTable({
		id: 'vat_invoice_info_submit',
		renderTo: 'id_table_container_vat_invoice_info_submit',
		width: '100%',
		height: '100%',
		lazyLoad: false,
		entityClassName : 'com.tenwa.leasing.entity.invoice.VatInvoiceInfo',
		title: "进项发票提交",
		remoteOper: true,
		multiSelect: true,
		showPage: true,
		hiddenQueryArea : false,
		pageSize: 20,
		showPager : true,//分页按钮是否显示
		tools: [{
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
					var id = checkedRowdata.id;
					tempIdArr.push(id);
				}
				params["ids"] = tempIdArr.join(",");
					
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
			},'edit'],
		xmlFileName: '/eleasing/jsp/invoice_manage/vat_invoice/vat_invoice_submit.xml',
		//发票号是否重复校验
		validateForm:function(miniTable, miniForm, editFormItemOperFlag, addIndex){
		if('add'== editFormItemOperFlag || 'edit' == editFormItemOperFlag){
			mini.parse();
			var invoiceId = mini.getbyName('invoiceno').getValue();
			var param = {};
			if('edit' == editFormItemOperFlag){
				var id = mini.getbyName('id').getValue();
				param.id = id;
			}
			param.invoiceId = invoiceId;
			param['xmlFileName'] = "/eleasing/jsp/invoice_manage/vat_invoice/vat_invoiceid_check.xml";
			var flag = true;
			ajaxRequest({
				params:param,
				url:'${pageContext.request.contextPath}/table/getTableData.action',
				method : 'POST',
				async : false,
				success:function(response){
					var jsondata = eval('(' + response.responseText + ')').datas;
					if(jsondata.length > 0 ){
						mini.alert('已存在该进项发票号，请重新输入！！');
						flag = false;
					}else{
						flag = true;
					}
				},
				failure:function(response){
					mini.alert("校验失败！");
					flag = false;
				}
			});
			return flag;
		}},
		columns: [
			{type: 'checkcolumn'},
 			{field: 'id', header: 'id',visible:false,formEditorConfig : {fieldVisible:false}},
 			{field: 'invoiceno', header: '发票号',queryConfig : {},formEditorConfig : {required: true},renderer: function(e){
				var invoiceid = e.record.id;
				var invoiceno = e.record.invoiceno;
				return "<a href='javascript:void(0);' onclick='opendetails(\"" + invoiceid + "\",\""+toolsFlag+"\")'>" + invoiceno + "</a>";
			}},
			{field: 'invoicemoney', header: '发票金额',align: 'right',queryConfig : {},formEditorConfig : {required: true,vtype:'float'}},
			{field: 'purchasenitsname', header: '购货单位',queryConfig : {},formEditorConfig : {fieldVisible: false}},
			{field: 'purchasenits', header: '购货单位',
				visible:false,
				formEditorConfig : {
				newLine:true,
				type : 'combobox',
				required: true,
				textField: 'name',
				valueField: 'value',
				allowInput: false,
				fieldVisible: true,
				width: 150,
				params: {
					cust_type: 'cust_type.assuror',
					xmlFileName: '/eleasing/workflow/contract/contract_approval/contract_custinfo.xml'
				}
			}},
			{field: 'suppliername', header: '供货商',queryConfig:{newLine:true},formEditorConfig : {fieldVisible: false}},
			{field: 'supplier', header: '供货商',visible:false,
				formEditorConfig : {
				type : 'combobox',
				allowInput: false,
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
			{field: 'goodsname', header: '货物名称',queryConfig : {},formEditorConfig : {newLine:true}},
			{field: 'recorddate', header: '登记日期',type:"date",format:"yyyy-MM-dd",queryConfig : {
				newLine:true,
				type: 'date',
				vtype: 'date',
				range: true,
				format: 'yyyy-MM-dd'
				},
				formEditorConfig:{
					type: 'date',
					vtype: 'date',
					format: 'yyyy-MM-dd',
					allowInput: false
				}
			},
			{field: 'contractnum',header: '合同数量',formEditorConfig : {fieldVisible :false}},
			{field: 'sumamt',header: '已登记合同金额',formEditorConfig : {fieldVisible :false}},
			{field: 'creatorname', header: '登记人',formEditorConfig:{fieldVisible: false}}
		]
	});
});
</script>
</head>
<body>
<div id="id_table_container_vat_invoice_info_submit" style="height: 100%;"></div>
<script type="text/javascript">
function opendetails(invoiceId,toolsFlag){
	var params = "invoiceId="+invoiceId+"&toolsFlag="+toolsFlag;
	var url = getRootPath()+"/leasing/invoice_manage/vat_invoice/vat_invoice_record_detail.bi?"+params;
	var sheight = window.screen.availHeight - 30;
	var swidth = window.screen.availWidth - 10;
	var winoption = "left=0px,top=0px,height=" + sheight + "px,width=" + swidth + "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
	window.open(url, '_blank', winoption);
}
</script>
</body>
</html>
